package uz.abdurahmon.cachespringproject.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime when;
}