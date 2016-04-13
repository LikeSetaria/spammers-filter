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
		StringBuilder strb=new StringBuilder();
		for(String set:followsListByHe){
			strb.append(set);
			strb.append("\t");
			
		}
		return user_UID+"\t"+strb.toString()+"\t";
		//末尾记录 它关注的用户总数
		//return user_UID+"\t"+strb.toString()+"\t"+followsListByHe.size();
		
	}
	

}
