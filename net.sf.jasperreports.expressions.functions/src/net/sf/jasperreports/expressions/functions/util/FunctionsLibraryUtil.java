package net.sf.jasperreports.expressions.functions.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.expressions.JRExprFunctions;
import net.sf.jasperreports.expressions.annotations.JRExprAnnotationsUtils;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionBean;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;

/**
 * This class contains standard utility methods to interact with the library of functions 
 * embeddable in a JasperReports expression.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class FunctionsLibraryUtil {
	
	private static Map<String, List<JRExprFunctionBean>> functionsByCategory=null;
	private static List<JRExprFunctionBean> allFunctions=null;
	private static List<String> libraryClassNames=null;

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
		allFunctions=new ArrayList<JRExprFunctionBean>();
		libraryClassNames=new ArrayList<String>();
		
		// Try to load the JR extensions
		List<JRExprFunctions> extensionObjects = ExtensionsEnvironment.getExtensionsRegistry().getExtensions(JRExprFunctions.class);
		List<Class<?>> foundClasses=new ArrayList<Class<?>>();
		for (JRExprFunctions extObj : extensionObjects){
			foundClasses.addAll(extObj.getFunctionsClasses());
		}
	
		// Scan potential annotated classes for functions
		for (Class<?> clazz : foundClasses){
			String clazzName=clazz.getName();
			if(!libraryClassNames.contains(clazzName)){
				libraryClassNames.add(clazzName);
			}
			List<JRExprFunctionBean> jrFunctionsList = JRExprAnnotationsUtils.getJRFunctionsList(clazz);
			for(JRExprFunctionBean f : jrFunctionsList){
				for(String category : f.getCategories()){
					if(!functionsByCategory.containsKey(category)){
						functionsByCategory.put(category, new ArrayList<JRExprFunctionBean>());
					}
					functionsByCategory.get(category).add(f);
					if(!allFunctions.contains(f)){
						allFunctions.add(f);
					}
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
	
	/**
	 * Returns the list of functions, no matter which category they belong to.
	 * 
	 * <p>
	 * According to this, if a function belongs to more categories it will appear only
	 * once inside the returned list. Moreover, the list is not guaranteed to be sorted.
	 * 
	 * @return the complete list of all functions available
	 */
	public static List<JRExprFunctionBean> getAllFunctions(){
		if(allFunctions==null){
			initLibrary();
		}
		return allFunctions;
	}
	
	/**
	 * Returns the list of classes that maintain the functions exposed by the library. 
	 * 
	 * @return the list of class names
	 */
	public static List<String> getLibraryClasses(){
		if(libraryClassNames==null){
			initLibrary();
		}
		return libraryClassNames;
	}
	
	/**
	 * Checks if it exists a function inside the library with the specified name.
	 * 
	 * @param functionName the function name
	 * @return <code>true</code> if a function exists, <code>false</code> otherwise
	 */
	public static boolean existsFunction(String functionName){
		for (JRExprFunctionBean f : getAllFunctions()){
			if(f.getName().equals(functionName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Forces the reload of the functions library.
	 */
	public static void reloadLibrary(){
		functionsByCategory.clear();
		functionsByCategory=null;
		allFunctions.clear();
		allFunctions=null;
		libraryClassNames.clear();
		libraryClassNames=null;
		initLibrary();
	}
}
