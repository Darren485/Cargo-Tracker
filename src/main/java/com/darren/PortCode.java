package com.darren;

public enum PortCode {
    // AFRICA PORT CODES
    LAG("Lagos, Nigeria"),
    CPT("Cape Town, South Africa"),
    TEM("Tema, Ghana"),
    ABI("Abidjan, Ivory Coast"),
    DUR("Durban, South Africa"),
    CTN("Port of Cape Town, South Africa"),
    PSD("Port Said, Egypt"),

    // EUROPE PORT CODES
    ROT("Rotterdam, Netherlands"),
    ANT("Antwerp, Belgium"),
    HAM("Hamburg, Germany"),
    VLC("Valencia, Spain"),
    GEN("Genoa, Italy"),
    LIV("Liverpool, United Kingdom"),

    // ASIA PORT CODES
    SHA("Shanghai, China"),
    SZN("Shenzhen, China"),
    SIN("Singapore"),
    PKL("Port Klang, Malaysia"),
    MUM("Mumbai, India"),
    BUS("Busan, South Korea"),

    // NORTH AMERICA PORT CODES
    NYC("New York, USA"),
    LGB("Long Beach, USA"),
    SAV("Savannah, USA"),
    HOU("Houston, USA"),
    LAX("Los Angeles, USA"),
    VAN("Vancouver, Canada"),

    // SOUTH AMERICA PORT CODES
    SAN("Santos, Brazil"),
    RIO("Rio de Janeiro, Brazil"),
    BUE("Buenos Aires, Argentina"),
    MON("Montevideo, Uruguay"),
    CAL("Callao, Peru"),
    VLP("Valparaiso, Chile");

    private final String description;

    // Constructor - THIS MUST BE DEFINED
    PortCode(String description) {
        this.description = description;
    }

    // Getter method
    public String getDescription() {
        return description;
    }


    public static void dispalayAllPortCodes() {
        System.out.println("\n=== AVAILABLE PORT CODES ===");
        for (PortCode portCode : PortCode.values()) {
            System.out.println(portCode.name() + ": " + portCode.getDescription());
        }
    }


    public static boolean isValidPort(String code) {
        try {
            PortCode.valueOf(code.toUpperCase());
            return true;
       } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Override toString() to return code: description format
    @Override
    public String toString() {
        return name() + ": " + description;
    }
}