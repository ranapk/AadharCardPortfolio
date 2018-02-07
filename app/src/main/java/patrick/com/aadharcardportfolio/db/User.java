package patrick.com.aadharcardportfolio.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;

@Entity
public class User {

    public User(String aadhar_no, String name, String dob, String postal, String createDate, byte[] img1, byte[] img2) {

        this.aadhar_no = aadhar_no;

        this.name = name;

        this.dob = dob;

        this.postal = postal;

        this.createDate= createDate;

        this.img1 = img1;

        this.img2 = img2;

    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "aadhar_m")
    public String aadhar_no;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "postal")
    public String postal;

    @ColumnInfo(name = "createDate")
    public String createDate;

    @ColumnInfo(name = "img1")
    public byte[] img1;

    @ColumnInfo(name = "img2")
    public byte[] img2;

    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img2) {
        this.img2 = img2;
    }
}