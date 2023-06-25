package org.tech.jdbc;

import lombok.NonNull;
import org.tech.jdbc.entities.Street;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StreetConnector extends ConnectorJDBC{

    public StreetConnector(String url, String user, String password) {
        super(url, user, password);
    }

    public void save(@NonNull Street street) throws SQLException {
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO mycity.city.streets (id, name, postcode) " +
                "VALUES (?, ?, ?)");
        statement.setLong(1, street.getId());
        statement.setString(2, street.getName());
        statement.setInt(3, street.getPostcode());
        statement.executeUpdate();
        statement.close();
        connection.close();

    }
    public void deleteStreetById(long id) throws SQLException{

        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM mycity.city.streets WHERE id = " + id);
        statement.close();
        connection.close();


    }
    public void deleteByEntity(@NonNull Street street) throws SQLException {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM mycity.city.streets WHERE id = " + street.getId());
        statement.close();
        connection.close();
    }
    public void deleteAll() throws SQLException {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE mycity.city.streets");
        statement.close();
        connection.close();
    }
    public void update(@NonNull Street street) throws SQLException {
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE mycity.city.streets " +
                    "SET name = ?, postcode = ? WHERE id = ?");
        statement.setString(1, street.getName());
        statement.setInt(2, street.getPostcode());
        statement.setLong(3, street.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    public Street getById(long id) throws SQLException {
        Street street = null;
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM mycity.city.streets WHERE id = ?");
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            street = new Street(result.getLong(1), result.getString(2), result.getInt(3));
        }
        statement.close();
        connection.close();
        if (street == null) {
            throw new SQLException("The entity with this id doesn't exist");
        }
        return street;
    }
    public List<Street> getAll() throws SQLException {
        List<Street> streets = new ArrayList<Street>();
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM mycity.city.streets");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            streets.add(new Street(result.getLong(1), result.getString(2), result.getInt(3)));
        }
        statement.close();
        connection.close();
        return streets;
    }
}
