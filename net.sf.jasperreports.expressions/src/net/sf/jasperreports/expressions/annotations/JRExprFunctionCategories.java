package net.sf.jasperreports.expressions.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be describe the types of categories to which a specific
 * {@link JRExprFunction} belong to. 
 *  
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JRExprFunctionCategories {
	String[] value();
}