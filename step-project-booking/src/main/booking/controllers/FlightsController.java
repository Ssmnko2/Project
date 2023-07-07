package main.booking.controllers;


import main.booking.directories.Cities;
import main.booking.objects.Flight;
import main.booking.services.FlightsService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static main.booking.utils.ConfigDate.DATE_FORMAT;
import static main.booking.utils.ConfigDate.TIME_FORMAT;
import static main.booking.utils.ConfigFormats.FORMAT_FLIGHTS_SEATS;
import static main.booking.utils.FormatDate.*;
import static main.booking.utils.FormatString.showMessageWithAnswer;
import static main.booking.utils.FormatString.showTitleForFlightsWithSeats;

public class FlightsController {
    private final FlightsService flightsService;
    private int countOfPassengers = 0;
    public void saveDB(String filePath) {
        flightsService.saveDB(filePath);
    }

    public void readDB(String filePath) {
        flightsService.readDB(filePath);
    }
    public FlightsController() {
        flightsService = new FlightsService();
    }
    public void setPassengersCount(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }
    //Получение конкретного полета по его id
    public Flight getFlightById(int id) {
        return this.flightsService.getFlightById(id);
    }
    /*
        Получение рейсов из БД, которые соответствуют условиям поиска место назначение, дата, количество человек.
        Возвращает список таких рейсов
     */
    public List<Flight> searchFlights(Cities city, LocalDate scheduledDeparture, int numberOfPersons) {
        return this.flightsService.searchFlights(city, scheduledDeparture, numberOfPersons);
    }
    public int getPassengersCount() {
        return countOfPassengers;
    }
    public int getMaxSeatNumber() {

        return flightsService.getAllFlights()
                .stream()
                .mapToInt(Flight::getTotalSeats)
                .max().orElse(-1);

    }
    public void printFlightWithSeats(Flight flight, String format, int index) {
        if (flight != null && format.length() > 0)
            if (index > 1) {
                format = "   ->" + format;
            }
        System.out.printf(format,
                flight.getFlightNo(),
                flight.getScheduledDeparture().format(DateTimeFormatter.ofPattern("dd/MM, kk:mm")),
                flight.getCityTo().getCity(),
                flight.getScheduledArrival().format(DateTimeFormatter.ofPattern("dd/MM, kk:mm")),
                flight.getFreeSeats()
        );
    }

    public void displayFlightInfo(Flight flight) {
        showTitleForFlightsWithSeats();
        printFlightWithSeats(flight, FORMAT_FLIGHTS_SEATS, 1);

    }
    public void printScoreboardOfFlights() {
        this.flightsService.printScoreboardOfFlights();
    }
    public List<Flight> searchFlightsForBooking() {
        String destination = showMessageWithAnswer(
                "Введите место назначения-[пример-Donetsk]:",
                "^[A-Z][A-Z a-z]+",
                "Вы не правильно ввели место назначения или в списке его нет!");

        String date = showMessageWithAnswer(
                "Введите дату вылета - [пример: yyyy-MM-dd]:",
                "^[2][0][1-2][0-9]-[0-9][0-9]-[0-9][0-9]",
                "Вы не правильно ввели дату вылета или рейсов на данную дату нету!");

        countOfPassengers = Integer.parseInt(showMessageWithAnswer(
                "Введите количество билетов:",
                "[0-9]+",
                "Вы указали некорректное число! Осталось " + getMaxSeatNumber() + " мест."));

        return searchFlights(Cities.valueOf(destination.toUpperCase()), LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")), countOfPassengers);
    }
    public void printFlightsWithAction(List<Flight> flights, String format) {
        if (flights.size() > 0)
            flights.forEach(flight -> {
                System.out.printf("%3d. ", flights.indexOf(flight) + +1);
                AtomicInteger index = new AtomicInteger();
                printFlightWithSeats(flight, format, index.addAndGet(1));
            });
    }
    public void printFlightsMenu(List<Flight> flights) {

        System.out.print("     ");
        showTitleForFlightsWithSeats();


        printFlightsWithAction(flights, FORMAT_FLIGHTS_SEATS);
    }
}
