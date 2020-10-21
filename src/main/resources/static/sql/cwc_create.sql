/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cwc

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-10-21 10:58:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cwc_energy_usage
-- ----------------------------
DROP TABLE IF EXISTS `cwc_energy_usage`;
CREATE TABLE `cwc_energy_usage` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `VAL` double DEFAULT NULL COMMENT '值',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电表使用数据';

-- ----------------------------
-- Table structure for cwc_info
-- ----------------------------
DROP TABLE IF EXISTS `cwc_info`;
CREATE TABLE `cwc_info` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `INFO` varchar(255) DEFAULT NULL COMMENT '厕所名称',
  `RECORD_TIME` datetime DEFAULT NULL COMMENT '记录时间',
  `LOCATION` bigint(11) DEFAULT NULL COMMENT '厕所位置',
  `PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='厕所配置信息';

-- ----------------------------
-- Table structure for cwc_score
-- ----------------------------
DROP TABLE IF EXISTS `cwc_score`;
CREATE TABLE `cwc_score` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `TIME` datetime DEFAULT NULL COMMENT '时间',
  `SCORE` int(11) DEFAULT NULL COMMENT '分数',
  `WC_ID` int(11) DEFAULT NULL COMMENT '厕所ID',
  `INFO` varchar(255) DEFAULT NULL COMMENT '厕所名称',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='厕所评分';

-- ----------------------------
-- Table structure for cwc_sensor_config
-- ----------------------------
DROP TABLE IF EXISTS `cwc_sensor_config`;
CREATE TABLE `cwc_sensor_config` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MODBUS_ID` int(11) DEFAULT NULL COMMENT '总线ID',
  `SENSOR_TYPE` int(11) DEFAULT NULL COMMENT '传感器类型',
  `OUT_ID` int(11) DEFAULT NULL COMMENT '输出ID',
  `LIMIT_VAL` double DEFAULT NULL COMMENT '动作值',
  `LIMIT_DOWN_VAL` double DEFAULT NULL COMMENT '解除值',
  `TIME` datetime DEFAULT NULL COMMENT '时间',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='传感器配置信息';

-- ----------------------------
-- Table structure for cwc_sensor_data
-- ----------------------------
DROP TABLE IF EXISTS `cwc_sensor_data`;
CREATE TABLE `cwc_sensor_data` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIME` datetime DEFAULT NULL COMMENT '记录时间',
  `MODBUS_ID` int(11) DEFAULT NULL COMMENT '总线ID',
  `SENSOR_TYPE` int(11) DEFAULT NULL COMMENT '传感器类型',
  `SENSOR_ID` int(11) DEFAULT NULL COMMENT '传感器ID',
  `VALUE1` double DEFAULT NULL COMMENT '值1',
  `VALUE2` double DEFAULT NULL COMMENT '值2',
  `VALUE3` double DEFAULT NULL COMMENT '值3',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='传感器数据';

-- ----------------------------
-- Table structure for cwc_set_data
-- ----------------------------
DROP TABLE IF EXISTS `cwc_set_data`;
CREATE TABLE `cwc_set_data` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `WC_ID` int(11) DEFAULT NULL COMMENT '厕所ID',
  `WC_SET_ID` int(11) DEFAULT NULL COMMENT '厕位ID',
  `START_TIME` datetime DEFAULT NULL COMMENT '开始时间',
  `TIME` int(11) DEFAULT NULL COMMENT '持续时间',
  `WC_TYPE` int(11) DEFAULT NULL COMMENT '厕所类型',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='厕位数据';

-- ----------------------------
-- Table structure for cwc_set_info
-- ----------------------------
DROP TABLE IF EXISTS `cwc_set_info`;
CREATE TABLE `cwc_set_info` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ZIGBEE_M_ID` int(11) DEFAULT NULL COMMENT '总线ID',
  `ZIGBEE_B_ID` int(11) DEFAULT NULL COMMENT '厕位标识ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `WC_TYPE` int(11) DEFAULT NULL COMMENT '厕所类型',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='厕位配置信息';

-- ----------------------------
-- Table structure for cwc_warn_data
-- ----------------------------
DROP TABLE IF EXISTS `cwc_warn_data`;
CREATE TABLE `cwc_warn_data` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `WC_ID` int(11) DEFAULT NULL COMMENT '厕所ID',
  `SWITCH_ID` int(11) DEFAULT NULL COMMENT '转换器ID',
  `TIME` datetime DEFAULT NULL COMMENT '报警时间',
  `DISPOSE_TIME` datetime DEFAULT NULL COMMENT '处置时间',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态',
  `DISPOSE_INFO` varchar(255) DEFAULT NULL COMMENT '处置信息',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='厕所报警数据';

-- ----------------------------
-- Table structure for cwc_water_usage
-- ----------------------------
DROP TABLE IF EXISTS `cwc_water_usage`;
CREATE TABLE `cwc_water_usage` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `VAL` double DEFAULT NULL COMMENT '值',
  `MAC_CODE` varchar(255) DEFAULT NULL COMMENT '物理地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='水表使用数据';

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `SERIES` varchar(64) NOT NULL,
  `USERNAME` varchar(64) DEFAULT NULL,
  `TOKEN` varchar(64) DEFAULT NULL,
  `LAST_USED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`SERIES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `AUTHORITY_ID` varchar(255) NOT NULL COMMENT '权限id',
  `AUTHORITY_NAME` varchar(255) NOT NULL COMMENT '权限名称，ROLE_开头，全大写',
  `AUTHORITY_REMARK` varchar(255) NOT NULL COMMENT '权限描述',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '修改时间',
  `AUTHORITY_CONTENT` varchar(255) NOT NULL COMMENT '权限内容，可访问的url，多个时用,隔开',
  PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限管理';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` varchar(255) NOT NULL COMMENT '菜单id',
  `MENU_NAME` varchar(255) NOT NULL COMMENT '菜单名称',
  `MENU_PATH` varchar(255) NOT NULL COMMENT '菜单路径',
  `MENU_PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级id',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='菜单管理';

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting` (
  `ID` varchar(255) NOT NULL COMMENT '表id',
  `SYS_NAME` varchar(255) DEFAULT NULL COMMENT '系统名称',
  `SYS_LOGO` varchar(255) DEFAULT NULL COMMENT '系统logo图标',
  `SYS_BOTTOM_TEXT` varchar(255) DEFAULT NULL COMMENT '系统底部信息',
  `SYS_NOTICE_TEXT` longtext COMMENT '系统公告',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `USER_INIT_PASSWORD` varchar(255) DEFAULT NULL COMMENT '用户管理：初始、重置密码',
  `SYS_COLOR` varchar(255) DEFAULT NULL COMMENT '系统颜色',
  `SYS_API_ENCRYPT` char(1) DEFAULT NULL COMMENT 'API加密 Y/N',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统设置';

-- ----------------------------
-- Table structure for sys_shortcut_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_shortcut_menu`;
CREATE TABLE `sys_shortcut_menu` (
  `SHORTCUT_MENU_ID` varchar(255) NOT NULL COMMENT '用户快捷菜单id',
  `SHORTCUT_MENU_NAME` varchar(255) NOT NULL COMMENT '用户快捷菜单名称',
  `SHORTCUT_MENU_PATH` varchar(255) NOT NULL COMMENT '用户快捷菜单路径',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户id',
  `SHORTCUT_MENU_PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级id',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`SHORTCUT_MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户快捷菜单表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(255) NOT NULL COMMENT '用户id',
  `LOGIN_NAME` varchar(255) NOT NULL COMMENT '登录名',
  `USER_NAME` varchar(255) NOT NULL COMMENT '用户名称',
  `PASSWORD` varchar(255) NOT NULL COMMENT '登录密码',
  `VALID` char(1) NOT NULL COMMENT '软删除标识，Y/N',
  `LIMITED_IP` varchar(255) DEFAULT NULL COMMENT '限制允许登录的IP集合',
  `EXPIRED_TIME` datetime DEFAULT NULL COMMENT '账号失效时间，超过时间将不能登录系统',
  `LAST_CHANGE_PWD_TIME` datetime NOT NULL COMMENT '最近修改密码时间，超出时间间隔，提示用户修改密码',
  `LIMIT_MULTI_LOGIN` char(1) NOT NULL COMMENT '是否允许账号同一个时刻多人在线，Y/N',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户管理';

-- ----------------------------
-- Table structure for sys_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_authority`;
CREATE TABLE `sys_user_authority` (
  `USER_AUTHORITY_ID` varchar(255) NOT NULL COMMENT '用户权限表id',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户id',
  `AUTHORITY_ID` varchar(255) NOT NULL COMMENT '权限id',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`USER_AUTHORITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户权限表';

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu` (
  `USER_MENU_ID` varchar(255) NOT NULL COMMENT '用户菜单表id',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户id',
  `MENU_ID` varchar(255) NOT NULL COMMENT '菜单id',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`USER_MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户菜单表';
