package game;

import java.util.ArrayList;

class Game
{
	private Team t1;
	private Team t2;
	private int minimumRoundsToWin;
	private int minimumWinningMargin;
	private ArrayList<Round> rounds = new ArrayList<Round>();
	private boolean gameInProgress = false;
	private boolean overtime = false;
	private boolean firstHalf = true;
	private String winner;
	
	Game(Team t1, Team t2, int minimumRoundsToWin, int minimumWinningMargin)
	{
		this.t1 = t1;
		this.t2 = t2;
		this.minimumWinningMargin = Integer.max(1, minimumWinningMargin);
		this.minimumRoundsToWin = Integer.max(this.minimumWinningMargin, minimumRoundsToWin);
	}
	
	void simulateGame()
	{
		gameInProgress = true;
		updateGameState();
		while(gameInProgress)
		{
			if(firstHalf)
				generateRound(t1, t2);
			else
				generateRound(t2, t1);
			updateGameState();
		}
	}
	
	String getWinner()
	{
		return winner;
	}
	
	private void updateGameState()
	{
		if(t1.getNumRoundsWon() >= minimumRoundsToWin || t2.getNumRoundsWon() >= minimumRoundsToWin)
		{
			if(t1.getNumRoundsWon()-t2.getNumRoundsWon() >= minimumWinningMargin)
			{
				t1.winGame();
				winner = "Team 1";
				gameInProgress = false;
			}
			else if(t2.getNumRoundsWon()-t1.getNumRoundsWon() >= minimumWinningMargin)
			{
				t2.winGame();
				winner = "Team 2";
				gameInProgress = false;
			}
			else
			{
				overtime = true;
				gameInProgress = true;
			}
		}

		
		if(gameInProgress)
		{
			if(rounds.size() <= minimumRoundsToWin)
			{
				firstHalf = true;
			}
			else if(rounds.size() >= minimumRoundsToWin)
			{
				firstHalf = false;
			}
		}
	}
	
	private void generateRound(Team attackers, Team defenders)
	{
		Round r = new Round(attackers, defenders);
		r.simulateRound();
		
		rounds.add(r);
	}
}
