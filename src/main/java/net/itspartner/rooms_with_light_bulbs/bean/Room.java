package net.itspartner.rooms_with_light_bulbs.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Room.
 */
public class Room implements Serializable {

    private String roomsName;
    private String country;
    private boolean lightStatus;

    /**
     * Gets rooms name.
     *
     * @return the rooms name
     */
    public String getRoomsName() {
        return roomsName;
    }

    /**
     * Sets rooms name.
     *
     * @param roomsName the rooms name
     */
    public void setRoomsName(String roomsName) {
        this.roomsName = roomsName;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Is light status boolean.
     *
     * @return the boolean
     */
    public boolean isLightStatus() {
        return lightStatus;
    }

    /**
     * Sets light status.
     *
     * @param lightStatus the light status
     */
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
