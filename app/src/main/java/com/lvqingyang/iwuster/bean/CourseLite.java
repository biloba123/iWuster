package com.lvqingyang.iwuster.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2018/1/14
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 * @see Course
 */
public class CourseLite extends DataSupport {
    private Course course;
    private long course_id;
    private String name;
    @Column(defaultValue = "unknown")
    private String room;
    private String time;
    private int startWeek;
    private int endWeek;
    @Column(defaultValue = "unknown")
    private String teacher;

    public CourseLite() {
    }

    public CourseLite(Course course) {
        this.course = course;
    }

    public CourseLite(String name, String room, String time, int startWeek, int endWeek, String teacher) {
        this.course = course;
        this.name = name;
        this.room = room;
        this.time = time;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "CourseLite{" +
                "course=" + course +
                ", course_id=" + course_id +
                ", name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", time='" + time + '\'' +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
