/**
 * 
 */
package cn.edu.whu.pojo;

/**
 * @author bczhang
 *特征向量，这里存储用户的所有的特征向量。
 */
public class FeatureVector implements Comparable<Object> {
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
	 	public String getWeiboAge() {
			return weiboAge;
		}
		public void setWeiboAge(String weiboAge) {
			this.weiboAge = weiboAge;
		}
		 public String getWbSourceRichnessK() {
				return wbSourceRichnessK;
			}
			public void setWbSourceRichnessK(String wbSourceRichnessK) {
				this.wbSourceRichnessK = wbSourceRichnessK;
			}
			public String getWbSourceRichnessD() {
				return wbSourceRichnessD;
			}
			public void setWbSourceRichnessD(String wbSourceRichnessD) {
				this.wbSourceRichnessD = wbSourceRichnessD;
			}
			public String getWbSourceRichnessH() {
				return wbSourceRichnessH;
			}
			public void setWbSourceRichnessH(String wbSourceRichnessH) {
				this.wbSourceRichnessH = wbSourceRichnessH;
			}
			public String getWbSourceRichnessS() {
				return wbSourceRichnessS;
			}
			public void setWbSourceRichnessS(String wbSourceRichnessS) {
				this.wbSourceRichnessS = wbSourceRichnessS;
			}
			  public String getGraphCentrality() {
					return graphCentrality;
				}
				public void setGraphCentrality(String graphCentrality) {
					this.graphCentrality = graphCentrality;
				}
				public String getGraphAverageDegree() {
					return graphAverageDegree;
				}
				public void setGraphAverageDegree(String graphAverageDegree) {
					this.graphAverageDegree = graphAverageDegree;
				}
				public String getGraphDensity() {
					return graphDensity;
				}
				public void setGraphDensity(String graphDensity) {
					this.graphDensity = graphDensity;
				}
				public String getGraphPathLength() {
					return graphPathLength;
				}
				public void setGraphPathLength(String graphPathLength) {
					this.graphPathLength = graphPathLength;
				}
				public String getGraphModularity() {
					return graphModularity;
				}
				public void setGraphModularity(String graphModularity) {
					this.graphModularity = graphModularity;
				}
				public String getGrapheightedDegree() {
					return grapheightedDegree;
				}
				public void setGrapheightedDegree(String grapheightedDegree) {
					this.grapheightedDegree = grapheightedDegree;
				}
	 private String graphCentrality="";
     private String graphAverageDegree="";
     private String graphDensity="";
     private String graphPathLength="";
     private String graphModularity="";
     private String grapheightedDegree="";
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
	 private String weiboAge;
	 private String wbSourceRichnessK="";
     private String wbSourceRichnessD="";
     private String wbSourceRichnessH="";
     private String wbSourceRichnessS="";
   
	//     public  String toString(){
//    	 String str=uid+" "+attentionRate+" "+commentRate+" "+repostRate+" "+atRate+" "+topicRate+" "+textURLrate+" "+weiboFromRepostRate+" "
//    			  +weiboTextSimilarity+" "+ timeItvalLess5+" "+timeItvalLess10+" "+timeItvalLess30+" "+timeItvalLess60+" "+timeItvalLess1440+" "+timeItvalLessmore+" "+triRealtion+" "+profileURL+" "+ifProfile+" "
//    			  +frinedsNum+" "+followsNum+" "+friendsNumDivfollowsNum+" "+userNameLen+" "+profileLen;
//    	 return str;
//     }
     @Override
     public int compareTo(Object o){
    	 if(this==o)
    	 {
    		 return 0;
    	 }else if (o!=null&& o instanceof FeatureVector){
    		 FeatureVector fv=(FeatureVector)o;
    		 if(Double.valueOf(fv.getTextURLrate().equals(0)?"0.0":fv.getTextURLrate())<Double.valueOf(this.getTextURLrate().equals(0)?"0.0":fv.getTextURLrate()))
    		 {
    			 return -1;
    		 }
    		 else {
    			 return 1;
    		 }
    	 }else 
    		 return -1;
     }
     public  String toString(){
    	 String str=uid+" "+attentionRate+" "+commentRate+" "+repostRate+" "+atRate+" "+topicRate+" "+textURLrate+" "+weiboFromRepostRate+" "
    			 // +weiboTextSimilarity
    			  +" "+ timeItvalLess0+" "+ timeItvalLess2+" "+ timeItvalLess5+" "+timeItvalLess10+" "+ timeItvalLess20+" "+timeItvalLess30+" "+timeItvalLess60+" "+ timeItvalLess120+" "
    			 + timeItvalLess300+" "+timeItvalLess1440+" "+timeItvalLessmore+" "+meanInterval+" "+triRealtion+" "+profileURL+" "+ifProfile+" "
    			  +frinedsNum+" "+followsNum+" "+friendsNumDivfollowsNum+" "+userNameLen+" "+profileLen+" "+weiboAge+" "+wbSourceRichnessK+" "+wbSourceRichnessD+" "+wbSourceRichnessH+" "+wbSourceRichnessS+
    			  " "+graphCentrality+" "+graphAverageDegree+" "+graphDensity+" "+graphPathLength+" "+graphModularity+" "+grapheightedDegree;
    	 return str;
     }
     
}
