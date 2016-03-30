/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.whu.utils.Utils;

/**
 * @author ����
 *���Է�����
 */
public class Test {
	 private static String WEIBO_USER_FILEPATH="D:\\Whuer\\Major\\Refs4Spammers";
	 private final static Logger logger = Logger.getLogger(Test.class);
	
	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
		FileFilter filter=new FileFilter();
		Test test=new Test();
		/*//����getFileList()����
      
      Map<String, File> map=utils.getFileList(WEIBO_USER_FILEPATH);
      for(Iterator i = map.keySet().iterator(); i.hasNext();){
    	   Object obj = i.next();
    	   System.out.println(obj);
    	   System.out.println("key=" + obj + " value=" + map.get(obj));
      }
      for(Map.Entry<String, File> entry :map.entrySet()){
    	  System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }*/
		
		//����copyFile()����
		/*
		utils.copyFile("c:\\unintall.log", "d:\\unintall.log");
		*/
		
		
		//���Զ�ȡ���ļ�
		/*
		logger.info("���ļ�����");
	//	utils.readBigFile("D:\\Whuer\\Major\\weibo\\weibo_users.csv");
		*/
		//���Զ�ȡһ���ļ�
		//utils.readFile("e:test.txt");
		
		//System.out.println(utils.writeToFile("e:\\test.txt", "hhhhhh"));
		//System.out.println(utils.readFile("e:\\test.txt"));
		//filter.Filter_V("D:\\Whuer\\Major\\weibo\\weibo_users.txt");
		//filter.onlyChinese("D:/DOWNLOAD/BaiduYunDownload/weibo_users_name.txt","E:/temp/data/777.txt");
		 String str ="丁为";
		// System.out.println(str.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]+", "#"));
     	String[] temp= test.formGrams(str,3);
//		 for (String s:temp){
//			 System.out.println(s);
//		 }
	          
	       
	}
	  public    String[] formGrams(String text,int ng){
		 
		  //对字符串进行切分处理时，对于重复的特殊字符合并看做一个来处理，降低它们出现的概率
	    	
	    	//对于非中文、英文、数字外的特殊字符进行过滤替换，看做一个整体统计其出现次数
	    	 text=text.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]+", "%");
	    	 text="%"+text;
	    	 int len=text.length();
	    	 
	    	 String[] res=null;
	  	  if(len-ng+1>0){
	    	 
	    	 res=new String[len-ng+1];   
	    	  
	    	 for(int i=0;i<len-ng+1;i++){
	    		  	res[i]=text.substring(i,i+ng);	
	    		  	System.out.println(res[i]);
	    	 }
	    	 
	    	 }
	    	  else {
	    		  res=new String[1];
	    	      res[0]=text;
	    	  }
	    	//   System.out.println(res[0]);
	    	 return res;
	    	 
	     } 
	

}
