package lab1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Point implements Comparable<Point>{
	int id;
	List<BigDecimal> list = new ArrayList<BigDecimal>();
	
	public Point(int id, List<BigDecimal> list) {
		super();
		this.id = id;
		this.list = list;
	}

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		double num = o.list.get(0).subtract(this.list.get(0)).doubleValue();
		if(num>0)
		{
			return 1;
		}
		else if(num==0)
		{
			return 0;
		}
		else {
			return -1;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<BigDecimal> getList() {
		return list;
	}

	public void setList(List<BigDecimal> list) {
		this.list = list;
	}
	
}
