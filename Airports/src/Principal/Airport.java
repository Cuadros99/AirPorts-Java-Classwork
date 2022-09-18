package Principal;

public class Airport {

// ATTRIBUTES
    String code, name, city, state;
    Double latit, longit;

// METHODS
    // Constructor
    public Airport(String code, String name, String city, String state, Double latit, Double longit) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.state = state;
        this.latit = latit;
        this.longit = longit;
    }
    
    // Get the direct distance between two airports
    public Double getDirectDistanceBetween(Airport apFinal) {
        double lon1 = Math.toRadians(longit);
        double lon2 = Math.toRadians(apFinal.getLongit());
        double lat1 = Math.toRadians(latit);
        double lat2 = Math.toRadians(apFinal.getLatit());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        return(c * r);
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public Double getLatit() { return latit; }
    public Double getLongit() { return longit; }
}
