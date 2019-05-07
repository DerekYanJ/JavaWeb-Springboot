package com.example.demo.bean.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM users where id = #{id}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "password", column = "password")
//    })
    User findById(int id);
}
