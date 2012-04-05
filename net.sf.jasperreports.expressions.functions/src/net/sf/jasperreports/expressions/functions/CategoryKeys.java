package net.sf.jasperreports.expressions.functions;

import java.util.ResourceBundle;

/**
 * Contains a list of predefined IDs to organize the JRExprFunction library in categories. 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class CategoryKeys {
	/** Category for text/string manipulation functions */
	public static final String TEXT="TEXT";
	/** Category for date and time manipulation functions */
	public static final String DATE_TIME="DATE_TIME";
	/** Category for mathematical operations functions */
	public static final String MATH="MATH"; 
	/** Category for logical operations functions */
	public static final String LOGICAL="LOGICAL";
	/** Category for information functions */
	public static final String INFORMATION="INFORMATION";
	/** Category for financial functions */
	public static final String FINANCIAL="FINANCIAL";
	/** Category for datasource related functions */
	public static final String DATASOURCES="DATASOURCES";
	
	/**
	 * Returns a human-readable text representation for the category specified.
	 * 
	 * <p>
	 * When the specified resource bundle is <code>null</code> then a default one
	 * is used.
	 * 
	 * @param category the category 
	 * @param the optional bundle from which retrieve the information
	 * @return the category display name
	 */
	public static String getCategoryDisplayName(String category, ResourceBundle rb){
		if(rb==null){
			rb=getResourceBundle();
		}
		String catKey = "Category."+category+".display";
		if(rb.containsKey(catKey)){
			return rb.getString(catKey);
		}
		else{
			// Fallback
			return category;
		}
	}
	
	/**
	 * Returns a human-readable description associated to the specified category.
	 *
	 * <p>
	 * When the specified resource bundle is <code>null</code> then a default one
	 * is used.
	 *   
	 * @param category the category
	 * @param the optional bundle from which retrieve the information
	 * @return the category description, <code>null</code> if none
	 */
	public static String getCategoryDescription(String category, ResourceBundle rb){
		if(rb==null){
			rb=getResourceBundle();
		}
		String catKey = "Category."+category+".description";
		if(rb.containsKey(catKey)){
			return rb.getString(catKey);
		}
		return null;
	}
	
	/*
	 * Gets the resource bundle containing the information 
	 * for category display names and descriptions.
	 */
	private static ResourceBundle getResourceBundle(){
		return ResourceBundle.getBundle("MessagesBundle");
	}
	
	private CategoryKeys(){
		// Prevents instantiation
	}
}
