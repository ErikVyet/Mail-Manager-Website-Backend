package code.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.hibernate.validator.constraints.Length;

import code.enums.PaymentMethod;
import code.models.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentDto implements Serializable {

    private static final long serialVersionUID = 9L;

    private Integer id;

    @NotBlank(message = "Name can not be empty or blank")
    @Length(max = 100, message = "Name can not exceed 100 characters")
    private String name;

    @NotNull(message = "Method can not be empty")
    private PaymentMethod method;

    @NotNull(message = "State can not be empty")
    private Boolean active;

    private OffsetDateTime createdAt;

    public PaymentDto() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static PaymentDto toDto(Payment entity) {
        if (entity == null) {
            return null;
        }
        PaymentDto dto = new PaymentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMethod(entity.getMethod());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static Payment toEntity(PaymentDto dto) {
        if (dto == null) {
            return null;
        }
        Payment entity = new Payment();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setMethod(dto.getMethod());
        entity.setActive(dto.getActive());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

}