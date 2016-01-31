package org.comap47577.model.v2_0;

public class ExpendableRocket extends DeliveryType{
	public ExpendableRocket(Vehicle typeOfPayload){
		super.maxDeploymentsPerYear = 4;
		super.buildCost = 0;
		super.deploymentCost = 50;
		super.yearlyCost = 2;
		super.initialFailureRisk = 0.05;
		super.initialFailureCost = 5;
		super.catastrophicRisk = 0.01;
		super.catastrophicCost = 2;
		super.repeatingRisk = 0.01;
		super.repeatingCost = 1;
		super.payload = typeOfPayload;
	}
}
