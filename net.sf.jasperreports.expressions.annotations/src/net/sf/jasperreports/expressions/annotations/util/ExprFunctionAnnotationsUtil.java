package net.sf.jasperreports.expressions.annotations.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.expressions.annotations.JRExprFunction;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionCategories;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameter;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionParameters;
import net.sf.jasperreports.expressions.annotations.beans.JRExprFunctionBean;
import net.sf.jasperreports.expressions.annotations.beans.JRExprFunctionParameterBean;

/**
 * Support class that works with the annotated Class(es).
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class ExprFunctionAnnotationsUtil {

	/**
	 * Retrieves the list of functions contributed in the specified class reference.
	 * 
	 * <p>
	 * The method seeks for annotated methods with the annotation {@link JRExprFunction} 
	 * in order to build the basic set of functions, and then scan for similar ones to
	 * decide which parameters are mandatory and which optional. 
	 * 
	 * @param clazz the class reference that is supposed to contain expression functions
	 * @return a list of JR expression functions
	 */
	public static List<JRExprFunctionBean> getJRFunctionsList(Class<?> clazz){
		Map<String, List<Method>> methodsCache = buildAnnotatedMethodsCache(clazz);
		
		List<JRExprFunctionBean> functionsList=new ArrayList<JRExprFunctionBean>();
		for (String functionName : methodsCache.keySet()){
			JRExprFunctionBean jrFunction = createJRFunction(methodsCache.get(functionName));
			functionsList.add(jrFunction);
		}
		
		return functionsList;
	}

	/*
	 * Creates a bean used to represent a "function".
	 */
	private static JRExprFunctionBean createJRFunction(List<Method> methods) {
		
		JRExprFunctionBean funct=new JRExprFunctionBean();
		// The first instance is the one annotated with @JRFunction
		// that maintains all the necessary infos to prepare the skeleton of the function bean
		Method first = methods.get(0);
		JRExprFunction functionAnnotation = first.getAnnotation(JRExprFunction.class);
		funct.setName(first.getName());
		funct.setDisplayName(functionAnnotation.name());
		funct.setDescription(functionAnnotation.description());
		funct.setReturnType(first.getReturnType());
		JRExprFunctionCategories functionCategoriesAnnotation=first.getAnnotation(JRExprFunctionCategories.class);
		funct.getCategories().addAll(Arrays.asList(functionCategoriesAnnotation.value()));
		JRExprFunctionParameters parametersAnnotation = first.getAnnotation(JRExprFunctionParameters.class);
		if(parametersAnnotation!=null){
			for(JRExprFunctionParameter param : parametersAnnotation.value()){
				// Get basic info from the annotation
				JRExprFunctionParameterBean paramDescriptor=new JRExprFunctionParameterBean();
				paramDescriptor.setName(param.name());
				paramDescriptor.setDescription(param.description());
				funct.getParameters().add(paramDescriptor);
			}
		}
		
		// Now computes the mandatory and cardinality of the parameters
		int paramIndex=0;
		int paramsNum=funct.getParameters().size();
		for (int i=0; i<methods.size() && paramIndex<paramsNum; i++){
			Method currMethod = methods.get(i);
			Class<?>[] parameterTypes = currMethod.getParameterTypes();
			boolean isOptional=(i==0)?false:true; // all parameters of the first method are for sure mandatory			
			for(int j=paramIndex;j<parameterTypes.length;j++,paramIndex++){
				boolean isMulti=parameterTypes[j].isArray();
				JRExprFunctionParameterBean paramFunctBean = funct.getParameters().get(paramIndex);
				if(paramFunctBean!=null){
					paramFunctBean.setOptional(isOptional);
					paramFunctBean.setMulti(isMulti);
					paramFunctBean.setParameterType(parameterTypes[j]);
				}
				else{
					// ERROR params number mismatch in descriptions/names/types
					// TODO issue Exception or log error (?!)
				}
			}
		}
		
		return funct;
	}

	/*
	 * Creates a support map that maintain for each method name (name as key) 
	 * a list of Methods found in the Class.
	 */
	private static Map<String,List<Method>> buildAnnotatedMethodsCache(Class<?> clazz) {
		Map<String,List<Method>> methodsByNameMap=new HashMap<String, List<Method>>();
				
		// First round locate all methods with the JRFunction annotation
		// in order to have a list of what will be the functions
		for (Method m : clazz.getMethods()){
			JRExprFunction jrFunctionAnn = m.getAnnotation(JRExprFunction.class);
			if(jrFunctionAnn!=null){
				String methodName = m.getName();
				List<Method> methods=methodsByNameMap.get(methodName);
				if(methods==null){
					methods=new ArrayList<Method>();
				}
				methods.add(m);
				methodsByNameMap.put(methodName, methods);
			}
		}
		
		// After that enrich the map with the remaining methods that have the same function
		// name but a different list of parameters
		for (Method m : clazz.getMethods()){
			JRExprFunction jrFunctionAnn = m.getAnnotation(JRExprFunction.class);
			if(jrFunctionAnn==null){
				String methodName = m.getName();
				List<Method> methods=methodsByNameMap.get(methodName);
				if (methods!=null){
					methods.add(m);
				}
			}
		}
		
		
		return methodsByNameMap;
	}
	
}
