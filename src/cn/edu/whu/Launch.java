/**
 * 
 */
package cn.edu.whu;

import cn.edu.whu.utils.PreprocessText;
import cn.edu.whu.utils.Utils;

/**
 * @author ����
 *�����
 */
public class Launch {
	    //�ѹ��˻�Ա����
	    private static  String SOURCE_PATH="D:\\Whuer\\Major\\weibo\\weibo_users.txt";
	    private static String RESULT_DEL_V_PATH="D:\\Whuer\\Major\\weibo\\RESULT\\weibo_users.txt";
	    //�õ����е��û���
	    private static  String SOURCE_DELETED_V_PATH="D:\\Whuer\\Major\\weibo\\RESULT\\weibo_users2.txt";	    
		private static String RESULT_GET_USERNAME_PATH="D:\\Whuer\\Major\\weibo\\RESULT\\weibo_users_name.txt";
		 private final static String  FOLLOWS_USER_TXT="E:/follows_users_25W_realated.txt";
		 private final static String  FOLLOWS_USER_RESULTS="E:/temp/relation_results_as_fans_25w.txt";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
		FileFilter filter=new FileFilter();
		PreprocessText process=new PreprocessText();
		//ȥ����V��Ա���ݣ��������RESULTĿ¼��
		//filter.Filter_V(SOURCE_PATH,SOURCE_DEL_V_PATH);
		/*
		 * 
		 */
		//filter.extractUserName(SOURCE_DELETED_V_PATH, RESULT_GET_USERNAME_PATH);
		/*
		 * 提取相关用户的关注关系，weibo_follows.csv是源数据，potential_spammers_4_uid_10w.txt是样本，最后一个是保存位置
		 * PreprocessText.extractPartOfRelation方法中此处控制是只提取potential_spammers_4_uid_25w.txt作为粉丝，相关的关系.还是只有涉及的用户都提取出来
		 * //if(set.contains(arr[0])||set.contains(arr[1]))
		 * if(set.contains(arr[0])){
		 */
		//process.extractPartOfRelation("D:/Whuer/FudanData/weibo_follows.csv", "E:/potential_spammers_4_uid_25w.txt","E:/user_is_concered.txt");
		/*
		 * 提取用户关注关系，第一个是用户ID，后面紧跟着的是他所关注的所有的uid
		 */
		//process.extractFollows(FOLLOWS_USER_TXT,FOLLOWS_USER_RESULTS);
		/*
		 *提取既有关注别人，又有粉丝的用户ID 
		 */
		process.extractBoth("E:/users_his_fans_25W.txt","E:/relation_results_as_fans_25w.txt","F:coll.txt");
	}

}
