package main.booking.collections;

import main.booking.DAO.BookingDAO;
import main.booking.objects.Booking;
import main.booking.objects.FlightBooking;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CollectionBookingDAO implements BookingDAO {
    private List<Booking> bookingsList;
    public ArrayList<FlightBooking> arrFlights = new ArrayList<FlightBooking>();
    public String namePort;
    public ArrayList aircraftFlights() {

        Random random = new Random();
        ArrayList<String> arrNamePortTo = new ArrayList<>();
        arrNamePortTo.add("Dnepropetrovsk Airport");
        arrNamePortTo.add("Donetsk Airport");
        arrNamePortTo.add("Lviv Airport");
        arrNamePortTo.add("Odessa International Airport");
        arrNamePortTo.add("Simferopol Airport");
        arrNamePortTo.add("Kharkiv Airport");

        ArrayList<String> arrIATA = new ArrayList<>();
        arrIATA.add("DNK");
        arrIATA.add("DOK");
        arrIATA.add("LWO");
        arrIATA.add("ODS");
        arrIATA.add("SIP");
        arrIATA.add("HRK");


        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(0, 0);

        for (int i = 0; i < 100; i++) {
            FlightBooking flight = new FlightBooking();

            flight.setFrom("Boryspil International Airport");
            flight.setIataFrom("KBP");

            int r = random.nextInt(6);
            flight.setTo(arrNamePortTo.get(r));
            flight.setIataTo(arrIATA.get(r));

            flight.setDate(date);
            flight.setTime(time.plusMinutes(14 * i));

            flight.setNamberBusinessClass(random.nextInt(20));
            flight.setNumberEconomClass(random.nextInt(160));

            flight.setTicketPriceEkomom(100 + Math.random()*100);
            flight.setTicketpriceBusiness(200 + Math.random()*200);

            flight.setId("ABBA" + (1000 + random.nextInt(1000)));


            arrFlights.add(flight);
            /*System.out.print(i + 1 + "  ");
            System.out.println(arrFlights.get(i));*/
        }

        return arrFlights;


    }




    public CollectionBookingDAO() {

        bookingsList = new ArrayList();

    }

    @Override
    public List<Booking> getAllBookings() {

        return bookingsList;

    }

    @Override
    public Booking getBookingByIndex(int index) {
        Booking result = null;
        if (index >= 0 && index < bookingsList.size()){
            result = bookingsList.get(index);
        }

        return result;
    }

    @Override
    public void saveBooking(Booking booking) {

        if (booking != null) {
            if (bookingsList.contains(booking)) {
                bookingsList.set(bookingsList.indexOf(booking), booking);
            } else {
                bookingsList.add(booking);
            }
        }

    }

    @Override
    public boolean deleteBooking(int index) {
        boolean result = false;
        if (index >= 0 && index < bookingsList.size()) {
            bookingsList.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        return bookingsList.remove(booking);
    }

    @Override
    public void saveDB(String path) {

        try {
            File af = new File(path);
            FileOutputStream fileOutput = new FileOutputStream(af.getAbsoluteFile());
            ObjectOutputStream streamOutput = new ObjectOutputStream(fileOutput);

            streamOutput.writeObject(bookingsList);
            streamOutput.close();
            fileOutput.close();

        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    @Override
    public void readDB(String path) {

        List<Booking> listBooking = null;

        File file = new File(path);
        FileInputStream fi = null;
        ObjectInputStream oi = null;

        try {

            fi = new FileInputStream(file.getAbsoluteFile());
            oi = new ObjectInputStream(fi);

            listBooking = (List<Booking>) oi.readObject();


            oi.close();
            fi.close();
            loadToDB(listBooking);

        } catch (ClassNotFoundException | IOException e) {
            e.getStackTrace();
        }


    }

    @Override
    public void loadToDB(List<Booking> bookingsList) {
        if (bookingsList != null){
            bookingsList.forEach(this::saveBooking);
        }
    }

    @Override
    public Map<String, ArrayList> readFile(String fileName) {
        return null;
    }

    @Override
    public void writeFile(String filename, Map<String, ArrayList> map1) {

    }


}
