package com.inzynieria.insurance.controller;


import com.inzynieria.insurance.commands.CommandInterface;
import com.inzynieria.insurance.commands.config.CommandsConfig;
import com.inzynieria.insurance.dto.CommandDto;
import com.inzynieria.insurance.model.Command;
import com.inzynieria.insurance.model.Role;
import com.inzynieria.insurance.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/role")
public class RoleController {
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    /**
     * Repozytorium ról
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Metoda służąca do pobrania ról zalogowanego użytkownika
     * @param roles
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public List<CommandDto> getRole(@RequestBody List<Role> roles)
    {
        Set<Command> set= new HashSet<>(0);
        for (Role r : roles) {
            set.addAll( roleRepository.findOne(r.getIdRole()).getCommands());
        }

        Command [] array =  set.toArray(new Command[0]);
        List<CommandDto> commandList = new ArrayList<>();
        for(int i = 0; i<array.length ;i++)
        {
            commandList.add(new CommandDto(array[i].getIdCommand(),array[i].getName()));
        }
        return commandList;
    }


    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String run( @PathVariable(value="id") Integer id)
    {
        LOGGER.info("Jestem w role, przyjete id: "+id);
        CommandInterface command = CommandsConfig.getCommandObject(id);
        return command.execute();
    }
}
