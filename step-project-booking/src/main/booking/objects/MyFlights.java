package main.booking.objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MyFlights implements Comparable<MyFlights>, Serializable {

    String nameSurname;
    String fromIata;
    String toIata;
    String from;
    String to;
    LocalDate ldDepart, ldArrival;
    LocalTime ltDepart, ltArrival;

    double price;
    String numberBooking;
    String type;
    String numberFlight;
    String airline;
    int idFlight;

    public MyFlights() {
    }

    public String getAirline() {
        return airline;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public LocalDate getLdDepart() {
        return ldDepart;
    }

    public void setLdDepart(LocalDate ldDepart) {
        this.ldDepart = ldDepart;
    }

    public LocalDate getLdArrival() {
        return ldArrival;
    }

    public void setLdArrival(LocalDate ldArrival) {
        this.ldArrival = ldArrival;
    }

    public LocalTime getLtDepart() {
        return ltDepart;
    }

    public void setLtDepart(LocalTime ltDepart) {
        this.ltDepart = ltDepart;
    }

    public LocalTime getLtArrival() {
        return ltArrival;
    }

    public void setLtArrival(LocalTime ltArrival) {
        this.ltArrival = ltArrival;
    }

    public String getNumberFlight() {
        return numberFlight;
    }

    public void setNumberFlight(String numberFlight) {
        this.numberFlight = numberFlight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getFromIata() {
        return fromIata;
    }

    public void setFromIata(String fromIata) {
        this.fromIata = fromIata;
    }

    public String getToIata() {
        return toIata;
    }

    public void setToIata(String toIata) {
        this.toIata = toIata;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }




    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNumberBooking() {
        return numberBooking;
    }

    public void setNumberBooking(String numberBooking) {
        this.numberBooking = numberBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyFlights myFlights = (MyFlights) o;
        return Double.compare(myFlights.price, price) == 0 && idFlight == myFlights.idFlight && nameSurname.equals(myFlights.nameSurname) && fromIata.equals(myFlights.fromIata) && toIata.equals(myFlights.toIata) && from.equals(myFlights.from) && to.equals(myFlights.to) && ldDepart.equals(myFlights.ldDepart) && ldArrival.equals(myFlights.ldArrival) && ltDepart.equals(myFlights.ltDepart) && ltArrival.equals(myFlights.ltArrival) && numberBooking.equals(myFlights.numberBooking) && Objects.equals(type, myFlights.type) && numberFlight.equals(myFlights.numberFlight) && airline.equals(myFlights.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSurname, fromIata, toIata, from, to, ldDepart, ldArrival, ltDepart, ltArrival, price, numberBooking, type, numberFlight, airline, idFlight);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String f1 = formatter.format(ltDepart);
        String f2 = formatter.format(ltArrival);
        return
                /*    "       " + nameSurname + */
                "       " + type + " " + numberBooking + "  " + numberFlight +
                        "       " + fromIata +
                        " --> " + toIata +
               /* "       " + from + '\'' +
                "       " + to + '\'' +*/
                        "       " + ldDepart +
                        "/" + f1 +
                        " --> " + ldArrival +
                        "/" + f2 +
                        "       " + String.format("%.2f", price)+
                        "       " + airline
                ;
    }



    @Override
    public int compareTo(MyFlights o) {
        /*double price = o.getPrice();
        double pr2 = this.price;*/
        int cmpT = this.ldDepart.compareTo(o.ldDepart);
        if (cmpT ==0)
            cmpT = this.ltDepart.compareTo(o.ltDepart);
        if (cmpT == 0)
            cmpT = this.numberBooking.compareTo(o.numberBooking);

        return cmpT;
    }
}
