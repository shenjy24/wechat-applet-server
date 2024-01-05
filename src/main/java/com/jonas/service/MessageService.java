package com.jonas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * MessageService
 *
 * @author shenjy
 * @time 2024/1/3 10:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    public void sendMessage1(String thing1, String characterString3) {
        Map<String, Object> params = new HashMap<>();
        params.put("thing1", thing1);
        params.put("character_string3", characterString3);
    }
}
