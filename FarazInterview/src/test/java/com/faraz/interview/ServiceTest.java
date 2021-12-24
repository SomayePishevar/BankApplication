package com.faraz.interview;

import com.faraz.interview.exception.CardNotFoundException;
import com.faraz.interview.repository.TransactionRepository;
import com.faraz.interview.service.PaymentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceTest{

    @Autowired
    PaymentService paymentService;

    @Autowired
    TransactionRepository transactionRepository;


    String testData = "{\n  \"source\" : \"6274-1211-9154-5869\",\n  \"dest\" : \"7306-2113-8660-1221\",\n  \"cvv2\" : \"224\",\n  \"expDate\" : \"9902\",\n  \"pin\" : \"123456\",\n  \"amount\": 5000\n}";


    @Test
    void init_Test_In_DoPayment() throws JSONException {
        paymentService.init(testData);
        String source = "6274-1211-9154-5869";
        String dest = "7306-2113-8660-1221";
        assertTrue(source.equals(paymentService.getSource()));
        assertTrue(dest.equals(paymentService.getDest()));
    }

    @Test
    void validation_Test_In_DoPayment(){
        paymentService.setSource("6274-1211-9154-5869");
        Throwable exception = assertThrows(CardNotFoundException.class, () -> paymentService.validate());
        assertEquals("CardNotFoundException has been occurred", exception.getMessage());
    }


    @Test
    void doBusiness_Test_In_DoPayment() throws IOException {

        paymentService.setSource("6274-1211-9154-5869");
        paymentService.setDest("7306-2113-8660-1221");
        paymentService.doBusiness(testData);
        int size = transactionRepository.findAll().size();
        String source = "6274-1211-9154-5869";
        assertTrue(source.equals(transactionRepository.findAll().get(size-1).getSourceCardNo()));
    }



}
