package org.example;

public class Finding {
    // Instance variables to represent the attributes of a Finding
    private int id;
    private String location;
    private int finderId;
    private String foundDate;
    private int year;
    private int museumId;
    private String type;
    private String material;
    private int weight;
    private int estimatedValue;
    private int diameter;
    private String metal;
    private String category;
    public Finding(){

    }

    public Finding(int id, String location, int finderId, String foundDate, int year, int museumId, String type, String material, int weight, int estimatedValue, int diameter, String metal, String category) {
        this.id = id;
        this.location = location;
        this.finderId = finderId;
        this.foundDate = foundDate;
        this.year = year;
        this.museumId = museumId;
        this.type = type;
        this.material = material;
        this.weight = weight;
        this.estimatedValue = estimatedValue;
        this.diameter = diameter;
        this.metal = metal;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFinderId() {
        return finderId;
    }

    public void setFinderId(int finderId) {
        this.finderId = finderId;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMuseumId() {
        return museumId;
    }

    public void setMuseumId(int museumId) {
        this.museumId = museumId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(int estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
