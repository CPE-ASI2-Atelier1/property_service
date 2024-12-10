package com.asi2.property.sp.comm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MicroPropertyController  {

    @Autowired
    MicroPropertyService propertyService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/card")
    public boolean sendInform(@RequestBody Map<String, String> body) {        
    	Map<String, String> subBody = Map.of(
    	        "url", body.get("url"),
    	        "cardid", body.get("cardid"),
    	        "userid", body.get("userid")
    	    );
        String busName = body.get("busName");
        propertyService.sendMsg(subBody, busName);
        return true;
    }
}
