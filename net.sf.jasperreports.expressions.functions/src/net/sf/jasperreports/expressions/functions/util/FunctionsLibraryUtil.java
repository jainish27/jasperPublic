package net.sf.jasperreports.expressions.functions.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.expressions.annotations.beans.JRExprFunctionBean;
import net.sf.jasperreports.expressions.annotations.util.ExprFunctionAnnotationsUtil;
import net.sf.jasperreports.expressions.functions.CategoryKeys;
import net.sf.jasperreports.expressions.functions.DataSourcesFunctions;
import net.sf.jasperreports.expressions.functions.DateTimeFunctions;
import net.sf.jasperreports.expressions.functions.FinancialFunctions;
import net.sf.jasperreports.expressions.functions.InformationFunctions;
import net.sf.jasperreports.expressions.functions.LogicalFunctions;
import net.sf.jasperreports.expressions.functions.MathFunctions;
import net.sf.jasperreports.expressions.functions.TextFunctions;

/**
 * This class contains standard utility methods to interact with the library of functions 
 * embeddable in a JasperReports expression.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see CategoryKeys
 *
 */
public class FunctionsLibraryUtil {
	
	private static List<String> builtinCategories;
	private static List<Class<?>> builtinCategoryClasses;
	private static Map<String, List<JRExprFunctionBean>> functionsByCategory;
	
	static{
		// Adds all the "standard" categories that can be found
		// in the CategoryKeys class
		builtinCategories=new ArrayList<String>();
		builtinCategories.add(CategoryKeys.DATE_TIME);
		builtinCategories.add(CategoryKeys.DATASOURCES);
		builtinCategories.add(CategoryKeys.FINANCIAL);
		builtinCategories.add(CategoryKeys.INFORMATION);
		builtinCategories.add(CategoryKeys.LOGICAL);
		builtinCategories.add(CategoryKeys.MATH);
		builtinCategories.add(CategoryKeys.TEXT);
		
		// Cache maps for builtin categories
		builtinCategoryClasses=new ArrayList<Class<?>>(7); // 1 file for category
		builtinCategoryClasses.add(DateTimeFunctions.class);
		builtinCategoryClasses.add(DataSourcesFunctions.class);
		builtinCategoryClasses.add(FinancialFunctions.class);
		builtinCategoryClasses.add(InformationFunctions.class);
		builtinCategoryClasses.add(LogicalFunctions.class);
		builtinCategoryClasses.add(MathFunctions.class);
		builtinCategoryClasses.add(TextFunctions.class);
		
		functionsByCategory=new HashMap<String, List<JRExprFunctionBean>>();
	}
	

	/**
	 * Returns the list of built-in categories.
	 * 
	 * @return list of categories (as keys)
	 */
	public static List<String> getBuiltinCategories(){
		return builtinCategories;
	}

	/**
	 * Returns a list of expression functions that are associated to the specified
	 * input category.
	 * 
	 * Result set can be empty if no functions in the particular category is found.
	 * 
	 * @param categoryKey the category
	 * @return a list of functions belonging to the specified category
	 */
	public static List<JRExprFunctionBean> getFunctionsByCategory(String categoryKey){
		List<JRExprFunctionBean> functions=new ArrayList<JRExprFunctionBean>();

		if(!functionsByCategory.containsKey(categoryKey)){
			// Try to get the functions for the specified category
			for (Class<?> clazz : builtinCategoryClasses){
				List<JRExprFunctionBean> jrFunctionsList = ExprFunctionAnnotationsUtil.getJRFunctionsList(clazz);
				for (JRExprFunctionBean b : jrFunctionsList){
					if (b.getCategories().contains(categoryKey)){
						functions.add(b);
					}
				}
			}
			
			functionsByCategory.put(categoryKey, functions);
		}
		else{
			List<JRExprFunctionBean> list = functionsByCategory.get(categoryKey);
			if(list!=null){
				return list;
			}
		}
		
		return functions;
	}
}
