/**
 * 
 */
package cn.edu.whu.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import cn.edu.whu.dao.GFeatureDao;
import cn.edu.whu.daoImpl.GFeatureDaoImpl;
import cn.edu.whu.pojo.GFeature;

/**
 * @author bczhang
 *
 */
public class Modify {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 
	 
		GFeatureDao gfdao=new GFeatureDaoImpl();
		List<GFeature>list=new ArrayList<GFeature>();
		GFeature gf=null;
	   try {
		  // Betweenness_centrality Closeness_centrality degree_centrality rich_club_coefficient degree_assortativity_coefficient  Centrality AverageDegree Density PathLength Modularity wheightedDegree AverageClusteringCoefficient
		String[] textarr=FileUtils.readFileToString(new File("E:\\normal\\normalSample\\featureVec\\12graph_plusRichClub.txt")).split("\n");
        for(String ss:textarr){
        	gf=new GFeature();
	    	String[] arr=ss.split(" +");
	    	gf=gfdao.findById(arr[0]);
	    	gf.setBetweenness_centrality(arr[1]);
	    	gf.setCloseness_centrality(arr[2]);
	    	gf.setDegree_centrality(arr[3]);
	    	gf.setRich_club_coefficient(arr[13]);
	    	gf.setDegree_assortativity_coefficient(arr[5]);
	    	gf.setEigenvectorCentrality(arr[6]);
	    	gf.setAverageDegree(arr[7]);
	    	gf.setDensity(arr[8]);
	    	gf.setPathLength(arr[9]);
	    	gf.setModularity(arr[10]);
	    	gf.setWheightedDegree(arr[11]);
	    	gf.setAverageClusteringCoefficient(arr[12]);
	       
	       //gf.setUser_type("spam");
	       
	        gfdao.update(gf);
	    	//System.out.println();
	    }
	} catch (IOException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    
	}
    
}
