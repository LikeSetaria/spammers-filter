/**
 * 
 */
package cn.edu.whu.pojo;

/**
 * @author bczhang
 *图特征
 */
public class GraphFeature {
 
  public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFriend_GraphDensit() {
		return friend_GraphDensit;
	}
	public void setFriend_GraphDensit(String friend_GraphDensit) {
		this.friend_GraphDensit = friend_GraphDensit;
	}
	public String getFriend_EigenvectorCentrality() {
		return friend_EigenvectorCentrality;
	}
	public void setFriend_EigenvectorCentrality(String friend_EigenvectorCentrality) {
		this.friend_EigenvectorCentrality = friend_EigenvectorCentrality;
	}
	public String getFriend_GraphDistance() {
		return friend_GraphDistance;
	}
	public void setFriend_GraphDistance(String friend_GraphDistance) {
		this.friend_GraphDistance = friend_GraphDistance;
	}
	public String getFriend_Modularity() {
		return friend_Modularity;
	}
	public void setFriend_Modularity(String friend_Modularity) {
		this.friend_Modularity = friend_Modularity;
	}
	public String getFriend_WeightedDegree() {
		return friend_WeightedDegree;
	}
	public void setFriend_WeightedDegree(String friend_WeightedDegree) {
		this.friend_WeightedDegree = friend_WeightedDegree;
	}
	public String getFriend_Degree() {
		return friend_Degree;
	}
	public void setFriend_Degree(String friend_Degree) {
		this.friend_Degree = friend_Degree;
	}
	public String getFollow_GraphDensit() {
		return follow_GraphDensit;
	}
	public void setFollow_GraphDensit(String follow_GraphDensit) {
		this.follow_GraphDensit = follow_GraphDensit;
	}
	public String getFollow_EigenvectorCentrality() {
		return follow_EigenvectorCentrality;
	}
	public void setFollow_EigenvectorCentrality(String follow_EigenvectorCentrality) {
		this.follow_EigenvectorCentrality = follow_EigenvectorCentrality;
	}
	public String getFollow_GraphDistance() {
		return follow_GraphDistance;
	}
	public void setFollow_GraphDistance(String follow_GraphDistance) {
		this.follow_GraphDistance = follow_GraphDistance;
	}
	public String getFollow_Modularity() {
		return follow_Modularity;
	}
	public void setFollow_Modularity(String follow_Modularity) {
		this.follow_Modularity = follow_Modularity;
	}
	public String getFollow_WeightedDegree() {
		return follow_WeightedDegree;
	}
	public void setFollow_WeightedDegree(String follow_WeightedDegree) {
		this.follow_WeightedDegree = follow_WeightedDegree;
	}
	public String getFollow_Degree() {
		return follow_Degree;
	}
	public void setFollow_Degree(String follow_Degree) {
		this.follow_Degree = follow_Degree;
	}
private String uid="";
  private String friend_GraphDensit="";
  private String friend_EigenvectorCentrality="";
  private String friend_GraphDistance="";
  private String friend_Modularity="";
  private String friend_WeightedDegree="";
  private String friend_Degree="";
  private String follow_GraphDensit="";
  private String follow_EigenvectorCentrality="";
  private String follow_GraphDistance="";
  private String follow_Modularity="";
  private String follow_WeightedDegree="";
  private String follow_Degree="";
  public String toString(){
	  String str=uid+" "+friend_GraphDensit+" "+friend_EigenvectorCentrality+" "+friend_GraphDistance+" "+friend_Modularity+" "+friend_WeightedDegree+" "+friend_Degree+" "
			  +follow_GraphDensit+" "+follow_EigenvectorCentrality+" "+follow_GraphDistance+" "+follow_Modularity+" "+follow_WeightedDegree+" "+follow_Degree;
	  return str;  
  }
}
