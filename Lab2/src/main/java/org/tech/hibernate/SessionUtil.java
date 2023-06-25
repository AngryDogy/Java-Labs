package org.tech.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {
    private static Session session;
    private static Transaction transaction;
    public static Session getSession() {
        return session;
    }
    public static Transaction transaction() {
        return transaction;
    }
    public static Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
    public static Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public static void closeSession() {
        session.close();
    }
    public static void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
