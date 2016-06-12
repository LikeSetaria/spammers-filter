/**
 * 
 */
package cn.edu.whu.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * @author bczhang
 *用libSVM实验的时候，很多时候需要某个类或者某些类对分类准确率的影响，由于libSVM要求的格式固定的，所以我打算这里设计
 *一个统一的方法来处理这个问题，而不是去改以前的代码。
 *整个对象需要传入的参数包括，libSVM样本位置，要删除的特征标号（这个标号必须从小到的存在一个数组中）
 */
public class FilterLibSVMFeatures {
    private static String libSVMFilePath=null;
    //要删除的类别标号，这个标号必须要依次从小到大
   // private static int[] ind={17};
    public FilterLibSVMFeatures(String str){
    	if(str.equals("")||str==null)
    		System.out.println("文件地址有误，请检查");
    	else
    	this.libSVMFilePath=str;
    }
    
    
    public static void main(String[] args){
    	//去除个人信息特征，观察个人信息特征对分类的影响。这四个类是：userNameLen，profileLen，profileURL，ifProfile
    	//int[] ar={16,17,21,22};
    	//去除关系特征，观察关系特征对分类的影响。这四个类是：triRealtion，frinedsNum，followsNum，friendsNumDivfollowsNum
    	//int[] ar={15,18,19,20};
    	//去除微博相似度特征，观察微博相似度特征对分类的影响。weiboTextSimilarity
    	//int[] ar={8};
    	//平均时间间隔处
    	//int[] ar={20};
    	//微博源
    	//int[] ar={30,31,32,33};
    	int[] ar={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
    	FilterLibSVMFeatures fls=new FilterLibSVMFeatures("E:/libSVM/实验九/sample9.txt");
    	fls.handle("E:/libSVM/实验九/sample9min.txt",ar);
    }
    private void  handle(String save,int[] ind){
    	File file=new File(libSVMFilePath);
    	LineIterator it=null;
    	List<String> list=new ArrayList<String>();
    	StringBuilder result=new StringBuilder();
    	try {
			it=FileUtils.lineIterator(file);
			while(it.hasNext()){
				list.clear();
				String line=it.nextLine();
				String[] arr=line.split("\\s+");
				for(String ss:arr){
					list.add(ss);
				}
				for(int j=ind.length-1;j>=0;j--){
					//这里有个值得注意点地方。就是由于每次删除，数组标号都会变化，但仍是连续的从0开始
					//所以想循环的删除下标就会有问题，删除的不是自己想要的。所以这里从大的下标号开始删除
				    list.remove(ind[j]);
				}
				result.append(tranListToString(list));
				result.append("\n");
				
				//System.out.println(tranListToString(list));
			}
			FileUtils.write(new File(save), result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    /**
     * 格式化输出字符串，输入是一个libSVM数据的list
     * 输出是符合libSVM格式的字符串
     * @param list
     * @return
     */
    private static  String tranListToString(List<String> list){
    	StringBuilder strb=new StringBuilder();
    	for(int i=0;i<list.size();i++){
    		if(i==0)
    		strb.append(list.get(i)+"  ");
    		else strb.append(list.get(i)+" ");
    	}
    	return strb.toString();
    }
    
    
}
