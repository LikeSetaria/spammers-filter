/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import cn.edu.whu.utils.Utils;

/**
 * @author bczhang
 *得到libSVM需要的格式化样本 
 *合并随机化
 *输入文件1是1004515110 0.442308 0.442308 0 0.076923 0.153846 0 0.087291 6.711538
 *输入文件2是1004515110 0.442308 0.442308 0 0.076923 0.153846 0 0.087291 6.711538
 *得到一个训练文件，一个测试文件3/7
 */
public class GetLibSVM {

	/**
	 * @param args
	 */
	
	
	private static final String SELECTED_PATH="E:/spam/5_selectedFeatureVec/selectVec9.txt";
	private static final String NORMAL_PATH="E:/normal/5_selectedFeatureVec/selectVec9.txt";
	private static final String LIBSVMRESULT="E:/libSVM/实验九/sample9.txt";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createLibSVM(SELECTED_PATH,NORMAL_PATH,LIBSVMRESULT);
	}
	//1代表正类，即normal部分，2代表负类，即selected部分
	public static void createLibSVM(String path1,String path2,String save){
		File file1=new File(path1);
		File file2=new File(path2);
		LineIterator it1=null;
		LineIterator it2=null;
		Utils utils =new Utils();
		Set<String> set=new HashSet<>();
		int key=0;
		Map<Integer,String> result =new HashMap<Integer,String>();
		try {
			it1=FileUtils.lineIterator(file1);
			it2=FileUtils.lineIterator(file2);
			StringBuilder strb=null;
			while(it1.hasNext()){
				int i=1;
				strb=new StringBuilder();
				String line=it1.nextLine();
				String[] arr1=line.split(" ");
				strb.append("1  ");
				for(int j=1;j<arr1.length;j++){
					strb.append(i+":"+arr1[j]+" ");
					i++;
				}
				//System.out.println(strb);
				result.put(key, strb.toString());
				set.add(strb.toString());
				key++;
			}
			
			while(it2.hasNext()){
				int m=1;
				strb=new StringBuilder();
				String line=it2.nextLine();
				String[] arr1=line.split(" ");
				strb.append("2  ");
				for(int j=1;j<arr1.length;j++){
					strb.append(m+":"+arr1[j]+" ");
					m++;
				}
				key++;
				set.add(strb.toString());
				result.put(key, strb.toString());
				//System.out.println(strb);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> hashset=new HashSet<>();
		StringBuilder ss=new StringBuilder();
		for(Integer k:result.keySet()){
			hashset.add(result.get(k));
			ss.append(result.get(k));
			ss.append("\n");
		}
		utils.saveResultBySet(set,save);
		System.out.println(result.size());
		
	}

	/*
	 * 统一的，对各个不符合的特征条目进行规范化操作，
	 * 输入：2505594030 0.63125 0.321903 0.321903 0.174779 0.24115 0.380531 0.134956 0 0 1  ....
	 * 
	 */
	public void standardizeVec(String vecpath){
		File vecfile=new File(vecpath);
		
	}

}
