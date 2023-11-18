package scooters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import scooters.dtos.StopDto;
import scooters.model.Stop;
import scooters.repositories.StopsRepository;

@Service
public class StopsService {
	
	@Autowired
	private StopsRepository stopsRepository;
	@Autowired
	private AuthService authService;
	
	public ResponseEntity<Stop> save(HttpServletRequest request, StopDto dto) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getRoleFromToken(token);
		if (role == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (!role.equals("ADMIN")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();	
		}
		return ResponseEntity.ok(stopsRepository.save(convertToEntity(dto)));
	}
	
	private Stop convertToEntity(StopDto dto) {
		return new Stop(dto.getLatitude(), dto.getLongitude());
	}

	public ResponseEntity<List<Stop>> findAll(HttpServletRequest request) {
		String token = authService.getTokenFromRequest(request);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getRoleFromToken(token);
		if (role == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (!role.equals("ADMIN")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();	
		}
		return ResponseEntity.ok(stopsRepository.findAll());
	}
}