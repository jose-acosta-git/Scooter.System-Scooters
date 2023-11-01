package scooters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scooters.dtos.ScooterDto;
import scooters.model.Scooter;
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
	
    @PatchMapping("/{scooterId}/status")
    public ResponseEntity<Scooter> updateScooterStatus(@PathVariable int scooterId, @RequestBody String newStatus) {
        return scootersService.updateScooterStatus(scooterId, newStatus);
    }

}
