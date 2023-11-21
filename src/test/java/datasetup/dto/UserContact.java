package datasetup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString

public class UserContact {

    UserDTOLombok user;
    List<ContactDTO> contacts;
}
