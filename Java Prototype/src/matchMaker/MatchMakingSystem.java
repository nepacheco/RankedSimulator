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
		for(int numSearches = 0; numSearches < 2000; numSearches++)
		{
			playerPop.getRandomAvailablePlayer(1500, 300);
		}
		
		return null;
	}
}
