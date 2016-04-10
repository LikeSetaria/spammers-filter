/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.utils.Utils;
/**
 * @author bczhang
 *
 */
public class Bigram {
	//微博用户名文件
	private static String USER_NAME_FILE_PATH="D:/Whuer/Major/weibo/RESULT/weibo_users_name.txt";
	//未排序 Bigram结果保存位置
	private static String USER_NAME_BIGRAM_RESULTS="E:\\temp\\result\\BigramReaultsTestUserName.txt";
	private static String USER_NAME_TriGRAM_RESULTS="E:\\temp\\result\\TrigramReaults.txt";
	//保存所有用户名的Ngram切割后的词，方便后面比较  makeNgramsByLine()
	static HashMap<String,Integer > gramMap=new HashMap<String,Integer>();
	//保存每一个用户名到map，方便后面计算用户在总的相似度    makeNgramsByLine()
	 static HashMap<String ,Integer> userNameMap=new HashMap<String,Integer>();
	//保存最后的用户及出现的出现的频度，不采用HashMap,因为后面要对其排序，TreeMap对排序效率更高  makeNgramsByLine()
	static  Map<String,Integer> userNameNgramResults=new TreeMap<String,Integer>();
	//makeNgramsByLine2 中用来保存最后结果，value使用String 可以根据需要进行ValueOf得到相应的数据类型
	static Map<String ,Double> userNameNgramResults2=new HashMap<String ,Double>();
	//统计每个词出现的次数
	static Map<String ,Integer> wordMap=new HashMap<String,Integer>();
	//打开用户明文件
	static Map<String,Double> newMap = new HashMap<String,Double>();  
	public static void main(String[] args){
		Utils utils=new Utils();
		 
		//Bigram对用户名进行划分并统计次数
//		makeNgramsByLine(USER_NAME_FILE_PATH,2);	 
// 		saveResultByHashMap(utils.sortMapByValue(userNameNgramResults),USER_NAME_BIGRAM_RESULTS);
		

 		//Trigram对用户名进行划分并统计次数
// 		makeNgramsByLine(USER_NAME_FILE_PATH,3);
// 		saveResultByHashMap(utils.sortMapByValue(userNameNgramResults),USER_NAME_TriGRAM_RESULTS);
		 //测试makeNgramsByLine2
		makeNgramByLine2(USER_NAME_FILE_PATH,2);
 	// utils.sortMapByValue2(userNameNgramResults2);
//		
// 		 
		 
		 saveResultByHashMap(utils.sortMapByValue2(userNameNgramResults2),USER_NAME_BIGRAM_RESULTS);
// 		for (Map.Entry<String, Double> entry : userNameNgramResults2.entrySet()) {
// 		 System.out.println(  entry.getKey() + "  " + entry.getValue());
// 		}
// 		 
		  
	}
    public  static HashMap<String,Integer> makeNgrams(String  text,int nGramSize) {
    	HashMap<String,Integer > gramMap=new HashMap<String,Integer>();
    	String[] str=formGrams(text,nGramSize);
    	 
    	for(String s:str){
    		Integer cou;
    		if(s.matches("^[a-zA-Z0-9\u4e00-\u9fa5]+$")){
    		cou=gramMap.get(s);
    		 gramMap.put(s, cou == null?1:cou+1);
    		}
    		
    	}
    	return   gramMap;
    	
}
    /**
     * 对makeNgrams方法进行扩展，以每行进行Ngram 切分统计
     * @param USER_NAME_FILE_PATH 待切分的文件
     * @param nGramSize
     * @return
     */
    public  static void makeNgramsByLine(String  USER_NAME_FILE_PATH,int nGramSize) {
    	//保存所有用户名的Ngram切割后的词，方便后面比较
    	//HashMap<String,Integer > gramMap=new HashMap<String,Integer>();
    	//保存每一个用户名到map，方便后面计算用户在总的相似度  
    	//Map<String ,Integer> userNameMap=new HashMap<String,Integer>();
    	//保存最后的用户及出现的出现的频度，不采用HashMap,因为后面要对其排序，TreeMap对排序效率更高
    	//Map<String,Integer> userNameNgramResults=new TreeMap<String,Integer>();
    	 
    	//打开用户明文件
    	File file=new File(USER_NAME_FILE_PATH);
    	//利用Apache common 包中的封装的工具，初始化一个遍历器，逐行遍历文件
    	LineIterator it=null;
    	//ͳ�������û���Ngram������Ĵ���
    	int totalGram=0;
    	
    	//统计所有用户明Ngram后产生的词数
    	try {
			it=FileUtils.lineIterator(file, "UTF-8");
			//逐行遍历文件，并进行Ngram处理，Ngram后的词组放到gramMaP中
			while(it.hasNext()){
				String line=it.next();
				//同时保存每一行到一个Map中，方便后面操作
				userNameMap.put(line, 1);
				//对每一个用户明进行Ngram 放到一个字符数组中，逐个对其比较次数
				String[] str=formGrams(line,nGramSize);
				for(String s:str){
		    		Integer cou;
		    		//过滤出中英文数字外的特殊字符
		    		//if(s.matches("^[a-zA-Z0-9\u4e00-\u9fa5]+$")){
		    		//过滤特殊字符，英文字符，只有数字和中文的词才加入map
		    		// if(s.matches("^[0-9\u4e00-\u9fa5]+$")){
		    		//匹配中英文及数字
		    		//if(s.matches("^[\u4e00-\u9fa5_a-zA-Z0-9]+$")){	 
		    		 cou=gramMap.get(s);
		    		 gramMap.put(s, cou == null?1:cou+1);
		    		 totalGram++;
		    //
		    		 //	 }
		    		
		    	}
				}
			Iterator iter=userNameMap.entrySet().iterator();
			while(iter.hasNext()){
				int keyCount=0;
				Map.Entry entry=(Map.Entry) iter.next();
				Object key=entry.getKey();
				//String oneUserName=it.next();
				//得到一个用户名中的所有的Ngram，
				String[] userNameArr=formGrams(key.toString(),nGramSize);
				for(String ss:userNameArr){
					//如用户名：小神万里,Bigram得到：小神、神万、万里，则拿得到Bigram去所有的用户明中去匹配统计出现词数
					if(gramMap.containsKey(ss))
					{
						keyCount=keyCount+gramMap.get(ss);
					}
					 
				}
				userNameNgramResults.put(key.toString(), keyCount);
				
				//System.out.println("用户名为："+key.toString()+"    相应的频度为："+keyCount);
			}
			//System.out.println(userNameNgramResults);
			System.out.println("总的分词数为"+totalGram);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return    ;
    	
}
    /*上一个方法只算频度，是不对的 
     * 这个方法对其完善计算正确的Ngram
     * 
     * 
     */
    public static void makeNgramByLine2(String  USER_NAME_FILE_PATH,int nGramSize){
     
    	//打开用户明文件
    	File file=new File(USER_NAME_FILE_PATH);
    	//利用Apache common 包中的封装的工具，初始化一个遍历器，逐行遍历文件
    	LineIterator it=null;  
    	int totalGram=0; 
    	//统计所有用户明Ngram后产生的词数
    	 
    	try {
			it=FileUtils.lineIterator(file, "UTF-8");
			//逐行遍历文件，并进行Ngram处理，Ngram后的词组放到gramMaP中
			while(it.hasNext()){
				String line=it.next();
				//同时保存每一行到一个Map中，方便后面操作
				userNameMap.put(line, 1);
				//对每一个用户明进行Ngram 放到一个字符数组中，逐个对其比较次数
				String[] str=formGrams(line,nGramSize);
				String[] strWord=formGrams(line,1);
				if(str.length>3){
				for(String s:str){
		    		Integer cou; 
		    		 cou=gramMap.get(s);
		    		 gramMap.put(s, cou == null?1:cou+1);
		    		 totalGram++;	
		    	}
				}
				//统计每个字出现的次数，放入到wordMap中
				for(String ss:strWord){
					Integer k;
					k=wordMap.get(ss); 
					wordMap.put(ss, k==null?1:k+1);
					
				}
				}
			Iterator iter=userNameMap.entrySet().iterator();
			while(iter.hasNext()){
				//w1、w2保存前后词的频度，根据他们算概率
				int w1,w2=0;
				//用以存储每个用户名算出的 概率
				Double temp=0.00;
				Map.Entry entry=(Map.Entry) iter.next();
				Object key=entry.getKey();
				//String oneUserName=it.next();
				//得到一个用户名中的所有的Ngram，
				String[] userNameArr=formGrams(key.toString(),nGramSize);
				//System.out.println(key.toString());
				if(userNameArr.length>3){
				for(int i=0;i<userNameArr.length-1;i++){
					//如用户名：小神万里,Bigram得到：小神、神万、万里，则拿得到Bigram去所有的用户明中去匹配统计出现词数
					if(gramMap.containsKey(userNameArr[i]))
					{
						 
						w1=gramMap.get(userNameArr[i]);
						//System.out.println("w1分词为：    "+userNameArr[i]+ "  "+w1);
						 
						 w2=wordMap.get(userNameArr[i].substring(0, 1));
						//System.out.println("w2分词为：    "+userNameArr[i].substring(0, 1).charAt(0) +"  "+w2 );
						if(w2!=0)
						temp=temp+(double)w1/(double)w2;
						else System.out.println("出现分母为零的情况 ");
					}
					 
				}
				 //BigDecimal bg = new BigDecimal(temp);
		           // double f1 = bg.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
				userNameNgramResults2.put(key.toString(), temp );
				}
				
				//System.out.println("用户名为："+key.toString()+"    相应的频度为："+keyCount);
			}
			//System.out.println(userNameNgramResults);
			System.out.println("总的分词数为"+totalGram);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return    ;
    }
    /**
     * Bigram �и��
     * @param text
     * @param ng
     * @return
     */
     private static String[] formGrams(String text,int ng){
    	 //对字符串进行切分处理时，对于重复的特殊字符合并看做一个来处理，降低它们出现的概率
    	
    	//对于非中文、英文、数字外的特殊字符进行过滤替换，看做一个整体统计其出现次数
    	 // text=text.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]+", "%");
    	 text="%"+text;
    	 int len=text.length();
    	 
    	 String[] res=null;
  	  if(len-ng+1>0){
    	 
    	 res=new String[len-ng+1];   
    	  
    	 for(int i=0;i<len-ng+1;i++){
    		  	res[i]=text.substring(i,i+ng);		  	
    	 }
    	 
    	 }
    	  else {
    		  res=new String[1];
    	      res[0]=text;
    	  }
    	 //  System.out.println(res[0]);
    	 return res;
    	 
     }    
     public static void saveResultByHashMap( Map<String,Double> map,String saveFilePath){
    	 File file=new File(saveFilePath);
      	 StringBuffer str=new StringBuffer();
      	 int cou=0;
         Iterator<?> it =map.entrySet().iterator();
         //对map中的内容进行格式化处理
         while(it.hasNext()){
        	 cou++;
        	 @SuppressWarnings("rawtypes")
			Map.Entry entry=(Map.Entry) it.next();
        	 Object key=entry.getKey();
        	 str.append(key.toString()+" ");
        	 Object val=entry.getValue();
        	 str.append(val.toString()+"  ");
        	 //if(cou%30==0)
        		 str.append("\n");
        	
         }
         try {
			FileUtils.writeStringToFile(file, str.toString(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // System.out.println(str.toString());
     }
     
     
     /**
      * 对HashMap根据value进行排序
      * @return HashMap
      * @param HashMap
      */
     public  static   Map<String,Double> sortMapByValue2(Map<String,Double> map ){
    	 
    	  List<Map.Entry<String ,Double>> list=new ArrayList<Map.Entry<String,Double>>(map.entrySet());
    	 Collections.sort(list,new Comparator<Map.Entry<String ,Double>>(){    		 
			@Override
			public int compare(java.util.Map.Entry<String, Double> arg0, java.util.Map.Entry<String, Double> arg1) {
				double result = arg0.getValue() - arg1.getValue();
			      if(result > 0)
			       return 1;
			      else if(result == 0)
			       return 0;
			      else 
			       return -1;
			}    		 
    	 });
    	     
            for (int i = 0; i < list.size(); i++) {  
                newMap.put(list.get(i).getKey(), list.get(i).getValue());
                //System.out.println(list.get(i).getKey()+"   "+list.get(i).getValue());
            }  
            return newMap; 
     }
}
