
public class TestDriver {
	
	public static void main(String[] args) {
		RemovalSystem a = new RemovalSystem();
		a.costUpFront = 1000;
		a.costPerDeployment = 100;
		a.costOfOperation = 50;
		a.costOfFailureOneTime = 5000;
		a.costOfFailurePerDeployment = 500;
		a.riskOfFailureOneTime = 0.1;
		a.riskOfFailurePerDeployment = 0.01;
		a.debrisPerDeployment = 2;
		a.maxDeploymentsPerYear = 4;
		
		RemovalSystem b = new RemovalSystem();
		b.costUpFront = 800;
		b.costPerDeployment = 150;
		b.costOfOperation = 70;
		b.costOfFailureOneTime = 3000;
		b.costOfFailurePerDeployment = 750;
		// risks
		b.debrisPerDeployment = 1;
		b.maxDeploymentsPerYear = 6;
		
		RemovalSystem[] systems = {a, b};
		
		LindoGenerator lindo = new LindoGenerator();
		String lindoCode = lindo.generateLindoCode(systems, 1, 5);
		
		System.out.println(lindoCode);
	}

}
