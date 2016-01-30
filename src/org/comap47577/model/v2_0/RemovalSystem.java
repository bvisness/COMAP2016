package org.comap47577.model.v2_0;

/**
 * This class describes a debris removal system. It is intended to function as a
 * "struct", of sorts; merely a collection of relevant data. Hence, no annoying
 * getter and setter methods.
 */
public class RemovalSystem {

	public double costUpFront;
	public double costPerDeployment;
	public double costOfOperation;

	public double costOfCatastrophicFailureOverall;
	public double costOfFailurePerDeployment;
	public double costOfCatastrophicFailurePerDeployment;

	public double riskOfCatastrophicFailureOverall;
	public double riskOfFailurePerDeployment;
	public double riskOfCatastrophicFailurePerDeployment;

	public int debrisPerDeployment;
	public double maxDeploymentsPerYear;

}
