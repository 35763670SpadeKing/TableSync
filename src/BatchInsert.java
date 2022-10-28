import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*;
public class BatchInsert {
/*    public static void insertDB(String id, String sql, Connection conn) {
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
  */

    private static long begin = 1;//起始id
    private static long increment = 10000 ;
    // long onetime = begin + end ;
    //private long end = begin + end ;//每次循环插入的数据量
    private static String url = "jdbc:mysql://localhost:3306/bigdata?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true";
    private static String user = "root";
    private static String password = "xzq3576367";



    public static void insertBigData(long end) {
        //定义连接、statement对象
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            // conn.setAutoCommit(false);
            //编写sql
            String sql = "INSERT INTO emp_copy VALUES (?,?,?,?,?,?,?,?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();
            end = begin + end ;
            //循环10次
            for(int i=0;i<10;i++) {

                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //赋值
                    pstm.setLong(1, begin);
                    pstm.setInt(2, RandomValue.getNum(10001,99999));
                    pstm.setString(3, RandomValue.getChineseName());
                    pstm.setString(4, RandomValue.getNumber(5, 9));
                    pstm.setString(5, RandomValue.gender);
                    pstm.setString(6, RandomValue.randomDate("1990-10-14 12:12:12", "2020-10-24 12:12:12"));
                    pstm.setInt(7, RandomValue.getSalary(2,30));
                    pstm.setString(8, RandomValue.getPosition());
                    pstm.setInt(9, RandomValue.getNum(1, 10));
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
                //提交事务
                //        conn.commit();
                //边界值自增
                end += increment;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入1W条数据耗时："+(eTime-bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入10W数据共耗时："+(eTime1-bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
    public static void main(String[] args) {
        insertBigData(10000); //插入
    }

}
