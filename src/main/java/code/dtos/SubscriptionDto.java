package code.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;

import code.enums.SubscriptionStatus;
import code.models.Subscription;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class SubscriptionDto implements Serializable {

    private static final long serialVersionUID = 11L;

    private Long id;

    @NotNull(message = "Status can not be empty")
    private SubscriptionStatus status;

    @NotNull(message = "Start timestamp can not be empty")
    @FutureOrPresent(message = "Start timestamp can not be in the past")
    private OffsetDateTime startAt;

    @NotNull(message = "End timestamp can not be empty")
    @Future(message = "End timestamp can not be in the past or present")
    private OffsetDateTime endAt;

    @NotNull(message = "Auto renew can not be empty")
    private Boolean autoRenew;

    private OffsetDateTime createdAt;

    public SubscriptionDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public OffsetDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(OffsetDateTime startAt) {
        this.startAt = startAt;
    }

    public OffsetDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(OffsetDateTime endAt) {
        this.endAt = endAt;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static SubscriptionDto toDto(Subscription entity) {
        if (entity == null) {
            return null;
        }
        SubscriptionDto dto = new SubscriptionDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setStartAt(entity.getStartAt());
        dto.setEndAt(entity.getEndAt());
        dto.setAutoRenew(entity.getAutoRenew());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static Subscription toEntity(SubscriptionDto dto) {
        if (dto == null) {
            return null;
        }
        Subscription entity = new Subscription();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        entity.setStartAt(dto.getStartAt());
        entity.setEndAt(dto.getEndAt());
        entity.setAutoRenew(dto.getAutoRenew());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

}