package uz.abdurahmon.cachespringproject.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class GroupResponse {
    @JsonSerialize(using = UUIDSerializer.class)
    private UUID id;
    private String groupName;
    private String groupDescription;
    private LocalDateTime createAt;

}
