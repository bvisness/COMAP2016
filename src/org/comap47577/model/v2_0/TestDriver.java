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
		
		RemovalSystem[] systems = {a, b};
		
		Model model = new Model(systems, 5, 100000000, 10);
		LindoGenerator lindo = new LindoGenerator();
		String lindoCode = lindo.generateLindoCode(model);
		
		System.out.println(lindoCode);
	}

}
