package game;

import java.util.ArrayList;
import java.util.Random;

public class Team
{
	private Random rand = new Random();
	private ArrayList<Player> players;
	private int numRoundsWon = 0;
	private int numGamesWon = 0;
	
	public Team(ArrayList<Player> players)
	{
		this.players = players;
	}
	
	void resetPlayers()
	{
		for(Player p : players)
		{
			p.resetRound();
		}
	}
	
	public void resetTeam()
	{
		resetPlayers();
		numRoundsWon = 0;
	}
	
	Player getRandomActivePlayer()
	{
		ArrayList<Player> actives = getActivePlayers();
		if(actives.size() == 0)
		{
			return null;
		}
		else
			return actives.get(rand.nextInt(actives.size()));
	}
	
	int getNumActivePlayers()
	{
		return getActivePlayers().size();
	}
	
	void winRound()
	{
		setNumRoundsWon(getNumRoundsWon() + 1);
	}
	
	void winGame()
	{
		for(Player player : players)
			player.winGame();
		
		numGamesWon++;
	}

	/**
	 * @return the roundsWon
	 */
	public int getNumRoundsWon()
	{
		return numRoundsWon;
	}

	/**
	 * @param roundsWon the roundsWon to set
	 */
	public void setNumRoundsWon(int roundsWon)
	{
		this.numRoundsWon = roundsWon;
	}
	
	private ArrayList<Player> getActivePlayers()
	{
		ArrayList<Player> activePlayers = new ArrayList<Player>();
		for(Player p : players)
			if(p.getCurrentState() == Player.ALIVE)
				activePlayers.add(p);
		
		return activePlayers;
	}

	/**
	 * @return the gamesWon
	 */
	public int getNumGamesWon()
	{
		return numGamesWon;
	}
}
