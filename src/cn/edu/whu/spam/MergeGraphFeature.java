/**
 * 
 */
package cn.edu.whu.spam;

import java.util.HashMap;
import java.util.Map;

import cn.edu.whu.pojo.GraphFeature;
import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *合并用户朋友，和粉丝的图向量为一个向量
 */
public class MergeGraphFeature {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mergeFeature();
	}
	public static void mergeFeature(){
		Utils utils=new Utils();
		Map<String,GraphFeature>result=new HashMap<String,GraphFeature>();
		//String savePath="E:/spam/3.1_graphFetures/graph_metric.txt";
		//String metric_friends_str=utils.readFileToString("E:/spam/3.1_graphFetures/graph_metric_friends.txt");
		//String metric_follows_str=utils.readFileToString("E:/spam/3.1_graphFetures/graph_metric_follows.txt");
		
		String savePath="E:/normal/3.1_graphFetures/graph_metric.txt";
		String metric_friends_str=utils.readFileToString("E:/normal/3.1_graphFetures/graph_metric_friends.txt");
		String metric_follows_str=utils.readFileToString("E:/normal/3.1_graphFetures/graph_metric_follows.txt");
		
		String[] metric_friends_arr=metric_friends_str.split("\n");
		String[] metric_follows_arr=metric_follows_str.split("\n");
		for(String line:metric_friends_arr){
			GraphFeature gf=new GraphFeature();
			String[] linearr=line.trim().split(" ");
				gf.setUid(linearr[0].trim());
			    gf.setFriend_Degree(linearr[1]);
			    gf.setFriend_EigenvectorCentrality(linearr[2]);
			    gf.setFriend_GraphDensit(linearr[3]);
			    gf.setFriend_GraphDistance(linearr[4]);
			    gf.setFriend_Modularity(linearr[5]);
			    gf.setFriend_WeightedDegree(linearr[6]);
			    result.put(linearr[0], gf);
			//System.out.println(gf.toString());
		}
		for(String line:metric_follows_arr){
			
			GraphFeature gf=new GraphFeature();
			String[] linearr=line.trim().split(" ");
			String uid=linearr[0].trim();
			if(result.containsKey(uid)){
				gf=result.get(uid);
				gf.setUid(linearr[0]);
			    gf.setFollow_Degree(linearr[1]);
			    gf.setFollow_EigenvectorCentrality(linearr[2]);
			    gf.setFollow_GraphDensit(linearr[3]);
			    gf.setFollow_GraphDistance(linearr[4]);
			    gf.setFollow_Modularity(linearr[5]);
			    gf.setFollow_WeightedDegree(linearr[6]);
			    result.put(uid, gf);
			}
			//System.out.println(gf.toString());
		}
		StringBuilder resultStrb=new StringBuilder();
		for(String mm:result.keySet()){
			resultStrb.append(result.get(mm));
			resultStrb.append("\n");
			//System.out.println(result.get(mm));
		}
		utils.writeToFile(savePath, resultStrb.toString());
		//System.out.println(resultStrb);
		
	}

}
