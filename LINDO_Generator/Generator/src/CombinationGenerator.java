
public class CombinationGenerator {
	public enum deploymentType{rocket, skyhook};
	public enum vehicleSize{small, large};
	public enum deorbitMethod{laser, tether, net};
	
	private double numSmallVehicles = 16;
	
	public double[] getObject(deploymentType a, vehicleSize b, deorbitMethod c){
		// maxDeploys, takeDownsPerDeploy, upfront, onetimeRisk, onetimeCost, costPerDeployment, operationalCost, repetitiveRisk, repetitiveCost
		double maxDeploys = getMaxDeploys(a);
		double takeDownsPerDeploy = getTakeDownRate(b, c);
		double upfrontCost = getUpFrontCost(a,b,c);
		double onetimeRisk = getInitialRisk(a, b, c);
		double onetimeFailureCost = getInitialFailureCost(a);
		double costPerDeployment = getCostPerDeployment(a, b, c);
		double operationalCost = getYearlyOperationalCost(a);
		double repetitiveRisk = getRepeatingRisk(a);
		double repetitiveFailureCost = getRepeatingFailureCost(a);
		return new double[]{maxDeploys, takeDownsPerDeploy, upfrontCost, onetimeRisk, onetimeFailureCost, costPerDeployment, operationalCost, repetitiveRisk, repetitiveFailureCost};
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
	
	private double getTakeDownRate(vehicleSize b, deorbitMethod c){
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
				return 5000000;
			}
			case skyhook:{
				return 300000000;
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
				return multiplier*10000;
			}
			case tether:{
				return multiplier*6000;
			}
			case net:{
				return multiplier*7000;
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
				totalRisk += .1;
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
				totalCost += 300000;
				break;
			}
			case skyhook:{
				totalCost += 50000;
				break;
			}
		}
		switch(b){
			case small:{
				totalCost += numSmallVehicles*30000;
				break;
			}
			case large:{
				totalCost += 500000;
				break;
			}
		}
		return totalCost;
	}

	private double getYearlyOperationalCost(deploymentType a) {
		switch(a){
			case rocket:{
				return 100000;
			}
			case skyhook:{
				return 50000;
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
				return 300000;
			}
			case skyhook:{
				return 50000;
			}
		}
		return 0;
	}
	
}