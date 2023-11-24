package datasetup;

import com.github.javafaker.Faker;
import datasetup.dto.ContactDTO;
import datasetup.dto.UserDTO;
import datasetup.dto.UserDTOLombok;
import datasetup.dto.UserDTOWith;
import datasetup.io.IO;
import org.testng.annotations.DataProvider;
import utils.ConfigurationProperties;
import utils.RandomGenerator;

import java.lang.reflect.Method;
import java.util.Iterator;

public class Data {

    static String email = ConfigurationProperties.getProperty("email");
    static String password = ConfigurationProperties.getProperty("password");
    static Faker faker = new Faker();


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

        public UserDTOLombok lombok = UserDTOLombok.builder()
                .username(faker.internet().emailAddress())
                .password(password)
                .build();
    }

    public static class RandomContactData {
        RandomGenerator random = new RandomGenerator();

        public ContactDTO contact = ContactDTO.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(random.generateStringDigits(12))
                .address(faker.address().fullAddress())
                .description(faker.demographic().educationalAttainment())
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
