/**
 * 
 */
package cn.edu.whu.pojo;

/**
 * @author bczhang
 *这个实体主要描述一个用户的特征，包括一个用户的各种属性，一会慢慢的对其属性进行扩充
 */
public class User {
	private String userName;
	 private String UID;
	 //这个用户他的粉丝数量
	 private int fansNums;
	 //这个用户关注的用户的数量
	 private int followNums;
 public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public int getFansNums() {
		return fansNums;
	}
	public void setFansNums(int fansNums) {
		this.fansNums = fansNums;
	}
	public int getFollowNums() {
		return followNums;
	}
	public void setFollowNums(int followsNums) {
		this.followNums = followsNums;
	}
	//控制台展示
// public String  toString(){
//	 return  "UID  is  "+UID+"  fansNums	is  "+fansNums+"  followNums  is   "+followNums;
// }
 public String  toString(){
	 return  UID+"\t"+fansNums +"\t"+followNums;
 }
 
}
