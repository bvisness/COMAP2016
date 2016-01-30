package org.comap47577.model.v2_0;

public class LindoGenerator {
	
	private static final String DEPLOYMENTS_VAR = "D";
	private static final String USING_VAR = "U";
	
	public String generateLindoCode(Model model) {
		String result = "";
		
		// Objective function
		result += "MAX " + objectiveFunction(model) + "\n";
		result += "ST\n";
		
		// Minimum debris constraint
		result += minDebrisConstraint(model) + "\n";
		
		// Max deployments per year constraints
		double[] maxDeployments = model.maxDeploymentsPerYear();
		for (int i = 0; i < maxDeployments.length; i++) {
			result += maxDeploymentsConstraint(i + 1, maxDeployments[i]) + "\n";
		}
		
		// Binary restriction on using variables
		for (int i = 0; i < model.numSystems; i++) {
			result += USING_VAR + (i + 1) + " <= 1\n";
		}
		
		result += "END\n";
		
		// Make using variables be ints
		for (int i = 0; i < model.numSystems; i++) {
			result += "INT " + USING_VAR + (i + 1) + "\n";
		}
		
		return result;
	}
	
	private String objectiveFunction(Model model) {
		String result = "";
		
		// Deployments variables
		double[] coefficientsD = model.objectiveFunctionCoefficientsD();
		for (int i = 0; i < model.numSystems; i++) {
			if (i > 0) {
				result += " + ";
			}
			result += (coefficientsD[i] + DEPLOYMENTS_VAR + (i + 1));
		}
		
		result += " + ";
		
		// Using variables
		double[] coefficientsU = model.objectiveFunctionCoefficientsU();
		for (int i = 0; i < model.numSystems; i++) {
			if (i > 0) {
				result += " + ";
			}
			result += (coefficientsU[i] + USING_VAR + (i + 1));
		}
		
		return result;
	}
	
	private String minDebrisConstraint(Model model) {
		String result = "";
		double[] debrisCoefficientsD = model.debrisConstraintCoefficientsD();
		for (int i = 0; i < model.numSystems; i++) {
			if (i > 0) {
				result += " + ";
			}
			result += (debrisCoefficientsD[i] + DEPLOYMENTS_VAR + (i + 1));
		}
		result += " >= " + model.minDebrisPerYear;
		return result;
	}
	
	private String maxDeploymentsConstraint(int index, double maxDeploymentsPerYear) {
		// e.g "D1 - 4U1 <= 0"
		return DEPLOYMENTS_VAR + index + " - " + maxDeploymentsPerYear + USING_VAR + index + " <= 0";
	}

}
