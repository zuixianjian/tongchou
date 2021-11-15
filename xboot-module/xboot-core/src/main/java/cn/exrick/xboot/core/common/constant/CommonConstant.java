package cn.exrick.xboot.core.common.constant;


/**
 * 常量
 * @author Exrickx
 */
public interface CommonConstant {

    /**
     * 用户默认头像
     */
    String USER_DEFAULT_AVATAR = "https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png";

    /**
     * 用户正常状态
     */
    Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户禁用状态
     */
    Integer USER_STATUS_LOCK = -1;

    /**
     * 普通用户
     */
    Integer USER_TYPE_NORMAL = 0;

    /**
     * 管理员
     */
    Integer USER_TYPE_ADMIN = 1;

    /**
     * 全部数据权限
     */
    Integer DATA_TYPE_ALL = 0;

    /**
     * 自定义数据权限
     */
    Integer DATA_TYPE_CUSTOM = 1;

    /**
     * 本部门及以下
     */
    Integer DATA_TYPE_UNDER = 2;

    /**
     * 本部门
     */
    Integer DATA_TYPE_SAME = 3;

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    Integer DEL_FLAG = 1;

    /**
     * 限流标识
     */
    String LIMIT_ALL = "XBOOT_LIMIT_ALL";

    /**
     * 顶部菜单类型权限
     */
    Integer PERMISSION_NAV = -1;

    /**
     * 页面类型权限
     */
    Integer PERMISSION_PAGE = 0;

    /**
     * 操作类型权限
     */
    Integer PERMISSION_OPERATION = 1;

    /**
     * 1级菜单父id
     */
    String PARENT_ID = "0";

    /**
     * 0级菜单
     */
    Integer LEVEL_ZERO = 0;

    /**
     * 1级菜单
     */
    Integer LEVEL_ONE = 1;

    /**
     * 2级菜单
     */
    Integer LEVEL_TWO = 2;

    /**
     * 3级菜单
     */
    Integer LEVEL_THREE = 3;

    /**
     * 部门负责人类型 主负责人
     */
    Integer HEADER_TYPE_MAIN = 0;

    /**
     * 部门负责人类型 副负责人
     */
    Integer HEADER_TYPE_VICE = 1;

    /**
     * 禁用词 英文请小写
     */
    String[] STOP_WORDS = {"anonymoususer"};


    /**
     * 业务相关数据
     */

    String ORDER_TYPE_COMMON="ORDER_TYPE_COMMON";//统筹单
    String ORDER_TYPE_EDIT="ORDER_TYPE_EDIT";//批单
    String ORDER_TYPE_HELP="ORDER_TYPE_HELP";//互助单
    /**
     * 统筹单状态
     */
    String ORDER_STATUS_ZANCUN="ORDER_STATUS_ZANCUN"; //暂存

    String ORDER_STATUS_WAIT_AUDIT="ORDER_STATUS_WAIT_AUDIT"; //待审核  批单同状态

    String ORDER_STATUS_AUDIT_FAILURE="ORDER_STATUS_AUDIT_FAILURE"; //审核不通过   批单同状态
    String ORDER_STATUS_WAITE_PAY="ORDER_STATUS_WAITE_PAY"; //待付款   批单同状态

    String ORDER_STATUS_FINISH="ORDER_STATUS_FINISH"; //已生效   批单同状态

    String ORDER_STATUS_INVILADE="ORDER_STATUS_INVILADE"; //已过期
    /**
     * 收款单状态
     */
    String PAYINFO_STATUS_WAIT="PAYINFO_STATUS_WAIT"; //待收款
    String PAYINFO_STATUS_SUCCESS="PAYINFO_STATUS_SUCCESS"; //已收款

    String PAYINFO_STATUS_WAIT_TO="PAYINFO_STATUS_WAIT_TO"; //待付款
    String PAYINFO_STATUS_SUCCESS_TO="PAYINFO_STATUS_SUCCESS_TO"; //已付款

    /**
     * 互助单状态
     */
    String HELPORDER_STATUS_ZANCUN="HELPORDER_STATUS_ZANCUN"; //暂存
    String HELPORDER_STATUS_DIAOCHA="HELPORDER_STATUS_DIAOCHA"; //正在调查
    String HELPORDER_STATUS_CHECK="HELPORDER_STATUS_CHECK"; //正在审核
    String HELPORDER_STATUS_CHECK_FAIL="HELPORDER_STATUS_CHECK_FAIL"; //审核不通过
    String HELPORDER_STATUS_WAITFORPAY="HELPORDER_STATUS_WAITFORPAY"; //待结案
    String HELPORDER_STATUS_FINISH="HELPORDER_STATUS_FINISH"; //已结案
}
