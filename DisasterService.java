package com.example.demo.service;

import com.example.demo.entity.Disaster;
import com.example.demo.repository.DisasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class DisasterService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DisasterRepository disasterRepository;

    @Autowired
    private ObjectMapper objectMapper;
 
    public List<Disaster> fetchAndSaveDisasters() {
        try {
            // Getting data from FEMA API
            String url = "available online";
            String rawJson = restTemplate.getForObject(url, String.class);

            // Parse raw JSON into a map
            Map<String, Object> response = objectMapper.readValue(rawJson, Map.class);

            // Parse the JSON response: The response from the API is in JSON format. Convert to a list of disasters.
            // Extract the disasterDeclarationsSummaries part
            List<Map<String, Object>> disastersData = (List<Map<String, Object>>) response.get("DisasterDeclarationsSummaries");

            // Convert raw data into disaster entities
            List<Disaster> disasters = disastersData.stream().map(data -> {
                Disaster disaster = new Disaster();
                disaster.setId(data.get("id") != null ? data.get("id").toString() : null);
                disaster.setHash(data.get("hash").toString());
                disaster.setLastRefresh(data.get("lastRefresh").toString());
                disaster.setDesignatedIncidentTypes(data.get("designatedIncidentTypes").toString());
                disaster.setRegion(Integer.parseInt(data.get("region").toString()));
                disaster.setIncidentId(data.get("incidentId").toString());
                disaster.setFemaDeclarationString(data.get("femaDeclarationString").toString());
                disaster.setState(data.get("state").toString());
                disaster.setDeclarationTitle(data.get("declarationTitle").toString());
                disaster.setDesignatedArea(data.get("designatedArea").toString());

                // Parse declarationDateStr to LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                String declarationDateStr = data.get("declarationDate").toString();
                disaster.setDeclarationDate(LocalDateTime.parse(declarationDateStr, formatter));

                return disaster;
            }).toList();

            // Save to H2 database
            return (List<Disaster>) disasterRepository.saveAll(disasters);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch and save disasters: " + e.getMessage());
        }
    }

    public Disaster createDisaster(Disaster disaster) {
        return disasterRepository.save(disaster);
    }

    public Disaster updateDisaster(String id, Disaster disasterDetails) {
        Disaster disaster = disasterRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Disaster not found for id: " + id));
        disaster.setHash(disasterDetails.getHash());
        disaster.setLastRefresh(disasterDetails.getLastRefresh());
        disaster.setDesignatedIncidentTypes(disasterDetails.getDesignatedIncidentTypes());
        disaster.setRegion(disasterDetails.getRegion());
        disaster.setIncidentId(disasterDetails.getIncidentId());
        disaster.setFemaDeclarationString(disasterDetails.getFemaDeclarationString());
        disaster.setState(disasterDetails.getState());
        disaster.setDeclarationTitle(disasterDetails.getDeclarationTitle());
        disaster.setDesignatedArea(disasterDetails.getDesignatedArea());
        disaster.setDeclarationDate(disasterDetails.getDeclarationDate());

        return disasterRepository.save(disaster);
    }

    public String deleteDisaster(Disaster disaster) {
        disasterRepository.delete(disaster);
        return "Disaster deleted successfully!";
    }

    public List<Disaster> getAllDisasters() {
        return (List<Disaster>) disasterRepository.findAll();
    }
}
