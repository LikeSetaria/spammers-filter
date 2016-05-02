/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *提取用户的特征
 */
public class ExtractTrait {
      static  String  	UID_FILE_PATH="E:/spam/UID.txt";
      //用户ID
      static Set<String> UID_SET=new LinkedHashSet<String>();
      static Map<String,StringBuilder> USER_WEIBO_MAP=new HashMap<String,StringBuilder>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExtractTrait ext=new ExtractTrait();
		//抽取部分用户的profile信息
		//ext.extractItemByUID("D:/Whuer/FudanData/weibo_users.txt", UID_FILE_PATH , "E:/spam/UserItems.txt");
		//抽取微博
//		ext.extractWeibo("D:/Whuer/FudanData/MERGE","E:/spam/weibo");
//		ext.save("E:/spam/weibo");
//		for (Entry<String, StringBuilder> entry : USER_WEIBO_MAP.entrySet()) {  
//			  
//		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
//		  
//		} 
		//提取特征
		ext.extractFeatures("E:/spam/weibo/159597830.txt");
	 
	}
	/**
     *根据ID抽取相关的条目。
     *参数输入：1、源文件，格式化文件（false,小神万里,m,湖北 武汉,44528425,农民
                    false,咯咯spy,m,江苏 扬州,44550011,""）以行为分割
             2、条件文件，格式化文件（44550011
                       44528425）
             3、输出结果，条件文件中指定的所有条目
     * @throws FileNotFoundException 
     */
    public  void extractItemByUID(String sourceFilePath,String needFilePath,String resultFilePath ) {
    	File sourceFile=new File(sourceFilePath);
    	File needFile=new File(needFilePath);
    	File resultFile=new File(resultFilePath);
    	if(!sourceFile.exists()||!needFile.exists())
			try {
				throw new FileNotFoundException();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        LineIterator iter=null;
        LineIterator it=null;
        Set<String> needItemsSet=new HashSet<String>();
        try {
			iter=FileUtils.lineIterator(sourceFile);
			it=FileUtils.lineIterator(needFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while(it.hasNext()){
        	needItemsSet.add(it.nextLine());
        }
        LineIterator.closeQuietly(it);
        String line;
        StringBuffer strb=new StringBuffer();
        System.out.println(needItemsSet.size());
        Map<String,String> map=new HashMap<String,String>();
        while(iter.hasNext()){
        	line=iter.nextLine();
        	String[] arr=line.split(",");
        	if(arr.length>4)
        	map.put(arr[4], line);
        	else System.out.println("异常，数组越界    "+line);
//        	if(arr.length>5){
//        		if(needItemsSet.contains(arr[4])){
//        			strb.append(line);
//        			strb.append("\n");
//        		}
//        		
//        	}
        }
        LineIterator.closeQuietly(iter);
      // System.out.println(map);
        StringBuffer str= new StringBuffer();
        for(String ss:needItemsSet){
        	if(map.containsKey(ss)){
        		str.append(map.get(ss));
        	str.append("\n");}
        }
     
		
        try {
			FileUtils.writeStringToFile(resultFile, str.toString(),"utf-8"); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 提取部分用户的微博信息，一个用户的微博集保存为一个以这个用户命名的txt文件
     * 遍历72GB的数据需要10分钟
     */
    public void extractWeibo(String weiboFolderPath,String saveFolderPath){
    	 Utils utils=new Utils();
    	 UID_SET=utils.readToSet(UID_FILE_PATH);
    	 //UID_SET.add("127978896");
    	 //UID_SET.add("159597830");
    	 Map<String,File> files=new TreeMap<String,File>();
    	 files=utils.getFileList(weiboFolderPath);
    	 Iterator it=files.keySet().iterator();
    	 while(it.hasNext()){
    		     String key;    
    		     File file;    
    		     key=it.next().toString();    
    		     file=files.get(key); 
    		     System.out.println("正在处理的文件为：  "+file.getName());
    		     try {
					LineIterator lineit=FileUtils.lineIterator(file);
					StringBuilder strb=null;
					while(lineit.hasNext()){
						String line=lineit.nextLine();
						String[] arr=line.split(",");
						if(UID_SET.contains(arr[0])){
							System.out.println("正在提取"+arr[0]+"用户的微博");
							//System.out.println(USER_WEIBO_MAP.get(arr[0]));
							if(!USER_WEIBO_MAP.containsKey(arr[0])){
								strb=new StringBuilder(line);
							USER_WEIBO_MAP.put(arr[0], strb);
							}
							else
							{
								strb=USER_WEIBO_MAP.get(arr[0]).append("\n"+line);
								USER_WEIBO_MAP.put(arr[0],strb );
							}
						}
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		     //保存结果
    		   save(saveFolderPath);
    		     
    	 }
    }
    //每个用户的微博保存为一个文件
    public void save(String saveFolderPath){
        //File saveFile=new File(saveFolderPath);
     	 StringBuffer str=null;
     	 int cou=0;
        Iterator<?> iter =USER_WEIBO_MAP.entrySet().iterator();
        while(iter.hasNext()){
        	str=new StringBuffer();
        	Map.Entry entry=(Map.Entry) iter.next();
        	String key=entry.getKey().toString();
       	    Object val=entry.getValue();
       	    File file=new File(saveFolderPath+File.separator+key+".txt");
       	    str.append(val.toString());
       		// str.append("\n");
       		try {
				FileUtils.writeStringToFile(file, str.toString(), "utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	
        }
        
    }
    /**
     * 提取一个用户的特征，
     * 输入：一个用户的所有微博(一个uid命名的txt文件)
     * 结构: userid,reposts_count,comment_count,source,created_at, text
	         解释: 用户ID,转发数,评论数,来源,创建时间,文本内容
	         @param the URL of user 
	         @return 返回一个字符串，UID，是否来自移动终端 ，微博中@数量，微博中URL
     */
    //采取新的规范的文件处理方式
    public String extractFeatures(String URL){
    	Path path=Paths.get(URL);
    	//微博中@数量
    	int atNums=0;
    	//微博中URL数量
    	int URLNums=0;
    	//微博中的是否提及话题，#topic#
    	int topicNums=0;
    	//微博来源，移动终端为0，非移动终端为1。具体实现，计数比较，如果移动终端微博数大于非移动终端则为0
    	int MCount,nonMCount;
    	//转发数
    	int reposts_count=0;
    	//评论数
    	int comment_count=0;
    	String content;
    	 
    	  try {
			 content = new String(Files.readAllBytes(path));
			 Pattern pattern = Pattern.compile("//(//d{3}//)//s//d{3}-//d{4}");
			
			String[]  strb=content.split("\\n");
			String[] temp=null;
			
			System.out.println("total Nums Of weibo "+strb.length);
			for(String ss:strb){
				StringBuilder tem=new StringBuilder();
				String[] arr=ss.split(",");
				//存储规范的数组切割
				String[] arr2=new String[6];
				//复制数组前5个，这部分是格式化的，第6才可能出现异常
				for(int i=0;i<6;i++)
					arr2[i]=arr[i];
				//原文本以逗号为分割，但是可能text中含有英文逗号，所以对于这些特殊的需要规范化处理
				if(arr.length>6){
					for(int i=5;i<arr.length;i++){
						tem.append(arr[i]);
					}
					arr2[5]=tem.toString();
				}
				//System.out.println(arr2[5]);
				reposts_count=Integer.valueOf(arr2[1])+reposts_count;
				comment_count=Integer.valueOf(arr2[2])+comment_count;
				 
			}
			System.out.println("评论数"+comment_count+"转发数"+reposts_count);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	  return null;
    	  
         
    }
    

}
