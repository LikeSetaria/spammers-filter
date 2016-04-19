/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.pojo.Relation;

/**
 * 寻找微博关注关系中三角关系，社会学上分析三角关注关系作为一种稳定关系，所以有这种关系的人是水军的可能性就比较低
 * 但另一方面集团水军一般会相互关注，这方面高的话是不是说明另一个问题呢？
 * @author bczhang
 *输入文件是关注关系文件，第一个是UID后面跟着的是他关注的用户ID,以Tab分割
 */
public class FindTriangleRelation {
	    //保存最后的结果，其中String是每个用户的ID作为Key，value存储的是间接关注的个数。如A->B,B->C,C->A其中value存的是C关注A的人数
       static Map<String ,Integer> result=new TreeMap<String,Integer>();
       //关注关系，
       static Map<String,Relation> friendMap=new HashMap<String,Relation>();
       static Set<String> Cset=new HashSet<String>();
       public static void main(String[] args){
    	   FindTriangleRelation find=new FindTriangleRelation();
    	   Utils utils=new Utils();
    	   find.initFriendMap();
    	   find.findTri();
    	   result=utils.sortMapByValue(result);
    	   utils.saveResultByHashMap(result, "F:/tri.txt");
    	  // System.out.println(result.size());
    	  //System.out.println(result);
    	   
       }
       
       //A->B;B->C;C->A
       public void initFriendMap(){
    	   File file=new File("E:/relation_results_as_fans_25w.txt");
      	 LineIterator iter =null;     	 
       	 String strb=null;
       	 Relation relation=null;
       	 try {
			iter=FileUtils.lineIterator(file);
			String str;
			//文件读入Map
			while(iter.hasNext()){
				strb=iter.next();
				String[] arr=strb.split("\t");
				relation =new Relation();
				relation.setUser_UID(arr[0]);
				for(int i=1;i<arr.length-1;i++)
				relation.setFollowsByHe(arr[i]);
				friendMap.put(arr[0], relation);
				//System.out.println(relation);
	       	 }
			System.out.println("加载关注关系文件，初始化完成");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			LineIterator.closeQuietly(iter);
		}
       	 
       }
       /*
        * 得到一个集合，B集合中关注的所有的ID,集合C
        * 参数uid是是集合B中的一个,这个Id应是A中存在的
        * 返回一个集合
        */
       public   List<String> getColBToC(String Auid){
    	   List<String> resultCSet=new ArrayList<String>();
    	   Set<String> temp=new HashSet<String>();
           //得到A中一个用户关注的列表B
    	   Relation rel=friendMap.get(Auid);
    	   temp=rel.getFollowsByHe();
    	   Relation reltemp=null;
    	   for(String ss:temp){
    		   //得到B中的一个用户的关注列表C子集
    		   //B得是A中的，否则不可能存在三角关系
    		   if(friendMap.containsKey(ss)){
    		   reltemp=friendMap.get(ss);
    		   resultCSet.addAll(reltemp.getFollowsByHe());
    		   }
    	   }
           return resultCSet;    
    	   
       }
       public void findTri(){
    	   Iterator<?> it =friendMap.entrySet().iterator();
	         while(it.hasNext()){  
				Map.Entry entry=(Map.Entry) it.next();
	        	 String key=entry.getKey().toString();
	             List<String> CList=getColBToC(key);
	             int count=0;
	             //统计集合C中关注A的用户数，如果为0代表这个用户没有三角关注关系
 	             for(String ss:CList){
	             if(ss.equals(key))
	            	 count++;
 	             }
 	             result.put(key, count);
	         }
	         
	         
       }
}
