package datasetup.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AllContactsDTO {

    ContactDTO[] contacts;
}

