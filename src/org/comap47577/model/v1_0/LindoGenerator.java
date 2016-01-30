package org.comap47577.model.v1_0;

public class LindoGenerator {
	
	private static final String DEPLOYMENTS_VAR = "D";
	private static final String USING_VAR = "U";
	
	public String generateLindoCode(RemovalSystem[] systems, int years, double minDebris) {
		String result = "";
		
		// Objective function
		result += "MIN " + objectiveFunction(systems, years) + "\n";
		result += "ST\n";
		
		// Minimum debris constraint
		result += minDebrisConstraint(systems, minDebris) + "\n";
		
		// Max deployments per year constraints
		for (int i = 0; i < systems.length; i++) {
			result += maxDeploymentsConstraint(systems[i], i + 1) + "\n";
		}
		
		// Binary restriction on using variables
		for (int i = 0; i < systems.length; i++) {
			result += USING_VAR + (i + 1) + " <= 1\n";
		}
		
		result += "END\n";
		
		// Make using variables be ints
		for (int i = 0; i < systems.length; i++) {
			result += "INT " + USING_VAR + (i + 1) + "\n";
		}
		
		return result;
	}
	
	private String objectiveFunctionUnit(RemovalSystem system, int index, int years) {
		double usingVarCoefficient = system.costUpFront
				+ (system.costOfFailureOneTime * system.riskOfFailureOneTime)
				+ (system.costOfOperation * years);
		double deploymentsVarCoefficient = (system.costPerDeployment * years)
				+ (system.costOfFailurePerDeployment * system.riskOfFailurePerDeployment * years);
		
		return (usingVarCoefficient + USING_VAR + index) + " + " + (deploymentsVarCoefficient + DEPLOYMENTS_VAR + index);
	}
	
	private String objectiveFunction(RemovalSystem[] systems, int years) {
		String result = "";
		for (int i = 0; i < systems.length; i++) {
			if (i > 0) {
				result += " + ";
			}
			result += objectiveFunctionUnit(systems[i], i + 1, years);
		}
		return result;
	}
	
	private String minDebrisConstraint(RemovalSystem[] systems, double minDebris) {
		String result = "";
		for (int i = 0; i < systems.length; i++) {
			if (i > 0) {
				result += " + ";
			}
			result += systems[i].debrisPerDeployment + DEPLOYMENTS_VAR + (i + 1);
		}
		result += " >= " + minDebris;
		return result;
	}
	
	private String maxDeploymentsConstraint(RemovalSystem system, int index) {
		// e.g "D1 - 4U1 <= 0"
		return DEPLOYMENTS_VAR + index + " - " + system.maxDeploymentsPerYear + USING_VAR + index + " <= 0";
	}

}
