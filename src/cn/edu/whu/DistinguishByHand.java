/**
 * 
 */
package cn.edu.whu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import cn.edu.whu.spam.ProProcess;
import cn.edu.whu.utils.PreprocessText;
import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *人工识别样本初步筛选过滤
 */
public class DistinguishByHand {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//getSubtraction("D:\\人工赛选\\第一次筛选\\spam_manual_uids.txt","E:/spam/UID.txt","D:\\人工赛选\\第二次筛选\\spam2_manual_uids.txt");
		//getSubtraction("D:\\人工赛选\\第一次筛选\\筛选结果\\359normalUID.txt","E:/normal/UID.txt","D:\\人工赛选\\第二次筛选\\normal2_manual_uids.txt");
		//copyWeiboByUID("E:\\spam\\3_UltimateSelected\\weibos\\","D:\\人工赛选\\第二次筛选\\spam_potential_uid.txt","D:\\人工赛选\\第二次筛选\\spam_reduced_weibos\\");
		copyWeiboByUID("D:\\人工赛选\\第二次筛选\\normal_weibos\\","D:\\人工赛选\\第二次筛选\\normal_potential_uid.txt","D:\\人工赛选\\第二次筛选\\normal_reduced_weibos\\");
		//selectProfilesByUID("E:/spam/3_UltimateSelected/profiles.txt", "D:\\人工赛选\\第二次筛选\\spam2_manual_uids.txt", "D:\\人工赛选\\第二次筛选\\profiles.txt");
		//selectProfilesByUID("E:/normal/2_UltimateNormal/normal_profiles.txt", "D:\\人工赛选\\第二次筛选\\normal2_manual_uids.txt", "D:\\人工赛选\\第二次筛选\\normal_profiles.txt");
	}
   /**
    * 得到uid差集
    * uid文件以行为分割，每行一个uid
    */
	public static void getSubtraction(String set1Path,String set2Path,String subtractionPath){
		Utils utils=new Utils();
		Set<String> set1=new HashSet<>();
		Set<String> set2=new HashSet<>();
		set1=utils.readToSet(set1Path);
		set2=utils.readToSet(set2Path);
		if(set1.size()>set2.size()){
			System.out.println(set1Path+"     size"+set1.size());
			System.out.println(set2Path+"     size"+set2.size());
		for(String ss:set2){
			if(set1.contains(ss)){
				set1.remove(ss);
			}
		}
	
		System.out.println(subtractionPath+"  差集     size"+set2.size());
		utils.saveResultBySet(set1, subtractionPath);
		}else{
			System.out.println(set1Path+"     size"+set1.size());
			System.out.println(set2Path+"     size"+set2.size());
			for(String ss:set1){
				if(set2.contains(ss)){
					set2.remove(ss);
				}
			}
		
			System.out.println(subtractionPath+"  差集     size"+set2.size());
			utils.saveResultBySet(set2, subtractionPath);
		}
		
	}
	/**
	 * 根据UID选择复制微博到指定的文件夹
	 * @param weibosFolder
	 * @param UIDPath
	 * @param targetSavePath
	 */
	public static void copyWeiboByUID(String weibosFolder,String UIDPath,String targetSavePath){
		Utils utils=new Utils();
		File folder=new File(weibosFolder);
		String[] arr=folder.list();
		Set<String> set=new HashSet<>();
		set=utils.readToSet2(UIDPath);
		System.out.println(set.size());
		for(String a:arr){
			String[] sarr=a.split(".txt");
			
			if(set.contains(sarr[0])){
		  utils.copyFile(weibosFolder+a, targetSavePath+a);
		  System.out.println(sarr[0]);}
			}
	}
	/**
	 * 根据Id选择这部分用户的profile
	 */
	public static void selectProfilesByUID(String totalProfilesFilePath,String uidPath,String tragetSavePath){
		PreprocessText process=new PreprocessText();
		try {
		process.extractItemByUID(totalProfilesFilePath,uidPath,tragetSavePath);
		//process.extractItemByUID("E:/normal/2_UltimateNormal/profiles.txt", "E:/normal/random400uid.txt", "E:/normal/人工赛选/profiles.txt");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}
