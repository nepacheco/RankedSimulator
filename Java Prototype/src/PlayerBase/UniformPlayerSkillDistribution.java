package PlayerBase;

public class UniformPlayerSkillDistribution extends PlayerSkillDistribution
{
	public UniformPlayerSkillDistribution(double minSkillValue, double maxSkillValue)
	{
		this.minSkillValue = minSkillValue;
		this.maxSkillValue = maxSkillValue;
	}
	
	public double getSkillValue()
	{
		return (rand.nextDouble()*(maxSkillValue - minSkillValue) + minSkillValue);
	}
}
