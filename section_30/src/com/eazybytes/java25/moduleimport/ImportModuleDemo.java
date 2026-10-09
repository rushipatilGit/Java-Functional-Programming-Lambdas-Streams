package com.eazybytes.java25.moduleimport;

import module java.base;

public class ImportModuleDemo {

    public static void main(String[] args) {
        // java.util.Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter some names (type 'exit' to finish):");

        // java.util.List, java.util.ArrayList
        List<String> names = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            names.add(input);
        }

        // java.util.Collections
        Collections.sort(names);

        // java.util.Date
        Date now = new Date();

        // java.io.File and FileWriter
        File file = new File("names.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Sorted names (saved on " + now + "):\n");
            for (String name : names) {
                writer.write(name + "\n");
            }
            System.out.println("Names saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // java.io.BufferedReader and FileReader
        System.out.println("\nReading names back from the file:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
