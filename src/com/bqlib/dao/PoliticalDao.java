package com.bqlib.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bqlib.model.Political;
import com.bqlib.util.DbUtil;

public class PoliticalDao {
    
    
    /**
     * 获取表中的所有政治面貌
     * @return
     * @throws Exception
     */
    public List<Political> listPolitical() throws Exception {
        String sql = "select id,name from political";
        List<Political> politicalList = new ArrayList<Political>();
        ResultSet rs = DbUtil.executeQuery(sql, null);
        while (rs.next()) {
            Political political = new Political();
            political.setId(rs.getInt("id"));
            political.setName(rs.getString("name"));
            politicalList.add(political);
        }
        return politicalList;
    }
}
