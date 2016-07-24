/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author bczhang
 *基于libsvm结果集成
 */
public class IntegrationVoted {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
   Utils utils=new Utils();
   //getTestSampleOut("E:\\libSVM\\libSVMSample\\bagging\\weiboTextBehaviorFeatures","E:\\libSVM\\libSVMSample\\bagging\\weiboTextBehaviorFeatures\\out\\real");
   patchVote();
	}
	public static void patchVote(){
		 String []args={"E:\\libSVM\\libSVMSample\\bagging\\weiboTextBehaviorFeatures\\out","E:\\libSVM\\libSVMSample\\bagging\\otherBehaviorFeatures\\out",
				 "E:\\libSVM\\libSVMSample\\bagging\\attributeFeatures\\out","E:\\libSVM\\libSVMSample\\bagging\\GraphMetrice\\out",
		 "E:\\libSVM\\libSVMSample\\bagging\\weiboTextBehaviorFeatures\\out\\real"};
		 for(int i=0;i<10;i++){
			 vote(args,i+"");
		 }
	}
  public static double vote(String[] args,String n){

	 double[] vote=null;
	 int total=0;
	  try {
		String[] out1=FileUtils.readFileToString(new File(args[0]+"\\out_"+n+".txt")).trim().split("\n");
		String[] out2=FileUtils.readFileToString(new File(args[1]+"\\out_"+n+".txt")).trim().split("\n");
		String[] out3=FileUtils.readFileToString(new File(args[2]+"\\out_"+n+".txt")).trim().split("\n");
		String[] out4=FileUtils.readFileToString(new File(args[3]+"\\out_"+n+".txt")).trim().split("\n");
		String[] testSample=FileUtils.readFileToString(new File(args[4]+"\\"+n+"_real.txt")).trim().split("\n");
		 vote=new double[testSample.length];
		for(int i=0;i<testSample.length;i++){
			//System.out.println(out1[i]+"  "+testSample[i]);
			if(out1[i].equals(testSample[i]+".0")){
				vote[i]=vote[i]+0.88;
			}else{
				vote[i]=vote[i]-0.88;
			}
			if(out2[i].equals(testSample[i]+".0")){
				vote[i]=vote[i]+0.66;
			}else{
				vote[i]=vote[i]-0.66;
			}
			if(out3[i].equals(testSample[i]+".0")){
				vote[i]=vote[i]+0.66;
			}else{
				vote[i]=vote[i]-0.66;
			}
//			if(out4[i].equals(testSample[i]+".0")){
//				vote[i]=vote[i]+0.80;
//			}else{
//				vote[i]=vote[i]-0.80;
//			}
		}
		for(double in:vote){
			if(in>0.0)total++;
			//System.out.print(in+" ");
			
		}
		System.out.print((double)total/(double)vote.length+" ");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return (double)total/(double)vote.length;
  }
  public static void getTestSampleOut(String OutFolderPath,String saveOutFolder){
	  Utils utils=new Utils();
	  for(int i=0;i<10;i++){
		  utils.saveResultByList(utils.readToList(OutFolderPath+"/"+i+".txt"), saveOutFolder+"/"+i+"_real.txt");
		  
		
	  }
  }
}
