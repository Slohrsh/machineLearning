package com.slohrsh.ki.machineLearning.nearestNeighbour;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.slohrsh.ki.machineLearning.Crossvalidation;
import com.slohrsh.ki.machineLearning.Point;
import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.Normalizor;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;
import com.slohrsh.ki.machineLearning.math.Vector;
import com.slohrsh.ki.machineLearning.math.VectorFactory;

public class NearestNeighbourTest {

	List<Point> learnedPoints = new ArrayList<Point>();
	private KNearestNeighbour nearest = new KNearestNeighbour();
	private int k = 1;

	private void initializeValues() {

		learnedPoints.add(new Point("0", new Vector(new float[] { 6, 1 })));
		learnedPoints.add(new Point("0", new Vector(new float[] { 7, 3 })));
		learnedPoints.add(new Point("0", new Vector(new float[] { 8, 2 })));
		learnedPoints.add(new Point("0", new Vector(new float[] { 9, 0 })));
		
		learnedPoints.add(new Point("1", new Vector(new float[] { 8, 4 })));
		learnedPoints.add(new Point("1", new Vector(new float[] { 8, 6 })));
		learnedPoints.add(new Point("1", new Vector(new float[] { 9, 2 })));
		learnedPoints.add(new Point("1", new Vector(new float[] { 9, 5 })));
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
	public void findBestKWithNormalizedPoints() {
		Crossvalidation validation = new Crossvalidation();
		File learnedPointsPath = new File("src/main/resources/LearnedPoints.csv");
		File testDataPath = new File("src/main/resources/TestData.csv");
		List<Point> learnedPoints = VectorFactory.readFile(learnedPointsPath);
		List<Point> testData = VectorFactory.readFile(testDataPath);
		learnedPoints = Normalizor.normalize(learnedPoints, learnedPoints.get(0).getPoint().getDimension());
		testData = Normalizor.normalize(testData, testData.get(0).getPoint().getDimension());
		
		List<Point> allPoints = new ArrayList<Point>();
		allPoints.addAll(learnedPoints);
		allPoints.addAll(testData);
		
		int bestK = validation.crossValidate(allPoints, allPoints.size()/10, 10);
		System.out.println("Best K with normalized points is: " + bestK);
	}
	
	@Test
	public void findBestKWithoutNormalizedPoints() {
		Crossvalidation validation = new Crossvalidation();
		File learnedPointsPath = new File("src/main/resources/LearnedPoints.csv");
		File testDataPath = new File("src/main/resources/TestData.csv");
		List<Point> learnedPoints = VectorFactory.readFile(learnedPointsPath);
		List<Point> testData = VectorFactory.readFile(testDataPath);
		
		List<Point> allPoints = new ArrayList<Point>();
		allPoints.addAll(learnedPoints);
		allPoints.addAll(testData);
		
		int bestK = validation.crossValidate(allPoints, allPoints.size()/10, 10);
		System.out.println("Best K without normalized points is: " + bestK);
	}

	@Test
	public void neareastNeigbourWithFile() {
		File learnedPointsPath = new File("src/main/resources/LearnedPoints.csv");
		File testDataPath = new File("src/main/resources/TestData.csv");
		List<Point> learnedPoints = VectorFactory.readFile(learnedPointsPath);
		List<Point> testData = VectorFactory.readFile(testDataPath);

		int correctClassified = 0;
		int falseClassified = 0;
		
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
		System.out.println("Correctnes: " + correctnes);

	}
	
	@Test
	public void neareastNeigbourWithFileNormalized() {
		File learnedPointsPath = new File("src/main/resources/LearnedPoints.csv");
		File testDataPath = new File("src/main/resources/TestData.csv");
		List<Point> learnedPoints = VectorFactory.readFile(learnedPointsPath);
		List<Point> testData = VectorFactory.readFile(testDataPath);
		
		learnedPoints = Normalizor.normalize(learnedPoints, learnedPoints.get(0).getPoint().getDimension());
		testData = Normalizor.normalize(testData, testData.get(0).getPoint().getDimension());
		
		int correctClassified = 0;
		int falseClassified = 0;
		
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
		System.out.println("Correctnes of Normalized Vector: " + correctnes);

	}
}
