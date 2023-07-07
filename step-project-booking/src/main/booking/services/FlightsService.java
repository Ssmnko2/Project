package main.booking.services;


import main.booking.DAO.DAOFlights;
import main.booking.directories.Cities;
import main.booking.logger.Logs;
import main.booking.objects.Flight;
import main.booking.collections.CollectionFlightsDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlightsService {
    private final CollectionFlightsDAO collectionFlightsDAO;


    public FlightsService() {
        collectionFlightsDAO = new CollectionFlightsDAO();
    }


    public Flight getFlightById(int id) {
        int index = indexFlight(id);
        if (index != -1) {
            return this.collectionFlightsDAO.getFlightById(index);
        }
        System.out.printf("Рейс с этим идентификатором '%d' не найден\n", id);
        return null;
    }

    private int indexFlight(int id) {
        List<Flight> flights = this.getAllFlights();
        int index = -1;
        for (int i = 0; i < flights.size(); i++) {
            if (id == flights.get(i).getIdFlight()) {
                index = i;
                return index;
            }
        }
        return index;
    }

    public boolean deleteFlight(Flight flight) {
        return this.collectionFlightsDAO.deleteFlight(flight);
    }

    public void deleteFlight(int id) {
        int index = indexFlight(id);
        if (index != -1) {
            this.collectionFlightsDAO.deleteFlight(index);

        } else {

            System.out.printf("Рейс с этим идентификатором '%d' не найден\n", id);


        }
    }

    public boolean saveFlight(Flight flight) {
        return this.collectionFlightsDAO.saveFlight(flight);
    }

    public boolean updateFlight(Flight flight) {
        return this.collectionFlightsDAO.updateFlight(flight);
    }

    public boolean updateFlight(Flight flight, int countSeats) {
        int numberOfFreeSeats = flight.getFreeSeats() - countSeats;
        if (numberOfFreeSeats >= 0) {
            flight.setFreeSeats(numberOfFreeSeats);
            return this.collectionFlightsDAO.updateFlight(flight);
        } else {
            System.out.println("ОШИБКА при передаче количества мест для бронирования!");
            return false;
        }
    }

    public boolean updateFlight(int[] booking) {
        Flight flight = this.getFlightById(booking[0]);
        if (flight != null && booking != null && booking.length == 2) {
            int numberOfFreeSeats = flight.getFreeSeats() + booking[1];
            if (numberOfFreeSeats <= flight.getTotalSeats()) {
                flight.setFreeSeats(numberOfFreeSeats);
                return this.collectionFlightsDAO.updateFlight(flight);
            }
        }
        System.out.println("ОШИБКА переноса количества мест при отмене бронирования");
        return false;
    }

    public List<Flight> getFutureFlights() {
        List<Flight> futureFlights = new ArrayList<>();
        List<Flight> flights = this.getAllFlights();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusOneDay = now.plusDays(1);

        for (Flight flight : flights) {
            if (flight.getScheduledDeparture().isAfter(now) && flight.getScheduledDeparture().isBefore(nowPlusOneDay)) {
                futureFlights.add(flight);
            }
        }
        return futureFlights;
    }

    public List<Flight> searchFlights(Cities city, LocalDate scheduledDeparture, int numberOfPersons) {
        List<Flight> allFlights = this.getAllFlights();
        List<Flight> flights = new ArrayList<>();

        for (Flight flight : allFlights) {
            LocalDate date = flight.getScheduledArrival().toLocalDate();
            if (city.equals(flight.getCityTo()) && date.equals(scheduledDeparture) && (flight.getFreeSeats() - numberOfPersons) >= 0) {
                flights.add(flight);
            }
        }
        return flights;
    }

    //Вывод онлайн-табло про все рейсы в ближайшие 24 часа
    public void printScoreboardOfFlights() {
        List<Flight> flights = this.getFutureFlights();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM, kk:mm");
        System.out.println("┌───────────┬───────────────┬────────────────┬─────────────────────┬───────────────────────────────────────────┐");
        System.out.printf("│%6s│ %-8s │ %s │ %-20s│ %-41s │\n", " ID flight ", "Flight number", "Departure time", "  City", "Airline");
        System.out.println("├───────────│───────────────│────────────────│─────────────────────│───────────────────────────────────────────┤");

        for (Flight flight : flights) {
            System.out.printf("│   %06d  │ %-13s │  %s  │ %-15s     │ %-36s      │\n", flight.getIdFlight(), flight.getFlightNo(),
                    flight.getScheduledDeparture().format(formatter), flight.getCityTo().getCity(),
                    flight.getAirline().getName());
        }
        System.out.println("└───────────┴───────────────┴────────────────┴─────────────────────┴───────────────────────────────────────────┘");

    }

    public List<Flight> getAllFlights() {
        Logs.log("get all flights", "info");
        return collectionFlightsDAO.getAllFlights();
    }

    public void saveDB(String path) {
        Logs.log("save DB", "info");
        collectionFlightsDAO.saveDB(path);
    }

    public void readDB(String path) {
        Logs.log("read DB", "info");
        collectionFlightsDAO.readDB(path);
    }

}
