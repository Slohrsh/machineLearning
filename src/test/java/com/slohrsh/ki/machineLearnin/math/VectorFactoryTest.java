package com.slohrsh.ki.machineLearnin.math;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.slohrsh.ki.machineLearning.math.IVector;
import com.slohrsh.ki.machineLearning.math.VectorFactory;
import org.junit.Test;

public class VectorFactoryTest {

	
	@Test
	public void parse()
	{
		File learnedPointsPath = new File("src/main/resources/LearnedPoints.csv");
		Map<String, List<IVector>> map = VectorFactory.readFile(learnedPointsPath);
	}
}
