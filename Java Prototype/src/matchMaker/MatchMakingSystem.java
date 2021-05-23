package matchMaker;

import PlayerBase.Player;
import PlayerBase.PlayerPopulation;

public class MatchMakingSystem
{
	// TODO define match making system parameters
	public MatchMakingSystem()
	{
		
	}
	
	// TODO make teams
	public Player[][] createMatchup(PlayerPopulation playerPop, int numTeams, int numPlayersPerTeam)
	{
		Player[][] players = new Player[numTeams][numPlayersPerTeam];
		
		for(int t = 0; t < numTeams; t++)
		{
			for(int p = 0; p < numPlayersPerTeam; p++)
			{
				for(int numSearches = 0; numSearches < 2000; numSearches++)
				{
					Player player = playerPop.getRandomAvailablePlayer(1500, 300);
					if(player != null)
					{
						players[t][p] = player;
						break;
					}
				}
			}
		}
		
		return players;
	}
}
