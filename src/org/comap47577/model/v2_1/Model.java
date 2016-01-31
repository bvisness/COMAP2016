package org.comap47577.model.v2_1;

import java.util.ArrayList;

public class Model {
	
	public final RemovalSystem[] systems;
	public final boolean debug;
	
	public final int numSystems;
	public final double minDebrisPerYear;
	public final double maxDebrisPerYear;
	public final double revenuePerDebris;
	public final int years;
	
	public Model(RemovalSystem[] systems, double minDebrisPerYear, double maxDebrisPerYear, double revenuePerDebris, int years) {
		this(systems, minDebrisPerYear, maxDebrisPerYear, revenuePerDebris, years, false);
	}
	
	public Model(RemovalSystem[] systems, double minDebrisPerYear, double maxDebrisPerYear, double revenuePerDebris, int years, boolean debug) {
		this.systems = systems;
		this.numSystems = systems.length;
		this.minDebrisPerYear = minDebrisPerYear;
		this.maxDebrisPerYear = maxDebrisPerYear;
		this.revenuePerDebris = revenuePerDebris;
		this.years = years;
		this.debug = debug;
	}
	
	public double[] objectiveFunctionCoefficientsD() {
		double[] coefficientsD = new double[systems.length];
		
		// The revenue part of the equation
		for (int i = 0; i < coefficientsD.length; i++) {
			coefficientsD[i] = revenuePerDebris * debrisFunctionCoefficientD(systems[i]);
		}
		
		// The cost part of the equation
		for (int i = 0; i < coefficientsD.length; i++) {
			coefficientsD[i] -= costFunctionCoefficientD(systems[i]); // We subtract because, you know, cost
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
	public double debrisFunctionCoefficientD(RemovalSystem system) {
		double r = system.riskOfFailurePerDeployment;
		double catAll = system.riskOfCatastrophicFailureOverall;
		
		double result = system.debrisPerDeployment * (1 - r) * catslope(system) * (1 - catAll);
		
		if (debug) {
			System.out.println("Debris function coefficient for D: " + result);
		}
		
		return result;
	}
	
	/**
	 * Gets the coefficient of the given system's D variable in the cost
	 * function. Units of the coefficient: years * cost.
	 */
	public double costFunctionCoefficientD(RemovalSystem system) {
		double cd = system.costPerDeployment;
		double fd = system.costOfFailurePerDeployment;
		double r = system.riskOfFailurePerDeployment;
		double fCat = system.costOfCatastrophicFailurePerDeployment;
		double cat = system.riskOfCatastrophicFailurePerDeployment;
		
		double result = years * (cd + fd * r + fCat * cat) * catslopeCost(system);
		
		if (debug) {
			System.out.println("Cost function coefficient for D: " + result);
		}
		
		return result;
	}
	
	/**
	 * Gets the coefficient of the given system's D variable in the cost
	 * function. Units of the coefficient: cost.
	 */
	public double costFunctionCoefficientU(RemovalSystem system) {
		double cu = system.costUpFront;
		double fAll = system.costOfCatastrophicFailureOverall;
		double catAll = system.riskOfCatastrophicFailureOverall;
		double cop = system.costOfOperation;
		
		double result = cu + fAll * catAll + cop * years;
		
		if (debug) {
			System.out.println("Cost function coefficient for U: " + result);
		}
		
		return result;
	}
	
	private double catslope(RemovalSystem system) {
		if (system.riskOfCatastrophicFailurePerDeployment == 0) {
			return 1;
		}
		
		double run = system.maxDeploymentsPerYear * years;
		double rise = realcat(system);
		
		double result = rise / run;
		
		if (debug) {
			System.out.println("catslope: " + result);
		}
		
		return result;
	}
	
	private double realcat(RemovalSystem system) {
		double cat = system.riskOfCatastrophicFailurePerDeployment;
		double dMax = system.maxDeploymentsPerYear;
		
		double result = ((1 - cat) - Math.pow(1 - cat, dMax * years + 1)) / cat;
		
		if (debug) {
			System.out.println("realcat: " + result);
		}
		
		return result;
	}
	
	private double catslopeCost(RemovalSystem system) {
		if (system.riskOfCatastrophicFailurePerDeployment == 0) {
			return 1;
		}
		
		double run = system.maxDeploymentsPerYear * years;
		double rise = realcatCost(system);
		
		double result = rise / run;
		
		if (debug) {
			System.out.println("catslope_cost: " + result);
		}
		
		return result;
	}
	
	private double realcatCost(RemovalSystem system) {
		double cat = system.riskOfCatastrophicFailurePerDeployment;
		double dMax = system.maxDeploymentsPerYear;
		
		double result = (1 - Math.pow(1 - cat, dMax * years)) / cat;
		
		if (debug) {
			System.out.println("realcat_cost: " + result);
		}
		
		return result;
	}
	
	public double totalRevenue(ArrayList<Double> dValues) {
		return totalRevenue(unboxArrayListDouble(dValues));
	}
	
	public double totalRevenue(double[] dValues) {
		double totalDebris = 0;
		for (int i = 0; i < dValues.length; i++) {
			double debrisCoefficientD = debrisFunctionCoefficientD(systems[i]);
			totalDebris += debrisCoefficientD * dValues[i];
		}
		
		return revenuePerDebris * totalDebris;
	}
	
	public double totalCost(ArrayList<Double> dValues, ArrayList<Double> uValues) {
		return totalCost(unboxArrayListDouble(dValues), unboxArrayListDouble(uValues));
	}
	
	public double totalCost(double[] dValues, double[] uValues) {
		double totalCost = 0;
		for (int i = 0; i < uValues.length; i++) {
			double costCoefficientD = costFunctionCoefficientD(systems[i]);
			double costCoefficientU = costFunctionCoefficientU(systems[i]);
			totalCost += costCoefficientU * uValues[i] + costCoefficientD * dValues[i];
		}
		
		return totalCost;
	}
	
	private double[] unboxArrayListDouble(ArrayList<Double> array) {
		double[] result = new double[array.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = array.get(i).doubleValue();
		}
		return result;
	}

}
