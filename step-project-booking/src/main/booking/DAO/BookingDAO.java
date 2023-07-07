package main.booking.DAO;

import main.booking.objects.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BookingDAO {
    List<Booking> getAllBookings();
    Booking getBookingByIndex(int index);
    void saveBooking(Booking booking);
    boolean deleteBooking(int index);
    boolean deleteBooking(Booking booking);
    void saveDB(String filePath);
    void readDB(String filePath);
    void loadToDB(List<Booking> bookingList);
    public Map<String, ArrayList> readFile(String fileName);
    public void writeFile(String filename, Map<String, ArrayList> map1);

}
