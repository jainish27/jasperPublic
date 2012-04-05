package net.sf.jasperreports.expressions.functions;

import static net.sf.jasperreports.expressions.functions.CategoryKeys.MATH;
import net.sf.jasperreports.expressions.annotations.JRExprFunction;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionCategories;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameter;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameters;

/**
 * This class should maintain all function methods that belongs to the category {@link CategoryKeys#MATH}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class MathFunctions {

	@JRExprFunction(name="SUM",description="Returns the sum of a list of numbers")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Number",description="Addendum")})
	@JRExprFunctionCategories({MATH})
	public static Number SUM(Number ...numbers){
		double result=0;
		for (int i=0;i<numbers.length;i++){
			result+=numbers[i].doubleValue();
		}
		return result;
	}
	
	// TODO - COMPLETE IMPLEMENTATION

}
