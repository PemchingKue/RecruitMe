/*
* Filename: UpdateFailedException.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.customexceptions;

/**
 * @author pk
 *
 */
public class UpdateFailedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UpdateFailedException(String message) {
		super(message);
	}

}
