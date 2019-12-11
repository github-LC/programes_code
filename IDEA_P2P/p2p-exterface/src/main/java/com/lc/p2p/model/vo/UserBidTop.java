package com.lc.p2p.model.vo;

/**
 * 用户投资排行榜
 */
public class UserBidTop {

    private String phone;
    private Double score;//投资金额

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public UserBidTop() {
    }
}
