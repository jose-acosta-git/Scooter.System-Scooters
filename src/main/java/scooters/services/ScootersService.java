package scooters.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scooters.dtos.ScooterDto;
import scooters.model.Scooter;
import scooters.repositories.ScootersRepository;

@Service
public class ScootersService {
	
	@Autowired
	private ScootersRepository scootersRepository;
	
	public Scooter save(ScooterDto dto) {
		return scootersRepository.save(convertToEntity(dto));
	}
	
	private Scooter convertToEntity(ScooterDto dto) {
		return new Scooter(dto.getLatitude(), dto.getLongitude(), dto.getLastMaintenanceDate());
	}

}
