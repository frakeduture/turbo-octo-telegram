/**
 * Project: A00943900Assignment1
 * File: CompareByDescription.java
 * Date: Feb 21, 2017
 * Time: 11:57:43 AM
 */
package a00943900.bcmc.util;

import java.util.Comparator;

import a00943900.bcmc.data.Inventory;

/**
 * @author Thomas Ngo, A00943900
 *
 */
public class CompareByDescription implements Comparator<Inventory> {
	@Override
	public int compare(Inventory item1, Inventory item2) {
		return item1.getDescription().compareTo(item2.getDescription());
	}
}
