/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
		ext.extractWeibo("D:/Whuer/FudanData/MERGE","E:/spam/weibo");
		ext.save("E:/spam/weibo");
		for (Entry<String, StringBuilder> entry : USER_WEIBO_MAP.entrySet()) {  
			  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		  
		}  
	 
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
     	 StringBuffer str=null;;
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
 

}
