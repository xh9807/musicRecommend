package com.hfut.mydesign.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "账号不能为空！")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "密码不能为空")
    @Column(name = "password")
    private String password;

    @Column(name = "qq")
    private String QQ;

    public User() {
    }

    public User(@NotBlank(message = "账号不能为空！") String name, @NotBlank(message = "密码不能为空") String password, String QQ) {
        this.name = name;
        this.password = password;
        this.QQ = QQ;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", QQ='" + QQ + '\'' +
                '}';
    }
}
