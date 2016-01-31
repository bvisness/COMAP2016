package org.comap47577.model.v2_0;

public abstract class DeliveryType {
	protected double maxDeploymentsPerYear;
	protected double buildCost;
	protected double deploymentCost;
	protected double yearlyCost;
	protected double initialFailureRisk;
	protected double initialFailureCost;
	protected double catastrophicRisk;
	protected double catastrophicCost;
	protected double repeatingRisk;
	protected double repeatingCost;
	
	protected Vehicle payload;
	
	// DeliveryType expRocket = new ExpendableRocket(new LargeSatellite(new Tether()));
	// expRocket.getObject();
	
	public RemovalSystem getObject(){
		RemovalSystem system = new RemovalSystem();
		system.maxDeploymentsPerYear = getMaxDeploys();
		system.debrisPerDeployment = getTakeDownRate();
		system.costUpFront = getUpFrontCost();
		system.riskOfCatastrophicFailureOverall = getInitialRisk();
		system.costOfCatastrophicFailureOverall = getInitialFailureCost();
		system.costPerDeployment = getCostPerDeployment();
		system.costOfOperation = getYearlyOperationalCost();
		system.riskOfFailurePerDeployment = getRepeatingRisk();
		system.costOfFailurePerDeployment = getRepeatingFailureCost();
		system.riskOfCatastrophicFailurePerDeployment = getRepeatingCatastrophicRisk();
		system.costOfCatastrophicFailurePerDeployment = getRepeatingCatastrophicFailureCost();
		return system;
	}
	
	private double getMaxDeploys(){
		return maxDeploymentsPerYear;
	}
	
	private double getUpFrontCost(){
		return buildCost;
	}
	
	private int getTakeDownRate(){
		return payload.getTakeDownRate();
	}

	private double getInitialRisk(){
		return initialFailureRisk;
	}
	
	private double getInitialFailureCost(){
		return initialFailureCost;
	}
	
	private double getCostPerDeployment(){
		return deploymentCost + payload.costPerVehicle*payload.numVehicles;
	}
	
	private double getYearlyOperationalCost(){
		return yearlyCost;
	}
	
	private double getRepeatingRisk(){
		return repeatingRisk;
	}
	
	private double getRepeatingFailureCost(){
		return repeatingCost;
	}
	
	private double getRepeatingCatastrophicRisk(){
		return catastrophicRisk;
	}
	
	private double getRepeatingCatastrophicFailureCost(){
		return catastrophicCost;
	}
}
