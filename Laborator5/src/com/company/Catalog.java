package com.company;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents;

    public Catalog() {
        this.name="defaultCatalog";
        this.path="./defaultCatalog.ser";
        documents=new LinkedList<>();
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
        this.documents=new LinkedList<>();
    }

    public Catalog(String name, String path, List<Document> documents) {
        this.name = name;
        this.path = path;
        this.documents = documents;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Document findById(String id) {
        return documents.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
