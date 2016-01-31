package org.comap47577.model.v2_2;

public class LargeSatellite extends Vehicle{
	public LargeSatellite(DeorbitMethod myChoiceOfDestruction){
		super.costPerVehicle = 10;
		super.numVehicles = 1;
		super.deorbitMethod = myChoiceOfDestruction;
	}
}
