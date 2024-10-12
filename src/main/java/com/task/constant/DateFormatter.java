package com.task.constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {

	 public static String dateToString(LocalDate date) {
	        // Define the date format (e.g., "dd-MM-yyyy")
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        // Return the formatted date as a string
	        return date.format(formatter);
	    }

	    // Function to convert String to LocalDate
	    public static LocalDate stringToDate(String dateString) {
	        // Define the date format (should match the format used in dateToString)
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        try {
	            // Parse the string into a LocalDate
	            return LocalDate.parse(dateString, formatter);
	        } catch (DateTimeParseException e) {
	            System.out.println("Invalid date format: " + e.getMessage());
	            return null; // Return null or handle error as needed
	        }
	  
}
	    
}
