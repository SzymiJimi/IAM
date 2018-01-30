package com.inzynieria.insurance.service;

import com.inzynieria.insurance.dto.ContractWithOffer;
import com.inzynieria.insurance.model.Contract;
import com.inzynieria.insurance.model.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ContractOfferConverter{
    /**
     * Finalny statyczny obiekt loggera służący do wyświetlania informacji o czasie oraz miejscu wystpienia błędu w konsoli lub w pliku.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractOfferConverter.class);

    public ContractWithOffer convert(Contract contract, Offer offer)
    {
        return new ContractWithOffer(contract.getIdContract(), contract.getStartDate(), contract.getExpirationDate(), contract.getIdUser(),offer );
    }
}
