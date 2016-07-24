/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author bczhang
 *卡方检验
 */
public class ChiSquareTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChiSquareTest chi=new ChiSquareTest();
		//chi.chisquare("E:\\spam\\spamSample\\featureVec\\average_Gfollows.txt","E:\\normal\\normalSample\\featureVec\\average_Gfollows.txt", "E:\\normal\\normalSample\\featureVec\\selectAllVec_Gfollows.txt");
		chi.calChi_square();
		//chi.differenceValue("E:\\spam\\spamSample\\featureVec\\average_Gfollows.txt", "E:\\spam\\spamSample\\featureVec\\selectAllVec_Gfollows.txt");
	}
	public  void calChi_square(){
		//i= 10 0.0 0.0 341.0 359.0 
		//i= 21 0.0 0.0 341.0 359.0 
		int Aarr[]={211,318,318,120,135,259,140,196,196,188,1,192,221,233,246,259,258,227,89,81,43,1,189,111,76,233,132,179,169,235,132,166,159,199,309,159,268,270};
		int Barr[]={183,321,321,135,43,32,140,43,114,199,1,185,204,184,164,139,114,198,55,57,6,1,66,79,6,154,127,184,152,171,126,156,56,32,187,56,143,80};
		int[] Carr={130,23,23,221,206,82,201,145,145,153,341,149,120,108,95,82,83,114,252,260,298,341,152,230,265,108,209,162,172,106,209,175,182,142,32,182,73,71};
		int[] Darr={176,38,38,224,316,327,219,316,245,160,359,174,155,175,195,220,245,161,304,302,353,359,293,280,353,205,232,175,207,188,233,203,303,327,172,303,216,279};
		int N=341;
		double result[]=new double[Aarr.length];
		DecimalFormat   df=new   java.text.DecimalFormat("#.######"); 
		for(int i=0;i<Aarr.length;i++){
		double a=Aarr[i];
		double b=Barr[i];
		double c=Carr[i];
		double d=Darr[i];
		if((a+c)*(a+b)*(b+d)*(c+d)!=0)
		result[i]=(((a*d-b*c)*(a*d-b*c))*N)/((a+c)*(a+b)*(b+d)*(c+d));
		else System.out.println("i= "+i+" "+a+" "+b+" "+c+" "+d+" ");
		}
		for(double d:result){
			System.out.print(df.format(d)+" ");
		}
	}
	/*
	 * 计算chi中A、B、C、D值
	 */
	public void chisquare(String spamAverageFilePath,String normalAverageFilePath,String spamNormalFilePath){
		 
		try {
			String[] spamaverageValues=FileUtils.readFileToString(new File(spamAverageFilePath)).split(" +");
			String[] normalaverageValues=FileUtils.readFileToString(new File(normalAverageFilePath)).split(" +");
			String allText=FileUtils.readFileToString(new File(spamNormalFilePath));
			String[] linearr=allText.split("\n");
			double spamAvg[]=new double[39];
			double normalAvg[]=new double[39];
			int totalA[]=new int [39];
			int totalC[]=new int [39];
			for(int i=0;i<spamaverageValues.length;i++){
				spamAvg[i]=Double.valueOf(spamaverageValues[i]);
				normalAvg[i]=Double.valueOf(normalaverageValues[i]);
			}
			for(String ss:linearr){
				String [] arr=ss.trim().split(" +");
				for(int j=0;j<arr.length;j++){
					double temp=Double.valueOf(arr[j]);
					double toSpamDistance=Math.abs(temp-spamAvg[j]);
					double toNormalDistance=Math.abs(temp-normalAvg[j]);
					//System.out.println(Math.abs(temp-spamAvg[j]));
					//System.out.println(Math.abs(temp-normalAvg[j]));
					if(toSpamDistance<toNormalDistance){
						System.out.print("spam"+"  ");
						totalA[j]++;//包含spam并且属于spam的个数
					}else {
						System.out.print("normal"+"  ");
						totalC[j]++;//不包含spam但是属于spam的个数
					}
				}
				System.out.println();
			}
			for(int i:totalA){
				System.out.print(i+" ");
			}
			System.out.println();
			for(int i:totalC){
				System.out.print(i+" ");
			}
		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void differenceValue(String averageFilePath,String filePath){
		DecimalFormat   df=new   java.text.DecimalFormat("#.######"); 
		try {
			String averageValues=FileUtils.readFileToString(new File(averageFilePath));
			String text=FileUtils.readFileToString(new File(filePath));
			String [] averageVal=averageValues.trim().split(" +");
			String[] linearr=text.split("\n");
			double result[]=new double[39];
			//System.out.println(averageVal.length);
			List<Double> list=new ArrayList<>();
			for(String ss:linearr){
				String[] arr=ss.trim().split(" +");
				
				for(int i=0;i<arr.length;i++){
					result[i]=Math.pow(Double.valueOf(arr[i])-Double.valueOf(averageVal[i]),2)/Double.valueOf(averageVal[i])+result[i];
				}
				 
			}
		   //Arrays.sort(result);
		   for( double d:result){
			   System.out.print(df.format(d)+" ");
		   }
		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
