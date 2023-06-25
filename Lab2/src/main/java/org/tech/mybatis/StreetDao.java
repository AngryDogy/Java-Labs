package org.tech.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.tech.mybatis.entities.Street;
import org.tech.mybatis.mappers.StreetMapper;

import java.sql.SQLException;
import java.util.List;

public class StreetDao {
    public StreetDao() {}
    public void save(Street street) throws SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        StreetMapper streetMapper = session.getMapper(StreetMapper.class);
        streetMapper.save(street);
        session.commit();
        session.close();
    }
    public void deleteById(long id) throws SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        StreetMapper streetMapper = session.getMapper(StreetMapper.class);
        streetMapper.deleteById(id);
        session.commit();
        session.close();
    }
    public void deleteByEntity(Street street) throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        StreetMapper streetMapper = session.getMapper(StreetMapper.class);
        streetMapper.deleteByEntity(street);
        session.commit();
        session.close();
    }
    public void deleteAll() throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        StreetMapper streetMapper = session.getMapper(StreetMapper.class);
        streetMapper.deleteAll();
        session.commit();
        session.close();
    }
    public void update(Street street) throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        StreetMapper streetMapper = session.getMapper(StreetMapper.class);
        streetMapper.update(street);
        session.commit();
        session.close();
    }
    public Street getById(long id) throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        StreetMapper streetMapper = session.getMapper(StreetMapper.class);
        Street street = streetMapper.getById(id);
        session.close();
        return street;
    }
    public List<Street> getAll() throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        StreetMapper streetMapper = session.getMapper(StreetMapper.class);
        List<Street> streets = streetMapper.getAll();
        session.close();
        return streets;
    }
}
