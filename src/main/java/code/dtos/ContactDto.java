package code.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import code.models.Contact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactDto implements Serializable {
    
    private static final long serialVersionUID = 3L;

    private Long id;

    @Email(message = "Email must be correct pattern")
    @NotBlank(message = "Email must not be empty or blank")
    @Length(max = 254, message = "Email must be less than 255 characters")
    private String email;

    @Length(max = 100, message = "Display name must be equals or less than 100 characters")
    @NotBlank(message = "Display name must not be empty or blank")
    private String name;

    public ContactDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ContactDto toDto(Contact entity) {
        if (entity == null) {
            return null;
        }
        ContactDto dto = new ContactDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        return dto;
    }

    public static Contact toEntity(ContactDto dto) {
        if (dto == null) {
            return null;
        }
        Contact entity = new Contact();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        return entity;
    }

}