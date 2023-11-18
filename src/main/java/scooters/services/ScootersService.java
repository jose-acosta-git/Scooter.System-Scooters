package scooters.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import scooters.dtos.LocationDto;
import scooters.dtos.ScooterDto;
import scooters.model.Scooter;
import scooters.model.Stop;
import scooters.repositories.ScootersRepository;
import scooters.repositories.StopsRepository;

@Service
public class ScootersService {
	
	@Autowired
	private ScootersRepository scootersRepository;
	@Autowired
	private StopsRepository stopsRepository;
	@Autowired
	private AuthService authService;
	
	public ResponseEntity<Scooter> save(HttpServletRequest request, ScooterDto dto) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getRoleFromToken(token);
		if (role != null && (role.equals("ADMIN") || role.equals("MAINTENANCE"))) {
			return ResponseEntity.ok(scootersRepository.save(convertToEntity(dto)));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
    
	public ResponseEntity<Scooter> startMaintenance(HttpServletRequest request, int scooterId) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getRoleFromToken(token);
		if (role == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else if (role.equals("USER")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

        Optional<Scooter> scooterOptional = scootersRepository.findById(scooterId);
        if (scooterOptional.isPresent()) {
            Scooter scooter = scooterOptional.get();
            if (scooter.getStatus().equals("maintenance")) {
        		return ResponseEntity.badRequest().build();
            }
            scooter.setStatus("maintenance");
            return ResponseEntity.ok(scootersRepository.save(scooter));
        }
        return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Scooter> finishMaintenance(HttpServletRequest request, int scooterId) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getRoleFromToken(token);
		if (role == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else if (role.equals("USER")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

        Optional<Scooter> scooterOptional = scootersRepository.findById(scooterId);
        if (scooterOptional.isPresent()) {
            Scooter scooter = scooterOptional.get();
            if (!scooter.getStatus().equals("maintenance")) {
        		return ResponseEntity.badRequest().build();
            }
            scooter.setStatus("available");
            scooter.setLastMaintenanceDate(LocalDate.now());
            return ResponseEntity.ok(scootersRepository.save(scooter));
        }
        return ResponseEntity.notFound().build();
	}
    
	public ResponseEntity<String> removeScooter(HttpServletRequest request, int scooterId) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getRoleFromToken(token);
		if (role == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else if (role.equals("USER")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
        Optional<Scooter> scooterOptional = scootersRepository.findById(scooterId);
        if (scooterOptional.isPresent()) {
            Scooter scooter = scooterOptional.get();
            if (scooter.getStatus().equals("in-use")) {
            	return ResponseEntity.badRequest().build();
            }
            scootersRepository.deleteById(scooterId);
            return ResponseEntity.ok("Scooter removed successfully");
        }
        return ResponseEntity.notFound().build();
	}
	
	private Scooter convertToEntity(ScooterDto dto) {
		return new Scooter(dto.getLatitude(), dto.getLongitude(), dto.getLastMaintenanceDate());
	}

	public ResponseEntity<Scooter> getById(HttpServletRequest request, int scooterId) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (!authService.isTokenValid(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		Optional<Scooter> scooterOptional = scootersRepository.findById(scooterId);
        if (scooterOptional.isPresent()) {
            return ResponseEntity.ok(scooterOptional.get());
        }
        return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Stop> currentStop(HttpServletRequest request, int scooterId) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (!authService.isTokenValid(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		Optional<Scooter> optionalScooter = scootersRepository.findById(scooterId);
		if (!optionalScooter.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Scooter scooter = optionalScooter.get();
		List<Stop> stops = stopsRepository.findAll();
		for (Stop stop : stops) {
			if (locationIsWithinStop(scooter, stop)) {
				return ResponseEntity.ok(stop);
			}
		}
		return ResponseEntity.ok(null);
	}

	private boolean locationIsWithinStop(Scooter scooter, Stop stop) {
		// The tolerance degrees used roughly equate to 111 meters
		double tolerance = 0.001;
		double latDiff = Math.abs(scooter.getLatitude() - stop.getLatitude());
		double lonDiff = Math.abs(scooter.getLongitude() - stop.getLongitude());
		
		return latDiff <= tolerance && lonDiff <= tolerance;
	}

	public ResponseEntity<Scooter> updateLocation(HttpServletRequest request, int scooterId, LocationDto location) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (!authService.isTokenValid(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	
		Optional<Scooter> optionalScooter = scootersRepository.findById(scooterId);
		if (!optionalScooter.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Scooter scooter = optionalScooter.get();
		scooter.setLatitude(location.getLatitude());
		scooter.setLongitude(location.getLongitude());
		return ResponseEntity.ok(scootersRepository.save(scooter));
	}

	public ResponseEntity<List<Scooter>> findAll(HttpServletRequest request) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getRoleFromToken(token);
		if (role != null && (role.equals("ADMIN") || role.equals("MAINTENANCE"))) {
			return ResponseEntity.ok(scootersRepository.findAll());
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
}