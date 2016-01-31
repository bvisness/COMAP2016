package org.comap47577.model.v2_1;

public class Skyhook extends DeliveryType{
	public Skyhook(Vehicle typeOfPayload){
		super.maxDeploymentsPerYear = 16;
		super.buildCost = 18000;
		super.deploymentCost = 1;
		super.yearlyCost = 2;
		super.initialFailureRisk = 0.05;
		super.initialFailureCost = 10;
		super.catastrophicRisk = 0.001;
		super.catastrophicCost = 10;
		super.repeatingRisk = 0.01;
		super.repeatingCost = 0.5;
		super.payload = typeOfPayload;
	}
}
