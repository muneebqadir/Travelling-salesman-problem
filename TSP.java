import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Random;

//Some useful code for the CS2004 (2018-2019) Travelling Salesman Worksheet
public class TSP 
{
	static private Random rand;
	//Print a 2D double array to the console Window
	static public void PrintArray(double x[][])
	{
		for(int i=0;i<x.length;++i)
		{
			for(int j=0;j<x[i].length;++j)
			{
				System.out.print(x[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	//This method reads in a text file and parses all of the numbers in it
	//This method is for reading in a square 2D numeric array from a text file
	//This code is not very good and can be improved!
	//But it should work!!!
	//'sep' is the separator between columns
	static public double[][] ReadArrayFile(String filename,String sep)
	{
		double res[][] = null;
		try
		{
			BufferedReader input = null;
			input = new BufferedReader(new FileReader(filename));
			String line = null;
			int ncol = 0;
			int nrow = 0;
			
			while ((line = input.readLine()) != null) 
			{
				++nrow;
				String[] columns = line.split(sep);
				ncol = Math.max(ncol,columns.length);
			}
			res = new double[nrow][ncol];
			input = new BufferedReader(new FileReader(filename));
			int i=0,j=0;
			while ((line = input.readLine()) != null) 
			{
				
				String[] columns = line.split(sep);
				for(j=0;j<columns.length;++j)
				{
					res[i][j] = Double.parseDouble(columns[j]);
				}
				++i;
			}
		}
		catch(Exception E)
		{
			System.out.println("+++ReadArrayFile: "+E.getMessage());
		}
	    return(res);
	}
	//This method reads in a text file and parses all of the numbers in it
	//This code is not very good and can be improved!
	//But it should work!!!
	//It takes in as input a string filename and returns an array list of Integers
	static public ArrayList<Integer> ReadIntegerFile(String filename)
	{
		ArrayList<Integer> res = new ArrayList<Integer>();
		Reader r;
		try
		{
			r = new BufferedReader(new FileReader(filename));
			StreamTokenizer stok = new StreamTokenizer(r);
			stok.parseNumbers();
			stok.nextToken();
			while (stok.ttype != StreamTokenizer.TT_EOF) 
			{
				if (stok.ttype == StreamTokenizer.TT_NUMBER)
				{
					res.add((int)(stok.nval));
				}
				stok.nextToken();
			}
		}
		catch(Exception E)
		{
			System.out.println("+++ReadIntegerFile: "+E.getMessage());
		}
	    return(res);
	}
	//Input 2 ints
	//Output Uniform random b/w a and b
	static public int UI(int aa,int bb)
	{
		int a = Math.min(aa,bb);
		int b = Math.max(aa,bb);
		if (rand == null) 
		{
			rand = new Random();
			rand.setSeed(System.nanoTime());
		}
		int d = b - a + 1;
		int x = rand.nextInt(d) + a;
		return(x);
	}
	static public double UR(double a,double b)
	{
		if (rand == null) 
		{
			rand = new Random();
			rand.setSeed(System.nanoTime());
		}
		return((b-a)*rand.nextDouble()+a);
	}
	// input N,T,D
	// out Fitness
	public double GetFitness(int N,ArrayList<Integer> T,double D[][]) {
		double fitnes = 0;
		for(int i=0;i<N;i++)
		{
			int a =T.get(i); 
			int b =T.get(i+1);
			//loop breaks when a/b =48
			//if(a<48 && b<48)
			{
				double Distance = D[a][b];
				fitnes= fitnes+Distance;
			}
			
		}
		int start_city = T.get(0);
		int end_city = T.get(N);
		fitnes = fitnes+ D[end_city][start_city];
		return fitnes;
	}
	//Input OPT file Arraylist, D
	//Output Optimsl Fitness
	public double OptimalFitness(int N, ArrayList<Integer> T,double D[][])
	{
		double fitness = GetFitness(N,T,D);
		return fitness;
	}
	// Input Solution,Opt fitness
	// Out Effeciency
	public double GetEfficiency(double Optimal,double Solutiton)
	{
		double effecieny= (Optimal/Solutiton)*100;
		return effecieny;
	}
	
	
}