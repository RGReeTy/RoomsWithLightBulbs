package net.itspartner.rooms_with_light_bulbs.controller.command;

import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.AjaxCommand;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.AjaxCommandName;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl.ChangeLightStatusCommand;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl.GetAllCountries;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl.GetAllRooms;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl.GetRoomInfoCommand;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.Command;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.CommandName;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.EmptyCommand;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.impl.AddNewRoomCommand;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * The type Command provider.
 */
public final class CommandProvider {

    private static final Logger logger = Logger.getLogger(CommandProvider.class);


    private final static CommandProvider instance = new CommandProvider();

    private final Map<CommandName, Command> frontRepository = new HashMap<>();
    private final Map<AjaxCommandName, AjaxCommand> ajaxRepository = new HashMap<>();

    private CommandProvider() {
        frontRepository.put(CommandName.ADD_NEW_ROOM, new AddNewRoomCommand());
        frontRepository.put(CommandName.WRONG_COMMAND, new EmptyCommand());


        ajaxRepository.put(AjaxCommandName.ALL_COUNTRIES, new GetAllCountries());
        ajaxRepository.put(AjaxCommandName.ALL_ROOMS, new GetAllRooms());
        ajaxRepository.put(AjaxCommandName.CHANGE_LIGHT_STATUS, new ChangeLightStatusCommand());
        ajaxRepository.put(AjaxCommandName.UPDATE_ROOM, new GetRoomInfoCommand());


    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets front command.
     *
     * @param name the name
     * @return the front command
     */
    public Command getFrontCommand(String name) {
        logger.info("getFrontCommand start. Name of action = " + name);

        CommandName commandName;
        //TODO delete null
        Command command = null;

        if (name == null) {
            command = frontRepository.get(CommandName.WRONG_COMMAND);
        } else {

            commandName = CommandName.valueOf(name.toUpperCase());
            command = frontRepository.get(commandName);

            if (command == null) {
                command = frontRepository.get(CommandName.WRONG_COMMAND);
            }
        }

        logger.info("getFrontCommand end  =  " + command.getClass());
        return command;
    }

    /**
     * Gets ajax command.
     *
     * @param name the name
     * @return the ajax command
     */
    public AjaxCommand getAjaxCommand(String name) {
        AjaxCommandName ajaxCommandName;
        AjaxCommand ajaxCommand;

        if (name != null) {
            ajaxCommandName = AjaxCommandName.valueOf(name.toUpperCase());
            ajaxCommand = ajaxRepository.get(ajaxCommandName);

            if (ajaxCommand == null) {
                //TODO add no_command
                ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);
            }
        } else {
            ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);

        }
        return ajaxCommand;
    }
}
