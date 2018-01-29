package com.inzynieria.insurance.commands.config;

import com.inzynieria.insurance.commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa która wspomaga użycie wzorca command. W klasie konfigurujemy numery ID, którym będą odpowiadały konkretne polecenia.
 */
public class CommandsConfig {

    /**
     * Lista która przechowuje obiekty implementujące interfejs CommandInterface.
     */
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
        for (CommandInterface command :commandList) {
           if(command.getIdCommand().equals(id))
           {
               return command;
           }
        }
        return null;
    }




}
