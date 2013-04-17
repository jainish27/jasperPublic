package net.sf.jasperreports.expressions.functions;

import static net.sf.jasperreports.expressions.functions.CategoryKeys.MATH;
import net.sf.jasperreports.expressions.annotations.JRExprFunction;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionCategories;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameter;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameters;
import net.sf.jasperreports.expressions.functions.errors.JRExpressionFunctionException;

/**
 * This class should maintain all function methods that belongs to the category {@link CategoryKeys#MATH}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class MathFunctions {
	
	// ===================== ABS function ===================== //
	@JRExprFunction(name="ABS",description="Returns the absolute value of a number.")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Number",description="The number to check.")})
	@JRExprFunctionCategories({MATH})
	public static Number ABS(Number number){
		if(number==null) {
			return null;
		}
		else{
			if(number instanceof Integer){
				return Math.abs((Integer)number);
			}
			else if(number instanceof Double){
				return Math.abs((Double)number);
			}
			else if(number instanceof Float){
				return Math.abs((Float)number);
			}
			else if(number instanceof Long){
				return Math.abs((Long)number);
			}
			else{
				// fall-back
				return Math.abs(number.doubleValue());
			}
		}
	}
	
	// ===================== FACT function ===================== //
	@JRExprFunction(name="FACT",description="Returns the factorial of a number")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Integer number",description="The argument.")})
	@JRExprFunctionCategories({MATH})
	public static Long FACT(Integer number){
		if(number==null) {
			return null;
		}
		if(number<0){
			throw new JRExpressionFunctionException("Unable to calculate the factorial number of a negative number.");
		}
		else{
	       Long result = 1l;
	       for (int i = 1; i <= number; ++i) {
	    	   result *= i;
	       }
	       return result;
		}
	}
	
	// ===================== ISEVEN function ===================== //
	@JRExprFunction(name="ISEVEN",description="Checks if a number is even. " +
			"If a non-integer number is specified, any digits after the decimal point are ignored.")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Number",description="The number to check.")})
	@JRExprFunctionCategories({MATH})
	public static Boolean ISEVEN(Number number){
		if(number==null) {
			return null;
		}
		else{
			return number.intValue()%2==0;			
		}
	}

	// ===================== ISODD function ===================== //
	@JRExprFunction(name="ISODD",description="Checks if a number is odd. " +
			"If a non-integer number is specified, any digits after the decimal point are ignored.")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Number",description="The number to check.")})
	@JRExprFunctionCategories({MATH})
	public static Boolean ISODD(Number number){
		if(number==null) {
			return null;
		}
		else{
			return number.intValue()%2==1;			
		}
	}
	
	// ===================== PRODUCT function ===================== //
	@JRExprFunction(name="PRODUCT",description="Returns the product of a list of numbers")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Number",description="Argument")})
	@JRExprFunctionCategories({MATH})
	public static Number PRODUCT(Number ...numbers){
		if(numbers.length==0) return null;		
		double result=1;
		for (int i=0;i<numbers.length;i++){
			result*=numbers[i].doubleValue();
		}
		return result;
	}

	// ===================== RAND function ===================== //
	@JRExprFunction(name="RAND",description="Returns a random number between 0.0 and 1.0.")
	@JRExprFunctionCategories({MATH})
	public static Double RAND(){
		return Math.random();
	}
	
	// ===================== RAND function ===================== //
	@JRExprFunction(name="RANDBETWEEN",description="Returns an Integer random number between bottom and top range (both inclusive).")
	@JRExprFunctionCategories({MATH})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Bottom range",description="Integer number for the bottom range"),
		@JRExprFunctionParameter(name="Top range",description="Integer number for the top range")})
	public static Integer RANDBETWEEN(Integer bottomRange, Integer topRange){
		int min=bottomRange.intValue();
		int max=topRange.intValue();
		return min + (int)(Math.random() * ((max - min) + 1));
	}	
	
	// ===================== SIGN function ===================== //
	@JRExprFunction(name="SIGN",description="Returns the sign of a number.")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Number",description="The number to check.")})
	@JRExprFunctionCategories({MATH})
	public static Integer SIGN(Number number){
		if(number==null) {
			return null;
		}
		else{
			return (int) Math.signum(number.doubleValue());			
		}
	}
	
	// ===================== SQRT function ===================== //
	@JRExprFunction(name="SQRT",description="Returns the positive square root of a number. The number must be positive")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Positive number",description="Argument.")})
	@JRExprFunctionCategories({MATH})
	public static Number SQRT(Number number){
		if(number==null) {
			return null;
		}
		else{
			return Math.sqrt(number.doubleValue());			
		}
	}
	
	// ===================== SUM function ===================== //
	@JRExprFunction(name="SUM",description="Returns the sum of a list of numbers")
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Number",description="Addendum")})
	@JRExprFunctionCategories({MATH})
	public static Number SUM(Number ...numbers){
		if(numbers.length==0) return null;		
		double result=0;
		for (int i=0;i<numbers.length;i++){
			result+=numbers[i].doubleValue();
		}
		return result;
	}
	
	// ===================== MIN function ===================== //
	@JRExprFunction(name="MIN",description="Returns the minimum of a list of numeric values.")
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Number",description="Number to compare")})
	@JRExprFunctionCategories({MATH})
	public static Number MIN(Number ...numbers){
		if(numbers.length==0) throw new IllegalArgumentException("No numbers have been specified");
		if(!isNumberListValid(numbers)) throw new IllegalArgumentException("No null element are allowed");
		double min= numbers[0].doubleValue();
		for (int i=1;i<numbers.length;i++){
			if(numbers[i].doubleValue()<min){
				min = numbers[i].doubleValue();
			}
		}
		return fixNumberReturnType(min, numbers);
	}	
	
	// ===================== MAX function ===================== //
	@JRExprFunction(name="MAX",description="Returns the maximum of a list of numeric values.")
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Number",description="Number to compare")})
	@JRExprFunctionCategories({MATH})
	public static Number MAX(Number ...numbers){
		if(numbers.length==0) throw new IllegalArgumentException("No numbers have been specified");
		if(!isNumberListValid(numbers)) throw new IllegalArgumentException("No null element are allowed");
		double max= numbers[0].doubleValue();
		for (int i=1;i<numbers.length;i++){
			if(numbers[i].doubleValue()>max){
				max = numbers[i].doubleValue();
			}
		}
		return fixNumberReturnType(max, numbers);
	}
	
	// ===================== FLOOR function ===================== //
	@JRExprFunction(name="FLOOR",description="" +
			"Returns the largest (closest to positive infinity) double value that is less " +
			"than or equal to the argument and is equal to a mathematical integer")
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Number",description="Value")})
	@JRExprFunctionCategories({MATH})
	public static Double FLOOR(Number number){
		if(number == null) throw new IllegalArgumentException("The value number can not be null");
		return Math.floor(number.doubleValue());
	}

	// ===================== CEIL function ===================== //
	@JRExprFunction(name="CEIL",description="" +
			"Returns the smallest (closest to negative infinity) double value that is greater " +
			"than or equal to the argument and is equal to a mathematical integer")
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Number",description="Value")})
	@JRExprFunctionCategories({MATH})
	public static Double CEIL(Number number){
		if(number == null) throw new IllegalArgumentException("The value number can not be null");
		return Math.ceil(number.doubleValue());
	}
	
	/*
	 * Checks if the array of numbers is valid. 
	 * No null element must be contained.
	 */
	private static boolean isNumberListValid(Number ...numbers){
		for(int i=0;i<numbers.length;i++){
			if(numbers[i]==null) return false;
		}
		return true;
	}
	
	/*
	 * Fixes the return type for the numeric result value.
	 */
	private static Number fixNumberReturnType(Number returnValue, Number ...numbers){
		if(haveSameType(Integer.class, numbers)) return returnValue.intValue();
		if(haveSameType(Long.class, numbers)) return returnValue.intValue();
		if(haveSameType(Float.class, numbers)) return returnValue.intValue();
		return returnValue.doubleValue();
	}
	
	/*
	 * Checks if the list of generic numbers have all the same type.
	 */
	private static boolean haveSameType(
			Class<? extends Number> clazz, Number ...numbers){
		for(int i=0; i<numbers.length; i++){
			if(numbers[i].getClass() != clazz){
				return false;
			}
		}
		return true;
	}
	
	
}
