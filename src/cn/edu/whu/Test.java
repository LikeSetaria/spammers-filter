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
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;

import cn.edu.whu.pojo.Relation;
import cn.edu.whu.pojo.User;
import cn.edu.whu.utils.PreprocessText;
import cn.edu.whu.utils.Utils;

/**
 * @author ����
 *���Է�����
 */
public class Test {
	 private static String WEIBO_USER_FILEPATH="D:\\Whuer\\Major\\Refs4Spammers";
	 private final static Logger logger = Logger.getLogger(Test.class);
	 private final static String  FOLLOWS_USER_TXT="E:/follows_users_10W_realated.txt";
	 private final static String  FOLLOWS_USER_RESULTS="E:/temp/relation_resultsExtracted.txt";
	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
		FileFilter filter=new FileFilter();
		Test test=new Test();
		PreprocessText process=new PreprocessText();
		/*//����getFileList()����
      
      Map<String, File> map=utils.getFileList(WEIBO_USER_FILEPATH);
      for(Iterator i = map.keySet().iterator(); i.hasNext();){
    	   Object obj = i.next();
    	   System.out.println(obj);
    	   System.out.println("key=" + obj + " value=" + map.get(obj));
      }
      for(Map.Entry<String, File> entry :map.entrySet()){
    	  System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }*/
		
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
test.compareFiles();
		// test.extractFid();
		//test.extractSort();
		//process.extractBoth("E:/spam/uidfollows.txt","E:/spam/uidfriends.txt","F:co.txt");
		//test.extraceEdge();
		//test.extractGender();
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
		File file=new File("E:/mute_fake/REs/UidInfo_follows1_fuid.txt");
		File save=new File("E:/mute_fake/REs/UidInfo_follows1_fuidEdge.txt");
		FileWriter fw=null;
		LineIterator it=null;
		try {
			fw= new FileWriter(save,true);
			it=FileUtils.lineIterator(file);
			String line;
			while(it.hasNext()){
				 line =it.nextLine();
				 String[] arr=line.split(" ");
				 StringBuilder strb=new StringBuilder();
				 for(int i=1;i<arr.length;i++){
					 strb.append(arr[0]);
					 strb.append(" ");
					 strb.append(arr[i]);
					 strb.append("\n");
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
			int count=0;
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
					count++;
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
    long len = 0L;  
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
                String line = tempString.substring(fromIndex, endIndex);//转换一行            
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
