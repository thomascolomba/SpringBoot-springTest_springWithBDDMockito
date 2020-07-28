package com.thomas.services;

import com.thomas.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    HelloRepository helloRepository;

    @Override
    public String get(String myString) {
        return helloRepository.get(myString + "stringAddedFromHelloServiceImpl");
    }

}
