package com.example.demo.controller;

import com.example.demo.entity.Disaster;
import com.example.demo.service.DisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Annotation
@RestController
public class DisasterController {

    @Autowired
    private DisasterService disasterService;

    // Fetch data from FEMA API and save it to H2
    @GetMapping("/fetch-and-save-disasters")
    public List<Disaster> fetchAndSaveDisasters() {
        return disasterService.fetchAndSaveDisasters();
    }

    // Create operation
    @PostMapping
    public Disaster createDisaster(@RequestBody Disaster disaster) {
        return disasterService.createDisaster(disaster);
    }

    // Update operation
    @PutMapping
    public Disaster updateDisaster(@PathVariable String id, @RequestBody Disaster disaster) {
        return disasterService.updateDisaster(id, disaster);
    }

    // Delete operation
    @DeleteMapping
    public String deleteDisaster(@RequestBody Disaster disaster) {
        return disasterService.deleteDisaster(disaster);
    }

    // Read operation
    @GetMapping("/disasters")
    public List<Disaster> getAllDisasters() {
        return disasterService.getAllDisasters();
    }
}
