/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;

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
		//patchUndirectedGraph();
		patchHandle();
	}
	public static void patchHandle(){
		//File folder=new File("E:/spam/3.1_graphFetures/graphs_friends/");
		//File folder=new File("E:/spam/120DataSet/120graphs_follows/");
		File folder=new File("E:/spam//3.1_graphFetures/graphs_follows_undirected/");
		String[] files=folder.list();
		for(String filepath:files){
			//getGraphFile(filepath,"E:/spam/3.1_graphFetures/gephi_gml/graphs_friends_gml/");
			//getGraphFile(filepath,"E:/spam/120DataSet/gephi_gml/graphs_follows_gml/");
			getGraphFile(filepath,"E:/spam/3.1_graphFetures/gephi_gml/graphs_follows_gml_undirected/");
			System.out.println(filepath);
		}
	}
	//
	public static void getGraphFile(String fileName,String savePath){
		StringBuilder gmlStrb=new StringBuilder();
		//String path="E:/spam/120DataSet/120graphs_follows/"+fileName;
		String path="E:/spam/3.1_graphFetures/graphs_follows_undirected/"+fileName;
		//String graphStr=utils.readFileToString("E:/spam/3.1_graphFetures/graphs_follows/1032153895.txt");
		String graphStr=utils.readFileToString(path);
		String[] uidarr=fileName.split(".txt");
		String uid=uidarr[0];
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
	//	gmlStrb.append("Creator"+ " \"BcZhang on "+date+"\"");
	//	gmlStrb.append("\n");
		gmlStrb.append("graph"+" "+"["+"\n");
		//拼接节点
		while(it.hasNext()){
			Object line=it.next();
			
			gmlStrb.append("  node"+" "+"["+"\n"+"    id "+line.toString()+"\n"+"    label "+line.toString());
			
			gmlStrb.append("\n");
			gmlStrb.append("  ]"+"\n");
			
		}
		//拼接边
		for(String ss:arr2){
			String[] temp=ss.split(" ");
			gmlStrb.append("  edge"+" ["+"\n"+"    source "+temp[0]);
			gmlStrb.append("\n"+"    target "+temp[1]);
//			gmlStrb.append("\n"+"   value 1");//关于是否生成有向图，如果是无向图，则不需要这个值
			gmlStrb.append("\n");
			gmlStrb.append("  ]"+"\n");
			 
		}
		gmlStrb.append("]");
	// System.out.println(gmlStrb);
	 try {
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
	public static void patchUndirectedGraph(){
		File folder=new File("E:\\spam\\3.1_graphFetures\\graphs_follows\\");
		String [] files=folder.list();
		for(String ss:files){
			getUndirectedGraph(ss);
		}
	}
	public static void getUndirectedGraph(String directedFileName){
		try {
			String text=FileUtils.readFileToString(new File("E:\\spam\\3.1_graphFetures\\graphs_follows\\"+directedFileName));
			String arr[]=text.split("\n");
			Set<String> set=new HashSet<String>();
			StringBuilder strb=new StringBuilder();
			for(String ss:arr){
				String [] arr2=ss.trim().split(" ");
				String temp=arr2[1]+" "+arr2[0];
				set.add(temp);
				if(!set.contains(ss.trim())){
					strb.append(ss);
					strb.append("\n");
				}
				}
			//System.out.println(strb);
			FileUtils.write(new File("E:\\spam\\3.1_graphFetures\\graphs_follows_undirected\\"+directedFileName), strb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
