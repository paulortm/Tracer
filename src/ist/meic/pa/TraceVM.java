package ist.meic.pa;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.tools.reflect.Loader;

public class TraceVM {

	public static PrintStream out = System.err;

	public static void main(String args[]) throws Throwable {
		Loader loader = createLoader();
		loader.delegateLoadingOf(Trace.class.getName());
		loader.delegateLoadingOf(History.class.getName());
		loader.delegateLoadingOf(TracingEditor.class.getName());
		runWith(new TracingEditor(), loader, args);
	}
	
	public static Loader createLoader() {
		Loader loader = null;
		try {
			loader = new Loader();
		} catch (CannotCompileException e) {
			new RuntimeException(e);
		} catch (NotFoundException e1) {
			new RuntimeException(e1);
		}
		return loader;
	}
	
	public static void runWith(ExprEditor editor, Loader loader, String args[]) {
		String mainClass = args[0];
		List<String> mainArgs = new LinkedList<String>();
		for (int i = 1; i < args.length; i++) {
			mainArgs.add(args[i]);
		}
		ClassPool cp = ClassPool.getDefault();
		
		try {
			loader.addTranslator(cp, new TraceTranslator(new TracingEditor()));
			loader.run(mainClass, removeFirstElm(args));
		} catch (NotFoundException e) {
			throw new RuntimeException(e);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static String[] removeFirstElm(String[] array) {
		List<String> newArrayLst = new LinkedList<String>();
		for (int i = 1; i < array.length; i++) {
			newArrayLst.add(array[i]);
		}
		return newArrayLst.toArray(new String[array.length - 1]);
	}

	public static void println(String msg) {
		out.println(msg);
	}
}
