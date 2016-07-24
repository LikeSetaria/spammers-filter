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
		cal.getAverageValue("E:\\spam\\spamSample\\featureVec\\average_Gfollows.txt");
	}
	
	public String getAverageValue(String libsvmFilePath){
		DecimalFormat   df=new   java.text.DecimalFormat("#.######"); 
		try {
			String text=FileUtils.readFileToString(new File(libsvmFilePath));
			String[] linearr=text.split("\n");
			double[] result=new double[39];
			//System.out.println(result.length);
			for(String ss:linearr){
				String[] arr=ss.trim().split(" +");
				//System.out.println(arr.length);
				for(int i=0;i<arr.length;i++){
					//System.out.println(Double.valueOf(arr[i]));
					result[i]=Double.valueOf(arr[i])+result[i];
				}
			}
			for(double d:result){
				System.out.print(df.format(d/linearr.length)+" ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
