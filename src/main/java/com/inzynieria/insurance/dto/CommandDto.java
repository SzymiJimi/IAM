package com.inzynieria.insurance.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Uproszczona werja modelu Command z bazy danych. Różni się tym od klasy modelowej Command tym, że klasa ta nie posiada żadnych odniesień do encji role.
 * Uławia to sparsowanie obiektu do JSONa.
 */
@Data
public class CommandDto {


    private Integer idCommand;
    private String name;

    public CommandDto (){

    }

    public CommandDto( Integer idCommand, String name)
    {
        this.idCommand= idCommand;
        this.name= name;
    }

    public CommandDto( String name)
    {
        this.name= name;
    }


    public Integer getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(Integer idCommand) {
        this.idCommand = idCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
