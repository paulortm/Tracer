package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.expr.FieldAccess;

public class TracingEditorExtended extends TracingEditor {
	public String fieldAccessSignature(FieldAccess expr) {
		return expr.getClassName() + "." + expr.getFieldName();
	}
	
	public void edit(FieldAccess expr) {
		try {
			expr.replace(
					"{"
				+	"$_ = $proceed($$);"
				+	"ist.meic.pa.History.logFieldAccess($0, $_, $args, \"" + fieldAccessSignature(expr) + "\", \"" + sourceInfo(expr) + "\");"
				+	"}"
			);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}
}
