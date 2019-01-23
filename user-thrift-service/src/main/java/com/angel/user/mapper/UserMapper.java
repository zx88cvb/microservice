package com.angel.user.mapper;

import com.angel.thrift.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户Mapper
 * @author JingXiang Bi
 * @date 2019/1/15
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户id查询
     * @param id id
     * @return 返回用户实体
     */
    @Select("select id, username, password, real_name as realName, mobile, email" +
            " from pe_user where id = #{id}")
    UserInfo getUserById(@Param("id")Integer id);

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return 返回用户实体
     */
    @Select("select id, username, password, real_name as realName, mobile, email" +
            " from pe_user where username = #{username}")
    UserInfo getUserByName(@Param("username")String username);

    /**
     * 注册
     * @param userInfo 用户实体
     */
    @Insert("insert into pe_user (username, password, real_name, mobile, email)" +
            "values (#{u.username}, #{u.password}, #{u.realName}, #{u.mobile}, #{u.email})")
    void registerUser(@Param("u")UserInfo userInfo);

    @Select("select u.id,u.username,u.password,u.real_name as realName," +
            "u.mobile,u.email,t.intro,t.stars from pe_user u," +
            "pe_teacher t where u.id=#{id} " +
            "and u.id=t.user_id")
    UserInfo getTeacherById(@Param("id")int id);
}
