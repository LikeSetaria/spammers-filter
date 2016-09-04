/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;

/**
 * @author bczhang
 *
 */
public class ReplaceLibSVMLabel {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String positiveClassSetPath="E:\\portrait\\mUID.txt";
		String negativeClassSetPath="E:\\portrait\\fUID.txt";
		String totalLibSVMFilePath="E:\\portrait\\totalTFVec___.txt";
		String resultSavePath="E:\\portrait\\libSVM\\wordFrequency\\totalSVM.txt";
		//replaceLabel(positiveClassSetPath, negativeClassSetPath, totalLibSVMFilePath,resultSavePath);
		test(resultSavePath);
	}
  public static void replaceLabel(String positiveClassSetPath,String negativeClassSetPath,String totalLibSVMFilePath,String resultSavePath) throws IOException{
	  Utils utils=new Utils();
	  Set<String> posSet=utils.readToSet(positiveClassSetPath);
	  Set<String> negSet=utils.readToSet(negativeClassSetPath);
	  String[] svmLines=utils.readFileToString(totalLibSVMFilePath).trim().split("\n");
	  StringBuilder strb=new StringBuilder();
	  for(String ss:svmLines){
		  String[] arr=ss.split(" ");
		  if(posSet.contains(arr[0]))
			ss= ss.replace(arr[0], "1");
		  else if(negSet.contains(arr[0]))
			  ss=ss.replace(arr[0], "2");
		  else 
			  ss=ss.replace(arr[0], "test");
		  //System.out.println(ss);
		  strb.append(ss);
		  strb.append("\n");
	  }
	  FileUtils.write(new File(resultSavePath), strb);
	  
	  
  }
  public static void test(String path) throws IOException{
	  String []line=FileUtils.readFileToString(new File(path)).split("\n");
	  for(String ss:line){
		  String [] arr=ss.split(" ");
		  if(arr[0].equals("test"))
			  FileUtils.write(new File("E:\\portrait\\libSVM\\wordFrequency\\testSortSVM.txt"), ss+"\n", true);
			 // System.out.println(ss);
	  }
  }
}
