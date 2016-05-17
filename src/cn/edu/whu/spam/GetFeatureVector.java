/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.pojo.FeatureVector;
import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *合并之前算出的各种向量。得到用户的特征向量。
 */
public class GetFeatureVector {
	private static final String  BEHAVIOR_FEATURES_SELECTED_FILE_PATH="E:/spam/4_extractFetures/behaviorFeatures3.txt";
	private static final String  BEHAVIOR_FEATURES_NORMAL_FILE_PATH="E:/normal/3_extractFetures/behaviorFeatures3.txt";
	private static final String  ATTENTIONRATE_SELECTED_FILE_PATH="E:/spam/4_extractFetures/attentionRate3.txt";
	private static final String  ATTENTIONRATE_NORMAL_FILE_PATH="E:/normal/3_extractFetures/attentionRate3.txt";
	private static final String  PROFILETRAIT_SELECTED_PATH="E:/spam/4_extractFetures/profilesTrait3.txt";
	private static final String  PROFILETRAIT_NORMAL_PATH="E:/normal/3_extractFetures/profilesTrait3.txt";
	private static final String TRIREALTION_SELECTED_PATH="E:/spam/4_extractFetures/tri_uidFriends3.txt";
	private static final String TRIREALTION_NORMAL_PATH="E:/normal/3_extractFetures/tri_uidFriends3.txt";
	static Map<String,FeatureVector> result=new HashMap<String ,FeatureVector>();
    static Map<String,String> saveMap=new HashMap<>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//得到select部分的特征向量
		initRelationFeature(ATTENTIONRATE_SELECTED_FILE_PATH,PROFILETRAIT_SELECTED_PATH,TRIREALTION_SELECTED_PATH);
		initBehaviorFeatures(BEHAVIOR_FEATURES_SELECTED_FILE_PATH);
		//standardizeIntervalRate();
		display();
		save("E:/spam/5_selectedFeatureVec/selectVec3.txt");
		//得到normal
		result.clear();
		saveMap.clear();
		initRelationFeature(ATTENTIONRATE_NORMAL_FILE_PATH,PROFILETRAIT_NORMAL_PATH,TRIREALTION_NORMAL_PATH);
		initBehaviorFeatures(BEHAVIOR_FEATURES_NORMAL_FILE_PATH);
		//standardizeIntervalRate();
		//display();
		save("E:/normal/5_selectedFeatureVec/selectVec3.txt");
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
						    	   fv.setTimeItvalLess5(arr[8]);
						    	   fv.setTimeItvalLess10(arr[9]);
						    	   fv.setTimeItvalLess30(arr[10]);
						    	   fv.setTimeItvalLess60(arr[11]);
						    	   fv.setTimeItvalLess1440(arr[12]);
						    	   fv.setTimeItvalLessmore(arr[13]);
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
	public static  void display(){
		for(String key:result.keySet()){
			System.out.println( result.get(key).toString());
		}
	}
	public static void save(String path ){
		Utils utils=new Utils();
		for(String key:result.keySet()){
			saveMap.put(key,result.get(key).toString());
		}
		utils.saveMap(saveMap, path);
	}

}
