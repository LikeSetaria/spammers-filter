package cn.edu.whu.spam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.utils.Utils;

public class HandNewDataSource {
    static String source="D:/whuer/formerData/spam.txt";
    
    static Set uidSet=new HashSet<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    init();
    //System.out.println(uidSet.size());
    extratRelation();
	}
	public static void  init(){
		Utils utils=new Utils();
		uidSet=utils.readToSet(source);
		extratRelation();
		extractF();
	}
	public static void extratRelation(){
		 File file=new File("D:/Whuer/FormerData/32w_users_FollowerS");
      	 LineIterator iter,it =null;
       	 String strb=null;
      
   			try {
				iter=FileUtils.lineIterator(file);
			
				FileWriter fw=null;
	  			 fw = new FileWriter("D:/Whuer/FormerData/200_users_FollowerS",true); 
				while(iter.hasNext()){
					String line=iter.nextLine().trim();
					String[] arr=line.split("\\s");
					//System.out.println(arr[0]);
					if(uidSet.contains(arr[0].trim())){
						 fw.write(line);
		  	        	 fw.write("\n");
		  	        	 fw.flush();
					}
				}
				 if (fw != null)  
		                try {  
		                    fw.close();  
		                } catch (IOException e) {  
		                    throw new RuntimeException("关闭失败！");  
		                }  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	public static void extractF(){
		 File file=new File("D:/Whuer/FormerData/200_users_FollowerS");
       	 LineIterator iter =null;
        	 String strb=null;
        	 try {
    			iter=FileUtils.lineIterator(file);
    			StringBuilder point=null;
    			int lineNums=0;
    			Map<String,StringBuilder> map=new TreeMap<String,StringBuilder>();
    			while(iter.hasNext()){
    				 
    				strb=iter.next().trim();
    				
    				String[] arr=strb.split("\\s");
    				//System.out.println(arr[0]);
    				if(map.containsKey(arr[0]))
    				{
    					map.put(arr[0], map.get(arr[0]).append(" "+arr[1]));
                    //System.out.println(map.get(arr[1]));
    				}else{
    					map.put(arr[0], new StringBuilder(arr[0]+" "+arr[1]));
    					 
    				}
    				lineNums++;
    				 
    			}
    			System.out.println("Now to save the results");
    			 Iterator<?> it =map.entrySet().iterator();
    			 System.out.println("用户数总计："+map.size()+"  "+"粉丝总数为"+lineNums);
    			 FileWriter fw=null;
    			 fw = new FileWriter("D:/Whuer/FormerData/200_uid_follows.txt",true); 
    			 
    	         while(it.hasNext()){
    	        	 
    				Map.Entry entry=(Map.Entry) it.next(); 
    	        	 Object val=entry.getValue();
    	        	 fw.write(val.toString()+" ");
    	        	 fw.write("\n");
    	        	 fw.flush();
    	         }
    	         if (fw != null)  
 	                try {  
 	                    fw.close();  
 	                } catch (IOException e) {  
 	                    throw new RuntimeException("关闭失败！");  
 	                }  
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println(strb);
    		}finally{
    			LineIterator.closeQuietly(iter);
    		}
        	
	}

}
