// 统一请求路径前缀在libs/axios.js中修改
import { getRequest, postRequest, putRequest, getNoAuthRequest, postNoAuthRequest, postJsonRequest, getFileRequest } from '@/libs/axios';



// 文件上传接口
export const uploadFile = "/xboot/upload/file"
// 验证码渲染图片接口
export const drawCodeImage = "/xboot/common/captcha/draw/"
// 获取菜单
export const getMenuList = "/xboot/permission/getMenuList"
// 获取数据字典
export const getDictData = "/xboot/dictData/getByType/"

export const fileUrl = "/xboot/files/"

// 登陆
export const login = (params) => {
    return postNoAuthRequest('/login', params)
}
// 获取用户登录信息
export const userInfo = (params) => {
    return getRequest('/user/info', params)
}
// 注册
export const regist = (params) => {
    return postNoAuthRequest('/user/regist', params)
}
// 初始化验证码
export const initCaptcha = (params) => {
    return getNoAuthRequest('/common/captcha/init', params)
}
// IP天气信息
export const ipInfo = (params) => {
    return getRequest('/common/ip/info', params)
}
// 个人中心编辑
export const userInfoEdit = (params) => {
    return postRequest('/user/edit', params)
}
// 个人中心修改密码
export const changePass = (params) => {
    return postRequest('/user/modifyPass', params)
}
// 解锁
export const unlock = (params) => {
    return postRequest('/user/unlock', params)
}




// 获取用户数据 多条件
export const getUserListData = (params) => {
    return getRequest('/user/getByCondition', params)
}
// 通过用户名搜索
export const searchUserByName = (username, params) => {
    return getRequest('/user/searchByName/' + username, params)
}
// 获取全部用户数据
export const getAllUserData = (params) => {
    return getRequest('/user/getAll', params)
}
// 通过部门获取全部用户数据
export const getUserByDepartmentId = (id, params) => {
    return getRequest(`/user/getByDepartmentId/${id}`, params)
}
// 添加用户
export const addUser = (params) => {
    return postRequest('/user/admin/add', params)
}
// 编辑用户
export const editUser = (params) => {
    return postRequest('/user/admin/edit', params)
}
// 启用用户
export const enableUser = (id, params) => {
    return postRequest(`/user/admin/enable/${id}`, params)
}
// 禁用用户
export const disableUser = (id, params) => {
    return postRequest(`/user/admin/disable/${id}`, params)
}
// 删除用户
export const deleteUser = (params) => {
    return postRequest('/user/delByIds', params)
}
// 重置用户密码
export const resetUserPass = (params) => {
    return postRequest('/user/resetPass', params)
}



// 获取一级部门
export const initDepartment = (params) => {
    return getRequest('/department/getByParentId/0', params)
}
// 加载部门子级数据
export const loadDepartment = (id, params) => {
    return getRequest(`/department/getByParentId/${id}`, params)
}
// 添加部门
export const addDepartment = (params) => {
    return postRequest('/department/add', params)
}
// 编辑部门
export const editDepartment = (params) => {
    return postRequest('/department/edit', params)
}
// 删除部门
export const deleteDepartment = (params) => {
    return postRequest('/department/delByIds', params)
}
// 搜索部门
export const searchDepartment = (params) => {
    return getRequest('/department/search', params)
}



// 获取全部角色数据
export const getAllRoleList = (params) => {
    return getRequest('/role/getAllList', params)
}
// 分页获取角色数据
export const getRoleList = (params) => {
    return getRequest('/role/getAllByPage', params)
}
// 添加角色
export const addRole = (params) => {
    return postRequest('/role/save', params)
}
// 编辑角色
export const editRole = (params) => {
    return postRequest('/role/edit', params)
}
// 设为或取消注册角色
export const setDefaultRole = (params) => {
    return postRequest('/role/setDefault', params)
}
// 分配角色权限
export const editRolePerm = (params) => {
    return postRequest('/role/editRolePerm', params)
}
// 分配角色数据权限
export const editRoleDep = (params) => {
    return postRequest('/role/editRoleDep', params)
}
// 删除角色
export const deleteRole = (params) => {
    return postRequest('/role/delByIds', params)
}



// 获取全部权限数据
export const getAllPermissionList = (params) => {
    return getRequest('/permission/getAllList', params)
}
// 添加权限
export const addPermission = (params) => {
    return postRequest('/permission/add', params)
}
// 编辑权限
export const editPermission = (params) => {
    return postRequest('/permission/edit', params)
}
// 删除权限
export const deletePermission = (params) => {
    return postRequest('/permission/delByIds', params)
}
// 搜索权限
export const searchPermission = (params) => {
    return getRequest('/permission/search', params)
}



// 获取全部字典
export const getAllDictList = (params) => {
    return getRequest('/dict/getAll', params)
}
// 添加字典
export const addDict = (params) => {
    return postRequest('/dict/add', params)
}
// 编辑字典
export const editDict = (params) => {
    return postRequest('/dict/edit', params)
}
// 删除字典
export const deleteDict = (params) => {
    return postRequest('/dict/delByIds', params)
}
// 搜索字典
export const searchDict = (params) => {
    return getRequest('/dict/search', params)
}
// 获取全部字典数据
export const getAllDictDataList = (params) => {
    return getRequest('/dictData/getByCondition', params)
}
// 添加字典数据
export const addDictData = (params) => {
    return postRequest('/dictData/add', params)
}
// 编辑字典数据
export const editDictData = (params) => {
    return postRequest('/dictData/edit', params)
}
// 删除字典数据
export const deleteData = (params) => {
    return postRequest('/dictData/delByIds', params)
}
// 通过类型获取字典数据
export const getDictDataByType = (type, params) => {
    return getRequest(`/dictData/getByType/${type}`, params)
}



// 分页获取日志数据
export const getLogListData = (params) => {
    return getRequest('/log/getAllByPage', params)
}
// 删除日志
export const deleteLog = (params) => {
    return postRequest('/log/delByIds', params)
}
// 清空日志
export const deleteAllLog = (params) => {
    return postRequest('/log/delAll', params)
}


// 分页获取Redis数据
export const getRedisData = (params) => {
    return getRequest('/redis/getAllByPage', params)
}
// 通过key获取Redis信息
export const getRedisByKey = (key, params) => {
    return getRequest(`/redis/getByKey/${key}`, params)
}
// 获取Redis键值数量
export const getRedisKeySize = (params) => {
    return getRequest('/redis/getKeySize', params)
}
// 获取Redis内存
export const getRedisMemory = (params) => {
    return getRequest('/redis/getMemory', params)
}
// 获取Redis信息
export const getRedisInfo = (params) => {
    return getRequest('/redis/info', params)
}
// 添加编辑Redis
export const saveRedis = (params) => {
    return postRequest('/redis/save', params)
}
// 删除Redis
export const deleteRedis = (params) => {
    return postRequest('/redis/delByKeys', params)
}
// 清空Redis
export const deleteAllRedis = (params) => {
    return postRequest('/redis/delAll', params)
}



// 分页获取定时任务数据
export const getQuartzListData = (params) => {
    return getRequest('/quartzJob/getAllByPage', params)
}
// 添加定时任务
export const addQuartz = (params) => {
    return postRequest('/quartzJob/add', params)
}
// 编辑定时任务
export const editQuartz = (params) => {
    return postRequest('/quartzJob/edit', params)
}
// 暂停定时任务
export const pauseQuartz = (params) => {
    return postRequest('/quartzJob/pause', params)
}
// 恢复定时任务
export const resumeQuartz = (params) => {
    return postRequest('/quartzJob/resume', params)
}
// 删除定时任务
export const deleteQuartz = (params) => {
    return postRequest('/quartzJob/delByIds', params)
}



// base64上传
export const base64Upload = (params) => {
    return postRequest('/upload/file', params)
}


//###########################################################################新增定制需求##################################################################
//费率相关
export const getFeeRateListData = (params) => {
    return getRequest('/rateConfig/getByCondition', params)
}
export const getMineFeeList = (params) => {
    return getRequest('/rateConfig/getMineFeeList', params)
}

export const addFeeRateListData = (params) => {
    return postRequest('/rateConfig/save', params)
}

export const deleteFeeRateListData = (params) => {
    return postRequest('/rateConfig/delByIds', params)
}

export const editFeeRateListData = (params) => {
    return putRequest('/rateConfig/update', params)
}


//客户相关

export const getClientInfoListData = (params) => {
    return getRequest('/clientInfo/getByCondition', params)
}

export const addClientInfoData = (params) => {
    return postRequest('/clientInfo/save', params)
}

export const deleteClientInfoData = (params) => {
    return postRequest('/clientInfo/delByIds', params)
}

export const editClientInfoData = (params) => {
    return putRequest('/clientInfo/update', params)
}

//车型相关

export const getCarTypeListData = (params) => {
    return getRequest('/carType/getByCondition', params)
}

export const addCarTypeData = (params) => {
    return postRequest('/carType/save', params)
}

export const deleteCarTypeData = (params) => {
    return postRequest('/carType/delByIds', params)
}

export const editCarTypeData = (params) => {
    return putRequest('/carType/update', params)
}

//月折旧率配置
export const getCarDepreciationListData = (params) => {
    return getRequest('/carDepreciation/getByCondition', params)
}

export const addCarDepreciationData = (params) => {
    return postRequest('/carDepreciation/save', params)
}

export const deleteCarDepreciationData = (params) => {
    return postRequest('/carDepreciation/delByIds', params)
}

export const editCarDepreciationData = (params) => {
    return putRequest('/carDepreciation/update', params)
}

//统筹单相关接口

export const getOrderListData = (params) => {
    return getRequest('/order/getByCondition', params)
}

export const getValidOrderListData = (params) => {
    return getRequest('/order/getValidOrderByCondition', params)
}

export const addOrderData = (params) => {
    return postRequest('/order/save', params)
}

export const deleteOrderData = (params) => {
    return postRequest('/order/delByIds', params)
}

export const editOrderData = (params) => {
    return putRequest('/order/update', params)
}

export const orderCreate = (params) => {
    return postJsonRequest('/order/orderCreate', params)
}

export const orderEdit = (params) => {
    return postJsonRequest('/order/orderEdit', params)
}
export const orderAuditEdit = (params) => {
    return postJsonRequest('/order/orderAuditEdit', params)
}
//获取报价单
export const getBaojia = (params) => {
    return getRequest('/order/baojia', params)
}

//获取保险单
export const getInsuranceCert = (params) => {
    return getRequest('/order/insuranceCert', params)
}
//审核统筹单
export const orderAudit = (params) => {
    return postRequest('/order/audit', params)
}

//提交审核
export const orderCommitForAudit = (params) => {
    return postRequest('/order/commitForAudit', params)
}



//费率优惠政策
export const getYouhuiListData = (params) => {
    return getRequest('/youhui/getByCondition', params)
}

export const addYouhuiData = (params) => {
    return postRequest('/youhui/save', params)
}

export const deleteYouhuiData = (params) => {
    return postRequest('/youhui/delByIds', params)
}

export const editYouhuiData = (params) => {
    return putRequest('/youhui/update', params)
}
export const getMineYouhui = (params) => {
    return getRequest('/youhui/getMineYouhui', params)
}


//渠道折扣相关

export const getChannelDiscountListData = (params) => {
    return getRequest('/channelDiscount/getByCondition', params)
}

export const addChannelDiscountData = (params) => {
    return postRequest('/channelDiscount/save', params)
}

export const deleteChannelDiscountData = (params) => {
    return postRequest('/channelDiscount/delByIds', params)
}

export const editChannelDiscountData = (params) => {
    return putRequest('/channelDiscount/update', params)
}
export const loadchannelDiscounts = (id, params) => {
    return getRequest(`/channelDiscount/getByParentId/${id}`, params)
}

export const searchChannelDiscount = (params) => {
    return getRequest('/channelDiscount/search', params)
}

//获取文件

export const getFile = (fileName, params) => {
    return getFileRequest('/files/' + fileName, params)
}
export const getPdfFile = (params) => {
    return getFileRequest('/xboot/mineupload/getPdfFile', params)
}

//上传文件(本地)
export const uploadMineFile = "/xboot/mineupload/file"


//收款信息
export const getOrderPayInfoListData = (params) => {
    return getRequest('/orderPayInfo/getByCondition', params)
}

export const addOrderPayInfoData = (params) => {
    return postRequest('/orderPayInfo/save', params)
}

export const deleteOrderPayInfoData = (params) => {
    return postRequest('/orderPayInfo/delByIds', params)
}

export const editOrderPayInfoData = (params) => {
    return putRequest('/orderPayInfo/update', params)
}
//收款成功
export const orderPayInfoPaySuccess = (params) => {
    return postRequest('/orderPayInfo/paySuccess', params)
}




//互助单管理

export const getHelperOrderListData = (params) => {
    return getRequest('/helperOrder/getByCondition', params)
}

export const addHelperOrderData = (params) => {
    return postRequest('/helperOrder/save', params)
}

export const deleteHelperOrderData = (params) => {
    return postRequest('/helperOrder/delByIds', params)
}

export const editHelperOrderData = (params) => {
    return putRequest('/helperOrder/update', params)
}

export const createHelperOrderData = (params) => {
    return postRequest('/helperOrder/createHelpOrder', params)
}
export const getDiaochaUserListData = (params) => {
    return getRequest('/helperOrder/getDiaochaUserList', params)
}

export const startDiaocha = (params) => {
    return postRequest('/helperOrder/submitStartDiaocha', params)
}

//理赔相关接口

export const getDamageListData = (params) => {
    return getRequest('/helperDamageClassify/getDamageList', params)
}

export const addHelperDamageData = (params) => {
    return postRequest('/helperDamage/save', params)
}

export const addHelperDamageDetailData = (params) => {
    return postRequest('/helperDamageDetail/save', params)
}

export const editHelperDamageData = (params) => {
    return putRequest('/helperDamage/update', params)
}

export const editHelperDamageDetailData = (params) => {
    return putRequest('/helperDamageDetail/update', params)
}

export const getByHelpOrderId = (params) => {
    return getRequest('/helperOrderCompensate/getByHelpOrderId', params)
}

export const editHelperOrderCompensate = (params) => {
    return putRequest('/helperOrderCompensate/update', params)
}

export const getHelpOrderPicDatas = (params) => {
    return getRequest('/helperOrderPic/getByHelpOrderId', params)
}

export const saveHelpOrderPics = (params) => {
    return postJsonRequest('/helperOrderPic/saveHelpOrderPics', params)
}

export const helperDamageClassifyDecide = (params) => {
    return postRequest('/helperDamageClassify/decide', params)
}

export const helperOrderCheckFail = (params) => {
    return postRequest('/helperOrder/checkFail', params)
}
export const helperOrderCheckSuccess = (params) => {
    return postRequest('/helperOrder/checkSuccess', params)
}
export const helperOrderCommitForCheck = (params) => {
    return postRequest('/helperOrder/commitForCheck', params)
}


//验车码

export const getCheckCode = (params) => {
    return getRequest('/checkCode/getTodyCode', params)
}


export const getCarTypeBByCartypeA = (params) => {
    return getRequest('/carTypeRelation/carTypeB', params)
}

//统筹单操作记录
export const getOrderLogs = (params) => {
    return getRequest('/order/orderActionLog', params)
}

//批单相关
export const getOrderEditListData = (params) => {
    return getRequest('/order/getEditOrderByCondition', params)
}

//查询统筹单

export const queryOrder = (params) => {
    return getRequest('/order/queryOrder', params)
}

export const editOrderCommitForAudit = (params) => {
    return postJsonRequest('/order/editOrderCommitForAudit', params)
}

export const editOrderEdit = (params) => {
    return postJsonRequest('/order/orderEditEdit', params)
}

export const orderEditAudit = (params) => {
    return postRequest('/order/orderEditAudit', params)
}

export const getStatisticOrders = (params) => {
    return getRequest('/orderPayInfo/statisticOrder', params)
}

export const getStatisticBussiness = (params) => {
    return getRequest('/orderPayInfo/statisticBussiness', params)
}

//批单打印

export const orderEditPrint = (params) => {
    return getRequest('/order/orderEditPrint', params)
}