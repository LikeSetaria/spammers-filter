/**
 * 
 */
package cn.edu.whu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.whu.dao.GFeatureDao;
import cn.edu.whu.pojo.GFeature;
import cn.edu.whu.utils.db.DBUtils;

/**
 * @author bczhang
 *
 */
public class GFeatureDaoImpl implements GFeatureDao {

	/* (non-Javadoc)
	 * @see cn.edu.whu.dao.GFeatureDao#add(cn.edu.whu.pojo.GFeature)
	 */
	@Override
	public void add(GFeature gf) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="insert into GRAPH_FEATURE(USERID,BET_CENTRALITY,CLO_CENTRALITY,DEGREE_CENTRALITY,RICH_CLUB_COFF,DEGREE_ASS_COFF"
				+ "CENTRALITY,AVERAGEDEGREE,DENSITY,PATHLENGTH,MODULARITY,WHEIGHTEDDEGREE,AVERAGECLUSCOFF,average_neighbor_degree,"
				+ "median_neighbor_degree,embeddedness,structural_holes,USER_TYPE)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
		 conn = DBUtils.getConnection();
		             ps = conn.prepareStatement(sql);
		             ps.setString(1, gf.getUid());
		             ps.setDouble(2, Double.valueOf(gf.getBetweenness_centrality()));
		             ps.setDouble(3, Double.valueOf(gf.getCloseness_centrality()));
		             ps.setDouble(4, Double.valueOf(gf.getDegree_centrality()));
		             ps.setDouble(5, Double.valueOf(gf.getRich_club_coefficient()));
		             ps.setDouble(6, Double.valueOf(gf.getDegree_assortativity_coefficient()));
		             ps.setDouble(7, Double.valueOf(gf.getEigenvectorCentrality()));
		             ps.setDouble(8, Double.valueOf(gf.getAverageDegree()));
		             ps.setDouble(9, Double.valueOf(gf.getDensity()));
		             ps.setDouble(10, Double.valueOf(gf.getPathLength()));
		             ps.setDouble(11, Double.valueOf(gf.getPathLength()));
		             ps.setDouble(12, Double.valueOf(gf.getModularity()));
		             ps.setDouble(13, Double.valueOf(gf.getWheightedDegree()));
		             ps.setDouble(14, Double.valueOf(gf.getAverageClusteringCoefficient()));
		             ps.setDouble(15, Double.valueOf(gf.getAverage_neighbor_degree()));
		             ps.setDouble(16, Double.valueOf(gf.getMedian_neighbor_degree()));
		             ps.setDouble(17, Double.valueOf(gf.getEmbeddedness()));
		             ps.setString(18, gf.getStructural_holes());
		             ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException("添加数据失败");

		}
		finally{
			DBUtils.close(null, ps, conn);
		}

	}

	/* (non-Javadoc)
	 * @see cn.edu.whu.dao.GFeatureDao#update(cn.edu.whu.pojo.GFeature)
	 */
	@Override
	public void update(GFeature gf) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="update GRAPH_FEATURE set USERID=?,BET_CENTRALITY=?,CLO_CENTRALITY=?,DEGREE_CENTRALITY=?,RICH_CLUB_COFF=?,DEGREE_ASS_COFF=?,"
				+ "CENTRALITY=?,AVERAGEDEGREE=?,DENSITY=?,PATHLENGTH=?,MODULARITY=?,WHEIGHTEDDEGREE=?,AVERAGECLUSCOFF=?,average_neighbor_degree=?,"
				+ "median_neighbor_degree=?,embeddedness=?,structural_holes=?,USER_TYPE=? where USERID=?";
		try{
		 conn = DBUtils.getConnection();
		             ps = conn.prepareStatement(sql);
		             ps.setString(1, gf.getUid());
		             ps.setDouble(2, Double.valueOf(gf.getBetweenness_centrality()));
		             ps.setDouble(3, Double.valueOf(gf.getCloseness_centrality()));
		             ps.setDouble(4, Double.valueOf(gf.getDegree_centrality()));
		             ps.setDouble(5, Double.valueOf(gf.getRich_club_coefficient()));
		             ps.setDouble(6, Double.valueOf(gf.getDegree_assortativity_coefficient()));
		             ps.setDouble(7, Double.valueOf(gf.getEigenvectorCentrality()));
		             ps.setDouble(8, Double.valueOf(gf.getAverageDegree()));
		             ps.setDouble(9, Double.valueOf(gf.getDensity()));
		             ps.setDouble(10, Double.valueOf(gf.getPathLength()));
		             ps.setDouble(11, Double.valueOf(gf.getModularity()));
		             ps.setDouble(12, Double.valueOf(gf.getWheightedDegree()));
		             ps.setDouble(13, Double.valueOf(gf.getAverageClusteringCoefficient()));
		             ps.setDouble(14, Double.valueOf(gf.getAverage_neighbor_degree()));
		             ps.setDouble(15, Double.valueOf(gf.getMedian_neighbor_degree()));
		             ps.setDouble(16, Double.valueOf(gf.getEmbeddedness()));
		             ps.setDouble(17, Double.valueOf(gf.getStructural_holes()));
		             ps.setString(18, gf.getUser_type());
		             ps.setString(19, gf.getUid());
		             ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException("更新数据失败");

		}
		finally{
			DBUtils.close(null, ps, conn);
		}
	}

	/* (non-Javadoc)
	 * @see cn.edu.whu.dao.GFeatureDao#delete(int)
	 */
	@Override
	public void delete(String uid) throws SQLException {
		// TODO Auto-generated method stub
	    Connection conn = null;
	             PreparedStatement ps = null;
	             String sql = "delete from GRAPH_FEATURE where userid=?";
	             try{
	                 conn = DBUtils.getConnection();
	                 ps = conn.prepareStatement(sql);
	                ps.setString(1,uid);
	                 ps.executeUpdate();
	            }catch(SQLException e){
	                e.printStackTrace();
	                 throw new SQLException(" 删除数据失败");
	             }finally{
	                 DBUtils.close(null, ps, conn);
	             }        
	         }
	

	/* (non-Javadoc)
	 * @see cn.edu.whu.dao.GFeatureDao#findById(int)
	 */
	@Override
	public GFeature findById(String uid) throws SQLException {
		// TODO Auto-generated method stub
	    Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GFeature gf=null;
        String sql = "select * from GRAPH_FEATURE where userid=?";
        try{
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,uid);
            rs=ps.executeQuery();
            if(rs.next()){
            	 gf=new GFeature();
            	 gf.setUid(rs.getString(1));
	             gf.setBetweenness_centrality(String.valueOf(rs.getFloat(2)));
	             gf.setCloseness_centrality(String.valueOf(rs.getFloat(3)));
	             gf.setDegree_centrality(String.valueOf(rs.getFloat(4)));
	             gf.setRich_club_coefficient(String.valueOf(rs.getFloat(5)));
	             gf.setDegree_assortativity_coefficient(String.valueOf(rs.getFloat(6)));
	             gf.setEigenvectorCentrality(String.valueOf(rs.getFloat(7)));
	             gf.setAverageDegree(String.valueOf(rs.getFloat(8)));
	             gf.setDensity(String.valueOf(rs.getFloat(9)));
	             gf.setPathLength(String.valueOf(rs.getFloat(10)));
	             gf.setModularity(String.valueOf(rs.getFloat(11)));
	             gf.setWheightedDegree(String.valueOf(rs.getFloat(12)));
	             gf.setAverageClusteringCoefficient(String.valueOf(rs.getFloat(13)));
	             gf.setAverage_neighbor_degree(String.valueOf(rs.getFloat(14)));
	             gf.setMedian_neighbor_degree(String.valueOf(rs.getFloat(15)));
	             gf.setEmbeddedness(String.valueOf(rs.getFloat(16)));
	             gf.setStructural_holes(String.valueOf(rs.getString(17)));
	             gf.setUser_type(rs.getString(18));
            }
       }catch(SQLException e){
           e.printStackTrace();
            throw new SQLException("根据id查询失败");
        }finally{
            DBUtils.close(rs, ps, conn);
        }  
        return gf;
    }
		
	 

	/* (non-Javadoc)
	 * @see cn.edu.whu.dao.GFeatureDao#findAll()
	 */
	@Override
	public List<GFeature> findAll() throws SQLException {
		// TODO Auto-generated method stub
	    Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GFeature gf=null;
        String sql = "select * from GRAPH_FEATURE ";
        List<GFeature>list=new ArrayList<GFeature>();
        try{
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            
            rs=ps.executeQuery();
            while(rs.next()){
            	 gf=new GFeature();
            	 gf.setUid(rs.getString(1));
	             gf.setBetweenness_centrality(String.valueOf(rs.getFloat(2)));
	             gf.setCloseness_centrality(String.valueOf(rs.getFloat(3)));
	             gf.setDegree_centrality(String.valueOf(rs.getFloat(4)));
	             gf.setRich_club_coefficient(String.valueOf(rs.getFloat(5)));
	             gf.setDegree_assortativity_coefficient(String.valueOf(rs.getFloat(6)));
	             gf.setEigenvectorCentrality(String.valueOf(rs.getFloat(7)));
	             gf.setAverageDegree(String.valueOf(rs.getFloat(8)));
	             gf.setDensity(String.valueOf(rs.getFloat(9)));
	             gf.setPathLength(String.valueOf(rs.getFloat(10)));
	             gf.setPathLength(String.valueOf(rs.getFloat(11)));
	             gf.setModularity(String.valueOf(rs.getFloat(12)));
	             gf.setWheightedDegree(String.valueOf(rs.getFloat(13)));
	             gf.setAverageClusteringCoefficient(String.valueOf(rs.getFloat(14)));
	             gf.setAverage_neighbor_degree(String.valueOf(rs.getFloat(15)));
	             
	             gf.setEmbeddedness(String.valueOf(rs.getFloat(16)));
	             gf.setStructural_holes(String.valueOf(rs.getString(17)));
	             gf.setUser_type(rs.getString(18));
	             list.add(gf);
	             
            }
       }catch(SQLException e){
           e.printStackTrace();
            throw new SQLException("全查询失败");
        }finally{
            DBUtils.close(rs, ps, conn);
        }  
        return list;
	}

}
