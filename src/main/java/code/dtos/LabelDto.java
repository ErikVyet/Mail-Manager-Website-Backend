package code.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import code.enums.LabelType;
import code.models.Label;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class LabelDto implements Serializable {

    private static final long serialVersionUID = 2L;

    private Long id;

    @Length(min = 1, max = 20, message = "Label's display name must be within 1 to 20 characters")
    @NotBlank(message = "Label's display name must not be empty or blank")
    private String name;

    @Pattern(regexp = "^[0-9][a-f][A-F]{7}$", message = "Color code must be 7 hex characters")
    private String color;

    @NotNull(message = "Label's type must not be empty")
    private LabelType type;

    public LabelDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LabelType getType() {
        return type;
    }

    public void setType(LabelType type) {
        this.type = type;
    }

    public static LabelDto toDto(Label entity) {
        if (entity == null) {
            return null;
        }
        LabelDto dto = new LabelDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setColor(entity.getColor());
        dto.setType(entity.getType());
        return dto;
    }

    public static Label toEntity(LabelDto dto) {
        if (dto == null) {
            return null;
        }
        Label entity = new Label();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setColor(dto.getColor());
        entity.setType(dto.getType());
        return entity;
    }

}