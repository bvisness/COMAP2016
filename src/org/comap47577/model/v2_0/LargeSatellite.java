package org.comap47577.model.v2_0;

public class LargeSatellite extends Vehicle{
	public LargeSatellite(DeorbitMethod myChoiceOfDestruction){
		super.costPerVehicle = 1;
		super.numVehicles = 1;
		super.takeThemDown = myChoiceOfDestruction;
	}
}
