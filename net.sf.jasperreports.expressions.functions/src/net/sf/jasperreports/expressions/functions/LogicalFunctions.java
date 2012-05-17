package net.sf.jasperreports.expressions.functions;

import static net.sf.jasperreports.expressions.functions.CategoryKeys.LOGICAL;
import net.sf.jasperreports.expressions.annotations.JRExprFunction;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionCategories;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameter;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameters;

/**
 * This class should maintain all functions that allows logic decisions, and belong to the category {@link CategoryKeys#LOGICAL}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class LogicalFunctions {
	
	// ===================== AND function ===================== //
	@JRExprFunction(name="AND",description="Returns true if all arguments are considered true, false otherwise. " +
			"Argument must be a logical result or a direct boolean value.")
	@JRExprFunctionCategories({LOGICAL})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Argument",description="A boolean expression or value.")})
	public static Boolean AND(Boolean ... arguments){
		if(arguments.length==0) return null;
		boolean result=true;
		for(Boolean arg : arguments){
			result=result && arg;
			// Stops at first false argument
			if (!result) return false;
		}
		return result;
	}

	// ===================== FALSE function ===================== //
	@JRExprFunction(name="FALSE",description="Returns the logical value FALSE.")
	@JRExprFunctionCategories({LOGICAL})
	public static Boolean FALSE(){
		return Boolean.FALSE;
	}
	
	// ===================== TRUE function ===================== //
	@JRExprFunction(name="TRUE",description="Returns the logical value TRUE.")
	@JRExprFunctionCategories({LOGICAL})
	public static Boolean TRUE(){
		return Boolean.TRUE;
	}
	
	// ===================== NOT function ===================== //
	@JRExprFunction(name="NOT",description="Returns the negation of the specified boolean expression.")
	@JRExprFunctionCategories({LOGICAL})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Argument",description="A boolean expression or value.")})
	public static Boolean NOT(Boolean boolValue){
		if (boolValue==null){
			return null;
		}
		else{
			return !boolValue;			
		}
	}
	
	// ===================== OR function ===================== //
	@JRExprFunction(name="OR",description="Returns true if any of the arguments is considered true, false otherwise. " +
			"Argument must be a logical result or a direct boolean value.")
	@JRExprFunctionCategories({LOGICAL})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Argument",description="A boolean expression or value.")})
	public static Boolean OR(Boolean ... arguments){
		if(arguments.length==0) return null;
		boolean result=false;
		for(Boolean arg : arguments){
			result=result || arg;
			// Stops at first true argument
			if (result) return true;
		}
		return result;
	}
	
	// ===================== IF function ===================== //
	@JRExprFunction(name="IF",description="Returns one of two values, depending on a test condition.")
	@JRExprFunctionCategories({LOGICAL})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Test condition",description="An expression returning a boolean value."),
		@JRExprFunctionParameter(name="Value 1 (true)",description="The value returned when the test is true."),
		@JRExprFunctionParameter(name="Value 2 (false)",description="The value returned when the test is false.")})
	public static Object IF(Boolean test, Object value1, Object value2){
		if(test==null) return null;
		return test ? value1 : value2; 
	}
	
	// ===================== EQUALS function ===================== //
	@JRExprFunction(name="EQUALS",description="Checks if the two specified objects are equals.")
	@JRExprFunctionCategories({LOGICAL})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Object 1",description="The first element to be compared."),
		@JRExprFunctionParameter(name="Object 2",description="The second element to be compared.")})
	public static Boolean EQUALS(Object obj1, Object obj2){
		if(obj1!=null){
			return obj1.equals(obj2);
		}
		else if (obj2!=null){
			return obj2.equals(obj1);
		}
		return true;	// both null
	}	
}
