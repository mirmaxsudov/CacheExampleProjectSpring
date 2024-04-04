package uz.abdurahmon.cachespringproject.model.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
}