package org.example;

public class Smykke extends Finding {
    private String filnavn;  // Additional attribute specific to Smykke

    // Default constructor
    public Smykke() {
        // Needed for default instantiation
    }

    // Constructor to initialize the Smykke object with all attributes
    public Smykke(int id, String funnsted, int finnerId, String funntidspunkt, int antattAarstall, int museumId, String type, String material, int weight, int estimatedValue, int diameter, String metal, String category, String filnavn) {
        super(id, funnsted, finnerId, funntidspunkt, antattAarstall, museumId, type, material, weight, estimatedValue, diameter, metal, category); // Call superclass constructor
        this.filnavn = filnavn;
    }

    // Additional getters and setters specific to Smykke
    public String getFilnavn() {
        return filnavn;
    }

    public void setFilnavn(String filnavn) {
        this.filnavn = filnavn;
    }
}
