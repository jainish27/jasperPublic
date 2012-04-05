package net.sf.jasperreports.expressions.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to describe a list of parameters for a specific {@link JRExprFunction}.
 *  
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JRExprFunctionParameters {
	JRExprFunctionParameter[] value();
}
