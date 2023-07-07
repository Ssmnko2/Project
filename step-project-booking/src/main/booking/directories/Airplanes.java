package main.booking.directories;

public enum Airplanes {
    AIRBUS_A320("Airbus A320", 140),
    BOEING_737("Boeing 737", 114),
    BOEING_777("Boeing 777", 235),
    EMBRAER_190("Embraer 190", 104),
    ATR_42("ATR 42", 42),
    ATR_72("ATR 72", 64),
    YAK_40("Yak-40", 32),
    AN_140("Аn-140", 48);

    private String airplane;
    private int numberOfSeats;

    Airplanes(String airplane, int numberOfSeats){
        this.airplane = airplane;
        this.numberOfSeats = numberOfSeats;
    }

    public String getAirplane(){
        return airplane;
    }
    public int getNumberOfSeats(){
        return numberOfSeats;
    }
}
