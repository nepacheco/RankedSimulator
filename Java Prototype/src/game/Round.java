package game;

import management.Main;

public class Round
{
	private Team attackers;
	private Team defenders;
	private double probabilityOfInteraction = 0.09;
	private double roundTimer = 100.0;
	private double roundStepInterval = 1.0;
	private double spikeTimer = 45.0;
	private double spikeStepInterval = 1.0;
	
	
	private static final int BUY_PHASE = 0;
	
	private static final int ROUND_IN_PROGRESS = 1;
	private static final int SPIKE_PLANTED = 2;
	
	private static final int SPIKE_DEFUSED = 3;
	private static final int ATTACKERS_ELIMINATED = 4;
	private static final int DEFENDERS_ELIMINATED = 5;
	private static final int SPIKE_DETONATED = 6;
	private static final int TIME_ELAPSED = 7;
	
	private int roundState = BUY_PHASE;
	
	public Round(Team att, Team def)
	{
		this.attackers = att;
		attackers.reset();
		this.defenders = def;
		defenders.reset();
	}
	
	void simulateRound()
	{
		roundState = ROUND_IN_PROGRESS;
		while(roundState != ATTACKERS_ELIMINATED
				&& roundState != DEFENDERS_ELIMINATED
				&& roundState != SPIKE_DETONATED
				&& roundState != TIME_ELAPSED
				&& roundState != SPIKE_DEFUSED)
		{
			updateRoundState();
			
			switch(roundState)
			{
				case ROUND_IN_PROGRESS:
					predictInteraction();
					roundTimer = roundTimer - roundStepInterval;
					break;
				case SPIKE_PLANTED:
					predictInteraction();
					spikeTimer = spikeTimer - spikeStepInterval;
					break;
				default:
					break;
			}
			
			updateRoundState();
		}
		
		switch(roundState)
		{
			case ATTACKERS_ELIMINATED:
				Main.recordAttackerEliminationWin();
				defenders.winRound();
				break;
			case TIME_ELAPSED:
				Main.recordTimeExpiredWin();
				defenders.winRound();
				break;
			case SPIKE_DEFUSED:
				Main.recordDefuseWin();
				defenders.winRound();
				break;
			case DEFENDERS_ELIMINATED:
				Main.recordDefenderEliminationWin();
				attackers.winRound();
				break;
			case SPIKE_DETONATED:
				Main.recordDetonationWin();
				attackers.winRound();
				break;
			default:
				// TODO Error handling
				System.out.println("Error, nobody won this round?");
				break;
		}
	}
	
	private void predictInteraction()
	{
		if(Math.random() <= probabilityOfInteraction)
		{
			maybePlantOrDefuse();
			killInteraction();
		}
	}
	
	private void maybePlantOrDefuse()
	{
		double att = attackers.getNumActivePlayers();
		double def = defenders.getNumActivePlayers();
		double numPlayers = att + def;
		
		if(Math.random()*numPlayers <= 1.0)
		{
			if(roundState == ROUND_IN_PROGRESS)
			{
				if(Math.random() <= (att/def)*0.5)
				{
					roundState = SPIKE_PLANTED;
					Main.recordSpikePlant();
				}
			}
			else if(roundState == SPIKE_PLANTED)
			{
				if(Math.random() <= (def/att)*0.5)
				{
					roundState = SPIKE_DEFUSED;
				}
			}
		}
	}
	
	private void killInteraction()
	{
		Player att = attackers.getRandomActivePlayer();
		Player def = defenders.getRandomActivePlayer();
		
		if(att != null && def != null)
		{
			double totalMmr = att.getMmr() + def.getMmr();
			
			if(Math.random()*totalMmr <= att.getMmr())
			{
				att.addKill();
				def.addDeath();
			}
			else
			{
				att.addDeath();
				def.addKill();
			}
		}
	}
	
	private void updateRoundState()
	{
		switch(roundState)
		{
			case ROUND_IN_PROGRESS:
				if(attackers.getNumActivePlayers() <= 0 )
				{
					roundState = ATTACKERS_ELIMINATED;
				}
				if(defenders.getNumActivePlayers() <= 0)
				{
					roundState = DEFENDERS_ELIMINATED;
				}
				if(roundTimer <= 0.0)
				{
					roundState = TIME_ELAPSED;
				}
				break;
			case SPIKE_PLANTED:
				if(defenders.getNumActivePlayers() <= 0)
				{
					roundState = DEFENDERS_ELIMINATED;
				}
				if(spikeTimer <= 0.0)
				{
					roundState = SPIKE_DETONATED;
				}
				break;
		}
	}
}
