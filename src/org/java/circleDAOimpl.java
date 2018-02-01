/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Hardik
 */
public class circleDAOimpl {

    private Circle c;
    private JdbcTemplate jdbcT; // Replacing with DataSource from (javax.sql.DataSource)
    

    public JdbcTemplate getJdbcT() {
        return jdbcT;
    }

    public void setJdbcT(JdbcTemplate jdbcT) {
        this.jdbcT = jdbcT;
    }

    public Circle getC() {
        return c;
    }

    public void setC(Circle c) {
        this.c = c;
    }

    public void createCircle() {

        String sql = "CREATE TABLE circle("
                + "c_id INTEGER,"
                + "c_name VARCHAR(20))";

        getJdbcT().execute(sql);

    }

    public void insertCircle() {

        int id = getC().getC_id();
        String nm = getC().getC_name();

        System.out.println("id .:" + id);
        System.out.println("name .:" + nm);

        String sql = "INSERT INTO circle VALUES(?,?)";
        getJdbcT().update(sql,new Object[]{id,nm});

    }

    public int getCircleCount() {

        return getJdbcT().queryForObject("SELECT COUNT(*) FROM circle",Integer.class);
    }

    public String getCircleName(int id) {

        String sql = "SELECT c_name FROM circle WHERE c_id = ?";

        return getJdbcT().queryForObject(sql, new Object[]{id}, String.class);

    }

    public Circle getCircleForID(int id) {

        String sql = "SELECT * FROM circle WHERE c_id = ?";

        return getJdbcT().queryForObject(sql, new Object[]{id}, new circleMapper());
    }

    public List<Circle> getALL() {

        String sql = "SELECT * FROM circle";

        return getJdbcT().query(sql, new circleMapper());
    }

    class circleMapper implements RowMapper<Circle> {

        @Override
        public Circle mapRow(ResultSet rs, int i) throws SQLException {
            Circle c = new Circle();

            c.setC_id(rs.getInt(1));
            c.setC_name(rs.getString(2));
            return c;
        }

    }

}
