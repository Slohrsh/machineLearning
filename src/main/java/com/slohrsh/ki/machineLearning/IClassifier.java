package com.slohrsh.ki.machineLearning;

import java.util.List;

import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.UnequalDimensionException;



public interface IClassifier {

	public IVector classify(IVector w, List<IVector> mPlus, List<IVector> mMinus) throws UnequalDimensionException;
}
