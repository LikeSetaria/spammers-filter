/**
 * 
 */
package cn.edu.whu;

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
	    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
		FileFilter filter=new FileFilter();
		//ȥ����V��Ա���ݣ��������RESULTĿ¼��
		//filter.Filter_V(SOURCE_PATH,SOURCE_DEL_V_PATH); 
		filter.extractUserName(SOURCE_DELETED_V_PATH, RESULT_GET_USERNAME_PATH);
	}

}
