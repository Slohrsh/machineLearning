package com.slohrsh.ki.machineLearning.math;

public class Vector implements IVector{
	
	private int dimension;
	private float[] values;

	public Vector(float[] values)
	{
		this.dimension = values.length;
		this.values = values;
	}
	
	public Vector(int dimension)
	{
		this.dimension = dimension;
		this.values = new float[dimension];
	}
	
	public float times(IVector x) throws UnequalDimensionException {
		if(x.getDimension() != dimension)
		{
			throw new UnequalDimensionException();
		}
		float[] xValues = x.getValues();
		float result = 0;
		
		for(int i = 0; i < values.length; i++)
		{
			result += values[i] * xValues[i];
		}
		return result;
	}

	public Vector plus(IVector x) throws UnequalDimensionException {
		if(x.getDimension() != dimension)
		{
			throw new UnequalDimensionException();
		}
		
		float[] xValues = x.getValues();
		Vector result = new Vector(dimension);
		
		for(int i = 0; i < values.length; i++)
		{
			result.add(i, values[i] + xValues[i]);
		}
		return result;
	}
	
	public Vector minus(IVector x) throws UnequalDimensionException {
		if(x.getDimension() != dimension)
		{
			throw new UnequalDimensionException();
		}
		
		float[] xValues = x.getValues();
		Vector result = new Vector(dimension);
		
		for(int i = 0; i < values.length; i++)
		{
			result.add(i, values[i] - xValues[i]);
		}
		return result;
	}

	public int getDimension() {
		return dimension;
	}

	public float[] getValues() {
		return values;
	}

	public void add(int index, float value) {
		values[index] = value;
	}

	public void print() {
		for(int i = 0; i < values.length; i++)
		{
			System.out.println("x" + i + ": " + values[i]);
		}
	}

	public void addDimension() {
		dimension++;
		float[] tmpValues = new float[dimension];
		for(int i = 0; i < values.length; i++)
		{
			tmpValues[i] = values[i];
		}
		tmpValues[values.length] = 1;
		values = tmpValues;
	}

}
