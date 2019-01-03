package edu.glyndwr.statanalyser.logic;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import edu.glyndwr.statanalyser.dataprovider.JSONLoader;
import edu.glyndwr.statanalyser.model.StackOverflowStats;
import edu.glyndwr.statanalyser.model.TagPercents;
import edu.glyndwr.statanalyser.model.UnivariantDataSetTo;


public class Analyser implements ApplicationRunner {

	JSONLoader loader;
	AnalysisController controller;
	Logger log = LogManager.getLogger(Analyser.class);
	
		public void init() {
			log.info("initializing...");
			loader = new JSONLoader();
		}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("starting analisys:");
		loader = new JSONLoader();
		StackOverflowStats stats = loader.loadJSONStatsFromStackoverflow();
		prepareAnalysisController(stats);
	}
	public void prepareAnalysisController(StackOverflowStats stats) {
		controller = new AnalysisController();
		HashMap<String,List<Double>> tagPercents = beanProperties(stats.getTagPercents());
		
		for(String setName :tagPercents.keySet()) {
			TreeMap<Date, Double> dataSetMap = new TreeMap<Date, Double>();
			ArrayList<Double> rawSetData = new ArrayList<Double>();
			rawSetData.addAll(tagPercents.get(setName));
			for (int i = 0; i < stats.getYear().size(); i++)
		      {
				Calendar c = Calendar.getInstance();
				c.set(Integer.valueOf(stats.getYear().get(i)),Integer.valueOf(stats.getMonth().get(i)), 1, 0, 0);   
				Date key = c.getTime();
		         Double value = tagPercents.get(setName).get(i);
		         dataSetMap.put(key, value);
		      }
			UnivariantDataSetTo set = new UnivariantDataSetTo(setName, rawSetData, dataSetMap);
			controller.datasets.put(setName, set);
			log.info("Set added: "+setName+" with "+String.valueOf(set.getRawSetData().size())+" entries");
		}
		AnalyseFlash();
	}
	
	public void AnalyseFlash() {
		Calendar c = Calendar.getInstance();
		c.set(2008, 8,1,0,0);
		Date sampleOneStart = c.getTime();
		c.set(2010, 4,1,0,0);
		Date sampleOneEnd = c.getTime();
		c.set(2010, 5,1,0,0);
		Date sampleTwoStart = c.getTime();
		c.set(2012,1,1,0,0);
		Date sampleTwoEnd = c.getTime();
		Double p = controller.runTTestforTagWithDiffrentTimeRange("flash", sampleOneStart, sampleOneEnd, sampleTwoStart, sampleTwoEnd);
		BigDecimal bigP = new BigDecimal(p);
		log.info("significance p is: "+bigP.toPlainString());
	}
	
	public HashMap<String,List<Double>> beanProperties(TagPercents bean) {
	    try {
	    	HashMap<String,List<Double>> map = new HashMap<>();
	        Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class)
	                                  .getPropertyDescriptors())
	              .stream()
	              // filter out properties with setters only
	              .filter(pd -> Objects.nonNull(pd.getReadMethod()))
	              .forEach(pd -> { // invoke method to get value
	                  try {
	                	  List<Double> value = (List<Double>) pd.getReadMethod().invoke(bean);
	                      if (value != null) {
	                          map.put(pd.getName(), value);
	                      }
	                  } catch (Exception e) {
	                	  e.printStackTrace();
	                      // add proper error handling here
	                  }
	              });
	        return map;
	    } catch (IntrospectionException e) {
	    	e.printStackTrace();
	        // and here, too
	        return new HashMap<>();
	    }
	}
}
