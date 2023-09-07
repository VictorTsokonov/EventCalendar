package com.example.kafkaproducermicroservice.Generator;

import com.example.kafkaproducermicroservice.Entities.Event;

import java.util.Date;
import java.util.Random;

public class RandomEventGenerator {

    private static final String[] COUNTRIES = {
            "USA", "Canada", "UK", "Germany", "France", "Spain", "Australia", "Japan", "China", "India",
            "Brazil", "Mexico", "Russia", "South Africa", "Turkey", "Italy", "Sweden", "Norway", "Finland", "Denmark",
            "Poland", "Ukraine", "Greece", "Portugal", "Netherlands", "Belgium", "Ireland", "Austria", "Switzerland", "Luxembourg",
            "New Zealand", "Singapore", "Malaysia", "Thailand", "Philippines", "Vietnam", "South Korea", "Indonesia", "Taiwan", "Hong Kong",
            "Saudi Arabia", "UAE", "Qatar", "Kuwait", "Oman", "Israel", "Egypt", "Morocco", "Algeria", "Tunisia",
            "Argentina", "Chile", "Peru", "Colombia", "Venezuela", "Uruguay", "Paraguay", "Ecuador", "Bolivia", "Costa Rica"
    };

    private static final String[] EVENTS = {
            "Music Concert", "Food Festival", "Tech Conference", "Art Exhibition", "Film Festival",
            "Theatre Play", "Book Fair", "Craft Fair", "Comedy Show", "Magic Show",
            "Sports Event", "Fitness Workshop", "Cultural Festival", "Historical Reenactment", "Science Fair",
            "Gardening Expo", "Automobile Show", "Boat Show", "Air Show", "Fashion Show",
            "Parade", "Carnival", "Circus", "Ballet Performance", "Opera Performance",
            "Symphony Orchestra", "Poetry Reading", "Writing Workshop", "Photography Workshop", "Painting Workshop",
            "Culinary Workshop", "Wine Tasting", "Brewery Tour", "Architectural Tour", "Historical Tour",
            "Nature Walk", "Bird Watching", "Stargazing Event", "Meteor Shower Viewing", "Solar Eclipse Viewing",
            "Lunar Eclipse Viewing", "Wellness Retreat", "Yoga Retreat", "Meditation Retreat", "Spiritual Retreat",
            "Business Networking", "Startup Pitch Event", "Hackathon", "Gaming Tournament", "Film Making Workshop"
    };

    private static final String[] STATUS_OF_IMPORTANCE = {
            "High", "Medium", "Low", "Critical", "Urgent",
            "Mandatory", "Optional", "Exclusive", "VIP", "Public",
            "Private", "Community", "Local", "National", "International",
            "Seasonal", "Annual", "Biennial", "Monthly", "Weekly",
            "Daily", "One-Time", "Recurring", "Introductory", "Closing",
            "Launch", "Anniversary", "Celebration", "Fundraising", "Charitable",
            "Educational", "Informative", "Entertaining", "Religious", "Spiritual",
            "Wellness", "Health", "Safety", "Security", "Environmental",
            "Scientific", "Technological", "Historical", "Cultural", "Artistic",
            "Sporting", "Recreational", "Family", "Youth", "Adult",
            "Senior", "Inclusive", "Diverse", "Traditional", "Modern"
    };

    private static final String[] DESCRIPTIONS = new String[50];
    static {
        for (int i = 0; i < 50; i++) {
            DESCRIPTIONS[i] = "Description for event " + (i + 1);
        }
    }

    private static final Random RANDOM = new Random();

    public static Event generateRandomEvent() {
        String name = getRandomEvent();
        String country = getRandomCountry();
        String type = getRandomType();
        String description = getRandomDescription();
        String statusOfImportance = getRandomStatusOfImportance();
        Date time = getRandomTime();
        Double actual = getRandomDouble();
        Double forecast = getRandomDouble();
        Double previous = getRandomDouble();

        return new Event(null, name, country, type, description, statusOfImportance, time, actual, forecast, previous);
    }

    public static String getRandomCountry() {
        return COUNTRIES[RANDOM.nextInt(COUNTRIES.length)];
    }

    public static String getRandomEvent() {
        return EVENTS[RANDOM.nextInt(EVENTS.length)];
    }

    public static String getRandomType() {
        return EVENTS[RANDOM.nextInt(EVENTS.length)];
    }

    public static String getRandomDescription() {
        return DESCRIPTIONS[RANDOM.nextInt(DESCRIPTIONS.length)];
    }

    public static String getRandomStatusOfImportance() {
        return STATUS_OF_IMPORTANCE[RANDOM.nextInt(STATUS_OF_IMPORTANCE.length)];
    }

    public static Date getRandomTime() {
        long currentTimeMillis = System.currentTimeMillis();
        // This will generate a random time within the last year
        long randomTimeMillis = currentTimeMillis - RANDOM.nextInt(365 * 24 * 60 * 60 * 1000);
        return new Date(randomTimeMillis);
    }

    public static Double getRandomDouble() {
        return 100 + (500 - 100) * RANDOM.nextDouble();
    }

    public static void main(String[] args) {
        Event randomEvent = generateRandomEvent();
        System.out.println(randomEvent);
    }
}

