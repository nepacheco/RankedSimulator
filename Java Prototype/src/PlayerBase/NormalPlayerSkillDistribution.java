package PlayerBase;

public class NormalPlayerSkillDistribution extends PlayerSkillDistribution
{
	public NormalPlayerSkillDistribution(double minSkillValue, double maxSkillValue)
	{
		super(minSkillValue, maxSkillValue);
	}
	
	@Override
	public double getSkillValue()
	{
		double mean = (maxSkillValue + minSkillValue)/2.0;
		double stdDev = (maxSkillValue - minSkillValue)/8.0;
		
		double skillValue = (rand.nextGaussian() * stdDev) + mean;
		
		if(skillValue < minSkillValue)
			skillValue = minSkillValue;
		else if(skillValue > maxSkillValue)
			skillValue = maxSkillValue;
		
		return skillValue;
	}
	
}
