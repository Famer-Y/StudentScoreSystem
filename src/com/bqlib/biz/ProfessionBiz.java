/**
 * 专业业务类
 */

package com.bqlib.biz;

import java.sql.Connection;
import java.util.List;

import com.bqlib.dao.DepartmentDao;
import com.bqlib.dao.ProfessionDao;
import com.bqlib.model.Department;
import com.bqlib.model.Profession;
import com.bqlib.model.Student;
import com.bqlib.util.DbUtil;

public class ProfessionBiz {

    private ProfessionDao professionDao = new ProfessionDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    
    /**
     * 添加专业
     * @param profession
     * @return
     * @throws Exception
     */
    public int addProfession(Profession profession) throws Exception{
        Connection conn = DbUtil.getConn();
        int num = professionDao.addProfession(profession);
        DbUtil.close();
        return num;
    }
    
    /**
     * 修改专业
     * @param profession
     * @return
     * @throws Exception
     */
    public int updateProfession(Profession profession) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = professionDao.updateProfession(profession);
        DbUtil.close();
        return num;
    }
    
    /**
     * 删除专业
     * @param pId
     * @return
     * @throws Exception
     */
    public int deleteProfession(String pId) throws Exception {
        Connection conn = DbUtil.getConn();
        int num = professionDao.deleteProfession(pId);
        DbUtil.close();
        return num;
    }
    
    /**
     * 根据id查找专业
     * @param pId
     * @return
     * @throws Exception
     */
    public Profession getProfessionById(String pId) throws Exception{
        Connection conn = DbUtil.getConn();
        Profession profession = professionDao.getProfessionById(pId);
        DbUtil.close();
        return profession;
    }
    
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
    
    /**
     * 统计表中专业的个数
     * @return
     * @throws Exception
     */
    public int countProfession() throws Exception {
        Connection conn = DbUtil.getConn();
        int num = professionDao.countProfession();
        DbUtil.close();
        return num;
    }
    
    /**
     * 获取部分专业（用于分页）
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Profession> listProfessionLimit(Integer start, Integer size) throws Exception{
        
        Connection conn = DbUtil.getConn();
        List<Profession> listprofession = professionDao.listProfessionLimit(start, size);
        for (Profession profession : listprofession){
            Department department = departmentDao.getDeparmentById(profession.getdId());
            if (department != null){
                profession.setdName(department.getdName());
            }                   
        }
        DbUtil.close();
        return listprofession;
    }
}
