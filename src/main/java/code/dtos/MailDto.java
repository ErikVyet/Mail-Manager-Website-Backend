package code.dtos;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import code.metadata.Attachment;
import code.metadata.Recipients;
import code.metadata.UserInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MailDto implements Serializable {

    private static final long serialVersionUID = 6L;
    
    private String id;

    @NotBlank(message = "User's ID must not be empty or blank")
    private String userId;

    @NotBlank(message = "Thread's ID must not be empty or blank")
    private String threadId;

    @NotNull(message = "Sender's info must not be empty")
    @Valid
    private UserInfo sender;

    @NotNull(message = "Recipients must not be empty")
    @Valid
    private Recipients recipients;

    @NotBlank(message = "Subject must not be empty or blank")
    @Length(max = 50, message = "Subject must not exceed 50 characters")
    private String subject;

    private String html;

    private String text;

    @NotEmpty(message = "The number of labels must not be empty")
    private List<Long> labelIds;

    @NotNull(message = "Read status must not be empty")
    private Boolean isRead;

    @NotNull(message = "Starred status must not be empty")
    private Boolean isStarred;

    @NotNull(message = "Attachments must not be null")
    private List<Attachment> attachments;

    @NotNull(message = "Creation timestamp can not be null")
    private OffsetDateTime createdAt;

    public MailDto() { }

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

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo sender) {
        this.sender = sender;
    }

    public Recipients getRecipients() {
        return recipients;
    }

    public void setRecipients(Recipients recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Long> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<Long> labelIds) {
        this.labelIds = labelIds;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsStarred() {
        return isStarred;
    }

    public void setIsStarred(Boolean isStarred) {
        this.isStarred = isStarred;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    

}