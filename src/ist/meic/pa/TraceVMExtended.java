package ist.meic.pa;

import javassist.tools.reflect.Loader;

public class TraceVMExtended extends TraceVM {
	public static void main(String args[]) {
		Loader loader = createLoader();
		loader.delegateLoadingOf(Trace.class.getName());
		loader.delegateLoadingOf(History.class.getName());
		loader.delegateLoadingOf(TracingEditor.class.getName());
		runWith(new TracingEditorExtended(), loader, args);
	}
}
