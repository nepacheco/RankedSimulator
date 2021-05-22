package management;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import game.Game;
import game.Player;
import game.Team;

public class Main
{
	private static Random rand = new Random();
	private static final int NUM_SIMULATIONS = 10000;
	private static int numDefenderEliminations = 0;
	private static int numAttackerEliminations = 0;
	private static int numDefuses = 0;
	private static int numDetonations = 0;
	private static int numTimeExpired = 0;
	private static int numSpikePlants = 0;
	
	public static void main(String[] args)
	{
		long start_time = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_SIMULATIONS; i++)
		{
			ArrayList<Player> teamList1 = new ArrayList<Player>();
			ArrayList<Player> teamList2 = new ArrayList<Player>();
			
			for(int j = 0; j < 5; j++)
			{
				teamList1.add(new Player (rand.nextGaussian()+5));
				teamList2.add(new Player (rand.nextGaussian()+5));
			}
			
			Team t1 = new Team(teamList1);
			Team t2 = new Team(teamList2);
			
			new Game(new Team(teamList1), new Team(teamList2), 13, 2).simulateGame();
		}
		
		double total = numAttackerEliminations + numDefenderEliminations + numTimeExpired + numDetonations + numDefuses;
		
		long end_time = System.currentTimeMillis();
		System.out.println(end_time-start_time);

		System.out.println("Time expired:             " + formatPercentage(numTimeExpired,total));
		System.out.println("Attackers eliminated:     " + formatPercentage(numAttackerEliminations,total));
		System.out.println("Spike defused:            " + formatPercentage(numDefuses,total));
		System.out.println("Defending win percentage: " + formatPercentage((numTimeExpired+numAttackerEliminations+numDefuses),total));
		System.out.println();
		System.out.println("Defenders eliminated:     " + formatPercentage(numDefenderEliminations,total));
		System.out.println("Spike detonated:          " + formatPercentage(numDetonations,total));
		System.out.println("Attacking win percentage: " + formatPercentage((numDefenderEliminations+numDetonations),total));
		System.out.println();
		System.out.println("Spike planted:            " + formatPercentage(numSpikePlants,total));
		System.out.println("Average Rounds:           " + total/(double) NUM_SIMULATIONS);
	}
	
	public static void recordAttackerEliminationWin()
	{
		numAttackerEliminations++;
	}
	
	public static void recordDefenderEliminationWin()
	{
		numDefenderEliminations++;
	}
	
	public static void recordDefuseWin()
	{
		numDefuses++;
	}
	
	public static void recordDetonationWin()
	{
		numDetonations++;
	}
	
	public static void recordTimeExpiredWin()
	{
		numTimeExpired++;
	}

	public static void recordSpikePlant()
	{
		numSpikePlants++;
	}
	
	public static String formatPercentage(double value, double total)
	{
		DecimalFormat df = new DecimalFormat("#.#%");
		return df.format(((double) value)/total);
	}
	
}
