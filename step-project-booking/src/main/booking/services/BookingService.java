package main.booking.services;

import main.booking.DAO.BookingDAO;
import main.booking.collections.CollectionBookingDAO;
import main.booking.logger.Logs;
import main.booking.objects.Booking;
import main.booking.objects.MyFlights;
import main.booking.objects.SurnameName;
import main.booking.utils.MyLibrary;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingService implements BookingDAO {
    private BookingDAO bookingDao = new CollectionBookingDAO();
    public BookingDAO getBookingDao() {
        return bookingDao;
    }
    public List<Booking> getAllBookings() {
        Logs.log("get all booking", "info");
        return bookingDao.getAllBookings();
    }

    @Override
    public Booking getBookingByIndex(int index) {
        return null;
    }

    public void displayAllBookings() {
        bookingDao.getAllBookings().forEach(System.out::println);
    }
    public void saveBooking(Booking booking) {
        Logs.log("save booking", "info");
        bookingDao.saveBooking(booking);
    }

    @Override
    public boolean deleteBooking(int index) {
        return false;
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        return false;
    }

    public void saveDB(String filePath) {
        Logs.log("save DB", "info");
        bookingDao.saveDB(filePath);
    }
    public void readDB(String filePath) {
        Logs.log("read DB", "info");
        bookingDao.readDB(filePath);
    }

    @Override
    public void loadToDB(List<Booking> bookingList) {

    }

    @Override
    public Map<String, ArrayList> readFile(String fileName) {
        return null;
    }

    @Override
    public void writeFile(String filename, Map<String, ArrayList> map1) {

    }

    public void deleteBookingByIndex(int index) {
        Logs.log("delete booking by index", "info");
        bookingDao.deleteBooking(index);
    }
    public void deleteBookingByObject(Booking booking) {
        Logs.log("delete booking by object", "info");
        bookingDao.deleteBooking(booking);
    }

    public int count() {
        Logs.log("get count booking", "info");
        return bookingDao.getAllBookings().size();
    }
    public Booking getBookingById(int index) {
        Logs.log("get booking by id", "info");
        if (index >= 0 && index < bookingDao.getAllBookings().size()) {
            return bookingDao.getAllBookings().get(index);
        }else {
            return null;
        }
    }

    Scanner scan = new Scanner(System.in);
    //    ArrayList<Flight> arrayFlight = new ArrayList<>();
//    ArrayList<Booking> arrBooking = new ArrayList<>();
    ArrayList<MyFlights> arrMyFlights = new ArrayList<>();
    Map<String, ArrayList> map = new HashMap<>();





    public void myFlights() {

//        map = createDataBaseMyFlight();

        map = readFile("myFlights.bin");
        int dataPassenger = 1;
        while (dataPassenger == 1) {
            System.out.print("Введите фамилию и имя в формате <Фамилия Имя>  : ");
            SurnameName nm = inputSurnameName();
            inputInfoMyFlights(nm.getSurnameName());
            System.out.println();
            System.out.print("Посмотреть данные по другому пассажиру?  (Да/Нет): ");
            dataPassenger = MyLibrary.YesNo();
        }


        writeFile("myFlights.bin", map);

    }

    public SurnameName inputSurnameName() {

        SurnameName nm = new SurnameName();
        nm = MyLibrary.inputString();
        return nm;
    }

    public void inputInfoMyFlights(String s) {
        System.out.println();
        if (map.containsKey(s)) {
            ArrayList<MyFlights> arrMF = new ArrayList<>();
            arrMF = map.get(s);

            System.out.println("Все полеты и бронирования, зарегистрированные на пассажира  " + s);
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%11s %19s %19s %27s %27s %19s %n", "тип", "рейс", "направление", "время", "цена", "авиакомпания");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
            SortedSet<String> nameSurname = new TreeSet();
            SortedSet<MyFlights> ssMF = new TreeSet<>();
            for (MyFlights mf : arrMF) {
                ssMF.add(mf);
                nameSurname.add(mf.getFrom() + "-->" + mf.getTo());
            }
            /*for (MyFlights mf : arrMF)
                nameSurname.add(mf.getFrom() + "-->" + mf.getTo());*/
            for (String s1 : nameSurname) {
                System.out.println(s1);
                for (MyFlights mf : ssMF)
                    if (s1.equals(mf.getFrom() + "-->" + mf.getTo())) {

                        System.out.println(mf);
                    }
            }
        } else
            System.out.println("Пассажир --> " + s + " не зарегистрирован в нашей базе данных");
    }

    public boolean createBookingNew(int id, String flightNo, LocalDateTime scheduledDeparture, String cityFrom, String cityTo, LocalDateTime scheduledArrival, String airline, int countSeats, double price) {
        System.out.println();
        System.out.println(" -------------------- БРОНИРОВАНИЕ --------------------");
        System.out.println();

        ArrayList<MyFlights> arrayMF = new ArrayList<>();
        SurnameName sn;
        String fromIata = createCodeFromIata(cityFrom);
        String toIata = createCodeToIata(cityTo);


        for (int i = 1; i <= countSeats; i++) {
            MyFlights mf = new MyFlights();
            mf.setIdFlight(id);
            mf.setNumberFlight(flightNo);
            mf.setLdDepart(scheduledDeparture.toLocalDate());
            mf.setLtDepart(scheduledDeparture.toLocalTime());
            mf.setLdArrival(scheduledArrival.toLocalDate());
            mf.setLtArrival(scheduledArrival.toLocalTime());
            mf.setFrom(cityFrom);
            mf.setTo(cityTo);
            mf.setFromIata(fromIata);
            mf.setToIata(toIata);
            mf.setAirline(airline);
            mf.setPrice(price);
            mf.setType("бронь");
            mf.setNumberBooking("          ");
            System.out.print("Пассажир " + i + ": введите фамилию и имя в формате <Фамилия Имя> : ");
            mf.setNameSurname(inputSurnameName().getSurnameName());
            arrayMF.add(mf);
        }

        System.out.println();
        int i = 1;
        while (i > 0) {
            System.out.println();
            System.out.println(" Вы бронируете билеты для следующих пассажиров:");
            int j = 1;
            for (MyFlights mf1 : arrayMF) {
                System.out.println(" " + j + " " + mf1.getNameSurname());
                j++;
            }
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" Для редактирования фамилии и имени пассажира введите соответсвующий номер пассажира");
            System.out.println(" Для продолжения бронирования введите 0 ");
            System.out.print(" Введите значение : ");


            for (; ; ) {
                while (!scan.hasNextInt()) {
                    System.out.print("Ошибка ввода, повторите еще раз! : ");
                    scan.next();
                }
                i = scan.nextInt();
                if (i >= 1 && i <= countSeats) {
                    System.out.println();
                    System.out.println(" Старое фамилия и имя пассажира " + i + " : " + arrayMF.get(i - 1).getNameSurname());
                    System.out.print(" Введите новые фамилию и имя пассажира " + i + " в формате <Фамилия Имя> : ");
                    arrayMF.get(i - 1).setNameSurname(inputSurnameName().getSurnameName());
                    break;
                } else if (i == 0)
                    break;
                else
                    System.out.print("Ошибка ввода, повторите еще раз! : ");
            }
        }
        System.out.println();
        System.out.println("Ваша бронь:");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%11s %19s %19s %27s %27s %19s %n", "тип", "рейс", "направление", "время", "цена", "авиакомпания");
        System.out.println(arrayMF.get(0));
        System.out.println();

        for (MyFlights mf1 : arrayMF) {
            System.out.printf("%50s  %-40s %9s %n", "пассажир ", mf1.getNameSurname(), String.format("%.2f", mf1.getPrice()));
        }
        System.out.printf("%102s %n", "-------------------------------------------------------------");
        System.out.printf("%93s %8s %n", "Всего к оплате :", String.format("%.2f", price * countSeats));
        System.out.print("Подтверждаете данное бронирование? (Да/Нет)");
        if (MyLibrary.YesNo() == 1) {
            MyFlights mf = new MyFlights();
            mf.setIdFlight(id);
            mf.setNumberFlight(flightNo);
            mf.setLdDepart(scheduledDeparture.toLocalDate());
            mf.setLtDepart(scheduledDeparture.toLocalTime());
            mf.setLdArrival(scheduledArrival.toLocalDate());
            mf.setLtArrival(scheduledArrival.toLocalTime());
            mf.setFrom(cityFrom);
            mf.setTo(cityTo);
            mf.setFromIata(fromIata);
            mf.setToIata(toIata);
            mf.setAirline(airline);
            mf.setPrice(price);
            mf.setType("бронь");

            System.out.print("Введите ваши фамилию и имя для регистрации брони в формате <Фамилия Имя> : ");
            mf.setNameSurname(inputSurnameName().getSurnameName());


            LocalDateTime l = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyddMMHHmm");
            String s = formatter.format(l);

            mf.setNumberBooking(s);


            mf.setPrice(price * countSeats);

            arrayMF.add(0, mf);
            System.out.println();

            System.out.println(" Создана бронь на имя " + mf.getNameSurname() + " . Номер брони : " + mf.getNumberBooking());
            map = readFile("myFlights.bin");
            for (MyFlights mf1 : arrayMF)
                if (map.containsKey(mf1.getNameSurname()))
                    map.get(mf1.getNameSurname()).add(mf1);
                else {
                    ArrayList<MyFlights> newArrMF = new ArrayList();
                    newArrMF.add(mf1);
                    map.put(mf1.getNameSurname(), newArrMF);
                }
            writeFile("myFlights.bin", map);
            Map<String, ArrayList> mapBooking = new HashMap<>();
            mapBooking = readFile("booking.bin");
            mapBooking.put(mf.getNumberBooking(), arrayMF);
            writeFile("booking.bin", mapBooking);



            /*  map.put(mf.getNameSurname(), );*/


            return true;
        } else
            return false;
    }

    public Map<String, ArrayList> createDataBaseMyFlight() {

        ArrayList<String> arrName = new ArrayList<>();
        arrName.add("СМИРНОВ НИКОЛАЙ");
        arrName.add("АНИСИМОВ ВЛАДИМИР");
        arrName.add("ПУГАЧЕВА АЛЛА");
        arrName.add("ДУДКИНА ИРИНА");
        arrName.add("ШАПОВАЛ АНДРЕЙ");
        arrName.add("СЕМЕНКО СЕРГЕЙ");
        arrName.add("НИМЧЕНКО ЕЛЕНА");
        arrName.add("ПУПКИН МИХАИЛ");
        arrName.add("КРЮЧКОВ ИГОРЬ");
        arrName.add("РЕЙЗИНА ЗОЯ");

        ArrayList<String> arrNamePortTo = new ArrayList<>();
        arrNamePortTo.add("Dnipro");
        arrNamePortTo.add("Donetsk");
        arrNamePortTo.add("Lviv");
        arrNamePortTo.add("Odesa");
        arrNamePortTo.add("Simferopol");
        arrNamePortTo.add("Kharkiv");
        arrNamePortTo.add("Zaporizhzhia");
        arrNamePortTo.add("Luhansk");
        arrNamePortTo.add("Sumy");
        arrNamePortTo.add("Poltava");
        arrNamePortTo.add("Mykolaiv");
        arrNamePortTo.add("Vinnytsia");


        ArrayList<String> arrIATA = new ArrayList<>();
        arrIATA.add("DNK");
        arrIATA.add("DOK");
        arrIATA.add("LWO");
        arrIATA.add("ODS");
        arrIATA.add("SIP");
        arrIATA.add("HRK");
        arrIATA.add("OZH");
        arrIATA.add("VSG");
        arrIATA.add("UMY");
        arrIATA.add("PLV");
        arrIATA.add("NLV");
        arrIATA.add("VIN");

        ArrayList<String> arrAirLines = new ArrayList<>();
        arrAirLines.add("Kharkiv Airlines");
        arrAirLines.add("Azur Air Ukraine");
        arrAirLines.add("AeroSvit Airlines");
        arrAirLines.add("WizzAir Ukraine");
        arrAirLines.add("Ukraine International Airlines");
        arrAirLines.add("WINDROSE");
        arrAirLines.add("Turkish Airlines");


        for (int j = 0; j <= 9; j++) {

            ArrayList<MyFlights> arrMyFlights = new ArrayList<>();
            for (int i = 0; i <= (int) (Math.random() * 100); i++) {
                MyFlights myFlights = new MyFlights();
                Random random = new Random();


                myFlights.setFrom("Boryspil");
                myFlights.setFromIata("KBP");


                int r = random.nextInt(12);

                myFlights.setTo(arrNamePortTo.get(r));
                myFlights.setToIata(arrIATA.get(r));

                myFlights.setNumberFlight("ABBA" + (random.nextInt(8999) + 1000));

                myFlights.setAirline(arrAirLines.get(random.nextInt(6)));


                int year = (int) (Math.random() * 12 + 2010);
                int month = (int) (Math.random() * 11 + 1);
                int day = (int) (Math.random() * 27 + 1);

                myFlights.setLdDepart(LocalDate.of(year, month, day));

                int hour = (int) (Math.random() * 23);
                int min = (int) (Math.random() * 59);
                myFlights.setLtDepart(LocalTime.of(hour, min));
                LocalDateTime depart = LocalDateTime.of(year, month, day, hour, min);
                LocalDateTime arrival = depart.plusMinutes(random.nextInt(100) + 100);
                myFlights.setLdArrival(arrival.toLocalDate());
                myFlights.setLtArrival(arrival.toLocalTime());
                myFlights.setNameSurname(arrName.get(j));

                myFlights.setPrice(Math.random() * 100 + 100);
                myFlights.setType("полет");
                myFlights.setNumberBooking("          ");
                arrMyFlights.add(myFlights);

            }
            System.out.println(arrName.get(j));
            for (MyFlights mf : arrMyFlights)
                System.out.println(mf);

            map.put(arrName.get(j), arrMyFlights);
        }

        return map;
    }

    public String createCodeFromIata(String cityFrom) {
        return "KBP";
    }

    public String createCodeToIata(String cityTo) {
        switch (cityTo) {
            case ("Dnipro"):
                return "DNK";
            case ("Donetsk"):
                return "DOK";
            case ("Lviv"):
                return "LWO";
            case ("Odesa"):
                return "ODS";
            case ("Simferopol"):
                return "SIP";
            case ("Kharkiv"):
                return "HRK";
            case ("Zaporizhzhia"):
                return "OZH";
            case ("Luhansk"):
                return "VSG";
            case ("Sumy"):
                return "UMY";
            case ("Poltava"):
                return "PLV";
            case ("Mykolaiv"):
                return "NLV";
            case ("Vinnytsia"):
                return "VIN";
            default:
                return "   ";
        }
    }

    public int[] delBooking() {
        int[] arrDelBooking = new int[2];
        arrDelBooking[0] = 0;
        arrDelBooking[1] = 0;

        Map<String, ArrayList> mapBooking;
        ArrayList<MyFlights> arrBooking = new ArrayList<>();
        mapBooking = readFile("booking.bin");
        for (; ; ) {
            System.out.print("Введите номер брони : ");
            String numberBooking = MyLibrary.inputNumberBooking();
            if (mapBooking.containsKey(numberBooking)) {
                map = readFile("myFlights.bin");
                arrBooking = mapBooking.get(numberBooking);
                if (arrBooking.get(0).getType().equals("отмена"))
                    System.out.println("Бронь с данным номером отменена и не является активной");

                else {
                    if (LocalDateTime.now().isAfter(LocalDateTime.of(arrBooking.get(0).getLdDepart(), arrBooking.get(0).getLtDepart()).minusMinutes(40)))
                        System.out.println("Отмена брони не возможна, так как закончена регистрация или рейс уже состоялся");

                    else {
                        System.out.println();
                        System.out.println("Ваша бронь: " + numberBooking + " . Бронь создана : " + arrBooking.get(0).getNameSurname());
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%11s %19s %19s %27s %27s %19s %n", "тип", "рейс", "направление", "время", "цена", "авиакомпания");
                        System.out.println(arrBooking.get(0));
                        System.out.println();
                        int i = 0;
                        for (MyFlights mf1 : arrBooking) {
                            if (i > 0)
                                System.out.printf("%50s  %-40s %9s %n", "пассажир ", mf1.getNameSurname(), String.format("%.2f", mf1.getPrice()));
                            i++;
                        }
                        System.out.printf("%102s %n", "-------------------------------------------------------------");
                        System.out.printf("%93s %8s %n", "Всего к оплате :", String.format("%.2f", arrBooking.get(0).getPrice()));

                        System.out.println("При удалении бронь будет не активной. Доступ к данным брони станет недоступен!");
                        System.out.print("Удалить бронь? (Да/Нет) : ");

                        if (MyLibrary.YesNo() == 1)
                            for (MyFlights mf : arrBooking) {
                                ArrayList<MyFlights> arrMF = map.get(mf.getNameSurname());
                                for (MyFlights mf1 : arrMF)
                                    if (mf.getIdFlight() == mf1.getIdFlight()) {
                                        mf1.setType("отмена");
                                        mf.setType("отмена");
                                    }
                            }

                        arrDelBooking[0] = arrBooking.get(0).getIdFlight();
                        arrDelBooking[1] = arrBooking.size() - 1;


                        writeFile("myFlights.bin", map);
                        writeFile("booking.bin", mapBooking);
                        break;
                    }
                }
            } else {
                System.out.println("Бронирование с таким номером не загегистрировано в базе данных");
                arrDelBooking[0] = 0;
                arrDelBooking[1] = 0;
            }
            System.out.print("Повторить ввод ? (Да/Нет) :");
            if (MyLibrary.YesNo() == 0)
                break;
        }
        return arrDelBooking;

    }

}
