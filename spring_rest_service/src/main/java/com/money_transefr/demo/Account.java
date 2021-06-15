package com.money_transefr.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Account {
    private int id;
    private BigDecimal money;

//    public Account(int id, BigDecimal money) {
//        this.id = id;
//        this.money = money;
//    }

    public Account(int id, int money) {
        this.id = id;
        this.money = BigDecimal.valueOf(money);
    }

    @JsonCreator
    public Account(@JsonProperty("id") int id, @JsonProperty("money") BigDecimal money){
        this.id = id;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setMoney(int money) {
        this.money = BigDecimal.valueOf(money);
    }

    public BigDecimal getMoney(){
        return money;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (((Account) obj).getId() == id) {
            return true;
        }
        return false;
    }
}
