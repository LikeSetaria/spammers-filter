/**
 * 
 */
package cn.edu.whu.spam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
	
	
	private static final String SELECTED_PATH="E:/spam/5_selectedFeatureVec/selectVec10.txt";
	private static final String NORMAL_PATH="E:/normal/5_selectedFeatureVec/selectVec10.txt";
	private static final String LIBSVMRESULT="E:/libSVM/实验十/sample10.txt";
	private static final String LIBSVMGRAPHRESULT="E:/libSVM/graph/368followAndfriendGM.txt";
	
	//加入一个gephi计算的新特征-平均聚类系数
	private static final String SPAM_GRAPH_PATH="E:/spam/3.1_graphFetures/9graph_metric_follows.txt";
	private static final String NORMAL_GRAPH_PATH="E:/normal/3.1_graphFetures/9graph_metric_follows.txt";
	private static final String LIBSVM_GRAPH_RESULT="E:/libSVM/libSVMSample/9Features_Gfollows.txt";
	//单独计算networkX计算的新的五个特征的分类效果
	private static final String SPAM__NETX_GRAPH_PATH="E:/spam/3.1_graphFetures/341GraphFeatures_networkX.txt";
	private static final String NORMAL_NETX_GRAPH_PATH="E:/normal/3.1_graphFetures/359GraphFeatures_networkX.txt";
	private static final String LIBSVM_NETX_GRAPH_RESULT="E:/libSVM/libSVMSample/GraphFeatures_networkX.txt";
	//合并networkX和gephi算的特征
	private static final String SPAM__NETX_GEPHI_GRAPH_PATH="E:/spam/3.1_graphFetures/networkX_gephi_follows.txt";
	private static final String NORMAL_NETX_GEPHI_GRAPH_PATH="E:/normal/3.1_graphFetures/networkX_gephi_follows.txt";
	private static final String LIBSVM_NETX_GEPHI_GRAPH_RESULT="E:/libSVM/libSVMSample/networkX_gephi_follows.txt";
	//下面的图关系只使用了，gephi计算的几个
	private static final String SPAM_FINAL_PATH="E:/spam/spamSample/featureVec/selectAllVec_Gfollows.txt";
	private static final String NORMAL_FINAL_PATH="E:/normal/normalSample/featureVec/selectAllVec_Gfollows.txt";
	private static final String LIBSVMFINALRESULT="E:/libSVM/libSVMSample/allFeatures_Gfollows.txt";
	//合并所有的图特征和一般的关系
	private static final String SPAM_last_PATH="E:/spam/spamSample/featureVec/allFeatures_gephi_networkX.txt";
	private static final String NORMAL_last_PATH="E:/normal/normalSample/featureVec/allFeatures_gephi_networkX.txt";
	private static final String LIBSVMlastRESULT="E:/libSVM/libSVMSample/allFeatures_gephi_networkX.txt";
	/*
	 * 去除时间间隔特征和微博源特征作为baseline。准备另一个实验
	 */
	private static final String SPAM_remTimeAndSource_PATH="E:/spam/spamSample/featureVec/selectVec_RemoveTimeAndSource.txt";
	private static final String NORMAL_remTimeAndSource_last_PATH="E:/normal/normalSample/featureVec/selectVec_RemoveTimeAndSource.txt";
	private static final String LIBSVM_remTimeAndSource_RESULT="E:/libSVM/libSVMSample/BASELINE.txt";
	//
	private static final String SPAM_TimeAndSource_PATH="E:/spam/spamSample/featureVec/uid_graphFeatures7.txt";
	private static final String NORMAL_TimeAndSource_PATH="E:/normal/normalSample/featureVec/uid_graphFeatures7.txt";
	private static final String LIBSVM_TimeAndSource_RESULT="E:/libSVM/libSVMSample/uid_graphFeatures7.txt";
	//增加cloness,between 中心性
	private static final String SPAM_12graph_PATH="E:/spam/spamSample/featureVec/allFeatures_12graph.txt";
	private static final String NORMAL_12graph_PATH="E:/normal/normalSample/featureVec/allFeatures_12graph.txt";
	private static final String LIBSVM_12graph_RESULT="E:/libSVM/libSVMSample/allFeatures_12graph.txt";
	//rich_club 中的K取平均值
	private static final String SPAM_12graph_richClub_PATH="E:/spam/spamSample/featureVec/allFeatures_12graph_plusRichClub.txt";
	private static final String NORMAL_12graph_richClub_PATH="E:/normal/normalSample/featureVec/allFeatures_12graph_plusRichClub.txt";
	private static final String LIBSVM_12graph_richClub_RESULT="E:/libSVM/libSVMSample/AAA.txt";
	//图特征中，添加四个ego network特征
	private static final String SPAM_12graph_4ego_PATH="E:/spam/spamSample/featureVec/graph_features_plus4New.txt";
	private static final String NORMAL_12graph_4ego_PATH="E:/normal/normalSample/featureVec/graph_features_plus4New.txt";
	private static final String LIBSVM_12graph_4ego_RESULT="E:/libSVM/libSVMSample/16graph_features_plus4New.txt";
	//整体上
	private static final String SPAM_all_4ego_PATH="E:/spam/spamSample/featureVec/GraphFeature_4_new_Fea.txt";
	private static final String NORMAL_all_4ego_PATH="E:/normal/normalSample/featureVec/GraphFeature_4_new_Fea.txt";
	private static final String LIBSVM_all_4ego_RESULT="E:/libSVM/libSVMSample/GraphFeature_4_new_Fea.txt";
	//交互关系图结构特征
	private static final String SPAM_interaction_PATH="E:/spam/spamSample/interaction/12interaction_graphFeatures.txt";
	private static final String NORMAL_interaction_PATH="E:/normal/normalSample/interaction/12interaction_graphFeatures.txt";
	private static final String LIBSVM_interaction_RESULT="E:/libSVM/libSVMSample/12interaction_graphFeatures.txt";
	//交互图关系特征级邻居关系特征interaction_neighbors_graphFeatures.txt
	private static final String SPAM_inter_neighbors_PATH="E:/spam/spamSample/interaction/interaction_neighbors_graphFeatures.txt";
	private static final String NORMAL_inter_neighbors_PATH="E:/normal/normalSample/interaction/interaction_neighbors_graphFeatures.txt";
	private static final String LIBSVM_inter_neighbors_RESULT="E:/libSVM/libSVMSample/interaction_neighbors_graphFeatures.txt";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//createLibSVM(SELECTED_PATH,NORMAL_PATH,LIBSVMRESULT);
		//createLibSVM(SPAM_GRAPH_PATH,NORMAL_GRAPH_PATH,LIBSVM_GRAPH_RESULT);
		//createLibSVM(SPAM_FINAL_PATH,NORMAL_FINAL_PATH,LIBSVMFINALRESULT);
		//createLibSVM(SPAM__NETX_GRAPH_PATH,NORMAL_NETX_GRAPH_PATH,LIBSVM_NETX_GRAPH_RESULT);
		//createLibSVM(SPAM_last_PATH,NORMAL_last_PATH,LIBSVMlastRESULT);
		//createLibSVM(SPAM_12graph_PATH,NORMAL_12graph_PATH,LIBSVM_12graph_RESULT);
		//规范化所有的
		createLibSVM(SPAM_inter_neighbors_PATH,NORMAL_inter_neighbors_PATH,LIBSVM_inter_neighbors_RESULT);
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
				String[] arr1=line.trim().split(" ");
				//String[] arr1=line.trim().split("\t");
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
				String[] arr1=line.trim().split(" ");
				//String[] arr1=line.trim().split("\t");
				strb.append("2  ");
				for(int j=1;j<arr1.length;j++){
					strb.append(m+":"+arr1[j]+" ");
					m++;
				}
				key++;
				set.add(strb.toString());
				result.put(key, strb.toString());
				//System.out.println(result.size());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//进行随机化处理，到达乱序效果,更深刻的理解String
		Set<StringBuilder> resultset=new HashSet<StringBuilder>();
		StringBuilder ss=new StringBuilder();
		for(Integer k:result.keySet()){
			resultset.add(new StringBuilder(result.get(k)));
		}
		for(StringBuilder strb:resultset){
			if(strb!=null){
			ss.append(strb);
			ss.append("\n");}
		}
		
		//System.out.println(ss);
		//utils.saveResultBySet(set,save);
		System.out.println(resultset.size());
		utils.writeToFile(save, ss.toString());
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
