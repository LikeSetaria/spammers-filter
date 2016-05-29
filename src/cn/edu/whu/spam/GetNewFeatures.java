/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *抽取一些新的创新的特征，
 */
public class GetNewFeatures {
	private static final String USER_PROFILE_PATH="";
	private static Map<String,String> Vrelation=new HashMap<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//for spam
		initIdentifyRela("D:/Whuer/FudanData/weibo_users.txt");
		//getVfriends("E:/spam/3_UltimateSelected/uidfriends_selected.txt","E:/VRelation_spam.txt");
		getVfriends("E:/normal/2_UltimateNormal/uidfriends.txt","E:/VRelation_normal.txt");
		
	}
	//初始化用户是否是认证用户关系Map
	public static void initIdentifyRela(String path){
	 	   File	 file= new File(path);
	 	   LineIterator lineit=null;
	 	   		     try {
	 					 lineit=FileUtils.lineIterator(file);
	 						while(lineit.hasNext()){
	 						String line=lineit.nextLine();
	 						String[] arr=line.split(",");
	 						if(arr.length>4)
	 						Vrelation.put(arr[4], arr[0]);
	 						}
	 						
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}finally{
	 						
	 						   LineIterator.closeQuietly(lineit);
	 					}
		
	}
	public static void getVfriends(String path,String save){
		File	 file= new File(path);
		Utils utils=new Utils();
		Map<String,String> result=new HashMap<>();
	 	   LineIterator lineit=null;
	 	   int totalFriends=0;
	 	   int count=0;
	 	   int Vnums=0;
	 	   int countHaveVfriendNums=0;
	 	   		     try {
	 					 lineit=FileUtils.lineIterator(file);
	 						while(lineit.hasNext()){
	 							count++;
	 							StringBuilder strb=new StringBuilder();
	 						String line=lineit.nextLine();
	 						String[] arr=line.split(" ");
	 						totalFriends+=arr.length-1;
	 						for(int i=1;i<arr.length;i++){
	 							if(Vrelation.containsKey(arr[i])&&Vrelation.get(arr[i]).equals("true"))
	 							{
	 								Vnums++;
	 							//System.out.println(arr[i]);
	 							strb.append(arr[i]);
	 							strb.append(" ");
	 							}
	 						}
	 						result.put(arr[0], strb.toString());
	 						}
	 						System.out.println("平均朋友数"+totalFriends/count+"总共认证用户"+Vnums);
	 						for(String key:result.keySet()){
	 							if(!result.get(key).equals("")&&result.get(key)!=null)
	 								countHaveVfriendNums++;
	 						}
	 						System.out.println("有"+countHaveVfriendNums+"个用户具有认证用户的朋友");
	 						utils.saveMapandSaveKey(result, save);
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}finally{
	 						
	 						   LineIterator.closeQuietly(lineit);
	 					}
	}

}
