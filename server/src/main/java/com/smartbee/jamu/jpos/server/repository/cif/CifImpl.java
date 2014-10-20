package com.smartbee.jamu.jpos.server.repository.cif;

import com.smartbee.jamu.jpos.server.domain.Cif;
import org.apache.log4j.Logger;
import org.jpos.util.NameRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by galihlasahido on 10/17/14.
 */
@Repository
public class CifImpl implements ParameterizedRowMapper<Cif>,  CifService {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Cif> getCifById(String id) {
        return jdbcTemplate.query("SELECT * FROM cif where nomorrekening = ?", new Object[]{id} ,this);
    }

    @Override
    public Cif mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cif cif = new Cif();
        cif.setId(rs.getLong("id"));
        cif.setNama(rs.getString("nama"));
        cif.setNomor_rekening(rs.getString("nomorrekening"));
        cif.setSaldo(rs.getInt("saldo"));
        return cif;
    }
}
