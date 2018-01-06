package com.slohrsh.ki.machineLearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.Normalizor;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;
import com.slohrsh.ki.machineLearning.nearestNeighbour.KNearestNeighbour;

public class Crossvalidation {

	public int crossValidate(List<Point> data, int blockSize, int bounds)
	{
		List<List<Point>> listOfPoints = splitList(data, blockSize);
		float correctnes = 0;
		int bestK = 0;
		for(int k = 0; k <= bounds; k++)
		{
			for(List<Point> points : listOfPoints)
			{
				float localCorrecntes = trainPoints(data, points, k);
				if(localCorrecntes > correctnes)
				{
					correctnes = localCorrecntes;
					bestK = k;
				}
			}
		}
		return bestK;
	}

	private float trainPoints(List<Point> learnedPoints, List<Point> testData, int k) {

		int correctClassified = 0;
		int falseClassified = 0;
		KNearestNeighbour nearest = new KNearestNeighbour();
		for(Point point : testData)
		{
			try {
				String result = nearest.classify(point.getPoint(), learnedPoints, k);
				if(result.equals(point.getClazz()))
				{
					correctClassified++;
				}else
				{
					falseClassified++;
				}
			} catch (UnequalDimensionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		float correctnes;
		if(falseClassified == 0)
		{
			correctnes = 1;
		}
		else
		{
			correctnes = (float)correctClassified/(float)(correctClassified + falseClassified);
		}
		return correctnes;
	}

	private List<List<Point>> splitList(List<Point> data, int blockSize) {
		List<List<Point>> listOfPoints = new ArrayList<List<Point>>();
		for(int i = blockSize; i < data.size(); i = i + blockSize)
		{
			listOfPoints.add(data.subList(i-blockSize, i));
		}
		return listOfPoints;
	}
}
