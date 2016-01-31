package org.comap47577.model.v2_0;

public abstract class Vehicle {
	public double numVehicles;
	public double costPerVehicle;
	
	public DeorbitMethod takeThemDown;
	
	public int getTakeDownRate(){
		return (int) (numVehicles*takeThemDown.takeDownRate);
	}
	
	public double getCostPerVehicle(){
		return numVehicles*(costPerVehicle + takeThemDown.buildCost);
	}
}
