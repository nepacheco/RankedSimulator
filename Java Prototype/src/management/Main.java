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
	private static long start_time = 0L;
	
	public static void main(String[] args)
	{
		start_time = System.currentTimeMillis();
		
//		for(int i = 0; i < NUM_SIMULATIONS; i++)
//		{
//			runRandomGame();
//		}
//		
//		printResults();
		
		testSingleMatchup(new double[] {1600, 1400, 1500, 1400, 1600},
				new double[] {1500, 1500, 1500, 1500, 1500});
	}
	
	public static void runRandomGame()
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
		
		new Game(t1, t2, 13, 2).simulateGame();
	}
	
	public static void testSingleMatchup(double[] team1Ratings, double[] team2Ratings)
	{
		ArrayList<Player> teamList1 = new ArrayList<Player>();
		ArrayList<Player> teamList2 = new ArrayList<Player>();
		
		for(int j = 0; j < 5; j++)
		{
			teamList1.add(new Player (team1Ratings[j]));
			teamList2.add(new Player (team2Ratings[j]));
		}
		
		Team t1 = new Team(teamList1);
		Team t2 = new Team(teamList2);
		
		for(int i = 0; i < NUM_SIMULATIONS; i++)
		{
			t1.resetTeam();
			t2.resetTeam();
			new Game(t1, t2, 13, 2).simulateGame();
		}
		
		System.out.println("Team 1 Wins: " + t1.getNumGamesWon());
		System.out.println("Team 2 Wins: " + t2.getNumGamesWon());
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
	
	public static void printResults()
	{
		double totalRounds = numAttackerEliminations + numDefenderEliminations + numTimeExpired + numDetonations + numDefuses;
		
		System.out.println("Program run duration:     " + (System.currentTimeMillis()-start_time) + "ms");
		System.out.println();
		System.out.println("Time expired:             " + formatPercentage(numTimeExpired,totalRounds));
		System.out.println("Attackers eliminated:     " + formatPercentage(numAttackerEliminations,totalRounds));
		System.out.println("Spike defused:            " + formatPercentage(numDefuses,totalRounds) + " (" + formatPercentage(numDefuses,numSpikePlants) + ")");
		System.out.println("Defending win percentage: " + formatPercentage((numTimeExpired+numAttackerEliminations+numDefuses),totalRounds));
		System.out.println();
		System.out.println("Defenders eliminated:     " + formatPercentage(numDefenderEliminations,totalRounds));
		System.out.println("Spike detonated:          " + formatPercentage(numDetonations,totalRounds) + " (" + formatPercentage(numDetonations,numSpikePlants) + ")");
		System.out.println("Attacking win percentage: " + formatPercentage((numDefenderEliminations+numDetonations),totalRounds));
		System.out.println();
		System.out.println("Spike planted:            " + formatPercentage(numSpikePlants,totalRounds));
		System.out.println("Average Rounds:           " + totalRounds/(double) NUM_SIMULATIONS);
	}
	
	public static String formatPercentage(double value, double total)
	{
		DecimalFormat df = new DecimalFormat("#.#%");
		return df.format(((double) value)/total);
	}
	
}
