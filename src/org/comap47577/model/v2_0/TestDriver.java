package org.comap47577.model.v2_0;

public class TestDriver {
	
	public static void main(String[] args) {
		CombinationGenerator generator = new CombinationGenerator();
		
		RemovalSystem a = generator.getObject(
			CombinationGenerator.deploymentType.rocket,
			CombinationGenerator.vehicleSize.large,
			CombinationGenerator.deorbitMethod.tether
		);
		RemovalSystem b = generator.getObject(
			CombinationGenerator.deploymentType.skyhook,
			CombinationGenerator.vehicleSize.small,
			CombinationGenerator.deorbitMethod.laser
		);
		
		RemovalSystem test = new RemovalSystem();
		test.costUpFront = 10;
		test.costPerDeployment = 1;
		test.costOfOperation = 2.5;
		test.costOfCatastrophicFailureOverall = 100;
		test.costOfFailurePerDeployment = 0.1;
		test.costOfCatastrophicFailurePerDeployment = 1;
		test.riskOfCatastrophicFailureOverall = 0.01;
		test.riskOfFailurePerDeployment = 0.1;
		test.riskOfCatastrophicFailurePerDeployment = 0.01;
		test.debrisPerDeployment = 1;
		test.maxDeploymentsPerYear = 4;
		
		RemovalSystem[] systems = {test, test};
		
		Model model = new Model(systems, 5, 100, 4);
		LindoGenerator lindo = new LindoGenerator();
		String lindoCode = lindo.generateLindoCode(model);
		
		System.out.println(lindoCode);
	}

}
