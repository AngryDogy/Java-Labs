package org.tech.hibernate.dao;

import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.tech.hibernate.entities.Building;
import org.tech.hibernate.entities.Street;
import org.tech.hibernate.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class BuildingDao {
    public void save(@NonNull Building building) {
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        session.save(building);
        SessionUtil.closeTransactionSession();
    }
    public void deleteById(long id) {
        Building building = this.getById(id);
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        session.delete(building);
        SessionUtil.closeTransactionSession();
    }
    public void deleteByEntity(@NonNull Building building) {
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        session.delete(building);
        SessionUtil.closeTransactionSession();
    }
    public void deleteAll() {
        String sql = "DELETE FROM Building";
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        Query query = session.createQuery(sql);
        query.executeUpdate();
        SessionUtil.closeTransactionSession();
    }
    public void update(@NonNull Building building) {
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        session.update(building);
        SessionUtil.closeTransactionSession();
    }
    public Building getById(long id) {
        return SessionUtil.openSession().get(Building.class, id);
    }
    public List<Building> getAll()  {

        return SessionUtil.openSession().createQuery("From Building ").stream().toList();

    }
    public List<Building> getAllByStreetId(long id) throws SQLException {
        String sql = "FROM Building WHERE street = " + id;
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        Query query = session.createQuery(sql, Building.class);
        return (List<Building>) query.getResultList();
    }
}
