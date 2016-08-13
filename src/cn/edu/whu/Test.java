/**
 * 
 */
package cn.edu.whu;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.dao.GFeatureDao;
import cn.edu.whu.daoImpl.GFeatureDaoImpl;
import cn.edu.whu.pojo.GFeature;
import cn.edu.whu.pojo.Relation;
import cn.edu.whu.pojo.User;
import cn.edu.whu.utils.PreprocessText;
import cn.edu.whu.utils.Utils;

/**
 * @author ����
 *���Է�����
 */
public class Test {
	 @SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		Utils utils=new Utils();
		new FileFilter();
		new Test();
		new PreprocessText();
		
		//����copyFile()����
		/*
		utils.copyFile("c:\\unintall.log", "d:\\unintall.log");
		*/
		
		
		//���Զ�ȡ���ļ�
		/*
		logger.info("���ļ�����");
	//	utils.readBigFile("D:\\Whuer\\Major\\weibo\\weibo_users.csv");
		*/
		//���Զ�ȡһ���ļ�
		//utils.readFile("e:test.txt");
		
		//System.out.println(utils.writeToFile("e:\\test.txt", "hhhhhh"));
		//System.out.println(utils.readFile("e:\\test.txt"));
		//filter.Filter_V("D:\\Whuer\\Major\\weibo\\weibo_users.txt");
		//filter.onlyChinese("D:/DOWNLOAD/BaiduYunDownload/weibo_users_name.txt","E:/temp/data/777.txt");
	 //String str ="丁为-V出现频次23";
//		// System.out.println(str.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]+", "#"));
//     	String[] temp= test.formGrams(str,1);
//		 for (String s:temp){
//			 System.out.println(s);
//		 }
//		Map<String,Double> map=new HashMap<String,Double>();
//		map.put("12",  3.2);
//		map.put("123",  7.2);
//		map.put("124",  20.);
//		map.put("125", 4.2);
//		map.put("126",  1.2);
//		//utils.sortMapByValue2(map).entrySet();
//	          for(Map.Entry<String, Double> ss:utils.sortMapByValue2(map).entrySet()){
//	        	  System.out.println(  ss.getKey() + "  " + ss.getValue());
//	          }
		//filter.selectUidByUserName("D:/Whuer/Major/weibo/RESULT/weibo_users.txt", "E:/temp/uid.txt", "D:/Whuer/Major/weibo/RESULT/BigramReaultsLenght_4.txt");
  // utils.readBigFile("F:/test/weibo_follows.csv");
		//process.extractPartOfRelation("D:/Whuer/FudanData/weibo_follows.csv", "E:/potential_spammers_4_uid_10w.txt","E:/result.txt");
		//utils.readBigFile("D:/Whuer/FudanData/weibo_follows.csv");
		//process.extractFollows(FOLLOWS_USER_TXT,FOLLOWS_USER_RESULTS);
		//process.extractFans("E:/users_25W_are_concered_rearhalf.txt","E:/ttrear.txt");
		//utils.copyFileByLine("E:/users_25W_are_concered.txt", "E:/users_25W_are_concered_rearhalf.txt", 10000000);
	//process.extractBoth("E:/users_his_fans_25W.txt","E:/relation_results_as_fans_25w.txt","F:coll.txt");
		//String path = "F:" + File.separator + "test"+File.separator+"weibo_follows.csv";    
	    //readFile1(path);  
//	    try {
//			readFile2(path);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
	   // readFile3(path); 
//	try {
//		process.extractItemByUID("F:/profiles.txt", "E:/relationUID.txt", "F:/profilestt.txt");
//		
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	//process.analyseProfile("e:/user_profiles.txt");
//test.compareFiles();
		// test.extractFid();
		//test.extractSort();
		//process.extractBoth("E:/spam/uidfollows.txt","E:/spam/uidfriends.txt","F:co.txt");
		//test.extraceEdge();
		//test.extractGender();
		//System.out.println(utils.readToSet("E:/spam/tt.txt"));
		//System.out.println(utils.calURL("erhttp:dfgdghsdfgdg的法国恢复的话www.dsgfs为广大http//"));
//		Set<String> result=new HashSet<String>();
//		result=utils.readToSet("E:/removeNormalUID.txt");
//		utils.saveResultBySet(result, "E:/spam/removeNormalUID.txt");

//		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}\"\"【】‘；：”“’。，、？]"; 
//		String str="xdher喜欢狗儿粉丝@ ！ # ￥……%&）    v “\"\"?？!&*日价格划算";
//		Pattern p = Pattern.compile(regEx); 
//		Matcher m = p.matcher(str);
//		m.replaceAll("").replaceAll("\\s", "");
//		System.out.println(str.replaceAll(regEx, "").replaceAll("\\s", ""));
//		LineIterator it=null;
//		Map<String,Double> map=new HashMap<>();
//		Set<String> set=new HashSet<>();
//		set=utils.readToSet("E:/spam/5_selectedFeatureVec/selectVec9.txt");
//		try {
//			//it = FileUtils.lineIterator(new File("E:/spam/5_selectedFeatureVec/selectVec8.1.txt"));
//			it = FileUtils.lineIterator(new File("E:/spam/5_selectedFeatureVec/selectVec8.txt"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//		    while (it.hasNext()) {
//		        String line = it.nextLine();
//		        String[] arr=line.split(" ");
//		        if(set.contains(arr[0]))
//		        map.put(arr[0], Double.valueOf(arr[7].equals(0)?"0.0":arr[7]));
//		    }
//		   StringBuilder str=new StringBuilder();
//	for(Map.Entry<String, Double> m:utils.sortMapByValue2(map).entrySet()){
//		System.out.println(m.getKey());
//		str.append(m.getKey());
//		str.append("\n");
//		
//	}
//	try {
//		FileUtils.write(new File("E:/spam/removeUIDBySimrate2.txt"), str.toString());
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		} finally {
//		    LineIterator.closeQuietly(it);
//		}		
		//test.extraceEdge();
		//test.modifyFuid();
//		try {
//			//process.extractItemByUID("E:/spam/3_UltimateSelected/profiles.txt", "E:/spam/manual_uids.txt", "E:/spam/人工赛选/profiles.txt");
//			process.extractItemByUID("E:/normal/2_UltimateNormal/profiles.txt", "E:/normal/random400uid.txt", "E:/normal/人工赛选/profiles.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		File folder=new File("E:\\normal\\2_UltimateNormal\\weibos");
//		String[] arr=folder.list();
//		Set<String> set=new HashSet<>();
//		set=utils.readToSet2("E:\\normal\\3.1_graphFetures\\359normalUID.txt");
//		System.out.println(set.size());
//		for(String a:arr){
//			String[] sarr=a.split(".txt");
//			
//			if(set.contains(sarr[0])){
//		  utils.copyFile("E:\\normal\\2_UltimateNormal\\weibos\\"+a, "D:/人工赛选/NormalWeibos/"+a);
//		  System.out.println(sarr[0]);}
//			}
		
//		
		//utils.saveResultBySet( utils.readToSet("E:\\normal\\3.1_graphFetures\\graph_metric_follows.txt"),"E:\\normal\\3.1_graphFetures\\359uid.txt");
		//utils.selectUIDF("E:\\normal\\3.1_graphFetures\\359normalUID.txt", "E:\\normal\\3.1_graphFetures\\9graph_metric_follows.txt", "E:\\normal\\3.1_graphFetures\\9graph_metric_follows2.txt");
//		File file=new File("I://人工赛选//weibos");
//		String[] arr=file.list();
//		StringBuilder strb=new StringBuilder();
//		for(String ss:arr){
//			String aa=ss.replace(".txt", "");
//			strb.append(aa.trim());
//			strb.append("\n");
//			
//		}
//		System.out.println(strb);
//		try {
//			FileUtils.write(new File("E:/spam/人工赛选/uid.txt"), strb.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(arr.length);
		
		
		//utils.selectUIDF("E:/spam/spam_both_selected.txt", "E:/spam/3_UltimateSelected/uidfollows_selected.txt", "E:/spam/3.1_graphFetures/341uidfriends.txt");
		//utils.selectUIDF("E:/normal/359normalUID.txt", "E:/normal/2_UltimateNormal/uidfollows.txt", "E:/normal/359uidfollows.txt");
		
		
		 //Set <String> setme=new HashSet<>();
		// Set <String> setshe=new HashSet<>();
	    //setme=utils.readToSet("D:/uidme.txt");
	   // setshe=utils.readToSet("D:/uidshe.txt");
		 //setme=utils.readToSet("E:/normal/UID.txt");
		// setshe=utils.readToSet("E:/spam/spammersManualUID.txt");
		 //int i=0;
		// StringBuilder strb=new StringBuilder();
		// for(String ss:setme){
			 
			
			// if(setshe.contains(ss.trim())){
			//	 strb.append(ss);
			//	 strb.append("\n");
				 
			//	 i++;
			// }
		// }
		 //System.out.println(strb);
		// utils.saveResultBySet(setme, "E:/normal/remove.txt");
//		 try {
//			
//			//FileUtils.write(new File("E:/normal/removeNormalUID.txt"), strb);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// System.out.println(i);
//		  
//		StringBuilder str=new StringBuilder();
//		LineIterator it=null;
//		Map<String,Double> map=new HashMap<>();
//		Set<String> set=new HashSet<>();
//		int i=0;
//		set=utils.readToSet("E:/normal/2_UltimateNormal/uidfriends.txt");
//		for(String sss:set){
//			str.append(sss.trim());
//			str.append("\n");
//			i++;
//			if(i==400)
//				break;
//		}
//	
//		try {
//			FileUtils.write(new File("E:/random400uid.txt"), str);
//			//it = FileUtils.lineIterator(new File("E:/spam/5_selectedFeatureVec/selectVec8.1.txt"));
//			it = FileUtils.lineIterator(new File("E:/spam/5_selectedFeatureVec/selectVec8.txt"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	//utils.selectUIDF("E:/normal/3.1_graphFetures/new27spam.txt", "E:/normal/3.1_graphFetures/graph_metric_friends.txt", "E:/normal/3.1_graphFetures/graph_metric_friends27.txt");
	//utils.removeUIDF("E:/normal/3.1_graphFetures/new27spam.txt", "E:/normal/3.1_graphFetures/graph_metric_follows400.txt", "E:/normal/3.1_graphFetures/graph_metric_follows373.txt");
//File folder=new File("F:/weibo/spam/ALLfriend"); 
//String[] arr=folder.list();
//System.out.println(arr.length);
//	String[] arr=folder.list();
//	Set<String> set=new HashSet<>();
//	for(String ss:arr){
//		String[] arr2=ss.split(".txt");
//		set.add(arr2[0].trim());
//		//System.out.println(arr2[0]);
//	}
//	utils.saveResultBySet(set, "E:/normal/人工赛选/myNormalUID373.txt");
//	System.out.println(arr.length);
		//utils.selectUIDF("F:\\赛选的渐变UID.txt", "E:\\spam\\3_UltimateSelected\\uidfriends_selected.txt", "F:\\uidfollows.txt");
		//getuid();
		//getAllFriendsUID();
//		try {
//			process.extractItemByUID("E:\\spam\\3_UltimateSelected\\profiles.txt", "F:\\赛选的渐变UID.txt", "F:/profilestt.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//utils.selectUIDF("E:/spam/3.1_graphFetures/341uid.txt", "E:\\spam\\3.1_graphFetures\\communityNums.txt", "E:\\spam\\3.1_graphFetures\\communityNums22.txt");
	//utils.mergeFeatures("E:\\normal\\normalSample\\interaction\\graph_interaction_feature.txt","E:\\normal\\normalSample\\interaction\\normal_interaction_networkX.txt",
				//"E:\\normal\\normalSample\\interaction\\12interaction_graphFeatures.txt");
	utils.mergeFeatures("E:\\normal\\normalSample\\interaction\\12interaction_graphFeatures.txt","E:\\normal\\normalSample\\featureVec\\12graph_plusRichClub.txt",
			"E:\\normal\\normalSample\\interaction\\interaction_neighbors_graphFeatures.txt");
//		utils.mergeFeatures("E:\\spam\\spamSample\\featureVec\\12graphFeatures.txt","E:\\spam\\spamSample\\featureVec\\spam_GraphFeature_rcc_avg.txt",
//				"E:\\spam\\spamSample\\featureVec\\12graph_plusRichClub.txt");
		//utils.mergeFeatures("E:\\normal\\normalSample\\featureVec\\selectVec_RemoveTimeAndSource.txt","E:\\normal\\normalSample\\featureVec\\graph_features_plus4New.txt",
				//"E:\\normal\\normalSample\\featureVec\\allFeatures_plus4NewGraph.txt");
		//utils.mergeFeatures("E:\\spam\\spamSample\\featureVec\\selectVec_RemoveTimeAndSource.txt","E:\\spam\\spamSample\\featureVec\\graph_features_plus4New.txt",
			//	"E:\\spam\\spamSample\\featureVec\\allFeatures_plus4NewGraph.txt");
		//calDee();
		//directedNodeEdgeNums();
		//communityNums();
		//remove();
		
			}
	 
	 public   static  void remove(){
		 try {
			 StringBuilder strb=new StringBuilder();
			String[] linearrspam=FileUtils.readFileToString(new File("E:\\normal\\normalSample\\featureVec\\12graph_plusRichClub.txt")).split("\n");
			for(String ss:linearrspam){
				String []arr=ss.split(" +");
				for(int i=0;i<arr.length;i++){
					if(i!=4){
						strb.append(arr[i]);
						strb.append(" ");
					}
					
				}
				strb.append("\n");
			}
			System.out.println(strb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void communityNums(){
		 try {
			String[] linearrspam=FileUtils.readFileToString(new File("E:\\spam\\3.1_graphFetures\\communityNums.txt")).split("\n");
			String[] linearrnormal=FileUtils.readFileToString(new File("E:\\normal\\3.1_graphFetures\\communityNums.txt")).split("\n");
			double countspam=0.0;
			double countnormal=0.0;
			for(String ss:linearrspam){
				String[] arr=ss.split(" ");
				countspam+=Double.valueOf(arr[1]);
				
			}
			for(String ss:linearrnormal){
				String[] arr=ss.split(" ");
				countnormal+=Double.valueOf(arr[1]);
				System.out.println(arr[1]);
			}
			System.out.println("spam: "+countspam/(double)linearrspam.length );
			System.out.println("normal: "+countnormal/(double)linearrnormal.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	public static void directedNodeEdgeNums(){
		try {
			int Nodecount=0;
			int edgeCount=0;   int filenum=0;
			Utils utils=new Utils();
			Set<String> spamuid=new HashSet<>();
			String basepath="E:\\spam\\3.1_graphFetures\\gephi_gml\\graphs_follows_gml\\";
			 spamuid=utils.readToSet( "E:\\spam\\3.1_graphFetures\\341uid.txt");
			String[] patharr=new File("E:\\spam\\3.1_graphFetures\\gephi_gml\\graphs_follows_gml\\").list();
			for(String filename:patharr){
				String[] getuid=filename.split(".gml");
				//System.out.println(getuid[0]);
			if(spamuid.contains(getuid[0].trim())){
				filenum++;
			String[] linearrspam=FileUtils.readFileToString(new File(basepath+filename)).split("\n");
			for(String ss:linearrspam)
			   {
			   if(ss.contains("node"))
				   Nodecount++;
			   if(ss.contains("edge"))
				   edgeCount++;
			    }
			     }
			
			}
			System.out.println(filenum);
			System.out.println(Nodecount);
			 System.out.println(edgeCount);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static  void calDee(){
		try {
			int followeeCountSpam=0;
			int followeeEdgeSpam=0;
			int followeeCountNormal=0;
			int followeeEdgeNormal=0;
			List<Integer> listspam=new ArrayList<>();
			List<Integer> listnormal=new ArrayList<>();
			List<Integer> listnormalEDGE=new ArrayList<>();
			List<Integer> listspamEDGE=new ArrayList<>();
			
			String[] linearrspam=FileUtils.readFileToString(new File("C:\\Users\\bczhang\\Desktop\\yq\\edge_node_spam341.txt")).split("\n");
			String[] linearrnormal=FileUtils.readFileToString(new File("C:\\Users\\bczhang\\Desktop\\yq\\edge_node_normall359.txt")).split("\n");
			for(String sss:linearrspam){
				String[] arr=sss.split(" ");
				listspam.add(Integer.valueOf(arr[1].trim()));
				listspamEDGE.add(Integer.valueOf(arr[2].trim()));
				if(followeeCountSpam<Integer.valueOf(arr[1].trim()))
				followeeCountSpam=Integer.valueOf(arr[1].trim());
				//followeeEdgeSpam+=Integer.valueOf(arr[2].trim());
			}
			for(String ss:linearrnormal){
				String[] arr=ss.split(" ");
				
				listnormal.add(Integer.valueOf(arr[1].trim()));
				listnormalEDGE.add(Integer.valueOf(arr[2].trim()));
				if(followeeCountNormal<Integer.valueOf(arr[1].trim()))
				followeeCountNormal=Integer.valueOf(arr[1].trim());
				//followeeEdgeNormal+=Integer.valueOf(arr[2].trim());
			}
			Collections.sort(listspam, new Comparator<Integer>() {
	            public int compare(Integer arg0, Integer arg1) {
	                return -arg0.compareTo(arg1);
	            }
	        });
			Collections.sort(listnormal, new Comparator<Integer>() {
	            public int compare(Integer arg0, Integer arg1) {
	                return -arg0.compareTo(arg1);
	            }
	        });
			Collections.sort(listspamEDGE, new Comparator<Integer>() {
	            public int compare(Integer arg0, Integer arg1) {
	                return -arg0.compareTo(arg1);
	            }
	        });
			Collections.sort(listnormalEDGE, new Comparator<Integer>() {
	            public int compare(Integer arg0, Integer arg1) {
	                return -arg0.compareTo(arg1);
	            }
	        });
			for(int i=0;i<listnormal.size();i++){
			//System.out.println(listnormal.get(i)+" "+listnormalEDGE.get(i));
			System.out.println(listnormalEDGE.get(i));
			//System.out.println(listnormal);
			}
			System.out.println(followeeCountSpam+" "+followeeEdgeSpam);
			System.out.println(followeeCountNormal+" "+followeeEdgeNormal);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static  void getChangingUID() throws IOException{
		String str1=FileUtils.readFileToString(new File("F:\\Liuuid.txt"));
		String str2=FileUtils.readFileToString(new File( "F:\\uidGuo.txt"));
		Set<String> set1=new HashSet<>();
		Set<String> set2=new HashSet<>();
		
		String[] arr1=str1.split("\n");
		String[] arr2=str2.split("\n");
		for(String ss:arr1){
			String [] arr=ss.trim().split(" ");
			//System.out.println(arr[1]);
			if(Integer.valueOf(arr[1])==1||arr[1]=="1")
				set1.add(arr[0].trim());
			
		}
		for(String ss:arr2){
			String [] arr=ss.trim().split("\\s");
	 // System.out.println(arr[0]);
			if(arr.length>1)
				set2.add(arr[0].trim());
			
		}
		for(String s:set1){
			if(set2.contains(s.trim())){
				System.out.println(s);
			}
		}
		//System.out.println(set2.size()+"set1"+set1.size());
	}
	
	public static  void getAllFriendsUID(){
		Utils utils=new Utils();
		Set<String> set=new HashSet<>();
		//String friendUIDs=utils.readFileToString("E:/spam/3.1_graphFetures/341uidfriends.txt");
		String friendUIDs=utils.readFileToString("E:/normal/359uidfollows.txt");
		String[] uidArr=friendUIDs.split("\\s");
		for(String ss:uidArr){
			set.add(ss.trim());
		}
		utils.saveResultBySet(set, "E:/normal/359uidFollowsSet.txt");
		System.out.println(uidArr.length);
	}
	
	
	//合并筛选的普通用户

	public static  void getuid(){
		Utils utils=new Utils();
		Set<String>zhenniSet=new HashSet<>();
		Set<String>MeSet=new HashSet<>();
		Set<String>result=new HashSet<>();
		Set<String> liubeiset=new HashSet<>();
	   MeSet=utils.readToSet("D:/uidMeNormal.txt");
		String str1=utils.readFileToString("D:/random400uid.txt");
		String str2=utils.readFileToString("D:/uidLiuBeiNormal.txt");
		String[] arr1=str1.split("\n");
		String[] arr2=str2.split("\n");
		for(String ss:arr1){
			String[] temp=ss.split(" ");
			//System.out.println(temp[1]);
			if(temp[1].equals("是")||temp[1]=="是")
			zhenniSet.add(temp[0]);
		}
	
		for(String ss:arr2){
			String[] temp2=ss.split("\\s");
			if(temp2[1].replace("\\s", "").trim().contains("1")){
			    //System.out.println(temp2[0]);
			    liubeiset.add( temp2[0]);
			}
		
			
		}
		for(String set:MeSet){
			int count=1;
			if(zhenniSet.contains(set.trim()))
				count++;
			if(liubeiset.contains(set.trim()))
				count++;
			if(count>=2){
				result.add(set.trim());
			System.out.println(set);
			}
			}
		//utils.saveResultBySet(zhenniSet, "D:uidZhenNiNormal.txt");
		//utils.saveResultBySet(liubeiset, "D:uidliubei.txt");
		
//		System.out.println(zhenniSet.size()+"result de dax "+result.size());
//		
//		File folder=new File("D:/weibo");
//		String[] list=folder.list();
//		String path="D:/weibo/";
//		for(String fileName:list){
//			//System.out.println(path+fileName);
//		  String name[]=	fileName.split(".txt");
//			String text[]=utils.readFileToString(path+fileName).split("\n");
//			String line[]=text[0].split("\\s");
//			if(line[0].replace("\\s", "").trim().equals("1")){
//			//System.out.println(line[0].trim().equals(1));
//			System.out.println(name[0]+  " "+line[0].trim().replace("\\s", ""));
//			}
//		}
	 
	}
	/**
	 * 得到无向图
	 */
	public static void getUndirectedGML(){
		String folderPath="E:\\normal\\3.1_graphFetures\\gephi_gml\\graphs_follows_gml\\";
		String newfolderPath="E:\\normal\\3.1_graphFetures\\gephi_gml\\graphs_follows_gml\\undirected\\";
		File folder =new File(folderPath);
		String [] arr=folder.list();
		
		for(String ss:arr){
			try {
				String text=FileUtils.readFileToString(new File(folderPath+ss));
				String  newtext=text.replace("\n"+"   "+"value 1", "");
				 
				  FileUtils.write(new File(newfolderPath+ss), newtext);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//System.out.println(folderPath+ss);
		}
		  
		
	}
	/**
	 * spam fuid列表中关注出现重复现象，
	 */
		
	public void modifyFuid(){
		Set<String> set;
		try {
			LineIterator it=FileUtils.lineIterator(new File("E:/spam/3_UltimateSelected/uidfriends_selected.txt"));
			StringBuilder strb=new StringBuilder();
			while(it.hasNext()){
				
				String line=it.nextLine();
				String [] arr=line.split(" ");
				set=new HashSet<String>();
				strb.append(arr[0]);
				strb.append(" ");
				for(int i=1;i<arr.length;i++){
					System.out.println(arr.length-1);
					set.add(arr[i]);
					System.out.println("set"+set.size());
				}
				for(String s:set){
					strb.append(s.trim());
					strb.append(" ");
				}
				strb.append("\n");
			}
		FileUtils.write(new File("E:/spam/3_UltimateSelected/uidfriend_modefy.txt"), strb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	  	public void extractFid(){
	  		File file=new File("E:/mute_fake/UidInfo_friends1.txt");
	  		File save=new File("E:/mute_fake/REs/UidInfo_friends1_fuid.txt");
	  		LineIterator it=null;
	  		FileWriter fw=null;
	  		 try {
				fw = new FileWriter(save,true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	  		try {
				it=FileUtils.lineIterator(file);
				String line;
			    String regEx = "[^0-9]";
			    String regEx2="\\s{1,}";
			    Long totalFriends=0l;
			    
				while(it.hasNext()){
					StringBuilder strb=new StringBuilder();
					line=it.nextLine();
					line=line.replaceAll(regEx, " ");
					//line=line.replaceAll(regEx2, " ");
					String[] arr=line.split(regEx2);
					StringBuilder temp=new StringBuilder();
					totalFriends=Integer.valueOf(arr[2])+totalFriends;
					for(int i=0;i<arr.length;i++){
						
						if(i!=0&&i!=2){
					temp.append(arr[i]);
						temp.append(" ");
						}
					}
					strb.append(temp);
					strb.append("\n");
					fw.write(strb.toString());
					
					
				}
				fw.flush();
				System.out.println(totalFriends);
				//FileUtils.writeStringToFile(save, strb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		 if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  		
	  	}
	/**
	 * 排序fid
	 */
	public void extractSort(){
		File file=new File("E:/mute_fake/REs/UidInfo_follows1_fuidqqq.txt");
		File save=new File("E:/mute_fake/REs/UidInfo_follows1_fuidSortqqq.txt");
		//统计不重复的
		Set<String> unRepeatFset=new HashSet<String>();
		FileWriter fw=null;
		LineIterator it=null;
		try {
			fw= new FileWriter(save,true);
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				Set<String> set=new TreeSet<String>();
				
				StringBuilder strb=new StringBuilder();
				line=it.nextLine();
				String[] arr=line.split(" ");
				for(int i=1;i<arr.length;i++){
					set.add(arr[i]);
				}
				//把set中的再拼接，逐行保存
				unRepeatFset.addAll(set);
				strb.append(arr[0]);
				strb.append(" ");
				for(String ss:set){
					strb.append(ss);
					strb.append(" ");
				}
				strb.append("\n");
				fw.write(strb.toString());
			}
			System.out.println(unRepeatFset.size());
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fw!=null)
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	/**
	 * 抽取边
	 */
	public void extraceEdge(){
//		File file=new File("E:/mute_fake/REs/UidInfo_follows1_fuid.txt");
//		File save=new File("E:/mute_fake/REs/UidInfo_follows1_fuidEdge.txt");
		File file=new File("E:/spam/3_UltimateSelected/uidfriends_selected.txt");
		File save=new File("E:/spam/3_UltimateSelected/uidfriends_edge.txt");
		FileWriter fw=null;
		LineIterator it=null;
		try {
			fw= new FileWriter(save,true);
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				 line =it.nextLine();
				 String[] arr=line.trim().split(" ");
				 StringBuilder strb=new StringBuilder();
				 for(int i=1;i<arr.length;i++){
					 if(!arr[i].equals("")&&arr[i]!=null){
					 strb.append(arr[0]);
					 strb.append(" ");
					 strb.append(arr[i]);
					 strb.append("\n");
					 }
				 }
				// System.out.println(strb.toString());
				 fw.write(strb.toString());
				 
			}
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fw!=null)
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void extractGender(){
		File file=new File("E:/mute_fake/UserInfo0.txt");
		File save=new File("E:/mute_fake/REs/UserInfo0_gender.txt");
		FileWriter fw=null;
		LineIterator it=null;
		try {
			fw= new FileWriter(save,true);
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				 line =it.nextLine();
				 String[] arr=line.split("\"gender\":");
				 String str=arr[1];
				 String[] arr2=str.split("\"lang\"");
				 String str2=arr2[0];
				 str2=str2.replaceAll("\"id\":\"", "");
				 str2=str2.replaceAll("\"","" );
				 String [] last=str2.split(",");
				 
				 //System.out.println(last[1]+" "+last[0]);
				 fw.write(last[1]+" "+last[0]);
				 fw.write("\n");
				 
			}
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fw!=null)
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	
	public void compareFiles(){
		File file1=new File("F:/UIDOrderByfollows.txt");
		File file2=new File("E:/UserNotExist_tmp.txt");
		LineIterator it1=null;
		LineIterator it2=null;
		Set<String> uidSet=new HashSet<String>();
		Map<String ,User> map=new TreeMap<String,User>();
		Map<String ,User> result=new TreeMap<String,User>();
		try {
			it1=FileUtils.lineIterator(file1);
			it2=FileUtils.lineIterator(file2);
			String line;
			User u;
			BigDecimal   b   =   null;  
			while(it1.hasNext()){
				line=it1.nextLine();
				u=new User();
				String[] arr=line.split(" ");
			    u.setUID(arr[0]);
			   // System.out.println(arr[2]);
			    u.setFollowNums(Integer.valueOf(arr[2].trim()).intValue());
			    u.setFriendNums(Integer.valueOf(arr[1]).intValue());
			    double d=Double.valueOf(arr[1]).doubleValue()/Double.valueOf(arr[2]).doubleValue();
			    b=new BigDecimal(d);
			    double   f1   =   b.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
			    u.setFriDivFolRate(f1);
				map.put(arr[0], u);
				uidSet.add(line);
			}
			PreprocessText p=new PreprocessText();
			
			String line2;
			while(it2.hasNext()){
				line2=it2.nextLine();
				if(map.containsKey(line2)){
					//System.out.println(map.get(line2));
					result.put(line2, map.get(line2));
				}
			}
			result=p.sortMapByValue(result);
			Iterator<?> it =result.entrySet().iterator();
			 while(it.hasNext()){
				 Map.Entry entry=(Map.Entry) it.next();
	        System.out.println(entry.getValue());
	        	
	         }
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			LineIterator.closeQuietly(it1);
			LineIterator.closeQuietly(it2);
			}
	
	}
     
   public static void readFile1(String path) {   
    long start = System.currentTimeMillis();//开始时间  
       File file = new File(path);    
       if (file.isFile()) {    
           BufferedReader bufferedReader = null;    
           FileReader fileReader = null;    
           try {    
               fileReader = new FileReader(file);    
               bufferedReader = new BufferedReader(fileReader);    
               String line = bufferedReader.readLine();    
               System.out.println("========================== 传统IO读取数据，使用虚拟机堆内存 ==========================");    
               while (line != null) { //按行读数据  
                  // System.out.println(line);    
                   line = bufferedReader.readLine();    
               }    
           } catch (FileNotFoundException e) {    
               e.printStackTrace();    
           } catch (IOException e) {    
               e.printStackTrace();    
           } finally {    
            //最后一定要关闭  
               try {    
                   fileReader.close();    
                   bufferedReader.close();    
               } catch (IOException e) {    
                   e.printStackTrace();    
               }    
               long end = System.currentTimeMillis();//结束时间  
               System.out.println("传统IO读取数据，不指定缓冲区大小，总共耗时："+(end - start)+"ms");  
           }    
   
       }    
   }   
   /**  
    * 传统IO读取数据,指定缓冲区大小 
    * @author linbingwen  
    * @since  2015年9月5日   
    * @param path  
    * @return  
    * @throws FileNotFoundException  
    */    
public static void readFile2(String path) throws FileNotFoundException {  
    long start = System.currentTimeMillis();//开始时间  
    int bufSize = 1024 * 1024 * 5;//5M缓冲区  
    File fin = new File(path); // 文件大小200M  
    FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();  
    ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);                        
    String enterStr = "\n";  
    try {  
        byte[] bs = new byte[bufSize];  
        String tempString = null;  
        while (fcin.read(rBuffer) != -1) {//每次读5M到缓冲区  
            int rSize = rBuffer.position();  
            rBuffer.rewind();  
            rBuffer.get(bs);//将缓冲区数据读到数组中  
            rBuffer.clear();//清除缓冲  
            tempString = new String(bs, 0, rSize);  
            int fromIndex = 0;//缓冲区起始  
            int endIndex = 0;//缓冲区结束  
            //按行读缓冲区数据  
            while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {  
                tempString.substring(fromIndex, endIndex);
               // System.out.print(line);                    
                fromIndex = endIndex + 1;  
            }  
        }  
           long end = System.currentTimeMillis();//结束时间  
           System.out.println("传统IO读取数据,指定缓冲区大小，总共耗时："+(end - start)+"ms");  
  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  
}  
public static void readFile3(String path) {  
    long start = System.currentTimeMillis();//开始时间  
    long fileLength = 0;    
    final int BUFFER_SIZE = 0x300000;// 3M的缓冲    
    File file = new File(path);    
    fileLength = file.length();    
        try {    
            MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, fileLength);// 读取大文件    

            byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容    

            for (int offset = 0; offset < fileLength; offset += BUFFER_SIZE) {    
                if (fileLength - offset >= BUFFER_SIZE) {    
                    for (int i = 0; i < BUFFER_SIZE; i++)    
                        dst[i] = inputBuffer.get(offset + i);    
                } else {    
                    for (int i = 0; i < fileLength - offset; i++)    
                        dst[i] = inputBuffer.get(offset + i);    
                }    
                // 将得到的3M内容给Scanner，这里的XXX是指Scanner解析的分隔符    
                Scanner scan = new Scanner(new ByteArrayInputStream(dst)).useDelimiter(" ");    
                while (scan.hasNext()) {    
                    // 这里为对读取文本解析的方法    
                  //  System.out.print(scan.next() + " ");    
                }    
                scan.close();    
            }    
            System.out.println();  
            long end = System.currentTimeMillis();//结束时间  
            System.out.println("NIO 内存映射读大文件，总共耗时："+(end - start)+"ms");  
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
}   

}
