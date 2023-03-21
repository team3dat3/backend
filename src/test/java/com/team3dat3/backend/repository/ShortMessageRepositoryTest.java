package com.team3dat3.backend.repository;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Short message repository
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.team3dat3.backend.entity.ShortMessage;

@DataJpaTest
public class ShortMessageRepositoryTest {
    
    @Autowired
    ShortMessageRepository shortMessageRepository;

    private final String sender = "+4512345678";
    private final String receiver = "+4512345678";

    private ShortMessage shortMessageCreatedToday;
    private ShortMessage shortMessageCreatedYesterday;

    private LocalDateTime yesterday = LocalDateTime.now().minusDays(1);

    @BeforeEach
    void beforeEach() {
        shortMessageCreatedToday = new ShortMessage("Hello World", sender, receiver);
        shortMessageCreatedYesterday = new ShortMessage("Hello World", sender, receiver);
        shortMessageCreatedToday = shortMessageRepository.save(shortMessageCreatedToday);
        // #IMPORTANT
        // The objects have to be saved before they can be updated.
        // so, to override the createdDate, the object has to be saved again.
        shortMessageRepository.save(shortMessageCreatedYesterday);
        shortMessageCreatedYesterday.setCreatedDate(yesterday);
        shortMessageCreatedYesterday = shortMessageRepository.save(shortMessageCreatedYesterday);
    }

    @Test
    void testShortMessageCreatedYesterday() {
        assertEquals(yesterday, shortMessageCreatedYesterday.getCreatedDate());
    }

    @Test
    void testCountShortMessagesCreatedToday() {
        LocalDateTime StartOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime EndOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        int count = shortMessageRepository.countShortMessagesCreatedToday(StartOfDay, EndOfDay);
        assertEquals(1, count);
    }
}
