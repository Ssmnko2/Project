package main.booking.actions;


import main.booking.DAO.MenuDAO;
import main.booking.objects.AppData;
import main.booking.objects.Flight;

import static main.booking.utils.FormatString.showMessageWithAnswer;

public class ActionShowFlightById extends Action implements MenuDAO {
    public ActionShowFlightById(AppData appData) {
        super(appData);
    }

    @Override
    public String getTitleAction() {
        return "2";
    }

    @Override
    public String getDesc() {
        return "Посмотреть информацию о рейсе";
    }

    @Override
    public void doAction() {
        String flightNumber = showMessageWithAnswer("" +
                "================================================================================================================\n" +
                "Введите номер рейса [пример: 1234]:").toUpperCase();
        System.out.println("================================================================================================================");

        Flight flight = this.appData.getFlight().getFlightById(Integer.parseInt(flightNumber));

        if (flight != null) {
            appData.getFlight().getFlightById(Integer.parseInt(flightNumber));

            System.out.println("┌────────────┬──────────────────────┬──────────────────────┬──────────────────┬─────────────────┐");
            appData.getFlight().displayFlightInfo(flight);
            System.out.println("└────────────┴──────────────────────┴──────────────────────┴──────────────────┴─────────────────┘");
        } else {
            System.out.println("Извините, но данного рейса нету в списке!");
            System.out.println("================================================================================================================");
        }
    }
}
