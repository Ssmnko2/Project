package main.booking;


import main.booking.logger.Logs;
import main.booking.objects.BookingApp;

public class App {
    public static void app() {
        BookingApp app = new BookingApp();

        Logs.log("Set test flights", "info");

        Logs.log("App is run", "info");
        app.start();

        Logs.log("Start app", "info");
    }

}

