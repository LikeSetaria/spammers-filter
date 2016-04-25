/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.pojo.Relation;
import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *
 */
public class ProProcess {
	//统计既有关注又有粉丝的一些UID
    static Set<String> set=new HashSet<String>();
    //全局变量
	static Map<String,StringBuilder> map1=new HashMap<String,StringBuilder>();
	static Map<String,StringBuilder> map2=new HashMap<String,StringBuilder>();
	  /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProProcess pro=new ProProcess();
		Utils uti=new Utils();
		/*
		 * 第一步，提取Ngram后得到UID
		 */
		//pro.extractUID("E:/spam/1_Ngram_result/NameProBy2gram4_6.txt","E:/spam/UserNameUID.txt") ;
		
		
		/*
		 * 第二步1，提取原始文件中的相关的关系
		 */
		//pro.extractPartOfRelation("D:/Whuer/FudanData/weibo_follows.csv","E:/spam/UID.txt", "E:/spam/allUIDRelations.txt");
		//切割大文件为小文件
		//uti.fileSpilt("E:/spam/allUIDRelations.txt", "E:/spam/", 2, 77363900);
		//提取粉丝关系
		//pro.extractFollows("E:/spam/allUIDRelations_part2.txt","E:/spam/uidfollows_part2.txt");
		
		
		/*
		 *第二步2，提取原始文件中的相关的关系.修改extractPartOfRelation()方法，使用if(set.contains(arr[0]))
		 */
		//原始文件中提取相关的关系，以方便进一步分析
         //pro.extractPartOfRelation("D:/Whuer/FudanData/weibo_follows.csv","E:/spam/UID.txt", "E:/spam/allUIDRelations_friends.txt");
		//切割大文件为小文件
		//uti.fileSpilt("E:/spam/allUIDRelations_friends.txt", "E:/spam/", 2, 79621101);
		//分别处理上面的得到两个uid friendsid 这样的文件
		//pro.extractFriends("E:/spam/allUIDRelations_friends_part2.txt","E:/spam/tt2.txt");
		
		/*
		 * 第二步3，合并上面得到的两个文件
		 */
		//pro.readToMap1("E:/spam/uidfriends_part1.txt");
		//pro.readToMap2("E:/spam/uidfriends_part1.txt");
		//pro.merge("E:/spam/uidfriendstemp.txt");
		
		//合并上面两个文件中又遇到内存溢出问题，所以再切分，分别处理。办法是，pro.merge()上面一步得到的两个文件，到某处内存溢出，到此
		//其实程序得到的结果是正确的，所以可以从上面那两个文件中，去除这部分已经处理的部分，就是条用pro.getUIDBoth(),调用except()排除得到下一步的文件
		//pro.getIUIDBoth("E:/spam/uidfriendstemp.txt");
		//pro.except("E:/spam/uidfriends_part2.txt","E:/spam/part2_except.txt");
		
		//pro.readToMap1("E:/spam/part1_except.txt");
		//pro.readToMap2("E:/spam/part2_except.txt");
		//pro.merge("E:/spam/uidfriends.txt");
		//System.out.println(set.size());
		
		//合并得到最后的结果
		//pro.appendFile("E:/spam/uidfriends.txt" , "E:/spam/uidfriendstemp.txt");
		
		//统计得到既有关注又有粉丝的uid
//		pro.getIUIDBoth("E:/spam/uidfriends_part1.txt");
//		pro.getIUIDBoth("E:/spam/uidfriends_part2.txt");
//		pro.getIUIDBoth("E:/spam/2_GetRelations/uidfollows_part1.txt");
//		uti.saveResultBySet(set, "E:/spam/bothUID");
	
		//pro.appendFile("E:/spam/uidfollows_part2.txt" , "E:/spam/uidfollows_part1.txt");
		/*
		 * 第三步抽取，珍妮爬取的不存在用户的特征
		 */
		//根据爬取的不存在列表，找出信息比较全（存在关注和粉丝）和不存在用户列表的交集。对其进行分析，提取他的特征
		pro.extractNotExist("E:/UserNotExist_tmp.txt","E:/spam/BothUID.txt","E:/spam/NotExistuid.txt");
		
	}
	//第一步，提取Ngram后的结果，方便下一步使用
	/**
	 * 输入文件是Ngram后的结果，格式化文件：时尚代购买手	2622155147	1.1752303914938278
                                                                                                    服饰搭配控_	2442534045	1.1752309682347668
	 * @param path 
	 */
	public Set<String> extractUID(String sourcePath,String resultPath){
		File file=new File(sourcePath);
		LineIterator it=null;
		Utils utils=new Utils();
		//存储UID的集合,想保持插入的顺序使用TreeSet是不可以的，应使用LinkedHashSet
		//TreeSet：提供一个使用树结构存储Set接口的实现，对象以升序顺序存储，访问和遍历的时间很快。
		Set<String> resultUID=new LinkedHashSet<String>();
		//存储UID及用户名对应的集合
		Set<String> resultUIDName=new LinkedHashSet<String>();
		try {
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				line=it.nextLine();
				String[] arr=line.split("\t");
				//保存用户UID
				resultUID.add(arr[1]);
				//保存用户名及UID
				resultUIDName.add(arr[0]+"\t"+arr[1]);
			}
			//保存为TXT文件
			//System.out.println(resultUID);
			utils.saveResultBySet(resultUIDName, resultPath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			LineIterator.closeQuietly(it);
		}
		
		return resultUID;
		
	}
	//第二步，对原始文件进行预处理，只抽取需求相关的关系
	 /**第二步1
	  * 由于原始文件太大了，为了方便处理这里对它们先进行一次提取。
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
   	  String line;
   	 FileWriter fw=null;
   	 int total1=0;int total2=0;
   	 // Map<String,LinkedList<String>> map=new HashMap<String,LinkedList<String>>(); 
			  try {
			    fw = new FileWriter(resultFilePath,true); 
				it = FileUtils.lineIterator(followFile, "UTF-8");
				iter = FileUtils.lineIterator(uidFile, "UTF-8");
				//把待提取的相关的UID，读到HashSet中
				while(iter.hasNext()){
					total1++;
					str1=new StringBuilder(iter.nextLine());
					set.add(str1.toString());
				}
				//逐行遍历原始数据文件，
				while(it.hasNext()){
					total2++;
					//str2=new StringBuilder(it.nextLine());
					//String[] arr=str2.toString().split(",");
					line=it.nextLine();
					String[] arr=line.split(",");
					//提取目标用户作为粉丝，和被关注者所有的关系信息
					//if(set.contains(arr[0])||set.contains(arr[1])){
					//提取目标用户仅作为粉丝，所有的关系
						if(set.contains(arr[0])){
					//提取目标用户仅作为被关注者的所有的关系信息
					//if(set.contains(arr[1])){
						//fw.write(str2.toString());
						fw.write(line);
						fw.write("\n");
					
					}
					fw.flush();
			
				}
				
				System.out.println("UID遍历的总行数为："+total1+
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
    //第二步，提取用户粉丝关系  ，即把用户表示成uid follow1_uid follow2_uid ...
    /**
     * 第二步2
     * 提取用户的friends,即用户关注的用户
     * 输入文件: 待处理文件的格式
     * follows_id     users_id
     * 1051852951,1045604114
       1092658523,1045604114
       1178310055,1045604114
     * 提取目标用户所有的粉丝提取的格式是：用户ID 粉丝id.....
     * 文件太大如超过1.3Gb时程序会报错，又待改进，不过可以拆分文件分部分，先基本完成任务
     * 依然没有解决都在内存中操作数据的范畴，程序写的太烂，有待想到更好的算法
     * 不过下面这样做，逻辑更清晰，结果更准确
     */
    public void extractFollows(String sourceFilePath,String resultPath){
      	 File file=new File(sourceFilePath);
       	 LineIterator iter =null;
        	 String strb=null;
        	 try {
    			iter=FileUtils.lineIterator(file);
    			StringBuilder point=null;
    			int lineNums=0;
    			Map<String,StringBuilder> map=new TreeMap<String,StringBuilder>();
    			while(iter.hasNext()){
    				 
    				strb=iter.next();
    				//System.out.println("遍历的是："+strb);
    				String[] arr=strb.split(",");
    				if(map.containsKey(arr[1]))
    				{
    					map.put(arr[1], map.get(arr[1]).append(" "+arr[0]));
                    //System.out.println(map.get(arr[1]));
    				}else{
    					map.put(arr[1], new StringBuilder(arr[1]+" "+arr[0]));
    					 
    				}
    				lineNums++;
    				 
    			}
    			System.out.println("Now to save the results");
    			 Iterator<?> it =map.entrySet().iterator();
    			 System.out.println("用户数总计："+map.size()+"  "+"粉丝总数为"+lineNums);
    			 FileWriter fw=null;
    			 fw = new FileWriter(resultPath,true); 
    			 
    	         while(it.hasNext()){
    	        	 
    				Map.Entry entry=(Map.Entry) it.next(); 
    	        	 Object val=entry.getValue();
    	        	 fw.write(val.toString()+" ");
    	        	 fw.write("\n");
    	        	 fw.flush();
    	         }
    	         if (fw != null)  
 	                try {  
 	                    fw.close();  
 	                } catch (IOException e) {  
 	                    throw new RuntimeException("关闭失败！");  
 	                }  
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println(strb);
    		}finally{
    			LineIterator.closeQuietly(iter);
    		}
        	
    }
     /**
      * 
      * @param sourceFilePath
      * @param resultPath
      */
    public void extractFriends(String sourceFilePath,String resultPath){
     	 File file=new File(sourceFilePath);
      	 LineIterator iter =null;
       	 String strb=null;
       	 try {
   			iter=FileUtils.lineIterator(file);
   			int lineNums=0;
   			Map<String,StringBuilder> map=new TreeMap<String,StringBuilder>();
   			while(iter.hasNext()){
   				 
   				strb=iter.next();
   				//System.out.println("遍历的是："+strb);
   				String[] arr=strb.split(",");
   				if(map.containsKey(arr[0]))
   				{
   					map.put(arr[0], map.get(arr[0]).append(" "+arr[1]));
               //  System.out.println(map.get(arr[0]));
   				}else{
   					map.put(arr[0], new StringBuilder(arr[0]+" "+arr[1]));
   					 
   				}
   				lineNums++;
   				 
   			}
   			System.out.println("Now to save the results");
   			 Iterator<?> it =map.entrySet().iterator();
   			 System.out.println("用户数总计："+map.size()+"  "+"粉丝总数为"+lineNums);
   			 FileWriter fw=null;
   			 fw = new FileWriter(resultPath,true); 
   			 
   	         while(it.hasNext()){
   	        	 
   				Map.Entry entry=(Map.Entry) it.next(); 
   	        	 Object val=entry.getValue();
   	        	 fw.write(val.toString()+" ");
   	        	 fw.write("\n");
   	        	 fw.flush();
   	         }
   	         if (fw != null)  
	                try {  
	                    fw.close();  
	                } catch (IOException e) {  
	                    throw new RuntimeException("关闭失败！");  
	                }  
   			
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   			System.out.println(strb);
   		}finally{
   			LineIterator.closeQuietly(iter);
   		}
       	
   }
    //总是想一个方法中解决所有的过程，这就会产生很多中间结果，而这些没有用的结果，gc又不能回收他们，造成占据大量空间。
    public void merge(String resultPath){
    	 File file=new File(resultPath);
    	 
    	Iterator<?> it =map1.entrySet().iterator();
    	 System.out.println("用户数总计："+map1.size());
		 FileWriter fw=null;
		 try {
			fw = new FileWriter(resultPath,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    	while(it.hasNext()){
    		
    		Map.Entry<String,StringBuilder> entry=(Entry<String, StringBuilder>) it.next(); 
    		 String key=entry.getKey();
    		  StringBuilder  value=entry.getValue(); 
    		
    		if(map2.containsKey(key.toString())){ 
    			StringBuilder temp=map2.get(key);
    			value.append(" ");
    			value.append(temp);
    			try {
    				fw.write(key.toString()+" ");
					fw.write(value.toString()+"\n");
    				fw.flush();
					//FileUtils.write(file, key+" "+value.toString()+"\n", true);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		} else{
				try {
					fw.write(key.toString()+" ");
					fw.write(value.toString()+"\n");
					//FileUtils.write(file, key+" "+value.toString()+"\n", true);
					fw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
    	}
    	
			try {fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    }
    //读取文件到map
    public  void readToMap1(String file1Path ){
    	File file1=new File(file1Path);
    	LineIterator it1=null;
    	try {
			it1=FileUtils.lineIterator(file1);
			String line=null;
			StringBuilder strb1;
			 
	    	while(it1.hasNext()){ 
	    		strb1=new StringBuilder();
	    		line=it1.nextLine();
	    		String[] arr1=line.split(" "); 
	    	    if(arr1.length>1){
	    	    	for(int i=1;i<arr1.length;i++){
	    	    		strb1.append(arr1[i]);
	    	    		strb1.append(" ");
	    	    	}
	    	    	map1.put(arr1[0],strb1);
	    	    }
	    	
		}System.out.println(map1.size());
	    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 finally{
    		 LineIterator.closeQuietly(it1);
    	 }
    	
    }
    public  void readToMap2(String file1Path ){
    	File file1=new File(file1Path);
    	LineIterator it1=null;
    	try {
			it1=FileUtils.lineIterator(file1);
			String line=null;
			StringBuilder strb1;
	    	while(it1.hasNext()){
	    		strb1=new StringBuilder();
	    	 
	    		line=it1.nextLine();
	    		String[] arr1=line.split(" "); 
	    	    if(arr1.length>1){
	    	    	for(int i=1;i<arr1.length;i++){
	    	    		strb1.append(arr1[i]);
	    	    		strb1.append(" "); 
	    	    	}
	    	    }
	    		map2.put(arr1[0], strb1);
	    	}
	    	System.out.println(map2.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 finally{
    		 LineIterator.closeQuietly(it1);
    	 }
    	
    }
    /**
     * 得打开文件，读取其中每行的第一个ID(都是以空格或者逗号为分割的)到一个static Set
     * @param file1Path
     */
    public  void getIUIDBoth(String file1Path ){
    	File file1=new File(file1Path);
    	LineIterator it1=null;
    	try {
			it1=FileUtils.lineIterator(file1);
			String line=null;
	    	while(it1.hasNext()){
	    		line=it1.nextLine();
	    		String[] arr=line.split(" ");
	    		if(!set.contains(arr[0]))
	    			set.add(arr[0]);
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 finally{
    		 LineIterator.closeQuietly(it1);
    	 }
    	
    }
    /**
     * 去除已经处理的部分，保存未处理的为另一个文件。
     * 前置条件，已经调用getIUIDBoth(String file1Path )方法初始化static Set。调用这个方法，得到需要排除的主ID.
     * 因为上面的结果都已经是，uid fuid fuid ...
     * @param file1Path
     * @param resultPath
     */
    public void except(String file1Path,String resultPath){
    	File file1=new File(file1Path);
    	LineIterator it1=null;
    	FileWriter fw=null;
		 try {
			fw = new FileWriter(resultPath,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	try {
			it1=FileUtils.lineIterator(file1);
			String line=null;
	    	while(it1.hasNext()){
	    		line=it1.nextLine();
	    		String[] arr=line.split(" ");
	    		if(!set.contains(arr[0])){
	    			fw.write(line);
	    			fw.write("\n");
	    			fw.flush();
	    		}
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 finally{
    		 LineIterator.closeQuietly(it1);
    	 }
    }
   
    /**
     * 把一个文件追加到另一个文件下面
     * 注意被追加的文件会被修改
     * @param filefromPath
     * @param filetoPath
     */
    
    public void appendFile(String filefromPath,String filetoPath){
    	File file =new File(filefromPath);
    	LineIterator it1=null;
    	FileWriter fw=null;
		 try {
			fw = new FileWriter(filetoPath,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	try {
			it1=FileUtils.lineIterator(file);
			String line=null;
	    	while(it1.hasNext()){
	    		line=it1.nextLine();
	    			fw.write(line);
	    			fw.write("\n");
	    			fw.flush();
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 finally{
    		 LineIterator.closeQuietly(it1);
    	 }
    }
    /**
     * 根据珍妮提供的不存在列表，提取出现在备选集合中不存在的UID
     * 打印选中的不存在的UID
     */
    public  void extractNotExist(String NotExistFile,String bothUIDFile,String save){
    	
    	File file1 =new File(NotExistFile);
    	File file2 =new File(bothUIDFile);
    	Set<String> NotExistSet=new HashSet<String>();
    	Set<String> uidSet=new HashSet<String>();
    	LineIterator it1=null;
    	LineIterator it2=null;
    	try {
			it1=FileUtils.lineIterator(file1);
			it2=FileUtils.lineIterator(file2);
			while(it1.hasNext()){
				String line=it1.nextLine();
				NotExistSet.add(line);
			}
			while(it2.hasNext()){
				String line=it2.nextLine();
				uidSet.add(line);
			}
			StringBuilder str=new StringBuilder();
			for(String ss:uidSet){
				if(NotExistSet.contains(ss)){
					str.append(ss);
					str.append("\n");
					System.out.println(ss);
				}
				
			}
			File ff=new File(save);
			FileUtils.write(ff, str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			LineIterator.closeQuietly(it1);
			LineIterator.closeQuietly(it2);
		}
    
    	
    }
    

}
