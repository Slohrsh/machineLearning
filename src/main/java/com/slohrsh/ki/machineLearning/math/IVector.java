package com.slohrsh.ki.machineLearning.math;

public interface IVector {

	public float times(IVector x) throws UnequalDimensionException;
	public Vector plus(IVector x) throws UnequalDimensionException;
	public Vector minus(IVector x) throws UnequalDimensionException;
	public int getDimension();
	public void addDimension();
	public void add(int index, float value);
	public float[] getValues();
	public void print();
}
