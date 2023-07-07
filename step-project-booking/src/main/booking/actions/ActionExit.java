package main.booking.actions;


import main.booking.DAO.MenuDAO;
import main.booking.controllers.FlightsController;
import main.booking.objects.AppData;

import static main.booking.utils.ConfigFiles.BOOKING_FILE;
import static main.booking.utils.ConfigFiles.FLIGHT_FILE;

public class ActionExit  extends Action implements MenuDAO {
    public ActionExit(AppData appData) {
        super(appData);
    }

    @Override
    public String getTitleAction() {
        return "6";
    }

    @Override
    public String getDesc() {
        return "Выход из программы";
    }

    @Override
    public boolean exit() {
        return true;
    }

    @Override
    public void doAction() {
        appData.getFlight().saveDB(FLIGHT_FILE);
        appData.getBooking().saveDB(BOOKING_FILE);
    }
}
