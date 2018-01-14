package com.lvqingyang.iwuster.bean;

import java.util.ArrayList;
import java.util.List;

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
public class CourseLite {
    private Course course;
    private String name;
    private String room;
    private String time;
    private int startWeek;
    private int endWeek;
    private String teacher;

    public CourseLite(Course course) {
        this.course = course;
    }

    public CourseLite(Course course, String name, String room, String time, int startWeek, int endWeek, String teacher) {
        this.course = course;
        this.name = name;
        this.room = room;
        this.time = time;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.teacher = teacher;
    }

    public static List<CourseLite> parseCourse(Course course) {
        List<CourseLite> courseLites=new ArrayList<>();

        String _name=course.getKcmc();
        String _week =course.getKkzc();
        String _time=course.getKcsj();
        String _room=course.getJsmc();
        String _teacher=course.getJsxm();

        if(_week !=null && _time!=null){
            String[] weeks= _week.split(","),
                    times=_time.split(","),
                    rooms=null;

            if(_room!=null){
                rooms=_room.split(",");
            }

            int multi=weeks.length/times.length;
            for (int i = 0; i < weeks.length; i++) {
                CourseLite courseLite=new CourseLite(course);
                courseLite.setName(_name);
                courseLite.setTeacher(_teacher);

                if(_room!=null){
                    courseLite.setRoom(rooms[i/multi]);
                }

                courseLite.setTime(times[i/multi]);

                String w=weeks[i], sw, ew;
                if(w.contains("-")){
                    String[] ws=w.split("-");
                    sw=ws[0];
                    ew=ws[1];
                }else {
                    sw=ew=w;
                }
                courseLite.setStartWeek(Integer.valueOf(sw));
                courseLite.setEndWeek(Integer.valueOf(ew));

                courseLites.add(courseLite);
            }
        }else{
            CourseLite courseLite=new CourseLite(course);
            courseLite.setName(_name);
            courseLite.setTeacher(_teacher);
            courseLites.add(courseLite);
        }

        return courseLites;
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

    @Override
    public String toString() {
        return "CourseLite{" +
                "course=" + course +
                ", name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", time='" + time + '\'' +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                '}';
    }
}
