package com.example.PreEntrega.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class WorldClockService {

    private static final String URL = "http://worldclockapi.com/api/json/utc/now";

    public Date getCurrentDateTime() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            WorldClockResponse worldClockResponse = restTemplate.getForObject(URL, WorldClockResponse.class);

            if (worldClockResponse != null) {
                return worldClockResponse.getCurrentDateTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class WorldClockResponse {
        private Date currentDateTime;

        public Date getCurrentDateTime() {
            return currentDateTime;
        }

        public void setCurrentDateTime(Date currentDateTime) {
            this.currentDateTime = currentDateTime;
        }
    }
}
