package net.sf.jasperreports.expressions.functions;

import java.text.DateFormat;
import java.util.Date;

import net.sf.jasperreports.expressions.annotations.JRExprFunction;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionCategories;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameter;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameters;

/**
 * This class should maintain all function methods that belongs to the category {@link CategoryKeys#DATE_TIME}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class DateTimeFunctions {

	@JRExprFunction(name="DATE",description="Returns the current date or the specified one, eventually formatted.")
	@JRExprFunctionCategories({ CategoryKeys.DATE_TIME})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Date pattern",description="The pattern to format the output string"),
		@JRExprFunctionParameter(name="Date",description="The date to format")})
	public static String DATE(){
		return null;
	}

	public static String DATE(DateFormat dateFormat){
		return null;
	}
	
	public static String DATE(DateFormat dateformat, Date date){
		return null;
	}
	
	// TODO - COMPLETE IMPLEMENTATION
	
}
