package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Disaster {

    @Id
    private String id;
    private String hash;
    private String lastRefresh;
    private String designatedIncidentTypes;
    private int region;
    private String incidentId;
    private String femaDeclarationString;
    private String state;
    private String incidentType;
    private String declarationTitle;
    private String designatedArea;
    private LocalDateTime declarationDate;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDesignatedIncidentTypes() {
        return designatedIncidentTypes;
    }

    public void setDesignatedIncidentTypes(String designatedIncidentTypes) {
        this.designatedIncidentTypes = designatedIncidentTypes;
    }

    public String getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(String lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getFemaDeclarationString() {
        return femaDeclarationString;
    }

    public void setFemaDeclarationString(String femaDeclarationString) {
        this.femaDeclarationString = femaDeclarationString;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getDeclarationTitle() {
        return declarationTitle;
    }

    public void setDeclarationTitle(String declarationTitle) {
        this.declarationTitle = declarationTitle;
    }

    public String getDesignatedArea() {
        return designatedArea;
    }

    public void setDesignatedArea(String designatedArea) {
        this.designatedArea = designatedArea;
    }

    public LocalDateTime getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(LocalDateTime declarationDate) {
        this.declarationDate = declarationDate;
    }
}
