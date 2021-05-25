package SkillDistributions;

public class NormalPlayerSkillDistribution extends PlayerSkillDistribution
{
	public NormalPlayerSkillDistribution(double minSkillValue, double maxSkillValue)
	{
		super(minSkillValue, maxSkillValue);
	}
	
	@Override
	double getSkillValue()
	{
		double mean = (maxSkillValue + minSkillValue)/2.0;
		// 5.5 standard deviations = 1 in 26,330,254 (0.0000037979125%) of population will hit min or max value)
		double stdDev = (maxSkillValue - minSkillValue)/11.0;
		
		double skillValue = (rand.nextGaussian() * stdDev) + mean;

		return skillValue;
	}
	
}
