package org.comap47577.model.v2_1;

public class ExpendableRocket extends DeliveryType{
	public ExpendableRocket(Vehicle typeOfPayload){
		super.maxDeploymentsPerYear = 8;
		super.buildCost = 0;
		super.deploymentCost = 50;
		super.yearlyCost = 2;
		super.initialFailureRisk = 0;
		super.initialFailureCost = 0;
		super.catastrophicRisk = 0;
		super.catastrophicCost = 0;
		super.repeatingRisk = 0.01;
		super.repeatingCost = 1;
		super.payload = typeOfPayload;
	}
}
