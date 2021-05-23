package PlayerBase;

import java.util.ArrayList;
import java.util.Random;

public class PlayerPopulation
{
	private static ArrayList<Player> availablePlayers = new ArrayList<Player>();
	private static ArrayList<Player> unavailablePlayers = new ArrayList<Player>();
	
	public PlayerPopulation(int totalNumPlayers, PlayerSkillDistribution dist)
	{
		for(int count = 0; count < totalNumPlayers; count++)
		{
			availablePlayers.add(new Player(dist.getSkillValue()));
		}
	}
	
	public Player getRandomAvailablePlayer(double targetSkill, double allowableRange)
	{
		Random rand = new Random();
		
		Player player = availablePlayers.get((int) (rand.nextGaussian() + (availablePlayers.size()/2.0)));
		
		if(player.getTrueSkill() <= targetSkill+allowableRange ||
				player.getTrueSkill() >= targetSkill-allowableRange)
		{
			availablePlayers.remove(player);
			unavailablePlayers.add(player);
			return player;
		}
		else
		{
			return null;
		}
	}
	
	public void updatePlayerSkills()
	{
		
	}
	
	public void checkPlayerActivity()
	{
		
	}
}
