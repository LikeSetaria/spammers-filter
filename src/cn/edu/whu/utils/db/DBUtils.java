/**
 * 
 */
package cn.edu.whu.utils.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author bczhang
 *数据库工具类
 */
public class DBUtils {

	/**
	 * @param args
	 */
	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	public static String DRIVER;
	private static ResourceBundle rb = ResourceBundle.getBundle("cn.edu.whu.utils.db.db-config");
	private DBUtils(){}
	//静态初始化块
	static{
		URL=rb.getString("jdbc.url");
		USERNAME=rb.getString("jdbc.username");
		PASSWORD=rb.getString("jdbc.password");
		DRIVER=rb.getString("jdbc.driver");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//获取数据库连接
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取连接失败。");
		}
		return  conn;
		
	}
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		try{  
		   if(rs != null)   // 关闭记录集    
	         rs.close() ;    
	       if(stmt != null)   // 关闭声明    
	         stmt.close() ;    
	       if(conn != null)  // 关闭连接对象    
	         conn.close() ;    
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        conn=getConnection();
        try {
	      stmt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql="select * from spam_graph_features";
        try {
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while(rs.next()){         
            String pass = rs.getString(1) ; // 此方法比较高效    
            System.out.println(pass);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       



        
	}
	
	

 
	

}
