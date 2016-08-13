/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
static  Set<String> profileset=new HashSet<>();

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
	
			  
		//patch("E:\\spam\\3_UltimateSelected\\341spamWeibos\\");
		getUidF("F:/fromTo.txt");
	}
	public static  void getUidF(String path){
	Utils utils=new Utils();
		Map<String,String>idname=new HashMap<>();
		Set<String > ssset=new HashSet<>();
		 File file=new File("D:\\Whuer\\FudanData\\weibo_users.txt");
		 File file2=new File("F:/NormalFromTo2.txt");
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
				FileUtils.write(new File("F:/normal_interaction_relation.txt"), result);
				
					 
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
		
		utils.saveResultBySet(set, "F:spamUserNameSet.txt");
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
		String basePath="E:\\spam\\3_UltimateSelected\\341SpamWeibos\\";
		StringBuilder strb=null;
		StringBuilder Aresult=null;
		for(String filename:files)
		{  
			//getFromToSet(basePath+filename);
			strb=new StringBuilder(FileUtils.readFileToString(new File(basePath+filename)));//得到一个用户的所有的微博
			String[] linearr=strb.toString().split("\n");//得到微博数组
			  Aresult=new StringBuilder();
			  Aresult.append(filename);		
			  for(String ss:linearr){
				for(String ausername:profileset){//对每一条微博都遍历以便
					
					if(ss.contains("@"+ausername)){
						//System.out.println(ausername);
					 Aresult.append(ausername);
					 Aresult.append(" ");
					}
				}
			}
			  Aresult.append("\n");
			  FileUtils.write(new File("F:/fromTo.txt"), Aresult,true);
			  System.out.println(Aresult);
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
