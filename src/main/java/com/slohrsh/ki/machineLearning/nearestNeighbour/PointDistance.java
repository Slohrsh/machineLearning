package com.slohrsh.ki.machineLearning.nearestNeighbour;

public class PointDistance {

	private String clazz;
	private Float distance;
	
	public PointDistance(String clazz, Float distance)
	{
		this.clazz = clazz;
		this.distance = distance;
	}
	
	
	public String getClazz()
	{
		return clazz;
	}
	public Float getDistance()
	{
		return distance;
	}
}
