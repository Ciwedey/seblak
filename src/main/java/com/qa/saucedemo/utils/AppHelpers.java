package com.qa.saucedemo.utils;

import java.util.Random;

public class AppHelpers {
    
    // Create a random zip code contain 5 digits number
    public int generateZipCode() {
        Random random = new Random();
        int randomZipCode = 10000 + random.nextInt(90000); // ensures a number between 10000 and 99999
        System.out.println("Generate random zip code: " + randomZipCode);
        return randomZipCode;
    }

    // Array of possible first names
    private static final String[] firstNames = {
        "Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah"
    };

    // Generate a random first name
    public String generateRandomFirstName() {
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        String generatedFirstName = firstNames[index];
        System.out.println("Generated first name: " + generatedFirstName);
        return generatedFirstName;
    }

    // Array of possible last names
    private static final String[] lastNames = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"
    };

    // Generate a random last name
    public String generateRandomLastName() {
        Random random = new Random();
        int index = random.nextInt(lastNames.length);
        String generatedLastName = lastNames[index];
        System.out.println("Generated last name: " + generatedLastName);
        return generatedLastName;
    }
}
