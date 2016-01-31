package org.comap47577.model.v2_0;

public class MediumSatellite extends Vehicle{
	public MediumSatellite(DeorbitMethod myChoiceOfDestruction){
		super.costPerVehicle = .8;
		super.numVehicles = 2;
		super.takeThemDown = myChoiceOfDestruction;
	}
}
