/**
 * Project: A00943900Assignment1
 * File: CompareByQuantity.java
 * Date: Feb 21, 2017
 * Time: 4:02:32 PM
 */
package a00943900.bcmc.util;

import java.util.Comparator;

import a00943900.bcmc.data.Inventory;

/**
 * @author Thomas Ngo, A00943900
 *
 */
public class CompareByQuantity implements Comparator<Inventory> {

	public int compare(Inventory item1, Inventory item2) {
		return (int) (item1.getQuantity() - item2.getQuantity());
	}
}
