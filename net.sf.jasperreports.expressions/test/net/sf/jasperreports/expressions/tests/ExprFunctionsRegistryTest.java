/**
 * 
 */
package net.sf.jasperreports.expressions.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.extensions.ExtensionsEnvironment;
import net.sf.jasperreports.functions.FunctionsBundle;

import org.junit.Test;

/**
 * @author gtoffoli
 *
 */
public class ExprFunctionsRegistryTest {


	@Test
	public void test() {
		
		List<FunctionsBundle> functions = ExtensionsEnvironment.getExtensionsRegistry().getExtensions(FunctionsBundle.class);
		
		List<Class<?>> foundClasses = new ArrayList<Class<?>>();
		
		for (FunctionsBundle f : functions)
		{
			foundClasses.addAll(f.getFunctionClasses());
		}
		
		assertTrue(foundClasses.size() == 2);
		assertEquals(SampleClassA.class,  foundClasses.get(0) );
		assertEquals(SampleClassB.class,  foundClasses.get(1) );
	}

}
