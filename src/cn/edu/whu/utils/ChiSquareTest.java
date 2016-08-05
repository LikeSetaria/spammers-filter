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
		//chi.chisquare("E:\\spam\\spamSample\\featureVec\\average_Gfollows.txt","E:\\normal\\normalSample\\featureVec\\average_Gfollows.txt", "E:\\normal\\normalSample\\featureVec\\selectAllVec_Gfollows.txt",39);
		//chi.calChi_square();
		
		//计算networkX计算出的图特征的chi-square
		chi.chisquare("E:\\spam\\spamSample\\featureVec\\GraphFeature_4_new_Fea.txt",
		"E:\\normal\\normalSample\\featureVec\\GraphFeature_4_new_Fea.txt");
		//chi.calChi_square();
		//chi.differenceValue("E:\\spam\\spamSample\\featureVec\\average_Gfollows.txt", "E:\\spam\\spamSample\\featureVec\\selectAllVec_Gfollows.txt");
	}
	public  void calChi_square(){
		//i= 10 0.0 0.0 341.0 359.0 
		//i= 21 0.0 0.0 341.0 359.0 
		//allFeature,不包含微博文本相似度特征，包含gephi计算的六个特征
		//int Aarr[]={211,318,318,120,135,259,140,196,196,188,1,192,221,233,246,259,258,227,89,81,43,1,189,111,76,233,132,179,169,235,132,166,159,199,309,159,268,270};
		//int Barr[]={183,321,321,135,43,32,140,43,114,199,1,185,204,184,164,139,114,198,55,57,6,1,66,79,6,154,127,184,152,171,126,156,56,32,187,56,143,80};
		//int[] Carr={130,23,23,221,206,82,201,145,145,153,341,149,120,108,95,82,83,114,252,260,298,341,152,230,265,108,209,162,172,106,209,175,182,142,32,182,73,71};
		//int[] Darr={176,38,38,224,316,327,219,316,245,160,359,174,155,175,195,220,245,161,304,302,353,359,293,280,353,205,232,175,207,188,233,203,303,327,172,303,216,279};
		//计算networkX计算的五个特征 ,对spam没有提纯，直接用的506和400的数据
//		int Aarr[]={404,406,276,404,378};
//		int Barr[]={188,203,158,188,225};
//		int[] Carr={102,100,230,102,128};
//		int[] Darr={212,197,242,212,175};
		//计算三个单节点的贡献度
//		int Aarr[]={171,189,278};
//		int Barr[]={170,152,63};
//		int[] Carr={186,66,181};
//		int[] Darr={173,293,178};
//		int Aarr[]={194 ,310 ,291};
//		int Barr[]={166,214,178};
//		int[] Carr={147,31,50};
//		int[] Darr={193,145,181 };
		int Aarr[]={260};
		int Barr[]={141};
		int[] Carr={81};
		int[] Darr={218};
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
	 * 目标样本如spam计算出的就是A、C 而用normal带入计算的则是B、D
	 */
     
	public void chisquare(String spamFilePath,String normalFilePath){
		CalFeatureAverageValue calAvg=new CalFeatureAverageValue();
		 
		try {
			//String[] spamaverageValues=FileUtils.readFileToString(new File(spamAverageFilePath)).split(" +");
			//String[] normalaverageValues=FileUtils.readFileToString(new File(normalAverageFilePath)).split(" +");
			String spamallText=FileUtils.readFileToString(new File(spamFilePath));
			String normalallText=FileUtils.readFileToString(new File(normalFilePath));
			String[] spamlinearr=spamallText.split("\n");
			String[] normallinearr=normalallText.split("\n");
			int vecLength=spamlinearr[0].trim().split(" +").length;
			//double spamAvg[]=new double[vecLength];
			//double normalAvg[]=new double[vecLength];
			double spamAvg[]=calAvg.getAverageValue(spamFilePath);
			double normalAvg[]=calAvg.getAverageValue(normalFilePath);
			int totalA[]=new int [vecLength];
			int totalC[]=new int [vecLength];
			int totalB[]=new int [vecLength];
			int totalD[]=new int [vecLength];
//			for(int i=0;i<spamaverageValues.length;i++){
//				//System.out.println(spamaverageValues.length);
//				spamAvg[i]=Double.valueOf(spamaverageValues[i]);
//				normalAvg[i]=Double.valueOf(normalaverageValues[i]);
//			}
			for(String ss:spamlinearr){
				String [] arr=ss.trim().split(" +");
				for(int j=0;j<arr.length;j++){
					
					double temp=Double.valueOf(arr[j]);
					double toSpamDistance=Math.abs(temp-spamAvg[j]);
					double toNormalDistance=Math.abs(temp-normalAvg[j]);
					if(toSpamDistance<toNormalDistance){
						totalA[j]++;//包含spam并且属于spam的个数
					}else {
						totalC[j]++;//不包含spam但是属于spam的个数
					}
				}
				
			}
			for(String ss:normallinearr){
				String [] arr=ss.trim().split(" +");
				for(int j=0;j<arr.length;j++){
					
					double temp=Double.valueOf(arr[j]);
					double toSpamDistance=Math.abs(temp-spamAvg[j]);
					double toNormalDistance=Math.abs(temp-normalAvg[j]);
					if(toSpamDistance<toNormalDistance){
						totalB[j]++;//包含spam并且属于spam的个数
					}else {
						totalD[j]++;//不包含spam但是属于spam的个数
					}
				}
				
			}
//			for(int i:totalA){
//				System.out.print(i+" ");
//			}
//			System.out.println();
//			for(int i:totalC){
//				System.out.print(i+" ");
//			}
			double chiresult[]=new double[vecLength];
			DecimalFormat   df=new   java.text.DecimalFormat("#.######"); 
			for(int i=0;i<vecLength;i++){
			double a=totalA[i];
			double b=totalB[i];
			double c=totalC[i];
			double d=totalD[i];
			if((a+c)*(a+b)*(b+d)*(c+d)!=0)
			chiresult[i]=(((a*d-b*c)*(a*d-b*c))*spamlinearr.length)/((a+c)*(a+b)*(b+d)*(c+d));
			else System.out.println("i= "+i+" "+a+" "+b+" "+c+" "+d+" ");
			}
			for(double d:chiresult){
				System.out.print(df.format(d)+" ");
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
