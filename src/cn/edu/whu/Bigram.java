/**
 * 
 */
package cn.edu.whu;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author bczhang
 *
 */
public class Bigram {
	
	
	public static void main(String[] args){
		Map<Integer,String > gramMap=new HashMap<Integer,String>();
		String[] st=formGrams("那些年我们发过的微博网络营销少帅严刚",2);
		for(int i=0;i<st.length;i++){
	    System.out.println("the length is "+st.length+"    "+st[i]);
	    }
	}
    public  void makeNgrams(String  text,int nGramSize) {
    	 
}
    /**
     * Bigram 切割词
     * @param text
     * @param ng
     * @return
     */
     private static String[] formGrams(String text,int ng){
    	 int len=text.length();
    	 String[] res=new String[len-ng+1];
    	 for(int i=0;i<len-ng+1;i++){
    		  	res[i]=text.substring(i,i+ng);
    		  	
    	 }
    	 return res;
    	 
     }    
}
