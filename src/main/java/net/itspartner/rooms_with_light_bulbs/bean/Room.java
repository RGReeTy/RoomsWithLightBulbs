package net.itspartner.rooms_with_light_bulbs.bean;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

    private String roomsName;
    private Country country;
    private boolean lightStatus;

    public String getRoomsName() {
        return roomsName;
    }

    public void setRoomsName(String roomsName) {
        this.roomsName = roomsName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(boolean lightStatus) {
        this.lightStatus = lightStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return lightStatus == room.lightStatus &&
                Objects.equals(roomsName, room.roomsName) &&
                Objects.equals(country, room.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomsName, country, lightStatus);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomsName='" + roomsName + '\'' +
                ", country=" + country +
                ", lightStatus=" + lightStatus +
                '}';
    }
}
