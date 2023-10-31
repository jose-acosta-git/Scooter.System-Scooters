package scooters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public Scooter create(ScooterDto dto) {
		return scootersService.save(dto);
	}
	
	@GetMapping
	public List<Scooter> findAll() {
		return scootersRepository.findAll();
	}

}
