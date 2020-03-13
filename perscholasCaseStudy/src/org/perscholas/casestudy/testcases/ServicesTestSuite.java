/*
* Filename: ServicesTestSuite.java
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
*/
package org.perscholas.casestudy.testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalendarServicesTestCases.class, 
		CandidateServicesTestCases.class, 
		ClientServicesTestCases.class,
		JobRoleServicesTestCases.class, 
		RecruiterServicesTestCases.class, 
		ResumeServicesTestCases.class })

public class ServicesTestSuite {

}
