/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

/**
 * @author bczhang
 *格式化生成器，把特征向量生成各种格式化文件，以便使用各种分类器
 */
public class FormatGenerators {

	public static void main(String[] args) throws IOException{
		FormatGenerators fg=new FormatGenerators();
		            
		/*
		 * 结构化特征
		 */
		String[] attribute_graph={"Betweenness_centrality","Closeness_centrality","degree_centrality",
				"rich_club_coefficient","degree_assortativity_coefficient","Centrality","AverageDegree",
				"Density","PathLength","Modularity","wheightedDegree","AverageClusteringCoefficient"};
		/*
		 * baseline特征
		 */
		String[] attribute_baseline={"attentionRate","commentRate","repostRate","atRate","topicRate","textURLrate","weiboFromRepostRate",
				"timeItvalLess0","timeItvalLess2","timeItvalLess5","timeItvalLess10","timeItvalLess20","timeItvalLess30","timeItvalLess60",
				"timeItvalLess120","timeItvalLess300","timeItvalLess1440","timeItvalLessmore","meanInterval","triRealtion","profileURL",
				"ifProfile","frinedsNum","followsNum",
				"friendsNumDivfollowsNum","userNameLen","profileLen","weiboAge","wbSourceRichnessK","wbSourceRichnessD","wbSourceRichnessH",
				"wbSourceRichnessS"};
		String attributeType="real";
		String info_network="networkFeatures";
		String info_baseline="baselineFeatures";
		fg.generateARFF("E:/weka/baseline/2000_WEKA_baseline.txt","E:/weka/baseline/2000_WEKA_baseline.arff",
				attribute_baseline,attributeType,info_baseline);
	}
	/**
	 * 
	 * @param vecPath 特征向量 格式：uid vec1 vec2 vec3../
	 * @param savePath 生成的ARFF格式文件保存的路径
	 * @param attribute 特征名称字符串数组0
	 * @param attributeType 特征的数据类型
	 * @throws IOException
	 */
	public void generateARFF(String vecPath,String savePath,String attribute[],String attributeType,String arffinfo) throws IOException{
		String vecArr[]=FileUtils.readFileToString(new File(vecPath)).split("\n");
		Map<String,String> sampleClassMap=new HashMap<>();
		sampleClassMap=getSampleClass("E:/weka/1000spamUid.txt","E:/weka/5443normalUid.txt");
		StringBuilder result=new StringBuilder();
		result.append(appendAttribute(attribute,attributeType,arffinfo));
		int attributeLen=attribute.length+1;
		for(String line:vecArr){
			String arr[]=line.trim().split(" +");
			if(arr.length!=attributeLen)System.out.println(line);
            String sampleKey=arr[0];
            //arr[0]=arr[arr.length-1];
            if(sampleClassMap.get(sampleKey)==null){
            //arr[arr.length-1]=sampleClassMap.get(sampleKey);
            	System.out.println("样本类别，映射错误，请检查");
            } //else System.out.println("样本类别，映射错误，请检查");
            for(int i=1;i<arr.length;i++){
            	result.append(arr[i]);
            	//if(i!=arr.length-1)
            	result.append(",");
            }
            result.append(sampleClassMap.get(sampleKey));
            result.append("\n");
		}
		//System.out.println(result);
		FileUtils.write(new File(savePath), result);
	}
	/*
	 * 正负样本类别映射
	 */
	public static  Map<String,String> getSampleClass(String Class_1_Path,String Class_2_Path) throws IOException{
		Map<String,String> sampleClassMap=new HashMap<>();
		String classArr1[]=FileUtils.readFileToString(new File(Class_1_Path)).split("\n");
		String classArr2[]=FileUtils.readFileToString(new File(Class_2_Path)).split("\n");
		for(String ss:classArr1){
			sampleClassMap.put(ss.trim(), "negitive");
		}
		for(String ss:classArr2){
			sampleClassMap.put(ss.trim(), "positive");
		}
		return sampleClassMap;
	}
	/*
	 * 订制属性及title信息
	 * weka中数据类型，有四种分别是：numeric、<nominal-specification> 、string、date 
	 */
	public static String appendAttribute(String attributeArr[],String attributeType ,String ARFFInfo ){
	      StringBuilder strb=new StringBuilder();
	      strb.append("@relation "+ARFFInfo);
	      strb.append("\n");
	      for(String ss:attributeArr){
	    	  strb.append("@attribute '"+ss+"' "+attributeType);
	    	  strb.append("\n");
	      }
	      strb.append("@attribute 'Class' {positive,negitive}");
	      strb.append("\n");
	      strb.append("@data");
	      strb.append("\n");
				return strb.toString();
	}
}
