package com.slohrsh.ki.machineLearning;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		System.out.println("Start classifying");
		Perzeptron perzi = new Perzeptron();
		IVector w = new Vector(new float[] {1,1});
		w.add(0, 1);
		w.add(1, 1);
		
		List<IVector> mPlus = new ArrayList<IVector>();
		List<IVector> mMinus = new ArrayList<IVector>();;
		
		mMinus.add(new Vector(new float[] {6,1}));
		mMinus.add(new Vector(new float[] {7,3}));
		mMinus.add(new Vector(new float[] {8,2}));
		mMinus.add(new Vector(new float[] {9,0}));
		
		mPlus.add(new Vector(new float[] {8,4}));
		mPlus.add(new Vector(new float[] {8,6}));
		mPlus.add(new Vector(new float[] {9,2}));
		mPlus.add(new Vector(new float[] {9,5}));
		
		
		try {
			IVector result = perzi.classify(w, mPlus, mMinus);
			result.print();
		} catch (UnequalDimensionException e) {
			e.printStackTrace();
		}
		System.out.println("Finished classifying");
	}

}
