package com.slohrsh.ki.machineLearning.nearestNeighbour;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.slohrsh.ki.machineLearning.Point;
import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;

public class KNearestNeighbour {

	List<PointDistance> pointDistances = new ArrayList<PointDistance>();
	Set<String> knownClasses = new HashSet<String>();
	Comparator<PointDistance> c = new Comparator<PointDistance>() {

		public int compare(PointDistance o1, PointDistance o2)
		{
			return o1.getDistance().compareTo(o2.getDistance());
		}
		
	};
	
	public String classify(IVector newPoint, List<Point> learnedPoints, int k) throws UnequalDimensionException {
		
		for(Point point : learnedPoints)
		{
			calculateDistance(point.getClazz(), point.getPoint(), newPoint);
			addKnownClass(point.getClazz());
		}
		
		pointDistances.sort(c);
		
		return classify(k);
	}

	private void addKnownClass(String clazz) {
		knownClasses.add(clazz);
	}

	private String classify(int k) 
	{		
		for(String key : knownClasses)
		{
			int count = 0;
			for(int i = 0; i < k; i++)
			{
				if(pointDistances.size()>i)
				{
					if(pointDistances.get(i).getClazz().equals(key))
					{
						count++;
					}
				}
			}
			if(count > k/knownClasses.size())
			{
				return key;
			}
		}
		
		return "Random";
	}

	private void calculateDistance(String clazz, IVector x, IVector newPoint) 
	{
		float distance = 0;
		for(int i = 0; i< x.getDimension(); i++)
		{
			distance += Math.abs(x.getValues()[i] - newPoint.getValues()[i]);
		}
		pointDistances.add(new PointDistance(clazz, distance));
	}

}
