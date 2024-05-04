package com.example.demo.controllers;
import com.example.demo.models.*;
import com.example.demo.repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

@Controller
public class CoindeskController {
    @Autowired
    private HistoryBTCRepository historybtcRepo;

    @GetMapping("/api/btc-history")
    @ResponseBody
    public Iterable<HistoryBTC> getCoins(){
        Iterable<HistoryBTC> history = historybtcRepo.findAll();
        return history;
    }

    @GetMapping("/api/btc-current-price")
    @ResponseBody
    public HistoryBTC getPrice(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        String jsonString = restTemplate.getForObject(url, String.class);
        if (historybtcRepo.count() == 10) {
            HistoryBTC firstRecord = null;
            Iterable<HistoryBTC> history = historybtcRepo.findAll();
            Iterator<HistoryBTC> iterator = history.iterator();
            if (iterator.hasNext()) {
                firstRecord = iterator.next();
            }
            historybtcRepo.delete(firstRecord);
        }
        if (jsonString != null && !jsonString.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                String price = jsonNode.path("bpi").path("USD").path("rate").asText();
                String dateTime = jsonNode.path("time").path("updated").asText();
                HistoryBTC btcPrice = new HistoryBTC(price, dateTime, Boolean.TRUE);
                historybtcRepo.save(btcPrice);
                return btcPrice;
            } catch (Exception e) {
                return null;
            }
        }

        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss zzz");
        String dateTime = currentDateTime.format(formatter);

        HistoryBTC btcPrice = new HistoryBTC(null, dateTime, Boolean.FALSE);
        historybtcRepo.save(btcPrice);
        return btcPrice;
    }
}
