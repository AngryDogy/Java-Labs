package org.tech.hibernate.dao;

import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.tech.hibernate.entities.Street;
import org.tech.hibernate.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class StreetDao {
    public void save(@NonNull Street street) throws SQLException {
        SessionUtil.openTransactionSession();
        Session session =  SessionUtil.getSession();
        session.save(street);
        SessionUtil.closeTransactionSession();
    }
    public void deleteById(long id) {
        Street street = this.getById(id);
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        session.delete(street);
        SessionUtil.closeTransactionSession();
    }
    public void deleteByEntity(@NonNull Street street) {
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        session.delete(street);
        SessionUtil.closeTransactionSession();
    }
    public void deleteAll() {
        String sql = "DELETE FROM Street";
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        Query query = session.createQuery(sql);
        query.executeUpdate();
        SessionUtil.closeTransactionSession();
    }
    public void update(@NonNull Street street) {
        SessionUtil.openTransactionSession();
        Session session = SessionUtil.getSession();
        session.update(street);
        SessionUtil.closeTransactionSession();
    }
    public Street getById(long id) {
        return SessionUtil.openSession().get(Street.class, id);
    }
    public List<Street> getAll()  {

        return SessionUtil.openSession().createQuery("From Street ").stream().toList();

    }

}
