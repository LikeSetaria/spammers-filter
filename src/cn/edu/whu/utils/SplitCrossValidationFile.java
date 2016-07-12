/**
 * 
 */
package cn.edu.whu.utils;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author bczhang
 *得到N折交叉验证的文件，如五折这分五个不相交子集，轮流作为
 */
public class SplitCrossValidationFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub//必须是规范化以后的数据
		//RandomSplit("D:/bczhang/libsvm-3.21/libsvm-3.21/windows/allFeatures_Gfollows.txt",1);
		 //splitTxt("E:\\libSVM\\libSVMSample\\scale\\allFeatures_Gfollows","E:\\libSVM\\libSVMSample\\allFeatures_Gfollows",10);
		 //splitTxt("E:\\libSVM\\libSVMSample\\scale\\onlyFollowsGraphMetrice.txt","E:\\libSVM\\libSVMSample\\onlyGraphMetrice_Gfollows",10);
		// splitTxt("E:\\libSVM\\libSVMSample\\scale\\noGraphMetrice.txt","E:\\libSVM\\libSVMSample\\noGraphMetrice",10);
		 splitTxt("E:\\libSVM\\libSVMSample\\scale\\allFeaturesRemTriRela_Gfollows.txt","E:\\libSVM\\libSVMSample\\allFeaturesRemTriRela_Gfollows",10);


		    
	}
   public static void RandomSplit(String libsvmFilePath,int Nfold){
	    Utils utils=new Utils();
	     
	    String text=utils.readFileToString(libsvmFilePath);
	    String[] textArr=text.trim().split("\n");
	    String[] fileStr=new String[Nfold];
	    int count=0;
	    int ten=textArr.length/Nfold;
	    int nCount=1;
	    for(int i=0; i<textArr.length;i++){
	    	if(count%ten==0&&nCount<Nfold){
	    		System.out.println("  sdf "+count);
	    	}
	    	count++;
	    	nCount++;
	    }
   }
  
   public static void splitTxt(String libsvmFilePath,String saveFolder,int count) {  
       try {  
           FileReader read = new FileReader(libsvmFilePath);  
           BufferedReader br = new BufferedReader(read);  
           String row;  
           List<FileWriter> flist = new ArrayList<FileWriter>(); 
           String fileName[]=new String[count];
           for (int i = 0; i < count; i++) {  
               flist.add(new FileWriter(saveFolder+"/"+ i + ".txt"));  
               fileName[i]=saveFolder+"/"+ i ;
           }  
           int rownum = 1;// 计数器  
           while ((row = br.readLine()) != null) {  
               flist.get(rownum % count).append(row + "\r\n");  
              
               rownum++;  
           }  
           for (int i = 0; i < flist.size(); i++) {  
               flist.get(i).close();  
           }  
           //得到差集
           for(int i = 0; i < fileName.length; i++){
        	   StringBuilder strb=new StringBuilder();
        	   for(int j=0;j<fileName.length;j++){
        		   if(j!=i){
        			   strb.append(FileUtils.readFileToString(new File(fileName[j]+ ".txt")));
        		   }
        	   }
        	   FileUtils.write(new File(fileName[i]+"_"+ ".txt"), strb.toString());
        	   strb=null;
           }
       } catch (FileNotFoundException e) {  
           e.printStackTrace();  
       } catch (IOException e) {  
           e.printStackTrace();  
       }  
   }  

}

