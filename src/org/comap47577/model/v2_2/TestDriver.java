package org.comap47577.model.v2_2;

public class TestDriver {
	
	public static void main(String[] args) {
		
		RemovalSystem[] systems = {
				new ExpendableRocket(new LargeSatellite(new Net())).getObject(),
				new ExpendableRocket(new LargeSatellite(new Tether())).getObject(),
				new ExpendableRocket(new MediumSatellite(new Net())).getObject(),
				new ExpendableRocket(new MediumSatellite(new Tether())).getObject(),
				new ExpendableRocket(new SmallSatellite(new Net())).getObject(),
				new CleanSpaceOne(new LargeSatellite(new Net())).getObject(),
				new CleanSpaceOne(new LargeSatellite(new Tether())).getObject(),
				new CleanSpaceOne(new MediumSatellite(new Net())).getObject(),
				new CleanSpaceOne(new MediumSatellite(new Tether())).getObject(),
				new CleanSpaceOne(new SmallSatellite(new Net())).getObject(),
				new Skyhook(new LargeSatellite(new Net())).getObject(),
				new Skyhook(new LargeSatellite(new Tether())).getObject(),
				new Skyhook(new MediumSatellite(new Net())).getObject(),
				new Skyhook(new MediumSatellite(new Tether())).getObject(),
				new Skyhook(new SmallSatellite(new Net())).getObject(),
		};
		
		RemovalSystem b = new CleanSpaceOne(new MediumSatellite(new Net())).getObject();
		RemovalSystem c = new Skyhook(new SmallSatellite(new Net())).getObject();
		RemovalSystem d = new CleanSpaceOne(new LargeSatellite(new Tether())).getObject();
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
		test.maxDeploymentsPerYear = 8;
		
//		RemovalSystem[] systems = {a, b, c, d, e};
//		RemovalSystem[] systems = {test};
		
		for (int years = 1; years <= 20; years++) {
			System.out.println("\n=====================================\n");
			System.out.println(years + " YEARS:\n");
			
			Model model = new Model(systems, 5, 18, 20, years, false);
			LindoGenerator lindo = new LindoGenerator();
			String lindoCode = lindo.generateLindoCode(model);
			System.out.println(lindoCode);
			
			LingoParser parser = new LingoParser();
			parser.parseLingo(model, true);
		}
	}

}
