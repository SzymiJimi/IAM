package com.inzynieria.insurance.commands.config;

import com.inzynieria.insurance.commands.*;
import com.inzynieria.insurance.repository.CommandRepository;
import com.inzynieria.insurance.repository.RoleRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class CommandsConfig {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommandsConfig.class);

    @Autowired
    CommandRepository commandRepository;

    @Autowired
    RoleRepository roleRepository;

    static List<CommandInterface> commandList = new ArrayList<>();

    public static void createCommands()
    {
        FindClient findClient = new FindClient(1, "Wyszukaj klienta");
        CreateContract createContract = new CreateContract(2, "Stwórz umowę");
        CreateNotification createNotification = new CreateNotification(5, "Zgłoszenie zdarzenia");
        ViewNotification viewNotification = new ViewNotification(6, "Przeglądaj zgłoszenia");
        CheckInsurance checkInsurance = new CheckInsurance(7, "Sprawdź czy ubezpieczony");
        commandList.add(findClient);
        commandList.add(createContract);
        commandList.add(createNotification);
        commandList.add(viewNotification);
        commandList.add(checkInsurance);
    }


    public static CommandInterface getCommandObject(Integer id)
    {
        LOGGER.info("Przekazane id:"+ id);
        if(commandList.isEmpty())
        {
            createCommands();
        }
        for (CommandInterface command :commandList) {
            System.out.println("Id danej komendy: "+command.getIdCommand());
           if(command.getIdCommand().equals(id))
           {
               return command;
           }
        }
        LOGGER.info("Nie znalazłem żadnej komendy");
//        CommandInterface command = commandList.get((id-1));
        return null;
    }




}
