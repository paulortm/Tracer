package ist.meic.pa;

import java.util.List;

public class Trace {
	
	static public void print(Object object) {
		List<String> objHist = History.objectHistory(object);
		for(String histEntry: objHist) {
			TraceVM.println(histEntry);
		}
	}

}
