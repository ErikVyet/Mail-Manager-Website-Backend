package code.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.hibernate.validator.constraints.Length;

import code.models.Billing;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BillingDto implements Serializable {

    private static final long serialVersionUID = 12L;

    private Long id;

    @NotBlank(message = "External customer id can not be empty or blank")
    @Length(max = 255, message = "External customer id can not exceed 255 characters")
    private String externalCustomerId;

    @NotBlank(message = "Payment method label can not be empty or blank")
    @Length(max = 100, message = "Payment method label can not exceed 100 characters")
    private String paymentMethodLabel;

    @NotNull(message = "Default state can not be empty")
    private Boolean isDefault;

    private OffsetDateTime createdAt;

    public BillingDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalCustomerId() {
        return externalCustomerId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        this.externalCustomerId = externalCustomerId;
    }

    public String getPaymentMethodLabel() {
        return paymentMethodLabel;
    }

    public void setPaymentMethodLabel(String paymentMethodLabel) {
        this.paymentMethodLabel = paymentMethodLabel;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static BillingDto toDto(Billing entity) {
        if (entity == null) {
            return null;
        }
        BillingDto dto = new BillingDto();
        dto.setId(entity.getId());
        dto.setExternalCustomerId(entity.getExternalCustomerId());
        dto.setPaymentMethodLabel(entity.getPaymentMethodLabel());
        dto.setIsDefault(entity.getIsDefault());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static Billing toEntity(BillingDto dto) {
        if (dto == null) {
            return null;
        }
        Billing entity = new Billing();
        entity.setId(dto.getId());
        entity.setExternalCustomerId(dto.getExternalCustomerId());
        entity.setPaymentMethodLabel(dto.getPaymentMethodLabel());
        entity.setIsDefault(dto.getIsDefault());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

}