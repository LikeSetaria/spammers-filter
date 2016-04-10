package cn.edu.whu.pojo;

import java.util.HashSet;

public class Relation {
	private String user_UID;
	//他关注的用户UID
    private HashSet<String> followsListByHe = new HashSet<>();
    
	public String getUser_UID() {
		return user_UID;
	}
	public void setUser_UID(String user_UID) {
		this.user_UID = user_UID;
	}
	public HashSet<String> getFollowsByHe() {
		return followsListByHe;
	}
	public void setFollowsByHe(String uid) {
		 if(!followsListByHe.contains(uid)){
			 followsListByHe.add(uid);
		 }
	}
	@Override
	public  String toString(){
		return user_UID+"  "+followsListByHe.toString();
		
	}
	

}
