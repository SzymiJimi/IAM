package com.inzynieria.insurance.controller;

import com.inzynieria.insurance.dto.ContractWithOffer;
import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.User;
import com.inzynieria.insurance.repository.ContractRepository;
import com.inzynieria.insurance.repository.OfferRepository;
import com.inzynieria.insurance.service.ContractOfferConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);


    @Autowired
    ContractRepository contractRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    ContractOfferConverter contractOfferConverter;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String createContract(@RequestBody Contract contract){
        LOGGER.info("Dodaje kontrakt:  idOferty: "+contract.getIdOffer()+" Data ważności:"+ contract.getExpirationDate()+", data stworzenia: "+contract.getStartDate()+" idUsera: "+contract.getIdUser());
        contractRepository.save(contract);
        return "Zarejestrowano pomyślnie";
    }

    @RequestMapping(value="/find/{id}", method = RequestMethod.GET)
    public Boolean findContract(@PathVariable(value="id") Integer id){
        LOGGER.info("Przyjąłem id: "+id);
        List<Contract> contracts= contractRepository.findContractByIdUser(id);
        LOGGER.info("Liczba znalezionych kontraktów: "+ contracts.size());
        return !contracts.isEmpty();
    }

    @RequestMapping(value="/get/{id}", method = RequestMethod.GET)
    public Contract getContract(@PathVariable(value="id") Integer id){
        Contract contract= contractRepository.findOne(id);
        return contract;
    }

    @RequestMapping(value="/getList/{userId}", method = RequestMethod.GET)
    public List<ContractWithOffer> getContractsWithOffers(@PathVariable(value="userId") Integer userId){
        LOGGER.info("Przyjąłem id: "+userId);
        List<Contract> contracts= contractRepository.findContractByIdUser(userId);
        List<ContractWithOffer> contractWithOffers = new ArrayList<>();
        for(Contract contract : contracts)
        {
            contractWithOffers.add(contractOfferConverter.convert(contract, offerRepository.findOne(contract.getIdOffer())));
        }

        return contractWithOffers;
    }

    @RequestMapping(value="/check", method = RequestMethod.POST)
    public ResponseEntity checkInsurance(@RequestBody String pesel){
        LOGGER.info("Podbrany pesel: "+pesel);
        try {
            List<Contract> contracts = contractRepository.findContractByPesel(pesel);
            List<ContractWithOffer> contractWithOffers = new ArrayList<>();
            for(Contract contract : contracts)
            {
                contractWithOffers.add(contractOfferConverter.convert(contract, offerRepository.findOne(contract.getIdOffer())));
            }
            if(contractWithOffers.isEmpty())
            {
                LOGGER.info("Zwracany błąd ");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brak użytkownika w bazie danych...");
            }
            return ResponseEntity.status(HttpStatus.OK).body(contractWithOffers);
        }catch(Exception e)
        {
            LOGGER.info("Zwracany błąd ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nieudany odczyt z bazy danych...");
        }
    }

}
