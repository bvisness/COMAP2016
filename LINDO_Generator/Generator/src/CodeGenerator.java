
public class CodeGenerator {

// boolean, maxDeploys, takeDownsPerDeploy, upfront, onetimeRisk, onetimeCost, costPerDeployment, operationalCost, repetitiveRisk, repetitiveCost
	/*
	
MIN z = (upfrontTotal + onetimeCostTotal) + (deploymentsCostTotal + operationalCostsTotal + repetitiveCostsTotal) * years
s.t.
years = 1
satelliteTakedowns = 5

totalTakedowns = SUM(numDeploys[i] * takedownsPerDeploy[i] * boolean[i])
totalTakedowns >= satelliteTakedowns

upfrontTotal = SUM(upfront[i] * boolean[i])
onetimeCostTotal = SUM(onetimeRisk[i] * onetimeCost[i] * boolean[i])
deploymentsCostTotal = SUM(numDeploys[i] * costPerDeployment[i] * boolean[i])
operationalCostsTotal = SUM(operationalCost[i] * boolean[i])
repetitiveCostsTotal = SUM(repetitiveRisk[i] * repetitiveCost[i] * boolean[i])

boolean[i] >=0
boolean[i] <=1
upfront[i] = param
onetimeRisk[i] = param
onetimeCost[i] = param
numDeploys[i] > 0
numDeploys[i] <= max[i]
max[i] = param
costPerDeployment[i] = param
operationalCost[i] = param
repetitiveRisk[i] = param
repetitiveCost[i] = param
takedownsPerDeploy[i] = param
	
	*/
}
