package com.aplus.entity;

import com.aplus.enums.GenderEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 *     会员实体
 *     每一个用户都属于一个会员(Member)
 * </pre>
 * Created by lifang on 2015/1/23.
 */
@Entity
@Table(name = "t_hr_member")
public class MemberEntity extends BaseEntity{

    /**
     * 会员名称
     */
    @Column(length = 32, nullable = false)
    private String name;

    /**
     * 会员编号
     */
    @Column(length = 32, nullable = false)
    private String memberNo;

    /**
     * 邮箱
     */
    @Column(length = 32)
    private String email;

    /**
     * 生日
     */
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * 会员头像
     */
    @Column(length = 100)
    private String face;

    /**
     * 性别
     */
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
