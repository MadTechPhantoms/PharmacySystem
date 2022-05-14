package com.example.caringpharmacy;

public class Product {
    private String prid;
    private String prcateg;
    private String prname;
    private Integer prprice;
    private String primgurl;

    public Product() {}

    public Product(String prid, String prcateg, String prname, Integer prprice, String primgurl) {
        this.prid = prid;
        this.prname = prname;
        this.prprice = prprice;
        this.prcateg = prcateg;
        this.primgurl = primgurl;
    }

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    public String getPrcateg() {
        return prcateg;
    }

    public void setPrcateg(String prcateg) {
        this.prcateg = prcateg;
    }

    public String getPrname() {
        return prname;
    }

    public void setPrname(String prname) {
        this.prname = prname;
    }

    public Integer getPrprice() {
        return prprice;
    }

    public void setPrprice(Integer prprice) {
        this.prprice = prprice;
    }

    public String getPrimgurl() {
        return primgurl;
    }

    public void setPrimgurl(String primgurl) {
        this.primgurl = primgurl;
    }
}
