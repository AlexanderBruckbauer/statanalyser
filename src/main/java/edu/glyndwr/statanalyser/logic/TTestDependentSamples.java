package edu.glyndwr.statanalyser.logic;

import java.util.List;

import org.apache.commons.math3.stat.inference.TTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TTestDependentSamples {
 private TTest ttest;
 private Logger log = LogManager.getLogger(TTestDependentSamples.class);
 public TTestDependentSamples() {
	 ttest = new TTest();
 }
 
 public Double testDoubleSidedTTestDependentSamples(List<Double> preData, List<Double> postData) {
	 double[] sample1 = preData.stream().mapToDouble(d -> d).toArray();
	 double[] sample2 = postData.stream().mapToDouble(d -> d).toArray();
	 log.info("sample1 :");
     for (double number : sample1) {
    	 System.out.println(String.valueOf(number));
     	// log.info(String.valueOf(number));
      }
	 log.info("sample2 :");
     for (double number : sample2) {
    	 System.out.println(String.valueOf(number));
    	// log.info(String.valueOf(number));
      }
	 return ttest.tTest(sample1, sample2);
 }
 
 public Double testSingleSidedTTestDependentSamples(List<Double> preData, List<Double> postData) {
	 double[] sample1 = preData.stream().mapToDouble(d -> d).toArray();
	 double[] sample2 = postData.stream().mapToDouble(d -> d).toArray();
	 log.info("sample1 :");
     for (double number : sample1) {
    	 log.info(String.valueOf(number));
      }
	 log.info("sample2 :");
     for (double number : sample2) {
    	 log.info(String.valueOf(number));
      }
	 return ttest.tTest(sample1, sample2)/2;
 }
}
