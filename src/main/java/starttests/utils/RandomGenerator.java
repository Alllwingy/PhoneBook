package starttests.utils;

import java.io.*;
import java.util.*;

public class RandomGenerator {

    static Random random = new Random();
    static String fileName = "listOfEmails.txt";

    public static String generateEmail(int length) {

        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "aol.com", "msn.com"};
        String domain = domains[random.nextInt(domains.length)];

        String email = (generateString(length) + "@" + domain);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true))) {

            out.write(email);
            out.newLine();

        } catch (IOException e) {

            System.out.println("Wrong data");
        }

        return email;
    }

    public static String generateString(int length) {

        String charSequence = "abcdefghijklmnopqrstuvwxyz0123456789";
        char[] generatedString = new char[length];

        int index = 0;
        int charSequenceLength = charSequence.length();

        for (int i = 0; i < length; i++) {

            index = random.nextInt(charSequenceLength);
            generatedString[i] = charSequence.charAt(index);
        }

        return new String(generatedString);
    }

    public static String readlistOfEmailsFromFile() throws IOException {

        List<String> listOfEmails = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {

            String line = "";

            while ((line = in.readLine()) != null) {

                listOfEmails.add(line);
            }
        } catch (IOException e) {

            System.out.println("Wrong data");
        }

        return listOfEmails.get(random.nextInt(listOfEmails.size()));
    }
}
