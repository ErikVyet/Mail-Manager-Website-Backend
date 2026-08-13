package code.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import code.enums.UserStatus;
import code.models.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private UUID id;

    @Length(min = 2, max = 100, message = "Username must be within 2 to 100 characters")
    @NotBlank(message = "Username must not be blank or empty")
    private String name;

    @Email(message = "Email must be correct pattern")
    @Length(min = 10, max = 254, message = "Email must be within 10 to 254 characters")
    @NotBlank(message = "Email must not be blank or empty")
    private String email;

    @Column(columnDefinition = "text")
    private String avatar;

    @NotNull(message = "User' status must not be empty")
    private UserStatus status;

    private OffsetDateTime createdAt;

    public UserDto() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static UserDto toDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAvatar(entity.getAvatar());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

}