package org.comap47577.model.v2_0;

public class SmallSatellite extends Vehicle{
	public SmallSatellite(DeorbitMethod myChoiceOfDestruction){
		super.costPerVehicle = .5;
		super.numVehicles = 4;
		super.takeThemDown = myChoiceOfDestruction;
	}
}