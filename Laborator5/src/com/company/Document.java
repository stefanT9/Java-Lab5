package com.company;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", tags=" + tags +
                '}';
    }

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Document(String id, String name, String location, Map<String, Object> tags) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.tags = tags;
    }

    private String id;
    private String name;
    private String location; //file name or Web page

    private Map<String, Object> tags = new HashMap<>();
    //…
    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isExternal() {
        try {
            var uri=new URI(location);
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }

    public boolean isLocal() {
        try{
            var file=new File(location);
        } catch (Exception e) {
            return false;
        }
        return true;

    }
//…
}