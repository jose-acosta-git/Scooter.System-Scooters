package scooters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import scooters.dtos.StopDto;
import scooters.model.Stop;
import scooters.services.StopsService;

@RestController
@RequestMapping("/stops")
public class StopsController {
	
	@Autowired
	private StopsService stopsService;
	
	@PostMapping
	public ResponseEntity<Stop> create(HttpServletRequest request, @RequestBody StopDto dto) {
		return stopsService.save(request, dto);
	}
	
	@GetMapping
	public ResponseEntity<List<Stop>> findlAll(HttpServletRequest request) {
		return stopsService.findAll(request);
	}
}