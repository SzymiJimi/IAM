package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Notification;

import java.util.Comparator;

public class CustomComparator implements Comparator<Notification> {
    @Override
    public int compare(Notification o1, Notification o2) {

        return o2.getIdNotification().compareTo(o1.getIdNotification());
    }
}
