package org.tech.mybatis.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.tech.mybatis.entities.Building;

import java.util.List;

public interface BuildingMapper {
    @Insert("INSERT INTO mycity.city.buildings (id, name, date_of_construction, number_of_floors, building_type, street)" +
            " VALUES(#{id}, #{name}, #{constructionDate}, #{floorsNumber}, #{buildingType}, #{street})")
    void save(Building building);

    @Delete("DELETE FROM mycity.city.buildings WHERE id = #{id}")
    void deleteById(long id);

    @Delete("DELETE FROM mycity.city.buildings WHERE id = #{id}")
    void deleteByEntity(Building building);

    @Delete("DELETE FROM mycity.city.buildings")
    void deleteAll();

    @Update("UPDATE mycity.city.buildings " +
            "SET name = #{name}," +
            "date_of_construction = #{constructionDate}," +
            "number_of_floors = #{floorsNumber}," +
            "building_type = #{buildingType}," +
            "street = #{street.id} WHERE id = #{id}" )
    void update(Building building);

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "constructionDate", column = "date_of_construction"),
            @Result(property = "buildingType", column = "building_type"),
            @Result(property = "street", column = "street")
    })
    @Select("SELECT * FROM mycity.city.buildings WHERE id = #{id}")
    Building getById(long id);

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "constructionDate", column = "date_of_construction"),
            @Result(property = "buildingType", column = "building_type"),
            @Result(property = "street", column = "street")
    })
    @Select("SELECT * FROM mycity.city.buildings")
    List<Building> getAll();

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "constructionDate", column = "date_of_construction"),
            @Result(property = "buildingType", column = "building_type"),
            @Result(property = "street", column = "street")
    })
    @Select("SELECT * FROM mycity.city.buildings WHERE street = #{streetId}")
    List<Building> getAllByStreetId(long streetId);
}
