package net.sf.jasperreports.expressions.functions.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.expressions.JRExprFunctions;
import net.sf.jasperreports.expressions.annotations.JRExprAnnotationsUtils;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionBean;
import net.sf.jasperreports.expressions.functions.CategoryKeys;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;

/**
 * This class contains standard utility methods to interact with the library of functions 
 * embeddable in a JasperReports expression.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see CategoryKeys
 *
 */
public class FunctionsLibraryUtil {
	
	private static Map<String, List<JRExprFunctionBean>> functionsByCategory=null;	

	/**
	 * Returns the list of built-in categories.
	 * 
	 * @return collection of categories (as keys)
	 */
	public static Collection<String> getCategories(){
		if(functionsByCategory==null){
			initLibrary();
		}
		return functionsByCategory.keySet();
	}

	private static void initLibrary() {
		// Initialize support data structures
		functionsByCategory=new HashMap<String, List<JRExprFunctionBean>>();
		
		// Try to load the JR extensions
		List<JRExprFunctions> extensionObjects = ExtensionsEnvironment.getExtensionsRegistry().getExtensions(JRExprFunctions.class);
		List<Class<?>> foundClasses=new ArrayList<Class<?>>();
		for (JRExprFunctions extObj : extensionObjects){
			foundClasses.addAll(extObj.getFunctionsClasses());
		}
	
		// Scan potential annotated classes for functions
		for (Class<?> clazz : foundClasses){
			List<JRExprFunctionBean> jrFunctionsList = JRExprAnnotationsUtils.getJRFunctionsList(clazz);
			for(JRExprFunctionBean f : jrFunctionsList){
				for(String category : f.getCategories()){
					if(!functionsByCategory.containsKey(category)){
						functionsByCategory.put(category, new ArrayList<JRExprFunctionBean>());
					}
					functionsByCategory.get(category).add(f);
				}
			}
		}
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
		if(functionsByCategory==null){
			initLibrary();
		}
		final List<JRExprFunctionBean> list = functionsByCategory.get(categoryKey);
		return list!=null ? list : new ArrayList<JRExprFunctionBean>(0);
	}
}
