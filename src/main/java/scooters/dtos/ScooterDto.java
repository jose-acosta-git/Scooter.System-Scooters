package scooters.dtos;

import java.time.LocalDate;

public class ScooterDto {

	private String status;
	private double latitude;
	private double longitude;
	private LocalDate lastMaintenanceDate;
	
	public ScooterDto(double latitude, double longitude, LocalDate lastMaintenanceDate) {
		this.status = "available";
		this.latitude = latitude;
		this.longitude = longitude;
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	
	public ScooterDto() {}

	public String getStatus() {return status;}
	public double getLatitude() {return latitude;}
	public double getLongitude() {return longitude;}
	public LocalDate getLastMaintenanceDate() {return lastMaintenanceDate;}
}