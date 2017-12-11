package com.slohrsh.ki.machineLearning.nearestNeighbour;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;
import com.slohrsh.ki.machineLearning.math.Vector;
import com.slohrsh.ki.machineLearning.math.VectorFactory;

public class NearestNeighbourTest {

	Map<String, List<IVector>> learnedPoints = new HashMap<String, List<IVector>>();
	private KNearestNeighbour nearest = new KNearestNeighbour();
	private int k = 5;

	private void initializeValues() {
		List<IVector> mPlus = new ArrayList<IVector>();
		List<IVector> mMinus = new ArrayList<IVector>();
		mMinus.add(new Vector(new float[] { 6, 1 }));
		mMinus.add(new Vector(new float[] { 7, 3 }));
		mMinus.add(new Vector(new float[] { 8, 2 }));
		mMinus.add(new Vector(new float[] { 9, 0 }));

		mPlus.add(new Vector(new float[] { 8, 4 }));
		mPlus.add(new Vector(new float[] { 8, 6 }));
		mPlus.add(new Vector(new float[] { 9, 2 }));
		mPlus.add(new Vector(new float[] { 9, 5 }));
		
		learnedPoints.put("0", mMinus);
		learnedPoints.put("1", mPlus);
	}

	@Test
	public void nearestNeigbourWithK5() {
		IVector w = new Vector(new float[] { 8, 3.5f });

		initializeValues();

		try {
			String result = nearest.classify(w, learnedPoints, 5);
			Assert.assertEquals("1", result);
		} catch (UnequalDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void nearestNeigbourWithK3() {
		IVector w = new Vector(new float[] { 8, 3.5f });

		initializeValues();

		try {
			String result = nearest.classify(w, learnedPoints, 3);
			Assert.assertEquals("0", result);
		} catch (UnequalDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void nearestNeigbourWithK2() {
		IVector w = new Vector(new float[] { 8, 3.5f });

		initializeValues();

		try {
			String result = nearest.classify(w, learnedPoints, 2);
			Assert.assertEquals("Random", result);
		} catch (UnequalDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void neareastNeigbourWithFile() {
		File learnedPointsPath = new File("src/main/resources/LearnedPoints.csv");
		File testDataPath = new File("src/main/resources/TestData.csv");
		Map<String, List<IVector>> learnedPoints = VectorFactory.readFile(learnedPointsPath);
		Map<String, List<IVector>> testData = VectorFactory.readFile(testDataPath);

		int correctClassified = 0;
		int falseClassified = 0;
		
		for (String clazz : testData.keySet()) {
			List<IVector> testPoints = testData.get(clazz);
			for (IVector testPoint : testPoints) {
				try {
					String result = nearest.classify(testPoint, learnedPoints, k);
					if(result.equals(clazz))
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
		}
		float correctnes;
		if(falseClassified == 0)
		{
			correctnes = 1;
		}
		else
		{
			correctnes = (float)correctClassified/(float)falseClassified;
		}
		System.out.println("Correctnes: " + correctnes);

	}
}
