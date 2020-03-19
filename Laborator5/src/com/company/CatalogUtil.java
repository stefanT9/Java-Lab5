package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    @Override
    public String toString() {
        return "CatalogUtil{}";
    }

    public static void save(Catalog catalog)
            throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }
    public static Catalog load(String path)
            throws InvalidCatalogException {
        try {
            var ois = new ObjectInputStream(new FileInputStream(path));

            Catalog catalog=new Catalog();
            catalog= (Catalog) ois.readObject();
            return catalog;
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

    }
        public static void view(Document doc) throws IOException {
        Desktop desktop = Desktop.getDesktop();

        if (doc.isExternal())
        {
            System.out.println(doc.getLocation()+"este pe net");
            desktop.browse(URI.create(doc.getLocation()));
        }
        else
        if(doc.isLocal())
        {
            System.out.println(doc.getLocation()+"este pe local");
            desktop.open(new File(doc.getLocation()));;
        }

        }
}