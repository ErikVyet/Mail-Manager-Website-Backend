package code.metadata;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserInfo {

    @NotBlank(message = "Display name can not be empty or blank")
    @Length(max = 100, message = "Contact's display name can not exceed 100 characters")
    private String name;

    @Email(message = "Email must be correct pattern")
    @NotBlank(message = "Email can not be empty or blank")
    private String email;

    public UserInfo() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}