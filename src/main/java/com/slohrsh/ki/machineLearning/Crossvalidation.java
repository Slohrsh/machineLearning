package com.slohrsh.ki.machineLearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slohrsh.ki.machineLearning.math.IVector;

public class Crossvalidation {

	public void crossValidate(List<Point> data, int blockSize, int bounds)
	{
		int blockEntries = data.size() / blockSize;
		List<List<Point>> listOfPoints = new ArrayList<List<Point>>();
		for(int i = blockEntries; i < data.size(); i = i + blockEntries)
		{
			listOfPoints.add(data.subList(i-blockEntries, i));
		}
		for(int i = 0; i <= bounds; i++)
		{
			for(List<Point> points : listOfPoints)
			{
				
			}
		}
	}
}
