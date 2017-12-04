package com.slohrsh.ki.machineLearning;

import java.util.List;



public interface IClassifier {

	public IVector classify(IVector w, List<IVector> mPlus, List<IVector> mMinus) throws UnequalDimensionException;
}
