package com.company;

import java.io.IOException;

public class ViewCommand implements Command {
    @Override
    public Object execute(Catalog catalog, String arg) throws IOException {
        CatalogUtil.view(catalog.findById(arg));
        return null;
    }
}
