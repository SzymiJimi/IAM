package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Application;
import com.inzynieria.insurance.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    ApplicationRepository applicationRepository;


    @Override
    public List<Application> findApplication(String value) throws ValidationException {
        if (value == null) {
            throw new ValidationException("The name cannot be null");
        }
        return applicationRepository.findApplicationByType(value);
    }

}