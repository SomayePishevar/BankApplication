package com.faraz.interview.controller;


import com.faraz.interview.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    final
    PaymentService paymentService;

    public TransactionController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/doTransaction")
    public String doTransaction(@RequestBody String requestValue){
        try {
            LOGGER.info("doPayment method in paymentService class is called...");
            paymentService.doPayment(requestValue);

        }catch (Exception ex){
            String errorMessage  = ex.getMessage();
            LOGGER.error("An Exception has been occurred with description : "+errorMessage);
            return errorMessage;
        }
        LOGGER.info("transaction done successfully");
        return "transaction done successfully";
    }
}
