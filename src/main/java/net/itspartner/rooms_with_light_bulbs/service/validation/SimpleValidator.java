package net.itspartner.rooms_with_light_bulbs.service.validation;

import net.itspartner.rooms_with_light_bulbs.bean.Room;

public class SimpleValidator {


    public static boolean roomTitleIsCorrectSymbols(Room room) {
        boolean temp = true;
        if (room == null || room.getRoomsName().matches("\\W")) {
            temp = false;
        }
        return temp;
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
