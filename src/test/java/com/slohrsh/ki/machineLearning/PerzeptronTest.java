package com.slohrsh.ki.machineLearning;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class PerzeptronTest extends TestCase {

	@Test
	private void TestClassifier()
	{
		Perzeptron perzi = new Perzeptron();
		IVector w = new Vector(new float[] {1,1});
		w.add(0, 1);
		w.add(1, 1);
		
		List<IVector> mPlus = new ArrayList<IVector>();
		mPlus.add(new Vector(new float[] {6,1}));
		mPlus.add(new Vector(new float[] {7,3}));
		mPlus.add(new Vector(new float[] {8,2}));
		mPlus.add(new Vector(new float[] {9,0}));
		
		List<IVector> mMinus = new ArrayList<IVector>();;
		mMinus.add(new Vector(new float[] {8,4}));
		mMinus.add(new Vector(new float[] {8,6}));
		mMinus.add(new Vector(new float[] {9,2}));
		mMinus.add(new Vector(new float[] {9,5}));
		
		
		try {
			IVector result = perzi.classify(w, mPlus, mMinus);
		} catch (UnequalDimensionException e) {
			e.printStackTrace();
		}
	}
}
