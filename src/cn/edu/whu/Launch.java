/**
 * 
 */
package cn.edu.whu;

import cn.edu.whu.utils.Utils;

/**
 * @author 宝超
 *入口类
 */
public class Launch {
	    //已过滤会员数据
	    private static  String SOURCE_PATH="D:\\Whuer\\Major\\weibo\\weibo_users.txt";
	    private static String RESULT_DEL_V_PATH="D:\\Whuer\\Major\\weibo\\RESULT\\weibo_users.txt";
	    //得到所有的用户名
	    private static  String SOURCE_DELETED_V_PATH="D:\\Whuer\\Major\\weibo\\RESULT\\weibo_users2.txt";	    
		private static String RESULT_GET_USERNAME_PATH="D:\\Whuer\\Major\\weibo\\RESULT\\weibo_users_name.txt";
	    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils utils=new Utils();
		FileFilter filter=new FileFilter();
		//去除含V会员数据，结果存在RESULT目录中
		//filter.Filter_V(SOURCE_PATH,SOURCE_DEL_V_PATH); 
		filter.extractUserName(SOURCE_DELETED_V_PATH, RESULT_GET_USERNAME_PATH);
	}

}
