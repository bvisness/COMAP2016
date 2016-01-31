package org.comap47577.model.v2_0;

public class CombinationGenerator {
	public enum deploymentType{rocket, skyhook};
	public enum vehicleSize{small, large};
	public enum deorbitMethod{laser, tether, net, gas};
	
	private int numSmallVehicles = 16;
	//All costs are in terms of 1 = $1 million
	
	public RemovalSystem getObject(deploymentType a, vehicleSize b, deorbitMethod c){
		RemovalSystem system = new RemovalSystem();
		system.maxDeploymentsPerYear = getMaxDeploys(a);
		system.debrisPerDeployment = getTakeDownRate(b, c);
		system.costUpFront = getUpFrontCost(a,b,c);
		system.riskOfCatastrophicFailureOverall = getInitialRisk(a, b, c);
		system.costOfCatastrophicFailureOverall = getInitialFailureCost(a);
		system.costPerDeployment = getCostPerDeployment(a, b, c);
		system.costOfOperation = getYearlyOperationalCost(a);
		system.riskOfFailurePerDeployment = getRepeatingRisk(a);
		system.costOfFailurePerDeployment = getRepeatingFailureCost(a);
		system.riskOfCatastrophicFailurePerDeployment = getRepeatingCatastrophicRisk(a);
		system.costOfCatastrophicFailurePerDeployment = getRepeatingCatastrophicFailureCost(a);
		return system;
	}

	private double getMaxDeploys(deploymentType a){
		//TODO: update these numbers
		switch(a){
			case rocket:{
				return 4;
			}
			case skyhook:{
				return 16;
			}
		}
		return 0;
	}
	
	private int getTakeDownRate(vehicleSize b, deorbitMethod c){
		//TODO: update these numbers
		int multiplier = 0;
		switch(b){
			case small:{
				multiplier = numSmallVehicles;
				break;
			}
			case large:{
				multiplier = 1;
				break;
			}
		}
		switch(c){
			case laser:{
				return multiplier*4;
			}
			case tether:{
				return multiplier*16;
			}
			case net:{
				return multiplier*1;
			}
		}
		return 0;
	}

	private double getUpFrontCost(deploymentType a, vehicleSize b, deorbitMethod c){
		return deployerBuildCost(a) + vehicleBuildCost(b,c);
	}
	
	private double deployerBuildCost(deploymentType a){
		//TODO: update these numbers
		switch(a){
			case rocket:{
				return 50;
			}
			case skyhook:{
				return 18000;
			}
		}
		return 0;
	}
	
	private double vehicleBuildCost(vehicleSize b, deorbitMethod c){
		//TODO: update these numbers
		double multiplier = 0;
		switch(b){
			case small:{
				multiplier = numSmallVehicles;
				break;
			}
			case large:{
				multiplier = 1;
				break;
			}
		}
		switch(c){
			case laser:{
				return multiplier*10;
			}
			case tether:{
				return multiplier*6;
			}
			case net:{
				return multiplier*7;
			}
		}
		return 0;
	}
	
	private double getInitialRisk(deploymentType a, vehicleSize b, deorbitMethod c) {
		// TODO Auto-generated method stub
		double totalRisk = 0;
		switch(b){
			case small:{
				totalRisk += 1.0/16.0;
				break;
			}
			case large:{
				totalRisk += .03;
				break;
			}
		}
		switch(c){
			case laser:{
				totalRisk += .15;
				break;
			}
			case tether:{
				totalRisk += .05;
				break;
			}
			case net:{
				totalRisk += .1;
				break;
			}
		}
		return totalRisk;
		}
	
	private double getInitialFailureCost(deploymentType a) {
		//TODO: update these numbers
		return deployerBuildCost(a);
	}

	private double getCostPerDeployment(deploymentType a, vehicleSize b, deorbitMethod c) {
		double totalCost = 0;
		switch(a){
			case rocket:{
				totalCost += 50;
				break;
			}
			case skyhook:{
				totalCost += .005;
				break;
			}
		}
		switch(b){
			case small:{
				totalCost += numSmallVehicles*0.03;
				break;
			}
			case large:{
				totalCost += 0.5;
				break;
			}
		}
		return totalCost;
	}

	private double getYearlyOperationalCost(deploymentType a) {
		switch(a){
			case rocket:{
				return 0.1;
			}
			case skyhook:{
				return 0.05;
			}
		}
		return 0;
	}

	private double getRepeatingRisk(deploymentType a) {
		switch(a){
			case rocket:{
				return .06;
			}
			case skyhook:{
				return .04;
			}
		}
		return 0;
	}
	
	private double getRepeatingFailureCost(deploymentType a) {
		switch(a){
			case rocket:{
				return 0.3;
			}
			case skyhook:{
				return 0.05;
			}
		}
		return 0;
	}
	
	private double getRepeatingCatastrophicRisk(deploymentType a) {
		switch(a) {
			case rocket: {
				return 0;
			}
			case skyhook: {
				return 0.005;
			}
		}
		return 0;
	}
	
	private double getRepeatingCatastrophicFailureCost(deploymentType a) {
		switch(a){
			case rocket:{
				return 0;
			}
			case skyhook:{
				return 1;
			}
		}
		return 0;
	}
	
}