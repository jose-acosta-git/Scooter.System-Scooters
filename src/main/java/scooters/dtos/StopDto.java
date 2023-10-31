package scooters.dtos;

public class StopDto {
	
	private double latitude;
	private double longitude;
	
	public StopDto(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public StopDto() {}

	public double getLatitude() {return latitude;}
	public double getLongitude() {return longitude;}
}
