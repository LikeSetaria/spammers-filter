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
    	//微博三角关系
    	//int[] ar={4,21};
    	//微博源
    	//int[] ar={30,31,32,33};
    	//图特征
    	//int[] ar={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
    	//图特征和三角形关系
    	//int[] ar={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33};
    	//int [] ar={7,8};
    	/*
    	 * 下面设计实现bagging装袋投票集成
    	 */
    	//基于微博文行为分析特征
    	//int[] ar={1,2,3,7,8,21,22,23,24,25,26,27,28,29,30,31,32,33};
    	//基于一般行为分析特征
    	//int[] ar={4,5,6,9,10,11,12,13,14,15,16,17,18,19,20,22,23,27,28,29,30,31,32,33};
    	//基于用户属性特征
    	//int[] ar={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,24,25,26};
    	//平均聚类系数算了两次，删除一次
    	//int []ar={38};
    	
    	/**
    	 * 重新做对比实验。目的是排除自己除图以外的新的特征
    	 */
    	//int [] ar={8,9,10,11,12,13,14,15,16,17,18,19,29,30,31,32};//除去时间间隔特征和微波源丰富度特征
    	//整理图特征有一些重复计算的去掉，比如nx.eigenvector_centrality_numpy(G)与gephi计算的eigenvector_centrality重复。平均聚集系数也去掉一个，留gephi计算的哪一个
    	//int[] ar={2,3};
    	//int [] ar={1,2,3,4,5,6,7,20,21,22,23,24,25,26,27,28};
    //	int ar[]={4};
//    	FilterLibSVMFeatures fls=new FilterLibSVMFeatures("E:\\normal\\normalSample\\featureVec\\selectVec.txt");
//    	fls.handle("E:\\normal\\normalSample\\featureVec\\TimeIntervalSource.txt",ar);
    	//重新设计，分类对比实验
    	//attentionRate commentRate repostRate atRate topicRate textURLrate weiboFromRepostRate triRealtion
    	//profileURL ifProfile frinedsNum followsNum friendsNumDivfollowsNum userNameLen profileLen weiboAge
    	//1topicRate textURLrate weiboFromRepostRate
    	//int ar[]={1,2,3,4,8,9,10,11,12,13,14,15,16};
    	//2 commentRate repostRate atRate
    	//int ar[]={1,5,6,7,8,9,10,11,12,13,14,15,16};
    	//3 其它 profile
    	 //int ar[]={2,3,4,5,6,7,8};
    	//int ar[]={2,3,4,5,6,7,8,9,10,14,15,16};
    	//int ar[]={1,2,3,4,5,6,7,8,11,12,13};
    	//int ar[]={15,16};
    	int ar[]={8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
    	FilterLibSVMFeatures fls=new FilterLibSVMFeatures("E:\\portrait\\features\\mbehavior.txt");
    	fls.handle("E:\\portrait\\features\\mbehavior_base.txt",ar);
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
