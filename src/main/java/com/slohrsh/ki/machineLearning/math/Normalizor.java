package com.slohrsh.ki.machineLearning.math;

import java.util.ArrayList;
import java.util.List;

import com.slohrsh.ki.machineLearning.Point;

public class Normalizor {

	
	public static List<Point> normalize(List<Point> points, int dimension)
	{
		List<Point> newPoints = new ArrayList<Point>();
		for(Point point : points)
		{
			Point newPoint = new Point(point.getClazz(), new Vector(dimension));
			newPoints.add(newPoint);
		}
		
		for(int i = 0; i < dimension; i++)
		{
			int numberOfValues = 0;
			double sum = 0;
			double mean = 0;
			
			for(Point point : points)
			{
				sum += point.getPoint().getValues()[i];
				numberOfValues++;
			}
			
			mean = sum/numberOfValues;
			
			double varianceSum = 0;
			double variance = 0;
			double deviation = 0;
			
			for(Point point : points)
			{
				varianceSum += Math.pow(point.getPoint().getValues()[i], mean);
			}
			
			variance = varianceSum/numberOfValues;
			deviation = Math.sqrt(variance);
			
			for(int j = 0; j < points.size(); j++)
			{
				Point point = points.get(j);
				Point newPoint = newPoints.get(j);
				newPoint.getPoint().add(i, (float) ((point.getPoint().getValues()[i] - mean)/deviation));
			}
		}
		return newPoints;
	}
	
}
