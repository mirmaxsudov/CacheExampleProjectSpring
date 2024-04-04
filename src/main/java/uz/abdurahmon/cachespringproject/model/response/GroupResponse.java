package uz.abdurahmon.cachespringproject.model.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class GroupResponse {
    private UUID id;
    private String groupName;
    private String groupDescription;
    private LocalDateTime createAt;

}
