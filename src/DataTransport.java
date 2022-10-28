import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataTransport {

    public static void insertDB(String id, String sql, Connection conn) {
        int i = 0;
        try {
            Statement sts = conn.createStatement();
            i = sts.executeUpdate(sql);
            if (i == -1) {
                System.out.println(id + " insert fail.");
            }
        } catch (SQLException e) {
            System.out.println(sql + " insert fail.");
            e.printStackTrace();
        }
    }

    private static void Transtable() {
        Connection conn = DBUtil.getConn();
        Statement sts = null;
        String sql = "select * from view_gaj_population limit 100";
        ResultSet result = null;
        try {
            sts = conn.createStatement();
            result = sts.executeQuery(sql);
            Connection conn2 = DBUtil.getConn2();
            int count = 0;
            while (result.next()) {
                // 通过字段检索
                String RYNBID = result.getString("RYNBID");
                String HHID = result.getString("HHID");
                String GMSFHM_AES = result.getString("GMSFHM_AES");
                String ZXXX = result.getString("ZXXX");
                String YHZGX = result.getString("YHZGX");

                // 输出数据
                String insert_sql = "insert into gaj_population values ('" + RYNBID + "', '" +
                        HHID + "', '" +
                        GMSFHM_AES + "', '" +
                        ZXXX + "', '" +
                        YHZGX +
                        "')";
                // System.out.println(insert_sql);
                insertDB(RYNBID, insert_sql, conn2);

                count = count + 1;
                if (count % 100 == 0) {
                    System.out.println("已插入" + count + "条记录");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (sts != null) sts.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Transtable();
    }
}
