/**
 * 
 */
package cn.edu.whu.pojo;

/**
 * @author bczhang
 *
 */
public class GephiEdge {
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getTarget() {
		return Target;
	}
	public void setTarget(String target) {
		Target = target;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getIntervel() {
		return Intervel;
	}
	public void setIntervel(String intervel) {
		Intervel = intervel;
	}
	public String getWeight() {
		return Weight;
	}
	public void setWeight(String weight) {
		Weight = weight;
	}
	private String Source="";
	private String Target="";
	private String Id="";
	private String Label="";
	private String Intervel="";
	private String Weight="";
	private String type="Directed";
	private String timeset="";
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString(){
		return Source+","+Target+","+type+","+Id+","+Label+","+timeset+","+Weight;
				
	}
 
}
