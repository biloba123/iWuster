package com.lvqingyang.iwuster.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 *已选课程详情信息
 * @author Lv Qingyang
 * @date 2018/1/13
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class Course extends DataSupport implements Parcelable {

    /**
     * 单位名称
     * dwmc : 理学院
     *
     * 开课时间明细
     * kksjmx : ,401,402,,,201,202,
     *
     * 教室名称
     * jsmc : 恒大楼三区104,恒大楼三区104
     *
     * jx02id : 05928
     *
     * 课程性质名称
     * kcxzmc : 学科基础平台课程
     *
     * jcmc : null
     *
     * sfxyjc : null
     *
     * 课程时间
     * kcsj : 40102,20102
     *
     * 课堂名称/教学班
     * ktmc : 教学班1659
     *
     * 课程属性名
     * kcsxm : 必修
     *
     * jx0501id : 35D92FE9F5CF4DB7E0531701D20A696B
     *
     * 教师姓名
     * jsxm : 刘春梅
     *
     * kcxzm : 15
     *
     * szkcfl : null
     *
     * 选课阶段
     * xkjd : 一选
     *
     * kch : 0703606
     *
     * 总学时
     * zxs : 24
     *
     * kcsx : 1
     *
     * 开课周次
     * kkzc : 1-6,1-6
     *
     * 开课周次明细
     * kkzcmx : ,1,2,3,4,5,6,,,1,2,3,4,5,6,
     *
     * 课程名称
     * kcmc : 大学物理B(二)
     *
     * 学分
     * xf : 1.5
     *
     * jx0404id : 201620171001265
     *
     * xs0101id : 201513137125
     */

    private String dwmc;
    private String kksjmx;
    private String jsmc;
    private String jx02id;
    private String kcxzmc;
    private String jcmc;
    private String sfxyjc;
    private String kcsj;
    private String ktmc;
    private String kcsxm;
    private String jx0501id;
    private String jsxm;
    private String kcxzm;
    private String szkcfl;
    private String xkjd;
    private String kch;
    private float zxs;
    private String kcsx;
    private String kkzc;
    private String kkzcmx;
    private String kcmc;
    private float xf;
    private String jx0404id;
    private String xs0101id;
    private List<CourseLite> courseLites;


    @Override
    public String toString() {
        return "Course{" +
                "dwmc='" + dwmc + '\'' +
                ", kksjmx='" + kksjmx + '\'' +
                ", jsmc='" + jsmc + '\'' +
                ", jx02id='" + jx02id + '\'' +
                ", kcxzmc='" + kcxzmc + '\'' +
                ", jcmc='" + jcmc + '\'' +
                ", sfxyjc='" + sfxyjc + '\'' +
                ", kcsj='" + kcsj + '\'' +
                ", ktmc='" + ktmc + '\'' +
                ", kcsxm='" + kcsxm + '\'' +
                ", jx0501id='" + jx0501id + '\'' +
                ", jsxm='" + jsxm + '\'' +
                ", kcxzm='" + kcxzm + '\'' +
                ", szkcfl='" + szkcfl + '\'' +
                ", xkjd='" + xkjd + '\'' +
                ", kch='" + kch + '\'' +
                ", zxs=" + zxs +
                ", kcsx='" + kcsx + '\'' +
                ", kkzc='" + kkzc + '\'' +
                ", kkzcmx='" + kkzcmx + '\'' +
                ", kcmc='" + kcmc + '\'' +
                ", xf=" + xf +
                ", jx0404id='" + jx0404id + '\'' +
                ", xs0101id='" + xs0101id + '\'' +
                ", courseLites=" + courseLites +
                '}';
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getKksjmx() {
        return kksjmx;
    }

    public void setKksjmx(String kksjmx) {
        this.kksjmx = kksjmx;
    }

    public String getJsmc() {
        return jsmc;
    }

    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }

    public String getJx02id() {
        return jx02id;
    }

    public void setJx02id(String jx02id) {
        this.jx02id = jx02id;
    }

    public String getKcxzmc() {
        return kcxzmc;
    }

    public void setKcxzmc(String kcxzmc) {
        this.kcxzmc = kcxzmc;
    }

    public String getJcmc() {
        return jcmc;
    }

    public void setJcmc(String jcmc) {
        this.jcmc = jcmc;
    }

    public String getSfxyjc() {
        return sfxyjc;
    }

    public void setSfxyjc(String sfxyjc) {
        this.sfxyjc = sfxyjc;
    }

    public String getKcsj() {
        return kcsj;
    }

    public void setKcsj(String kcsj) {
        this.kcsj = kcsj;
    }

    public String getKtmc() {
        return ktmc;
    }

    public void setKtmc(String ktmc) {
        this.ktmc = ktmc;
    }

    public String getKcsxm() {
        return kcsxm;
    }

    public void setKcsxm(String kcsxm) {
        this.kcsxm = kcsxm;
    }

    public String getJx0501id() {
        return jx0501id;
    }

    public void setJx0501id(String jx0501id) {
        this.jx0501id = jx0501id;
    }

    public String getJsxm() {
        return jsxm;
    }

    public void setJsxm(String jsxm) {
        this.jsxm = jsxm;
    }

    public String getKcxzm() {
        return kcxzm;
    }

    public void setKcxzm(String kcxzm) {
        this.kcxzm = kcxzm;
    }

    public String getSzkcfl() {
        return szkcfl;
    }

    public void setSzkcfl(String szkcfl) {
        this.szkcfl = szkcfl;
    }

    public String getXkjd() {
        return xkjd;
    }

    public void setXkjd(String xkjd) {
        this.xkjd = xkjd;
    }

    public String getKch() {
        return kch;
    }

    public void setKch(String kch) {
        this.kch = kch;
    }

    public float getZxs() {
        return zxs;
    }

    public void setZxs(float zxs) {
        this.zxs = zxs;
    }

    public String getKcsx() {
        return kcsx;
    }

    public void setKcsx(String kcsx) {
        this.kcsx = kcsx;
    }

    public String getKkzc() {
        return kkzc;
    }

    public void setKkzc(String kkzc) {
        this.kkzc = kkzc;
    }

    public String getKkzcmx() {
        return kkzcmx;
    }

    public void setKkzcmx(String kkzcmx) {
        this.kkzcmx = kkzcmx;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public float getXf() {
        return xf;
    }

    public void setXf(float xf) {
        this.xf = xf;
    }

    public String getJx0404id() {
        return jx0404id;
    }

    public void setJx0404id(String jx0404id) {
        this.jx0404id = jx0404id;
    }

    public String getXs0101id() {
        return xs0101id;
    }

    public void setXs0101id(String xs0101id) {
        this.xs0101id = xs0101id;
    }

    public List<CourseLite> getCourseLites() {
        return courseLites;
    }

    public void setCourseLites(List<CourseLite> courseLites) {
        this.courseLites = courseLites;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dwmc);
        dest.writeString(this.kksjmx);
        dest.writeString(this.jsmc);
        dest.writeString(this.jx02id);
        dest.writeString(this.kcxzmc);
        dest.writeString(this.jcmc);
        dest.writeString(this.sfxyjc);
        dest.writeString(this.kcsj);
        dest.writeString(this.ktmc);
        dest.writeString(this.kcsxm);
        dest.writeString(this.jx0501id);
        dest.writeString(this.jsxm);
        dest.writeString(this.kcxzm);
        dest.writeString(this.szkcfl);
        dest.writeString(this.xkjd);
        dest.writeString(this.kch);
        dest.writeFloat(this.zxs);
        dest.writeString(this.kcsx);
        dest.writeString(this.kkzc);
        dest.writeString(this.kkzcmx);
        dest.writeString(this.kcmc);
        dest.writeFloat(this.xf);
        dest.writeString(this.jx0404id);
        dest.writeString(this.xs0101id);
        dest.writeTypedList(this.courseLites);
    }

    public Course() {
    }

    protected Course(Parcel in) {
        this.dwmc = in.readString();
        this.kksjmx = in.readString();
        this.jsmc = in.readString();
        this.jx02id = in.readString();
        this.kcxzmc = in.readString();
        this.jcmc = in.readString();
        this.sfxyjc = in.readString();
        this.kcsj = in.readString();
        this.ktmc = in.readString();
        this.kcsxm = in.readString();
        this.jx0501id = in.readString();
        this.jsxm = in.readString();
        this.kcxzm = in.readString();
        this.szkcfl = in.readString();
        this.xkjd = in.readString();
        this.kch = in.readString();
        this.zxs = in.readFloat();
        this.kcsx = in.readString();
        this.kkzc = in.readString();
        this.kkzcmx = in.readString();
        this.kcmc = in.readString();
        this.xf = in.readFloat();
        this.jx0404id = in.readString();
        this.xs0101id = in.readString();
        this.courseLites = in.createTypedArrayList(CourseLite.CREATOR);
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
}
