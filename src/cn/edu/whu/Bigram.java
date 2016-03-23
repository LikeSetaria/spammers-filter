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
import org.apache.commons.io.FileUtils;

import cn.edu.whu.utils.Utils;
/**
 * @author bczhang
 *
 */
public class Bigram {
	
	
	public static void main(String[] args){
		 
		String str2="";
		 try {
			  str2=FileUtils.readFileToString(new File("E:\\temp\\data\\444.txt"), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str=",那,些sfgds,sgdgsd,那些那些那些那些那些年我们发过的微博网络营销少帅严刚";
 		// System.out.println(makeNgrams(",那,些sfgds,sgdgsd,那些那些那些那些那些年我们发过的微博网络营销少帅严刚",2));
	//	File file =new File("D:\\DOWNLOAD\\BaiduYunDownload\\Major\\weibo\\RESULT\\weibo_users_name.txt");
//		String content="";
//		 try {
//			content=FileUtils.readFileToString(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.print( makeNgrams(content,2));
 		saveResultByHashMap(makeNgrams(str2,3),"");
 		 
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
     * Bigram 切割词
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
     public static void saveResultByHashMap(HashMap<String,Integer> map,String saveFilePath){
    	 File file=new File(saveFilePath);
      	 StringBuffer str=new StringBuffer();
      	 int cou=0;
         Iterator<?> it =map.entrySet().iterator();
         while(it.hasNext()){
        	 cou++;
        	 @SuppressWarnings("rawtypes")
			Map.Entry entry=(Map.Entry) it.next();
        	 Object key=entry.getKey();
        	 str.append(key.toString()+" ");
        	 Object val=entry.getValue();
        	 str.append(val.toString()+"  ");
        	 if(cou%30==0)
        		 str.append("\n");
        	
         }
        
         System.out.println(str.toString());
     }
}
