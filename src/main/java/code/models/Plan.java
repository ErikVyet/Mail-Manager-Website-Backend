package code.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import code.enums.BillingInterval;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 10, unique = true, nullable = false)
    private String name;

    @JdbcTypeCode(SqlTypes.NUMERIC)
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "billing_interval", nullable = false)
    private BillingInterval billingInterval;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "max_api_keys", nullable = false)
    private Integer maxApiKeys;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "max_api_calls", nullable = false)
    private Long maxApiCalls;

    @JdbcTypeCode(SqlTypes.BOOLEAN)
    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;

    public Plan() { }

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

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

}