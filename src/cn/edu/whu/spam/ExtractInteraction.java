/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *提取互动关系
 */
public class ExtractInteraction {
	
static  Set<String> set=new HashSet<>();


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
	
			  
		//patch("D:\\人工赛选\\第二次筛选\\筛选结果\\zbc_spam_reduced筛选\\spam_reduced_weibos\\");
		//patch("D:\\人工赛选\\第二次筛选\\筛选结果\\zbc_normal_reduced_part1\\");
		//getUidF("E:\\normal\\expandSample\\thirdExpandSample\\zbc_select_normal_uid_name.txt","E:\\normal\\expandSample\\thirdExpandSample\\zbc_select_normal_uid_interactions.txt");
		
		
		//statistics("E:\\spam\\3.1_graphFetures\\spam_interaction_relation_Friend.txt");
		//removefollowed("E:\\spam\\3.1_graphFetures\\341uidfriends.txt","E:\\spam\\3.1_graphFetures\\spam_interaction_relation.txt",
				//"E:\\spam\\3.1_graphFetures\\spam_interaction_relation_Friend.txt");
		//removefollowed("E:\\normal\\3.1_graphFetures\\400uidfriends.txt","E:\\normal\\3.1_graphFetures\\normal_interaction_relation.txt",
				//"E:\\normal\\3.1_graphFetures\\normal_interaction_relation_Friend.txt");
		
	}
	 /**
	  * uid followeds 文件1 
	  * uid interactions 文件2 
	  * 目的是从文件2中去除文件1包含的。交互关系中去除朋友关系
	  * @param uidPath
	  * @param interactionRelationPath
	  * @param saveNewinteractionPath
	  */
	 public static void removefollowed(String uidfollowedPath,String interactionRelationPath,String saveNewinteractionPath){
		 Map<String,List<Long>> followeeMap=new HashMap<>();
		 Map<String,List<Long>> interactionMap=new HashMap<>();
		 StringBuilder result=new StringBuilder();
		 try {
			String followee[]=FileUtils.readFileToString(new File(uidfollowedPath)).split("\n");
			String interaction[]=FileUtils.readFileToString(new File(interactionRelationPath)).split("\n");
			//List<String> templist=null;
			//初始化
			for(String ss:followee){
				String arr[]=ss.trim().split(" ");
				List<Long> templist=new ArrayList<>();
				for(int i=1;i<arr.length;i++){
					templist.add(Long.parseLong(arr[i].trim()));
				}
				followeeMap.put(arr[0], templist);
			}
			for(String str:interaction){
				String arr[]=str.trim().split(" ");
				List<Long> templist2=new ArrayList<>();
				for(int i=1;i<arr.length;i++){
					templist2.add(Long.parseLong(arr[i].trim()));
				}
				interactionMap.put(arr[0], templist2);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(Map.Entry<String, List<Long>> map:interactionMap.entrySet()){
			 List<Long> interList=interactionMap.get(map.getKey());
			 List<Long> folloList=followeeMap.get(map.getKey());
			 result.append(map.getKey());result.append(" ");
			 for(Long ss:interList){
				 if(folloList.contains(ss)){
					result.append(ss);
					result.append(" ");
				 }
			 }
			 result.append("\n");
			 //System.out.println(map.getKey()+" "+result);
			 //System.out.println(map.getValue());
		 }
		 try {
			FileUtils.write(new File(saveNewinteractionPath), result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 /**
	  * 统计每个用户交互的人数
	  */
	 public static  void statistics(String interactionPath){
		 try {
			String[] textarr=FileUtils.readFileToString(new File(interactionPath)).split("\n");
			
			for(String ss:textarr){
				Set<String> set=new HashSet<>();
				String []arr=ss.split(" ");
				for(String kk:arr)
					set.add(kk.trim());
				System.out.println(set.size()-1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 
	/**
	 * 把uid 用户名映射为 uid interactionUIDs
	 * @param path
	 */
	public static  void getUidF(String uidUsernameFilePath,String savePath){
	Utils utils=new Utils();
		Map<String,String>idname=new HashMap<>();
		Set<String > ssset=new HashSet<>();
		 File file=new File("D:\\Whuer\\FudanData\\weibo_users.txt");
		 File file2=new File(uidUsernameFilePath);
    	 LineIterator it=null;
    	 LineIterator it2=null;
			  try {
				it = FileUtils.lineIterator(file);
				it2 = FileUtils.lineIterator(file2);
				while(it.hasNext()){
					String line =it.nextLine();
					String [] arr=line.split(",");
					if(arr.length>4){
					idname.put(arr[1], arr[4]);
					}
				}
				StringBuilder strb=null;
				StringBuilder result=new StringBuilder();
				while(it2.hasNext()){
					strb=new StringBuilder();
					String line2 =it2.nextLine();
					String [] arr2=line2.trim().split(" +");
					String id=arr2[0];
					if(ssset.contains(id))
						continue;
					strb.append(id);
					ssset.add(id);
					strb.append(" ");
					if(ssset.contains(arr2[0]))
					for(int i=0;i<arr2.length;i++){
						if(idname.containsKey(arr2[i])&&arr2.length>1){
							strb.append(idname.get(arr2[i]));
							strb.append(" ");
						}
					}
					System.out.println(strb);
					result.append(strb.toString());
					result.append("\n");
				}
				FileUtils.write(new File( savePath), result);
				
					 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} finally{
			   LineIterator.closeQuietly(it);
		}
	}
	/**
	 * 提取交互关系，主要是每个用户的 @的人 和转发中@中的 人
	 * @param folderPath
	 * @throws IOException
	 */
	public  static void patch(String folderPath) throws IOException{
		Utils utils=new Utils();
		  Set<String> profileset=new HashSet<>();
		 File file=new File("D:\\Whuer\\FudanData\\weibo_users.txt");
    	 LineIterator it=null;
			  try {
				it = FileUtils.lineIterator(file);
				while(it.hasNext()){
					String line =it.nextLine();
					String [] arr=line.split(",");
					if(arr.length>5){
					profileset.add(arr[1].trim());
					//System.out.println(arr[1]);
					}
				}
				
					 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} finally{
			   LineIterator.closeQuietly(it);
		}
		File folder=new File(folderPath);
		String[] files=folder.list();
		//String basePath="E:\\spam\\3_UltimateSelected\\341SpamWeibos\\";
		String basePath=folderPath;
		StringBuilder strb=null;
		StringBuilder Aresult=null;
		for(String filename:files)
		{  
			//getFromToSet(basePath+filename);
			strb=new StringBuilder(FileUtils.readFileToString(new File(basePath+filename)));//得到一个用户的所有的微博
			String[] linearr=strb.toString().split("\n");//得到微博数组
			  Aresult=new StringBuilder();
			  Aresult.append(filename);		
//			  for(String ss:linearr){
//				for(String ausername:profileset){//对每一条微博都遍历以便
//					if(ss.contains("@"+ausername)){
//						//System.out.println(ausername);
//					 Aresult.append(ausername);
//					 Aresult.append(" ");
//					}
//				}
//				 
//			}
				for(String ausername:profileset){//��ÿһ��΢���������Ա�
				    for(int j=0;j<linearr.length;j++){
					if(linearr[j].contains("@"+ausername)){
						//System.out.println(ausername);
					 Aresult.append(ausername);
					 Aresult.append(" ");
					}
				    }
				//}
			}
			  Aresult.append("\n");
			  FileUtils.write(new File("D:\\人工赛选\\第二次筛选\\筛选结果\\select_normal2.txt"), Aresult,true);
			 // System.out.println(Aresult);
		}
		
	}
	public static void getFromToSet(String weibofilePath){
	
		try {
			String[] textarr=FileUtils.readFileToString(new File(weibofilePath)).split("\n");
			Pattern p=Pattern.compile("@.*:");  
		
			Pattern p2=Pattern.compile("@.*"); //^@[^@]+\\s$   
			
			
			for(String ss:textarr){
				String [] linearr=ss.split(",");
				String aweibo=linearr[linearr.length-1];
				if(aweibo.contains("@")){
					String [] fromarr=aweibo.split("//");
					String [] toarr=aweibo.split(" ");
					for(String str:fromarr){
					Matcher m=p.matcher(str);  
					 
					while(m.find()){  
						//System.out.println(m.group().replace("@", "").replace(":", ""));  
						set.add(m.group().replace("@", "").replace(":", ""));
						}  
					}
//					for(String str:toarr){
//						if(str.contains("@"))
//						{//System.out.println(str);
//							Matcher m2=p2.matcher(str); 
//						while(m2.find()){  
//							String temp=m2.group();
//							//System.out.println(temp);
//							if(!temp.contains(":")&&!temp.contains("：")){
//								if(temp.contains("。")){
//									String  akk[]=temp.split("。");
//							//System.out.println(akk[0].replace("@", ""));  
//							 set.add(akk[0].replace("@", ""));
//								}
//								else if (temp.contains("，")){
//									String  akk[]=temp.split("，");
//							      //  System.out.println((akk[0].replace("@", "")));  
//							        set.add(akk[0].replace("@", ""));
//								}else if (temp.contains(",")){
//									String  akk[]=temp.split(",");
//							       // System.out.println((akk[0].replace("@", "")));  
//							        set.add(akk[0].replace("@", ""));
//								}else
//						       set.add(temp.replace("@", ""));
//						   }
//								
//							} 
//						}
//						}
				//System.out.println(aweibo);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
