package com.slohrsh.ki.machineLearning.perzeptron;

import java.util.List;

import com.slohrsh.ki.machineLearning.IClassifier;
import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;

public class Perzeptron implements IClassifier{


	public IVector classify(IVector w, List<IVector> mPlus, List<IVector> mMinus) throws UnequalDimensionException {
		
		boolean isCorrectClassified = false;
		int iterations = 0;
		while(!isCorrectClassified) 
		{
			isCorrectClassified = true;
			for(IVector x : mPlus)
			{
				if(w.times(x)<=0)
				{
					w = w.plus(x);
					isCorrectClassified = false;
				}
			}
			for(IVector x : mMinus)
			{
				if(w.times(x)>0)
				{
					w = w.minus(x);
					isCorrectClassified = false;
				}
			}
			System.out.println("Iteration: " + iterations);
			iterations++;
			if(iterations > 1000)
			{
				System.out.println("Add additional Dimension");
				w.addDimension();
				for(IVector x : mPlus)
				{
					x.addDimension();
				}
				for(IVector x : mMinus)
				{
					x.addDimension();
				}
				iterations = 0;
			}
		}
		return w;
	}
	
	
}
