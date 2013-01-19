/**
 * 
 */
package com.github.masalthunlass.complex.model.queries;

/**
 * Defines a SPARQL Query with related metadata.
 * Will be sent to the client interface to offer default queries to check out.
 * @date 190113
 */
public class DefaultQuery {
	
	private String name;
	private String title;
	private String description;
	private String string;
	
	/**
	 * @param name
	 * @param title
	 * @param description
	 * @param string
	 */
	public DefaultQuery(String name, String title, String description, String string) {
		super();
		this.name = name;
		this.title = title;
		this.description = description;
		this.string = string;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}
	/**
	 * @param string the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("name:%s, title:%s, description:%s, string:%s", name, title, description, string);
	}
}
