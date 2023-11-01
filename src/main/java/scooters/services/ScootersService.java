package scooters.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
	public ResponseEntity<Scooter> startMaintenance(int scooterId) {
        Optional<Scooter> scooterOptional = scootersRepository.findById(scooterId);
        if (scooterOptional.isPresent()) {
            Scooter scooter = scooterOptional.get();
            if (scooter.getStatus().equals("maintenance")) {
        		return ResponseEntity.badRequest().build();
            }
            scooter.setStatus("maintenance");
            return ResponseEntity.ok(scootersRepository.save(scooter));
        }
        return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Scooter> finishMaintenance(int scooterId) {
        Optional<Scooter> scooterOptional = scootersRepository.findById(scooterId);
        if (scooterOptional.isPresent()) {
            Scooter scooter = scooterOptional.get();
            if (!scooter.getStatus().equals("maintenance")) {
        		return ResponseEntity.badRequest().build();
            }
            scooter.setStatus("available");
            scooter.setLastMaintenanceDate(LocalDate.now());
            return ResponseEntity.ok(scootersRepository.save(scooter));
        }
        return ResponseEntity.notFound().build();
	}
    
	public ResponseEntity<String> removeScooter(int scooterId) {
        Optional<Scooter> scooterOptional = scootersRepository.findById(scooterId);
        if (scooterOptional.isPresent()) {
            Scooter scooter = scooterOptional.get();
            if (scooter.getStatus().equals("in-use")) {
            	return ResponseEntity.badRequest().build();
            }
            scootersRepository.deleteById(scooterId);
            return ResponseEntity.ok("Scooter removed successfully");
        }
        return ResponseEntity.notFound().build();
	}
    
    private boolean isValidStatus(String status) {
    	return (status.equals("available") || status.equals("in-use") || status.equals("maintenance"));
    }
	
	private Scooter convertToEntity(ScooterDto dto) {
		return new Scooter(dto.getLatitude(), dto.getLongitude(), dto.getLastMaintenanceDate());
	}







}
