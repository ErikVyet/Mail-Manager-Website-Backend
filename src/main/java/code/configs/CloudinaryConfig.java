package code.configs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.name}")
    protected String name;

    @Value("${cloudinary.key}")
    protected String key;

    @Value("${cloudinary.secret}")
    protected String secret;

    @Bean
    protected Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(
            Map.of(
                "cloud_name", this.name,
                "api_key", this.key,
                "api_secret", this.secret,
                "secure", true
            )
        );
        return cloudinary;
    }

}