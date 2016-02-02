package uk.ac.ucl.cs.sse.dino;

/**
 * A simple, service implementation independent parameter. The aim of this class
 * is to make simplify dealing with services and to allow implementation
 * independence. Using this class should be simpler than using something like
 * OWL-S directly. Parameters have a name, type, value and can have properties.
 * Properties are themselves implemented by this class and so properties can
 * themselves have properties.
 * 
 */
public final class Param {
	private String name;

	private String type;

	private String value;

	private Param[] properties;

	/**
	 * Creates an empty parameter.
	 */
	public Param() {
	}

	/**
	 * Creates a parameter with the specified value.
	 * 
	 * @param name
	 *            the name of the parameter.
	 * @param type
	 *            the type of the parameter.
	 * @param value
	 *            the value of the parameter.
	 */
	public Param(final String name, final String type, final String value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String toString() {
		String s = type + " parameter name=" + name + " value=" + value;
		if (properties != null) {
			for (Param p : properties) {
				s += "\n    " + p;
			}
		}
		return s;
	}

	/**
	 * Gets the URL which identifies the name of this parameter.
	 * 
	 * @return the name of this parameter.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this param.
	 * 
	 * @param name
	 *            the name of the parameter.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the type of this parameter. The value is either "individual" ,
	 * "class" or "string".
	 * 
	 * @return the type of this parameter.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the parameter. For OWL-S the value should be
	 * "individual", "class" or "string".
	 * 
	 * @param type
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * Gets the value of this parameter. If the type of this parameter is
	 * "string" then the value is simply the string. If the type is "individual"
	 * the value is represented by a URI which identifies an element in an
	 * ontology representing the OWL individual. If the type is "class" then the
	 * value is a URI which identifies an OWL class.
	 * 
	 * @return the value of this parameter.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of this parameter.
	 * @param value the value to set for this parameter.
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Gets the properties of this parameter.
	 * @return an array of properties.
	 */
	public Param[] getProperties() {
		return properties;
	}

	/**
	 * Sets the properties of this parameter.
	 * @param properties an array of properties.
	 */
	public void setProperties(final Param[] properties) {
		this.properties = properties;
	}

	/**
	 * Gets the property of this parameter at the specified index.
	 * @param index the index of the property to get.
	 * @return the property at the specified index.
	 */
	public Param getProperties(final int index) {
		return properties[index];
	}

	/**
	 * Sets the property at the specified index.
	 * @param index the index of the property to set.
	 * @param property the property to set at the given index.
	 */
	public void setProperties(final int index, final Param property) {
		this.properties[index] = property;
	}

	/**
	 * Gets the property with the given name.
	 * @param name The name of the property to get.
	 * @return the property with the given name.
	 */
	public Param getProperty(String name) {
		for (Param p : properties) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
}
