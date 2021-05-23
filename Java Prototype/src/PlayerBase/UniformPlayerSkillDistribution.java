package PlayerBase;

public class UniformPlayerSkillDistribution extends PlayerSkillDistribution
{
	public UniformPlayerSkillDistribution(double minSkillValue, double maxSkillValue)
	{
		super(minSkillValue, maxSkillValue);
	}

	@Override
	public double getSkillValue()
	{
		return (rand.nextDouble()*(maxSkillValue - minSkillValue) + minSkillValue);
	}
}
