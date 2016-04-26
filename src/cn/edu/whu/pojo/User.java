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
	 private int followNums;
	//这个用户关注的用户的数量
	 private int friendNums;
    //关注数与粉丝数的比率
	 private double friDivFolRate;
	 //用户关注度=Nfriend/(Nfriend+Nfollow)
	 private double friendsRate;
	 //简介信息是否为空,false代表简介为空
	 private boolean profile;
		//微博转发数
	 private int reposts_count;
	 //微博评论数
	 private int comment_count;
	 //微博来源
	 private char source;
	 //微博创建时间
	 private String created_at;
	 public boolean isProfile() {
		return profile;
	}
	public void setProfile(boolean profile) {
		this.profile = profile;
	}
	public int getReposts_count() {
		return reposts_count;
	}
	public void setReposts_count(int reposts_count) {
		this.reposts_count = reposts_count;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public char getSource() {
		return source;
	}
	public void setSource(char source) {
		this.source = source;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	 public double getFriendsRate() {
		return friendsRate;
	}
	public void setFriendsRate(double friendsRate) {
		this.friendsRate = friendsRate;
	}
	public double getFriDivFolRate() {
		return friDivFolRate;
	}
	public void setFriDivFolRate(double friDivFolRate) {
		this.friDivFolRate = friDivFolRate;
	}
	public int getFollowNums() {
		return followNums;
	}
	public void setFollowNums(int followNums) {
		this.followNums = followNums;
	}
	public int getFriendNums() {
		return friendNums;
	}
	public void setFriendNums(int friendNums) {
		this.friendNums = friendNums;
	}


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
	 
	//控制台展示
// public String  toString(){
//	 return  "UID  is  "+UID+"  fansNums	is  "+fansNums+"  followNums  is   "+followNums;
// }
	//以tab为分割
// public String  toString(){
//	 return  UID+"\t"+friendNums +"\t"+followNums+"\t"+friDivFolRate+"\t";//+friendsRate;
// }
	//以空格为分割
 public String  toString(){
	 return  this.UID+" "+this.friendNums +" "+this.followNums;//+friendsRate;
 }
}
