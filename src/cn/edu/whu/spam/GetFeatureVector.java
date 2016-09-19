/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.pojo.FeatureVector;
import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *合并之前算出的各种向量。得到用户的特征向量。
 */
public class GetFeatureVector {
//	private static final String  BEHAVIOR_FEATURES_SELECTED_FILE_PATH="E:/spam/4_extractFetures/behaviorFeatures9.txt";
//	private static final String  BEHAVIOR_FEATURES_NORMAL_FILE_PATH="E:/normal/3_extractFetures/behaviorFeatures9.txt";
//	private static final String  ATTENTIONRATE_SELECTED_FILE_PATH="E:/spam/4_extractFetures/attentionRate6.txt";
//	private static final String  ATTENTIONRATE_NORMAL_FILE_PATH="E:/normal/3_extractFetures/attentionRate6.txt";
//	private static final String  PROFILETRAIT_SELECTED_PATH="E:/spam/4_extractFetures/profilesTrait6.txt";
//	private static final String  PROFILETRAIT_NORMAL_PATH="E:/normal/3_extractFetures/profilesTrait6.txt";
//	private static final String TRIREALTION_SELECTED_PATH="E:/spam/4_extractFetures/tri_uidFriends6.txt";
//  private static final String TRIREALTION_NORMAL_PATH="E:/normal/3_extractFetures/tri_uidFriends6.txt";
//	private static final String  BEHAVIOR_FEATURES_SELECTED_FILE_PATH="E:/spam/spamSample/features/behaviorFeatures.txt";
//	private static final String  BEHAVIOR_FEATURES_NORMAL_FILE_PATH="E:/normal/normalSample/features/behaviorFeatures.txt";
//	private static final String  ATTENTIONRATE_SELECTED_FILE_PATH="E:/spam/spamSample/features/attentionRate.txt";
//	private static final String  ATTENTIONRATE_NORMAL_FILE_PATH="E:/normal/normalSample/features/attentionRate.txt";
//	private static final String  PROFILETRAIT_SELECTED_PATH="E:/spam/spamSample/features/profilesTrait.txt";
//	private static final String  PROFILETRAIT_NORMAL_PATH="E:/normal/normalSample/features/profilesTrait.txt";
//	private static final String TRIREALTION_SELECTED_PATH="E:/spam/spamSample/features/tri_uidFriends.txt";
//	private static final String TRIREALTION_NORMAL_PATH="E:/normal/normalSample/features/tri_uidFriends.txt";
//	private static final String NORMAL_GRAPH_METRICE_PATH="E:/normal/normalSample/features/graph_metric_follows359.txt";
//	private static final String SPAM_GRAPH_METRICE_PATH="E:/spam/spamSample/features/graph_metric_follows341.txt";
	private static final String  BEHAVIOR_FEATURES_NORMAL_FILE_PATH="E:/spam/temp/behaviorFeatures9.txt";
	private static final String  ATTENTIONRATE_NORMAL_FILE_PATH="E:/spam/temp/attentionRate6.txt";
	private static final String  PROFILETRAIT_NORMAL_PATH="E:/spam/temp/profilesTrait6.txt";
  private static final String TRIREALTION_NORMAL_PATH="E:/spam/temp/tri_uidFriends6.txt";
	static Map<String,FeatureVector> result=new HashMap<String ,FeatureVector>();
    static Map<String,String> saveMap=new HashMap<>();
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//得到select部分的特征向量
		initRelationFeature(ATTENTIONRATE_NORMAL_FILE_PATH,PROFILETRAIT_NORMAL_PATH,TRIREALTION_NORMAL_PATH);
		initBehaviorFeatures(BEHAVIOR_FEATURES_NORMAL_FILE_PATH);
		//initGraphFeature(SPAM_GRAPH_METRICE_PATH);
		//standardizeIntervalRate();已经更改时间间隔特征选取方式，不再需要再次进行规范化
		//display();
		//removeNomalUID("E:/spam/5_selectedFeatureVec/spamRemoveNormalUID.txt");//去除1984；余10874-1984=8890条
		//save("E:/spam/5_selectedFeatureVec/selectVec10.txt");
		//save("E:/spam/spamSample/featureVec/selectAllVec_Gfollows.txt");
		save("E:/spam/temp/spam_baseline.txt");
		//得到normal
		
//		result.clear();
//		saveMap.clear();
//		
//		initRelationFeature(ATTENTIONRATE_NORMAL_FILE_PATH,PROFILETRAIT_NORMAL_PATH,TRIREALTION_NORMAL_PATH);
//		initBehaviorFeatures(BEHAVIOR_FEATURES_NORMAL_FILE_PATH);
//		initGraphFeature(NORMAL_GRAPH_METRICE_PATH);
//		//removeNomalUID("E:/normal/5_selectedFeatureVec/PunishRemovePartUID.txt");//正类为了平衡，也得去除一些，正类10207;所以得剪去1317
//		//standardizeIntervalRate();
//		//display();
//		//save("E:/normal/5_selectedFeatureVec/selectVec10.txt");
//		save("E:/normal/normalSample/featureVec/selectAllVec_Gfollows.txt");
	}
	/**
	 * 初始化用户图特征
	 */
	public static void initGraphFeature(String graphMetricePath){
		File file=new File(graphMetricePath);
		LineIterator lineit=null;
		try {
			lineit=FileUtils.lineIterator(file);
			FeatureVector fv=new FeatureVector();
			while(lineit.hasNext()){
		       String line=lineit.nextLine();
		       String[] arr=line.split(" ");
		       if(result.containsKey(arr[0])){
		    	   fv=result.get(arr[0]);
		    	   fv.setGraphAverageDegree(arr[1]);
		    	   fv.setGraphCentrality(arr[2]);
		    	   fv.setGraphDensity(arr[3]);
		    	   fv.setGrapheightedDegree(arr[4]);
		    	   fv.setGraphModularity(arr[5]);
		    	   fv.setGraphPathLength(arr[6]);
		       }
		       result.put(arr[0], fv);
		       
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			   LineIterator.closeQuietly(lineit);
		}
	} 
	/**
	 * 初始化用户关系特征，主要包括关注度等
	 * 关注度文件为：uid 关注度
	 * @param path
	 */
	public static void initRelationFeature(String attentionRatepath,String profilesTraitPath,String triPath){
		File	 file= new File(attentionRatepath);
		File  profilesTraitFile=new File(profilesTraitPath);
		File trifile=new File(triPath);
		   LineIterator lineit=null;
		   LineIterator it=null;
		   LineIterator triit=null;
		   //需要对这个几个特征进行规范化处理,在这里处理规范化不合适，需要对最后的特征向量做个规范化程序，对其规范化，不然让添加特征时要频繁的修改
		   		     try {	
						 lineit=FileUtils.lineIterator(file);
						 it=FileUtils.lineIterator(profilesTraitFile);
						 triit=FileUtils.lineIterator(trifile);
						 StringBuilder strb=null;
							while(lineit.hasNext()){
						       String line=lineit.nextLine();
						       String[] arr=line.split(" ");
						       FeatureVector fv=new FeatureVector();
						       fv.setUid(arr[0]);
						       fv.setFollowsNum(arr[1]);
						       fv.setFrinedsNum(arr[2]);
						       fv.setFriendsNumDivfollowsNum(arr[3]);
						       fv.setAttentionRate(arr[4]);
						       result.put(arr[0], fv);
						       
							}
							//
							FeatureVector vec=new FeatureVector();
							while(it.hasNext()){
								 String line=it.nextLine();
							     String[] arr=line.split(" ");
								 vec=result.get(arr[0]);
								 vec.setProfileURL(arr[1]);
								 vec.setIfProfile(arr[2]); 
								 vec.setProfileLen(arr[3]);
								 vec.setUserNameLen(arr[4]);
								 result.put(arr[0], vec);
							}
							FeatureVector tri=new FeatureVector();
							while(triit.hasNext()){
								 String line=triit.nextLine();
							     String[] arr=line.split(" ");
								 tri=result.get(arr[0]);
								 tri.setTriRealtion(arr[1]);
								 result.put(arr[0], tri);
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							   LineIterator.closeQuietly(lineit);
							   LineIterator.closeQuietly(it);
							   LineIterator.closeQuietly(triit);
						}
		
	}
	/**
	 * uid+commentRate+repostRate+atRate+topicRate+URLrate+weiborepostRate+weibosimilarity+intervalRate
	 * @param path
	 */
	public static void initBehaviorFeatures(String path){
		File	 file= new File(path);
		   LineIterator lineit=null;
		   		     try {	
						 lineit=FileUtils.lineIterator(file);
						 FeatureVector fv=new FeatureVector();
							while(lineit.hasNext()){
						       String line=lineit.nextLine();
						       String[] arr=line.split(" ");
						       if(result.containsKey(arr[0])){
						    	   fv=result.get(arr[0]);
						    	   fv.setCommentRate(arr[1]);
						    	   fv.setRepostRate(arr[2]);
						    	   fv.setAtRate(arr[3]);
						    	   fv.setTopicRate(arr[4]);
						    	   fv.setTextURLrate(arr[5]);
						    	   fv.setWeiboFromRepostRate(arr[6]);
						    	   fv.setWeiboTextSimilarity(arr[7]);
						    	   //fv.setIntervalRate(arr[8]);
						    	   fv.setTimeItvalLess0(arr[8]);
						    	   fv.setTimeItvalLess2(arr[9]);
						    	   fv.setTimeItvalLess5(arr[10]);
						    	   fv.setTimeItvalLess10(arr[11]);
						    	   fv.setTimeItvalLess20(arr[12]);
						    	   fv.setTimeItvalLess30(arr[13]);
						    	   fv.setTimeItvalLess60(arr[14]);
						    	   fv.setTimeItvalLess120(arr[15]);
						    	   fv.setTimeItvalLess300(arr[16]);
						    	   fv.setTimeItvalLess1440(arr[17]);
						    	   fv.setTimeItvalLessmore(arr[18]);
						    	   fv.setMeanInterval(arr[19]);
						    	   fv.setWeiboAge(arr[20]);
						    	   fv.setWbSourceRichnessK(arr[21]);
						    	   fv.setWbSourceRichnessD(arr[22]);
						    	   fv.setWbSourceRichnessH(arr[23]);
						    	   fv.setWbSourceRichnessS(arr[24]);
						    	   
						       }else{
						    	   System.out.println(line);
						       }
						       result.put(arr[0], fv);
						       
						     //  saveMap.put(arr[0], fv.toString());
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							
							   LineIterator.closeQuietly(lineit);
						}
	}
	/**
	 * 规范化，主要是对特征向量中的一些不规范的量进行规范化，这里提供一种规范化方式：处理这一维中的最大值。下面是其实现
	 * 输入是：Map<String,FeatureVector> ,参数是对那一维
	 * 
	 */
	public static void standardizeIntervalRate(){
		double max=0.0;
		double temp=0.0;
		DecimalFormat   df=new   java.text.DecimalFormat("#.######"); 
		for(String key:result.keySet()){
			System.out.println(key+result.get(key).getIntervalRate());
			temp=Double.valueOf(result.get(key).getIntervalRate().trim());
			
			if(temp>max)
				max=temp;
		}
		System.out.println("  这一维上的最大值是"+max);
		FeatureVector fv=new FeatureVector();
		for(String key:result.keySet()){
			fv=result.get(key);
			//System.out.println("规范化前的值"+fv.getIntervalRate());
			double d=Double.valueOf(fv.getIntervalRate())/max;
			fv.setIntervalRate(df.format(d));
			//System.out.println("规范化后的值"+fv.getIntervalRate());
			result.put(key, fv);
		}
	}
	public static  void display(Map<String,FeatureVector> map){
		for(String key:map.keySet()){
			System.out.println( map.get(key).toString());
		}
	}
	public static void save(String path ){
		Utils utils=new Utils();
		int count=0;
		for(String key:result.keySet()){
			count++;
			saveMap.put(key,result.get(key).toString());
		}
		utils.saveMap(saveMap, path);
		System.out.println(count);
	}
	/**
     * 根据一个文件中的uid，删除另一个文件中包含那些uid的条目。 
     * 此处过滤结果，删除负类样本中可能是正类的部分
     *输入文件需要过滤的uid列表。的格式是： uid(key)　
     *                          uid
     *                          uid
     *输入文件二，待过滤的特征向量是：uid(key) 特征1 特征2 特征3 。。。 
     */
    public static void removeNomalUID(String uidFilePath){
    	 
    	Utils utils=new Utils();
    	Set<String> uidSet=new HashSet<String>();
    	uidSet=utils.readToSet2(uidFilePath);
    	int count=0;
    	for(String key:uidSet){
    		if(result.containsKey(key)){
    			result.remove(key);
    			count++;
    		}	
    	}
    	 System.out.println("spam中去除可能正类样本数"+count);
    }
    /**
     * 对HashMap根据value进行排序
     * @return HashMap
     * @param HashMap
     */
    public static  Map<String,FeatureVector> sort(){
   	  List<Map.Entry<String ,FeatureVector>> list=new ArrayList<Map.Entry<String,FeatureVector>>(result.entrySet());
   	 Collections.sort(list,new Comparator<Map.Entry<String ,FeatureVector>>(){    		 
			@Override
			public int compare(java.util.Map.Entry<String, FeatureVector> arg0, java.util.Map.Entry<String, FeatureVector> arg1) {
				return   arg0.getValue().compareTo(arg1.getValue());
				
			     
			}    		 
   	 });
   	     Map<String,FeatureVector> newMap = new LinkedHashMap<String,FeatureVector>();  
           for (int i = 0; i < list.size(); i++) {  
               newMap.put(list.get(i).getKey(), list.get(i).getValue());
               System.out.println(list.get(i).getKey()+"   "+list.get(i).getValue());
           }  
           return newMap; 
    }

}
