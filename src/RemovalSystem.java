
/**
 * This class describes a debris removal system. It is intended to function as a
 * "struct", of sorts; merely a collection of relevant data. Hence, no annoying
 * getter and setter methods.
 */
public class RemovalSystem {

	public int costUpFront;
	public int costPerDeployment;
	public int costOfOperation;

	public int costOfFailureOneTime;
	public int costOfFailurePerDeployment;

	public double riskOfFailureOneTime;
	public double riskOfFailurePerDeployment;

	public int debrisPerDeployment;
	public double maxDeploymentsPerYear;

}
