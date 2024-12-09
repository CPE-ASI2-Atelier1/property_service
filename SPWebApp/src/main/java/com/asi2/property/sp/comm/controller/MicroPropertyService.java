package com.asi2.property.sp.comm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MicroPropertyService {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMsg(Map<String, String> subBody, String busName) {
        System.out.println("[BUSSERVICE] SEND body with url =["+subBody.get("url")+"] to Bus =["+busName+"]");
        jmsTemplate.convertAndSend(busName, subBody);
    }
}

