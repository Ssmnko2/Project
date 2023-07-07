package main.booking.utils;

import main.booking.directories.Airlines;
import main.booking.directories.Airplanes;
import main.booking.directories.Cities;
import main.booking.objects.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Generation {

    //Техничиский клас генерация рейсов
    public static void generation(List<Flight> flights, int count){
        Random random = new Random();
        LocalDate now = LocalDate.now();

        for (int i = 1; i <= count; i++) {
            int year = 2022;
            int month = random.nextInt(2) + 11;
            int randomPlusDay = random.nextInt(20);

            LocalDate newLocalDay = now.plusDays(randomPlusDay);

            month = newLocalDay.getMonthValue();
            int day = newLocalDay.getDayOfMonth();
            int hour = random.nextInt(24);
            int minute = random.nextInt(60);

            Cities cityFrom = Cities.KYIV;
            Cities cityTo = getRandomCity(random);

            int hourFlight = random.nextInt(3) + 1;
            int minutesFlight = random.nextInt(60);

            Airlines airline = getRandomAirline(random);
            String flightNo = "" + (random.nextInt(8888) + 1000);
            Airplanes airplane = getRandomAirplane(random);

            double price = random.nextDouble(6000) + 1500.0;

            Flight flight = new Flight(LocalDateTime.of(year, month, day, hour, minute),
                    cityFrom, cityTo, LocalTime.of(hourFlight, minutesFlight), airline, flightNo,
                    airplane, price);
            flights.add(flight);
        }
    }

    public static Cities getRandomCity(Random random){
        int pick = random.nextInt(Cities.values().length);
        return Cities.values()[pick];
    }

    public static Airplanes getRandomAirplane(Random random){
        int pick = random.nextInt(Airplanes.values().length);
        return Airplanes.values()[pick];
    }

    public static Airlines getRandomAirline(Random random){
        int pick = random.nextInt(Airlines.values().length);
        return Airlines.values()[pick];
    }

}
