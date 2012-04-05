package net.sf.jasperreports.expressions.functions.errors;

/**
 * Custom exception for expression functions tasks.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JRExpressionFunctionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JRExpressionFunctionException() {
		super();
	}

	public JRExpressionFunctionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public JRExpressionFunctionException(String arg0) {
		super(arg0);
	}

	public JRExpressionFunctionException(Throwable arg0) {
		super(arg0);
	}

	
}
