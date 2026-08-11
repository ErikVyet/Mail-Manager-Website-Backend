package code.metadata;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Attachment {

    private UUID id;

    @NotBlank(message = "Attachment file's name must not be empty or blank")
    private String name;

    @NotBlank(message = "Attachment file's content type can not be empty or blank")
    private String contentType;

    @NotNull(message = "Attachment file' size must not be empty")
    @Max(value = 20, message = "Attachment file' size must be less or equals to 20 MB")
    private Long size;
    
    private String url;

    public Attachment() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}