package com.slohrsh.ki.machineLearning;

import java.util.List;

import com.slohrsh.ki.machineLearning.math.IVector;

public class Point{

	private String clazz;
	private IVector point;
	
	public Point(String clazz, IVector point)
	{
		this.clazz = clazz;
		this.point = point;
	}

	public String getClazz() {
		return clazz;
	}

	public IVector getPoint() {
		return point;
	}
}
