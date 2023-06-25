package org.tech.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.tech.mybatis.entities.Building;
import org.tech.mybatis.mappers.BuildingMapper;
import java.sql.SQLException;
import java.util.List;

public class BuildingDao {
    public BuildingDao() {}
    public void save(Building building) throws SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        buildingMapper.save(building);
        session.commit();
        session.close();
    }
    public void deleteById(long id) throws SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        buildingMapper.deleteById(id);
        session.commit();
        session.close();
    }
    public void deleteByEntity(Building building) throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        buildingMapper.deleteByEntity(building);
        session.commit();
        session.close();
    }
    public void deleteAll() throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        buildingMapper.deleteAll();
        session.commit();
        session.close();
    }
    public void update(Building building) throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        buildingMapper.update(building);
        session.commit();
        session.close();
    }

    public Building getById(long id) throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        Building building = buildingMapper.getById(id);
        session.close();
        return building;
    }
    public List<Building> getAll() throws  SQLException {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        List<Building> buildings = buildingMapper.getAll();
        session.close();
        return buildings;
    }
    public List<Building> getAllByStreetId(long streetId) {
        SqlSession session = MyBatisUtil.getSessionFactory().openSession();
        BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
        List<Building> buildings = buildingMapper.getAllByStreetId(streetId);
        session.close();
        return buildings;
    }
}
