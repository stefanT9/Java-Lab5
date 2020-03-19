package com.company;

import java.io.File;
import java.io.IOException;

public class AddCommand implements Command {
    @Override
    public Object execute(Catalog catalog, String arg) throws InvalidCatalogException, IOException {
        var file=new File(arg);
        var name=file.getName();
        var id=file.getName().split("[.]")[0];
        Document document=new Document(id,name,arg);
        catalog.add(document);

        System.out.println(document);
        return null;
    }
}
