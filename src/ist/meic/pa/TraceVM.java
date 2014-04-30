package ist.meic.pa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class TraceVM {
	public static void main(String args[]) {
		String mainClass = args[0];
		List<String> mainArgs = new LinkedList<String>();
		for(int i = 1; i < args.length; i++) {
			mainArgs.add(args[i]);
		}
        ClassPool cp = ClassPool.getDefault();
        
        callMain(cp, mainClass, mainArgs);
	}
	
	public static void callMain(ClassPool cp, String mainClass, List<String> mainArgs) {
        try {
        	System.out.println("run main of " + mainClass);
	        CtClass cc = cp.get(mainClass);
	        Class c = cc.toClass();
	        Method main = c.getMethod("main", String[].class);
	        main.invoke(null, (Object)mainArgs.toArray());
        } catch (NotFoundException e) {
			throw new RuntimeException(e);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
