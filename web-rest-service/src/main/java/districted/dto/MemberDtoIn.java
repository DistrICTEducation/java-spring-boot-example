package districted.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@EqualsAndHashCode
public class MemberDtoIn {
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String username;
}
