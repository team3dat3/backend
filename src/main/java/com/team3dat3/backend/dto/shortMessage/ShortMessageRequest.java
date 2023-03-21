package com.team3dat3.backend.dto.shortMessage;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Short message request
 */

import com.team3dat3.backend.entity.ShortMessage;

import lombok.*;
 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShortMessageRequest {

    private String message;
    private String sender;
    private String receiver;

    public void copyTo(ShortMessage shortMessage) {
        shortMessage.setMessage(message);
        shortMessage.setSender(sender);
        shortMessage.setReceiver(receiver);
    }

    public ShortMessage toShortMessage() {
        return new ShortMessage(message, sender, receiver);
    }
}
