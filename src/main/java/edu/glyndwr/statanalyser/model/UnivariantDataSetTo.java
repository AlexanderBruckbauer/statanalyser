package edu.glyndwr.statanalyser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import lombok.Data;

@Data
public class UnivariantDataSetTo {
	private String setName;
	private ArrayList<Double> rawSetData;
	private TreeMap<Date, Double> dataSetMap;

	public UnivariantDataSetTo(String setName, ArrayList<Double> rawSetData, TreeMap<Date, Double> dataSetMap) {
		this.setName = setName;
		this.rawSetData = rawSetData;
		this.dataSetMap = dataSetMap;
	}

	public TreeMap<Date, Double> getSubSetFromTo(Date startDate, Date endDate) {
		TreeMap<Date, Double> result = new TreeMap<>();
		for(Date d : dataSetMap.keySet()) {
			if(d.compareTo(startDate) >= 0 && d.compareTo(endDate) <= 0) {
				Date key = d;
				Double value = dataSetMap.get(key);
				result.put(key, value);
			}
		}
		return result;
	}
	
}
