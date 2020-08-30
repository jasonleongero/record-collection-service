package org.jasongero.recordcollectionservice.entity;

public class Country {
    private String countryIsoCode;
    private String name;
    
    public Country(String countryIsoCode, String name) {
        this.countryIsoCode = countryIsoCode;
        this.name = name;
    }
    
    public String getCountryIsoCode() {
        return countryIsoCode;
    }
    
    public String getName() {
        return name;
    }
}
