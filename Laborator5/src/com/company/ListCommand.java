package com.company;

public class ListCommand implements Command {
    @Override
    public Object execute(Catalog catalog, String arg) {
        for(var el:catalog.getDocuments())
        {
            System.out.println(el);
        }
        return null;
    }
}
