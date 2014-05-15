package ist.meic.pa;

import java.util.List;

public class Trace {

	static public void print(Object object) {
		List<String> objHist = History.objectHistory(object);
		if(objHist.isEmpty()) {
			TraceVM.println("Tracing for "+ object +" is nonexistent!");
		} else {
			TraceVM.println("Tracing for " + object);
			for (String histEntry : objHist) {
				TraceVM.println(histEntry);
			}
		}
	}

}
