package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.model.Orders;
import com.inzynieria.insurance.repository.OrdersRepository;
import com.inzynieria.insurance.service.SendMailService;
import org.apache.naming.factory.SendMailFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@RequestMapping(value = "/order")
public class OrderController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrdersRepository ordersRepository;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Orders orderData)
    {
        LOGGER.info("Dodaje zamówienie");
        String message=" Witaj "+orderData.getName()+" "+ orderData.getSurname()+" przyjeliśmy Twoje zamówienie, w krótce odezwie się do Pana(i) nasz konsultant.";
        try{
            orderData.setStatus("OPEN");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar startDate = Calendar.getInstance();
            orderData.setFillingDate(format1.format(startDate.getTime()));
            ordersRepository.save(orderData);
            SendMailService sendMailService = new SendMailService(orderData.getEmail(), message);
            sendMailService.send();
            return ResponseEntity.status(HttpStatus.OK).body("Dodawanie zgłoszenia zakończone pomyślnie!");
        }catch (Exception e )
        {
            LOGGER.info("Coś poszło nie tak...");
            LOGGER.info("Wyjątek: "+ e.toString());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Wystąpił błąd przy dodawaniu danych do bazy...");
        }
    }


}
