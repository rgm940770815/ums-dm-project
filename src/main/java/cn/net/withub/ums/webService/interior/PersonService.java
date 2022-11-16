package cn.net.withub.ums.webService.interior;

import cn.net.withub.ums.entity.UmsUserInfoView;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * 人员统计接口
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PersonService {

    /**
     * 统计员额法官人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 统计数量
     */
    @WebMethod
    public Result yefgCount(@WebParam(name = "fydm") String fydm, @WebParam(name = "org_code")String org_code);


    /**
     * 查找员额法官列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 查询结果
     */
    @WebMethod
    public Result yefgList(@WebParam(name = "pageSize") Integer pageSize, @WebParam(name = "pageNum") Integer pageNum, @WebParam(name = "fydm") String fydm, @WebParam(name = "org_code") String org_code);

    /**
     * 统计法官助理人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 统计数量
     */
    @WebMethod
    public Result fgzlCount(@WebParam(name = "fydm") String fydm, @WebParam(name = "org_code")String org_code);


    /**
     * 查找法官助理列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 查询结果
     */
    @WebMethod
    public Result fgzlList(@WebParam(name = "pageSize") Integer pageSize, @WebParam(name = "pageNum") Integer pageNum, @WebParam(name = "fydm") String fydm, @WebParam(name = "org_code") String org_code);

    /**
     * 统计书记员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 统计数量
     */
    @WebMethod
    public Result sjyCount(@WebParam(name = "fydm") String fydm, @WebParam(name = "org_code")String org_code);

    /**
     * 查询书记员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 查询结果
     */
    @WebMethod
    public Result sjyList(@WebParam(name = "pageSize") Integer pageSize, @WebParam(name = "pageNum") Integer pageNum, @WebParam(name = "fydm") String fydm, @WebParam(name = "org_code") String org_code);

    /**
     * 统计审判员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 统计数量
     */
    @WebMethod
    public Result spyCount(@WebParam(name = "fydm") String fydm, @WebParam(name = "org_code")String org_code);

    /**
     * 查询审判员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 查询结果
     */
    @WebMethod
    public Result spyList(@WebParam(name = "pageSize") Integer pageSize, @WebParam(name = "pageNum") Integer pageNum, @WebParam(name = "fydm") String fydm, @WebParam(name = "org_code") String org_code);

    /**
     * 统计助理审判员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 统计数量
     */
    @WebMethod
    public Result zlspyCount(@WebParam(name = "fydm") String fydm, @WebParam(name = "org_code")String org_code);

    /**
     * 查询助理审判员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 查询结果
     */
    @WebMethod
    public Result zlspyList(@WebParam(name = "pageSize") Integer pageSize, @WebParam(name = "pageNum") Integer pageNum, @WebParam(name = "fydm") String fydm, @WebParam(name = "org_code") String org_code);

    /**
     * 统计司法行政人员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 统计数量
     */
    @WebMethod
    public Result sfxzryCount(@WebParam(name = "fydm") String fydm, @WebParam(name = "org_code")String org_code);


    /**
     * 查询司法行政人员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 查询结果
     */
    @WebMethod
    public Result sfxzryList(@WebParam(name = "pageSize") Integer pageSize, @WebParam(name = "pageNum") Integer pageNum, @WebParam(name = "fydm") String fydm, @WebParam(name = "org_code") String org_code);


    /**
     * 统计临聘人员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 统计数量
     */
    @WebMethod
    public Result lpryCount(@WebParam(name = "fydm") String fydm, @WebParam(name = "org_code")String org_code);

    /**
     * 查询临聘人员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return 查询结果
     */
    @WebMethod
    public Result lpryList(@WebParam(name = "pageSize") Integer pageSize, @WebParam(name = "pageNum") Integer pageNum, @WebParam(name = "fydm") String fydm, @WebParam(name = "org_code") String org_code);

    /**
     * 根据人员id查详情
     * @param id 人员id
     * @return 查询结果
     */
    @WebMethod
    public Result selectById(@WebParam(name = "id") String id,@WebParam(name = "xtptId") String xtptId);

    /**
     * 查询所有人员列表
     * @param pageSize 页大小
     * @param pageNum 页数(从0开始)
     * @param fydm 法院代码
     * @param courtNo 法院编码
     * @param orgCode 标准部门编码
     * @param deptNo  部门编码
     * @param administrationPosition  行政职务编码
     * @param personnelClassification 人员分类编码
     * @return
     */
    @WebMethod
    public Result selectAllList(
            @WebParam(name = "pageSize") Integer pageSize,
            @WebParam(name = "pageNum") Integer pageNum,
            @WebParam(name = "fydm") String fydm,
            @WebParam(name = "courtNo") String courtNo,
            @WebParam(name = "orgCode") String orgCode,
            @WebParam(name = "deptNo") String deptNo,
            @WebParam(name = "administrationPosition") String administrationPosition,
            @WebParam(name = "personnelClassification") String personnelClassification,
            @WebParam(name = "yefg") String yefg
    );

    /**
     * 获取离职人员列表
     * @param fydm 法院代码 (默认所有)
     * @param startDate 开始时间(格式为: 年-月-日 的字符串 如:2021-01-01)
     * @param endDate 结束时间(格式为: 年-月-日 的字符串 如:2021-01-01)
     * @param pageNum 页数(开始页数为1)
     * @param pageSize 页大小
     * @return 查询结果
     */
    @WebMethod
    public Result getLeaveList(@WebParam(name = "fydm") String fydm, @WebParam(name = "startDate") String startDate, @WebParam(name = "endDate") String endDate, @WebParam(name = "pageNum") Integer pageNum,@WebParam(name = "pageSize") Integer pageSize);

    /**
     * 获取部门列表
     * @param fydm 法院代码
     * @param orgCode 部门代码(三位数)
     * @param deptNo 部门编码
     * @param  orgType 部门类型 1 业务部门 2 综合部门 8 派出法庭
     * @return
     */
    @WebMethod
    public Result getDeptList(
            @WebParam( name = "fydm") String fydm,
            @WebParam( name = "orgCode") String orgCode,
            @WebParam( name = "deptNo") String deptNo,
            @WebParam( name = "orgType") String orgType
            );

    /**
     * 根据法院编码、标准部门编码、部门编码、时间段、人员分类获取人员列表（含人员调职情况）
     * @param isGetJob 是否获取调职信息(true需要, false需要)
     * @param fydm 法院代码
     * @param orgCode 标准部门编码
     * @param deptNo 部门编码
     * @param personnelClassification 人员分类
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @WebMethod
    public Result getPersonAndChangeJobList(
            @WebParam( name = "isGetJob") Boolean isGetJob,
            @WebParam( name = "fydm") String fydm,
            @WebParam( name = "orgCode") String orgCode,
            @WebParam( name = "deptNo") String deptNo,
            @WebParam( name = "personnelClassification") String personnelClassification,
            @WebParam( name = "startTime") String startTime,
            @WebParam( name = "endTime") String endTime
    );

    /**
     * 行政职务查询（业务庭副庭长）
     * @param xtptId 系统平台id
     * @return
     */
    @WebMethod
    public Result selectXzzw(@WebParam(name = "xtptId") String xtptId);

    /**
     * 法官等级信息查询（员额法官）
     * @param xtptId 系统平台id
     * @return
     */
    @WebMethod
    public Result selectFgdjxx(@WebParam(name = "xtptId") String xtptId);

    /**
     * 法官助理等级信息查询（法官助理）
     * @param xtptId 系统平台id
     * @return
     */
    @WebMethod
    public Result selectFgzldjxx(@WebParam(name = "xtptId") String xtptId);

    /**
     * 四、书记员等级信息查询
     * @param xtptId 系统平台id
     * @return
     */
    @WebMethod
    public Result selectSjyDjxx(@WebParam(name = "xtptId") String xtptId);

}
