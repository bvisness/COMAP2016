package org.comap47577.model.v2_1;

public class SmallSatellite extends Vehicle{
	public SmallSatellite(DeorbitMethod myChoiceOfDestruction){
		super.costPerVehicle = 2.5;
		super.numVehicles = 4;
		super.deorbitMethod = myChoiceOfDestruction;
	}
}
