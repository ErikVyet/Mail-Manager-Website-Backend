package code.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import code.models.Thread;

public class ThreadDto implements Serializable {
    
    private static final long serialVersionUID = 7L;

    @NotNull(message = "Thread's ID can not be null")
    private String id;

    @NotNull(message = "Thread's user's ID can not be null")
    private String userId;

    @NotBlank(message = "Thread' subject can not be empty or blank")
    private String subject;

    @NotBlank(message = "Thread' snippet can not be empty or blank")
    @Length(max = 150, message = "Thread' snippet can not exceed 150 characters")
    private String snippet;

    @NotNull(message = "Thread's last message timestamp can not be null")
    private OffsetDateTime lastMessageAt;

    @Size(max = 150, message = "The number of participants must not exceed 150")
    @NotEmpty(message = "The number of participants must not be empty")
    private List<String> participants;

    @NotEmpty(message = "The number of labels must not be empty")
    private List<Long> labelIds;

    public ThreadDto() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public OffsetDateTime getLastMessageAt() {
        return lastMessageAt;
    }

    public void setLastMessageAt(OffsetDateTime lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<Long> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<Long> labelIds) {
        this.labelIds = labelIds;
    }

    public static ThreadDto toDto(Thread document) {
        if (document == null) {
            return null;
        }
        ThreadDto dto = new ThreadDto();
        dto.setId(document.getId());
        dto.setUserId(document.getUserId());
        dto.setSubject(document.getSubject());
        dto.setSnippet(document.getSnippet());
        dto.setLastMessageAt(document.getLastMessageAt());
        dto.setParticipants(document.getParticipants());
        dto.setLabelIds(document.getLabelIds());
        return dto;
    }

    public static Thread toDocument(ThreadDto dto) {
        if (dto == null) {
            return null;
        }
        Thread document = new Thread();
        document.setId(dto.getId());
        document.setUserId(dto.getUserId());
        document.setSubject(dto.getSubject());
        document.setSnippet(dto.getSnippet());
        document.setLastMessageAt(dto.getLastMessageAt());
        document.setParticipants(dto.getParticipants());
        document.setLabelIds(dto.getLabelIds());
        return document;
    }

}