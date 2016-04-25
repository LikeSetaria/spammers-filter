 
package cn.edu.whu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.pojo.Relation;
import cn.edu.whu.pojo.User;

/**
 * @author bczhang
 *Ԥ�����ı���
 *
 */
public class PreprocessText {
	/*
	 * ���ı�����Ԥ����ȥ�����еķ������ַ�
	 * ���浽
	 */
	private static String savePath="E:/temp/data/";
	Utils utils=new Utils();
	public static void onlyChinese(String filePath){
		String[] tempsplit=filePath.split("/");
		String prefileName=savePath+tempsplit[tempsplit.length-1];
		//createFile(prefileName);
		File file=new File(filePath);
		File prefile=new File(prefileName);
		 LineIterator it=null;
    	 
    	 FileWriter filewriter=null;
		 PrintWriter pw=null;
    	 int count=0;
		  try {
			it = FileUtils.lineIterator(file, "UTF-8");
			filewriter=new FileWriter( prefile ,true);
			pw=new PrintWriter(filewriter);
			while(it.hasNext()){
				count++;
				String line=it.nextLine();
				if(line.matches("[\u4e00-\u9fa5]")){
					pw.print(line);
					if(count%100==0)
					pw.println();
				}
				pw.flush();
				filewriter.flush();
					
			}
			  
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} finally{
		   LineIterator.closeQuietly(it);
	}
		return  ;
	}
	
	
//	public static  void main(String[] args){
//		PreprocessText preText=new PreprocessText();
//		String p="D:/DOWNLOAD/BaiduYunDownload/weibo_users_name.txt";
//		onlyChinese(p);
//	
//	}
	 public static boolean createDir(String destDirName) {  
	        File dir = new File(destDirName);  
	        if (dir.exists()) {  
	            System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�Ŀ��Ŀ¼�Ѿ�����");  
	            return false;  
	        }  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        //����Ŀ¼  
	        if (dir.mkdirs()) {  
	            System.out.println("����Ŀ¼" + destDirName + "�ɹ���");  
	            return true;  
	        } else {  
	            System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�");  
	            return false;  
	        }  
	    } 
	 public static boolean createFile(String destFileName) {  
	        File file = new File(destFileName);  
	        if(file.exists()) {  
	            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");  
	            return false;  
	        }  
	        if (destFileName.endsWith(File.separator)) {  
	            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ�����ΪĿ¼��");  
	            return false;  
	        }  
	        //�ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����  
	        if(!file.getParentFile().exists()) {  
	            //���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼  
	            System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");  
	            if(!file.getParentFile().mkdirs()) {  
	                System.out.println("����Ŀ���ļ�����Ŀ¼ʧ�ܣ�");  
	                return false;  
	            }  
	        }  
	        //����Ŀ���ļ�  
	        try {  
	            if (file.createNewFile()) {  
	                System.out.println("���������ļ�" + destFileName + "�ɹ���");  
	                return true;  
	            } else {  
	                System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�");  
	                return false;  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�" + e.getMessage());  
	            return false;  
	        }  
	    }
	 /**
	  * 预处理follows_user.csv文件，从中提取潜在垃圾用户ID所有对应的 关系
	  * @param filePath
	  */
	 
     public    void extractPartOfRelation(String followsFilePath,String uidFilePath,String resultFilePath){
    	 File followFile=new File(followsFilePath);
    	 File uidFile =new File(uidFilePath);
    	 LineIterator it=null;
    	 LineIterator iter=null;
    	//这里必须使用Hash一类的，因为对于follow_user.csv中的一行，都要去这个集合中查找是否出现，
    	 //一开始我使用的是List 使用contain（）方法， 可想而知速度奇慢无比，每条比较都要遍历二百多万条数据，而follow文件有8Gb大小
    	 //后来使用HashSet速度提高了百万倍 ，体会Hash原理，对于查找的意义，以及为什么会这么快
    	  HashSet<String> set=new HashSet<String>();
    	  StringBuilder str1=null;
    	  StringBuilder str2=new StringBuilder();
    	 FileWriter fw=null;
    	 int total1=0;int total2=0;
    	 // Map<String,LinkedList<String>> map=new HashMap<String,LinkedList<String>>(); 
			  try {
			    fw = new FileWriter(resultFilePath,true); 
				it = FileUtils.lineIterator(followFile, "UTF-8");
				iter = FileUtils.lineIterator(uidFile, "UTF-8");
				while(iter.hasNext()){
					total1++;
					str1=new StringBuilder(iter.nextLine());
					set.add(str1.toString());
				}
				while(it.hasNext()){
					total2++;
					str2=new StringBuilder(it.nextLine());
					String[] arr=str2.toString().split(",");
					//提取目标用户作为粉丝，和被关注者所有的关系信息
					//if(set.contains(arr[0])||set.contains(arr[1])){
					//提取目标用户仅作为粉丝，所有的关系
					//	if(set.contains(arr[0])){
					//提取目标用户仅作为被关注者的所有的关系信息
					if(set.contains(arr[1])){
						fw.write(str2.toString());
						fw.write("\n");
					
					}
					fw.flush();
			
				}
				
				System.out.println("potential_spammers_4_uid_10w遍历的总行数为："+total1+
						"   weibo_follows.csv的总行数是："+total2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} finally{
			   LineIterator.closeQuietly(it);
			   if (fw != null)  
	                try {  
	                    fw.close();  
	                } catch (IOException e) {  
	                    throw new RuntimeException("关闭失败！");  
	                }  
		}
    	 return ;
     }
     
     /**
      * 提取关系，形式是:用户UID，他关注的用户的UID
      */
     public void extractFollows(String relationFilePath,String saveFilePath){
    	File file =new File(relationFilePath); 
    	HashMap<String ,Relation>  hashmap=new HashMap<String,Relation>();
    	LineIterator iter =null;
    	String  line=null;
    	Relation relation =new Relation();
    	//Iterator it = map.entrySet().iterator();  
        int total=0;
    	try {
			iter=FileUtils.lineIterator(file, "utf-8");
			while(iter.hasNext()){
				 total++;
				line=iter.nextLine();
				String[] arr=line.split(",");
				if(hashmap.containsKey(arr[0])){
					//relation 对象已经存在，更新其follows
					relation=hashmap.get(arr[0]);
					relation.setFollowsByHe(arr[1]);
					hashmap.put(arr[0],relation);
				}
				else
				{  //relation对象不存在，创建对象然后加入到hashmap中
					//HashSet<String> set=new HashSet<String>();
				Relation rel=	new Relation();
				rel.setUser_UID(arr[0]);
				rel.setFollowsByHe(arr[1]);
				hashmap.put(arr[0], rel);
				}
				 
				}
			System.out.println("从文件"+file.getName()+"遍历的总行数为："+total);
			//System.out.println(hashmap.get("1589002662").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   LineIterator.closeQuietly(iter);
		}	
    	//hashmap持久化\
    	System.out.println("Now to save the results");
     	File savefile=new File(saveFilePath);
   	 StringBuffer str=null;
  	 int cou=0;
     Iterator<?> it =hashmap.entrySet().iterator();
     FileWriter filewriter=null;
	 PrintWriter pw=null;
	

     try {
    	 filewriter=new FileWriter(savefile,true);
 		pw=new PrintWriter(filewriter);
 	     while(it.hasNext()){
 	    	 cou++;
 	    	str=new StringBuffer();
 	    	 @SuppressWarnings("rawtypes")
 			Map.Entry entry=(Map.Entry) it.next();
// 	    	 Object key=entry.getKey();
// 	    	 str.append(key.toString()+" ");
 	    	 Object val=entry.getValue();
 	    	 str.append(val.toString());
 	    	 //if(cou%30==0)
 	    		// str.append("\n");
 	    		 pw.print(str);
			     pw.println(); 
 	     }
 	    pw.flush();
		filewriter.flush();
		//FileUtils.writeStringToFile(file, str.toString(), "utf-8");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
     }
     /**
      * 待处理文件的格式
      * follows_id     users_id
      * 1051852951,1045604114
        1092658523,1045604114
        1178310055,1045604114
      * 提取目标用户所有的粉丝提取的格式是：用户ID 粉丝id.....
      * 文件太大如超过400Mb时程序会报错，又待改进，不过可以拆分文件分部分，先基本完成任务
      */
     public void extractFans(String followUsersFilePath ,String saveFilePath ){
    	 File file=new File(followUsersFilePath);
    	 LineIterator iter =null;     	 
     	 Map<String,Relation> hashmap=new HashMap<String,Relation>();
     	 String strb=null;
     	 Relation relation=new Relation();
     	 try {
			iter=FileUtils.lineIterator(file);
			while(iter.hasNext()){
				strb=iter.next();
				String[] arr=strb.split(",");
				if(hashmap.containsKey(arr[1])){
					//System.out.println("arr1wei: "+arr[1]);
					relation=hashmap.get(arr[1]);
					relation.setFollowsByHe(arr[0]);
				    hashmap.put(arr[1], relation);
				}else{
					Relation rel=new Relation();
					rel.setUser_UID(arr[1]);
					rel.setFollowsByHe(arr[0]);
					hashmap.put(arr[1], rel);
					
				}
			}
		//System.out.println(hashmap.get("1749866927").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(strb);
		}finally{
			LineIterator.closeQuietly(iter);
		}
     	System.out.println("Now to save the results");
     	File savefile=new File(saveFilePath);
   	 StringBuffer str=null;
  	 int cou=0;
     Iterator<?> it =hashmap.entrySet().iterator();
     FileWriter filewriter=null;
	 PrintWriter pw=null;
	

     try {
    	 filewriter=new FileWriter(savefile,true);
 		pw=new PrintWriter(filewriter);
 	     while(it.hasNext()){
 	    	 cou++;
 	    	str=new StringBuffer();
 	    	 @SuppressWarnings("rawtypes")
 			Map.Entry entry=(Map.Entry) it.next();
// 	    	 Object key=entry.getKey();
// 	    	 str.append(key.toString()+" ");
 	    	 Object val=entry.getValue();
 	    	 str.append(val.toString());
 	    	 //if(cou%30==0)
 	    		// str.append("\n");
 	    		 pw.print(str);
			     pw.println(); 
 	     }
 	    pw.flush();
		filewriter.flush();
		//FileUtils.writeStringToFile(file, str.toString(), "utf-8");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	 
 
     }
	  
	  /**
	   * 功能一：提取既关注别人，又有粉丝关注的用户UID，保存这个UID列表
	   * 功能二：提取用户的粉丝数量，及其关注的用户的数量，并根据关注数进行降序排列 。形式是2589370790	32	20
	   */
	  public   void extractBoth(String uidfollowsFile,String uidfriendsFile,String saveFilePath){
		  File fileIsFollowed=new File(uidfollowsFile);
		  File fileAsFans=new File(uidfriendsFile);
	    	 LineIterator iter =null;  
	    	 LineIterator it =null;  
	     	 Set<String> isFollowedset=new HashSet<String>();
	     	Set<String> asFansset=new HashSet<String>();
	     	 String strb=null;
	     	 String str=null;
	     	 User user=null;
	     	Map<String ,User > userMap=new HashMap<String ,User>();
	     	 Map<String ,User > result=new HashMap<String ,User>();
	     	 
	     	 try {
				iter=FileUtils.lineIterator(fileAsFans);
				while(iter.hasNext()){
					user=new User();
					strb=iter.next();
					String[] arr=strb.split(" ");
					user.setUID(arr[0]);
					user.setFriendNums(arr.length-1);
					userMap.put(arr[0], user);
					//功能一部分，保存列表
					if(!isFollowedset.contains(arr[0])){
						isFollowedset.add(arr[0]);
					}
				}
				
				System.out.println(" 现在做好一半了，这个集合一共有"+isFollowedset.size());
				it=FileUtils.lineIterator(fileIsFollowed);
				User ue=null;
				BigDecimal   b   =   null;  
				BigDecimal   c   =   null;  
				
			 
				while(it.hasNext()){
				    
					str=it.nextLine();
					String[] arr2=str.split(" ");
					ue=userMap.get(arr2[0]);
				    //做非空处理,不为空就是意味着上面统计关注数量时有了这个对象
					if(ue!=null){
                    ue.setUID(arr2[0]);
                    ue.setFollowNums(arr2.length-1);
                    //计算关注数除以粉丝数
                    b=new BigDecimal((double)ue.getFriendNums()/(double)ue.getFollowNums());
                    double   f1   =   b.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();  
                    ue.setFriDivFolRate(f1);
                    //计算用户的关注度，关注度=Nfriends/(Nfriends+Nfollows)
                    c=new BigDecimal((double)ue.getFriendNums()/((double)(ue.getFollowNums()+ue.getFriendNums())));
                    double   f2   =   c.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();  
                    ue.setFriendsRate(f2);
                    result.put(arr2[0], ue);
                    }
                    //System.out.println(ue);
                    //功能一部分，保存UID
					if(!asFansset.contains(arr2[0])){
						asFansset.add(arr2[0]);
					}
				
					
				}
				
				//对结果map进行排序，这里是根据用户关注的用户数量进行排序，具体可以重写sortMapByValue中的相关代码
				result=sortMapByValue(result);
				//保存得到结果
			  saveResultByUserMap(result,saveFilePath);
//				for (Map.Entry<String, User> entry : result.entrySet()) {
//					   System.out.println(entry.getValue());
//					  }
//				System.out.println(" 现在另一半也做好了，这个集合一共有"+asFansset.size());
				//isFollowedset.addAll(asFansset);
				
				Set<String> results=new HashSet<String>();
				Iterator itt=asFansset.iterator();
				while(itt.hasNext()){
					
					String temp=itt.next().toString();
					if(isFollowedset.contains(temp)){
						
						results.add(temp);
					}		
				}
				System.out.println("交集的大小为"+results.size());
				/*控制台显示
				int i=0;
				for(String res:results){
					
					i++;
				System.out.print(res+"\t");
				
				if(i%10==0)
					
					System.out.println();
				
				}*/
				//功能一，保存UID列表
				//utils.saveResultBySet(results, saveFilePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(strb);
			}finally{
				LineIterator.closeQuietly(iter);
				LineIterator.closeQuietly(it);
			}		  
	  }
	  
	  
	    public   Map<String,User> sortMapByValue(Map<String,User> map ){
	    	 
	    	 ArrayList<Map.Entry<String ,User>> list=new ArrayList<Map.Entry<String,User>>(map.entrySet());
	    	 Collections.sort(list,new Comparator<Map.Entry<String ,User>>(){    		 
				@Override
				public int compare(java.util.Map.Entry<String, User> arg0, java.util.Map.Entry<String, User> arg1) {
					// TODO Auto-generated method stub
					User user1=arg0.getValue();
					User user2=arg1.getValue();
					//根据不同参数排序
					//根据关注数降序排列
					//int result=user2.getFriendNums()- user1.getFriendNums();
					//根据粉丝数降序排列
					int result=user2.getFollowNums()- user1.getFollowNums();
					
					//根据关注数与粉丝数比例降序排列
					//double result=user2.getFriDivFolRate()- user1.getFriDivFolRate();
					
					//根据关注数与粉丝数比例降序排列
					//double result=user2.getFriendNums()- user1.getFriendNums();	
				      if(result > 0)
				       return 1;
				      else if(result == 0)
				       return 0;
				      else 
				       return -1;
				}    		 
	    	 });
	    	     Map<String ,User> newMap = new LinkedHashMap<String,User>();  
	            for (int i = 0; i < list.size(); i++) {  
	                newMap.put(list.get(i).getKey(), list.get(i).getValue());  
	            }  
	            return newMap; 
	     }
	    
	    public   void saveResultByUserMap( Map<String,User> map,String saveFilePath){
	    	 File file=new File(saveFilePath);
	      	 StringBuffer str=new StringBuffer();
	      	 int cou=0;
	         Iterator<?> it =map.entrySet().iterator();
	         while(it.hasNext()){
	        	 cou++;
	        	 @SuppressWarnings("rawtypes")
				Map.Entry entry=(Map.Entry) it.next();
	        	 Object key=entry.getKey();
	        	// str.append(key.toString()+" ");
	        	 Object val=entry.getValue();
	        	 str.append(val.toString()+"  ");
	        	// if(cou%30==0)
	        		 str.append("\n");
	        	
	         }
	         try {
				FileUtils.writeStringToFile(file, str.toString(), "utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         //System.out.println(str.toString());
	     }
	    
	    
	    /**
	     *根据ID抽取相关的条目。
	     *参数输入：1、源文件，格式化文件（false,小神万里,m,湖北 武汉,44528425,农民
                        false,咯咯spy,m,江苏 扬州,44550011,""）以行为分割
                 2、条件文件，格式化文件（44550011
                           44528425）
                 3、输出结果，条件文件中指定的所有条目
	     * @throws FileNotFoundException 
	     */
	    public  void extractItemByUID(String sourceFilePath,String needFilePath,String resultFilePath ) throws FileNotFoundException{
	    	File sourceFile=new File(sourceFilePath);
	    	File needFile=new File(needFilePath);
	    	File resultFile=new File(resultFilePath);
	    	if(!sourceFile.exists())
	    		throw new FileNotFoundException();
            LineIterator iter=null;
            LineIterator it=null;
            Set<String> needItemsSet=new HashSet<String>();
            try {
				iter=FileUtils.lineIterator(sourceFile);
				it=FileUtils.lineIterator(needFile);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            while(it.hasNext()){
            	needItemsSet.add(it.nextLine());
            }
            LineIterator.closeQuietly(it);
            String line;
            StringBuffer strb=new StringBuffer();
            System.out.println(needItemsSet.size());
            Map<String,String> map=new HashMap<String,String>();
            while(iter.hasNext()){
            	line=iter.nextLine();
            	String[] arr=line.split(",");
            	map.put(arr[4], line);
            	 
//            	if(arr.length>5){
//            		if(needItemsSet.contains(arr[4])){
//            			strb.append(line);
//            			strb.append("\n");
//            		}
//            		
//            	}
            }
            LineIterator.closeQuietly(iter);
          // System.out.println(map);
            StringBuffer str= new StringBuffer();
            for(String ss:needItemsSet){
            	if(map.containsKey(ss)){
            		str.append(map.get(ss));
            	str.append("\n");}
            }
         
			
            try {
				FileUtils.writeStringToFile(resultFile, str.toString(),"utf-8"); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
     
	    /**
	     * 统计简介情况
	     */
	    public void analyseProfile(String userFilePath){
	    	File file =new File(userFilePath);
	    	LineIterator it=null;
	    	int count=0;
	    	try {
				it=FileUtils.lineIterator(file);
				String line;
				while(it.hasNext()){
					line=it.nextLine();
					String[] arr=line.split(",");
//					if(arr[arr.length-1].equals("\"\"")){
//						count++;
//					}
					if(arr[arr.length-1].toString().contains("http")){
						count++;
					}
					
				}
				System.out.println(count);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				LineIterator.closeQuietly(it);
			}
	    }
     
}
