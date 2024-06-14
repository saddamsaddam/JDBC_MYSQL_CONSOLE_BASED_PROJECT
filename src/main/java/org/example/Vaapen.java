package org.example;

public class Vaapen extends Finding {
    // Additional attribute specific to Vaapen
    private String materiale;
    private int vekt;

    // Default constructor
    public Vaapen() {
        super();  // Call to the default constructor of the superclass
    }

    // Parameterized constructor
    public Vaapen(int id, String funnsted, int finnerId, String funntidspunkt, int antattAarstall, int museumId, String type, String materiale, int vekt) {
        super(id, funnsted, finnerId, funntidspunkt, antattAarstall, museumId, type, materiale, vekt, 0, 0, "unknown", "unknown");  // Call to the parameterized constructor of the superclass
        this.materiale = materiale;
        this.vekt = vekt;
    }

    // Getters and setters for Vaapen-specific fields
    public String getMateriale() {
        return materiale;
    }

    public void setMateriale(String materiale) {
        this.materiale = materiale;
    }

    public int getVekt() {
        return vekt;
    }

    public void setVekt(int vekt) {
        this.vekt = vekt;
    }
}
