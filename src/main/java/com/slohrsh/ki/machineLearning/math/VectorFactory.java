package com.slohrsh.ki.machineLearning.math;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class VectorFactory {

	public static Map<String, List<IVector>> readFile(File file)
	{
		try {
			CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
			List<CSVRecord> records = parser.getRecords();
			Map<String, List<IVector>> map = new HashMap<String, List<IVector>>();
			for(CSVRecord record : records)
			{
				int size = record.size();
				float[] basicVector = new float[size-1];
				for(int i = 0; i < size-1; i++)
				{
					basicVector[i] = Float.parseFloat(record.get(i));
				}
				IVector vector = new Vector(basicVector);
				String clazz = record.get(size-1);
				List<IVector> list = map.get(clazz);
				if(list == null)
				{
					list = new ArrayList<IVector>();
				}

				list.add(vector);
				map.put(clazz, list);
			}
			
			return map;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
