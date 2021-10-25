import java.util.ArrayList;

public class SA {

	static RMHC RMHC = new RMHC();
	static TSP TSP = new TSP();
	
	public double Simulated_Anealing(ArrayList<Integer> tour,double StartingTemp, Integer iter, double Lmbda,double D[][])
	{
		double newfitness=0;
		double fitness =0;
		double prscore =0;
		int tour_length = D.length; 
		int N = tour_length-1;
		
		for(int i =0; i<iter; i++)
		{
			
			fitness = TSP.GetFitness(N,tour,D);
			ArrayList<Integer> oldTour = new ArrayList<Integer>();
			oldTour = (ArrayList<Integer>)tour.clone();
			tour=(RMHC.SmallChange(tour));
			newfitness = TSP.GetFitness(N, tour, D);
			
			if(newfitness>fitness)
			{
				prscore = PR(newfitness, fitness, StartingTemp);
			
				if(prscore < TSP.UR(0.0,1.0)) {
				tour = oldTour;
				}
				else {
				//Dont do anything
				}
			}else {
				//Dont do anything
			}
			StartingTemp = Lmbda *StartingTemp;
			
		}

			//saEfficiency = Calculations.CalculateEfficiency(saFitness);
			//saMSTEfficiency = Calculations.CalculateEfficiencyOfMST(saFitness);
			fitness = TSP.GetFitness(tour.size()-1, tour, D);
		
			
		
		
		return fitness;
		
	}
	
	public double PR(double newfitness, double fitness, double Temp)
	{
		double changeInFitness = Math.abs(newfitness - fitness);
		changeInFitness = -1 * changeInFitness;
		double PRscore = Math.exp(changeInFitness / Temp);
		return PRscore;
			
	}
	// raj created the method below i have no idea what it does it wasnt called
	// it looks similer to the one above and i think this is the wrong version of PR
	static double CalculateAcceptanceProbability(double newFitness, double oldFitness, double temperature) {
		double deltaFitness = newFitness - oldFitness;
		double p = 1 / (1 + Math.exp(deltaFitness / temperature));
		return p;
	}
	
	
	
}
