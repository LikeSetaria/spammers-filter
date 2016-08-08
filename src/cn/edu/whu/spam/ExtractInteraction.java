/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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
		//getFromToSet("E:\\spam\\3_UltimateSelected\\weibos\\1081204670.txt");
		
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
			  
			  patch("E:\\spam\\3_UltimateSelected\\341spamWeibos\\");
	}
	public  static void patch(String folderPath) throws IOException{
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
