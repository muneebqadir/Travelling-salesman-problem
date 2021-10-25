import java.util.ArrayList;

public class Lab15 {
	
	static TSP TSP = new TSP();
	static RMHC RMHC = new RMHC();
	static SA SA = new SA();
	//static MST MST = new MST();
	public static String filename = "C:\\Users\\Muneeb\\Documents\\JAVA YEAR2\\Lab15\\src\\TSP_48.txt";
	public static String filename2 = "C:\\Users\\Muneeb\\Documents\\JAVA YEAR2\\Lab15\\src\\TSP_48_OPT.txt";
	public static void main(String[] args) {
		//Initalizing Variables
		ArrayList<Integer> tour = new ArrayList<Integer>();
		ArrayList<Integer> optimal_tour = new ArrayList<Integer>();
		
		double distance_array[][]=RMHC.SetDistance(filename);
		int N = distance_array.length-1;
		int tour_lenght = N+1;
		optimal_tour = TSP.ReadIntegerFile(filename2);
		double optimal_Fitness = TSP.OptimalFitness(N,optimal_tour, distance_array);
		
		// Setting ArrayList tour with random values
				tour = RMHC.RandomTour(tour_lenght);
				
		double stochasticTemperature =  (TSP.GetFitness(N, tour, distance_array)) / 1000;
		double annealingTemperature = TSP.GetFitness(N, tour, distance_array) / 250;
		double coolingRate = CalculateCoolingRate(stochasticTemperature, 10000);
		
		
		// RMHC
		double soulutionFitness = RMHCiter(tour,N,distance_array,10000);
		System.out.println("");
		
		// Printing Results
		System.out.println("Optimal Fitness = "+optimal_Fitness);
		// Checking Efficiency
		double efficiency = TSP.GetEfficiency(optimal_Fitness, soulutionFitness);
		// Printing stats
		System.out.println("Effeciency by OPT-Tour is "+efficiency);
		System.out.println("");
		System.out.println("Number of Cities are "+tour.size());
		
		
		
		// SA calling here
		
		
		double SA_Fitness = SA.Simulated_Anealing(tour,annealingTemperature,10000,coolingRate,distance_array);
		System.out.println("SA FITNESS IS ABOUT");
		System.out.println(SA_Fitness);
		

		 //Checking Effeciency by MST.
		distance_array=MST.PrimsMST(distance_array);
		double sum = 0;
		for (int i=0;i<distance_array.length;i++)
		{
			for (int j=0;j<distance_array.length;j++)
			{
				sum = sum+ distance_array[i][j];
			}
			
		}
		System.out.println(sum);
		
		double optraj= (sum)*100;
		System.out.println("OPT FINAL =  "+optraj);
		
		
	}
	
	// i spent alot of time exchanging fitness instead tours are supposed to be changed.
	
	public static double RMHCiter(ArrayList<Integer> tour,int N, double x[][], int iter ) 
	{
		double fitness=0;
		double newfitness=0;
		ArrayList<Integer> oldTour = new ArrayList<Integer>(); ;
		for(int i=0;i<iter;i++)
		{
			
			oldTour = (ArrayList<Integer>)tour.clone();
			fitness = TSP.GetFitness(N,tour,x);
			tour=(RMHC.SmallChange(tour));
			newfitness = TSP.GetFitness(N, tour, x);
			if(newfitness>fitness)
			{
				tour = oldTour;
			}
			
			
			System.out.println("Current Fitness = "+fitness+" , Iteratioin Number = "+ (i+1));
			
		}
		return fitness;
	}
	

	static double CalculateCoolingRate(double startingTemperature, int numberOfIterations) {
		double tIter, tValue, coolingRate, powerValue;
		int iter;
		// The tValue and tIter are named after the SA PowerPoint Presentation.
		// Value chosen from SA PowerPoint Slide 28
		tIter = 0.001;
		iter = numberOfIterations;
		tValue = tIter / startingTemperature;
		powerValue = 1.0 / iter;
		coolingRate = Math.pow(tValue, powerValue);
		return coolingRate;
	
	}
}
