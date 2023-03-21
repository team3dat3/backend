package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.shortMessage.ShortMessageRequest;
import com.team3dat3.backend.repository.ShortMessageRepository;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Short message service
 */

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortMessageService {
    
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;
    
    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;
    
    @Value("${twilio.phone.number}")
    private String TWILIO_NUMBER;

    private int DAILY_LIMIT = 5;
    
    private ShortMessageRepository shortMessageRepository;

    public ShortMessageService(ShortMessageRepository shortMessageRepository) {
        this.shortMessageRepository = shortMessageRepository;
    }

    public void send(ShortMessageRequest shortMessageRequest) throws RuntimeException {
        if (isDailyLimitReached()) {
            throw new RuntimeException("Daily short message limit reached");
        }

        saveRequest(shortMessageRequest);
        sendSms(shortMessageRequest.getReceiver(), shortMessageRequest.getMessage());
    }

    private void saveRequest(ShortMessageRequest shortMessageRequest) {
        shortMessageRequest.setSender(TWILIO_NUMBER);
        shortMessageRepository.save(shortMessageRequest.toShortMessage());
    }

    private boolean isDailyLimitReached() {
        LocalDateTime StartOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime EndOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return shortMessageRepository.countShortMessagesCreatedToday(StartOfDay, EndOfDay) >= DAILY_LIMIT;
    }

    private void sendSms(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(to), new PhoneNumber(TWILIO_NUMBER), message).create();
    }
}
