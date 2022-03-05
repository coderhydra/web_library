package com.example.libraryweb.dao;

import com.example.libraryweb.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDAO")
public class UserDAO implements UserDAO_IF{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(UserVO u) {
        String sql = "INSERT INTO library_user VALUES('member',NULL,?,?,?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                u.getUid(),u.getPwd(),u.getName(),u.getBirth(),u.getGender(),u.getPhone(),u.getEmail(),u.getAddress());
        return rows>0;
    }

    @Override
    public int insertAndGetId(UserVO u) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean update(UserVO u) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(int num) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<UserVO> getList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserVO select(String uid) {
        String sql = "SELECT* FROM library_user WHERE uid=?";
        return jdbcTemplate.queryForObject(sql, (rs,i)->
                        //select ---> queryforObject
                        new UserVO(rs.getString(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),
                                rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10))
                ,uid);
    }
}
