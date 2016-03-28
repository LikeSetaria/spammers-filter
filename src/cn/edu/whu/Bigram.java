/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
	//΢���û����ļ�
	private static String USER_NAME_FILE_PATH="D:/Whuer/Major/weibo/RESULT/weibo_users_name.txt";
	//δ���� Bigram�������λ��
	private static String USER_NAME_BIGRAM_RESULTS="E:\\temp\\result\\BigramReaults.txt";
	private static String USER_NAME_TriGRAM_RESULTS="E:\\temp\\result\\TrigramReaults.txt";
	//���������û�����Ngram�и��Ĵʣ��������Ƚ�  makeNgramsByLine()
	static HashMap<String,Integer > gramMap=new HashMap<String,Integer>();
	//����ÿһ���û�����map�������������û����ܵ����ƶ�    makeNgramsByLine()
	 static HashMap<String ,Integer> userNameMap=new HashMap<String,Integer>();
	 //���������û������ֵĳ��ֵ�Ƶ�ȣ�������HashMap,��Ϊ����Ҫ��������TreeMap������Ч�ʸ���  makeNgramsByLine()
	static  Map<String,Integer> userNameNgramResults=new TreeMap<String,Integer>();
 	//���û����ļ�
	public static void main(String[] args){
		Utils utils=new Utils();
		 
//		String str2="";
//		 try {
//			  str2=FileUtils.readFileToString(new File("E:\\temp\\data\\ngramTest.txt"), "utf-8");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String str="С���ְ�����";
		
		
		
		
 		// System.out.println(makeNgrams(",��,Щsfgds,sgdgsd,��Щ��Щ��Щ��Щ��Щ�����Ƿ�����΢������Ӫ����˧�ϸ�",2));
	//	File file =new File("D:\\DOWNLOAD\\BaiduYunDownload\\Major\\weibo\\RESULT\\weibo_users_name.txt");
//		String content="";
//		 try {
//			content=FileUtils.readFileToString(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.print( makeNgrams(content,2));
 		//saveResultByHashMap(makeNgramsByLine(USER_NAME_FILE_PATH,2),"");
		//Bigram���û������л��ֲ�ͳ�ƴ���
		makeNgramsByLine(USER_NAME_FILE_PATH,2);	 
 		saveResultByHashMap(utils.sortMapByValue(userNameNgramResults),USER_NAME_BIGRAM_RESULTS);
		
		//Trigram���û������л��ֲ�ͳ�ƴ���
// 		makeNgramsByLine(USER_NAME_FILE_PATH,3);
// 		saveResultByHashMap(utils.sortMapByValue(userNameNgramResults),USER_NAME_TriGRAM_RESULTS);
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
     * ��makeNgrams����������չ����ÿ�н���Ngram �з�ͳ��
     * @param USER_NAME_FILE_PATH ���зֵ��ļ�
     * @param nGramSize
     * @return
     */
    public  static void makeNgramsByLine(String  USER_NAME_FILE_PATH,int nGramSize) {
    	   //���������û�����Ngram�и��Ĵʣ��������Ƚ�
    	//HashMap<String,Integer > gramMap=new HashMap<String,Integer>();
    	   //����ÿһ���û�����map�������������û����ܵ����ƶ�  
    	//Map<String ,Integer> userNameMap=new HashMap<String,Integer>();
    	   //���������û������ֵĳ��ֵ�Ƶ�ȣ�������HashMap,��Ϊ����Ҫ��������TreeMap������Ч�ʸ���
    	//Map<String,Integer> userNameNgramResults=new TreeMap<String,Integer>();
    	 
     	//���û����ļ�
    	File file=new File(USER_NAME_FILE_PATH);
    	//����Apache common ���еķ�װ�Ĺ��ߣ���ʼ��һ�������������б����ļ�
    	LineIterator it=null;
    	//ͳ�������û���Ngram������Ĵ���
    	int totalGram=0;
    	
    	
    	try {
			it=FileUtils.lineIterator(file, "UTF-8");
			//���б����ļ���������Ngram����Ngram��Ĵ���ŵ�gramMaP��
			while(it.hasNext()){
				String line=it.next();
				//ͬʱ����ÿһ�е�һ��Map�У�����������
				userNameMap.put(line, 1);
				//��ÿһ���û�������Ngram �ŵ�һ���ַ������У��������Ƚϴ���
				String[] str=formGrams(line,nGramSize);
				for(String s:str){
		    		Integer cou;
		    		//���˳���Ӣ��������������ַ�
		    		//if(s.matches("^[a-zA-Z0-9\u4e00-\u9fa5]+$")){
		    		//���������ַ���Ӣ���ַ���ֻ�����ֺ����ĵĴʲż���map
		    		 if(s.matches("^[0-9\u4e00-\u9fa5]+$")){
		    		 cou=gramMap.get(s);
		    		 gramMap.put(s, cou == null?1:cou+1);
		    		 totalGram++;
		    		 }
		    		
		    	}
				}
			Iterator iter=userNameMap.entrySet().iterator();
			while(iter.hasNext()){
				int keyCount=0;
				Map.Entry entry=(Map.Entry) iter.next();
				Object key=entry.getKey();
				//String oneUserName=it.next();
				//�õ�һ���û����е����е�Ngram��
				String[] userNameArr=formGrams(key.toString(),nGramSize);
				for(String ss:userNameArr){
					//���û�����С������,Bigram�õ���С������������õõ�Bigramȥ���е��û�����ȥƥ��ͳ�Ƴ��ִ���
					if(gramMap.containsKey(ss))
					{
						keyCount=keyCount+gramMap.get(ss);
					}
				}
				userNameNgramResults.put(key.toString(), keyCount);
				
				//System.out.println("�û���Ϊ��"+key.toString()+"    ��Ӧ��Ƶ��Ϊ��"+keyCount);
			}
			//System.out.println(userNameNgramResults);
			System.out.println("�ܵķִ���Ϊ"+totalGram);
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
    	 text="%"+text;
    	 int len=text.length();
    	 
    	 String[] res=null;
//    	  if(len-ng+1>0){
    	 
    	 res=new String[len-ng+1];   
    	  
    	 for(int i=0;i<len-ng+1;i++){
    		  	res[i]=text.substring(i,i+ng);		  	
    	 }
    	 
//    	 }
//    	  else 
//    		  res=new String[1];
//    	      res[0]=text;
//    	  
    	 //  System.out.println(res[0]);
    	 return res;
    	 
     }    
     public static void saveResultByHashMap( Map<String,Integer> map,String saveFilePath){
    	 File file=new File(saveFilePath);
      	 StringBuffer str=new StringBuffer();
      	 int cou=0;
         Iterator<?> it =map.entrySet().iterator();
         //��map�е����ݽ��и�ʽ������
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
}
