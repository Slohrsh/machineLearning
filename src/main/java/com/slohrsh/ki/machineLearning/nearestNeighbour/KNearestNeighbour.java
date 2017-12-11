package com.slohrsh.ki.machineLearning.nearestNeighbour;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;

public class KNearestNeighbour {

	List<PointDistance> pointDistances = new ArrayList<PointDistance>();
	Set<String> learnedPointsKeySet;

	Comparator<PointDistance> c = new Comparator<PointDistance>() {

		public int compare(PointDistance o1, PointDistance o2)
		{
			return o1.getDistance().compareTo(o2.getDistance());
		}
		
	};
	
	public String classify(IVector newPoint, Map<String, List<IVector>> learnedPoints, int k) throws UnequalDimensionException {
		
		learnedPointsKeySet = learnedPoints.keySet();
		for(String key : learnedPoints.keySet())
		{
			calculateDistance(key, learnedPoints.get(key), newPoint);
		}
		
		pointDistances.sort(c);
		
		return classify(k);
	}

	private String classify(int k) 
	{		
		for(String key : learnedPointsKeySet)
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
			if(count > k/learnedPointsKeySet.size())
			{
				return key;
			}
		}
		
		return "Random";
	}

	private void calculateDistance(String clazz, List<IVector> mPlus, IVector newPoint) {
		for(IVector x : mPlus)
		{
			float distance = 0;
			for(int i = 0; i< x.getDimension(); i++)
			{
				distance += Math.abs(x.getValues()[i] - newPoint.getValues()[i]);
			}
			pointDistances.add(new PointDistance(clazz, distance));
		}
	}

}
