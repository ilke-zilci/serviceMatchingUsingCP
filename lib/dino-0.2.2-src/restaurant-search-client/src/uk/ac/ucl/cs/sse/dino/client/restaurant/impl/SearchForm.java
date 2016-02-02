package uk.ac.ucl.cs.sse.dino.client.restaurant.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import uk.ac.ucl.cs.sse.dino.BrokerCommunicationException;
import uk.ac.ucl.cs.sse.dino.DinoIdException;
import uk.ac.ucl.cs.sse.dino.Param;
import uk.ac.ucl.cs.sse.dino.UnsupportedServiceException;
import uk.ac.ucl.cs.sse.dino.broker.DinoBroker;
import uk.ac.ucl.cs.sse.dino.invocation.ServiceInvocationException;

/**
 * Form used for searching for restaurants.
 * 
 */
public class SearchForm extends JPanel {
	private JSpinner numberOfPeopleSpinner = new JSpinner();

	private final static String[] meals = { "breakfast", "lunch", "dinner" };

	private JComboBox mealComboBox = new JComboBox(meals);

	private final static String[] cuisines = { "Any", "African", "Caribbean",
			"Jamaican", "Eritrean", "North African", "South African",
			"Asian/Oriental", "Chinese", "Indian", "North Indian",
			"South Indian", "Indonesian", "Japanese", "Korean", "Malaysian",
			"Singaporean", "Thai", "Vietnamese", "European", "Basque",
			"British", "English", "Irish", "Scottish", "Eastern European",
			"Hungarian", "Polish", "Russian", "French", "Mediterranean",
			"Greek", "Italian", "Portugese", "Spanish", "Turkish",
			"Modern European", "Northern European", "Belgian", "German",
			"Scandinavian", "International", "Middle Eastern", "Lebanese",
			"Persian", "New Zealand", "North African/Middle Eastern",
			"Moroccan", "Specific Menus", "Bistro", "Gastropub", "Halal",
			"Kosher", "Seafood", "Vegetarian", "The Americas", "Brazilian",
			"Cuban", "Latin American", "Mexican & Tex Mex", "Modern American",
			"North American", "Steakhouse", "World", "Fusion",
			"Modern Eclectic", "Modern Global", "Unusual" };

	private JComboBox cuisineComboBox = new JComboBox(cuisines);

	private JButton searchButton = new JButton("Search");

	private static final String restaurantNS = "http://www.cs.ucl.ac.uk/research/dino/reqdoc/restaurant.owl#";

	private Calendar calendar = Calendar.getInstance();

	private boolean userInputLocation = false;

	private final SearchForm thisPanel;

	/**
	 * Creates a search form for searching for restaurants.
	 * 
	 * @param broker
	 *            the Dino broker used to access services.
	 * @param sessionId
	 *            the session ID to use with the Dino broker.
	 * @param display
	 *            the display for the search results.
	 * @param userInputLocation
	 *            whether to ask the user for the location or to use a GPS
	 *            service ant lat-long to city service to get it.
	 */
	public SearchForm(final DinoBroker broker, final String sessionId,
			final RestaurantSearchDisplay display,
			final boolean userInputLocation) {
		this.userInputLocation = userInputLocation;
		thisPanel = this;

		initComponents(broker, sessionId, display);
		addComponents();
	}

	private void initComponents(final DinoBroker broker,
			final String sessionId, final RestaurantSearchDisplay display) {
		SpinnerNumberModel model = (SpinnerNumberModel) numberOfPeopleSpinner
				.getModel();
		model.setMinimum(1);
		model.setMaximum(100);
		model.setValue(2);

		mealComboBox.setSelectedItem(meals[2]);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display.clearTable();

				Runnable r = new Runnable() {
					public void run() {
						try {
							Param location;
							if (!userInputLocation) {
								Param[] in = {};
								Param[] out = broker.invokeService(sessionId,
										"gps", in).getOutputParameters();

								out = broker.invokeService(sessionId,
										"latlong-to-city", out)
										.getOutputParameters();

								Param city = out[0].getProperties(0);

								location = new Param("cityName", "string", city
										.getValue());
							} else {
								String locationString = JOptionPane
										.showInputDialog(thisPanel,
												"Enter your city");
								location = new Param("cityName", "string",
										locationString);
							}

							// Use current date for search
							calendar
									.setTimeInMillis(System.currentTimeMillis());

							Param day = new Param("day", "string", ""
									+ calendar.get(Calendar.DAY_OF_MONTH));
							Param month = new Param("month", "string", ""
									+ (calendar.get(Calendar.MONTH) + 1));
							Param year = new Param("year", "string", ""
									+ calendar.get(Calendar.YEAR));

							String selectedMeal = mealComboBox
									.getSelectedItem().toString();
							String mealCode;
							if (selectedMeal.equals(meals[0])) {
								mealCode = "b";
							} else if (selectedMeal.equals(meals[1])) {
								mealCode = "l";
							} else {
								mealCode = "d";
							}

							Param meal = new Param("meal", "string", mealCode);

							String cuisineString = cuisineComboBox
									.getSelectedItem().toString();
							Param cuisine = new Param("cuisine", "string",
									cuisineString);

							Param number = new Param("numberOfPeople",
									"string", numberOfPeopleSpinner.getValue()
											.toString());

							Param[] restaurantIn = { day, month, year, meal,
									location, cuisine, number };

							Param[] out = broker.invokeService(sessionId,
									"restaurant-search", restaurantIn)
									.getOutputParameters();

							Param restaurantList = out[0];
							for (Param restaurantParam : restaurantList
									.getProperties()) {
								Param nameProp = restaurantParam
										.getProperty(restaurantNS + "name");
								Param cusineProp = restaurantParam
										.getProperty(restaurantNS + "foodtype");
								Param postcodeProp = restaurantParam
										.getProperty(restaurantNS + "postcode");
								Param minPriceParam = restaurantParam
										.getProperty(restaurantNS + "minPrice");
								Param maxPriceParam = restaurantParam
										.getProperty(restaurantNS + "maxPrice");

								int minPrice = Integer.parseInt(minPriceParam
										.getValue());
								int maxPrice = Integer.parseInt(maxPriceParam
										.getValue());

								RestaurantInfo restaurant = new RestaurantInfo(
										nameProp.getValue(), cusineProp
												.getValue(), postcodeProp
												.getValue(), minPrice, maxPrice);
								display.addRow(restaurant);
							}
						} catch (DinoIdException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						} catch (UnsupportedServiceException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						} catch (ServiceInvocationException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						} catch (BrokerCommunicationException e) {
							display.displayErrorDialog(e);
							e.printStackTrace();
						}
					}
				};
				
				Thread t = new Thread(r);
				t.start();
			}
		});
	}

	private void addComponents() {
		add(new JLabel("Number of people"));
		add(numberOfPeopleSpinner);

		add(new JLabel("Meal"));
		add(mealComboBox);

		add(new JLabel("Cuisine"));
		add(cuisineComboBox);

		add(searchButton);
	}

}
