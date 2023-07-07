package main.booking.collections;


import main.booking.objects.Flight;
import main.booking.DAO.DAOFlights;
import main.booking.utils.ConfigFiles;

import java.io.*;
import java.nio.file.Path;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static main.booking.utils.ConfigDate.TIME_ZONE;

public class CollectionFlightsDAO implements DAOFlights {
    private final List<Flight> flights;

    public CollectionFlightsDAO() {
        this.flights = new ArrayList<>();
    }

    @Override
    public List<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Flight getFlightById(int id) {
        return this.flights.get(id);
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return this.flights.remove(flight);
    }

    public void deleteFlight(int id) {
        this.flights.remove(id);
    }

    @Override
    public boolean saveFlight(Flight flight) {
        return this.flights.add(flight);
    }

    @Override
    public boolean updateFlight(Flight flight) {
        int index = this.flights.indexOf(flight);
        if (index != -1) {
            this.flights.set(index, flight);
            return true;
        } else {
            System.out.println("This flight is not found");
        }
        return false;
    }

    public void saveDB(String path) {

        Path path1 = Path.of(path);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path1.toFile()))) {
            objectOutputStream.writeInt(flights.size());

            for (int i = 0; i < flights.size(); i++) {
                objectOutputStream.writeObject(flights.get(i));
            }
        } catch (IOException e) {
            System.out.println("Database save error");
        }
    }

    public void readDB(String path) {
        Path path1 = Path.of(path);

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path1.toFile()))) {
            int count = objectInputStream.readInt();

            for (int i = 0; i < count; i++) {
                Flight flight = (Flight) objectInputStream.readObject();
                flights.add(flight);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Database load error");
        }

    }

}
