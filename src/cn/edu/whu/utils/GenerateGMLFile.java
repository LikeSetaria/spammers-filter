/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.mysql.jdbc.StringUtils;

/**
 * @author bczhang
 *此类用以生成gml文件
 *
 */
public class GenerateGMLFile {

	/**
	 * @param args
	 */
	static Utils utils=new Utils();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//得到无向图，边节点关系
		//patchUndirectedGraph("E:\\normal\\SecondPhase\\thirdExpandSample\\zbc_interaction_graphs\\","E:\\normal\\SecondPhase\\thirdExpandSample\\zbc_interaction_graphs_undirected\\");
		//patchUndirectedGraph("E:\\spam\\expandSample\\followee_graph\\","E:\\spam\\expandSample\\followee_graph_undirected\\");
		//patchHandle("E:/normal/3.1_graphFetures/interaction_graph_undirected/","E:/normal/3.1_graphFetures/gephi_gml/graph_interaction_undirected_gml/");
		patchHandle("E:\\normal\\SecondPhase\\thirdExpandSample\\zbc_followee_graphs\\","E:\\normal\\SecondPhase\\thirdExpandSample\\zbc_followee_graphs_gmlAAAA\\");
	}
	/** 
	 * @param relationFolderPath 关系文件夹，里面是用户边 的文件。文件夹路径要以/结尾
	 * @param gmlSaveFloderPath 生成的gml文件保存的位置,文件夹路径要以/结尾
	 */
	public static void patchHandle(String relationFolderPath,String gmlSaveFloderPath){
		//File folder=new File("E:/spam/3.1_graphFetures/graphs_friends/");
		//File folder=new File("E:/spam/120DataSet/120graphs_follows/");
		//File folder=new File("E:/spam//3.1_graphFetures/graphs_follows_undirected/");
		File folder=new File(relationFolderPath);
		String[] files=folder.list();
		for(String filepath:files){
			//getGraphFile(filepath,"E:/spam/3.1_graphFetures/gephi_gml/graphs_friends_gml/");
			//getGraphFile(filepath,"E:/spam/120DataSet/gephi_gml/graphs_follows_gml/");
			//getGraphFile(filepath,"E:/spam/3.1_graphFetures/gephi_gml/graphs_follows_gml_undirected/");
			getGraphFile(filepath,relationFolderPath,gmlSaveFloderPath);
			//System.out.println(filepath);
		}
	}
	/**
	 * 由一般的图得到gml格式通用的图格式
	 * @param fileName
	 * @param relationFolderPath
	 * @param savePath
	 */
	public static void getGraphFile(String fileName,String relationFolderPath,String savePath,String weightGraphPath){
		StringBuilder gmlStrb=new StringBuilder("");
		String path=relationFolderPath+fileName;
		String graphStr=utils.readFileToString(path);
		//得到边权重关系map，如交互关系图可能需要得到边的权重的，
		Map<String,Integer> weightedNumMap=new HashMap<>();
		String interaction_graph[]=utils.readFileToString(weightGraphPath+fileName).split("\n");
		for(String ss:interaction_graph){
			weightedNumMap.put(ss.trim(), weightedNumMap.get(ss.trim())==null?1:weightedNumMap.get(ss.trim())+1);
		}
		String[] uidarr=fileName.split(".txt");
		String uid=uidarr[0];
		if(graphStr.length()!=0){//过滤掉空文件
		String[] arr=graphStr.split("\\s");
		String[] arr2=graphStr.split("\n");
		Set<String> nodeSet=new HashSet<>();
		for(String ss:arr){
			if(!nodeSet.contains(ss.trim())){
				nodeSet.add(ss.trim());
			}
		}
		System.out.println("总共含有节点数:"+nodeSet.size());
		Iterator it=nodeSet.iterator();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date=df.format(new Date());// new Date()为获取当前系统时间
		gmlStrb.append("graph"+" "+"["+"\n");
		while(it.hasNext()){
			Object line=it.next();
			gmlStrb.append("  node"+" "+"["+"\n"+"    id "+line.toString()+"\n"+"    label "+line.toString());
			gmlStrb.append("\n");
			gmlStrb.append("  ]"+"\n");
			
		}
		//拼接边 过滤重复的边，
		Set<String> set=new HashSet<String>();
		for(String ss:arr2){
			String[] temp=ss.split(" ");
			if(!set.contains(ss.trim())){
			gmlStrb.append("  edge"+" ["+"\n"+"    source "+temp[0]);
			gmlStrb.append("\n"+"    target "+temp[1]);
			//gmlStrb.append("\n"+"   value 1");//关于是否生成有向图，如果是无向图，则不需要这个值
			gmlStrb.append("\n"+"   value "+weightedNumMap.get(temp[0]+" "+temp[1]));//扩展计算权重
			gmlStrb.append("\n");
			gmlStrb.append("  ]"+"\n");
			}
			set.add(ss.trim());
		}
		gmlStrb.append("]");
	// System.out.println(gmlStrb);
	 try {
		FileUtils.write(new File(savePath+uid+".gml"), gmlStrb);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }//end 过滤掉空文件
		else try {
			FileUtils.write(new File(savePath+uid+".gml"), gmlStrb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void getGraphFile(String fileName,String relationFolderPath,String savePath){
		StringBuilder gmlStrb=new StringBuilder("");
		//String path="E:/spam/120DataSet/120graphs_follows/"+fileName;
		String path=relationFolderPath+fileName;
		//String graphStr=utils.readFileToString("E:/spam/3.1_graphFetures/graphs_follows/1032153895.txt");
		String graphStr=utils.readFileToString(path);
		
		//得到边权重关系map，如交互关系图可能需要得到边的权重的，
		String[] uidarr=fileName.split(".txt");
		String uid=uidarr[0];
		if(graphStr.length()!=0){//过滤掉空文件
		String[] arr=graphStr.split("\\s");
		String[] arr2=graphStr.split("\n");
		Set<String> nodeSet=new HashSet<>();
		for(String ss:arr){
			if(!nodeSet.contains(ss.trim())){
				nodeSet.add(ss.trim());
			}
		}
		//System.out.println("总共含有节点数:"+nodeSet.size());
		Iterator it=nodeSet.iterator();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date=df.format(new Date());// new Date()为获取当前系统时间
	//	gmlStrb.append("Creator"+ " \"BcZhang on "+date+"\"");
	//	gmlStrb.append("\n");
		gmlStrb.append("graph"+" "+"["+"\n");
		//拼接节点
		while(it.hasNext()){
			Object line=it.next();
			
			if(StringUtils.isNullOrEmpty((String) line)){
				System.out.println("存在空单节点，已处理"+line);
				continue;
			}
			
			gmlStrb.append("  node"+" "+"["+"\n"+"    id "+line.toString()+"\n"+"    label "+line.toString());
			
			gmlStrb.append("\n");
			gmlStrb.append("  ]"+"\n");
			
		}
		//拼接边
		//过滤重复的边，
		Set<String> set=new HashSet<String>();
		for(String ss:arr2){
			String[] temp=ss.split(" ");
			if(temp[1].length()<3){//处理爬去的坏的节点，即有些节边只有一个节点的情况
				System.out.println("...存在单节点边，已处理"+ss);
				continue;
			}
			if(!set.contains(ss.trim())){
			gmlStrb.append("  edge"+" ["+"\n"+"    source "+temp[0]);
			gmlStrb.append("\n"+"    target "+temp[1]);
			gmlStrb.append("\n"+"   value 1");//关于是否生成有向图，如果是无向图，则不需要这个值
			gmlStrb.append("\n");
			gmlStrb.append("  ]"+"\n");
			}
			set.add(ss.trim());
		}
		gmlStrb.append("]");
	// System.out.println(gmlStrb);
	 try {
		FileUtils.write(new File(savePath+uid+".gml"), gmlStrb);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }//end 过滤掉空文件
		else try {
			FileUtils.write(new File(savePath+uid+".gml"), gmlStrb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 有向图转化为无向图，关键是去除冗余的边。
	 * 得到编表结对an
	 */
	public static void patchUndirectedGraph(String graphRelationFolderPath,String saveFolderPath){
		//File folder=new File("E:\\spam\\3.1_graphFetures\\graphs_follows\\");
		File folder=new File(graphRelationFolderPath);
		String [] files=folder.list();
		for(String ss:files){
			getUndirectedGraph(ss, graphRelationFolderPath,saveFolderPath);
		}
	}
	public static void getUndirectedGraph(String directedFileName,String graphRelationFolderPath,String saveFolderPath){
		try {
			//String text=FileUtils.readFileToString(new File("E:\\spam\\3.1_graphFetures\\graphs_follows\\"+directedFileName));
			String text=FileUtils.readFileToString(new File(graphRelationFolderPath+directedFileName));
			String arr[]=text.split("\n");
			Set<String> set=new HashSet<String>();
			StringBuilder strb=new StringBuilder("");
			if(text.length()!=0){
			for(String ss:arr){
				String [] arr2=ss.trim().split(" ");
				if(arr2.length<2){
					System.out.println("notice the file of"+directedFileName+"  broken edge of"+ss);
					continue;
				}
				String temp=arr2[1]+" "+arr2[0];//判断是否存在有向重复边，比较简单，就是把原图的边指向对调，加入到集合中，不重复的就可以
				set.add(temp);
				if(!set.contains(ss.trim())){
					strb.append(ss);
					strb.append("\n");
				}
				}
			//System.out.println(strb);
			//FileUtils.write(new File("E:\\spam\\3.1_graphFetures\\graphs_follows_undirected\\"+directedFileName), strb);
			FileUtils.write(new File(saveFolderPath+directedFileName), strb);
			}else
				FileUtils.write(new File(saveFolderPath+directedFileName), strb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}
