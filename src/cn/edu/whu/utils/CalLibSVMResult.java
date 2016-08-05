/**
 * 
 */
package cn.edu.whu.utils;

/**
 * @author bczhang
 *
 */
public class CalLibSVMResult {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Utils utils=new Utils();
      String result1=utils.readFileToString("E:\\libSVM\\libSVMSample\\libSVM_result\\graph_features_plus4NewRem1516.txt");
      String[] arr=result1.split("\n");
      StringBuilder strb=new StringBuilder();
      int count=1;
      double result=0;
      strb.append("十折交叉验证准确率：");
      strb.append("\n");
      for(String ss:arr){
    	  
    	  if(ss.contains("Accuracy")){
    		  String[] arr2=ss.split(" ");
    		  strb.append(arr2[2]+" "+arr2[3]+"   ");
    		  if(count%5==0)
    		  strb.append("\n");
    		  count++;
    		   
    		  result+=Double.parseDouble(arr2[2].split("%")[0]);
    		    //System.out.println(result);
    	  }
      } 
      strb.append("\n");
      strb.append("\n");
      strb.append("平均准确率："+result/10);
      System.out.println(strb);
    
	}

}
