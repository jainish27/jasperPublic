package net.sf.jasperreports.expressions;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.extensions.ExtensionsRegistry;

/**
 * This extensions registry is used to collect generic classes that contains implementations of methods used as functions in expressions.
 * These methods use annotations to  specify meta-data as the parameter names, description, etc...
 * 
 * 
 * @author gtoffoli
 *
 */
public class ExprFunctionsRegistry implements ExtensionsRegistry
{

	/**
	 * The list of class names found in the extension
	 */
	private final List<String> expressionFunctionsClassNames;
	
	/**
	 * A single element list containing the JRExprFunctions class. This class holds the list of classes which implement methods to be used as functions
	 */
	private List<JRExprFunctions> expressionFunctionsClasses;
	
	/**
	 * 
	 * @param classNames a set of full referenced class names available in the classpath containing annotated methods
	 */
	public ExprFunctionsRegistry(List<String> classNames)
	{
		this.expressionFunctionsClassNames = classNames;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getExtensions(Class<T> extensionType)
	{
		if (JRExprFunctions.class.equals(extensionType)) 
		{
			if (expressionFunctionsClasses == null && expressionFunctionsClassNames != null)
			{
				JRExprFunctions expressionFunctionsClass = new JRExprFunctions();
				for (String className : expressionFunctionsClassNames)
				{
					
					try {
						Class<?> c = loadClassForName(className);
						expressionFunctionsClass.getFunctionsClasses().add(c);
					} catch (ClassNotFoundException e)
					{
						// log the problem...
					}
				}
				
				expressionFunctionsClasses = new ArrayList<JRExprFunctions>();
				expressionFunctionsClasses.add(expressionFunctionsClass);
			}
			return (List<T>)expressionFunctionsClasses;
		}
		return null;
	}
	
	
	/**
	 * This class uses the system and the context class loader to load the functions class.
	 * 
	 * @param name
	 * @return A loaded class
	 * @throws ClassNotFoundException
	 */
	protected Class<?> loadClassForName(String name) throws ClassNotFoundException
	{
		
		try {
	
			return Class.forName(name, false, this.getClass().getClassLoader());
			
		} catch (ClassNotFoundException e)
		{
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if (contextClassLoader == null)
			{
				throw e;
			}
			
			try
			{
				//attempt to load the class using the context class loader
				return Class.forName(name, false, contextClassLoader);
			}
			catch (ClassNotFoundException e2)
			{
				//fallback to the original exception
				throw e;
			}
		}
	}

}
