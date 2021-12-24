package com.faraz.interview.entity;


import com.faraz.interview.exception.CardNotFoundException;
import com.faraz.interview.exception.CardOwnerNotFoundException;
import com.faraz.interview.service.SMSService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;
import java.io.IOException;


public class TransactionLoggingListener {

    @Autowired
    SMSService smsService;

    @PostPersist
    public void methodInvokedAfterPersist(ETransaction tx) throws JSONException, IOException, CardOwnerNotFoundException, CardNotFoundException {
        String sourceCardNo = tx.getSourceCardNo();
        smsService.sendMessage(sourceCardNo);
    }
}
