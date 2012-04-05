package net.sf.jasperreports.expressions.functions;

import static net.sf.jasperreports.expressions.functions.CategoryKeys.TEXT;
import net.sf.jasperreports.expressions.annotations.JRExprFunction;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionCategories;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameter;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameters;
import net.sf.jasperreports.expressions.functions.errors.JRExpressionFunctionException;

/**
 * This class should maintain all function methods that belongs to the category {@link CategoryKeys#TEXT}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class TextFunctions {
	
	private static final int BASE_MIN_RADIX=2;
	private static final int BASE_MAX_RADIX=36;

	// ===================== BASE function ===================== //
	
	@JRExprFunction(name="BASE",description="Returns a text representation of a number, in a specified base radix.")
	@JRExprFunctionCategories({TEXT})
	@JRExprFunctionParameters({
		@JRExprFunctionParameter(name="Number",description="The positive integer number to convert"),
		@JRExprFunctionParameter(name="Radix",description="The base radix, an integer between 2 and 36"),
		@JRExprFunctionParameter(name="Minimum length",description="Specifies the minimum number of characters returned; zeroes are added on the left if necessary.")})
	public static String BASE(Integer number, Integer radix){
		// java.lang.Character.MIN_RADIX and java.lang.Character.MAX_RADIX are already 2 and 36 respectively
		// However we should check the parameter specified because the method we rely on uses 10 radix
		// as fallback when a smaller/greater radix is specified.
		if(radix>BASE_MAX_RADIX || radix<BASE_MIN_RADIX){
			throw new JRExpressionFunctionException("The radix parameter must be an integer number between 2 and 36.");
		}
		return Integer.toString(number, radix);
	}

	public static String BASE(Integer number, Integer radix, Integer minlength){
		String spacePaddedStr = String.format("%"+minlength+"s",BASE(number, radix));
		return spacePaddedStr.replace(' ', '0');
	}

	// ===================== CHAR function ===================== //

	@JRExprFunction(name="CHAR",description="Returns a single text character, given a character code.")
	@JRExprFunctionCategories({TEXT})
	@JRExprFunctionParameters({
			@JRExprFunctionParameter(name="Char code",description="The character code, in the range 1-255.")})
	public static String CHAR(Integer number){
		if(number <1 || number>255){
			throw new JRExpressionFunctionException("The number must be an integer number between 1 and 255.");
		}
		return Character.toString((char)number.intValue());
	}
	
	// TODO COMPLETE IMPLEMENTATION
}
