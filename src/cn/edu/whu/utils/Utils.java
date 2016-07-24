/**
 * 
 */
package cn.edu.whu.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

 
/**
 * @author 宝超
 *工具类
 */
public class Utils {
	  /**
     * 根据路径取出文件列表
     * @param path
     * @return
     */
     public   Map<String, File> getFileList(String folderPath)
     {
    	 Map<String, File> map=new TreeMap<>();
    	 File file=new File(folderPath);
    	 int i=0;
    	 if(file.isDirectory()){
    		 File [] f=file.listFiles();
    		 for(File tempFile :f){
    			 if (tempFile.isFile()) {
    				 String fileName=tempFile.getName();
    				// String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
    				 map.put(fileName,tempFile);
    				 //i++;
					
				}
    		 }
    	 }
		return map;
    	 
     }
     /**
      * 读取一般文件（文件大小一般小于1Gb大小）的内容
      * 利用 org.apache.commons.io.FileUtils快速读写文件
      * @return 把文件返回为字符串
      */
     public   String readFileToString(String filePath)  {
    	 File file =new File(filePath);
    	 String fileContent="";
    	 try {
			fileContent=org.apache.commons.io.FileUtils.readFileToString(file,"utf-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return fileContent;
     }
     /**
      * 读取大文件（大于1Gb大小的文件）的内容
      * 使用apache commons包中的IO工具类读取大文件
      * 在方法中需要进行相应的业务处理
      */
     public    void readBigFile(String filePath){
    	 File file=new File(filePath);
    	 LineIterator it=null;
    	 int i=0;
    	 StringBuilder str2=new StringBuilder();
			  try {
				it = FileUtils.lineIterator(file);
				while(it.hasNext()){
					i++;
					
					str2=new StringBuilder(it.nextLine());
					String[] arr=str2.toString().split(",");
					if(arr[0].equals("2506976754")){

						System.out.println("找到有这样的2506976754是粉丝，有关注人"+"一共有行："+i);
					
					}
					
					// System.out.println(line);
					//。。。。对每一行进行相应操作
					 
				}
				System.out.println("没有找到2506976754是粉丝的记录"+"一共有行："+i);
					 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} finally{
			   LineIterator.closeQuietly(it);
		}
    	 return ;
     }
     
     /**
      * 换行，追加字符串到文件尾部
     * @return 
      */
     public   boolean writeToFile(  String filePath ,String content){
    	  File file=new File(filePath);
    	  try { 
    	   org.apache.commons.io.FileUtils.writeStringToFile(file, content, "utf-8"); 
    	  } catch (IOException e) { 
    	   e.printStackTrace(); 
    	  } 
    	 return true;
     }
     /**
      * 复制单个文件
      * @param fileFrom String 原文件路径 如：c:/abc.txt
      * @param fileTo String 复制后路径 如：d:/abc.txt
      * @return boolean
      */
     public   boolean copyFile(String fileFrom,String fileTo){
    	 try{
    		 FileInputStream in =new java.io.FileInputStream(fileFrom);
    		 File file=new File(fileTo);//如果目标路径不存在则创建目录
    		 if(!file.exists()){
    			 file.getParentFile().mkdirs();
    		 }
    		 FileOutputStream out =new FileOutputStream(fileTo);
    		 byte [] bt =new byte[1024];
    		 int count;
    		 while((count=in.read(bt))>0){
    			 out.write(bt, 0, count);
    			 
    		 }
    		 in.close();
    		 out.close();
    		 return true;
    	 }catch(IOException ex){
    	 return false;
    	 }
     }
     /**
      * 以行为单位复制文件
      * 
      */
     @SuppressWarnings("resource")
	public void copyFileByLine(String filefrom,String fileto,int copyLineNum){
    	 File filef=new File(filefrom);
    	 LineIterator iterf =null;  
    	 StringBuilder strb=new StringBuilder();
    	 FileWriter filewriter=null;
    	 File savefile=new File(fileto);
  	     PrintWriter pw=null; 
  	     int count=0;
    	 try {
    		 filewriter=new FileWriter(savefile,true);
    	 		pw=new PrintWriter(filewriter);
			iterf=FileUtils.lineIterator(filef);
			while(iterf.hasNext()){
				count++;
				strb=new StringBuilder(iterf.nextLine());
				
				
				pw.println(strb);
				if(count==copyLineNum)
					break;
				
			}
			pw.flush();
			filewriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
     /**
      * 删除文件
      */
     public   void deleteFile(String filePath) {
         File file = new File(filePath);
         if (file.exists()) {
             file.delete();
         }
     }

     public   void deleteByFolder(String folderPath) {
         try {
             deleteAllFile(folderPath); //删除完里面所有内容
             String filePath = folderPath;
             filePath = filePath.toString();
             java.io.File myFilePath = new java.io.File(filePath);
             myFilePath.delete(); //删除空文件夹
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     public   boolean deleteAllFile(String path) {
         boolean flag = false;
         File file = new File(path);
         if (!file.exists()) {
             return flag;
         }
         if (!file.isDirectory()) {
             return flag;
         }
         String[] tempList = file.list();
         File temp = null;
         for (int i = 0; i < tempList.length; i++) {
             if (path.endsWith(File.separator)) {
                 temp = new File(path + tempList[i]);
             } else {
                 temp = new File(path + File.separator + tempList[i]);
             }
             if (temp.isFile()) {
                 temp.delete();
             }
             if (temp.isDirectory()) {
                 deleteAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                 deleteByFolder(path + "/" + tempList[i]);//再删除空文件夹
                 flag = true;
             }
         }
         return flag;
     }
     /**
      *超大文件切分成N份小文件。
      *将一个行数为fileLines的文本文件平均分为splitNum个小文本文件，其中换行符'r'是linux上的，windows的java换行符是'\r\n'：
      *@author bczhang
      *@param filePath 待处理文件位置
      *@param splitNum要切割的小文件个数
      *@param splitLines 文件的总共的行数
      */
     public void fileSpilt(String filePath,String outputFolderPath,int splitNums,int fileTotalLines){
    	 int bufferSize = 20 * 1024 * 1024;//设读取文件的缓存为20MB   
         //建立缓冲文本输入流   
         File file = new File(filePath);     
         int splitNum = splitNums;//要分割的块数减一   
         int fileLines = fileTotalLines;//输入文件的行数   
         long perSplitLines = fileLines / splitNum;//每个块的行数   
         try{
        	 FileInputStream fileInputStream = new FileInputStream(file);  
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);  
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);  
             BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);  
         for (int i = 0; i <= splitNum; ++i)  
         {  
             //分割   
             //每个块建立一个输出   
             FileWriter output = new FileWriter(outputFolderPath+ i + ".txt");  
             String line = null;  
             //逐行读取，逐行输出   
             for (long lineCounter = 0; lineCounter < perSplitLines && (line = input.readLine()) != null; ++lineCounter)  
             {  
                 output.append(line + "\r\n");  
             }  
             output.flush();  
             output.close();  
             output = null;  
         }  
         input.close(); }
         catch (IOException e){
        	 e.printStackTrace();
         }
          
       
     }  
     /**
      *超大文件切分成N份小文件。
      *将一个行数为fileLines的文本文件平均分为splitNum个小文本文件，其中换行符'r'是linux上的，windows的java换行符是'\r\n'：
      *@author bczhang
      *@param filePath 待处理文件位置
      *@param splitNum要切割的小文件个数
      *@param splitLines 文件的总共的行数
      */
     public void fileSpilt(String filePath,String outputFolderPath,int splitNums){
    	 int bufferSize = 20 * 1024 * 1024;//设读取文件的缓存为20MB   
         //建立缓冲文本输入流   
         File file = new File(filePath);     
         int splitNum = splitNums;//要分割的块数减一   
         int fileLines=0;
	
			fileLines = getFileLineNums(filePath);
		
		
         long perSplitLines = fileLines / splitNum;//每个块的行数   
         try{
        	 FileInputStream fileInputStream = new FileInputStream(file);  
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);  
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);  
             BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);  
         for (int i = 0; i <= splitNum; ++i)  
         {  
             //分割   
             //每个块建立一个输出   
             FileWriter output = new FileWriter(outputFolderPath+ i + ".txt");  
             String line = null;  
             //逐行读取，逐行输出   
             for (long lineCounter = 0; lineCounter < perSplitLines && (line = input.readLine()) != null; ++lineCounter)  
             {  
                 output.append(line + "\r\n");  
             }  
             output.flush();  
             output.close();  
             output = null;  
         }  
         input.close(); }
         catch (IOException e){
        	 e.printStackTrace();
         }
          
       
     }  
 
     /**
      * 得到文件的行数
     * @throws IOException 
      */
     public int getFileLineNums(String filePath){
    	 File file=new File(filePath);
    	 int couny=0;
    	 LineIterator it=null;
    	 try {
			it=FileUtils.lineIterator(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 while(it.hasNext()){
    		 couny++;
    	 }
    	 return couny;
     }
     /**保存链表到磁盘
      *@author bczhang
      *@param d 待保存链表
      * @param saveFilePath 保存到的目录地址
      */
     public void saveResultByList(List<String> d,String saveFilePath){
     	File file=new File(saveFilePath);
     	StringBuffer str=new StringBuffer();
     	int cou=0;
     	for(String res:d){
     		cou++;
     		str.append(res.toString());
     		//if(cou%10==0)
     			str.append("\n");
     	}
     	String content=str.toString();
     	try {
 			org.apache.commons.io.FileUtils.writeStringToFile(file, content, "utf-8");
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
     	
     	
     }
     /**保存链表到磁盘
      *@author bczhang
      *@param d 待保存链表
      * @param saveFilePath 保存到的目录地址
      */
     public void saveResultBySet(Set<String> d,String saveFilePath){
     	File file=new File(saveFilePath);
     	StringBuffer str=new StringBuffer();
     	int cou=0;
     	for(String res:d){
     		
     		str.append(res.toString());
     		//if(cou%10==0)
     			str.append("\n");
     			cou++;
     			
     	}
     	String content=str.toString();
     	try {
 			org.apache.commons.io.FileUtils.writeStringToFile(file, content, "utf-8");
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
     	
     	
     }
     /**保存Map数据到磁盘
      *@author bczhang
      *@param d 待保存map
      * @param saveFilePath 保存到的目录地址
      */
     public   void saveResultByHashMap( Map<String,Integer> map,String saveFilePath){
    	 File file=new File(saveFilePath);
      	 StringBuffer str=new StringBuffer();
      	 int cou=0;
         Iterator<?> it =map.entrySet().iterator();
         while(it.hasNext()){
        	 cou++;
        	 @SuppressWarnings("rawtypes")
			Map.Entry entry=(Map.Entry) it.next();
        	 Object key=entry.getKey();
        	 str.append(key.toString()+" ");
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
     /**保存Map数据到磁盘
      *@author bczhang
      *@param d 待保存map
      * @param saveFilePath 保存到的目录地址
      */
     public   void saveMap( Map<String,String> map,String saveFilePath){
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
     /**保存Map数据到磁盘
      *@author bczhang
      *@param d 待保存map
      * @param saveFilePath 保存到的目录地址
      */
     public   void saveMapInteger( Map<Integer,String> map,String saveFilePath){
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
     /**保存Map数据到磁盘
      *@author bczhang
      *@param d 待保存map
      * @param saveFilePath 保存到的目录地址
      */
     public   void saveMapandSaveKey( Map<String,String> map,String saveFilePath){
    	 File file=new File(saveFilePath);
      	 StringBuffer str=new StringBuffer();
      	 int cou=0;
         Iterator<?> it =map.entrySet().iterator();
         while(it.hasNext()){
        	 cou++;
        	 @SuppressWarnings("rawtypes")
			Map.Entry entry=(Map.Entry) it.next();
        	 Object key=entry.getKey();
        	 str.append(key.toString()+" ");
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
      * 对HashMap根据value进行排序
      * @return HashMap
      * @param HashMap
      */
     public   Map<String,Integer> sortMapByValue(Map<String,Integer> map ){
    	 
    	 ArrayList<Map.Entry<String ,Integer>> list=new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
    	 Collections.sort(list,new Comparator<Map.Entry<String ,Integer>>(){    		 
			@Override
			public int compare(java.util.Map.Entry<String, Integer> arg0, java.util.Map.Entry<String, Integer> arg1) {
				// TODO Auto-generated method stub
				//在这里调整降序还是升序
				return arg1.getValue() - arg0.getValue();  
			}    		 
    	 });
    	    HashMap<String ,Integer> newMap = new LinkedHashMap<String,Integer>();  
            for (int i = 0; i < list.size(); i++) {  
                newMap.put(list.get(i).getKey(), list.get(i).getValue());  
            }  
            return newMap; 
     }
     /**
      * 对HashMap根据value进行排序
      * @return HashMap
      * @param HashMap
      */
     public    Map<String,Double> sortMapByValue2(Map<String,Double> map ){
    	 
    	  List<Map.Entry<String ,Double>> list=new ArrayList<Map.Entry<String,Double>>(map.entrySet());
    	 Collections.sort(list,new Comparator<Map.Entry<String ,Double>>(){    		 
			@Override
			public int compare(java.util.Map.Entry<String, Double> arg0, java.util.Map.Entry<String, Double> arg1) {
				double result = arg0.getValue() - arg1.getValue();
			      if(result > 0)
			       return 1;
			      else if(result == 0)
			       return 0;
			      else 
			       return -1;
			}    		 
    	 });
    	     Map<String,Double> newMap = new LinkedHashMap<String,Double>();  
            for (int i = 0; i < list.size(); i++) {  
                newMap.put(list.get(i).getKey(), list.get(i).getValue());
               System.out.println(list.get(i).getKey()+"   "+list.get(i).getValue());
            }  
            return newMap; 
     }
     /**
      * 读取一个文件的内容，到一个集合中。注意唯一性
      * 文件应该的格式是：空格分隔
      * uid
      * uid
      * 或者是
      * uid uid uid uid 
      * uid uid uid uid
      * @param filePath
      * @return
      */
     public  Set<String> readToSet(String filePath){
    	  File file=new File(filePath);
    	  Set<String> set=new HashSet<String>();
    	  LineIterator it=null;
    	   try {
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				line=it.nextLine();
				
				//String [] arr=line.split(",");
				String [] arr=line.split(" ");
				if(!set.contains(arr[0]))
					set.add(arr[0]);
				//对于只有uid列表
				//set.add(line.trim());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   finally{
    		   LineIterator.closeQuietly(it);
    	   }
    	   
    	 return set;
    	 
     }
     //读取文件到一个set集合，这个文件是每行一个uid
     public  Set<String> readToSet2(String filePath){
   	  File file=new File(filePath);
   	  Set<String> set=new LinkedHashSet<String>();
   	  LineIterator it=null;
   	   try {
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				line=it.nextLine();
				
				//String [] arr=line.split(" ");
				
				//对于只有uid列表
				set.add(line.trim());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	   finally{
   		   LineIterator.closeQuietly(it);
   	   }
   	   
   	 return set;
   	 
    }
     /**
      * 读取一个文件的内容，到一个集合中。注意唯一性
      * 文件应该的格式是：空格分隔
      * uid
      * uid
      * 或者是
      * uid uid uid uid 
      * uid uid uid uid
      * @param filePath
      * @return  所有的uid 
      */
     public  Set<String> readToSet3(String filePath){
    	  File file=new File(filePath);
    	  Set<String> set=new LinkedHashSet<String>();
    	  LineIterator it=null;
    	   try {
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				line=it.nextLine();
				String [] arr=line.split(" ");
				for(String ss:arr){
					set.add(ss);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   finally{
    		   LineIterator.closeQuietly(it);
    	   }
    	   
    	 return set;
    	 
     }
     /**
      * 计算一个字符串中，某个字符出现的次数，如：@是大法官圣诞节还是@司法所，统计其中@字符的次数
      */
     public int calStr(String sourceStr,String findStr){
    	 int beginIndex=-1;
    	 int count=0;
    	 if(sourceStr!=null){
    	 while((beginIndex=sourceStr.indexOf(findStr))!=-1){
    		 sourceStr=sourceStr.substring(beginIndex+findStr.length());
    		 count++;
    	 }}
    	 
    	 
    	 return  count;
     }
     /**
      * 计算一个字符串中含有的URL数，
      */
     public int calURL(String sourceStr){
    	 sourceStr=sourceStr.toLowerCase();
    	 int count=0;
    	 if(sourceStr.contains("www")||sourceStr.contains("http")){
    	 String[] st=sourceStr.split("http");
    	 String[] st2=sourceStr.split("www");
    	 if(st.length==st2.length)
    		 count=st.length-1;
    	 else if(st.length>st2.length)
    		 count=st.length-1;
    	 else count=1;
    	 return count;
    	 }
    	 else return count;
    	  
     }
     /**
      * 导出
      * 
      * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
      * @param dataList 数据
      * @return
      */
     public static boolean exportCsv(File file, List<String> dataList){
         boolean isSucess=false;
         
         FileOutputStream out=null;
         OutputStreamWriter osw=null;
         BufferedWriter bw=null;
         try {
             out = new FileOutputStream(file);
             osw = new OutputStreamWriter(out);
             bw =new BufferedWriter(osw);
             if(dataList!=null && !dataList.isEmpty()){
                 for(String data : dataList){
                     bw.append(data).append("\r");
                 }
             }
             isSucess=true;
         } catch (Exception e) {
             isSucess=false;
         }finally{
             if(bw!=null){
                 try {
                     bw.close();
                     bw=null;
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
             }
             if(osw!=null){
                 try {
                     osw.close();
                     osw=null;
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
             }
             if(out!=null){
                 try {
                     out.close();
                     out=null;
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
             }
         }
         
         return isSucess;
     }
     /**
      * 根据uid列表，选择uid列表里的条目。用来根据uid列表选择，uid_follows 或者uid_friends
      * 参数uidPath是待提取的uid列表
      * 参数uidFPath是uid文件所在路径，每行以空格为分割
      * 参数savePath是提取的uidf文件保存路径
      */
     public void selectUIDF(String uidPath,String uidFPath,String SavePath){
    	 Set<String> uidset=new HashSet<>();
    	 uidset=this.readToSet(uidPath);
    	 LineIterator it=null;
    	 StringBuilder strb=new StringBuilder();
    	 try {
			it=FileUtils.lineIterator(new File(uidFPath));
			int count=0;
			while(it.hasNext()){
				String line=it.nextLine();
				String []arr=line.split(" ");
				//String []arr=line.split("\t");
				if(uidset.contains(arr[0].trim())){
					strb.append(line);
					strb.append("\n");
					count++;
				}
				
			}
			System.out.println("uid列表含有"+uidset.size()+"选择的uidF有"+count+"条");
			FileUtils.write(new File(SavePath), strb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 
     }
     /**
      * 根据uid列表，删除uid列表里的条目。用来根据uid列表删除，uid_follows 或者uid_friends指定的条目
      * 参数uidPath是待删除的uid列表
      * 参数uidFPath是uid文件所在路径，每行以空格为分割
      * 参数savePath是提取的uidf文件保存路径
      */
     public void removeUIDF(String uidPath,String uidFPath,String SavePath){
    	 Set<String> uidset=new HashSet<>();
    	 uidset=this.readToSet(uidPath);
    	 LineIterator it=null;
    	 StringBuilder strb=new StringBuilder();
    	 try {
			it=FileUtils.lineIterator(new File(uidFPath));
			int count=0;
			while(it.hasNext()){
				String line=it.nextLine();
				String []arr=line.split(" ");
				if(!uidset.contains(arr[0].trim())){
					strb.append(line);
					strb.append("\n");
					count++;
				}
				
			}
			System.out.println("uid删除列表含有"+uidset.size()+"选择的uidF有"+count+"条");
			FileUtils.write(new File(SavePath), strb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 
     }
     /**
      * 鉴于基于pojo特征对象来合并扩展特征向量，有时十分的不方便
      * 此方法在于，以uid合并两个文件中的特征向量为一个。并且认为这两个文件中的特征是不同的
      */
     public void mergeFeatures(String filepath1,String filepath2,String savepath){
    	 Map<String,StringBuilder> result=new HashMap<String,StringBuilder>();
    	 try {
			String text1=FileUtils.readFileToString(new File(filepath1));
			String text2=FileUtils.readFileToString(new File(filepath2));
			String [] linearr1=text1.split("\n");
			String[] linearr2=text2.split("\n");
			//处理文件一的
			for(String ss:linearr1){
				String[] arr=ss.split(" ");
				String uid=arr[0];
				 result.put(uid.trim(), new StringBuilder(ss+" "));
			}
			for(String s:linearr2){
				String[] arr2=s.split(" ");
				
				String uid2=arr2[0];
				StringBuilder strb=new StringBuilder();
				for(int i=1;i<arr2.length;i++){
					strb.append(arr2[i]);
					strb.append(" ");
					
				}
				if(result.containsKey(uid2.trim())){
					
					result.put(uid2, result.get(uid2.trim()).append(strb));
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  Iterator<Map.Entry<String, StringBuilder>> it = result.entrySet().iterator();
    	  StringBuilder resultStrb=new StringBuilder();
    	     while (it.hasNext()) {
    	      Map.Entry<String, StringBuilder> entry = it.next();
    	      resultStrb.append(entry.getValue());
    	      resultStrb.append("\n");
    	     // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
    	     }
    	     System.out.println(resultStrb);
    	     try {
				FileUtils.write(new File(savepath), resultStrb);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     }
   
}
