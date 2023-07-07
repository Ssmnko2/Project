package main.booking.actions;


import main.booking.DAO.MenuDAO;
import main.booking.objects.AppData;
import main.booking.objects.Booking;

import java.util.List;

import static main.booking.utils.ConfigFormats.FORMAT_BOOKING;
import static main.booking.utils.FormatString.showMessageWithAnswer;

public class ActionShowMyFlights extends Action implements MenuDAO {
    public ActionShowMyFlights(AppData appData) {
        super(appData);
    }

    @Override
    public String getTitleAction() {
        return "5";
    }

    @Override
    public String getDesc() {
        return "Мои рейсы";
    }


    @Override
    public void doAction() {
        String firstname = showMessageWithAnswer(
                "Введите имя:",
                "^[A-Z][A-Za-z]+",
                "Вы не корректно ввели имя!");

        String lastname = showMessageWithAnswer(
                "Введите фамилию:",
                "^[A-Z][A-Za-z]+",
                "Вы не корректно ввели фамилию!");

        List<Booking> bookings = appData.getBooking().getAllBookingsByFullName(firstname, lastname);
        if (bookings.size() > 0 ) {

            appData.getBooking().printAllBookings(bookings, FORMAT_BOOKING);
            System.out.println();
        } else {
            System.out.printf("По данным пользователя '%s %s' бронировки не найдено!\n", firstname, lastname);
        }

    }


}
