package game;

public class Player
{
	private double mmr;
	
	private double combatScore = 0;
	private int numKills = 0;
	private int numAssists = 0;
	private int numDeaths = 0;
	
	static final int AFK = 0;
	static final int DEAD = 1;
	static final int ALIVE = 2;
	private int currentState = ALIVE;
	private int roundsPlayed;
	
	public Player(double mmr)
	{
		this.mmr = mmr;
	}
	
	void resetRound()
	{
		currentState = ALIVE;
	}
	
	void winGame()
	{
		
	}

	/**
	 * @return the combatScore
	 */
	public double getCombatScore()
	{
		return combatScore;
	}

	/**
	 * @param combatScore the combatScore to set
	 */
	public void setCombatScore(double combatScore)
	{
		this.combatScore = combatScore;
	}

	/**
	 * @return the roundsPlayed
	 */
	public int getRoundsPlayed()
	{
		return roundsPlayed;
	}

	/**
	 * @param roundsPlayed the roundsPlayed to set
	 */
	public void setRoundsPlayed(int roundsPlayed)
	{
		this.roundsPlayed = roundsPlayed;
	}

	/**
	 * @return the currentState
	 */
	public int getCurrentState()
	{
		return currentState;
	}
	
	void addKill()
	{
		if(currentState == ALIVE)
			numKills++;
	}
	
	void addAssist()
	{
		if(currentState == ALIVE)
			numAssists++;
	}
	
	void addDeath()
	{
		if(currentState == ALIVE)
			numDeaths++;
		currentState = DEAD;
	}

	/**
	 * @return the mmr
	 */
	public double getMmr()
	{
		return mmr;
	}
}
