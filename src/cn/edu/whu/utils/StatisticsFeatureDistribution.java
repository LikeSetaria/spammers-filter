/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import cn.edu.whu.pojo.GFeature;

/**
 * @author bczhang
 *对特征进行分布统计 即每个特征值段，样本的分布情况
 */
public class StatisticsFeatureDistribution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		statistics();
	}
	//统计累加分布
	public static void statistics(){
		List<GFeature> spamlist=new LinkedList<>();
		List<GFeature> normallist=new LinkedList<>();
		try {
			String[] textArr=FileUtils.readFileToString(new File("C:\\Users\\bczhang\\Desktop\\yq\\spam_GraphFeature_rcc_avg.txt")).split("\n");
			for(String sss:textArr){
				String[] arr=sss.split(" ");
				
			//	if(arr[0].equals("1.0")){
					
				GFeature gf=new GFeature();
				gf.setUid("1");
//				gf.setBetweenness_centrality(arr[1]);
//				gf.setCloseness_centrality(arr[2]);
//				gf.setDegree_centrality(arr[3]);
				gf.setRich_club_coefficient(arr[1]);
//				gf.setDegree_assortativity_coefficient(arr[5]);
//				gf.setEigenvectorCentrality(arr[6]);
//				gf.setAverageDegree(arr[7]);
//				gf.setDensity(arr[8]);
//				gf.setPathLength(arr[9]);
//				gf.setModularity(arr[10]);
//				gf.setWheightedDegree(arr[11]);
//				gf.setAverageClusteringCoefficient(arr[12]);
				//System.out.println(gf.toString());
				spamlist.add(gf);
				//}else {
					
	//				GFeature gf=new GFeature();
	//				gf.setUid("2");
//					gf.setBetweenness_centrality(arr[1]);
//					gf.setCloseness_centrality(arr[2]);
//					gf.setDegree_centrality(arr[3]);
//					gf.setRich_club_coefficient(arr[1]);
//					gf.setDegree_assortativity_coefficient(arr[5]);
//					gf.setEigenvectorCentrality(arr[6]);
//					gf.setAverageDegree(arr[7]);
//					gf.setDensity(arr[8]);
//					gf.setPathLength(arr[9]);
//					gf.setModularity(arr[10]);
//					gf.setWheightedDegree(arr[11]);
//					gf.setAverageClusteringCoefficient(arr[12]);
//					//System.out.println(gf.toString());
					normallist.add(gf);
				//	}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0,c8=0,c9=0,c10=0;
		for(GFeature gf:spamlist){
			//double temp=Double.valueOf(gf.getWheightedDegree().trim());
			//double temp=Double.valueOf(gf.getAverageDegree().trim());
			//double temp=Double.valueOf(gf.getDensity().trim());
			//double temp=Double.valueOf(gf.getModularity().trim());
			//double temp=Double.valueOf(gf.getDegree_centrality().trim());
			////double temp=Double.valueOf(gf.getRich_club_coefficient().trim());
			//double temp=Double.valueOf(gf.getEigenvectorCentrality().trim());
			//double temp=Double.valueOf(gf.getBetweenness_centrality().trim());
//			double temp=Double.valueOf(gf.getCloseness_centrality().trim());
			double temp=Double.valueOf(gf.getRich_club_coefficient().trim());
			if(temp<0.1)
				c1++;
			else if(temp<0.2)
				c2++;
			else if(temp<0.3)
				c3++;
			else if(temp<0.4)
				c4++;
			else if(temp<0.5)
				c5++;
			else if(temp<0.6)
				c6++;
			else if(temp<0.7)
				c7++;
			else if(temp<0.8)
				c8++;
			else if(temp<0.9)
					c9++;
			else if(temp<=1)
				c10++;
		}
		System.out.println(c1+" "+c2+" "+c3+" "+c4+" "+c5+" "+c6+" "+c7+" "+c8+" "+c9+" "+c10);
		int d1=0,d2=0,d3=0,d4=0,d5=0,d6=0,d7=0,d8=0,d9=0,d10=0;
		for(GFeature gf:normallist){
			//double temp=Double.valueOf(gf.getWheightedDegree().trim());
			//double temp=Double.valueOf(gf.getAverageDegree().trim());
			//double temp=Double.valueOf(gf.getDensity().trim());
			//double temp=Double.valueOf(gf.getModularity().trim());
			//double temp=Double.valueOf(gf.getDegree_centrality().trim());
			////double temp=Double.valueOf(gf.getRich_club_coefficient().trim());
			//double temp=Double.valueOf(gf.getEigenvectorCentrality().trim());
			//double temp=Double.valueOf(gf.getBetweenness_centrality().trim());
			double temp=Double.valueOf(gf.getCloseness_centrality().trim());
			if(temp<0.1)
				d1++;
			else if(temp<0.2)
				d2++;
			else if(temp<0.3)
				d3++;
			else if(temp<0.4)
				d4++;
			else if(temp<0.5)
				d5++;
			else if(temp<0.6)
				d6++;
			else if(temp<0.7)
				d7++;
			else if(temp<0.8)
				d8++;
			else if(temp<0.9)
					c9++;
			else if(temp<=1)
				d10++;
		}
		System.out.println(d1+" "+d2+" "+d3+" "+d4+" "+d5+" "+d6+" "+d7+" "+d8+" "+d9+" "+d10);
	}

}
