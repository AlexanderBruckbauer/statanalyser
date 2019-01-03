package edu.glyndwr.statanalyser.dataprovider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import edu.glyndwr.statanalyser.logic.Analyser;
import edu.glyndwr.statanalyser.model.StackOverflowStats;


public class JSONLoader {

	private String url = "https://cdn.sstatic.net/insights/data/month_tag_percents.json?v=4bd89f2f98a7";
	Logger log = LogManager.getLogger(Analyser.class);
	public StackOverflowStats loadJSONStatsFromStackoverflow() {
		log.info("initializing gson");
		Gson gson = new Gson();
		StackOverflowStats stats = null;
		   InputStream is;
		try {
			log.info("opening connection to "+url);
			is = new URL(url).openStream();
			String json = convertStreamToString(is,"UTF-8");
			log.info(json);
			stats = gson.fromJson(json, StackOverflowStats.class);		
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		   return stats;

	}
	

	public String convertStreamToString( InputStream is, String ecoding ) throws IOException
	{
	    StringBuilder sb = new StringBuilder( Math.max( 16, is.available() ) );
	    char[] tmp = new char[ 4096 ];

	    try {
	       InputStreamReader reader = new InputStreamReader( is, ecoding );
	       for( int cnt; ( cnt = reader.read( tmp ) ) > 0; )
	            sb.append( tmp, 0, cnt );
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    return sb.toString();
	}
}

