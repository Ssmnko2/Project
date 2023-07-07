package main.booking.objects;

import java.util.ArrayList;

public class BookingArr {
    ArrayList<Passenger> arrBookingPassenger = new ArrayList<>();
    double sumBooking;
    String numberBooking;
    int numberPassengerEconom;
    int numberPassengerBusiness;

    public ArrayList<Passenger> getArrBookingPassenger() {
        return arrBookingPassenger;
    }

    public void setArrBookingPassenger(ArrayList<Passenger> arrBookingPassenger) {
        this.arrBookingPassenger = arrBookingPassenger;
    }

    public double getSumBooking() {
        return sumBooking;
    }

    public void setSumBooking(double sumBooking) {
        this.sumBooking = sumBooking;
    }

    public String getNumberBooking() {
        return numberBooking;
    }

    public void setNumberBooking(String numberBooking) {
        this.numberBooking = numberBooking;
    }

    public int getNumberPassengerEconom() {
        return numberPassengerEconom;
    }

    public void setNumberPassengerEconom(int numberPassengerEconom) {
        this.numberPassengerEconom = numberPassengerEconom;
    }

    public int getNumberPassengerBusiness() {
        return numberPassengerBusiness;
    }

    public void setNumberPassengerBusiness(int numberPassengerBusiness) {
        this.numberPassengerBusiness = numberPassengerBusiness;
    }

}
