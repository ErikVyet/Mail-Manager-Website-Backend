package code.configs;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class BackblazeConfig {
    
    @Value("${backblaze.bucket.name}")
    protected String bucketName;

    @Value("${backblaze.bucket.endpoint}")
    protected String endpoint;

    @Value("${backblaze.key.id}")
    protected String keyId;

    @Value("${backblaze.key.value}")
    protected String applicationKey;

    @Bean
    public S3Client s3Client() {
        S3Client client = S3Client.builder()
            .endpointOverride(URI.create(this.endpoint))
            .region(Region.of("us-east-005"))
            .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create(this.keyId, this.applicationKey)
            ))
            .build();
        return client;
    }

}