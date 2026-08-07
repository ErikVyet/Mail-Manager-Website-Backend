package code.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.stripe.Stripe;

import jakarta.annotation.PostConstruct;

@Configuration
public class StripeConfig {
    
    @Value("${stripe.key.secret}")
    protected String secretKey;

    @PostConstruct
    public void initialize() {
        Stripe.apiKey = this.secretKey;
    }

}