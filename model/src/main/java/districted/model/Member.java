package districted.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "member", schema = "dbo")
public class Member {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(nullable = false)
    @Size(max = 255)
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email address format")
    private String email;

    @Column(name = "phone_number")
    @Size(max = 20)
    private String phoneNumber;

    @Column(nullable = false)
    @Size(max = 255)
    private String password;

    @Column(nullable = false)
    @Size(max = 100, message = "Username must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
    @NotBlank(message = "Username cannot be blank")
    private String username;
}
