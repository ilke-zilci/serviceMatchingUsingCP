package uk.ac.ucl.cs.sse.dino.client.bookprice;

/**
 * Stores information about a book such as title and price.
 *
 */
final class BookInfo {
	private final String title;
	private final double price;
	
	/**
	 * Creates book information with the given title and price.
	 * @param title the title of the book.
	 * @param price the price of the book.
	 */
	BookInfo(final String title, final double price) {
		this.title = title;
		this.price = price;
	}
	
	/**
	 * Creates book information with the given title and an undefined value (NaN) for the price.
	 * @param title the title of the book.
	 */
	BookInfo(final String title) {
		this.title = title;
		price = Double.NaN;
	}
	
	/**
	 * Returns the title of the book.
	 * @return non-null title of the book.
	 */
	String getTitle() {
		return title;
	}
	
	/**
	 * Returns the price of the book or NaN if the price is unknown.
	 * @return the price of the book or NaN if the price is unknown.
	 */
	double getPrice() {
		return price;
	}

}
