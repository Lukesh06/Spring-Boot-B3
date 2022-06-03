package com.ekalavya;



public class TravelAgent {

	Vehicle vehicle ;
	String vehicleNumber;
	
	public void travel() {
		vehicle.move();
		System.out.println("Vehicle Number:::"+vehicleNumber);
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public TravelAgent(Vehicle vehicle, String vehicleNumber) {
		this.vehicle = vehicle;
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public TravelAgent() {
		super();
	}

	
	
	
	
}
