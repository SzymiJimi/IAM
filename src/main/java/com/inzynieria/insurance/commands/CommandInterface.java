package com.inzynieria.insurance.commands;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public interface CommandInterface {

    Integer idCommand = null;
    String name=null;
    String execute();
    Integer getIdCommand();
    void setIdCommand(Integer idCommand);
    String getName();
    void setName(String name);
}
