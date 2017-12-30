package com.slohrsh.ki.machineLearning.math;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.slohrsh.ki.machineLearning.Point;

public class VectorFactory {

	public static List<Point> readFile(File file)
	{
		try {
			CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
			List<CSVRecord> records = parser.getRecords();
			List<Point> points = new ArrayList<Point>();
			for(CSVRecord record : records)
			{
				int size = record.size();
				float[] basicVector = new float[size-1];
				for(int i = 0; i < size-1; i++)
				{
					basicVector[i] = Float.parseFloat(record.get(i));
				}
				IVector point = new Vector(basicVector);
				String clazz = record.get(size-1);
				points.add(new Point(clazz, point));
			}
			
			return points;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
