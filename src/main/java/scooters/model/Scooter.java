package scooters.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Scooter {
	
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String status;
	
	@Column
	private double latitude;
	
	@Column
	private double longitude;
	
	@Column
	private LocalDate lastMaintenanceDate;

	public Scooter(double latitude, double longitude, LocalDate lastMaintenanceDate) {
		this.status = "available";
		this.latitude = latitude;
		this.longitude = longitude;
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	
	public Scooter() {}

	public int getId() {return id;}
	public String getStatus() {return status;}
	public double getLatitude() {return latitude;}
	public double getLongitude() {return longitude;}
	public LocalDate getLastMaintenanceDate() {return lastMaintenanceDate;}
}
