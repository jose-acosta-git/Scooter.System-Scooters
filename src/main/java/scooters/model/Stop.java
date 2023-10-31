package scooters.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stop {
	
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private double latitude;
	
	@Column
	private double longitude;

	public Stop(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Stop() {}

	public int getId() {return id;}
	public double getLatitude() {return latitude;}
	public double getLongitude() {return longitude;}
}
