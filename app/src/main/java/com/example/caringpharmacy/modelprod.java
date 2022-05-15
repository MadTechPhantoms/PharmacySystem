package com.example.caringpharmacy;

public class modelprod {
    String prname, primgurl;
    Integer prprice;
    Integer prquantity;

    modelprod(){}

    public modelprod(String prname, String prcateg, String primgurl, Integer prprice,Integer prquantity ) {
        this.prname = prname;
        this.primgurl = primgurl;
        this.prprice = prprice;
        this.prquantity=prquantity;
    }

    public String getPrname()
    {
        return prname;
    }

    public void setPrname(String prname)
    {
        this.prname = prname;
    }

    public String getPrimgurl()
    {
        return primgurl;
    }

    public void setPrimgurl(String primgurl)
    {
        this.primgurl = primgurl;
    }

    public Integer getPrprice()
    {
        return prprice;
    }

    public void setPrprice(Integer prprice)
    {
        this.prprice = prprice;
    }
    public Integer getPrquantity()
    {
        return prquantity;
    }

    public void setPrquantity(Integer prquantity)
    {
        this.prquantity = prquantity;
    }
}
