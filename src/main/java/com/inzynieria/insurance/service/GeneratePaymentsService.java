package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Contract;

import java.text.ParseException;
import java.util.Calendar;

public interface GeneratePaymentsService {

    void generatePayments(Integer idContract, Integer userId, Integer idOffer, String startDateString);
}
