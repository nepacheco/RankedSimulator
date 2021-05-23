package PlayerBase;

public class Player
{
	private double trueSkill;
	
	private double combatScore = 0;
	private int numKills = 0;
	private int numAssists = 0;
	private int numDeaths = 0;
	
	public static final int AFK = 0;
	public static final int DEAD = 1;
	public static final int ALIVE = 2;
	private int currentState = ALIVE;
	private int roundsPlayed;
	
	public Player(double skill)
	{
		this.trueSkill = skill;
	}
	
	public void resetRound()
	{
		currentState = ALIVE;
	}
	
	public void winGame()
	{
		
	}

	/**
	 * @return (double) The Player's current combat score.
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
	 * @return (int) The number of rounds played.
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
	 * @return (int) The Player's current state value.
	 */
	public int getCurrentState()
	{
		return currentState;
	}
	
	public void addKill()
	{
		if(currentState == ALIVE)
			numKills++;
	}
	
	public void addAssist()
	{
		if(currentState == ALIVE)
			numAssists++;
	}
	
	public void addDeath()
	{
		if(currentState == ALIVE)
			numDeaths++;
		currentState = DEAD;
	}

	/**
	 * @return (double) The Player's true skill value.
	 */
	public double getTrueSkill()
	{
		return trueSkill;
	}
}
