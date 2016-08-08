package cn.edu.whu.dao;
import java.sql.SQLException;
import java.util.List;

import cn.edu.whu.pojo.GFeature;
public interface GFeatureDao {
	  //添加方法
	  public void add(GFeature p)throws SQLException;
	  
	  //更新方法
	  public void update(GFeature p)throws SQLException;
	  
	  //删除方法
	  public void delete(String uid)throws SQLException;
	  
	  //查找方法
	  public GFeature findById(String uid)throws SQLException;
	  
	  //查找所有
	  public List<GFeature> findAll()throws SQLException;
}
