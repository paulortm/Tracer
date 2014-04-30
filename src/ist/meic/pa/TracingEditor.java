package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;

public class TracingEditor extends ExprEditor {

	public void edit(ConstructorCall c) {
		try {
			c.replace("{" + "System.out.println($0 + \"." + c.getMethodName()
					+ "\");" + "$_ = $proceed($$);" + "}");
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}
}
