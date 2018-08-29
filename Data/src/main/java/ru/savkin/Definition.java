package ru.savkin;

import org.w3c.dom.Node;

import java.io.Serializable;

public class Definition implements Serializable {

    private final  String definition;
    private final String description;


    public Definition(String definition, String description ) {
        this.definition = definition;
        this.description = description;
    }

    public Definition(String data) {
         if (data.contains(":")) {
             String [] args = data.split(":");
             this.definition = args[0];
             this.description = args[1];
         } else {
             throw  new IllegalArgumentException("Wrong format");
         }

    }

    public String getDefinition() {
        return definition;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        return sb
                .append(definition)
                .append(":")
                .append(description).toString();

    }


}
