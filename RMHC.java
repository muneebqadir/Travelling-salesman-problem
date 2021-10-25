import java.util.ArrayList;

public class RMHC {
	public double res[][] = null;
	public double newres[][] = null;
	TSP tspclass = new TSP();
	
	//input File name 
	//Out res
	public double [][] SetDistance(String filename) {
		res= tspclass.ReadArrayFile(filename," ");	
		return res;
	}
	//input N
	//Output Random arraylist tour
	public ArrayList<Integer> RandomTour(int N)
	{
		ArrayList<Integer> tour = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i=0;i<N;i++)
		{
			temp.add(i);
		}
		
		while(temp.size()>0)
		{
			int a = tspclass.UI(0,temp.size()-1);
			tour.add(temp.get(a));
			
			temp.remove(a);
		}
		
		return tour;
	}
	// Input Arraylist Tour
	//Output new ArrayList New_Tour
	public ArrayList<Integer> SmallChange(ArrayList<Integer> T)
	{
		int i = 0,j=0,temp;
		while (i==j)
		{
			i=tspclass.UI(0,T.size()-1);
			j=tspclass.UI(0,T.size()-1);
		}
		temp =T.get(i);
		T.add(i,T.get(j));
		T.remove(i+1);
		T.add(j,temp);
		T.remove(j+1);
		return T;
		
	}
	
	
	
	
}
