package org.example;

public class Mynt extends Finding {
    // Additional attributes specific to Mynt, if any, can be added here

    // Default constructor
    public Mynt() {
        super();  // Call the default constructor of the superclass
    }

    // Parameterized constructor
    public Mynt(int id, String funnsted, int finnerId, String funntidspunkt, int antattAarstall, int museumId, int diameter, String metall) {
        // Call the parameterized constructor of the superclass
        super(id, funnsted, finnerId, funntidspunkt, antattAarstall, museumId, "Mynt", "unknown", 0, 0, diameter, metall, "unknown");
    }
}
