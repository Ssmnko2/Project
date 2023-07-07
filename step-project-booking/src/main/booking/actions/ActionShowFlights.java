package main.booking.actions;


import main.booking.DAO.MenuDAO;
import main.booking.controllers.FlightsController;
import main.booking.objects.AppData;
import main.booking.objects.Flight;

import java.util.List;

import static main.booking.utils.ConfigFormats.FORMAT_FLIGHTS;
import static main.booking.utils.FormatDate.printNow;
import static main.booking.utils.FormatString.showTitleForFlights;

public class ActionShowFlights extends Action implements MenuDAO {

    public ActionShowFlights(AppData appData) {
        super(appData);
    }

    @Override
    public String getTitleAction() {
        return "1";
    }

    @Override
    public String getDesc() {
        return "Онлайн-табло";
    }

    @Override
    public void doAction() {
        appData.getFlight().printScoreboardOfFlights();
    }


}
