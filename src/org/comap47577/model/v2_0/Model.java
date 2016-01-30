package org.comap47577.model.v2_0;

public class Model {
	
	private RemovalSystem[] systems;
	
	public final int numSystems;
	public final double minDebrisPerYear;
	public final double revenuePerDebris;
	public final int years;
	
	public Model(RemovalSystem[] systems, double minDebrisPerYear, double revenuePerDebris, int years) {
		this.systems = systems;
		this.numSystems = systems.length;
		this.minDebrisPerYear = minDebrisPerYear;
		this.revenuePerDebris = revenuePerDebris;
		this.years = years;
	}
	
	public double[] objectiveFunctionCoefficientsD() {
		double[] coefficientsD = new double[systems.length];
		
		// The revenue part of the equation
		for (int i = 0; i < coefficientsD.length; i++) {
			coefficientsD[i] = revenuePerDebris * debrisFunctionCoefficientD(systems[i]);
		}
		
		// The cost part of the equation
		for (int i = 0; i < coefficientsD.length; i++) {
			coefficientsD[i] += debrisFunctionCoefficientD(systems[i]);
		}
		
		return coefficientsD;
	}
	
	public double[] objectiveFunctionCoefficientsU() {
		double[] coefficientsU = new double[systems.length];
		
		// We only need the cost part of the objective function for this
		for (int i = 0; i < coefficientsU.length; i++) {
			coefficientsU[i] = costFunctionCoefficientU(systems[i]);
		}
		
		return coefficientsU;
	}
	
	public double[] debrisConstraintCoefficientsD() {
		double[] coefficientsD = new double[systems.length];
		
		for (int i = 0; i < coefficientsD.length; i++) {
			coefficientsD[i] = debrisFunctionCoefficientD(systems[i]);
		}
		
		return coefficientsD;
	}
	
	public double debrisConstraintRHS() {
		return minDebrisPerYear * years;
	}
	
	public double[] maxDeploymentsPerYear() {
		double[] maxDeployments = new double[systems.length];
		
		for (int i = 0; i < maxDeployments.length; i++) {
			maxDeployments[i] = systems[i].maxDeploymentsPerYear;
		}
		
		return maxDeployments;
	}
	
	/**
	 * Gets the coefficient of the given system's D variable in the debris
	 * function. Units of the coefficient: units of debris.
	 */
	private double debrisFunctionCoefficientD(RemovalSystem system) {
		double r = system.riskOfFailurePerDeployment;
		double catAll = system.riskOfCatastrophicFailureOverall;
		return system.debrisPerDeployment * (1 - r) * catslope(system) * (1 - catAll);
	}
	
	/**
	 * Gets the coefficient of the given system's D variable in the cost
	 * function. Units of the coefficient: years * cost.
	 */
	private double costFunctionCoefficientD(RemovalSystem system) {
		double cd = system.costPerDeployment;
		double fd = system.costOfFailurePerDeployment;
		double r = system.riskOfFailurePerDeployment;
		double fCat = system.costOfCatastrophicFailurePerDeployment;
		double cat = system.riskOfCatastrophicFailurePerDeployment;
		return years * (cd + fd * r + fCat * cat) * catslopeCost(system);
	}
	
	/**
	 * Gets the coefficient of the given system's D variable in the cost
	 * function. Units of the coefficient: cost.
	 */
	private double costFunctionCoefficientU(RemovalSystem system) {
		double cu = system.costUpFront;
		double fAll = system.costOfCatastrophicFailureOverall;
		double catAll = system.riskOfCatastrophicFailureOverall;
		double cop = system.costOfOperation;
		return cu + fAll * catAll + cop * years;
	}
	
	private double catslope(RemovalSystem system) {
		if (system.riskOfCatastrophicFailurePerDeployment == 0) {
			return 1;
		}
		
		double run = system.maxDeploymentsPerYear * years;
		double rise = realcat(system);
		return rise / run;
	}
	
	private double realcat(RemovalSystem system) {
		double cat = system.riskOfCatastrophicFailurePerDeployment;
		double dMax = system.maxDeploymentsPerYear;
		return ((1 - cat) - Math.pow(1 - cat, dMax * years + 1)) / cat;
	}
	
	private double catslopeCost(RemovalSystem system) {
		if (system.riskOfCatastrophicFailurePerDeployment == 0) {
			return 1;
		}
		
		double run = system.maxDeploymentsPerYear * years;
		double rise = realcatCost(system);
		return rise / run;
	}
	
	private double realcatCost(RemovalSystem system) {
		double cat = system.riskOfCatastrophicFailurePerDeployment;
		double dMax = system.maxDeploymentsPerYear;
		return (1 - Math.pow(1 - cat, dMax * years)) / cat;
	}

}
