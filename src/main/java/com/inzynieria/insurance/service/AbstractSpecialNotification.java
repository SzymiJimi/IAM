package com.inzynieria.insurance.service;

abstract class AbstractSpecialNotification {

    protected AbstractSpecialNotification nextType;
    protected String type;

    public void processStatus(String status) {
        if (this.type.equals(status)) {
            sendRedirect();
        } else if (nextType != null) {
            nextType.processStatus(status);
        }
    }
    abstract protected void sendRedirect();

}
