package com.company;

import java.io.IOException;

public interface Command {
    Object execute(Catalog catalog, String arg) throws InvalidCatalogException, IOException;
}
