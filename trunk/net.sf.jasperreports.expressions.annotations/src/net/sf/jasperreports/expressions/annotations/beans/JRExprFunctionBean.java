package net.sf.jasperreports.expressions.annotations.beans;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.expressions.annotations.JRExprFunction;

/**
 * Bean to describe the a function of the expression library.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see JRExprFunction
 */
public class JRExprFunctionBean implements Comparable<JRExprFunctionBean>{

	private String name;
	private String displayName;
	private String description;
	private List<JRExprFunctionParameterBean> parameters;
	private Class<?> returnType;
	private List<String> categories;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<JRExprFunctionParameterBean> getParameters() {
		if(parameters==null){
			parameters=new ArrayList<JRExprFunctionParameterBean>();
		}
		return parameters;
	}
	public void setParameters(List<JRExprFunctionParameterBean> parameters) {
		this.parameters = parameters;
	}
	public Class<?> getReturnType() {
		return returnType;
	}
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
	public List<String> getCategories() {
		if(categories==null){
			categories=new ArrayList<String>();
		}
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	@Override
	public int compareTo(JRExprFunctionBean o) {
		return name.compareTo(o.getName());
	}
}
