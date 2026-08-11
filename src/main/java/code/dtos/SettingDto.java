package code.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import code.models.Setting;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SettingDto implements Serializable {
    
    private static final long serialVersionUID = 4L;

    @NotNull(message = "Id must not be empty")
    private Long id;

    @Length(min = 256, max = 256, message = "Signature must be 256 characters")
    @NotBlank(message = "Signature must not be empty or blank")
    private String signature;

    public SettingDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public static SettingDto toDto(Setting entity) {
        if (entity == null) {
            return null;
        }
        SettingDto dto = new SettingDto();
        dto.setId(entity.getId());
        dto.setSignature(entity.getSignature());
        return dto;
    }

    public static Setting toEntity(SettingDto dto) {
        if (dto == null) {
            return null;
        }
        Setting entity = new Setting();
        entity.setId(dto.getId());
        entity.setSignature(dto.getSignature());
        return entity;
    }

}