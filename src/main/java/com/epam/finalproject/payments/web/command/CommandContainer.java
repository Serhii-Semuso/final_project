package com.epam.finalproject.payments.web.command;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private final static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("viewRegister", new ViewRegisterCommand());
        commands.put("viewAccounts", new ViewAccountsCommand());
        commands.put("noCommand", new NoCommand());
    }

    public static Command get(String commandName){
        if(commandName == null || !commands.containsKey(commandName)){
            log.trace("Command not found ->" + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}
