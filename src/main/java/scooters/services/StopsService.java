package scooters.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scooters.dtos.StopDto;
import scooters.model.Stop;
import scooters.repositories.StopsRepository;

@Service
public class StopsService {
	
	@Autowired
	private StopsRepository stopsRepository;
	
	public Stop save(StopDto dto) {
		return stopsRepository.save(convertToEntity(dto));
	}
	
	private Stop convertToEntity(StopDto dto) {
		return new Stop(dto.getLatitude(), dto.getLongitude());
	}
}