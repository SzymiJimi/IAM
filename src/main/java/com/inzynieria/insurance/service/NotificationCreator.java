package com.inzynieria.insurance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;

public class NotificationCreator implements Observer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationCreator.class);

    @Override
    public void update(Observable o, Object arg) {
        LOGGER.info("Wywołał się updejt dla obiektu: "+o.toString()+" argument: "+arg.toString() );
    }
}
