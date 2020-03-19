package com.company;

import java.io.IOException;

public class SaveCommand implements Command {
    @Override
    public Object execute(Catalog catalog, String arg) throws InvalidCatalogException, IOException {
        CatalogUtil.save(catalog);
        return null;
    }
}
