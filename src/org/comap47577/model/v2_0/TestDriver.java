package org.comap47577.model.v2_0;

public class TestDriver {
	
	public static void main(String[] args) {
		
		RemovalSystem a = new ExpendableRocket(new LargeSatellite(new Tether())).getObject();
		RemovalSystem b = new CleanSpaceOne(new MediumSatellite(new Net())).getObject();
		RemovalSystem c = new Skyhook(new SmallSatellite(new Net())).getObject();
		RemovalSystem d = new CleanSpaceOne(new MediumSatellite(new Tether())).getObject();
		RemovalSystem e = new Skyhook(new SmallSatellite(new Tether())).getObject();
		
//		a.debrisPerDeployment = 4;
//		a.maxDeploymentsPerYear = 5;
//		b.debrisPerDeployment = 1;
		
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
		
		RemovalSystem[] systems = {a, b, c, d, e};
		
		for (int years = 1; years <= 10; years++) {
			System.out.println("\n=====================================\n");
			
			Model model = new Model(systems, 5, 50, years);
			LindoGenerator lindo = new LindoGenerator();
			String lindoCode = lindo.generateLindoCode(model);
			System.out.println(lindoCode);
			
			LingoParser parser = new LingoParser();
			parser.parseLingo(model, false);
		}
	}

}
