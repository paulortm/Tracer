package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;

public class TracingEditor extends ExprEditor {

	public void edit(NewExpr call) {
		try {
			call.replace("{"
					+ "$_ = $proceed($$);"
					+ "System.out.println($_ + \"." + call.getClassName() + "\");" + "}");
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(MethodCall call) {
		try {
			call.replace("{"
					+ "System.out.println($0 + \"." + call.getMethodName() + "\");"
					+ "$_ = $proceed($$);"
					+ "}"
			);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}
}
