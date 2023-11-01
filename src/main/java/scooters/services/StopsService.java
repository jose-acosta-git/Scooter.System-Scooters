package scooters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import scooters.dtos.LocationDto;
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
	
	public ResponseEntity<Stop> checkLocationWithinStop(LocationDto location) {
		List<Stop> stops = stopsRepository.findAll();
		for (Stop stop : stops) {
			if (locationIsWithinStop(location, stop)) {
				return ResponseEntity.ok(stop);
			}
		}
		return ResponseEntity.ok(null);
	}
	
	private boolean locationIsWithinStop(LocationDto location, Stop stop) {
		// The tolerance degrees used roughly equate to 111 meters
		double tolerance = 0.001;
		double latDiff = Math.abs(location.getLatitude() - stop.getLatitude());
		double lonDiff = Math.abs(location.getLongitude() - stop.getLongitude());
		
		return latDiff <= tolerance && lonDiff <= tolerance;
	}
	
	private Stop convertToEntity(StopDto dto) {
		return new Stop(dto.getLatitude(), dto.getLongitude());
	}
}