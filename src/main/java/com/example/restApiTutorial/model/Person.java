package com.example.restApiTutorial.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Person {
    private final UUID id;

    private final String name;
                //dzieki temu spring wie które wartości z 'body' przypisac
    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
