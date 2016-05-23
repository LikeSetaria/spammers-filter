package cn.edu.whu.commonsAlgorithms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>Title:</p>
 * <p>Description: 余弦获取文章相似性
 * </p>
 * @createDate：2013-8-26
 * @author xq
 * @version 1.0
 */
public class CosineSimilarAlgorithm {

	/**
	 * 
	* @Title: cosSimilarityByFile
	* @Description: 获取两个文件相似性
	* @param @param firstFile
	* @param @param secondFile
	* @param @return    
	* @return Double   
	* @throws
	 */
	public static Double cosSimilarityByFile(String firstFile,String secondFile){
		try{
			Map<String, Map<String, Integer>> firstTfMap=TfIdfAlgorithm.wordSegCount(firstFile);
			Map<String, Map<String, Integer>> secondTfMap=TfIdfAlgorithm.wordSegCount(secondFile);
			if(firstTfMap==null || firstTfMap.size()==0){
				throw new IllegalArgumentException("firstFile not found or firstFile is empty! ");
			}
			if(secondTfMap==null || secondTfMap.size()==0){
				throw new IllegalArgumentException("secondFile not found or secondFile is empty! ");
			}
			Map<String,Integer> firstWords=firstTfMap.get(firstFile);
			Map<String,Integer> secondWords=secondTfMap.get(secondFile);
			if(firstWords.size()<secondWords.size()){
				Map<String, Integer> temp=firstWords;
				firstWords=secondWords;
				secondWords=temp;
			}
			return calculateCos((LinkedHashMap<String, Integer>)firstWords, (LinkedHashMap<String, Integer>)secondWords);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}
	
	/**
	 * 
	* @Title: cosSimilarityByString
	* @Description: 得到两个字符串的相似性
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	public Double cosSimilarityByString(String first,String second){
		try{
			Map<String, Integer> firstTfMap=TfIdfAlgorithm.segStr(first);
			Map<String, Integer> secondTfMap=TfIdfAlgorithm.segStr(second);
			if(firstTfMap.size()<secondTfMap.size()){
				Map<String, Integer> temp=firstTfMap;
				firstTfMap=secondTfMap;
				secondTfMap=temp;
			}
			return calculateCos((LinkedHashMap<String, Integer>)firstTfMap, (LinkedHashMap<String, Integer>)secondTfMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}

	/**
	 * 
	* @Title: calculateCos
	* @Description: 计算余弦相似性
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	private static Double calculateCos(LinkedHashMap<String, Integer> first,LinkedHashMap<String, Integer> second){
		
		List<Map.Entry<String, Integer>> firstList = new ArrayList<Map.Entry<String, Integer>>(first.entrySet());
		List<Map.Entry<String, Integer>> secondList = new ArrayList<Map.Entry<String, Integer>>(second.entrySet());
		//计算相似度  
        double vectorFirstModulo = 0.00;//向量1的模  
        double vectorSecondModulo = 0.00;//向量2的模  
        double vectorProduct = 0.00; //向量积  
        int secondSize=second.size();
		for(int i=0;i<firstList.size();i++){
			if(i<secondSize){
				vectorSecondModulo+=secondList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
				vectorProduct+=firstList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
			}
			vectorFirstModulo+=firstList.get(i).getValue().doubleValue()*firstList.get(i).getValue().doubleValue();
		}
	   return vectorProduct/(Math.sqrt(vectorFirstModulo)*Math.sqrt(vectorSecondModulo));
	}
	
	public static void main(String[] args){
		CosineSimilarAlgorithm cosSim=new CosineSimilarAlgorithm();
		Double result=cosSim.cosSimilarityByString("[群体围观]//@雷满天: 历任证监会主席没有从会里出来的，全是外调的，且证监会主席是正部级，姚刚不可能直接升职，资格不够 美的他，就算换人也不是他//@韩复龄: 姚刚：证监会继任主席的热门候选人！//@越子张郞: 姚刚：留下一堆烫手山芋我",
				"#姚刚问计：800新股发行如何去库存#“以前我在发行部的时候，最多的一年也就是300多家，现在排队发行企业数量太多，历史上从来没有过。”姚刚感叹道，“这些股票还是要发的，但问题的关键是如何发。”史上最严重的IPO“堰塞湖”该如何疏导，成为了姚刚所必须要面对的首要难题。 @投行老人 @投行新闻");
		System.out.println(result);
	}
}
