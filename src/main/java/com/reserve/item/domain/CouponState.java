package com.reserve.item.domain;

import com.reserve.item.domain.exceptions.NotEnoughCouponException;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CouponState {
    @Id @GeneratedValue
    @Column(name="USER_COUPON_ID")
    private long pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COUPON_ID")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    //발급받은 횟수
    private int issuedAmount;

    //현재 들고있는 갯수
    private int currentAmount;


    /**
    * 새 쿠폰 발행
    * */
    public static CouponState issueNewCoupon(User user, Coupon coupon) {
        CouponState c = new CouponState();
        c.coupon = coupon;
        c.user = user;
        c.issuedAmount = 1;
        c.currentAmount = 1;
        return c;
    }

    //이미 있는 쿠폰 하나 더 발행
    public void issueCoupon() {
        this.currentAmount += 1;
        this.issuedAmount += 1;
    }

    public void refundCoupon() {
        this.currentAmount += 1;
    }

    public void useCoupon() throws NotEnoughCouponException {
        if(this.currentAmount == 0) throw new NotEnoughCouponException("This user does not have a coupon");
        this.currentAmount -= 1;
    }
}
