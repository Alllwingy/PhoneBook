package datasetup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class ContactDTO {

    String id; // always 0, needs for API
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;
}
