package org.tech.jdbc;

import lombok.NonNull;
import org.tech.jdbc.entities.Building;
import org.tech.jdbc.entities.BuildingType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BuildingConnector extends ConnectorJDBC {
    private StreetConnector streetConnector;

    public BuildingConnector(String url, String user, String password) {
        super(url, user, password);
        streetConnector = new StreetConnector(url, user, password);
    }

    public void save(@NonNull Building building) throws SQLException {
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO mycity.city.buildings " +
                "(id, name, date_of_construction, number_of_floors, building_type, street) " +
                "VALUES (?, ?, ?, ?, ?, ?)");
        statement.setLong(1, building.getId());
        statement.setString(2, building.getName());
        statement.setDate(3, building.getConstructionDate());
        statement.setInt(4, building.getFloorsNumber());
        statement.setString(5, building.getBuildingType().toString());
        statement.setLong(6, building.getStreet().getId());
        statement.executeUpdate();
        statement.close();
        connection.close();

    }

    public void deleteById(long id) throws SQLException {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM mycity.city.buildings WHERE id = " + id);
        statement.close();
        connection.close();

    }

    public void deleteByEntity(@NonNull Building building) throws SQLException {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM mycity.city.streets WHERE id = " + building.getId());
        statement.close();
        connection.close();

    }

    public void deleteAll() throws SQLException {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE mycity.city.buildings");
        statement.close();
        connection.close();
    }

    public void update(@NonNull Building building) throws SQLException {
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE mycity.city.buildings " +
                "SET name = ?, date_of_construction = ?, number_of_floors = ?, building_type = ?, street = ?" +
                " WHERE id = ?");
        statement.setString(1, building.getName());
        statement.setDate(2, building.getConstructionDate());
        statement.setInt(3, building.getFloorsNumber());
        statement.setString(4, building.getBuildingType().toString());
        statement.setLong(5, building.getStreet().getId());
        statement.setLong(6, building.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public Building getById(long id) throws SQLException {
        Building building = null;
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM mycity.city.buildings WHERE id = ?");
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            building = new Building(result.getLong(1),
                    result.getString(2),
                    result.getDate(3),
                    result.getInt(4),
                    BuildingType.valueOf(result.getString(5)),
                    streetConnector.getById(result.getLong(6)));
        }
        statement.close();
        connection.close();
        if (building == null) {
            throw new SQLException("The entity with this id doesn't exist");
        }
        return building;
    }

    public List<Building> getAll() throws SQLException {
        List<Building> buildings = new ArrayList<Building>();
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM mycity.city.buildings");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            buildings.add(new Building(result.getLong(1),
                    result.getString(2),
                    result.getDate(3),
                    result.getInt(4),
                    BuildingType.valueOf(result.getString(5)),
                    streetConnector.getById(result.getLong(6))));
        }
        statement.close();
        connection.close();
        return buildings;
    }
    public List<Building> getAllByStreetId(long id) throws SQLException {
        List<Building> buildings = new ArrayList<Building>();
        Connection connection = this.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM mycity.city.buildings WHERE street = ?");
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            buildings.add(new Building(result.getLong(1),
                    result.getString(2),
                    result.getDate(3),
                    result.getInt(4),
                    BuildingType.valueOf(result.getString(5)),
                    streetConnector.getById(result.getLong(6))));
        }
        statement.close();
        connection.close();
        return buildings;
    }
}
