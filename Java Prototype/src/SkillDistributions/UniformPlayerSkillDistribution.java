package SkillDistributions;

public class UniformPlayerSkillDistribution extends PlayerSkillDistribution
{
	public UniformPlayerSkillDistribution(double minSkillValue, double maxSkillValue)
	{
		super(minSkillValue, maxSkillValue);
	}

	@Override
	double getSkillValue()
	{
		return (rand.nextDouble()*(maxSkillValue - minSkillValue) + minSkillValue);
	}
}
