package datasetup;

import datasetup.dto.*;
import datasetup.io.IO;
import org.testng.annotations.DataProvider;
import utils.ConfigurationProperties;
import utils.FakerGenerator;

import java.lang.reflect.Method;
import java.util.*;

public class Data {

    static String email = ConfigurationProperties.getProperty("email");
    static String password = ConfigurationProperties.getProperty("password");

    public static class ConfigurationPropertiesData {


        public UserDTO dto = new UserDTO(email, password);

        public UserDTOWith with = new UserDTOWith()
                .withEmail(email)
                .withPassword(password);

        public UserDTOLombok lombok = UserDTOLombok.builder()
                .username(email)
                .password(password)
                .build();
    }

    public static class RandomUserData {
        FakerGenerator faker = new FakerGenerator();

        public UserDTOLombok lombok = UserDTOLombok.builder()
                .username(faker.generateEmail_Faker())
                .password(password)
                .build();
    }

    public static class UserDataCSV {
        @DataProvider
        public Iterator<Object[]> dataU(Method method) {

            return IO.read(method.getName());
        }
    }

    public static class ContactDataCSV {
        @DataProvider
        public Iterator<Object[]> dataC(Method method) {

            return IO.read(method.getName());
        }
    }
}
