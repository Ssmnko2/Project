package main.java.controller;

import main.booking.objects.Flight;
import main.booking.services.FlightsService;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static main.booking.utils.ConfigDate.DATE_FORMAT;
import static main.booking.utils.ConfigDate.TIME_FORMAT;
import static main.booking.utils.FormatDate.dateToStr;
import static main.booking.utils.FormatDate.timeOfDayLongToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightControllerTest {
    @Test
    void getPassengersCount() {
        int countOfPassengers = 0;

        assertEquals(countOfPassengers, 0);
    }

    @Test
    void setPassengersCount() {
        int countOfPassengers = 0;
        assertEquals(countOfPassengers, 0);

        countOfPassengers = 1;
        assertEquals(countOfPassengers, 1);
    }

    @Test
    void saveDB() {
        FlightsService flightService = new FlightsService();
        flightService.saveDB("./db/flights.txt");
    }

    @Test
    void readDB() {
        FlightsService flightService = new FlightsService();
        flightService.readDB("./db/flights.txt");
    }

    @Test
    void printAllSortedCurrent24Hours() {
        String FORMAT_FLIGHTS = "| %-10s | %-20s | %-20s | %-15s |\n";
        String format = FORMAT_FLIGHTS;
        FlightsService flightService = new FlightsService();
        flightService.getAllFlights()
                .stream()
                .sorted(Comparator.comparingLong(Flight::getDepartureDateTime))
                .forEach(f ->
                        printFlight(f, format)
                );


    }



    void printFlight(Flight flight, String format) {
        String dateTime = String.format("%s %s", dateToStr(flight.getDepartureDateTime(), DATE_FORMAT), dateToStr(flight.getDepartureDateTime(), TIME_FORMAT));
        System.out.printf(format,
                flight.getFlightNo(),
                dateTime,
                flight.getCityFrom(),
                timeOfDayLongToString(flight.getDepartureDateTime())
        );

        assertEquals(dateTime, "");
    }

    @Test
    void getByFlightNumber() {
        FlightsService flightService = new FlightsService();
        String flightNumber = "1";
        Flight flight = null;

        flight = flightService.getAllFlights()
                .stream()
                .filter(f -> f.getFlightNo().equalsIgnoreCase(flightNumber))
                .findFirst().orElse(null);



        assertEquals(flight, null);
    }
}
