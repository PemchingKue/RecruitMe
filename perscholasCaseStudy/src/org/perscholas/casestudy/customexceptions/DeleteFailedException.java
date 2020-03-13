/*
* Filename: DeleteFailedException.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.customexceptions;

/**
 * @author pk
 *
 */
public class DeleteFailedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteFailedException(String message) {
		super(message);
	}
	
}
