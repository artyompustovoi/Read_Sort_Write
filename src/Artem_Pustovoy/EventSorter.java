package Artem_Pustovoy;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static Artem_Pustovoy.EventSorting.sortByDate;

public class EventSorter {

    public static void main(String[] args) {
        String inputFilePath = "input.txt"; // Путь к входному файлу
        String outputFilePath = "output.txt"; // Путь к выходному файлу

        List<Event> events = readEventsFromFile(inputFilePath);
        events = sortByDate(events);
       // events.sort(Comparator.comparing(Event::date));

        writeEventsToFile(events, outputFilePath);

        System.out.println("Events sorted and written to file successfully.");
    }

    private static List<Event> readEventsFromFile(String filePath) {
        List<Event> events = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                LocalDate date = LocalDate.parse(parts[1].substring(0, 10), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String name = parts[3];
                String description = parts[5];

                events.add(new Event(date, name, description));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
        return events;
    }

    private static void writeEventsToFile(List<Event> events, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Event event : events) {
                writer.write("date: " + event.date() + " name: " + event.name() + " description: " + event.description());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

record Event(LocalDate date, String name, String description) { }