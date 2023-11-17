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
	public Scooter create(@RequestBody ScooterDto dto) {
		return scootersService.save(dto);
	}
	
	@GetMapping
	public List<Scooter> findAll() {
		return scootersRepository.findAll();
	}
	
	@GetMapping("/{scooterId}")
	public ResponseEntity<Scooter> getById(@PathVariable int scooterId) {
		return scootersService.getById(scooterId);
	}
	
    @PatchMapping("/{scooterId}/startMaintenance")
    public ResponseEntity<Scooter> startMaintenance(@PathVariable int scooterId) {
        return scootersService.startMaintenance(scooterId);
    }
    
    @PatchMapping("/{scooterId}/finishMaintenance")
    public ResponseEntity<Scooter> finishMaintenance(@PathVariable int scooterId) {
        return scootersService.finishMaintenance(scooterId);
    }
    
    @DeleteMapping("/{scooterId}")
    public ResponseEntity<String> removeScooter(@PathVariable int scooterId) {
        return scootersService.removeScooter(scooterId);
    }  
    
    @GetMapping("/{scooterId}/currentStop")
    public ResponseEntity<Stop> currentStop(HttpServletRequest request, @PathVariable int scooterId) {
    	return scootersService.currentStop(request, scooterId);
    }
    
    @PatchMapping("/{scooterId}/updateLocation")
    public ResponseEntity<Scooter> updateLocation(@PathVariable int scooterId, @RequestBody LocationDto location) {
    	return scootersService.updateLocation(scooterId, location);
    }
}