package game;

import java.util.ArrayList;

import PlayerBase.Player;

public class GameController
{
	@SuppressWarnings("unused")
	// TODO remove unused warning when error handling is added
	public static Object[] runValorantGame(ArrayList<Player> playersTeam1, ArrayList<Player> playersTeam2)
	{
		if(playersTeam1.size() < 5)
		{
			// TODO Throw Error
		}
		if(playersTeam2.size() < 5)
		{
			// TODO Throw Error
		}
		
		if(false)	// TODO CentralCommand.AnyErrors()
		{
			return null;
		}
		else
		{
			Team team1 = new Team(playersTeam1);
			Team team2 = new Team(playersTeam2);
			
			Game game = new Game(team1, team2, 13, 2);
			game.simulateGame();
			return new Object[] {game.getWinner()};
		}
	}
}
