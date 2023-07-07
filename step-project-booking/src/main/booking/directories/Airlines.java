package main.booking.directories;

public enum Airlines {
    KHARKIV_AIRLINES("Kharkiv Airlines", "KT"),
    AZUR_AIR_UKRAINE("Azur Air Ukraine", "QU"),
    AEROSVIT_AIRLINES("AeroSvit Airlines", "VV"),
    WIZZAIR_UKRAINE("WizzAir Ukraine", "WU"),
    UKRAINE_INTERNATIONAL_AIRLINES("Ukraine International Airlines", "PS"),
    WINDROSE("WINDROSE", "7W"),
    TURKISH_AIRLINES("Turkish Airlines", "TK");

    private String name;
    private final String iataCode;

    Airlines(String name, String iataCode) {
        this.name = name;
        this.iataCode = iataCode;
    }

    public String getName() {
        return this.name;
    }

    public String getIataCode() {
        return this.iataCode;
    }

}
