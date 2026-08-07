package code.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.resend.Resend;

@Configuration
public class ResendConfig {

    @Value("${resend.key}")
    protected String key;
    
    @Bean
    protected Resend resend() {
        return new Resend(key);
    }

}