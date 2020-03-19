package com.company;

public class LoadCommand implements Command {
    @Override
    public Catalog execute(Catalog catalog, String arg) throws InvalidCatalogException {
        return CatalogUtil.load(arg);
    }
}
