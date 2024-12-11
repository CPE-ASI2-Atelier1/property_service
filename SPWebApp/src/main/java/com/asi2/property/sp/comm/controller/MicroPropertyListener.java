package com.asi2.property.sp.comm.controller;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tp.cpe.ImgToProperties;

import jakarta.jms.Message;
import org.springframework.web.client.RestTemplate;

@Component
public class MicroPropertyListener {
	@Autowired
    JmsTemplate jmsTemplate;
	 private final String apiUrl = "http://localhost:8080/sch/Properties";
	
    @JmsListener(destination = "PropertyImageUrls", containerFactory = "connectionFactory")
    public void receiveMessage(Map<String, String> subBody, Message message) {
        System.out.println("Received msgStr: " + subBody.get("url"));

        processMessage(subBody);
    }


    private void processMessage(Map<String, String> subBody) {
    	RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/" + "";
        String imageUrl = subBody.get("url");
    	try {
            System.out.println("Processing message: " + imageUrl);
//            String test = "https://e1.pngegg.com/pngimages/691/153/png-clipart-circulos-round-green-art-thumbnail.png";

            Map<String, Float> result = ImgToProperties.getPropertiesFromImg(imageUrl, 100f, 4, 0.3f, false);
            Float defenseValue = result.get("DEFENSE");
            Float energyValue = result.get("ENERGY");
            Float hpValue = result.get("HP");
            Float attackValue = result.get("ATTACK");
            
            Map<String, Object> body = new HashMap<>();
            body.put("cardid", subBody.get("cardid"));
            body.put("defense", defenseValue);
            body.put("energy", energyValue);
            body.put("hp", hpValue);
            body.put("attack", attackValue);
            
            System.out.println(body);	
            
            String response = restTemplate.postForObject(url, body, String.class);
            // Selon la réponse on a plusieurs choix.

        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing message: " + e);
        }
    }
    
}
