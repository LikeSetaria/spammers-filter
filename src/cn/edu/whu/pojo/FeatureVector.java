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
     public String toString(){
    	 String str=uid+" "+attentionRate+" "+commentRate+" "+repostRate+" "+atRate+" "+topicRate+" "+textURLrate+" "+weiboFromRepostRate+" "
    			 +weiboTextSimilarity+" "+intervalRate+" "+triRealtion+" "+profileURL+" "+ifProfile;
    	 return str;
     }
}
