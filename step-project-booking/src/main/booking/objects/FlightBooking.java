package main.booking.objects;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightBooking {
    public String from;
    public String to;
    public String iataFrom;
    public String iataTo;

    public String id;
    public LocalDate date;
    public LocalTime time;
    public int numberEconomClass;
    public int namberBusinessClass;
    public double ticketPriceEkomom;
    public double ticketpriceBusiness;

    public String getFrom() {
        return from;
    }

    public String getIataFrom() {
        return iataFrom;
    }

    public void setIataFrom(String iataFrom) {
        this.iataFrom = iataFrom;
    }

    public String getIataTo() {
        return iataTo;
    }

    public void setIataTo(String iataTo) {
        this.iataTo = iataTo;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getTicketPriceEkomom() {
        return ticketPriceEkomom;
    }

    public void setTicketPriceEkomom(double ticketPriceEkomom) {
        this.ticketPriceEkomom = ticketPriceEkomom;
    }

    public double getTicketpriceBusiness() {
        return ticketpriceBusiness;
    }

    public void setTicketpriceBusiness(double ticketpriceBusiness) {
        this.ticketpriceBusiness = ticketpriceBusiness;
    }

    public int getNumberEconomClass() {
        return numberEconomClass;
    }

    public void setNumberEconomClass(int numberEconomClass) {
        this.numberEconomClass = numberEconomClass;
    }

    public int getNamberBusinessClass() {
        return namberBusinessClass;
    }

    public void setNamberBusinessClass(int namberBusinessClass) {
        this.namberBusinessClass = namberBusinessClass;
    }

    @Override
    public String toString() {
        return  id +
                "     " + iataFrom + "-->" +
                iataTo +
                "     " + date +
                "     " + time +
                "     " + ticketPriceEkomom+
                "     " + numberEconomClass +
                "     " + ticketpriceBusiness+
                "     " + namberBusinessClass
                ;
    }

}
