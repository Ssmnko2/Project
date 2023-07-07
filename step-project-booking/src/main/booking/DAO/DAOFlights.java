package main.booking.DAO;


import main.booking.objects.Flight;

import java.util.List;

public interface DAOFlights {
    List<Flight> getAllFlights();
    Flight getFlightById(int id);
    boolean deleteFlight(Flight flight);
    void deleteFlight(int id);
    boolean saveFlight(Flight flight);
    boolean updateFlight(Flight flight);
    void saveDB(String path);
    void readDB(String path);
//    void loadToDB(List<Flight> list);

}
