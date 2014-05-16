package ist.meic.pa;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class History {

	private static Map<Object, List<String>> mapObjHistory = new IdentityHashMap<Object, List<String>>();

	// devolve o histórico do objecto.
	// se obj == null então devolve uma lista vazia
	public static List<String> objectHistory(Object obj) {

		if (obj != null && mapObjHistory.containsKey(obj)) {
			List<String> historyObj = mapObjHistory.get(obj);
			return historyObj;
		}
		return new LinkedList<String>();
	}

	// Adiciona uma linha ao historico do objecto.
	// Só adiciona se obj != null
	private static void addObjHistory(Object obj, String entry) {
		if (obj != null) {
			if (mapObjHistory.containsKey(obj))
				mapObjHistory.get(obj).add(entry);
			else {
				List<String> listHistory = new ArrayList<String>();
				listHistory.add(entry);
				mapObjHistory.put(obj, listHistory);
			}
		}
	}

	private static void logObjectReturned(Object obj, String signature) {
		addObjHistory(obj, "  <- " + signature);
	}

	private static void logPassedObject(Object obj, String signature) {
		addObjHistory(obj, "  -> " + signature);
	}

	public static void logMethodCall(String signature, Object[] args) {
		for (int i = 0; i < args.length; i++) {
			logPassedObject(args[i], signature);
		}
	}

	public static void logReturn(String signature, Object returned) {
		logObjectReturned(returned, signature);
	}
	
	public static void logFieldAccess(Object target, Object read, Object[] args, String signature, String sourceInfo) {
		// ignore static fields
		if(target != null) {
			if(args.length == 1) {
				// write
				logPassedObject(args[0], signature + " = " + args[0].getClass().getName() + sourceInfo);
			}
			if(read != null) {
				// read
				logObjectReturned(read, signature + sourceInfo);
			}
		}
	}
	
	public static void logCatch(Object exception, Class<?> type, String sourceInfo) {
		addObjHistory(exception, "     catch(" + type.getName() + ")" + sourceInfo );
	}
	
	public static void logCast(Object original, Class<?> castType, String sourceInfo) {
		addObjHistory(original,  "     (" + castType.getName() + ")" + original.getClass().getName() + sourceInfo);
	}
	
	public static void logNewArray(Object array, Class<?> arrayType, Object[] dimentions, String srouceInfo ) {
		String dimString = " ";
		for(int i = 0; i < dimentions.length; i++) {
			dimString += "[" + dimentions[i] + "]";
		}
		
		logObjectReturned(array, arrayType.getName() + dimString + srouceInfo);
	}
}
