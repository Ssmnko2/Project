package main.booking.logger;


import main.booking.utils.ConfigDate;
import main.booking.utils.FormatDate;

public class Logs implements ConfigDate {
    public static void log(String msg, String typeLog) {
        String logWithFile = "[" + FormatDate.printNowTimeForLogger() + "] " + typeLog.toUpperCase() + " - " + msg + "\n"; // для вывода в файл
        SaveLogs.writeLogs(logWithFile);
    }
}
