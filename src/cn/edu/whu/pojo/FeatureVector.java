/**
 * 
 */
package cn.edu.whu.pojo;

/**
 * @author bczhang
 *特征向量，这里存储用户的所有的特征向量。
 */
public class FeatureVector {
     public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAttentionRate() {
		return attentionRate;
	}
	public void setAttentionRate(String attentionRate) {
		this.attentionRate = attentionRate;
	}
	public String getCommentRate() {
		return commentRate;
	}
	public void setCommentRate(String commentRate) {
		this.commentRate = commentRate;
	}
	public String getRepostRate() {
		return repostRate;
	}
	public void setRepostRate(String repostRate) {
		this.repostRate = repostRate;
	}
	public String getAtRate() {
		return atRate;
	}
	public void setAtRate(String atRate) {
		this.atRate = atRate;
	}
	public String getTopicRate() {
		return topicRate;
	}
	public void setTopicRate(String topicRate) {
		this.topicRate = topicRate;
	}
	public String getTextURLrate() {
		return textURLrate;
	}
	public void setTextURLrate(String textURLrate) {
		this.textURLrate = textURLrate;
	}
	public String getWeiboFromRepostRate() {
		return weiboFromRepostRate;
	}
	public void setWeiboFromRepostRate(String weiboFromRepostRate) {
		this.weiboFromRepostRate = weiboFromRepostRate;
	}
	public String getWeiboTextSimilarity() {
		return weiboTextSimilarity;
	}
	public void setWeiboTextSimilarity(String weiboTextSimilarity) {
		this.weiboTextSimilarity = weiboTextSimilarity;
	}
	public String getIntervalRate() {
		return intervalRate;
	}
	public void setIntervalRate(String intervalRate) {
		this.intervalRate = intervalRate;
	}
	public String getTriRealtion() {
		return triRealtion;
	}
	public void setTriRealtion(String triRealtion) {
		this.triRealtion = triRealtion;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	public String getIfProfile() {
		return ifProfile;
	}
	public void setIfProfile(String ifProfile) {
		this.ifProfile = ifProfile;
	}

     public String getTimeItvalLess5() {
		return timeItvalLess5;
	}
	public void setTimeItvalLess5(String timeItvalLess5) {
		this.timeItvalLess5 = timeItvalLess5;
	}
	public String getTimeItvalLess10() {
		return timeItvalLess10;
	}
	public void setTimeItvalLess10(String timeItvalLess10) {
		this.timeItvalLess10 = timeItvalLess10;
	}
	public String getTimeItvalLess30() {
		return timeItvalLess30;
	}
	public void setTimeItvalLess30(String timeItvalLess30) {
		this.timeItvalLess30 = timeItvalLess30;
	}
	public String getTimeItvalLess60() {
		return timeItvalLess60;
	}
	public void setTimeItvalLess60(String timeItvalLess60) {
		this.timeItvalLess60 = timeItvalLess60;
	}
	public String getTimeItvalLess1440() {
		return timeItvalLess1440;
	}
	public void setTimeItvalLess1440(String timeItvalLess1440) {
		this.timeItvalLess1440 = timeItvalLess1440;
	}
	public String getTimeItvalLessmore() {
		return timeItvalLessmore;
	}
	public void setTimeItvalLessmore(String timeItvalLessmore) {
		this.timeItvalLessmore = timeItvalLessmore;
	}

     public String getFrinedsNum() {
		return frinedsNum;
	}
	public void setFrinedsNum(String frinedsNum) {
		this.frinedsNum = frinedsNum;
	}
	public String getFollowsNum() {
		return followsNum;
	}
	public void setFollowsNum(String followsNum) {
		this.followsNum = followsNum;
	}
	public String getFriendsNumDivfollowsNum() {
		return friendsNumDivfollowsNum;
	}
	public void setFriendsNumDivfollowsNum(String friendsNumDivfollowsNum) {
		this.friendsNumDivfollowsNum = friendsNumDivfollowsNum;
	}
	public String getUserNameLen() {
		return userNameLen;
	}
	public void setUserNameLen(String userNameLen) {
		this.userNameLen = userNameLen;
	}
	public String getProfileLen() {
		return profileLen;
	}
	public void setProfileLen(String profileLen) {
		this.profileLen = profileLen;
	}
	 private String uid;
     private String attentionRate;
     private String commentRate;
     private String repostRate;
     private String atRate;
     private String topicRate;
     private String textURLrate;
     private String weiboFromRepostRate;
     private String weiboTextSimilarity;
     private String intervalRate;
     private String triRealtion;
     private String profileURL;
     private String ifProfile;
     public String getTimeItvalLess0() {
		return timeItvalLess0;
	}
	public void setTimeItvalLess0(String timeItvalLess0) {
		this.timeItvalLess0 = timeItvalLess0;
	}
	public String getTimeItvalLess2() {
		return timeItvalLess2;
	}
	public void setTimeItvalLess2(String timeItvalLess2) {
		this.timeItvalLess2 = timeItvalLess2;
	}
	public String getTimeItvalLess20() {
		return timeItvalLess20;
	}
	public void setTimeItvalLess20(String timeItvalLess20) {
		this.timeItvalLess20 = timeItvalLess20;
	}
	public String getTimeItvalLess120() {
		return timeItvalLess120;
	}
	public void setTimeItvalLess120(String timeItvalLess120) {
		this.timeItvalLess120 = timeItvalLess120;
	}
	public String getTimeItvalLess300() {
		return timeItvalLess300;
	}
	public void setTimeItvalLess300(String timeItvalLess300) {
		this.timeItvalLess300 = timeItvalLess300;
	}
	   public String getMeanInterval() {
			return meanInterval;
		}
		public void setMeanInterval(String meanInterval) {
			this.meanInterval = meanInterval;
		}
	private String timeItvalLess0;
     private String timeItvalLess2;
	 private String timeItvalLess5;
     private String timeItvalLess10;
     private String timeItvalLess20;
     private String timeItvalLess30;
     private String timeItvalLess60;
     private String timeItvalLess120;
     private String timeItvalLess300;
     private String timeItvalLess1440;
     private String timeItvalLessmore;
	 private String meanInterval;
	 private  String frinedsNum;
     private String followsNum;
     private String friendsNumDivfollowsNum;
     private String userNameLen;
     private String profileLen;
     public String getWeiboSource() {
		return weiboSource;
	}
	public void setWeiboSource(String weiboSource) {
		this.weiboSource = weiboSource;
	}
	private String weiboSource;
	private String weiboAge;
public String getWeiboAge() {
		return weiboAge;
	}
	public void setWeiboAge(String weiboAge) {
		this.weiboAge = weiboAge;
	}
	//     public  String toString(){
//    	 String str=uid+" "+attentionRate+" "+commentRate+" "+repostRate+" "+atRate+" "+topicRate+" "+textURLrate+" "+weiboFromRepostRate+" "
//    			  +weiboTextSimilarity+" "+ timeItvalLess5+" "+timeItvalLess10+" "+timeItvalLess30+" "+timeItvalLess60+" "+timeItvalLess1440+" "+timeItvalLessmore+" "+triRealtion+" "+profileURL+" "+ifProfile+" "
//    			  +frinedsNum+" "+followsNum+" "+friendsNumDivfollowsNum+" "+userNameLen+" "+profileLen;
//    	 return str;
//     }
     public  String toString(){
    	 String str=uid+" "+attentionRate+" "+commentRate+" "+repostRate+" "+atRate+" "+topicRate+" "+textURLrate+" "+weiboFromRepostRate+" "
    			  +weiboTextSimilarity+" "+ timeItvalLess0+" "+ timeItvalLess2+" "+ timeItvalLess5+" "+timeItvalLess10+" "+ timeItvalLess20+" "+timeItvalLess30+" "+timeItvalLess60+" "+ timeItvalLess120+" "
    			 + timeItvalLess300+" "+timeItvalLess1440+" "+timeItvalLessmore+" "+meanInterval+" "+triRealtion+" "+profileURL+" "+ifProfile+" "
    			  +frinedsNum+" "+followsNum+" "+friendsNumDivfollowsNum+" "+userNameLen+" "+profileLen+" "+weiboSource+" "+weiboAge;
    	 return str;
     }
}
