package uz.abdurahmon.cachespringproject.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
}