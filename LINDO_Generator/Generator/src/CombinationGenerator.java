
public class CombinationGenerator {
	public enum deploymentType{rocket, skyhook};
	public enum vehicleSize{small, large};
	public enum deorbitMethod{laser, tether, net};
	
	public double[] getParameters(deploymentType a, vehicleSize b, deorbitMethod c){
		// maxDeploys, takeDownsPerDeploy, upfront, onetimeRisk, onetimeCost, costPerDeployment, operationalCost, repetitiveRisk, repetitiveCost
		double maxDeploys = getMaxDeploys(a);
		double takeDownsPerDeploy = getTakeDownRate(b, c);
		double upfrontCost = getUpFrontCost(a,b,c);
		return new double[]{maxDeploys, takeDownsPerDeploy, upfrontCost};
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
				multiplier = 16;
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
				return 4;
			}
			case skyhook:{
				return 16;
			}
		}
		return 0;
	}
	
	private double vehicleBuildCost(vehicleSize b, deorbitMethod c){
		//TODO: update these numbers
		double multiplier = 0;
		switch(b){
			case small:{
				multiplier = 16;
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

}
