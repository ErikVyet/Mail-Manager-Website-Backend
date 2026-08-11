package code.models;

import java.util.List;

import code.metadata.UserInfo;

public class Recipients {

    private List<UserInfo> to;
    private List<UserInfo> cc;
    private List<UserInfo> bcc;
    
    public Recipients() { }

    public Recipients(List<UserInfo> to, List<UserInfo> cc, List<UserInfo> bcc) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
    }

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