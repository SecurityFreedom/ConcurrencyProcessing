INSERT INTO user(user_id,account,email,id,name,password)
VALUES
    (1,100000000,'test1@test.com','test1','테스트1','password1'),
    (2,100000000,'test2@test.com','test2','테스트2','password2');

INSERT INTO category(category_id,name)
VALUES
    (1,'음식'),
    (2,'전자제품');

INSERT INTO item(item_id,price,name,quantity,category_id)
VALUES
    (1, 9900, '사과',100,1),
    (2,19900,'포도',200,1),
    (3, 965000, '컴퓨터',20,2),
    (4, 432000,'전자레인지',30,2);

INSERT INTO coupon(type, coupon_id, count, name, discount_amount,discount_rate,category_id)
VALUES
    ('F', 1, 5,'추석 맞이 음식 쿠폰', 1500, null, 1),
    ('R', 2, 1,'추석 맞이 전자제품 쿠폰', null, 10, 2);