package org.comap47577.model.v2_1;

public class CleanSpaceOne extends DeliveryType{
	public CleanSpaceOne(Vehicle typeOfPayload){
		super.maxDeploymentsPerYear = 8;
		super.buildCost = 250;
		super.deploymentCost = 8;
		super.yearlyCost = 2;
		super.initialFailureRisk = 0;
		super.initialFailureCost = 0;
		super.catastrophicRisk = 0;
		super.catastrophicCost = 0;
		super.repeatingRisk = 0.01;
		super.repeatingCost = 5;
		super.payload = typeOfPayload;
	}
}
