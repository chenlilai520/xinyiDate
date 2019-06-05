package com.example.esdemotest.conext;

/**
 * redisKEY静态值
 * <p>
 * ************************************************************************
 * 命名规则
 * <p>
 * 变量名称大写  App key前缀200开头
 * ************************************************************************
 *
 * @author Administrator
 */
public class RedisConstant {


    //**********************************  App 200前缀  ***************************************
    /**
     * app登陆token
     */
    public static final String APP_LOGIN_TOKEN = "APP_TOKEN";

    /**
     * H5微信公众号登陆token
     */
    public static final String H5_LOGIN_TOKEN = "H5_TOKEN";

    /**
     * 微信小程序登陆token
     */
    public static final String WE_CHAT_LOGIN_TOKEN = "WE_CHAT_TOKEN";

    /**
     * PC登陆token
     */
    public static final String WEB_LOGIN_TOKEN = "WEB_TOKEN";

    /**
     * PC登陆token
     */
    public static final String SYS_LOGIN_TOKEN = "SYS_TOKEN";

    /**
     * PC临时帐户
     */
    public static final String PC_TEMPORARY_ACCOUNT = "PC_TEMPORARY_ACCOUNT";

    /**
     * token
     */
    public static final String SYS_AUTHORITY = "SYS_AUTHORITY";


    /**
     * token
     */
    public static final String WEB_CHECK_PHONE = "WEB_CHECK_PHONE_";


    /**
     * PC登陆token
     */
    public static final String SMS_SERVICE_PROVIDER = "SMS_SERVICE_PROVIDER";
    /**
     * 登录密码
     * <p>
     * 规则： 200LP_ #hash
     * <p>
     */
    public static final String LOGIN_PASSWORD = "200LP_";

    /**
     * 用户信息记录
     * <p>
     * 规则： 200UAI__#hash
     * <p>
     */
    public static final String USER_ACCOUNT_ID = "200UAI_";

    /**
     * 用户信息
     */
    public static final String USER_INFO = "200LOCK_USER_INFO_";

    /**
     * 用户登录
     */
    public static final String GET_TIME_STORE_INFO = "200LOCK_GET_TIME_STORE_INFO_";

    /**
     * 验证验证码是否超时
     * <p>
     * 规则： 200SMS_LAST_#type+phone
     * <p>
     */
    public static final String CAPTCHA_DURATION_PREFIX = "200SMS_LAST_";

    /**
     * 用户验证码
     * <p>
     * 规则： 200SMS_#type+phone
     * <p>
     */
    public static final String USER_CAPTCHA = "200SMS_";

    /**
     * 数据字典 value
     * <p>
     * 规则： 200SMS_#type+phone
     * <p>
     */
    public static final String SYS_DICTIONARY_DATA_VALUE = "200SYS_DATA_VALUE_";
    /**
     * 数据字典 data
     * <p>
     * 规则： 200SMS_#type+phone
     * <p>
     */
    public static final String SYS_DICTIONARY_DATA = "200SYS_DATA_";

    /**
     * 数据字典 data
     * <p>
     * 规则： 200SMS_#type+phone
     * <p>
     */
    public static final String SYS_DICTIONARY_DATA_LABEL = "200SYS_DATA_LABEL";

    /**
     * 红包剩余数量 200BATCH_COUNT_batchId 过期时间到当前包自动过期
     */
//    public static final String BATCH_COUNT = "200BATCH_COUNT_";

    public static final String BATCH_LIST = "200BATCH_LIST_";

    /**
     * 占包的key 200BATCH_HOLD_ACCOUNT_accountId 值为batchId，过期时间为当前包的视频时长+ 可配置的输验证码时长
     */
    public static final String BATCH_HOLD_ACCOUNT = "200BATCH_HOLD_ACCOUNT_";

    /**
     * 用于redis监听，以便及时更改红包的剩余数量 2OOHOLD_BATCH_batchId_accountId 和占包key过期时间相同
     */
    public static final String HOLD_BATCH = "200HOLD_BATCH_";

    /**
     * 领包验证码 200RECEIVE_BATCH_VERIFY_verificationNum
     */
    public static final String RECEIVE_BATCH_VERIFY = "200RECEIVE_BATCH_VERIFY_";

    /**
     * 存储包预关闭状态 200BATCH_STATUS_batchId_accountId  发起关包请求的用户
     */
    public static final String BATCH_CLOSE = "200BATCH_CLOSE_";

    /**
     * 用户提现
     */
    public static final String USER_WITHDRAWAL = "200USER_WITHDRAWAL_";

    /**
     * 发包 200ADD_BATCH_accountId
     */
    public static final String ADD_BATCH = "200ADD_BATCH_";

    /**
     * 领券 200RECEIVE_VOUCHER_
     */
    public static final String RECEIVE_VOUCHER = "200RECEIVE_VOUCHER_";


    //**********************************  WEB 300前缀  ***************************************

    /**
     * pc二次发包 300ADD_BATCH_AGAIN
     */
    public static final String ADD_BATCH_AGAIN = "300ADD_BATCH_AGAIN";

    /**
     * PC新建活动页面-活动预览  300ACTIVITY_PREVIEW_
     */
    public static final String WEB_RPACTIVITY_PREVIEW = "300ACTIVITY_PREVIEW_";

    /**
     * PC制券 300WEB_MAKER_VOUCHER_ACCOUNTID
     */
    public static final String WEB_MAKER_VOUCHER = "300MAKER_VOUCHER_";

    /**
     * PC加钱 300WEB_ADD_MONEY_
     */
    public static final String WEB_ADD_MONEY = "300ADD_MONEY_";

    /**
     * PC关包 300WEB_CLOSE_BATCH_
     */
    public static final String WEB_CLOSE_BATCH = "300CLOSE_BATCH_";

    /**
     * 时间网点加时长每日任务 200TASK_EVERY_DAY
     */
    public static final String TASK_EVERY_DAY = "200TASK_EVERY_DAY";

    /**
     * 时间网点加时长每日任务记录 200TASK_EVERY_DAY_RECORD
     */
    public static final String TASK_EVERY_DAY_RECORD = "200TASK_EVERY_DAY_RECORD";

    /**
     * 添加银行卡
     */
    public static final String USER_COMPANY_BANK_ADD = "300USER_COMPANY_BANK_ADD_";

    /**
     * 申请提现
     */
    public static final String USER_WITHDRAWAL_APPLY = "300USER_WITHDRAWAL_APPLY_";

    /**
     * 网易云信批量发送自定义系统消息每分钟内剩余数
     */
    public static final String IM_BATCH_MSG_REMAINING_NUMBER = "im_batch_msg_remaining_number:";

    /**
     * 网易云信批量发送自定义系统消息每分钟内剩余数redis锁
     */
    public static final String IM_BATCH_MSG_REMAINING_NUMBER_LOCK = "im_batch_msg_remaining_number_lock";

    /**
     * 社交
     */
    public static final String USER_DATE_EXPIRE_EXECUTOR = "SC_UD_EXPIRE_EXECUTOR";

    public static final String HEART_MEANING_EXPIRE_EXECUTOR = "SC_HM_EXPIRE_EXECUTOR";

    public static final String USER_DATE_REDISTRIBUTION_EXECUTOR = "SC_UD_REDISTRIBUTION_EXECUTOR";

    public static final String RICH_PARTY_REDISTRIBUTION_EXECUTOR = "SC_RP_REDISTRIBUTION_EXECUTOR";

    public static final String RICH_PARTY_ACTIVITY_EXPIRE_EXECUTOR = "SC__RP_ACTIVITY_EXPIRE_EXECUTOR";

    public static final String RICH_PARTY_RED_PACKAGE_EXPIRE_EXECUTORR = "SC__RP_PACKAGE_EXPIRE_EXECUTORR";

    public static final String USER_DATE_EXPIRE_EXECUTOR_LOCK = "SC_UD_EXPIRE_EXECUTOR_LOCK_";

    public static final String HEART_MEANING_EXPIRE_EXECUTOR_LOCK = "SC_HM_EXPIRE_EXECUTOR_LOCK_";

    public static final String USER_DATE_REDISTRIBUTION_EXECUTOR_LOCK = "SC_UD_REDISTRIBUTION_EXECUTOR_LOCK_";

    public static final String RICH_PARTY_REDISTRIBUTION_EXECUTOR_LOCK = "SC_RP_REDISTRIBUTION_EXECUTOR_LOCK_";

    public static final String RICH_PARTY_ACTIVITY_EXPIRE_EXECUTOR_LOCK = "SC__RP_ACTIVITY_EXPIRE_EXECUTOR_LOCK_";

    public static final String RICH_PARTY_RED_PACKAGE_EXPIRE_EXECUTORR_LOCK = "SC__RP_PACKAGE_EXPIRE_EXECUTORR_LOCK_";

    public static final String USER_DATE_CREATE_TEAM = "SC_USER_DATE_CREATE_TEAM";

    public static final String USER_DATE_CREATE_TEAM_LOCK = "SC_USER_DATE_CREATE_TEAM_LOCK_";

    public static final String USER_DATE_REMOVE_TEAM_LOCK = "SC_USER_DATE_REMOVE_TEAM_LOCK_";


    public static final String SMS_MESSAGE_TIMEOUT = "SMS_MESSAGE_TIMEOUT_";


    /**
     * 待领取用户id
     */
    public static final String WAIT_RECEIVE_ACCOUNTIDS = "WAIT_RECEIVE_ACCOUNTIDS";
}
