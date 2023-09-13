package com.example.kafkaproducermicroservice.Generator;

import com.example.kafkaproducermicroservice.Entities.Event;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//public class RandomEventGenerator {
//
//    private static final String[] COUNTRIES = {
//            "USA", "Canada", "UK", "Germany", "France", "Spain", "Australia", "Japan", "China", "India",
//            "Brazil", "Mexico", "Russia", "South Africa", "Turkey", "Italy", "Sweden", "Norway", "Finland", "Denmark",
//            "Poland", "Ukraine", "Greece", "Portugal", "Netherlands", "Belgium", "Ireland", "Austria", "Switzerland", "Luxembourg",
//            "New Zealand", "Singapore", "Malaysia", "Thailand", "Philippines", "Vietnam", "South Korea", "Indonesia", "Taiwan", "Hong Kong",
//            "Saudi Arabia", "UAE", "Qatar", "Kuwait", "Oman", "Israel", "Egypt", "Morocco", "Algeria", "Tunisia",
//            "Argentina", "Chile", "Peru", "Colombia", "Venezuela", "Uruguay", "Paraguay", "Ecuador", "Bolivia", "Costa Rica"
//    };
//
//    private static final String[] EVENTS = {
//            "Music Concert", "Food Festival", "Tech Conference", "Art Exhibition", "Film Festival",
//            "Theatre Play", "Book Fair", "Craft Fair", "Comedy Show", "Magic Show",
//            "Sports Event", "Fitness Workshop", "Cultural Festival", "Historical Reenactment", "Science Fair",
//            "Gardening Expo", "Automobile Show", "Boat Show", "Air Show", "Fashion Show",
//            "Parade", "Carnival", "Circus", "Ballet Performance", "Opera Performance",
//            "Symphony Orchestra", "Poetry Reading", "Writing Workshop", "Photography Workshop", "Painting Workshop",
//            "Culinary Workshop", "Wine Tasting", "Brewery Tour", "Architectural Tour", "Historical Tour",
//            "Nature Walk", "Bird Watching", "Stargazing Event", "Meteor Shower Viewing", "Solar Eclipse Viewing",
//            "Lunar Eclipse Viewing", "Wellness Retreat", "Yoga Retreat", "Meditation Retreat", "Spiritual Retreat",
//            "Business Networking", "Startup Pitch Event", "Hackathon", "Gaming Tournament", "Film Making Workshop"
//    };
//
//    private static final String[] STATUS_OF_IMPORTANCE = {
//            "High", "Medium", "Low", "Critical", "Urgent",
//            "Mandatory", "Optional", "Exclusive", "VIP", "Public",
//            "Private", "Community", "Local", "National", "International",
//            "Seasonal", "Annual", "Biennial", "Monthly", "Weekly",
//            "Daily", "One-Time", "Recurring", "Introductory", "Closing",
//            "Launch", "Anniversary", "Celebration", "Fundraising", "Charitable",
//            "Educational", "Informative", "Entertaining", "Religious", "Spiritual",
//            "Wellness", "Health", "Safety", "Security", "Environmental",
//            "Scientific", "Technological", "Historical", "Cultural", "Artistic",
//            "Sporting", "Recreational", "Family", "Youth", "Adult",
//            "Senior", "Inclusive", "Diverse", "Traditional", "Modern"
//    };
//
//    private static final String[] DESCRIPTIONS = new String[50];
//    static {
//        for (int i = 0; i < 50; i++) {
//            DESCRIPTIONS[i] = "Description for event " + (i + 1);
//        }
//    }
//
//    private static final Random RANDOM = new Random();
//
//    public static Event generateRandomEvent() {
//        String name = getRandomEvent();
//        String country = getRandomCountry();
//        String type = getRandomType();
//        String description = getRandomDescription();
//        String statusOfImportance = getRandomStatusOfImportance();
//        Date time = getRandomTime();
//        Double actual = getRandomDouble();
//        Double forecast = getRandomDouble();
//        Double previous = getRandomDouble();
//
//        return new Event(null, name, country, type, description, statusOfImportance, time, actual, forecast, previous);
//    }
//
//    public static String getRandomCountry() {
//        return COUNTRIES[RANDOM.nextInt(COUNTRIES.length)];
//    }
//
//    public static String getRandomEvent() {
//        return EVENTS[RANDOM.nextInt(EVENTS.length)];
//    }
//
//    public static String getRandomType() {
//        return EVENTS[RANDOM.nextInt(EVENTS.length)];
//    }
//
//    public static String getRandomDescription() {
//        return DESCRIPTIONS[RANDOM.nextInt(DESCRIPTIONS.length)];
//    }
//
//    public static String getRandomStatusOfImportance() {
//        return STATUS_OF_IMPORTANCE[RANDOM.nextInt(STATUS_OF_IMPORTANCE.length)];
//    }
//
//    public static Date getRandomTime() {
//        long currentTimeMillis = System.currentTimeMillis();
//        // This will generate a random time within the last year
//        long randomTimeMillis = currentTimeMillis - RANDOM.nextInt(365 * 24 * 60 * 60 * 1000);
//        return new Date(randomTimeMillis);
//    }
//
//    public static Double getRandomDouble() {
//        return 100 + (500 - 100) * RANDOM.nextDouble();
//    }
//
//    public static void main(String[] args) {
//        Event randomEvent = generateRandomEvent();
//        System.out.println(randomEvent);
//    }
//}

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
            "High", "Medium", "Low", "Cancelled", "Low", "Medium", "High", "Low", "Medium"
    };

    private static final String[] ECONOMIC_EVENT_TYPES = {
            "GDP",
            "Employment",
            "CPI",
            "PMI",
            "Retail Sales",
            "Business Investment",
            "Stock Market Performance",
            "Housing Market Trends",
            "Trade Balance",
            "Government Debt Levels",
            "Currency Exchange Rates",
            "Commodity Prices",
            "Inflation Rate",
            "Interest Rates",
            "Productivity Levels",
            "Banking Sector Health",
            "Consumer Confidence",
            "Corporate Profits",
            "Wage Growth",
            "Manufacturing Output",
            "Energy Consumption",
            "Unemployment Rate",
            "Export/Import Levels",
            "Credit Market Trends",
            "Market Liquidity",
            "Monetary Policy Updates",
            "Fiscal Policy Updates",
            "Economic Growth Projections",
            "Investment Flow Trends",
            "Corporate Mergers and Acquisitions",
            "Sectoral Performance Analysis",
            "Real Estate Market Dynamics",
            "Industrial Production",
            "Construction Activity",
            "Oil and Gas Market Trends",
            "Tourism Sector Performance",
            "Technology Sector Performance",
            "Healthcare Sector Performance",
            "Environmental Policy Impacts",
            "Socio-economic Indicators",
            "Global Economic Trends",
            "Public Sector Spending",
            "Private Sector Activity",
            "FDI",
            "R&D",
            "Labor Market Trends",
            "Education Sector Performance",
            "Transport and Infrastructure Developments"
    };

    private static final String[] DESCRIPTIONS = new String[50];
    static {
        for (int i = 0; i < 50; i++) {
            DESCRIPTIONS[i] = "Description for event " + (i + 1);
        }
    }

    private static final Random RANDOM = new Random();

    public static List<Event> generateRandomEvents(int numberOfEvents) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < numberOfEvents; i++) {
            Event event = new Event(
                    UUID.randomUUID().toString(),
                    getRandomEvent(),
                    getRandomCountry(),
                    getRandomType(),
                    getRandomDescription(),
                    getRandomStatusOfImportance(),
                    getRandomTime(),
                    getRandomDouble(),
                    getRandomDouble(),
                    getRandomDouble(),
                    new ArrayList<>()
            );
            events.add(event);
        }

        for (int i = 0; i < events.size(); i++) {
            for (int j = 0; j < events.size(); j++) {
                if (i != j) {
                    events.get(i).linkedEventsIds().add(events.get(j).id());
                }
            }
        }

        return events;
    }

    public static String getRandomCountry() {
        return COUNTRIES[RANDOM.nextInt(COUNTRIES.length)];
    }

    public static String getRandomEvent() {
        return EVENTS[RANDOM.nextInt(EVENTS.length)];
    }

    public static String getRandomType() {
        return ECONOMIC_EVENT_TYPES[RANDOM.nextInt(ECONOMIC_EVENT_TYPES.length)];
    }

    public static Date getRandomTime() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        long startDay = startDate.toEpochDay();
        long endDay = endDate.toEpochDay();
        long randomDay = startDay + RANDOM.nextInt((int)(endDay - startDay));

        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        ZoneId zoneId = ZoneId.systemDefault();

        return Date.from(randomDate.atStartOfDay(zoneId).toInstant());
    }

    public static String getRandomDescription() {
        return DESCRIPTIONS[RANDOM.nextInt(DESCRIPTIONS.length)];
    }

    public static String getRandomStatusOfImportance() {
        return STATUS_OF_IMPORTANCE[RANDOM.nextInt(STATUS_OF_IMPORTANCE.length)];
    }

    public static Double getRandomDouble() {
        return 100 + (500 - 100) * RANDOM.nextDouble();
    }

    public static void main(String[] args) {
        List<Event> randomEvents = generateRandomEvents(10);
        randomEvents.forEach(System.out::println);
    }
}

