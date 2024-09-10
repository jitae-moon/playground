package com.example.playground.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@Slf4j
@RestController
public class MainController {

    @GetMapping("/regex")
    public void regex(@RequestParam("phoneNumber") String phoneNumber) {
        log.info("MainController :: regex :: phoneNumber = {}", phoneNumber);
        String phoneNumberPatternString = "^\\d{3}-\\d{3,4}-\\d{4}$";
        String changedPhoneNumber = phoneNumber;

        if (Pattern.matches(phoneNumberPatternString, phoneNumber)) {
            changedPhoneNumber = phoneNumber.replaceAll("-", "");
        }

        log.info("MainController :; regex :: changedPhoneNumber = {}", changedPhoneNumber);
    }

}
