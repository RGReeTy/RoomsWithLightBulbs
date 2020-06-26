package net.itspartner.rooms_with_light_bulbs.controller.command;

import net.itspartner.rooms_with_light_bulbs.controller.command.front.Command;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.CommandName;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.impl.AddNewRoomCommand;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.impl.ChangeLightStatusCommand;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.impl.GetAllRoomsCommand;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public final class CommandProvider {

    private static final Logger logger = Logger.getLogger(CommandProvider.class);


    private final static CommandProvider instance = new CommandProvider();

    private final Map<CommandName, Command> frontRepository = new HashMap<>();
    //private final Map<AjaxCommandName, AjaxCommand> ajaxRepository = new HashMap<>();

    private CommandProvider() {
        frontRepository.put(CommandName.ADD_NEW_ROOM, new AddNewRoomCommand());
        frontRepository.put(CommandName.CHANGE_LIGHT_STATUS, new ChangeLightStatusCommand());
        frontRepository.put(CommandName.GET_ALL_ROOMS, new GetAllRoomsCommand());


//        ajaxRepository.put(AjaxCommandName.EDIT_USER, new EditUser());
//        ajaxRepository.put(AjaxCommandName.SHOW_QUESTION, new ShowQuestion());
//        ajaxRepository.put(AjaxCommandName.SAVE_ANSWER, new SaveAnswer());
//        ajaxRepository.put(AjaxCommandName.GET_TESTS, new GetTests());
//        ajaxRepository.put(AjaxCommandName.ASSIGN_TEST, new AssignTest());
//        ajaxRepository.put(AjaxCommandName.GET_ASSIGNED_USERS, new GetAssignedUsers());
//        ajaxRepository.put(AjaxCommandName.DELETE_ASSIGNMENT, new DeleteAssignment());
//        ajaxRepository.put(AjaxCommandName.SHOW_RESULT_DATA, new ShowResultData());
//        ajaxRepository.put(AjaxCommandName.DELETE_TEST, new DeleteTest());
//        ajaxRepository.put(AjaxCommandName.CREATE_TEST, new CreateTest());
//        ajaxRepository.put(AjaxCommandName.CREATE_QUESTION_ANSWER, new CreateQuestionAnswer());
//        ajaxRepository.put(AjaxCommandName.UPDATE_QUESTION, new UpdateQuestion());
//        ajaxRepository.put(AjaxCommandName.COMPLETE_TEST, new CompleteTestCreation());
//        ajaxRepository.put(AjaxCommandName.DELETE_QUESTION, new DeleteQuestion());
//        ajaxRepository.put(AjaxCommandName.UPDATE_TEST_INFO, new UpdateTestInfo());


    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getFrontCommand(String name) {
        logger.info("getFrontCommand start. Name of action = " + name);

        CommandName commandName;
        //TODO delete null
        Command command = null;

        if (name == null) {
            // command = frontRepository.get(CommandName.WRONG_COMMAND);
        } else {

            commandName = CommandName.valueOf(name.toUpperCase());
            command = frontRepository.get(commandName);

//            if (command == null) {
//                command = frontRepository.get(CommandName.WRONG_COMMAND);
//            }
        }

        logger.info("getFrontCommand end  =  " + command.getClass());
        return command;
    }

//    public AjaxCommand getAjaxCommand(String name) {
//        AjaxCommandName ajaxCommandName;
//        AjaxCommand ajaxCommand;
//
//        if (name != null) {
//            ajaxCommandName = AjaxCommandName.valueOf(name.toUpperCase());
//            ajaxCommand = ajaxRepository.get(ajaxCommandName);
//
//            if (ajaxCommand == null) {
//                ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);
//            }
//        } else {
//            ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);
//
//        }
//        return ajaxCommand;
//    }
}
