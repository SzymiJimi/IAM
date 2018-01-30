package com.inzynieria.insurance.commands.config;

import com.inzynieria.insurance.commands.*;

import com.inzynieria.insurance.repository.CommandRepository;
import com.inzynieria.insurance.repository.RoleRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;



public class CommandsConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsConfig.class);
    @Autowired
    CommandRepository commandRepository;

    @Autowired
    RoleRepository roleRepository;


    static List<CommandInterface> commandList = new ArrayList<>();

    /**
     * Metoda, w której tworzone są obiekty implementujące interfejs CommandInterface, nadawane jest im ID komendy, nazwa i dodawane są do listy commandList.
     */
    public static void createCommands()
    {
        FindClient findClient = new FindClient(1, "Wyszukaj klienta");
        CreateContract createContract = new CreateContract(2, "Stwórz umowę");
        FindApplications findApplications = new FindApplications(80, "Przeglądaj wnioski");
        AddApplication addApplication = new AddApplication(81, "Złóż wniosek");

        AddClient addClient=new AddClient(3, "Dodaj klienta");
        CreateNotification createNotification = new CreateNotification(5, "Zgłoszenie zdarzenia");
        ViewNotification viewNotification = new ViewNotification(6, "Przeglądaj zgłoszenia");
        CheckInsurance checkInsurance = new CheckInsurance(7, "Sprawdź czy ubezpieczony");
        AddOffer addOffer=new AddOffer(41, "Dodaj ofertę");
        FindOffer findOffer= new FindOffer(42, "Wyszukaj oferty");
        commandList.add(findClient);
        commandList.add(createContract);
        commandList.add(createNotification);
        commandList.add(viewNotification);
        commandList.add(checkInsurance);
        commandList.add(addOffer);
        commandList.add(findOffer);
        commandList.add(addClient);
        commandList.add(findApplications);
        commandList.add(addApplication);
    }

    /**
     * Wyszaukiwana jest komenda w liście na podstawie podanego ID.
     * @param id ID wyszukiwanej komendy.
     * @return Zwraca znalezioną komende.
     */
    public static CommandInterface getCommandObject(Integer id)
    {

        LOGGER.info("Klient uzywa komendy o id:"+ id);
        if(commandList.isEmpty())
        {
            createCommands();
        }

        for (CommandInterface command :commandList) {
           if(command.getIdCommand().equals(id))
           {
               return command;
           }
        }
        return null;
    }




}
