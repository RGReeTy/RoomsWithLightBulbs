package net.itspartner.rooms_with_light_bulbs.bean;

import java.io.Serializable;
import java.util.Objects;

public class Country implements Serializable {

    private int idCountry;
    private String countrysName;



    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getCountrysName() {
        return countrysName;
    }

    public void setCountrysName(String countrysName) {
        this.countrysName = countrysName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return idCountry == country.idCountry &&
                Objects.equals(countrysName, country.countrysName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCountry, countrysName);
    }

    @Override
    public String toString() {
        return "Country{" +
                "idCountry=" + idCountry +
                ", countrysName='" + countrysName + '\'' +
                '}';
    }
}
