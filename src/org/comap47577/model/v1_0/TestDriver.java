package org.comap47577.model.v1_0;

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
		
		LindoGenerator lindo = new LindoGenerator();
		String lindoCode = lindo.generateLindoCode(systems, 1, 5);
		
		System.out.println(lindoCode);
	}

}
