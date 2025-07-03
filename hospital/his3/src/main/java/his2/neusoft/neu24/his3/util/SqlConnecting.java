package his2.neusoft.neu24.his3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlConnecting {

    // 新增获取数据库连接的方法
    private static Connection getConnection() throws Exception {
        Class.forName(GlobalData.getClassName());
        return DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
    }

    public static void Insert(String sql) throws Exception {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        }
    }

    public static void Delete(String sql) throws Exception {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        }
    }

    public static void Update(String sql) throws Exception {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        }
    }

    // 修复 executeQuery 方法
    public static ResultSet executeQuery(String sql) throws Exception {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();
    }
}
