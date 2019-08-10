package com.Ferret56.FlowerShopEE.be.entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;


@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @NotEmpty
    @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    @Column(name = "email")
    private String email;
    @NotNull
    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})")
    @Column(name = "phone")
    private String phone;
    @NotNull
    @NotEmpty
    @Column(name = "money")
    private BigDecimal money;
    @NotNull
    @NotEmpty
    @Column(name = "discount")
    private int discount;
    @NotNull
    @NotEmpty
    @Column(name = "role")
    private UserRoles role;


    public User(String username, String password,
                String email,String phone,
                BigDecimal money,int discount,
                UserRoles role) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.money = money;
        this.discount = discount;
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                ", discount=" + discount +
                ", role=" + role +
                '}';
    }

}
