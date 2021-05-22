package game;

import java.util.ArrayList;
import java.util.Random;

public class Team
{
	private Random rand = new Random();
	private ArrayList<Player> players;
	private int roundsWon = 0;
	
	public Team(ArrayList<Player> players)
	{
		this.players = players;
	}
	
	void reset()
	{
		for(Player p : players)
		{
			p.resetRound();
		}
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
		setRoundsWon(getRoundsWon() + 1);
	}
	
	void winGame()
	{
		for(Player player : players)
			player.winGame();
	}

	/**
	 * @return the roundsWon
	 */
	public int getRoundsWon()
	{
		return roundsWon;
	}

	/**
	 * @param roundsWon the roundsWon to set
	 */
	public void setRoundsWon(int roundsWon)
	{
		this.roundsWon = roundsWon;
	}
	
	private ArrayList<Player> getActivePlayers()
	{
		ArrayList<Player> activePlayers = new ArrayList<Player>();
		for(Player p : players)
			if(p.getCurrentState() == Player.ALIVE)
				activePlayers.add(p);
		
		return activePlayers;
	}
}
