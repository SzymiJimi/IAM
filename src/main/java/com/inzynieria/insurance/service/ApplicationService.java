package com.inzynieria.insurance.service;

import com.inzynieria.insurance.model.Application;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ApplicationService{

    List<Application>  findApplication(String value) throws ValidationException;
}