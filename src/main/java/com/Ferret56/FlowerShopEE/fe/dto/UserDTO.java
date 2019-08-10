package com.Ferret56.FlowerShopEE.fe.dto;

import com.Ferret56.FlowerShopEE.be.entity.UserRoles;

import java.math.BigDecimal;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String confirm_password;
    private String email;
    private String phone;
    private BigDecimal money;
    private int discount;
    private UserRoles role;

    public UserDTO() {}

    public UserDTO(Long id, String username, String password,
                   String confirm_password,String email,
                   String phone,BigDecimal money, int discount,
                   UserRoles role) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.confirm_password = confirm_password;
        this.email = email;
        this.phone = phone;
        this.money = money;
        this.discount = discount;
        this.role = role;
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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
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

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirm_password='" + confirm_password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                ", discount=" + discount +
                ", role=" + role +
                '}';
    }
}
