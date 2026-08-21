package code.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.hibernate.validator.constraints.Length;

import code.models.Api;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApiDto implements Serializable  {
    
    private static final long serialVersionUID = 8L;

    private Long id;

    @NotBlank(message = "Require API key")
    @Length(min = 256, max = 256, message = "Invalid API key structure")
    private String key;

    @NotNull(message = "Require initial call counts")
    private Long callCounts;

    @NotNull(message = "Required creation timestamp of API key")
    private OffsetDateTime createdAt;

    public ApiDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getCallCounts() {
        return callCounts;
    }

    public void setCallCounts(Long callCounts) {
        this.callCounts = callCounts;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static ApiDto toDto(Api entity) {
        if (entity == null) {
            return null;
        }
        ApiDto dto = new ApiDto();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setCallCounts(entity.getCallCounts());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static Api toEntity(ApiDto dto) {
        if (dto == null) {
            return null;
        }
        Api entity = new Api();
        entity.setId(dto.getId());
        entity.setKey(dto.getKey());
        entity.setCallCounts(dto.getCallCounts());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

}