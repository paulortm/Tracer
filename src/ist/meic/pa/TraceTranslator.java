package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * Translator that adds tracing code to the loaded classes code.
 * 
 */
public class TraceTranslator implements javassist.Translator {

	private TracingEditor tracingEditor;

	public TraceTranslator(TracingEditor tracingEditor) {
		this.tracingEditor = tracingEditor;
	}

	@Override
	public void onLoad(ClassPool cp, String className)
			throws NotFoundException, CannotCompileException {
		CtClass clazz = cp.get(className);
		clazz.instrument(this.tracingEditor);
	}

	@Override
	public void start(ClassPool arg0) throws NotFoundException,
			CannotCompileException {
		// TODO Auto-generated method stub

	}

}
