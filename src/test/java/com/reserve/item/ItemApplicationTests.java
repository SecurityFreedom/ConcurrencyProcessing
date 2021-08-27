package com.reserve.item;

import com.reserve.item.domain.Category;
import com.reserve.item.domain.CouponFixed;
import com.reserve.item.domain.CouponRate;
import com.reserve.item.domain.User;
import com.reserve.item.repository.CategoryRepositorySDJ;
import com.reserve.item.repository.CouponRepositorySDJ;
import com.reserve.item.repository.UserRepositorySDJ;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class ItemApplicationTests {

	@Autowired
	UserRepositorySDJ userRepo;
	@Autowired
	CouponRepositorySDJ couponRepo;
	@Autowired
	CategoryRepositorySDJ categoryRepo;


	@Test
	void insertValues() {
		// 딱 한번만 실행. 테이블에 데이터 넣기 위한 코드. 롤백이 false라서 데이터 들어있는 상태로 유지됨
		// 대신 ddl-auto 설정은 이거 실행후에 none으로 설정

		User user1 = User.createUser("id1", "강윤식", "1234", "sik@naver.com");
		User user2 = User.createUser("idid2", "이승환", "4321", "nlsh@naver.com");
		User user3 = User.createUser("idd3", "성원영", "3412", "10@naver.com");

		Category category = new Category();
		category.setName("티켓");

		CouponFixed cf = CouponFixed.CreateCoupon("추석 쿠폰", category, 1000, 10);
		CouponRate cr = CouponRate.CreateCoupon("가을 쿠폰", category, 10, 10);

		userRepo.save(user1);
		userRepo.save(user2);
		userRepo.save(user3);

		categoryRepo.save(category);

		couponRepo.save(cf);
		couponRepo.save(cr);
	}

}

