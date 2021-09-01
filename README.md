# A Project For Practicing Concurrency Processing

------------------------------------

### Index

1. Description
2. Environments
3. Dependency
4. Prerequisite
5. Usage
6. Service
7. Repository
8. Class Dependency
9. DB schema - Table

------------------------------------

### 1. Description

`Abstract`

Assume a virtual case in which users receive coupons and order products by inquiring a product page. Users can be issued
the restricted number of coupons, and more coupons must not be issued. In addition, when traffic increases for an order
request exceeding the remaining quantity of the product, our processing system is aimed not to exceed the remaining
quantity.

So, we would like to proceed with a project that solves these concurrency problems.

`Objective 1 : Per person`

A system that must receive a set number of coupons per person

`Objective 2 : Traffic error handling`

In case of heavy traffic, a system


------------------------------------

### 2. Environments

Spring : 2.5.4 SpringBoot

DB : MySQL 8.0.*

Java : 11

OS : dev(Window), release(Ubuntu 18.04 LTS)




------------------------------------

### 3. Dependency

`react`

axios: ^0.21.1

react: ^17.0.2

react-dom: ^17.0.2

react-router-dom: ^5.2.0

react-scripts: 4.0.3

sass: ^1.38.1

web-vitals: ^1.1.2

webpack: ^4.44.2

webpack-cli: ^4.8.0

webpack-dev-server: ^3.11.1

`spring compile & runtime`

junit:^4.13.1

mysql:8.0.^

lombok:1.18.18

spring-boot-starter-jpa:2.5.4

spring-boot-starter-thymeleaf:2.5.4

spring-boot-starter-web:2.5.4

`spring runtime`

spring-boot-devtools:2.5.4

------------------------------------

### 4. Prerequisite

+ ./resources/application.properties

```
spring.datasource.url=jdbc:mysql://localhost:${DATABASE_PORT}/${DATABASE_NAME}?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul
spring.datasource.username=${DATABASE_ID}
spring.datasource.password=${DATABASE_PASSWORD}
spring.jpa.show-sql=true
```

+ ./resources/application.yml

```
server:
  address: localhost
  port: 8080

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:${DATABASE_PORT}/${DATABASE_NAME}?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul
    username: ${DATABASE_ID}
    password: ${DATABASE_PASSWORD}
  jpa:
    database: mysql
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    generate-ddl: true
    show-sql: true
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        format_sql: true
```

------------------------------------

### 5. Usage

+ Frontend

```
cd front
npm install
npm start
```

+ Backend (window) build

```
./gradlew.bat { build | clean build }
cd ./build/libs
java -jar ./${SNAPSHOT}.jar

<RUN JAR FILE>
```

+ Backend (linux) build

```
./gradlew { build | clean build }
cd ./build/libs
java -jar ./${SNAPSHOT}.jar

<RUN JAR FILE>
```

------------------------------------

### 6. Design - Service

+ `UserService`
  + void user(User)
  + JWT login(User.id, User.pw)
  + boolean editUser(User)

+ `ItemService`
  + List<Item> getItems()

+ `OrderService`
  + Optional<Orders> createOrder(User,Item,Coupon)
  + boolean verifyOrder(Order)
  + AcceptOrder(Order)

+ `CouponService`
  + List<Coupon> getCouponInfo()
  + boolean getCoupon(User,Coupon)

------------------------------

### 7. Design - Repository

Repositories are based on Spring Data JPA

+ UserRepository
+ ItemRepository
+ OrderRepository
+ CouponRepository
+ CouponStateRepository

-----------------------------

### 8. Design - Class Dependency

> UserService
> > --->UserRepository
>
> ItemService
> > --->ItemRepository
>
> OrderService
> > --->OrderRepository
>
> > --->CouponStateRepository
>
> CouponService
> > --->CouponRepository
>
> > --->CouponStateRepository

-----------------------------

### 9. Design - DB schema - Table

Table `User`

+ (pk)
+ id
+ name
+ password
+ email
  > If user forget his password, he can retrieve it using this email.
+ account
  > user's account (money)

------------------------------------

Table `Category`

+ (pk)
+ name

------------------------------------

Table `Coupon`

According to the discount policy, the coupon table is divided into two concrete classes.

+ (pk)
+ category(fk)
+ name
+ count
  > This indicates maximum issue-able number per user
+ discountAmount / discountRate
  > This number refers to a quantitative or rate discount value.
------------------------------------

Table `CouponState`

+ (pk)
+ Coupon(fk)
+ User(fk)
+ current_amount
  > Literally, the current number the user has.
+ issued_amount
  > This indicates how many coupons the user has issued.

------------------------------------

Table `Item`

+ (pk)
+ category(fk)
+ quantity
  > Amount currently available for purchase
+ price
  > Full price

------------------------------------

Table `Order`

+ (pk)
+ User(fk)
+ Item(fk)
+ Coupon(fk)
  > Foreign key's first Index is mean that Not using any coupon.

### 10. Entity - Methods


Table `User`

+ public static User createUser(String id, String name, String password, String email)



+ public void setAccount(Long account)


------------------------------------

Table `Coupon`

+ public abstract void changeDiscount(int amount);

+ public abstract int getDiscountValue(int itemPrice);

------------------------------------

Table `CouponState`

+ public static CouponState issueNewCoupon(User user, Coupon coupon)

+ public void issueCoupon()

+ public void refundCoupon()

+ public void useCoupon()

------------------------------------

Table `Item`

+ public static Item createItem(Category category, int quantity, int price)

+ public void sell()

------------------------------------
