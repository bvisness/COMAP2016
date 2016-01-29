
/**
 * This class describes a debris removal system. It is intended to function as a
 * "struct", of sorts; merely a collection of relevant data. Hence, no annoying
 * getter and setter methods.
 */
public class RemovalSystem {

	public double costUpFront;
	public double costPerDeployment;
	public double costOfOperation;

	public double costOfFailureOneTime;
	public double costOfFailurePerDeployment;

	public double riskOfFailureOneTime;
	public double riskOfFailurePerDeployment;

	public int debrisPerDeployment;
	public double maxDeploymentsPerYear;

}
