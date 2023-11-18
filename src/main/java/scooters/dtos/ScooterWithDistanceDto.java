package scooters.dtos;

import java.time.LocalDate;

public class ScooterWithDistanceDto {
    private int id;
	private String status;
	private double latitude;
	private double longitude;
	private LocalDate lastMaintenanceDate;
    private double totalDistance;
    
    public ScooterWithDistanceDto() {
    }

    public ScooterWithDistanceDto(int id, String status, double latitude, double longitude,
            LocalDate lastMaintenanceDate, double totalDistance) {
        this.id = id;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.totalDistance = totalDistance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    
    
}
