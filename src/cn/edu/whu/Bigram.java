/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import org.apache.commons.io.FileUtils;
/**
 * @author bczhang
 *
 */
public class Bigram {
	
	
	public static void main(String[] args){
		
//		 System.out.println(makeNgrams(",��,Щsfgds,sgdgsd,��Щ��Щ��Щ��Щ��Щ�����Ƿ�����΢������Ӫ����˧�ϸ�",2));
		File file =new File("D:\\DOWNLOAD\\BaiduYunDownload\\Major\\weibo\\RESULT\\weibo_users_name.txt");
		String content="";
		 try {
			content=FileUtils.readFileToString(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print( makeNgrams(content,2));
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
     * Bigram �и��
     * @param text
     * @param ng
     * @return
     */
     private static String[] formGrams(String text,int ng){
    	 int len=text.length();
    	 String[] res=new String[len-ng+1];
    	 for(int i=0;i<len-ng+1;i++){
    		  	res[i]=text.substring(i,i+ng);
    		  	
    	 }
    	 return res;
    	 
     }    
}
