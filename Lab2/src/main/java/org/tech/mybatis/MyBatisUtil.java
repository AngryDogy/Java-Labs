package org.tech.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory factory = buildSessionFactory();
    private static SqlSessionFactory buildSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        catch (IOException exception) {
            throw new ExceptionInInitializerError(exception);
        }
        return factory;
    }
    public static SqlSessionFactory getSessionFactory() {
        return factory;
    }
}
