package uk.ac.ucl.cs.sse.dino.client.restaurant.osmdisplay;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class which represents an Open Street Map map.
 *
 */
public class OSMMap {
	private List<Way> ways = new LinkedList<Way>();
	
	/**
	 * Adds a way to this map.
	 * @param way the way to add.
	 */
	void addWay(final Way way) {
		ways.add(way);
		
		//Sort ways to ensure that areas are drawn before roads.
		Collections.sort(ways, new Comparator<Way>() {
			public int compare(Way w1, Way w2) {
				if (w1.isArea()) {
					return w2.isArea() ? 0 : -1;
				} else {
					return w2.isArea() ? 1 : 0;
				}
			}
			
		});
	}
	
	/**
	 * Draws this map to the provided graphics context.
	 * @param g non-null graphics context.
	 */
	void draw(final Graphics2D g) {
		for (Way w : ways) {
			w.draw(g);
		}
	}

}
