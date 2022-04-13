import java.util.ArrayList;

import static java.lang.Math.min;

public class Point {

	public ArrayList<Point> neighbors;
	public static Integer []types ={0,1,2,3};
	public int type;
	public int staticField;
	public boolean isPedestrian;
	public boolean blocked = false;

	public Point() {
		type=0;
		staticField = 100000;
		neighbors= new ArrayList<>();
	}
	
	public void clear() {
		staticField = 100000;
	}

	public boolean calcStaticField() {
		if(type == 1)
			return false;

		int min_ = 100000;
		for(var neigh: neighbors)
			min_ = min(min_, neigh.staticField);

		if(staticField > min_ + 1)
		{
			staticField = min_ + 1;
			return true;
		}

		return false;
	}
	
	public void move(){
		if(!blocked && isPedestrian)
		{
			int res = 10000000;
			Point dest = null;
			for(var neigh: neighbors)
				if(!neigh.isPedestrian && neigh.type != 1 && res > neigh.staticField)
				{
					dest = neigh;
					res = dest.staticField;
				}

			if(dest != null)
			{
				isPedestrian = false;
				dest.blocked = true;
				if (dest.type != 2)
					dest.isPedestrian = true;
			}
		}
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
}
