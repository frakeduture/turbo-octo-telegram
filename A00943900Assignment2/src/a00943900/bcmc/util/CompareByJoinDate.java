/**
 * Project: A00943900Assignment1
 * File: CompareByJoinDate.java
 * Date: Feb 8, 2017
 * Time: 3:09:48 PM
 */
package a00943900.bcmc.util;

import java.util.Comparator;

import a00943900.bcmc.data.Customer;

/**
 * @author Thomas Ngo, A00943900
 *
 */
public class CompareByJoinDate implements Comparator<Customer> {
	@Override
	public int compare(Customer customer1, Customer customer2) {
		return customer1.getJoinDate().compareTo(customer2.getJoinDate());
	}

}
