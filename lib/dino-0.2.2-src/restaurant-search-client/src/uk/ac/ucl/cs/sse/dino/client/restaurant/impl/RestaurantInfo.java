package uk.ac.ucl.cs.sse.dino.client.restaurant.impl;

/**
 * Class which stores information about a restaurant, such as name, cuisine,
 * postcode, and price.
 * 
 */
public final class RestaurantInfo {
	private final String name;
	private final String cusine;
	private final String postcode;
	private final int minPrice;
	private final int maxPrice;

	/**
	 * Creates information about a restaurant with the provided data.
	 * @param name the anme of the restaurant.
	 * @param cusine the cusine type of the restaurant.
	 * @param postcode the postcode of the restaurant.
	 * @param minPrice the approximate minimum price of a meal in pounds.
	 * @param maxPrice the approximate maximum price of a meal in pounds.
	 */
	public RestaurantInfo(String name, String cusine, String postcode, int minPrice, int maxPrice) {
		this.name = name;
		this.cusine = cusine;
		this.postcode = postcode;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	/**
	 * Gets the cuisine type of the restaurant.
	 * @return the cuisine type of this restaurant.
	 */
	public String getCusine() {
		return cusine;
	}

	/**
	 * Gets the name of the restaurant.
	 * @return the name of the restaurant.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the post code of the restaurant.
	 * @return the post code of the restaurant.
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Gets the approximate maximum price of a meal in the restaurant.
	 * @return the maximum price of a meal.
	 */
	public int getMaxPrice() {
		return maxPrice;
	}

	/**
	 * Gets the approximate minimum price of a meal in the restaurant.
	 * @return the minimum price of a meal.
	 */
	public int getMinPrice() {
		return minPrice;
	}

}
