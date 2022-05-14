package com.example.caringpharmacy;

public class ContactUsData {

    String cname;
    String Cphone;
    String cemail;
    String cmessage;

    public ContactUsData(){}
    public ContactUsData(String cname, String cphone, String cemail, String cmessage) {
        this.cname = cname;
        Cphone = cphone;
        this.cemail = cemail;
        this.cmessage = cmessage;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return Cphone;
    }

    public void setCphone(String cphone) {
        Cphone = cphone;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCmessage() {
        return cmessage;
    }

    public void setCmessage(String cmessage) {
        this.cmessage = cmessage;
    }
}

