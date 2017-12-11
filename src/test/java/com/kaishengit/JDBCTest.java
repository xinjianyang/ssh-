package com.kaishengit;

import com.kaishengit.pojo.Product;
import com.kaishengit.pojo.Student;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * @author NativeBoy
 */
public class JDBCTest {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///jian";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "310628";

    @Test
    public void demoTest(){
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql = "select * from t_stu";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1));
            System.out.println(rsmd.getColumnName(2));
            System.out.println(rsmd.getColumnName(3));

            Class clazz = Product.class;
            ClassLoader classLoader = clazz.getClassLoader();
            Product product = (Product) clazz.newInstance();
            System.out.println(product);
            System.out.println(clazz);
            System.out.println(classLoader.toString());
           /* Method[] methods = clazz.getMethods();
            Annotation[] annotations = clazz.getAnnotations();
            for(Annotation annotation : annotations){
                System.out.println(annotation.toString());
            }

            for(Method method : methods){
                System.out.println(method.getName());

            }*/

           /* while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setPassword(rs.getString(3));
                System.out.println(student);
            }
*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
