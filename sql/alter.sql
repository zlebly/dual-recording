-- usr_function_tree 新增字段
alter table usr_function_tree add COLUMN path VARCHAR(20);
alter table usr_function_tree add COLUMN component VARCHAR(50);
alter table usr_function_tree add COLUMN query VARCHAR(20);
alter table usr_function_tree add COLUMN is_frame char(1);
alter table usr_function_tree add COLUMN is_cache char(1);
alter table usr_function_tree add COLUMN perms VARCHAR(50);

-- 增加sys_post
-- 岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
    post_id       bigint(20)      not null auto_increment    comment '岗位ID',
    post_code     varchar(64)     not null                   comment '岗位编码',
    post_name     varchar(50)     not null                   comment '岗位名称',
    post_sort     int(4)          not null                   comment '显示顺序',
    status        char(1)         not null                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   datetime                                   comment '创建时间',
    update_by     varchar(64)     default ''			       comment '更新者',
    update_time   datetime                                   comment '更新时间',
    remark        varchar(500)    default null               comment '备注',
    primary key (post_id)
) engine=innodb comment = '岗位信息表';

-- 增加sys_user_post
-- 用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
    user_id   bigint(20) not null comment '用户ID',
    post_id   bigint(20) not null comment '岗位ID',
    primary key (user_id, post_id)
) engine=innodb comment = '用户与岗位关联表';

-- ----------------------------
-- 参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config (
                            config_id         int(5)          not null auto_increment    comment '参数主键',
                            config_name       varchar(100)    default ''                 comment '参数名称',
                            config_key        varchar(100)    default ''                 comment '参数键名',
                            config_value      varchar(500)    default ''                 comment '参数键值',
                            config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
                            create_by         varchar(64)     default ''                 comment '创建者',
                            create_time       datetime                                   comment '创建时间',
                            update_by         varchar(64)     default ''                 comment '更新者',
                            update_time       datetime                                   comment '更新时间',
                            remark            varchar(500)    default null               comment '备注',
                            primary key (config_id)
) engine=innodb auto_increment=100 comment = '参数配置表';

insert into sys_config values(1, '主框架页-默认皮肤样式名称',     'sys.index.skinName',            'skin-blue',     'Y', 'admin', sysdate(), '', null, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow' );
insert into sys_config values(2, '用户管理-账号初始密码',         'sys.user.initPassword',         '123456',        'Y', 'admin', sysdate(), '', null, '初始化密码 123456' );
insert into sys_config values(3, '主框架页-侧边栏主题',           'sys.index.sideTheme',           'theme-dark',    'Y', 'admin', sysdate(), '', null, '深色主题theme-dark，浅色主题theme-light' );
insert into sys_config values(4, '账号自助-验证码开关',           'sys.account.captchaEnabled',    'true',          'Y', 'admin', sysdate(), '', null, '是否开启验证码功能（true开启，false关闭）');
insert into sys_config values(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser',      'false',         'Y', 'admin', sysdate(), '', null, '是否开启注册用户功能（true开启，false关闭）');
insert into sys_config values(6, '用户登录-黑名单列表',           'sys.login.blackIPList',         '',              'Y', 'admin', sysdate(), '', null, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');


-- ----------------------------
-- 操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
                              oper_id           bigint(20)      not null auto_increment    comment '日志主键',
                              title             varchar(50)     default ''                 comment '模块标题',
                              business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
                              method            varchar(200)    default ''                 comment '方法名称',
                              request_method    varchar(10)     default ''                 comment '请求方式',
                              operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
                              oper_name         varchar(50)     default ''                 comment '操作人员',
                              org_name          varchar(50)     default ''                 comment '机构名称',
                              oper_url          varchar(255)    default ''                 comment '请求URL',
                              oper_ip           varchar(128)    default ''                 comment '主机地址',
                              oper_location     varchar(255)    default ''                 comment '操作地点',
                              oper_param        varchar(2000)   default ''                 comment '请求参数',
                              json_result       varchar(2000)   default ''                 comment '返回参数',
                              status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
                              error_msg         varchar(2000)   default ''                 comment '错误消息',
                              oper_time         datetime                                   comment '操作时间',
                              cost_time         bigint(20)      default 0                  comment '消耗时间',
                              primary key (oper_id),
                              key idx_sys_oper_log_bt (business_type),
                              key idx_sys_oper_log_s  (status),
                              key idx_sys_oper_log_ot (oper_time)
) engine=innodb auto_increment=100 comment = '操作日志记录';

-- ----------------------------
-- 新增用户头像表
-- ----------------------------
drop table if exists usr_avatar;
create table usr_avatar (
                             user_id             bigint(20)      not null                   comment '用户ID',
                             avatar             varchar(500)    default ''                 comment '头像URL',
                             primary key (user_id)
);

-- -----------------
-- 菜单表新增
-- -------------------
insert into usr_function_tree values ('1','系统管理','0',NULL,'4',NULL,NULL,NULL,'system',NULL,'8974','0',NULL,NULL,NULL,'system',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('2','系统监控','0',NULL,'5',NULL,NULL,NULL,'monitor',NULL,'8974','0',NULL,NULL,NULL,'monitor',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('3','系统工具','0',NULL,'6',NULL,NULL,NULL,'tool',NULL,'8974','0',NULL,NULL,NULL,'tool',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('100','用户管理','1',NULL,'1',NULL,NULL,NULL,'user',NULL,'8975','0',NULL,NULL,NULL,'user','system/user/index',NULL,'1','0','system:user:list');
insert into usr_function_tree values ('101','角色管理','1',NULL,'2',NULL,NULL,NULL,'peoples',NULL,'8975','0',NULL,NULL,NULL,'role','system/role/index',NULL,'1','0','system:role:list');
insert into usr_function_tree values ('102','菜单管理','1',NULL,'3',NULL,NULL,NULL,'tree-table',NULL,'8975','0',NULL,NULL,NULL,'menu','system/menu/index',NULL,'1','0','system:menu:list');
insert into usr_function_tree values ('103','机构管理','1',NULL,'4',NULL,NULL,NULL,'tree',NULL,'8975','0',NULL,NULL,NULL,'dept','system/org/index',NULL,'1','0','system:org:list');
insert into usr_function_tree values ('104','岗位管理','1',NULL,'5',NULL,NULL,NULL,'post',NULL,'8975','0',NULL,NULL,NULL,'post','system/post/index',NULL,'1','0','system:post:list');
insert into usr_function_tree values ('105','字典管理','1',NULL,'6',NULL,NULL,NULL,'dict',NULL,'8975','0',NULL,NULL,NULL,'dict','system/dict/index',NULL,'1','0','system:dict:list');
insert into usr_function_tree values ('106','参数设置','1',NULL,'7',NULL,NULL,NULL,'edit',NULL,'8975','0',NULL,NULL,NULL,'config','system/config/index',NULL,'1','0','system:config:list');
insert into usr_function_tree values ('107','通知公告','1',NULL,'8',NULL,NULL,NULL,'message',NULL,'8975','0',NULL,NULL,NULL,'notice','system/notice/index',NULL,'1','0','system:notice:list');
insert into usr_function_tree values ('108','日志管理','1',NULL,'9',NULL,NULL,NULL,'log',NULL,'8974','0',NULL,NULL,NULL,'log',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('109','在线用户','2',NULL,'1',NULL,NULL,NULL,'online',NULL,'8975','0',NULL,NULL,NULL,'online','monitor/online/index',NULL,'1','0','monitor:online:list');
insert into usr_function_tree values ('110','定时任务','2',NULL,'2',NULL,NULL,NULL,'job',NULL,'8975','0',NULL,NULL,NULL,'job','monitor/job/index',NULL,'1','0','monitor:job:list');
insert into usr_function_tree values ('111','数据监控','2',NULL,'3',NULL,NULL,NULL,'druid',NULL,'8975','0',NULL,NULL,NULL,'druid','monitor/druid/index',NULL,'1','0','monitor:druid:list');
insert into usr_function_tree values ('112','服务监控','2',NULL,'4',NULL,NULL,NULL,'server',NULL,'8975','0',NULL,NULL,NULL,'server','monitor/server/index',NULL,'1','0','monitor:server:list');
insert into usr_function_tree values ('113','缓存监控','2',NULL,'5',NULL,NULL,NULL,'redis',NULL,'8975','0',NULL,NULL,NULL,'cache','monitor/cache/index',NULL,'1','0','monitor:cache:list');
insert into usr_function_tree values ('114','缓存列表','2',NULL,'6',NULL,NULL,NULL,'redis-list',NULL,'8975','0',NULL,NULL,NULL,'cacheList','monitor/cache/list',NULL,'1','0','monitor:cache:list');
insert into usr_function_tree values ('117','系统接口','3',NULL,'3',NULL,NULL,NULL,'swagger',NULL,'8975','0',NULL,NULL,NULL,'swagger','tool/swagger/index',NULL,'1','0','tool:swagger:list');
insert into usr_function_tree values ('500','操作日志','108',NULL,'1',NULL,NULL,NULL,'form',NULL,'8975','0',NULL,NULL,NULL,'operlog','monitor/operlog/index',NULL,'1','0','monitor:operlog:list');
insert into usr_function_tree values ('501','登录日志','108',NULL,'2',NULL,NULL,NULL,'logininfor',NULL,'8975','0',NULL,NULL,NULL,'logininfor','monitor/logininfor/index',NULL,'1','0','monitor:logininfor:list');
insert into usr_function_tree values ('1000','用户查询','100',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:user:query');
insert into usr_function_tree values ('1001','用户新增','100',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:user:add');
insert into usr_function_tree values ('1002','用户修改','100',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:user:edit');
insert into usr_function_tree values ('1003','用户删除','100',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:user:remove');
insert into usr_function_tree values ('1004','用户导出','100',NULL,'5',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:user:export');
insert into usr_function_tree values ('1005','用户导入','100',NULL,'6',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:user:import');
insert into usr_function_tree values ('1006','重置密码','100',NULL,'7',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:user:resetPwd');
insert into usr_function_tree values ('1007','角色查询','101',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:role:query');
insert into usr_function_tree values ('1008','角色新增','101',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:role:add');
insert into usr_function_tree values ('1009','角色修改','101',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:role:edit');
insert into usr_function_tree values ('1010','角色删除','101',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:role:remove');
insert into usr_function_tree values ('1011','角色导出','101',NULL,'5',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:role:export');
insert into usr_function_tree values ('1012','菜单查询','102',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:menu:query');
insert into usr_function_tree values ('1013','菜单新增','102',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:menu:add');
insert into usr_function_tree values ('1014','菜单修改','102',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:menu:edit');
insert into usr_function_tree values ('1015','菜单删除','102',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:menu:remove');
insert into usr_function_tree values ('1016','部门查询','103',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:org:query');
insert into usr_function_tree values ('1017','部门新增','103',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:org:add');
insert into usr_function_tree values ('1018','部门修改','103',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:org:edit');
insert into usr_function_tree values ('1019','部门删除','103',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:org:remove');
insert into usr_function_tree values ('1020','岗位查询','104',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:post:query');
insert into usr_function_tree values ('1021','岗位新增','104',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:post:add');
insert into usr_function_tree values ('1022','岗位修改','104',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:post:edit');
insert into usr_function_tree values ('1023','岗位删除','104',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:post:remove');
insert into usr_function_tree values ('1024','岗位导出','104',NULL,'5',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,NULL,NULL,NULL,'1','0','system:post:export');
insert into usr_function_tree values ('1025','字典查询','105',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:dict:query');
insert into usr_function_tree values ('1026','字典新增','105',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:dict:add');
insert into usr_function_tree values ('1027','字典修改','105',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:dict:edit');
insert into usr_function_tree values ('1028','字典删除','105',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:dict:remove');
insert into usr_function_tree values ('1029','字典导出','105',NULL,'5',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:dict:export');
insert into usr_function_tree values ('1030','参数查询','106',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:config:query');
insert into usr_function_tree values ('1031','参数新增','106',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:config:add');
insert into usr_function_tree values ('1032','参数修改','106',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:config:edit');
insert into usr_function_tree values ('1033','参数删除','106',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:config:remove');
insert into usr_function_tree values ('1034','参数导出','106',NULL,'5',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:config:export');
insert into usr_function_tree values ('1035','公告查询','107',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:notice:query');
insert into usr_function_tree values ('1036','公告新增','107',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:notice:add');
insert into usr_function_tree values ('1037','公告修改','107',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:notice:edit');
insert into usr_function_tree values ('1038','公告删除','107',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','system:notice:remove');
insert into usr_function_tree values ('1039','操作查询','500',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:operlog:query');
insert into usr_function_tree values ('1040','操作删除','500',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:operlog:remove');
insert into usr_function_tree values ('1041','日志导出','500',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:operlog:export');
insert into usr_function_tree values ('1042','登录查询','501',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:logininfor:query');
insert into usr_function_tree values ('1043','登录删除','501',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:logininfor:remove');
insert into usr_function_tree values ('1044','日志导出','501',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:logininfor:export');
insert into usr_function_tree values ('1045','账户解锁','501',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:logininfor:unlock');
insert into usr_function_tree values ('1046','在线查询','109',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:online:query');
insert into usr_function_tree values ('1047','批量强退','109',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:online:batchLogout');
insert into usr_function_tree values ('1048','单条强退','109',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:online:forceLogout');
insert into usr_function_tree values ('1049','任务查询','110',NULL,'1',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:job:query');
insert into usr_function_tree values ('1050','任务新增','110',NULL,'2',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:job:add');
insert into usr_function_tree values ('1051','任务修改','110',NULL,'3',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:job:edit');
insert into usr_function_tree values ('1052','任务删除','110',NULL,'4',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:job:remove');
insert into usr_function_tree values ('1053','状态修改','110',NULL,'5',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:job:changeStatus');
insert into usr_function_tree values ('1054','任务导出','110',NULL,'6',NULL,NULL,NULL,'#',NULL,'8976','0',NULL,NULL,NULL,'#',NULL,NULL,'1','0','monitor:job:export');
insert into usr_function_tree values ('2000','业务管理','0',NULL,'1',NULL,NULL,NULL,'clipboard',NULL,'8974','0',NULL,NULL,NULL,'product',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('2001','产品信息管理','2000',NULL,'1',NULL,NULL,NULL,'redis-list',NULL,'8975','0',NULL,NULL,NULL,'list','productInfo/index',NULL,'1','0','productInfo:list');
insert into usr_function_tree values ('2002','双录管理','0',NULL,'2',NULL,NULL,NULL,'list',NULL,'8974','0',NULL,NULL,NULL,'dualRecording',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('2003','客户信息录入','2002',NULL,'1',NULL,NULL,NULL,'peoples',NULL,'8975','0',NULL,NULL,NULL,'addCustomerInfo','dualRecording/addCustomerInfo/index',NULL,'1','0','dualRecording:addCustomerInfo');
insert into usr_function_tree values ('2004','双录录制','2002',NULL,'2',NULL,NULL,NULL,'radio',NULL,'8974','0',NULL,NULL,NULL,'2',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('2005','双录查看','2002',NULL,'3',NULL,NULL,NULL,'eye-open',NULL,'8974','0',NULL,NULL,NULL,'3',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('2006','报表中心','0',NULL,'3',NULL,NULL,NULL,'chart',NULL,'8974','0',NULL,NULL,NULL,'report',NULL,NULL,'1','0',NULL);
insert into usr_function_tree values ('2007','用户双录视频数量统计','2006',NULL,'1',NULL,NULL,NULL,'logininfor',NULL,'8975','0',NULL,NULL,NULL,'getCustomerReport','report/customerRecordReport/index',NULL,'1','0','report:customer');
insert into usr_function_tree values ('2008','机构双录视频数量统计','2006',NULL,'2',NULL,NULL,NULL,'monitor',NULL,'8975','0',NULL,NULL,NULL,'getOrgReport','report/organRecordReport/index',NULL,'1','0','report:organ');


-- 新增角色菜单配置
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 2);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 3);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 100);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 101);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 102);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 103);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 104);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 105);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 106);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 107);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 108);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 109);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 110);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 111);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 112);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 113);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 114);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 117);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 500);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 501);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1000);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1001);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1002);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1003);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1004);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1005);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1006);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1007);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1008);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1009);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1010);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1011);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1012);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1013);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1014);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1015);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1016);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1017);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1018);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1019);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1020);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1021);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1022);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1023);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1024);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1025);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1026);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1027);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1028);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1029);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1030);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1031);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1032);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1033);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1034);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1035);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1036);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1037);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1038);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1039);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1040);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1041);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1042);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1043);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1044);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1045);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1046);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1047);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1048);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1049);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1050);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1051);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1052);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1053);
INSERT INTO `usr_role_function` (`role_code`, `function_code`) VALUES ('RZ001', 1054);


-- ---------------
-- 字典表新增数据
-- ---------------
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`,`stop_flag`) VALUES (101, 1, '男', '0', '9001',NULL,'2013-12-25',NULL,'sys_user_sex','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (102, 2, '女', '1', '9001', NULL,'2013-12-25',NULL,'sys_user_sex','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (103, 3, '未知', '2', '9001', NULL,'2013-12-25',NULL,'sys_user_sex','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (104, 1, '显示', '0', '9002', NULL,'2013-12-25',NULL,'sys_show_hide','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (105, 2, '隐藏', '1', '9002', NULL,'2013-12-25',NULL,'sys_show_hide','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (106, 1, '正常', '0', '9003', NULL,'2013-12-25',NULL,'sys_normal_disable','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (107, 2, '停用', '1', '9003', NULL,'2013-12-25',NULL,'sys_normal_disable','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (108, 1, '正常', '0', '9004', NULL,'2013-12-25',NULL,'sys_job_status','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (109, 2, '暂停', '1', '9004', NULL,'2013-12-25',NULL,'sys_job_status','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (110, 1, '默认', 'DEFAULT', '9005', NULL,'2013-12-25',NULL,'sys_job_group','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (111, 2, '系统', 'SYSTEM', '9005', NULL,'2013-12-25',NULL,'sys_job_group','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (112, 1, '是', 'Y', '9006', NULL,'2013-12-25',NULL,'sys_yes_no','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (113, 2, '否', 'N', '9006', NULL,'2013-12-25',NULL,'sys_yes_no','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (114, 1, '通知', '1', '9007', NULL,'2013-12-25',NULL,'sys_notice_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (115, 2, '公告', '2', '9007', NULL,'2013-12-25',NULL,'sys_notice_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (116, 1, '正常', '0', '9008', NULL,'2013-12-25',NULL,'sys_notice_status','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (117, 2, '关闭', '1', '9008', NULL,'2013-12-25',NULL,'sys_notice_status','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (118, 99, '其他', '0', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (119, 1, '新增', '1', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (120, 2, '修改', '2', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (121, 3, '删除', '3', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (122, 4, '授权', '4', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (123, 5, '导出', '5', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (124, 6, '导入', '6', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (125, 7, '强退', '7', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (126, 8, '生成代码', '8', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (127, 9, '清空数据', '9', '9009', NULL,'2013-12-25',NULL,'sys_oper_type','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (128, 1, '成功', '0', '9010', NULL,'2013-12-25',NULL,'sys_common_status','0');
INSERT INTO `sys_data_dict` (`id`, `view_index`, `dict_name`, `dict_code`, `dict_type`,`remark`,`enable_date`,`expire_date`,`rel_dict_code`, `stop_flag`) VALUES (129, 2, '失败', '1', '9010', NULL,'2013-12-25',NULL,'sys_common_status','0');


-- --------------
-- 字典类型表新增数据
-- --------------
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (1, '-1','用户性别', 'sys_user_sex', '0', NULL, NULL, NULL, NULL, '用户性别列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (2, '-1','菜单状态', 'sys_show_hide', '0', NULL, NULL, NULL, NULL, '菜单状态列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (3, '-1','系统开关', 'sys_normal_disable', '0', NULL, NULL, NULL, NULL, '系统开关列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (4, '-1','任务状态', 'sys_job_status', '0', NULL, NULL, NULL, NULL, '任务状态列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (5, '-1','任务分组', 'sys_job_group', '0', NULL, NULL, NULL, NULL, '任务分组列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (6, '-1','系统是否', 'sys_yes_no', '0', NULL, NULL, NULL, NULL, '系统是否列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (7, '-1','通知类型', 'sys_notice_type', '0', NULL, NULL, NULL, NULL, '通知类型列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (8, '-1','通知状态', 'sys_notice_status', '0', NULL, NULL, NULL, NULL, '通知状态列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (9, '-1','操作类型', 'sys_oper_type', '0', NULL, NULL, NULL, NULL, '操作类型列表');
INSERT INTO `sys_data_dict_type` (`id`, `dict_type`,`dict_name`, `dict_code`, `stop_flag`, `sub_dict_col`, `view_index`, `sub_dict_code`, `sub_dict_table`, `remark`) VALUES (10, '-1','系统状态', 'sys_common_status', '0', NULL, NULL, NULL, NULL, '登录状态列表');
