/**
 * 
 */
package cn.edu.whu.pojo;

/**
 * @author bczhang
 *
 */
public class GephiNode {
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
	public String getModularityClass() {
		return ModularityClass;
	}
	public void setModularityClass(String modularityClass) {
		ModularityClass = modularityClass;
	}
	private String Id ="";
	 private String Label="";
	 private String Intervel="";
	 private String ModularityClass="";
	 public String toString (){
		 //return Id+","+Label+","+Intervel+","+ModularityClass;
		 return Id+","+Label+","+Intervel;
	 }
	 
}
