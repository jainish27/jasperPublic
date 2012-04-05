package net.sf.jasperreports.expressions.functions.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.expressions.annotations.beans.JRExprFunctionBean;
import net.sf.jasperreports.expressions.annotations.beans.JRExprFunctionParameterBean;
import net.sf.jasperreports.expressions.annotations.util.ExprFunctionAnnotationsUtil;
import net.sf.jasperreports.expressions.functions.CategoryKeys;
import net.sf.jasperreports.expressions.functions.DateTimeFunctions;
import net.sf.jasperreports.expressions.functions.MathFunctions;
import net.sf.jasperreports.expressions.functions.TextFunctions;


public class TestAnnotations {

	public static void main(String[] args) {
		List<JRExprFunctionBean> jrFunctionsList = new ArrayList<JRExprFunctionBean>(); 
		jrFunctionsList.addAll(ExprFunctionAnnotationsUtil.getJRFunctionsList(MathFunctions.class));
		jrFunctionsList.addAll(ExprFunctionAnnotationsUtil.getJRFunctionsList(DateTimeFunctions.class));
		jrFunctionsList.addAll(ExprFunctionAnnotationsUtil.getJRFunctionsList(TextFunctions.class));
		
		
		for (JRExprFunctionBean fdesc : jrFunctionsList){
			System.out.println("======== Function " + fdesc.getName() + " ========");
			System.out.println("Name: " + fdesc.getName());
			System.out.println("Display name: " + fdesc.getDisplayName());
			System.out.println("Description: " + fdesc.getDescription());
			System.out.println("Return type: " + fdesc.getReturnType());
			for (JRExprFunctionParameterBean param : fdesc.getParameters()){
				System.out.println("Param name: " + param.getName());
				System.out.println("Param description: " + param.getDescription());
				System.out.println("Param type: " + param.getParameterType());
				System.out.println("Param ismulti: " + param.isMulti());
				System.out.println("Param isoptional: " + param.isOptional());
			}
			System.out.print("Categories: ");
			for (String cat : fdesc.getCategories()){
				System.out.print("\n\tName: " + CategoryKeys.getCategoryDisplayName(cat,null) + " ---- ");
				System.out.print("Description: " + CategoryKeys.getCategoryDescription(cat,null) + " ");
			}
			System.out.println();
		}
	}

}
