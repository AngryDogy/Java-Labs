package org.tech.mybatis.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.tech.mybatis.entities.Street;

import java.util.List;

public interface StreetMapper {

    @Insert("INSERT INTO mycity.city.streets (id, name, postcode) VALUES(#{id}, #{name}, #{postcode})")
    void save(Street street);

    @Delete("DELETE FROM mycity.city.streets WHERE id = #{id}")
    void deleteById(long id);

    @Delete("DELETE FROM mycity.city.streets WHERE id = #{id}")
    void deleteByEntity(Street street);

    @Delete("DELETE FROM mycity.city.streets")
    void deleteAll();

    @Update("UPDATE mycity.city.streets SET name = #{name}, postcode = #{postcode} WHERE id = #{id}")
    void update(Street street);

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "postcode", column = "postcode")
    })
    @Select("SELECT * FROM mycity.city.streets WHERE id = #{id}")
    Street getById(long id);

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "postcode", column = "postcode")
    })
    @Select("SELECT * FROM mycity.city.streets")
    List<Street> getAll();
}
