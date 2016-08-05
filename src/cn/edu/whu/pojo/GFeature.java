/**
 * 
 */
package cn.edu.whu.pojo;

/**
 * @author bczhang
 *最终确定的图特征
 */
public class GFeature {
    @Override
	public String toString() {
		return uid +" "+ degree_centrality + " " + rich_club_coefficient
				+ " " + degree_assortativity_coefficient + " "
				+ eigenvectorCentrality + " " + AverageDegree + " " + Density + " "
				+ PathLength + " " + Modularity + " " + wheightedDegree
				+ " " + AverageClusteringCoefficient+" "+betweenness_centrality+" "+closeness_centrality;
	}
 
 
	public String getDegree_centrality() {
		return degree_centrality;
	}
	public void setDegree_centrality(String degree_centrality) {
		this.degree_centrality = degree_centrality;
	}
	public String getRich_club_coefficient() {
		return rich_club_coefficient;
	}
	public void setRich_club_coefficient(String rich_club_coefficient) {
		this.rich_club_coefficient = rich_club_coefficient;
	}
	public String getDegree_assortativity_coefficient() {
		return degree_assortativity_coefficient;
	}
	public void setDegree_assortativity_coefficient(String degree_assortativity_coefficient) {
		this.degree_assortativity_coefficient = degree_assortativity_coefficient;
	}
	public String getEigenvectorCentrality() {
		return eigenvectorCentrality;
	}
	public void setEigenvectorCentrality(String eigenvectorCentrality) {
		this.eigenvectorCentrality = eigenvectorCentrality;
	}
	public String getAverageDegree() {
		return AverageDegree;
	}
	public void setAverageDegree(String averageDegree) {
		AverageDegree = averageDegree;
	}
	public String getDensity() {
		return Density;
	}
	public void setDensity(String density) {
		Density = density;
	}
	public String getPathLength() {
		return PathLength;
	}
	public void setPathLength(String pathLength) {
		PathLength = pathLength;
	}
	public String getModularity() {
		return Modularity;
	}
	public void setModularity(String modularity) {
		Modularity = modularity;
	}
	public String getWheightedDegree() {
		return wheightedDegree;
	}
	public void setWheightedDegree(String wheightedDegree) {
		this.wheightedDegree = wheightedDegree;
	}
	public String getAverageClusteringCoefficient() {
		return AverageClusteringCoefficient;
	}
	public void setAverageClusteringCoefficient(String averageClusteringCoefficient) {
		AverageClusteringCoefficient = averageClusteringCoefficient;
	}
	private String uid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	private String degree_centrality;
    private String rich_club_coefficient;
    private String degree_assortativity_coefficient;
    private String eigenvectorCentrality;
    private String AverageDegree;
    private String Density;
    private String PathLength;
    private String Modularity;
    private String wheightedDegree;
    private String AverageClusteringCoefficient;
    public String getCloseness_centrality() {
		return closeness_centrality;
	}


	public void setCloseness_centrality(String closeness_centrality) {
		this.closeness_centrality = closeness_centrality;
	}


	public String getBetweenness_centrality() {
		return betweenness_centrality;
	}


	public void setBetweenness_centrality(String betweenness_centrality) {
		this.betweenness_centrality = betweenness_centrality;
	}
	private String closeness_centrality;
    private String betweenness_centrality;
}
