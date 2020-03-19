package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.LinkedList;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class Main {
    public static void main(String args[]) throws IOException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();

        String commandLine;

        Catalog catalog=new Catalog();

        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));
        while (true) {
            //read the command
            System.out.print("shell>");
            commandLine = console.readLine();

            if (commandLine.equals("help")) {
                System.out.println("Welcome heres some help");
            }
            if (commandLine.equals("exit")) {
                System.out.println("Bye bye");
                exit(0);
            }
            if(commandLine.startsWith("load"))
            {
                Command command=new LoadCommand();
                String arg=commandLine.split(" ")[1];
                try {
                    catalog= (Catalog) command.execute(null,arg);
                } catch (InvalidCatalogException e) {
                    System.out.println("something went wrong");
                }
            }
            if(commandLine.startsWith("view"))
            {
                Command command=new ViewCommand();
                String arg=commandLine.split(" ")[1];
                try {
                    command.execute(catalog,arg);
                } catch (InvalidCatalogException e) {
                    System.out.println("something went wrong");
                }
            }
            if(commandLine.equals("list"))
            {
                Command command=new ListCommand();
                try {
                    command.execute(catalog,null);
                } catch (InvalidCatalogException e) {
                    System.out.println("something went wrong");
                }
            }
            if(commandLine.equals("save"))
            {
                Command command=new SaveCommand();
                try
                {
                    command.execute(catalog,null);
                } catch (InvalidCatalogException e) {
                    System.out.println("something went wrong");
                }
            }
            if(commandLine.startsWith("add"))
            {
                Command command=new AddCommand();
                var arg=commandLine.split(" ")[1];
                try {
                    command.execute(catalog,arg);
                } catch (InvalidCatalogException e) {
                    e.printStackTrace();
                }
            }
            if(commandLine.startsWith("report"))
            {
                Command command=new RepportCommand();
                var arg=commandLine.split(" ")[1];

                try{
                    command.execute(catalog,arg);
                } catch (InvalidCatalogException e) {
                    System.out.println("something went wrong");
                }
            }
        }
    }

    private void testCreateSave() {
        Catalog catalog =
                new Catalog("Java Resources", "D:\\javaEnv\\catalog.ser");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);

        try {
            CatalogUtil.save(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testLoadView() {
        Catalog catalog = null;
        try {
            catalog = CatalogUtil.load("D:\\javaEnv\\catalog.ser");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);

        } catch (InvalidCatalogException | IOException e) {
            e.printStackTrace();
        }
    }
}
