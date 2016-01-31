package org.comap47577.model.v2_2;

public abstract class Vehicle {
	public double numVehicles;
	public double costPerVehicle;
	
	public DeorbitMethod deorbitMethod;
	
	public double getTakeDownRate(){
		return numVehicles * deorbitMethod.takeDownRate;
	}
	
	public double getCostPerVehicle(){
		return numVehicles * (costPerVehicle + deorbitMethod.buildCost);
	}
}
