package org.tech;

import org.tech.hibernate.dao.StreetDao;
import org.tech.hibernate.entities.Street;
import org.tech.jdbc.StreetConnector;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("compare.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        long startTime = System.nanoTime();
        jdbc();
        long endTime   = System.nanoTime();
        double totalTime = (endTime - startTime) / 1000000000.0 ;
        writer.println("JDBC: " + totalTime);

        startTime = System.nanoTime();
        hibernate();
        endTime   = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000000.0 ;
        writer.println("Hibernate: " + totalTime);


        startTime = System.nanoTime();
        mybatis();
        endTime   = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000000.0 ;
        writer.println("Mybatis: " + totalTime);
        writer.close();
    }
    public static void hibernate() {
        org.tech.hibernate.dao.StreetDao streetDao = new StreetDao();
        streetDao.deleteAll();
        String name = "a";
        int postcode = 1234;
        List<org.tech.hibernate.entities.Street> streets = new ArrayList<org.tech.hibernate.entities.Street>();
        for (int i = 0; i < 100; i++) {
            streets.add(new Street(name, postcode));
            name = name + "a";
            postcode += 1;
        }
        System.out.println("here");
        for (int i = 0; i < 100; i++) {
            try {
                streetDao.save(streets.get(i));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        List<Street> result = streetDao.getAll();
    }
    public static void jdbc() {
        StreetConnector streetConnector = new StreetConnector("jdbc:postgresql://localhost:5432/mycity", "angrydog", "321");
        try {
            streetConnector.deleteAll();
            String name = "a";
            int postcode = 1234;
            int id = 0;
            List<org.tech.jdbc.entities.Street> streets = new ArrayList<org.tech.jdbc.entities.Street>();
            for (int i = 0; i < 100; i++) {
                streets.add(new org.tech.jdbc.entities.Street(id, name, postcode));
                name = name + "a";
                postcode += 1;
                id += 1;
            }
            for (int i = 0; i < 100; i++) {
                try {
                    streetConnector.save(streets.get(i));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            List<org.tech.jdbc.entities.Street> result = streetConnector.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void mybatis() {
        org.tech.mybatis.StreetDao streetDao = new org.tech.mybatis.StreetDao();
        try {
            streetDao.deleteAll();
            String name = "a";
            int postcode = 1234;
            int id = 0;
            List<org.tech.mybatis.entities.Street> streets = new ArrayList<org.tech.mybatis.entities.Street>();
            for (int i = 0; i < 100; i++) {
                streets.add(new org.tech.mybatis.entities.Street(id, name, postcode));
                name = name + "a";
                postcode += 1;
                id += 1;
            }
            for (int i = 0; i < 100; i++) {
                try {
                    streetDao.save(streets.get(i));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            List<org.tech.mybatis.entities.Street> result = streetDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
