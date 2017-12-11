package com.slohrsh.ki.machineLearning.perzeptron;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;
import com.slohrsh.ki.machineLearning.math.Vector;
import com.slohrsh.ki.machineLearning.perzeptron.Perzeptron;

public class PerzeptronTest{

	List<IVector> mPlus = new ArrayList<IVector>();
	List<IVector> mMinus = new ArrayList<IVector>();;
	private void initializeValues()
	{
		mMinus.add(new Vector(new float[] {6,1}));
		mMinus.add(new Vector(new float[] {7,3}));
		mMinus.add(new Vector(new float[] {8,2}));
		mMinus.add(new Vector(new float[] {9,0}));
		
		mPlus.add(new Vector(new float[] {8,4}));
		mPlus.add(new Vector(new float[] {8,6}));
		mPlus.add(new Vector(new float[] {9,2}));
		mPlus.add(new Vector(new float[] {9,5}));
	}
	
	@Test
	public void perzeptron()
	{
		Perzeptron perzi = new Perzeptron();
		IVector w = new Vector(new float[] {1,1});
		
		initializeValues();
		
		try {
			IVector result = perzi.classify(w, mPlus, mMinus);
			result.print();
		} catch (UnequalDimensionException e) {
			e.printStackTrace();
		}
	}
}
