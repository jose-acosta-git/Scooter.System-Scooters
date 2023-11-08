package scooters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scooters.dtos.LocationDto;
import scooters.dtos.StopDto;
import scooters.model.Stop;
import scooters.repositories.StopsRepository;
import scooters.services.StopsService;

@RestController
@RequestMapping("/stops")
public class StopsController {
	
	@Autowired
	private StopsService stopsService;
	
	@Autowired
	private StopsRepository stopsRepository;
	
	@PostMapping
	public Stop create(@RequestBody StopDto dto) {
		return stopsService.save(dto);
	}
	
	@GetMapping
	public List<Stop> findlAll() {
		return stopsRepository.findAll();
	}
}