/*
* Filename: RecruitMeLogger.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.logger;

import java.util.logging.Logger;

/**
 * @author PK
 *
 */
public class RecruitMeLogger {

	public Logger logger;
	
	public RecruitMeLogger(){
		logger = Logger.getLogger(RecruitMeLogger.class.getName()); 
	}
}
