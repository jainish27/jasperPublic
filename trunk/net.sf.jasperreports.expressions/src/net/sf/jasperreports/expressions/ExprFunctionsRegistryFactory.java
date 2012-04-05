package net.sf.jasperreports.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;
import net.sf.jasperreports.extensions.DefaultExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;

public class ExprFunctionsRegistryFactory  implements ExtensionsRegistryFactory
{

	/**
	 * The key used inside a jasperrpeorts_extensions.properties file to denote the classes
	 * referenced by this extension 
	 */
	public final static String EXPRESSION_FUNCTIONS_CLASSES_PROPERTY_PREFIX = 
		DefaultExtensionsRegistry.PROPERTY_REGISTRY_PREFIX + "expression.functions.classes.";
	
	/**
	 * The key used to register the specific type of extension factory
	 */
	public final static String PROPERTY_EXPRESSION_FUNCTIONS_REGISTRY_FACTORY =
		DefaultExtensionsRegistry.PROPERTY_REGISTRY_FACTORY_PREFIX + "expression.functions";
	
	/**
	 * 
	 */
	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties)
	{
		List<PropertySuffix> expressionsFunctionsClassesProperties = JRPropertiesUtil.getProperties(properties, EXPRESSION_FUNCTIONS_CLASSES_PROPERTY_PREFIX);
		List<String> classNames = new ArrayList<String>();
		for (Iterator<PropertySuffix> it = expressionsFunctionsClassesProperties.iterator(); it.hasNext();)
		{
			PropertySuffix functionsClassesProp = it.next(); 

			// We assume this property has a value like...  a.b.c.ClassA, a.b.d.ClassB,....
			
			String[] classes = functionsClassesProp.getValue().split(",");
			
			for (String className : classes)
			{
				className = className.trim();
			    if (className.length() > 0)
			    {
			    	classNames.add( className);
			    }
			}
		}
		
		return new  ExprFunctionsRegistry(classNames);
	}

}

