package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.expr.Cast;
import javassist.expr.FieldAccess;
import javassist.expr.Handler;
import javassist.expr.NewArray;

public class TracingEditorExtended extends TracingEditor {
	public String fieldAccessSignature(FieldAccess expr) {
		return expr.getClassName() + "." + expr.getFieldName();
	}
	
	public void edit(FieldAccess expr) {
		try {
			expr.replace(
					"{"
				+	"$_ = $proceed($$);"
				+	"ist.meic.pa.History.logFieldAccess(($w)$0, ($w)$_, ($w)$args, \"" + fieldAccessSignature(expr) + "\", \"" + sourceInfo(expr) + "\");"
				+	"}"
			);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(Handler expr) {
		try {
			expr.insertBefore(
					"{"
				+	"ist.meic.pa.History.logCatch(($w)$1, $type,\"" + sourceInfo(expr) + "\");"
				+	"}"
			);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(Cast expr) {
		try {
			expr.replace(
					"{"
				+   "$_ = $proceed($$);"
				+	"ist.meic.pa.History.logCast(($w)$1, $type,\"" + sourceInfo(expr) + "\");"
				+	"}"
			);
		} catch (CannotCompileException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(NewArray expr) {
//		try {
//			expr.replace(
//					"{"
//				+   "$_ = $proceed($$);"
//				+	"ist.meic.pa.History.logNewArray(($w)$_, $type, $args, \"" + sourceInfo(expr) + "\");"
//				+	"}"
//			);
//		} catch (CannotCompileException e) {
//			throw new RuntimeException(e);
//		}
	}
}
