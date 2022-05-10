package com.example.caringpharmacy;

public class modelprod {
    String prname, prcateg, primgurl;
    Integer prprice;

    modelprod(){}

    public modelprod(String prname, String prcateg, String primgurl, Integer prprice) {
        this.prname = prname;
        this.prcateg = prcateg;
        this.primgurl = primgurl;
        this.prprice = prprice;
    }

    public String getPrname() {
        return prname;
    }

    public void setPrname(String prname) {
        this.prname = prname;
    }

    public String getPrcateg() {
        return prcateg;
    }

    public void setPrcateg(String prcateg) {
        this.prcateg = prcateg;
    }

    public String getPrimgurl() {
        return primgurl;
    }

    public void setPrimgurl(String primgurl) {
        this.primgurl = primgurl;
    }

    public Integer getPrprice() {
        return prprice;
    }

    public void setPrprice(Integer prprice) {
        this.prprice = prprice;
    }
}
