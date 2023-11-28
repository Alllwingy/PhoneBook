package datasetup.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ContactDTO {

    String id; // always 0, needs for API
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;
}
