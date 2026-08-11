package code.metadata;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Recipients {

    @Size(max = 50, message = "Can only have 50 TO recipients")
    @NotNull(message = "TO recipients can not be null")
    private List<UserInfo> to;

    @Size(max = 50, message = "Can only have 50 CC recipients")
    @NotNull(message = "CC recipients can not be null")
    private List<UserInfo> cc;

    @Size(max = 50, message = "Can only have 50 BCC recipients")
    @NotNull(message = "BCC recipients can not be null")
    private List<UserInfo> bcc;
    
    public Recipients() { }

    public List<UserInfo> getTo() {
        return to;
    }

    public void setTo(List<UserInfo> to) {
        this.to = to;
    }

    public List<UserInfo> getCc() {
        return cc;
    }

    public void setCc(List<UserInfo> cc) {
        this.cc = cc;
    }

    public List<UserInfo> getBcc() {
        return bcc;
    }

    public void setBcc(List<UserInfo> bcc) {
        this.bcc = bcc;
    }

}