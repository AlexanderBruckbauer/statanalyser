package edu.glyndwr.statanalyser.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.glyndwr.statanalyser.model.UnivariantDataSetTo;

public class AnalysisController {
	HashMap<String,UnivariantDataSetTo> datasets;
	
	public AnalysisController() {
		datasets = new HashMap<String,UnivariantDataSetTo>();
	}
	
	public Double runTTestforTagWithDiffrentTimeRange(String tag, Date sampleOneStart, Date sampleOneEnd, Date sampleTwoStart, Date sampleTwoEnd) {
		Double result = null;
		UnivariantDataSetTo tagData = datasets.get(tag);
		List<Double> sample1 =new ArrayList<Double>(tagData.getSubSetFromTo(sampleOneStart, sampleOneEnd).values());
		List<Double> sample2 =new ArrayList<Double>(tagData.getSubSetFromTo(sampleTwoStart, sampleTwoEnd).values());
		TTestDependentSamples test = new TTestDependentSamples();
		result = test.testDoubleSidedTTestDependentSamples(sample1, sample2);
		return result;
	}
}
