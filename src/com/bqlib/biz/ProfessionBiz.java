/**
 * 专业业务类
 */

package com.bqlib.biz;

import java.sql.Connection;
import java.util.List;

import com.bqlib.dao.ProfessionDao;
import com.bqlib.model.Profession;
import com.bqlib.util.DbUtil;

public class ProfessionBiz {

    private ProfessionDao professionDao = new ProfessionDao();
    
    /**
     * 根据院系id获取专业列表
     * @param dId
     * @return
     * @throws Exception
     */
    public List<Profession> listProfessionByDepartment(String dId) throws Exception{
        Connection conn = DbUtil.getConn();
        List<Profession> professionList = professionDao.listProfessionByDepartment(dId);
        DbUtil.close();
        return professionList;
    }
    
    /**
     * 获取表中所有的专业
     * @return
     * @throws Exception
     */
    public List<Profession> listProfession() throws Exception{
        Connection conn = DbUtil.getConn();
        List<Profession> professionList = professionDao.listProfession();
        DbUtil.close();
        return professionList;
    }
}
