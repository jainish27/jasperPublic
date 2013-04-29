package net.sf.jasperreports.expressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * This class contains a list of classes which contain one or more methods annotated to be used in example as expressions language functions in the Jaspersoft Studio
 * expressions editor.
 * 
 * The registry may return one or more instances of this classes.
 * 
 * @author gtoffoli
 *
 */
public class JRExprFunctions {

	private List<Class<?>> functionsClasses = new ArrayList<Class<?>>();
	
	/**
	 * 
	 * @param functionsCalsses
	 */
	public JRExprFunctions(Collection<Class<?>> functionsCalsses) {
		super();
		this.functionsClasses.addAll(functionsCalsses);
	}
	
	/**
	 * 
	 */
	public JRExprFunctions() {
		super();
	}


	/**
	 * @return the functionsCalsses
	 */
	public List<Class<?>> getFunctionsClasses() {
		return functionsClasses;
	}


	/**
	 * @param functionsCalsses the functionsCalsses to set
	 */
	public void setFunctionsClasses(List<Class<?>> functionsCalsses) {
		this.functionsClasses = functionsCalsses;
	}


	
}
