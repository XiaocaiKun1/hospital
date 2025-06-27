package his2.neusoft.neu24.his3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegisterText {
    private static List<String> sex = new ArrayList<>();
    private static List<String> week = new ArrayList<>();
    public static List<String> day = new ArrayList<>();
    public static List<String> room = new ArrayList<>();
    public static List<String> number = new ArrayList<>();
    public static List<String> pay = new ArrayList<>();

    static {
        sex.add("男");
        sex.add("女");
        week.add("星期一");
        week.add("星期二");
        week.add("星期三");
        week.add("星期四");
        week.add("星期五");
        week.add("星期六");
        week.add("星期日");
        day.add("上午");
        day.add("下午");
        pay.add("微信支付");
        pay.add("支付宝支付");
        pay.add("现金支付");
    }
    static {
        try {
            Class.forName(GlobalData.getClassName());
            Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
            Statement st = con.createStatement();

            String sql1 = "select dept.dept_name from dept;";
            ResultSet res = st.executeQuery(sql1);
            while (res.next()) {
                room.add(res.getString("dept_name"));
            }

            String sql2 = "select regist_level.level_name from regist_level";
            ResultSet res1 = st.executeQuery(sql2);
            while (res1.next()) {
                number.add(res1.getString("level_name"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    public static List<String> getWeek() {
        return week;
    }
    public static List<String> getSex() {
        return sex;
    }
    public static List<String> getDay() {
        return day;
    }
    public static List<String> getRoom() {
        return room;
    }
    public static List<String> getNumber() {
        return number;
    }
    public static List<String> getPay() {
        return pay;
    }

}
