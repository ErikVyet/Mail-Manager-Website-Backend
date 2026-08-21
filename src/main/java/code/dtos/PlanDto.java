package code.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import code.enums.BillingInterval;
import code.models.Plan;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlanDto implements Serializable {
    
    private static final long serialVersionUID = 10L;
    
    private Integer id;

    @NotBlank(message = "Unique name can not be empty or blank")
    @Length(max = 10, message = "Plan's unique name can not exceed 10 characters")
    private String name;

    @NotNull(message = "Price can not be empty")
    @Range(min = 0, max = 1000, message = "Plan's price range can not exceed the range of 0$ and 1000$")
    private BigDecimal price;

    @NotNull(message = "Billing interval can not be empty")
    private BillingInterval billingInterval;

    @NotNull(message = "Max API keys can not be empty")
    @Range(min = 5, max = 100, message = "Max API keys can not exceed the range of 5 and 100 keys")
    private Integer maxApiKeys;

    @NotNull(message = "Max API calls can not be empty")
    @Min(value = 100, message = "Max API calls can not be below 100 calls")
    private Long maxApiCalls;

    @NotNull(message = "State can not be empty")
    private Boolean active;

    private OffsetDateTime createdAt;

    public PlanDto() { }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BillingInterval getBillingInterval() {
        return billingInterval;
    }

    public void setBillingInterval(BillingInterval billingInterval) {
        this.billingInterval = billingInterval;
    }

    public Integer getMaxApiKeys() {
        return maxApiKeys;
    }

    public void setMaxApiKeys(Integer maxApiKeys) {
        this.maxApiKeys = maxApiKeys;
    }

    public Long getMaxApiCalls() {
        return maxApiCalls;
    }

    public void setMaxApiCalls(Long maxApiCalls) {
        this.maxApiCalls = maxApiCalls;
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

    public static PlanDto toDto(Plan entity) {
        if (entity == null) {
            return null;
        }
        PlanDto dto = new PlanDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setBillingInterval(entity.getBillingInterval());
        dto.setMaxApiKeys(entity.getMaxApiKeys());
        dto.setMaxApiCalls(entity.getMaxApiCalls());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static Plan toEntity(PlanDto dto) {
        if (dto == null) {
            return null;
        }
        Plan entity = new Plan();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setBillingInterval(dto.getBillingInterval());
        entity.setMaxApiKeys(dto.getMaxApiKeys());
        entity.setMaxApiCalls(dto.getMaxApiCalls());
        entity.setActive(dto.getActive());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

}