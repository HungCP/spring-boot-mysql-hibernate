package netgloo.domain;

import netgloo.domain.EnumStatus.AttendanceStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G551 on 04/29/2016.
 */
public class AttendanceTableEntity {

    private User user;

    private List<AttendanceStatus> attendanceStatusList =  new ArrayList<>();

    public AttendanceTableEntity() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AttendanceStatus> getAttendanceStatusList() {
        return attendanceStatusList;
    }

    public void setAttendanceStatusList(List<AttendanceStatus> attendanceStatusList) {
        this.attendanceStatusList = attendanceStatusList;
    }

    @Override
    public String toString() {
        return "AttendanceTableEntity{" +
                "user=" + user +
                ", attendanceStatusList='" + attendanceStatusList +
                '}';
    }
}
