package com.tatva.controller;

import com.tatva.datafaker.CustomClassInfoStrategy;
import com.tatva.datafaker.CustomPodamFactory;
import com.tatva.dto.User;
import com.tatva.model.CompanyShare;
import com.tatva.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.jemos.podam.api.PodamFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    static PodamFactory getCustomPodamFactory() {
        CustomPodamFactory customPodamFactory = new CustomPodamFactory();
        customPodamFactory.setClassStrategy(new CustomClassInfoStrategy());
        return customPodamFactory;
    }

    @GetMapping("/publish/{message}")
    public void publishMessage(@PathVariable String message) {
        try {
            System.currentTimeMillis();
            List<CompanyShare> fake = new ArrayList<>();
            CompanyShare fakeData = null;
            Random rand = new Random();
            for (int i = 0; i < 50; i++) {
                int y = rand.nextInt(1000,5000);
                fakeData = new CompanyShare();
                fakeData.setDate(new Date());
                fakeData.setCompanyId(i+1);
                fakeData.setCurrentValue(new BigDecimal(y));
                fake.add(fakeData);
            }
            while (true) {
                for (int i = 0; i < 50; i++) {
                    int x = rand.nextInt(-3,3);
                    fake.get(i).setCurrentValue(fake.get(i).getCurrentValue().add(new BigDecimal(x)));
                    if (i == 0) System.out.println(fake);
                    publisher.sendMessageToTopic(fake.get(i));
                }
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @PostMapping("/publish")
    public void sendEvents(@RequestBody User user) {
        publisher.sendEventsToTopic(user);
    }


}
