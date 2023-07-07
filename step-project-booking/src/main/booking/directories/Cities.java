package main.booking.directories;

public enum Cities {
    DONETSK("Donetsk"),
    LVIV("Lviv"),
    KHARKIV("Kharkiv"),
    KYIV("Kyiv"),
    DNIPRO("Dnipro"),
    ZAPORIZHZHIA("Zaporizhzhia"),
    LUHANSK("Luhansk"),
    ODESA("Odesa"),
    SUMY("Sumy"),
    POLTAVA("Poltava"),
    MYKOLAIV("Mykolaiv"),
    VINNYTSIA("Vinnytsia"),
    SIMFEROPOL("Simferopol");

    private String city;

    Cities(String city) {
        this.city = city;
    }

    public String getCity(){
        return city;
    }

    public boolean equalsIgnoreCase(String destination) {
        return true;
    }
}
