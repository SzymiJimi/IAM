package com.inzynieria.insurance.commands.config;

import com.inzynieria.insurance.commands.CommandInterface;
import com.inzynieria.insurance.commands.CreateContract;
import com.inzynieria.insurance.commands.FindApplications;
import com.inzynieria.insurance.commands.FindClient;
import com.inzynieria.insurance.repository.CommandRepository;
import com.inzynieria.insurance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class CommandsConfig {

    @Autowired
    CommandRepository commandRepository;

    @Autowired
    RoleRepository roleRepository;

    static List<CommandInterface> commandList = new ArrayList<>();

    public static void createCommands()
    {
        FindClient findClient = new FindClient(1, "Wyszukaj klienta");
        CreateContract createContract = new CreateContract(2, "Stwórz umowę");
        FindApplications findApplications = new FindApplications(80, "Przeglądaj wnioski");

        commandList.add(findClient);
        commandList.add(createContract);
        commandList.add(findApplications);
    }


    public static CommandInterface getCommandObject(Integer id)
    {
        for (CommandInterface command :commandList) {
           if(command.getIdCommand().equals(id))
           {
               return command;
           }
        }
//        CommandInterface command = commandList.get((id-1));
        return null;
    }




}
