package com.bqlib.biz;

import java.sql.Connection;
import java.util.List;

import com.bqlib.dao.PoliticalDao;
import com.bqlib.model.Political;
import com.bqlib.util.DbUtil;

public class PoliticalBiz {
    
    private PoliticalDao politicalDao = new PoliticalDao();
    
    /**
     * 获取表中所有政治面貌
     * @return
     * @throws Exception
     */
    public List<Political> listPolitical() throws Exception {
        Connection conn = DbUtil.getConn();
        List<Political> politicalList = politicalDao.listPolitical();
        DbUtil.close();
        return politicalList;
    }
}
