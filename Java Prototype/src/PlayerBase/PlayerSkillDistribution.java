package PlayerBase;

import java.util.Random;

abstract class PlayerSkillDistribution
{
	protected double minSkillValue = 0;
	protected double maxSkillValue = 0;
	protected Random rand = new Random();
	
	public PlayerSkillDistribution(double minSkillValue, double maxSkillValue)
	{
		this.minSkillValue = minSkillValue;
		this.maxSkillValue = maxSkillValue;
	}
	
	public abstract double getSkillValue();
}
