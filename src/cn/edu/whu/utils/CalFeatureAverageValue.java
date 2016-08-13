/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.commons.io.FileUtils;

/**
 * @author bczhang
 *计算特征的平均值
 *输入是最终的svm格式的特征向量
 *返回每一列，也就是每一个特征的平均值
 */
public class CalFeatureAverageValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CalFeatureAverageValue cal=new CalFeatureAverageValue();
		//cal.getAverageValue("E:\\spam\\spamSample\\featureVec\\spam_ColAndBet_cen.txt",3);
		//cal.getAverageValue("E:\\normal\\normalSample\\featureVec\\normal_GraphFeature_rcc_avg.txt",2);
		//cal.getAverageValue("E:\\normal\\normalSample\\featureVec\\12graph_plusRichClub.txt",14);
		//cal.getAverageValue("E:\\normal\\normalSample\\featureVec\\selectVec_RemoveTimeAndSource.txt");
		cal.getAverageValue("E:\\normal\\3.1_graphFetures\\normal_interaction_networkX.txt");
	}
	/**
	 * 
	 * @param libsvmFilePath 特征向量 形如：1747538987 0.167663373923 0.0444211207127 0.485405042716 0.167663373923 -0.173971194773
	 * @param vecLength 特征向量的长度，包括uid 如上面的长度就是6
	 * @return
	 */
	public double[] getAverageValue(String featuresFilePath){
		DecimalFormat   df=new   java.text.DecimalFormat("#.######"); 
		double	result[]=null;
		try {
			String text=FileUtils.readFileToString(new File(featuresFilePath));
			String[] linearr=text.split("\n");
			int vecLength=linearr[0].trim().split(" +").length;
			double[] acc=new double[vecLength];
		   	result  =new double[vecLength];
			for(String ss:linearr){
				String[] arr=ss.trim().split(" +");
			//	if(arr[0].equals("1.0")){
				for(int i=0;i<arr.length;i++){
					acc[i]=Double.valueOf(arr[i])+acc[i];
				//}
			}
				}
			for(int i=0;i<acc.length; i++){
				result[i]=acc[i]/(double)linearr.length;
				System.out.print(df.format(acc[i]/(double)linearr.length)+" ");
			}
			 System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
