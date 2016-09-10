/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

/**
 * @author bczhang
 *格式化生成器，把特征向量生成各种格式化文件，以便使用各种分类器
 */
public class FormatGenerators {

	public static void main(String[] args) throws IOException{
		FormatGenerators fg=new FormatGenerators();
		fg.generateARFF("E:/WEKATest.txt");
	}
	public void generateARFF(String vecPath) throws IOException{
		String vecArr[]=FileUtils.readFileToString(new File(vecPath)).split("\n");
		Map<String,String> sampleClassMap=new HashMap<>();
		sampleClassMap=getSampleClass("E:/spam/1000spamUid.txt","E:/normal/1000normalUid.txt");
		StringBuilder result=new StringBuilder();
		for(String line:vecArr){
			String arr[]=line.trim().split(" +");
            String sampleKey=arr[0];
            arr[0]=arr[arr.length-1];
            if(sampleClassMap.get(sampleKey)!=null){
            arr[arr.length-1]=sampleClassMap.get(sampleKey);
            } else System.out.println("样本类别，映射错误，请检查");
            for(String ss:arr){
            	result.append(ss);
            	result.append(",");
            }
            result.append("\n");
		}
		System.out.println(result);
	}
	/*
	 * 正负样本类别映射
	 */
	public static  Map<String,String> getSampleClass(String Class_1_Path,String Class_2_Path) throws IOException{
		Map<String,String> sampleClassMap=new HashMap<>();
		String classArr1[]=FileUtils.readFileToString(new File(Class_1_Path)).split("\n");
		String classArr2[]=FileUtils.readFileToString(new File(Class_2_Path)).split("\n");
		for(String ss:classArr1){
			sampleClassMap.put(ss.trim(), "positive");
		}
		for(String ss:classArr2){
			sampleClassMap.put(ss.trim(), "negitive");
		}
		return sampleClassMap;
	}
}
