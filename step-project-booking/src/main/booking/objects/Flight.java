package main.booking.objects;


import main.booking.directories.Airlines;
import main.booking.directories.Airplanes;
import main.booking.directories.Cities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Serializable {
    private static int id;
    private int idFlight;
    private String flightNo;    //номер рейса
    private LocalDateTime scheduledDeparture; //запланированный вылет
    private long departureDateTime;
    private Cities cityFrom;
    private Cities cityTo;
    private LocalDateTime scheduledArrival; //запланированный прилет
    private Airlines airline;
    private Airplanes airplane;
    private int totalSeats;
    private int freeSeats;
    private double price;
    private List<Passenger> passengers;


    public Flight(String s, long l, String s1, int i){}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightNo.equals(flight.getFlightNo());
    }

    @Override
    public int hashCode() {

        int result = 11;
        int coef = 31;

        return coef * result + flightNo.hashCode();

    }


    public Flight(LocalDateTime scheduledDeparture, Cities cityFrom, Cities cityTo,
                  LocalTime flightDuration, Airlines airline, String flightNo, Airplanes airplane, double price) {
        this.scheduledDeparture = scheduledDeparture;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        setScheduledArrival(flightDuration);

        this.airline = airline;
        this.setFlightNo(flightNo);
        this.airplane = airplane;
        this.totalSeats = this.airplane.getNumberOfSeats();
        this.freeSeats = this.airplane.getNumberOfSeats();
        this.price = price;
        id++;
        this.idFlight = id;
        this.passengers = new ArrayList();

    }


    public int getIdFlight() {
        return this.idFlight;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = this.airline.getIataCode() + " " + flightNo;
    }

    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public Cities getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(Cities cityFrom) {
        this.cityFrom = cityFrom;
    }

    public Cities getCityTo() {
        return cityTo;
    }

    public void setCityTo(Cities cityTo) {
        this.cityTo = cityTo;
    }

    public LocalDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalTime flightDuration) {
        this.scheduledArrival = this.scheduledDeparture.plusHours(flightDuration.getHour()).plusMinutes(flightDuration.getMinute());
    }

    public Airlines getAirline() {
        return airline;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public Airplanes getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplanes airplane) {
        this.airplane = airplane;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(long departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public boolean addPassenger(Passenger passenger) {

        if (!passengers.contains(passenger) &&
                passengers.size() < totalSeats &&
                passenger != null) {

            passengers.add(passenger);
            return true;

        } else

            return false;

    }
    @Override
    public String toString() {
        return "Flight{" +
                "id= " + idFlight +
                ", flightNo='" + flightNo + '\'' +
                ", scheduledDeparture=" + scheduledDeparture +
                ", cityFrom=" + cityFrom +
                ", cityTo=" + cityTo +
                ", scheduledArrival=" + scheduledArrival +
                ", airline=" + airline +
                ", airplane=" + airplane +
                ", totalSeats=" + totalSeats +
                ", freeSeats=" + freeSeats +
                ", price=" + price +
                '}';
    }
    public boolean deletePassenger(Passenger passenger) {

        if (!passengers.contains(passenger)) return false;

        this.freeSeats++;
        passengers.remove(passenger);
        return true;

    }

    public boolean deletePassenger(int index) {
        if (index >= 0 && index < passengers.size()) {
            if (!passengers.contains(passengers.get(index))) return false;

            passengers.remove(passengers.get(index));
            return true;
        }
        return false;
    }
}
