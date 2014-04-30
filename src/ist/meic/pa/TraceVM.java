package ist.meic.pa;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.tools.reflect.Loader;

public class TraceVM {
	
	public static PrintStream out = System.err;
	
	public static void main(String args[]) throws Throwable {
		String mainClass = args[0];
		List<String> mainArgs = new LinkedList<String>();
		for(int i = 1; i < args.length; i++) {
			mainArgs.add(args[i]);
		}
        
		// add translators
		ClassPool cp = ClassPool.getDefault();
		Loader loader = null;
		try {
			loader = new Loader();
		} catch (CannotCompileException e) {
			new RuntimeException(e);
		} catch (NotFoundException e1) {
			new RuntimeException(e1);
		}
		
		loader.run(mainClass, removeFirstElm(args));
	}
	
	public static String[] removeFirstElm(String[] array) {
		List<String> newArrayLst = new LinkedList<String>();
		for(int i = 1; i < array.length; i++) {
			newArrayLst.add(array[i]);
		}
		return newArrayLst.toArray(new String[array.length-1]);
	}
}
