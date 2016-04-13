 
package cn.edu.whu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.pojo.Relation;

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
	
	
	public static  void main(String[] args){
		PreprocessText preText=new PreprocessText();
		String p="D:/DOWNLOAD/BaiduYunDownload/weibo_users_name.txt";
		onlyChinese(p);
	
	}
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
}
