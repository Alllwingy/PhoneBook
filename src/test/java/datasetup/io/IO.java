package datasetup.io;

import datasetup.Data;
import datasetup.dto.ContactDTO;
import datasetup.dto.UserContact;
import datasetup.dto.UserDTOLombok;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IO {

    public static String switchPath(String methodName) {

        String path;

        switch (methodName) {

            case "positive_UserDTO": path = "src/test/resources/userpositive.csv"; return path;

            case "negative_UserDTOLombok_EmptyEmail": path = "src/test/resources/usernegative.csv"; return path;

            case "addContactPositive": path = "src/test/resources/contactpositive.csv"; return path;

            case "addContactNegativePhone": path = "src/test/resources/contactnegativephone.csv"; return path;

            case "addContactNegativeEmail": path = "src/test/resources/contactnegativeemail.csv"; return path;
        }

        return null;
    }

    public static Iterator<Object[]> read(String methodName) {

        List<Object[]> list = new ArrayList<>();
        List<ContactDTO> contacts = new ArrayList<>();
        Data.ConfigurationPropertiesData cpd = new Data.ConfigurationPropertiesData();
        String path = switchPath(methodName);

        if (path != null) {
            try (BufferedReader in = new BufferedReader(new FileReader(new File(path)))) {

                String line = in.readLine();
                String[] check = line.split(",");

                if (check.length == 2) {

                    while (line != null) {

                        String[] split = line.split(",");

                        list.add(new Object[]{
                                UserDTOLombok.builder()
                                        .username(split[0])
                                        .password(split[1])
                                        .build()
                        });

                        line = in.readLine();
                    }
                }

                if (check.length == 7) {

                    while (line != null) {

                        String[] split = line.split(",");

                        contacts.add(ContactDTO.builder()
                                .id(split[0])
                                .name(split[1])
                                .lastName(split[2])
                                .email(split[3])
                                .phone(split[4])
                                .address(split[5])
                                .description(split[6])
                                .build());

                        line = in.readLine();
                    }

                    list.add(new Object[]{
                            UserContact.builder()
                                    .user(cpd.lombok)
                                    .contacts(contacts)
                                    .build()
                    });
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        return list.iterator();
    }
}
