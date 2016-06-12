/**
 * 
 */
package cn.edu.whu.utils;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author bczhang
 *提取丰富度
 *公式：K=10^4 *
 */
public class GetRichnessMetrices {
	/*
	 * 有多少源
	 */
	
	 
	 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr={"新浪微博","新浪微博","新浪微博","微博","微博","微博","微博","浪微博","浪微博","新浪博","新博","新博","新浪博","新浪微","浪微博","新浪"};
		//String[] arr={"weibo","weibo"};
		GetRichnessMetrices gm=new GetRichnessMetrices();
		gm.getRM_YuleAndSimpson(arr);
	}
	/**
	 * 主方法
	 */
	public  String  getRM_YuleAndSimpson( String[] sourceArr){
		 Map<String,Integer> kindsMap=new HashMap<String,Integer>();
		 //得到不同类的次数
		 int V_T=0;
		 //出现次数最多的词
		 int v=0;
		 int temp;
		 //得到总的次数
		 int T=sourceArr.length;
		 DecimalFormat   df=new   java.text.DecimalFormat("#.######"); 
		 //统计每次出现的次数，词作为主键，次数作为值，放到Map里面
		   for(String s:sourceArr)
		   {   if(kindsMap.containsKey(s))
			   kindsMap.put(s, kindsMap.get(s)+1);
		       else kindsMap.put(s, 1);
		   }
		   V_T=kindsMap.size();
		   //对不同的次数进行再次统计，次数作为主键
		 Map<Integer,Integer> map=new HashMap<>();
		 ArrayList<Integer> list=new  ArrayList<Integer>();
		   for(String ss:kindsMap.keySet()){
			   temp=kindsMap.get(ss);
			   //把所有的类别数都放到一个数组里面，好实现统计不同数出现的次数
			   list.add(temp);
			   if(temp>v)
				   v=temp;
		   }
		   //Collections.sort(list);
		   
		   for(int i=0;i<list.size();i++){
				   map.put(list.get(i), map.containsKey(list.get(i))? map.get(list.get(i))+1:1);
		   }
		   double K=0.0,D=0.0,S=0.0;
		   int H=0;
		   int Yulefator1=0;
		   int Simpsonfator1=0;
		   for(int m=1;m<=v;m++){
			   if(map.containsKey(m)){
				   Yulefator1+=((m*m)* map.get(m))-T;
				   Simpsonfator1+=(m*(m-1))*map.get(m);
			   }
		   }
		   //System.out.println("m="+v+" T="+T);
		   int YuleMolecule=(int)Math.pow(10, 4)*Yulefator1;
		   int SimpsonMolecule=Simpsonfator1;
		   int HapaxMolecule=0;
           if(map.containsKey(2)){
		    HapaxMolecule=map.get(2);
           }
		   K=(double)YuleMolecule/Math.pow(T,2);
		   if(T>1)
		       D=(double)SimpsonMolecule/(double)(T*(T-1));
		   else
			   D=(double)SimpsonMolecule;
		   if(map.containsKey(1)){
		   H=map.get(1);
		   }
		   S=(double)HapaxMolecule/(double)V_T;
		   String result=df.format(K)+" "+df.format(D)+" "+df.format(H)+" "+df.format(S);
		  // System.out.println(result);
		   return result;
	}
	 
}
