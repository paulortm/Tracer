package ist.meic.pa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class History {	
	

	private static  Map<Object,List<String>> mapObjHistory = new HashMap<Object,List<String>>();
		
	// devolve o histórico do objecto
	public static List<String> objectHistory(Object obj){
		
		if(obj!= null)
		  if (mapObjHistory.containsKey(obj)){
			  List<String> historyObj = mapObjHistory.get(obj); 
			   return historyObj;
		   }else{ 
			   //lança exception null object exception
			   System.err.println("Chave não existe"); 
		   }	 
		return null;	
	}

	// adiciona uma linha ao historico do objecto
	public static void addObjHistory(Object obj, String entry){
		
		if(obj !=null && entry != ""){		
		  if (mapObjHistory.containsKey(obj))  
			  mapObjHistory.get(obj).add(entry);		  
		  else{
			  List<String> listHistory = new ArrayList<String>();
			  listHistory.add(entry);
			  mapObjHistory.put(obj,listHistory);
		  }		
	   }else{
		  //lança exception null object 
		  System.err.println("Chave não existe"); 
	   }			
	}
}
