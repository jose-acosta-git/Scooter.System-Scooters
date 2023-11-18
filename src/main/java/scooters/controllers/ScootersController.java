package scooters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import scooters.dtos.LocationDto;
import scooters.dtos.ScooterDto;
import scooters.dtos.ScooterWithDistanceDto;
import scooters.dtos.ScooterWithTimeDto;
import scooters.model.Scooter;
import scooters.model.Stop;
import scooters.repositories.ScootersRepository;
import scooters.services.ScootersService;

@RestController
@RequestMapping("/scooters")
public class ScootersController {
	
	@Autowired
	private ScootersRepository scootersRepository;
	
	@Autowired
	private ScootersService scootersService;
	
	@PostMapping
	public ResponseEntity<Scooter> create(HttpServletRequest request, @RequestBody ScooterDto dto) {
		return scootersService.save(request, dto);
	}
	
	@GetMapping
	public ResponseEntity<List<Scooter>> findAll(HttpServletRequest request) {
        return scootersService.findAll(request);
	}
	
	@GetMapping("/{scooterId}")
	public ResponseEntity<Scooter> getById(HttpServletRequest request, @PathVariable int scooterId) {
		return scootersService.getById(request, scooterId);
	}
	
    @PatchMapping("/{scooterId}/startMaintenance")
    public ResponseEntity<Scooter> startMaintenance(HttpServletRequest request, @PathVariable int scooterId) {
        return scootersService.startMaintenance(request, scooterId);
    }
    
    @PatchMapping("/{scooterId}/finishMaintenance")
    public ResponseEntity<Scooter> finishMaintenance(HttpServletRequest request, @PathVariable int scooterId) {
        return scootersService.finishMaintenance(request, scooterId);
    }
    
    @DeleteMapping("/{scooterId}")
    public ResponseEntity<String> removeScooter(HttpServletRequest request, @PathVariable int scooterId) {
        return scootersService.removeScooter(request, scooterId);
    }  
    
    @GetMapping("/{scooterId}/currentStop")
    public ResponseEntity<Stop> currentStop(HttpServletRequest request, @PathVariable int scooterId) {
    	return scootersService.currentStop(request, scooterId);
    }
    
    @PatchMapping("/{scooterId}/updateLocation")
    public ResponseEntity<Scooter> updateLocation(HttpServletRequest request, @PathVariable int scooterId, @RequestBody LocationDto location) {
    	return scootersService.updateLocation(request, scooterId, location);
    }

    @GetMapping("/orderedByDistance")
    public ResponseEntity<List<ScooterWithDistanceDto>> getOrderedByDistance(HttpServletRequest request) {
		return scootersService.getOrderedByTotalDistance(request);
    }

    @GetMapping("/orderedByTotalTime/{includePauses}")
    public ResponseEntity<List<ScooterWithTimeDto>> getOrderedByTotalTime(HttpServletRequest request, @PathVariable boolean includePauses) {
    	return scootersService.getOrderedByTotalTime(request, includePauses);
    }
}