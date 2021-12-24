package com.faraz.interview.service;

import com.faraz.interview.applicationConfig.AppConfig;
import com.faraz.interview.entity.ECard;
import com.faraz.interview.entity.ETransaction;
import com.faraz.interview.exception.CardNotFoundException;
import com.faraz.interview.repository.CardRepository;
import com.faraz.interview.repository.TransactionRepository;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

@Service
@Getter
@Setter
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    final
    CardRepository cardRepository;

    final
    TransactionRepository transactionRepository;


    public PaymentService(CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    private JSONObject jsonObject;
    private String source;
    private String dest;

    @Transactional
    public void doPayment(String requestValue) throws JSONException, CardNotFoundException, IOException {

        init(requestValue);
        validate();
        doBusiness(requestValue);
    }

    public void init(String requestValue) throws JSONException {
        LOGGER.info("init method in PaymentService class is started...");
        jsonObject = new JSONObject(requestValue);
        source = jsonObject.getString("source");
        dest = jsonObject.getString("dest");
        LOGGER.info("init method in PaymentService class is done successfully...");
    }

    public void validate() throws CardNotFoundException {
        LOGGER.info("validate method in PaymentService class is started...");
        ECard card = cardRepository.findByCardNo(source);
        if (card == null) {
            LOGGER.info("Card number {} is not already defined", source);
            throw new CardNotFoundException("CardNotFoundException has been occurred");
        }
        LOGGER.info("validate method in PaymentService class is finished successfully...");
    }

    public void doBusiness(String requestValue) throws IOException {
        LOGGER.info("doBusiness method in PaymentService class is started...");
        boolean cardType = ECard.bankServiceProviderType(source);
        String result;
        String url;

        if (cardType){
            url = AppConfig.paymentServiceProvider1().getUrl();
        }else{
            url = AppConfig.paymentServiceProvider2().getUrl();
        }
        result = HttpHandler.sendPostRequest(url , requestValue);

        if (result.contains("200")){
            ETransaction transaction = new ETransaction();
            transaction.setSourceCardNo(source);
            transaction.setDestinationCardNo(dest);
            transaction.setTransactionDate(new Date().toString());
            transactionRepository.save(transaction);
        }
        LOGGER.info("doBusiness method in PaymentService class is finished successfully...");
    }
}
