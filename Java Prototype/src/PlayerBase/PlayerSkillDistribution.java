package PlayerBase;

import java.util.Random;

abstract class PlayerSkillDistribution
{
	protected double minSkillValue = 0;
	protected double maxSkillValue = 0;
	protected Random rand = new Random();
	
	public abstract double getSkillValue();
}
