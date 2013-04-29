/**
 * 
 */
package net.sf.jasperreports.expressions.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.expressions.JRExprFunctions;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;

import org.junit.Test;

/**
 * @author gtoffoli
 *
 */
public class ExprFunctionsRegistryTest {


	@Test
	public void test() {
		
		List<JRExprFunctions> functions = ExtensionsEnvironment.getExtensionsRegistry().getExtensions(JRExprFunctions.class);
		
		List<Class<?>> foundClasses = new ArrayList<Class<?>>();
		
		for (JRExprFunctions f : functions)
		{
			foundClasses.addAll(f.getFunctionsClasses());
		}
		
		assertTrue(foundClasses.size() == 2);
		assertEquals(SampleClassA.class,  foundClasses.get(0) );
		assertEquals(SampleClassB.class,  foundClasses.get(1) );
	}

}
