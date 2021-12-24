package com.faraz.interview.service;

import com.faraz.interview.applicationConfig.AppConfig;
import com.faraz.interview.entity.ECard;
import com.faraz.interview.entity.ECustomer;
import com.faraz.interview.exception.CardNotFoundException;
import com.faraz.interview.exception.CardOwnerNotFoundException;
import com.faraz.interview.repository.CardRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SMSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SMSService.class);

    final
    CardRepository cardRepository;

    public SMSService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void sendMessage(String sourceCardNo) throws JSONException, CardNotFoundException, CardOwnerNotFoundException, IOException {
        LOGGER.info("sendMessage method in SMSService class is started...");
        String message = AppConfig.smsServiceProvider().getTextMessage();
        ECard card = cardRepository.findByCardNo(sourceCardNo);
        ECustomer customer;
        if (card == null){
            LOGGER.info("Card number {} is not already defined", sourceCardNo);
            throw new CardNotFoundException("CardNotFoundException has been occurred");
        }else {
            customer = card.getCustomer();
        }
        if (customer == null){
            LOGGER.info("CardOwnerNotFoundException has been occurred");
            throw new CardOwnerNotFoundException("CardOwnerNotFoundException has been occurred");
        }
        String mobileNumber = customer.getPhoneNumber();
        JSONObject object = jsonBuilder(message, mobileNumber);
        HttpHandler.sendPostRequest(AppConfig.smsServiceProvider().getUrl(), object.toString());
        LOGGER.info("sendMessage method in SMSService class is finished successfully...");
    }

    private static JSONObject jsonBuilder(String message , String phoneNumber) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message" , message);
        jsonObject.put("mobileNo" , phoneNumber);
        return jsonObject;
    }
}
