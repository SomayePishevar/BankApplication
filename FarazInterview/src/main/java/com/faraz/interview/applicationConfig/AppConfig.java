package com.faraz.interview.applicationConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public static PaymentServiceProvider1 paymentServiceProvider1(){
        return new PaymentServiceProvider1();
    }

    @Bean
    public static PaymentServiceProvider2 paymentServiceProvider2(){
        return new PaymentServiceProvider2();
    }

    @Bean
    public static SMSServiceProvider smsServiceProvider(){
        return new SMSServiceProvider();
    }
}
