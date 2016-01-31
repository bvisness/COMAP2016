package org.comap47577.model.v2_0;

public abstract class Vehicle {
	public double numVehicles;
	public double costPerVehicle;
	
	public DeorbitMethod deorbitMethod;
	
	public int getTakeDownRate(){
		return (int) (numVehicles*deorbitMethod.takeDownRate);
	}
	
	public double getCostPerVehicle(){
		return numVehicles*(costPerVehicle + deorbitMethod.buildCost);
	}
}
