package SkillDistributions;

import java.util.Random;

public abstract class PlayerSkillDistribution
{
	protected double minSkillValue = 0;
	protected double maxSkillValue = 0;
	protected Random rand = new Random();
	
	public PlayerSkillDistribution(double minSkillValue, double maxSkillValue)
	{
		this.minSkillValue = minSkillValue;
		this.maxSkillValue = maxSkillValue;
	}
	
	public double getSkillValueBounded()
	{
		double skillValue = this.getSkillValue();

		// If getting the skill from the distribution causes the skill to be greater than the max skill rating
		// or less than the minimum skill rating, repoll the distribution. This is done via recursion.
		if ((skillValue > this.maxSkillValue) || (skillValue < this.minSkillValue))
		{
			skillValue = this.getSkillValueBounded();
		}
		return skillValue;

	}
	
	abstract double getSkillValue();
}
