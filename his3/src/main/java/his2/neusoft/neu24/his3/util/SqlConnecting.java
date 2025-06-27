package his2.neusoft.neu24.his3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlConnecting {
    public  static void Insert(String sql) throws Exception{

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
    }

    public  static void Delete(String sql) throws Exception{

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
    }

    public  static void Update(String sql) throws Exception{

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
    }
}
