package main.booking.objects;


import main.booking.controllers.BookingController;
import main.booking.controllers.FlightsController;

public class AppData {
    private final BookingController booking = new BookingController();
    private final FlightsController flight = new FlightsController();

    public BookingController getBooking() {
        return this.booking;
    }

    public FlightsController getFlight() {
        return this.flight;
    }

}
