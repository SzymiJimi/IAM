package com.inzynieria.insurance.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Polecenie szukania wniosków, klasa implementuje interfejs CommandInterface co umożliwia podpięcie tego polecenia to systemu obsługi poleceń wykorzystującego
 * wzorzec command.
 */
public class FindApplications implements CommandInterface {
    /**
     * ID komendy, takie jakie zostanie przypisane w bazie danych.
     */
    private Integer idCommand;
    /**
     * Nazwa komendy taka jaka będzie wyświetlana w interfejsie aplikacji.
     */
    private String name;
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FindClient.class);

    public FindApplications(Integer idCommand, String name){
        this.idCommand = idCommand;
        this.name = name;
    }


    @Override
    public Integer getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(Integer idCommand) {
        this.idCommand = idCommand;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String execute(){
        return "http://localhost:8090/find/findApplication";
    }
}
