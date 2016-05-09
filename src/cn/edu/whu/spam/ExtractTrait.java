/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *提取用户的特征
 */
public class ExtractTrait {
      static  String  	UID_FILE_PATH="E:/normal/UID.txt";
 	 public static final long ONEHOUR = 3600000l;
      //用户ID
      static Set<String> UID_SET=new LinkedHashSet<String>();
      static Map<String,StringBuilder> USER_WEIBO_MAP=new HashMap<String,StringBuilder>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExtractTrait ext=new ExtractTrait();
		//抽取not exist用户的profile信息,注意UID_FILE_PATH初始化
		//ext.extractItemByUID("D:/Whuer/FudanData/weibo_users.txt", UID_FILE_PATH , "E:/spam/profiles.txt");
		//抽取normal用户的profile信息
		//ext.extractItemByUID("D:/Whuer/FudanData/weibo_users.txt", UID_FILE_PATH , "E:/normal/profiles.txt");
		//抽取微博
	    //ext.extractWeibo("H:/复旦大学/Weibo.Corpus/Weibo.data/merge","E:/normal/2_UltimateNormal/weibos");
	    //ext.extractWeibo2("D:/Whuer/FudanData/weibodata.csv","E:/normal/weibo.txt");


		//提取not exist用户的行为特征
		ext.extractFeatures("E:/spam/3_UltimateSelected/weibos", "E:/spam/extractF.txt");
		//提取normal用户的行为特征
		//ext.extractFeatures("E:/normal/2_UltimateNormal/weibos", "E:/normal/behaviorFeatures.txt");
	    //ext.calF();
		//ext.calIntersection();
		
	
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
        Set<String> had=new HashSet<>();
        while(iter.hasNext()){
        	line=iter.nextLine();
        	String[] arr=line.split(",");
        	//if(arr.length>4)
        	//map.put(arr[4], line);
        	//else System.out.println("异常，数组越界    "+line);
        	if(arr.length>5){
        		if(needItemsSet.contains(arr[4])&&!had.contains(arr[4])){
        			had.add(arr[4]);
        			System.out.println(arr[4]);
        			strb.append(line);
        			strb.append("\n");
        		}
        		
        	}
        }
        LineIterator.closeQuietly(iter);
      // System.out.println(map);
//        StringBuffer str= new StringBuffer();
//        for(String ss:needItemsSet){
//        	if(map.containsKey(ss)){
//        		str.append(map.get(ss));
//        	str.append("\n");}
//        }
//     
//		
        try {
			FileUtils.writeStringToFile(resultFile, strb.toString(),"utf-8"); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 提取部分用户的微博信息，一个用户的微博集保存为一个以这个用户命名的txt文件
     * 遍历72GB的数据需要10分钟
     * 也可以分割一个大微博文件为每个uid命名的文件
     */
    public void extractWeibo(String weiboFolderPath,String saveFolderPath){
    	 Utils utils=new Utils();
    	 UID_SET=utils.readToSet(UID_FILE_PATH);
    	 //UID_SET.add("127978896");
    	 //UID_SET.add("159597830");
    	 Map<String,File> files=new TreeMap<String,File>();
    	 //把用户的微博做成静态的Map,当要抽取的微博非常多是，这样明显不合适，所以用下面的局部变量存每个文件下的
    	 Map<String,StringBuilder> localMap=new HashMap<String,StringBuilder>();
    	// files=utils.getFileList(weiboFolderPath);
//    	 files.remove("lv2_weibo.zip");
//    	 files.remove("lv3_0.rar");
//    	 files.remove("weibodata.rar");
         files.put("weibo.txt", new File("E:/normal/weibo.txt"));
    	 System.out.println(files.size());
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
							//System.out.println("正在提取"+arr[0]+"用户的微博");							
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
    		     splitWeiboToFiles(saveFolderPath);
    		     
    	 }
    }
    //每个用户的微博保存为一个文件
    public void splitWeiboToFiles(String saveFolderPath){
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
     * 抽取一个文件中的微博
     * @param weibosource
     * @param save
     */
    public void extractWeibo2(String weibosource,String save){
   	 Utils utils=new Utils();
   	 UID_SET=utils.readToSet(UID_FILE_PATH);
   File	 file= new File(weibosource);
   LineIterator lineit=null;
   		     try {	 FileWriter filewriter=null;
   			 PrintWriter pw=null;
   				filewriter=new FileWriter( new File(save) ,true);
   				pw=new PrintWriter(filewriter);
				 lineit=FileUtils.lineIterator(file);
					while(lineit.hasNext()){
						String line=lineit.nextLine();
						String[] arr=line.split(",");
						if(UID_SET.contains(arr[0])){
							pw.write(line);
							pw.write("\n");
							pw.flush();
						}
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
					   LineIterator.closeQuietly(lineit);
				}
   		     //保存结果
   		   //save(saveFolderPath);
   		     
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
    public String extractF(String URL){
    	Path path=Paths.get(URL);
    	
    	//微博中@数量
    	int atNums=0;
    	//含URL的微博数
    	int URLNums=0;
    	//微博中的是否提及话题，#topic#
    	int topicNums=0;
    	//微博来源，移动终端为0，非移动终端为1。具体实现，计数比较，如果移动终端微博数大于非移动终端则为0
    	int MCount,nonMCount;
    	//转发数
    	int reposts_count=0;
    	//评论数
    	int comment_count=0;
    	//转发微博数
    	int rep=0;
    	//微博总数
    	int weiboTotal=0;
    	Utils utils=new Utils();
    	String result=null;
    	String content;
    	String uid=null;
    	List<String> timeList=new LinkedList<String>();
    	  try {
			 content = new String(Files.readAllBytes(path));
			 //这个数组中存储的是一个用户的所有的微博
			String[]  strb=content.split("\\n");
			weiboTotal=strb.length;
			//System.out.println("total Nums Of weibo "+strb.length);
			StringBuilder weibotext=new StringBuilder();
			for(int j=0;j<strb.length;j++){
				StringBuilder tem=new StringBuilder();
				String[] arr=strb[j].split(",");
				
				//存储规范的数组切割
				String[] arr2=new String[6];
				uid=arr[0];
				//复制数组前5个，这部分是格式化的，第6才可能出现异常
				for(int i=0;i<5;i++)
					arr2[i]=arr[i];
				//原文本以逗号为分割，但是可能text中含有英文逗号，所以对于这些特殊的需要规范化处理
				if(arr.length>5){
					for(int i=5;i<arr.length;i++){
						tem.append(arr[i]);
					}
					arr2[5]=tem.toString();
				}
				//微博中没有爬到转发的源微博，所以对于博文只有“转发微博”的过滤掉，不计算这样的微博相似性
				if(arr2[5]!=null&&!(arr2[5].trim().equals(("转发微博")))){
				weibotext.append(arr2[5]);
				weibotext.append("\n");}
				//保存时间列表，处理时间特征
				timeList.add(arr[4]);
				//下面计算微博之间的相似度，算法是，一条微博与其他微博进行相似性计算，
				//一条微博取所有微博中匹配最大的作为，这个条微博的最大相似匹配，然后把所有的加起来作为这个用户的微博相似性特征
				reposts_count=Integer.valueOf(arr2[1])+reposts_count;
				comment_count=Integer.valueOf(arr2[2])+comment_count;
				atNums=atNums+utils.calStr(arr2[5], "@");
				topicNums=topicNums+utils.calStr(arr2[5], "#"); 
				if(arr2.length>5&&arr2[5]!=null){
				if(arr2[5].contains("http")||arr2[5].contains("www"))
					URLNums++;
				if(arr2[5].contains("转发微博")||arr2[5].contains("//@"))
					rep++;
				}
			}
			DecimalFormat   df=new   java.text.DecimalFormat("#.###"); 
			double URLrate=(double)URLNums/(double)weiboTotal;
			double weiborepostRate=(double)rep/(double)weiboTotal;
			double commentRate=(double)comment_count/(double)weiboTotal;
			double repostRate=(double)reposts_count/(double)weiboTotal;
			double atRate=(double)atNums/(double)weiboTotal;
			double topicRate=(double)topicNums/(double)weiboTotal;
			String[] weibo=weibotext.toString().split("\n");
			double weibosimilarity=calWeiboSimilarity(weibo);
			double intervalRate=calTimeTrait(timeList);
			//System.out.println("总评论数"+comment_count+"总转发数"+
			//reposts_count+"@总数"+atNums+"#总数数"+topicNums+"URL比例"+df.format(URLrate)+"转发的微博比例"+df.format(repostRate));
			result=uid+" "+df.format(commentRate)+" "+df.format(repostRate)+" "+df.format(atRate)+" "+df.format(topicRate)+" "+df.format(URLrate)+" "+df.format(weiborepostRate)+" "+df.format(weibosimilarity)+
					" "+df.format(intervalRate);
		   // System.out.println(result);
    	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	  return result;
    	  
         
    }
    /**
     * 计算微博的相似度,返回的是微博层级之间的相似度之和。如A,B,C三天微博，计算A和B，B和C
     * @return
     */
    public  double calWeiboSimilarity(String[] str){

    	GetSimilarity getSimi=new GetSimilarity();
    	//保存一个句子的最大值 
    	double max=0.0;
    	 //保存一个比较的临时结果
    	 double temp=0.0;
    	 //保存结果
    	 double result=0.0;
         for(int i=0;i<str.length-1;i++){
        	 max=0.0;
         	 temp=0.0;
        	
        	// for(int j=0;j<str.length;j++){
        		// if(j!=i){
        			temp=getSimi.getSimilarity(str[i], str[i+1], 2);
        		// }
        		// if(temp>max)
        			 max=temp;
        	// }
        	 result+=temp;
        	// System.out.println("正在计算的句子的是     "+str[i]+"其最大匹配度为"+max);
         }
    return result/(double)str.length;
    }
    /**
     * 遍历所有的文件抽取特征，并保存结果
     */
    public void extractFeatures(String weiboFolder,String save){
    	Utils utils=new Utils();
    	File saveFile=new File(save);
    	Map<String,File> filemap=new LinkedHashMap<String,File>();
    	filemap=utils.getFileList(weiboFolder);
    	Iterator it=filemap.keySet().iterator();
    	List<String> result=new ArrayList<String>();
    	while(it.hasNext()){
    		String key=it.next().toString();
    		File value=filemap.get(key);
    		//System.out.println(value);
    		result.add(extractF(value.toString()));
    	}
    	utils.saveResultByList(result, save);
    	System.out.println(result.size());
    }
    /**
     * 进一步赛选微博
     */
    public void calF(){
    	Utils utils=new Utils();
    	Set<String> s=new LinkedHashSet<String>();
    	s=utils.readToSet2("E:/spam/UID.txt");
    	   File	 file= new File("E:/spam/weibo/weibo.txt");
    	   LineIterator lineit=null;
    	   int i=0;
    	   		     try {	 
    	   		    	 FileWriter filewriter=null;
    	   			     PrintWriter pw=null;
    	   				filewriter=new FileWriter( new File("E:/spam/weibo_copy.txt") ,true);
    	   				pw=new PrintWriter(filewriter);
    					 lineit=FileUtils.lineIterator(file);
    					 System.out.println(s.size());
    						while(lineit.hasNext()){
    							i++;
    							String line=lineit.nextLine();
    							String[] arr=line.split(",");
    							if(s.contains(arr[0])){
    								pw.write(line);
    								pw.write("\n");
    								pw.flush();
    							}
    						}
    						System.out.println(i);
    						
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}finally{
    						   LineIterator.closeQuietly(lineit);
    					}
    	
    }
    /**
     * 计算关注列表，粉丝列表交集
     */
    public void calIntersection(){
    	Utils utils=new Utils();
    	Set<String> followSet=new LinkedHashSet<String>();
    	Set<String> friendSet=new LinkedHashSet<String>();
    	Set<String> result=new LinkedHashSet<String>();
    	followSet=utils.readToSet("E:/spam/uidfollows_selected.txt");
    	friendSet=utils.readToSet("E:/spam/uidfriends_selected.txt");
    	Iterator it=followSet.iterator();
    	while(it.hasNext()){
    		Object s=it.next();
    		if(friendSet.contains(s))
    			result.add(s.toString());
    	}
    	utils.saveResultBySet(result, "E:/spam/UID.txt");
    	
    }
    /**
     * 计算微博发布时间间隔特征，
     * 输入为一个用户的所有的微博中的时间list,时间格式为2012-12-19 21:02
     * 返回一个用户的平均每条微博的间隔时间，以分钟为单位
     * 时间间隔在60分钟的不计算在内
     */
    public double calTimeTrait(List<String> list){
    	//把字符串的时间序列转化为日期类型，并且得到从标准时间到这个时间的毫秒数
         long interval=0l;
         long result=0l;
         double rate=0.0;
         List<Long> timelist=new LinkedList<Long>();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
         Date date=new Date();
         try{
         for(String ss:list){
        	 date = df.parse(ss); 
        	 //格式化的时间是精确到分钟，所以这里只需要精确到分钟加入到list
        	 timelist.add(date.getTime()/60000);
         }
         }catch  (Exception e) {
        	 e.printStackTrace();
         }
         for(int i=0;i<timelist.size()-1;i++){
        	 interval=timelist.get(i)-timelist.get(i+1);
        	 //System.out.println(interval);
        	 //如果时间间隔小于一小时，就累加这个间隔作为这个用户的时间间隔特征，否则不处理
        	 if(interval>0&&interval<=60){
        		 result+=interval;
        	 }
        	 //小间隔时间，惩罚时间
        	 if(interval==0)
        		 result+=10l;
        	 if(interval>0&&interval<6)
        		 result+=5l;
         }
         rate=(double)result/(double)(list.size());
         return rate;
         
    }
   
}
