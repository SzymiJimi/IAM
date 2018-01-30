package com.inzynieria.insurance.commands;

/**
 * Interfejs, który umożliwia zaimplementowanie wzorca Command. Wszystkie polecenia w naszym systemie będą implementowały ten interfejs.
 */
public interface CommandInterface {

    /**
     * ID komendy jakie zapisane zostanie do bazy danych.
     */
    Integer idCommand = null;
    /**
     * Nazwa polecenia wyświetlana w aplikacji.
     */
    String name=null;

    /**
     * Metoda której wywołanie uruchomi daną komendę.
     * @return
     */
    String execute();

    Integer getIdCommand();
    void setIdCommand(Integer idCommand);
    String getName();
    void setName(String name);
}
