/*
 Navicat Premium Data Transfer

 Source Server         : test.66hp.top
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : test.66hp.top:13306
 Source Schema         : tc

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 15/11/2021 20:15:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('quartzScheduler', 'cn.exrick.xboot.quartz.jobs.SampleJob', 'DEFAULT', '0 0/1 * 1/1 * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('quartzScheduler', 'cn.exrick.xboot.your.jobs.OrderValidJob', 'DEFAULT', '0 0/1 * 1/1 * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('quartzScheduler', 'cn.exrick.xboot.quartz.jobs.SampleJob', 'DEFAULT', NULL, 'cn.exrick.xboot.quartz.jobs.SampleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D65746572707800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('quartzScheduler', 'cn.exrick.xboot.your.jobs.OrderValidJob', 'DEFAULT', NULL, 'cn.exrick.xboot.your.jobs.OrderValidJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000001740009706172616D657465727400046E756C6C7800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'cn.exrick.xboot.quartz.jobs.SampleJob', 'DEFAULT', 'cn.exrick.xboot.quartz.jobs.SampleJob', 'DEFAULT', NULL, 1635210960000, 1635210900000, 5, 'PAUSED', 'CRON', 1635139689000, 0, NULL, 2, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'cn.exrick.xboot.your.jobs.OrderValidJob', 'DEFAULT', 'cn.exrick.xboot.your.jobs.OrderValidJob', 'DEFAULT', NULL, 1635210960000, 1635210900000, 5, 'PAUSED', 'CRON', 1635139804000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for b_car_depreciation
-- ----------------------------
DROP TABLE IF EXISTS `b_car_depreciation`;
CREATE TABLE `b_car_depreciation`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `month_rate` decimal(19, 2) NULL DEFAULT NULL,
  `use_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typea` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typeb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_car_depreciation
-- ----------------------------

-- ----------------------------
-- Table structure for b_car_type
-- ----------------------------
DROP TABLE IF EXISTS `b_car_type`;
CREATE TABLE `b_car_type`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `and_rate_buy_price` decimal(19, 2) NULL DEFAULT NULL,
  `buy_price` decimal(19, 2) NULL DEFAULT NULL,
  `car_new_price` decimal(19, 2) NULL DEFAULT NULL,
  `car_sieral_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `displacement` decimal(19, 2) NULL DEFAULT NULL,
  `load_capacity` decimal(19, 2) NULL DEFAULT NULL,
  `site_count` int(11) NULL DEFAULT NULL,
  `belong_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `use_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typea` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typeb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_type_name_alaise` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `kws` decimal(19, 2) NULL DEFAULT NULL,
  `productor_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sale_year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `weight` decimal(19, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_car_type
-- ----------------------------

-- ----------------------------
-- Table structure for b_car_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `b_car_type_relation`;
CREATE TABLE `b_car_type_relation`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `car_typea` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typeb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_car_type_relation
-- ----------------------------
INSERT INTO `b_car_type_relation` VALUES ('1', NULL, NULL, 0, NULL, NULL, '1', '2吨以下');
INSERT INTO `b_car_type_relation` VALUES ('10', NULL, NULL, 0, NULL, NULL, '3', '36座以上');
INSERT INTO `b_car_type_relation` VALUES ('11', NULL, NULL, 0, NULL, NULL, '4', '6座以下');
INSERT INTO `b_car_type_relation` VALUES ('12', NULL, NULL, 0, NULL, NULL, '4', '6-10座');
INSERT INTO `b_car_type_relation` VALUES ('13', NULL, NULL, 0, NULL, NULL, '4', '10-20座');
INSERT INTO `b_car_type_relation` VALUES ('14', NULL, NULL, 0, NULL, NULL, '4', '20-36座');
INSERT INTO `b_car_type_relation` VALUES ('15', NULL, NULL, 0, NULL, NULL, '4', '36座以上');
INSERT INTO `b_car_type_relation` VALUES ('16', NULL, NULL, 0, NULL, NULL, '6', '特种车一');
INSERT INTO `b_car_type_relation` VALUES ('17', NULL, NULL, 0, NULL, NULL, '6', '特种车二');
INSERT INTO `b_car_type_relation` VALUES ('18', NULL, NULL, 0, NULL, NULL, '6', '特种车三');
INSERT INTO `b_car_type_relation` VALUES ('19', NULL, NULL, 0, NULL, NULL, '6', '特种车四');
INSERT INTO `b_car_type_relation` VALUES ('2', NULL, NULL, 0, NULL, NULL, '1', '2-5吨');
INSERT INTO `b_car_type_relation` VALUES ('20', NULL, NULL, 0, NULL, NULL, '7', '6座以下');
INSERT INTO `b_car_type_relation` VALUES ('21', NULL, NULL, 0, NULL, NULL, '7', '6-10座');
INSERT INTO `b_car_type_relation` VALUES ('22', NULL, NULL, 0, NULL, NULL, '7', '10座以上');
INSERT INTO `b_car_type_relation` VALUES ('23', NULL, NULL, 0, NULL, NULL, '8', '6座以下');
INSERT INTO `b_car_type_relation` VALUES ('24', NULL, NULL, 0, NULL, NULL, '8', '6-10座');
INSERT INTO `b_car_type_relation` VALUES ('25', NULL, NULL, 0, NULL, NULL, '8', '10-20座');
INSERT INTO `b_car_type_relation` VALUES ('26', NULL, NULL, 0, NULL, NULL, '8', '20座以上');
INSERT INTO `b_car_type_relation` VALUES ('27', NULL, NULL, 0, NULL, NULL, '9', '6座以下');
INSERT INTO `b_car_type_relation` VALUES ('28', NULL, NULL, 0, NULL, NULL, '9', '6-10座');
INSERT INTO `b_car_type_relation` VALUES ('29', NULL, NULL, 0, NULL, NULL, '9', '10-20座');
INSERT INTO `b_car_type_relation` VALUES ('3', NULL, NULL, 0, NULL, NULL, '1', '5-10吨');
INSERT INTO `b_car_type_relation` VALUES ('30', NULL, NULL, 0, NULL, NULL, '9', '20座以上');
INSERT INTO `b_car_type_relation` VALUES ('31', NULL, NULL, 0, NULL, NULL, '10', '6-10座');
INSERT INTO `b_car_type_relation` VALUES ('32', NULL, NULL, 0, NULL, NULL, '10', '10-20座');
INSERT INTO `b_car_type_relation` VALUES ('33', NULL, NULL, 0, NULL, NULL, '10', '20-36座');
INSERT INTO `b_car_type_relation` VALUES ('34', NULL, NULL, 0, NULL, NULL, '10', '36座以上');
INSERT INTO `b_car_type_relation` VALUES ('4', NULL, NULL, 0, NULL, NULL, '1', '10吨以上');
INSERT INTO `b_car_type_relation` VALUES ('5', NULL, NULL, 0, NULL, NULL, '1', '低速载货汽车');
INSERT INTO `b_car_type_relation` VALUES ('6', NULL, NULL, 0, NULL, NULL, '2', '挂车');
INSERT INTO `b_car_type_relation` VALUES ('7', NULL, NULL, 0, NULL, NULL, '3', '6-10座');
INSERT INTO `b_car_type_relation` VALUES ('8', NULL, NULL, 0, NULL, NULL, '3', '10-20座');
INSERT INTO `b_car_type_relation` VALUES ('9', NULL, NULL, 0, NULL, NULL, '3', '20-36座');

-- ----------------------------
-- Table structure for b_channel_discount
-- ----------------------------
DROP TABLE IF EXISTS `b_channel_discount`;
CREATE TABLE `b_channel_discount`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `discount_rate` decimal(19, 2) NULL DEFAULT NULL,
  `discount_region_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rate_region_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_channel_discount
-- ----------------------------
INSERT INTO `b_channel_discount` VALUES ('1444706487766224896', 'admin', '2021-10-03 11:50:54.463000', 0, 'admin', '2021-10-23 21:01:46.819000', '40322777781112832', 0.50, '鲁', '鲁');
INSERT INTO `b_channel_discount` VALUES ('1445963541273251840', 'admin', '2021-10-06 23:05:51.865000', 0, 'admin', '2021-10-06 23:05:51.865000', '40322777781112832', 0.50, NULL, NULL);
INSERT INTO `b_channel_discount` VALUES ('1452090630334451712', 'admin', '2021-10-23 20:52:39.534000', 0, 'admin', '2021-10-23 20:52:39.534000', '40322777781112832', 0.50, NULL, NULL);
INSERT INTO `b_channel_discount` VALUES ('1452090912783077376', 'admin', '2021-10-23 20:53:46.867000', 0, 'admin', '2021-10-23 20:53:46.867000', '40322777781112832', 0.50, NULL, NULL);
INSERT INTO `b_channel_discount` VALUES ('1452092276691046400', 'admin', '2021-10-23 20:59:12.013000', 0, 'admin', '2021-10-23 20:59:12.013000', '40322777781112832', 0.50, NULL, NULL);
INSERT INTO `b_channel_discount` VALUES ('1452092369024454656', 'admin', '2021-10-23 20:59:34.029000', 0, 'admin', '2021-10-23 20:59:34.029000', '40322777781112832', 0.50, NULL, NULL);
INSERT INTO `b_channel_discount` VALUES ('1452092673610616832', 'admin', '2021-10-23 21:00:46.640000', 0, 'admin', '2021-10-23 21:00:46.640000', '40322777781112832', 0.50, '津', '40322777781112832');
INSERT INTO `b_channel_discount` VALUES ('1452092704510054400', 'admin', '2021-10-23 21:00:54.012000', 0, 'admin', '2021-10-23 21:00:54.012000', '40322777781112832', 0.50, '津', '40322777781112832');
INSERT INTO `b_channel_discount` VALUES ('1452092851818205184', 'admin', '2021-10-23 21:01:29.126000', 0, 'admin', '2021-10-23 21:01:29.126000', '40322777781112832', 0.50, '津', '京');
INSERT INTO `b_channel_discount` VALUES ('1452092926028025856', 'admin', '2021-10-23 21:01:46.818000', 0, 'admin', '2021-10-23 21:01:46.818000', '40322777781112832', 0.50, '鲁', '鲁');

-- ----------------------------
-- Table structure for b_check_code
-- ----------------------------
DROP TABLE IF EXISTS `b_check_code`;
CREATE TABLE `b_check_code`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `generate_date` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_check_code
-- ----------------------------
INSERT INTO `b_check_code` VALUES ('1451161946425528320', 'admin', '2021-10-21 07:22:23.987000', 0, 'admin', '2021-10-21 07:22:23.987000', 'qObLqN', '2021-10-21 07:22:23.971000');
INSERT INTO `b_check_code` VALUES ('1451751421610430464', 'admin', '2021-10-22 22:24:45.842000', 0, 'admin', '2021-10-22 22:24:45.842000', 'qFvyHU', '2021-10-22 22:24:45.807000');
INSERT INTO `b_check_code` VALUES ('1451819178494398464', 'admin', '2021-10-23 02:54:00.400000', 0, 'admin', '2021-10-23 02:54:00.400000', 'hlqULo', '2021-10-23 02:54:00.326000');
INSERT INTO `b_check_code` VALUES ('1452184665183096832', 'admin', '2021-10-24 03:06:19.163000', 0, 'admin', '2021-10-24 03:06:19.163000', 'YZsAyr', '2021-10-24 03:06:19.126000');
INSERT INTO `b_check_code` VALUES ('1452434178024017920', 'admin', '2021-10-24 19:37:47.676000', 0, 'admin', '2021-10-24 19:37:47.676000', 'bMmSiO', '2021-10-24 19:37:47.621000');
INSERT INTO `b_check_code` VALUES ('1452470806041989120', 'admin', '2021-10-24 22:03:20.465000', 0, 'admin', '2021-10-24 22:03:20.465000', 'ULcUhR', '2021-10-24 22:03:20.420000');
INSERT INTO `b_check_code` VALUES ('1452625103136362496', 'admin', '2021-10-25 08:16:28.430000', 0, 'admin', '2021-10-25 08:16:28.430000', 'FZfjRT', '2021-10-25 08:16:27.715000');
INSERT INTO `b_check_code` VALUES ('1452805359726104576', 'admin', '2021-10-25 20:12:44.285000', 0, 'admin', '2021-10-25 20:12:44.285000', 'fOtNXq', '2021-10-25 20:12:44.235000');
INSERT INTO `b_check_code` VALUES ('1452805510662328320', 'admin', '2021-10-25 20:13:20.263000', 0, 'admin', '2021-10-25 20:13:20.263000', 'ghzyMM', '2021-10-25 20:13:20.221000');
INSERT INTO `b_check_code` VALUES ('1452871158394392576', 'admin', '2021-10-26 00:34:11.943000', 0, 'admin', '2021-10-26 00:34:11.943000', 'gaBzEG', '2021-10-26 00:34:11.860000');
INSERT INTO `b_check_code` VALUES ('1453161692589592576', 'admin', '2021-10-26 19:48:40.672000', 0, 'admin', '2021-10-26 19:48:40.672000', 'iCUawK', '2021-10-26 19:48:40.609000');
INSERT INTO `b_check_code` VALUES ('1453186502963105792', 'admin', '2021-10-26 21:27:15.910000', 0, 'admin', '2021-10-26 21:27:15.910000', 'tplJUL', '2021-10-26 21:27:15.863000');
INSERT INTO `b_check_code` VALUES ('1453234078512975872', 'admin', '2021-10-27 00:36:18.834000', 0, 'admin', '2021-10-27 00:36:18.834000', 'VhQhbj', '2021-10-27 00:36:18.760000');
INSERT INTO `b_check_code` VALUES ('1453528999199379456', 'admin', '2021-10-27 20:08:13.390000', 0, 'admin', '2021-10-27 20:08:13.390000', 'OZoTBp', '2021-10-27 20:08:13.334000');
INSERT INTO `b_check_code` VALUES ('1454346084938813440', 'admin', '2021-10-30 02:15:01.805000', 0, 'admin', '2021-10-30 02:15:01.805000', 'RkKhQL', '2021-10-30 02:15:01.750000');
INSERT INTO `b_check_code` VALUES ('1454636762059116544', 'admin', '2021-10-30 21:30:04.621000', 0, 'admin', '2021-10-30 21:30:04.621000', 'xQrcHE', '2021-10-30 21:30:04.576000');
INSERT INTO `b_check_code` VALUES ('1454644039042338816', 'test', '2021-10-30 21:58:59.586000', 0, 'test', '2021-10-30 21:58:59.586000', 'LfdCyF', '2021-10-30 21:58:59.544000');
INSERT INTO `b_check_code` VALUES ('1454644226838106112', 'test1', '2021-10-30 21:59:44.365000', 0, 'test1', '2021-10-30 21:59:44.365000', 'TBgqmI', '2021-10-30 21:59:44.319000');
INSERT INTO `b_check_code` VALUES ('1454651217354559488', 'test2', '2021-10-30 22:27:31.028000', 0, 'test2', '2021-10-30 22:27:31.028000', 'NtRfjz', '2021-10-30 22:27:30.987000');
INSERT INTO `b_check_code` VALUES ('1454652305977774080', 'test2', '2021-10-30 22:31:50.580000', 0, 'test2', '2021-10-30 22:31:50.580000', 'icJGJB', '2021-10-30 22:31:50.535000');
INSERT INTO `b_check_code` VALUES ('1454655571763531776', 'test2', '2021-10-30 22:44:49.214000', 0, 'test2', '2021-10-30 22:44:49.214000', 'xptMZc', '2021-10-30 22:44:49.160000');
INSERT INTO `b_check_code` VALUES ('1454656595685412864', 'test3', '2021-10-30 22:48:53.325000', 0, 'test3', '2021-10-30 22:48:53.325000', 'mbhBoK', '2021-10-30 22:48:53.281000');
INSERT INTO `b_check_code` VALUES ('1454656858882183168', 'test4', '2021-10-30 22:49:56.074000', 0, 'test4', '2021-10-30 22:49:56.074000', 'MwrIdG', '2021-10-30 22:49:56.032000');
INSERT INTO `b_check_code` VALUES ('1454657033038073856', 'test5', '2021-10-30 22:50:37.601000', 0, 'test5', '2021-10-30 22:50:37.601000', 'OZkBLV', '2021-10-30 22:50:37.554000');
INSERT INTO `b_check_code` VALUES ('1454676423569707008', 'test4', '2021-10-31 00:07:40.661000', 0, 'test4', '2021-10-31 00:07:40.661000', 'HysUAe', '2021-10-31 00:07:40.617000');
INSERT INTO `b_check_code` VALUES ('1454987011650883584', 'admin', '2021-10-31 20:41:50.870000', 0, 'admin', '2021-10-31 20:41:50.870000', 'RgXoSd', '2021-10-31 20:41:50.594000');
INSERT INTO `b_check_code` VALUES ('1455006816365842432', 'admin', '2021-10-31 22:00:32.444000', 0, 'admin', '2021-10-31 22:00:32.444000', 'DluqXO', '2021-10-31 22:00:32.398000');
INSERT INTO `b_check_code` VALUES ('1455134628993175552', 'admin', '2021-11-01 06:28:25.317000', 0, 'admin', '2021-11-01 06:28:25.317000', 'nzwcsu', '2021-11-01 06:28:25.304000');
INSERT INTO `b_check_code` VALUES ('1455382842082201600', 'admin', '2021-11-01 22:54:43.984000', 0, 'admin', '2021-11-01 22:54:43.984000', 'lbpajz', '2021-11-01 22:54:43.917000');
INSERT INTO `b_check_code` VALUES ('1455425555481628672', 'admin', '2021-11-02 01:44:27.715000', 0, 'admin', '2021-11-02 01:44:27.715000', 'gofxss', '2021-11-02 01:44:27.584000');
INSERT INTO `b_check_code` VALUES ('1455712615027314688', 'admin', '2021-11-02 20:45:08.018000', 0, 'admin', '2021-11-02 20:45:08.018000', 'YBBBUJ', '2021-11-02 20:45:07.913000');
INSERT INTO `b_check_code` VALUES ('1455730829459329024', 'admin', '2021-11-02 21:57:30.630000', 0, 'admin', '2021-11-02 21:57:30.630000', 'OIPDBQ', '2021-11-02 21:57:30.573000');
INSERT INTO `b_check_code` VALUES ('1455766801207136256', 'admin', '2021-11-03 00:20:27.022000', 0, 'admin', '2021-11-03 00:20:27.022000', 'MEOTCA', '2021-11-03 00:20:26.905000');
INSERT INTO `b_check_code` VALUES ('1456099457576865792', 'admin', '2021-11-03 22:22:18.419000', 0, 'admin', '2021-11-03 22:22:18.419000', 'XAUSSN', '2021-11-03 22:22:18.366000');
INSERT INTO `b_check_code` VALUES ('1456448657237872640', 'admin', '2021-11-04 21:29:54.154000', 0, 'admin', '2021-11-04 21:29:54.154000', 'ZPYMTF', '2021-11-04 21:29:54.051000');
INSERT INTO `b_check_code` VALUES ('1456546284918935552', 'admin', '2021-11-05 03:57:50.353000', 0, 'admin', '2021-11-05 03:57:50.353000', 'MLEZQK', '2021-11-05 03:57:50.304000');
INSERT INTO `b_check_code` VALUES ('1456906516983582720', 'admin', '2021-11-06 03:49:16.334000', 0, 'admin', '2021-11-06 03:49:16.334000', 'OEEZXX', '2021-11-06 03:49:16.321000');
INSERT INTO `b_check_code` VALUES ('1457185166693896192', 'admin', '2021-11-06 22:16:31.688000', 0, 'admin', '2021-11-06 22:16:31.688000', 'KRKHHA', '2021-11-06 22:16:31.589000');
INSERT INTO `b_check_code` VALUES ('1457516135611240448', 'admin', '2021-11-07 19:11:40.779000', 0, 'admin', '2021-11-07 19:11:40.779000', 'UFRMIG', '2021-11-07 19:11:40.727000');
INSERT INTO `b_check_code` VALUES ('1457524228864741376', 'admin', '2021-11-07 19:43:50.351000', 0, 'admin', '2021-11-07 19:43:50.351000', 'WZOVDV', '2021-11-07 19:43:50.309000');
INSERT INTO `b_check_code` VALUES ('1457549613107122176', 'admin', '2021-11-07 21:24:42.481000', 0, 'admin', '2021-11-07 21:24:42.481000', 'OGXBOG', '2021-11-07 21:24:42.385000');
INSERT INTO `b_check_code` VALUES ('1457549761207996418', 'admin', '2021-11-07 21:25:17.735000', 0, 'admin', '2021-11-07 21:25:17.735000', 'DHXLEF', '2021-11-07 21:25:17.694000');
INSERT INTO `b_check_code` VALUES ('1457589801577484288', 'admin', '2021-11-08 00:04:24.149000', 0, 'admin', '2021-11-08 00:04:24.149000', 'LNXDBH', '2021-11-08 00:04:24.063000');
INSERT INTO `b_check_code` VALUES ('1458760811152543744', 'admin', '2021-11-11 05:37:36.588000', 0, 'admin', '2021-11-11 05:37:36.588000', 'WCXABL', '2021-11-11 05:37:34.604000');
INSERT INTO `b_check_code` VALUES ('1459029301860110336', 'admin', '2021-11-11 23:24:27.724000', 0, 'admin', '2021-11-11 23:24:27.724000', 'UFUPIF', '2021-11-11 23:24:27.675000');
INSERT INTO `b_check_code` VALUES ('1459029367983312896', 'admin', '2021-11-11 23:24:43.481000', 0, 'admin', '2021-11-11 23:24:43.481000', 'ZSAUHR', '2021-11-11 23:24:43.439000');
INSERT INTO `b_check_code` VALUES ('1459029995677683712', 'admin', '2021-11-11 23:27:13.136000', 0, 'admin', '2021-11-11 23:27:13.136000', 'GYOEJJ', '2021-11-11 23:27:13.093000');
INSERT INTO `b_check_code` VALUES ('1459034340628500480', 'admin', '2021-11-11 23:44:29.046000', 0, 'admin', '2021-11-11 23:44:29.046000', 'ZOSEJA', '2021-11-11 23:44:29.013000');
INSERT INTO `b_check_code` VALUES ('1459035933528690688', 'admin', '2021-11-11 23:50:48.800000', 0, 'admin', '2021-11-11 23:50:48.800000', 'JAKSZH', '2021-11-11 23:50:48.787000');
INSERT INTO `b_check_code` VALUES ('1459037223038095360', 'admin', '2021-11-11 23:55:56.234000', 0, 'admin', '2021-11-11 23:55:56.234000', 'HATNSM', '2021-11-11 23:55:56.230000');
INSERT INTO `b_check_code` VALUES ('1459454536363151360', 'admin', '2021-11-13 03:34:12.346000', 0, 'admin', '2021-11-13 03:34:12.346000', 'XPFIBP', '2021-11-13 03:34:11.504000');

-- ----------------------------
-- Table structure for b_client_info
-- ----------------------------
DROP TABLE IF EXISTS `b_client_info`;
CREATE TABLE `b_client_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `client_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_client_info
-- ----------------------------

-- ----------------------------
-- Table structure for b_helper_damage
-- ----------------------------
DROP TABLE IF EXISTS `b_helper_damage`;
CREATE TABLE `b_helper_damage`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `helper_damage_classify_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `helper_order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_helper_damage
-- ----------------------------

-- ----------------------------
-- Table structure for b_helper_damage_classify
-- ----------------------------
DROP TABLE IF EXISTS `b_helper_damage_classify`;
CREATE TABLE `b_helper_damage_classify`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `helper_order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `damange_total_money` decimal(19, 2) NULL DEFAULT NULL,
  `deductible_fee` decimal(19, 2) NULL DEFAULT NULL,
  `fee_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `insurance_money` int(11) NULL DEFAULT NULL,
  `is_decide` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_helper_damage_classify
-- ----------------------------

-- ----------------------------
-- Table structure for b_helper_damage_detail
-- ----------------------------
DROP TABLE IF EXISTS `b_helper_damage_detail`;
CREATE TABLE `b_helper_damage_detail`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `helper_damage_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `check_count` int(11) NULL DEFAULT NULL,
  `check_price` decimal(19, 2) NULL DEFAULT NULL,
  `check_total_price` decimal(19, 2) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `damage_project_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `damage_project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(19, 2) NULL DEFAULT NULL,
  `supplier_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `total_price` decimal(19, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_helper_damage_detail
-- ----------------------------

-- ----------------------------
-- Table structure for b_helper_order
-- ----------------------------
DROP TABLE IF EXISTS `b_helper_order`;
CREATE TABLE `b_helper_order`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `accident_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `accident_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `accident_time` datetime(6) NULL DEFAULT NULL,
  `accident_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_no_head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `carframe_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end_date` datetime(6) NULL DEFAULT NULL,
  `motor_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `report_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `report_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_date` datetime(6) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `third_car_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `third_car_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `third_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `diaocha_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_helper_order
-- ----------------------------

-- ----------------------------
-- Table structure for b_helper_order_compensate
-- ----------------------------
DROP TABLE IF EXISTS `b_helper_order_compensate`;
CREATE TABLE `b_helper_order_compensate`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `appraisal_money` decimal(19, 2) NULL DEFAULT NULL,
  `arbitrate_money` decimal(19, 2) NULL DEFAULT NULL,
  `bank_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `compensate_money` decimal(19, 2) NULL DEFAULT NULL,
  `compensate_target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `compulsory_compensate_money` decimal(19, 2) NULL DEFAULT NULL,
  `dianfu_money` decimal(19, 2) NULL DEFAULT NULL,
  `duty_percent` decimal(19, 2) NULL DEFAULT NULL,
  `investigate_money` decimal(19, 2) NULL DEFAULT NULL,
  `lawsuit_money` decimal(19, 2) NULL DEFAULT NULL,
  `open_bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `oteher_money` decimal(19, 2) NULL DEFAULT NULL,
  `public_money` decimal(19, 2) NULL DEFAULT NULL,
  `total_loss` decimal(19, 2) NULL DEFAULT NULL,
  `wait_for_compensate_money` decimal(19, 2) NULL DEFAULT NULL,
  `helper_order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_helper_order_compensate
-- ----------------------------

-- ----------------------------
-- Table structure for b_helper_order_pic
-- ----------------------------
DROP TABLE IF EXISTS `b_helper_order_pic`;
CREATE TABLE `b_helper_order_pic`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `classify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `helper_order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_helper_order_pic
-- ----------------------------

-- ----------------------------
-- Table structure for b_order
-- ----------------------------
DROP TABLE IF EXISTS `b_order`;
CREATE TABLE `b_order`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `after_discount_money` decimal(19, 2) NULL DEFAULT NULL,
  `before_month_old_rate_price` decimal(19, 2) NULL DEFAULT NULL,
  `belong_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_new_price` decimal(19, 2) NULL DEFAULT NULL,
  `car_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_no_head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_sieral_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `carframe_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_card_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `discount_rate` decimal(19, 2) NULL DEFAULT NULL,
  `end_date` datetime(6) NULL DEFAULT NULL,
  `fee_float` decimal(19, 2) NULL DEFAULT NULL,
  `fee_float_rate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fee_float_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `first_log_date` datetime(6) NULL DEFAULT NULL,
  `handle_card_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `handle_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `handle_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_check_car` bit(1) NULL DEFAULT NULL,
  `month` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `month_old_rate` decimal(19, 2) NULL DEFAULT NULL,
  `month_old_rate_price` decimal(19, 2) NULL DEFAULT NULL,
  `motor_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `negotiate_price` decimal(19, 2) NULL DEFAULT NULL,
  `order_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_agree` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `origin_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `run_card_car_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `run_card_card_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `run_card_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `run_card_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `short_discount` decimal(19, 2) NULL DEFAULT NULL,
  `site_count` int(11) NULL DEFAULT NULL,
  `start_date` datetime(6) NULL DEFAULT NULL,
  `time_limit_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `total_fee` decimal(19, 2) NULL DEFAULT NULL,
  `use_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `buy_price` decimal(19, 2) NULL DEFAULT NULL,
  `car_typea` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typeb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `displacement` decimal(19, 2) NULL DEFAULT NULL,
  `kws` decimal(19, 2) NULL DEFAULT NULL,
  `load_capacity` decimal(19, 2) NULL DEFAULT NULL,
  `weight` decimal(19, 2) NULL DEFAULT NULL,
  `car_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_order
-- ----------------------------

-- ----------------------------
-- Table structure for b_order_agree
-- ----------------------------
DROP TABLE IF EXISTS `b_order_agree`;
CREATE TABLE `b_order_agree`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_order_agree
-- ----------------------------

-- ----------------------------
-- Table structure for b_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `b_order_detail`;
CREATE TABLE `b_order_detail`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `actual_fee` decimal(19, 2) NULL DEFAULT NULL,
  `base_fee` decimal(19, 2) NULL DEFAULT NULL,
  `deductible_fee` decimal(19, 2) NULL DEFAULT NULL,
  `every_fee` decimal(19, 2) NULL DEFAULT NULL,
  `fee_rate` decimal(19, 2) NULL DEFAULT NULL,
  `fee_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `insurance_money` int(11) NULL DEFAULT NULL,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `site_count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for b_order_file
-- ----------------------------
DROP TABLE IF EXISTS `b_order_file`;
CREATE TABLE `b_order_file`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_order_file
-- ----------------------------

-- ----------------------------
-- Table structure for b_order_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `b_order_pay_info`;
CREATE TABLE `b_order_pay_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `bussiness_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bussiness_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_recieve` decimal(19, 2) NULL DEFAULT NULL,
  `recieve_evidence` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `recieve_man` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `recieve_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `send_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `should_recieve` decimal(19, 2) NULL DEFAULT NULL,
  `should_clean_recieve` decimal(19, 2) NULL DEFAULT NULL,
  `recieve_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `recieve_time` datetime(6) NULL DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_order_pay_info
-- ----------------------------

-- ----------------------------
-- Table structure for b_rate_config
-- ----------------------------
DROP TABLE IF EXISTS `b_rate_config`;
CREATE TABLE `b_rate_config`  (
  `id` bigint(20) NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `base_fee` decimal(19, 4) NULL DEFAULT NULL,
  `belong_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fee_rate` decimal(19, 4) NULL DEFAULT NULL,
  `fee_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `insurance_money` int(11) NULL DEFAULT NULL,
  `region_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `use_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `year_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_sieral_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typea` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `car_typeb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_rate_config
-- ----------------------------

-- ----------------------------
-- Table structure for b_youhui
-- ----------------------------
DROP TABLE IF EXISTS `b_youhui`;
CREATE TABLE `b_youhui`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `car_no_head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fee_float_rate` decimal(19, 2) NULL DEFAULT NULL,
  `fee_float_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `region_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_youhui
-- ----------------------------

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `home_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_client
-- ----------------------------

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `parent_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `sort_order` decimal(10, 2) NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_parent` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (40322777781112832, '', '2018-08-10 20:40:40', 0, '', '2018-08-11 00:03:06', 0, 1.00, 0, '总部', b'1');
INSERT INTO `t_department` VALUES (40322811096469504, '', '2018-08-10 20:40:48', 0, '', '2018-08-11 00:27:05', 40322777781112832, 1.00, 0, '技术部', b'1');
INSERT INTO `t_department` VALUES (40322852833988608, '', '2018-08-10 20:40:58', 0, '', '2018-08-11 01:29:42', 40322811096469504, 1.00, 0, '研发中心', NULL);

-- ----------------------------
-- Table structure for t_department_header
-- ----------------------------
DROP TABLE IF EXISTS `t_department_header`;
CREATE TABLE `t_department_header`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `department_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `type` tinyint(1) NULL DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department_header
-- ----------------------------
INSERT INTO `t_department_header` VALUES (1254427833757995008, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 0, 682265633886208);
INSERT INTO `t_department_header` VALUES (1254427833757995009, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 0, 16739222421508096);
INSERT INTO `t_department_header` VALUES (1254427833757995010, 'admin', '2020-04-26 23:11:16', 0, 'admin', '2020-04-26 23:11:16', 40322777781112832, 1, 16739222421508096);

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort_order` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES (75135930788220928, 'admin', '2018-11-14 22:15:43', 0, 'admin', '2018-11-27 01:39:06', '', '性别', 'sex', 0.00);
INSERT INTO `t_dict` VALUES (75388696739713024, 'admin', '2018-11-15 15:00:07', 0, 'admin', '2018-11-27 01:39:22', '', '按钮权限类型', 'permission_type', 2.00);
INSERT INTO `t_dict` VALUES (1443588533548879872, 'admin', '2021-09-30 09:48:22', 0, 'admin', '2021-09-30 09:48:22', '', '车型信息-所属性质', 'belongType', 0.00);
INSERT INTO `t_dict` VALUES (1443589233368502272, 'admin', '2021-09-30 09:51:09', 0, 'admin', '2021-09-30 09:51:09', '', '车型信息-使用性质', 'useType', 0.00);
INSERT INTO `t_dict` VALUES (1443589775079641088, 'admin', '2021-09-30 09:53:18', 0, 'admin', '2021-10-21 21:12:57', '', '车型信息-车辆种类A', 'carTypeA', 0.00);
INSERT INTO `t_dict` VALUES (1443590448777138176, 'admin', '2021-09-30 09:55:58', 0, 'admin', '2021-09-30 09:55:58', '', '车型信息-年限', 'yearType', 0.00);
INSERT INTO `t_dict` VALUES (1443594398704209920, 'admin', '2021-09-30 10:11:40', 0, 'admin', '2021-09-30 23:05:11', '', '省名简写', 'regionGroups', 0.00);
INSERT INTO `t_dict` VALUES (1443594794969468928, 'admin', '2021-09-30 10:13:14', 0, 'admin', '2021-09-30 10:13:14', '', '车型信息-车系分类', 'carSieralType', 0.00);
INSERT INTO `t_dict` VALUES (1443595072150048768, 'admin', '2021-09-30 10:14:21', 0, 'admin', '2021-09-30 10:14:21', '', '保额列表', 'insuranceMoney', 0.00);
INSERT INTO `t_dict` VALUES (1443596083673567232, 'admin', '2021-09-30 10:18:22', 0, 'admin', '2021-09-30 10:18:22', '', '统筹单-期限类型', 'timeLimitType', 0.00);
INSERT INTO `t_dict` VALUES (1443596355175059456, 'admin', '2021-09-30 10:19:26', 0, 'admin', '2021-09-30 10:19:26', '', '统筹单-自然月数', 'month', 0.00);
INSERT INTO `t_dict` VALUES (1443599654146150400, 'admin', '2021-09-30 10:32:33', 0, 'admin', '2021-09-30 10:32:33', '', '统筹单-行驶证车辆类型', 'runCardCarType', 0.00);
INSERT INTO `t_dict` VALUES (1443600051065720832, 'admin', '2021-09-30 10:34:08', 0, 'admin', '2021-09-30 10:41:49', '', '统筹单-特别约定', 'orderAgree', 0.00);
INSERT INTO `t_dict` VALUES (1443609680856354816, 'admin', '2021-09-30 11:12:24', 0, 'admin', '2021-09-30 11:12:24', '', '统筹单-客户性质', 'clientType', 0.00);
INSERT INTO `t_dict` VALUES (1443772132722085888, 'admin', '2021-09-30 21:57:55', 0, 'admin', '2021-09-30 21:57:55', '', '费率配置-分类', 'feeType', 0.00);
INSERT INTO `t_dict` VALUES (1444958458309906432, 'admin', '2021-10-04 04:31:57', 0, 'admin', '2021-10-04 04:31:57', '', '统筹单-每人统筹', 'everyFee', 0.00);
INSERT INTO `t_dict` VALUES (1444965620495028224, 'admin', '2021-10-04 05:00:25', 0, 'admin', '2021-10-04 05:00:25', '', '统筹单-座位数', 'siteCount', 0.00);
INSERT INTO `t_dict` VALUES (1445257863781027840, 'admin', '2021-10-05 00:21:41', 0, 'admin', '2021-10-05 00:21:41', '', '统筹单-短期折扣', 'shortDiscount', 0.00);
INSERT INTO `t_dict` VALUES (1446472824091971584, 'admin', '2021-10-08 08:49:30', 0, 'admin', '2021-10-08 08:49:30', '', '收款方式', 'recieveTypes', 0.00);
INSERT INTO `t_dict` VALUES (1449358222141034496, 'admin', '2021-10-16 07:55:03', 0, 'admin', '2021-10-16 07:55:03', '非开发人员不要更改此项配置', '系统配置', 'systemConfig', 0.00);
INSERT INTO `t_dict` VALUES (1450341902045024256, 'admin', '2021-10-19 01:03:50', 0, 'admin', '2021-10-19 01:03:50', '', '供应商级别', 'supplierLevel', 0.00);
INSERT INTO `t_dict` VALUES (1450709963231072256, 'admin', '2021-10-20 01:26:23', 0, 'admin', '2021-10-20 01:26:23', '', '事故调查照片-分类', 'helperOrderPicSort', 0.00);
INSERT INTO `t_dict` VALUES (1451371092366200832, 'admin', '2021-10-21 21:13:28', 0, 'admin', '2021-10-21 21:13:28', '', '车型信息-车辆种类B', 'carTypeB', 0.00);

-- ----------------------------
-- Table structure for t_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_data`;
CREATE TABLE `t_dict_data`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `sort_order` decimal(10, 2) NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sort_order`(`sort_order`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict_data
-- ----------------------------
INSERT INTO `t_dict_data` VALUES (1, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '10-20座', '10-20座');
INSERT INTO `t_dict_data` VALUES (2, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '10吨以上', '10吨以上');
INSERT INTO `t_dict_data` VALUES (3, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '10座以上', '10座以上');
INSERT INTO `t_dict_data` VALUES (4, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '20-36座', '20-36座');
INSERT INTO `t_dict_data` VALUES (5, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '20座以上', '20座以上');
INSERT INTO `t_dict_data` VALUES (6, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '2-5吨', '2-5吨');
INSERT INTO `t_dict_data` VALUES (7, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '2吨以下', '2吨以下');
INSERT INTO `t_dict_data` VALUES (8, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '36座以上', '36座以上');
INSERT INTO `t_dict_data` VALUES (9, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '5-10吨', '5-10吨');
INSERT INTO `t_dict_data` VALUES (10, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '6-10座', '6-10座');
INSERT INTO `t_dict_data` VALUES (11, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '6座以下', '6座以下');
INSERT INTO `t_dict_data` VALUES (12, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '低速载货汽车', '低速载货汽车');
INSERT INTO `t_dict_data` VALUES (13, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '挂车', '挂车');
INSERT INTO `t_dict_data` VALUES (14, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '特种车二', '特种车二');
INSERT INTO `t_dict_data` VALUES (15, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '特种车三', '特种车三');
INSERT INTO `t_dict_data` VALUES (16, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '特种车四', '特种车四');
INSERT INTO `t_dict_data` VALUES (17, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1451371092366200832, 0.00, 0, '特种车一', '特种车一');
INSERT INTO `t_dict_data` VALUES (19, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(非营业)1年以下', '1');
INSERT INTO `t_dict_data` VALUES (20, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(非营业)1-2年', '1.9');
INSERT INTO `t_dict_data` VALUES (21, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(营业)2年以下', '2');
INSERT INTO `t_dict_data` VALUES (22, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(营业)2-3年', '3');
INSERT INTO `t_dict_data` VALUES (23, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(营业)3-4年', '4');
INSERT INTO `t_dict_data` VALUES (24, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(非营业)2-6年', '6');
INSERT INTO `t_dict_data` VALUES (25, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(营业)4年以上', '50');
INSERT INTO `t_dict_data` VALUES (26, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443590448777138176, 0.00, 0, '(非营业)6年以上', '61');
INSERT INTO `t_dict_data` VALUES (27, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '北京', '京');
INSERT INTO `t_dict_data` VALUES (28, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '天津', '津');
INSERT INTO `t_dict_data` VALUES (29, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '河北', '冀');
INSERT INTO `t_dict_data` VALUES (30, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '山西', '晋');
INSERT INTO `t_dict_data` VALUES (31, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '内蒙古', '蒙');
INSERT INTO `t_dict_data` VALUES (32, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-22 01:03:33', '', 1443594398704209920, 0.00, 0, '大连', '大');
INSERT INTO `t_dict_data` VALUES (33, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '辽宁', '辽');
INSERT INTO `t_dict_data` VALUES (34, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '吉林', '吉');
INSERT INTO `t_dict_data` VALUES (35, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '黑龙江', '黑');
INSERT INTO `t_dict_data` VALUES (36, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '上海', '沪');
INSERT INTO `t_dict_data` VALUES (37, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '江苏', '苏');
INSERT INTO `t_dict_data` VALUES (38, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-22 01:04:03', '', 1443594398704209920, 0.00, 0, '宁波', '宁');
INSERT INTO `t_dict_data` VALUES (39, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '浙江', '浙');
INSERT INTO `t_dict_data` VALUES (40, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '安徽', '皖');
INSERT INTO `t_dict_data` VALUES (41, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '福建', '闽');
INSERT INTO `t_dict_data` VALUES (42, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-22 01:03:54', '', 1443594398704209920, 0.00, 0, '厦门', '厦');
INSERT INTO `t_dict_data` VALUES (43, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '江西', '赣');
INSERT INTO `t_dict_data` VALUES (44, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-22 01:03:44', '', 1443594398704209920, 0.00, 0, '青岛', '靑');
INSERT INTO `t_dict_data` VALUES (45, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '山东', '鲁');
INSERT INTO `t_dict_data` VALUES (46, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '河南', '豫');
INSERT INTO `t_dict_data` VALUES (47, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '湖北', '鄂');
INSERT INTO `t_dict_data` VALUES (48, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '湖南', '湘');
INSERT INTO `t_dict_data` VALUES (49, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '广东', '粤');
INSERT INTO `t_dict_data` VALUES (50, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-22 01:04:14', '', 1443594398704209920, 0.00, 0, '深圳', '深');
INSERT INTO `t_dict_data` VALUES (51, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '广西', '桂');
INSERT INTO `t_dict_data` VALUES (52, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '海南', '琼');
INSERT INTO `t_dict_data` VALUES (53, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '重庆', '渝');
INSERT INTO `t_dict_data` VALUES (54, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '四川', '川');
INSERT INTO `t_dict_data` VALUES (55, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '贵州', '黔');
INSERT INTO `t_dict_data` VALUES (56, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '云南', '滇');
INSERT INTO `t_dict_data` VALUES (57, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '西藏', '藏');
INSERT INTO `t_dict_data` VALUES (58, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '陕西', '陕');
INSERT INTO `t_dict_data` VALUES (59, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '甘肃', '甘');
INSERT INTO `t_dict_data` VALUES (60, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '青海', '青');
INSERT INTO `t_dict_data` VALUES (61, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '宁夏', '宁');
INSERT INTO `t_dict_data` VALUES (62, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443594398704209920, 0.00, 0, '新疆', '新');
INSERT INTO `t_dict_data` VALUES (64, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-24 07:34:47', '', 1443599654146150400, 0.00, 0, '大型普通客车', '1');
INSERT INTO `t_dict_data` VALUES (65, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '小型面包车', '2');
INSERT INTO `t_dict_data` VALUES (66, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型普通客车', '3');
INSERT INTO `t_dict_data` VALUES (67, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '小型普通客车', '4');
INSERT INTO `t_dict_data` VALUES (68, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '小型越野客车', '5');
INSERT INTO `t_dict_data` VALUES (69, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轿车', '6');
INSERT INTO `t_dict_data` VALUES (70, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '微型普通客车', '7');
INSERT INTO `t_dict_data` VALUES (71, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '微型轿车', '8');
INSERT INTO `t_dict_data` VALUES (72, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型普通货车', '9');
INSERT INTO `t_dict_data` VALUES (73, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型厢式货车', '10');
INSERT INTO `t_dict_data` VALUES (74, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型自卸货车', '11');
INSERT INTO `t_dict_data` VALUES (75, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型普通货车', '12');
INSERT INTO `t_dict_data` VALUES (76, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型厢式货车', '13');
INSERT INTO `t_dict_data` VALUES (77, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型普通货车', '14');
INSERT INTO `t_dict_data` VALUES (78, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型厢式货车', '15');
INSERT INTO `t_dict_data` VALUES (79, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型封闭货车', '16');
INSERT INTO `t_dict_data` VALUES (80, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型自卸货车', '17');
INSERT INTO `t_dict_data` VALUES (81, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '微型普通货车', '18');
INSERT INTO `t_dict_data` VALUES (82, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '低速普通货车', '19');
INSERT INTO `t_dict_data` VALUES (83, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型半挂牵引车', '20');
INSERT INTO `t_dict_data` VALUES (84, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型专项作业车', '21');
INSERT INTO `t_dict_data` VALUES (85, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '小型专项作业车', '22');
INSERT INTO `t_dict_data` VALUES (86, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '普通二轮摩托车', '23');
INSERT INTO `t_dict_data` VALUES (87, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '其他', '24');
INSERT INTO `t_dict_data` VALUES (88, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型自卸半挂车', '25');
INSERT INTO `t_dict_data` VALUES (89, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '低速自卸货车', '26');
INSERT INTO `t_dict_data` VALUES (90, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型专项作业车', '27');
INSERT INTO `t_dict_data` VALUES (91, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型自卸货车', '28');
INSERT INTO `t_dict_data` VALUES (92, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '仓栅式运输半挂车', '29');
INSERT INTO `t_dict_data` VALUES (93, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '大型专项作业车', '30');
INSERT INTO `t_dict_data` VALUES (94, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '下灰半挂车', '31');
INSERT INTO `t_dict_data` VALUES (95, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型罐式半挂车', '32');
INSERT INTO `t_dict_data` VALUES (96, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型低平板半挂车', '33');
INSERT INTO `t_dict_data` VALUES (97, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型普通半挂车', '34');
INSERT INTO `t_dict_data` VALUES (98, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型专项作业半挂车', '35');
INSERT INTO `t_dict_data` VALUES (99, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型半挂牵引车', '36');
INSERT INTO `t_dict_data` VALUES (100, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '低速电动四轮车', '37');
INSERT INTO `t_dict_data` VALUES (101, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型集装箱半挂车', '38');
INSERT INTO `t_dict_data` VALUES (102, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型特殊结构货车', '39');
INSERT INTO `t_dict_data` VALUES (103, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型罐式货车', '40');
INSERT INTO `t_dict_data` VALUES (104, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型平板自卸半挂车', '41');
INSERT INTO `t_dict_data` VALUES (105, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型非载货专项作业车', '42');
INSERT INTO `t_dict_data` VALUES (106, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型仓栅式半挂车', '43');
INSERT INTO `t_dict_data` VALUES (107, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型仓栅式货车', '44');
INSERT INTO `t_dict_data` VALUES (108, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型载货专项作业车', '45');
INSERT INTO `t_dict_data` VALUES (109, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型厢式半挂车', '46');
INSERT INTO `t_dict_data` VALUES (110, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型非载货专项作业车', '47');
INSERT INTO `t_dict_data` VALUES (111, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型平板货车', '48');
INSERT INTO `t_dict_data` VALUES (112, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型平板货车', '49');
INSERT INTO `t_dict_data` VALUES (113, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型栏板半挂车', '50');
INSERT INTO `t_dict_data` VALUES (114, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型自卸式垃圾车', '51');
INSERT INTO `t_dict_data` VALUES (115, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型自卸车式垃圾车', '52');
INSERT INTO `t_dict_data` VALUES (116, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型仓栅式货车', '53');
INSERT INTO `t_dict_data` VALUES (117, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型平板半挂车', '54');
INSERT INTO `t_dict_data` VALUES (118, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型栏板货车', '55');
INSERT INTO `t_dict_data` VALUES (119, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型仓栅式货车', '56');
INSERT INTO `t_dict_data` VALUES (120, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型特殊结构货车', '57');
INSERT INTO `t_dict_data` VALUES (121, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '中型罐式货车', '58');
INSERT INTO `t_dict_data` VALUES (122, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '低平板半挂车', '59');
INSERT INTO `t_dict_data` VALUES (123, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型罐式自卸半挂车', '60');
INSERT INTO `t_dict_data` VALUES (124, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '重型平板自卸货车', '61');
INSERT INTO `t_dict_data` VALUES (125, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '自卸低速货车', '62');
INSERT INTO `t_dict_data` VALUES (126, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '售货车', '63');
INSERT INTO `t_dict_data` VALUES (127, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型特殊结构货车', '64');
INSERT INTO `t_dict_data` VALUES (128, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型非载货专项作业车', '65');
INSERT INTO `t_dict_data` VALUES (129, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '轻型栏板货车', '66');
INSERT INTO `t_dict_data` VALUES (130, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '小型非载货专项作业车', '67');
INSERT INTO `t_dict_data` VALUES (131, 'admin', '2021-10-23 03:11:51', 0, 'admin', '2021-10-23 03:11:51', '', 1443599654146150400, 0.00, 0, '普通低速货车', '68');
INSERT INTO `t_dict_data` VALUES (75158227712479232, 'admin', '2018-11-14 23:44:19', 0, 'admin', '2019-04-01 23:53:13', '', 75135930788220928, 0.00, 0, '男', '男');
INSERT INTO `t_dict_data` VALUES (75159254272577536, 'admin', '2018-11-14 23:48:24', 0, 'admin', '2019-04-01 23:53:17', '', 75135930788220928, 1.00, 0, '女', '女');
INSERT INTO `t_dict_data` VALUES (75159898425397248, 'admin', '2018-11-14 23:50:57', 0, 'admin', '2019-04-01 23:53:22', '', 75135930788220928, 2.00, -1, '保密', '保密');
INSERT INTO `t_dict_data` VALUES (75390787835138048, 'admin', '2018-11-15 15:08:26', 0, 'admin', '2021-10-10 00:00:32', '', 75388696739713024, 0.00, 0, '查看操作(view)', 'view');
INSERT INTO `t_dict_data` VALUES (75390886501945344, 'admin', '2018-11-15 15:08:49', 0, 'admin', '2021-10-10 00:00:28', '', 75388696739713024, 1.00, 0, '添加操作(add)', 'add');
INSERT INTO `t_dict_data` VALUES (75390993939042304, 'admin', '2018-11-15 15:09:15', 0, 'admin', '2021-10-10 00:00:24', '', 75388696739713024, 2.00, 0, '编辑操作(edit)', 'edit');
INSERT INTO `t_dict_data` VALUES (75391067532300288, 'admin', '2018-11-15 15:09:32', 0, 'admin', '2018-11-15 15:09:32', '', 75388696739713024, 3.00, 0, '删除操作(delete)', 'delete');
INSERT INTO `t_dict_data` VALUES (75391126902673408, 'admin', '2018-11-15 15:09:46', 0, 'admin', '2018-11-15 15:09:46', '', 75388696739713024, 4.00, 0, '清空操作(clear)', 'clear');
INSERT INTO `t_dict_data` VALUES (75391192883269632, 'admin', '2018-11-15 15:10:02', 0, 'admin', '2018-11-15 15:10:02', '', 75388696739713024, 5.00, 0, '启用操作(enable)', 'enable');
INSERT INTO `t_dict_data` VALUES (75391251024711680, 'admin', '2018-11-15 15:10:16', 0, 'admin', '2018-11-15 15:10:16', '', 75388696739713024, 6.00, 0, '禁用操作(disable)', 'disable');
INSERT INTO `t_dict_data` VALUES (75391297124306944, 'admin', '2018-11-15 15:10:27', 0, 'admin', '2018-11-15 15:10:27', '', 75388696739713024, 7.00, 0, '搜索操作(search)', 'search');
INSERT INTO `t_dict_data` VALUES (75391343379091456, 'admin', '2018-11-15 15:10:38', 0, 'admin', '2018-11-15 15:10:38', '', 75388696739713024, 8.00, 0, '上传文件(upload)', 'upload');
INSERT INTO `t_dict_data` VALUES (75391407526776832, 'admin', '2018-11-15 15:10:53', 0, 'admin', '2018-11-15 15:10:53', '', 75388696739713024, 9.00, 0, '导出操作(output)', 'output');
INSERT INTO `t_dict_data` VALUES (75391475042488320, 'admin', '2018-11-15 15:11:09', 0, 'admin', '2018-11-15 15:11:09', '', 75388696739713024, 10.00, 0, '导入操作(input)', 'input');
INSERT INTO `t_dict_data` VALUES (75391522182270976, 'admin', '2018-11-15 15:11:21', 0, 'admin', '2018-11-15 15:11:21', '', 75388696739713024, 11.00, 0, '分配权限(editPerm)', 'editPerm');
INSERT INTO `t_dict_data` VALUES (75391576364290048, 'admin', '2018-11-15 15:11:34', 0, 'admin', '2018-11-15 15:11:34', '', 75388696739713024, 12.00, 0, '设为默认(setDefault)', 'setDefault');
INSERT INTO `t_dict_data` VALUES (75391798033256448, 'admin', '2018-11-15 15:12:26', 0, 'admin', '2018-11-15 15:12:26', '', 75388696739713024, 13.00, 0, '其他操作(other)', 'other');
INSERT INTO `t_dict_data` VALUES (1443588658060988416, 'admin', '2021-09-30 09:48:51', 0, 'admin', '2021-09-30 09:48:51', '', 1443588533548879872, 0.00, 0, '企业用车', 'company');
INSERT INTO `t_dict_data` VALUES (1443588770107625472, 'admin', '2021-09-30 09:49:18', 0, 'admin', '2021-09-30 09:49:18', '', 1443588533548879872, 0.00, 0, '个人用车', 'single');
INSERT INTO `t_dict_data` VALUES (1443588962487767040, 'admin', '2021-09-30 09:50:04', 0, 'admin', '2021-09-30 09:50:04', '', 1443588533548879872, 0.00, 0, '机关用车', 'office');
INSERT INTO `t_dict_data` VALUES (1443589570372440064, 'admin', '2021-09-30 09:52:29', 0, 'admin', '2021-10-22 02:23:02', '', 1443589233368502272, 0.00, 0, '营运', '1');
INSERT INTO `t_dict_data` VALUES (1443589656842211328, 'admin', '2021-09-30 09:52:49', 0, 'admin', '2021-10-22 02:22:56', '', 1443589233368502272, 0.00, 0, '非营运', '2');
INSERT INTO `t_dict_data` VALUES (1443589993951006721, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-09-30 09:54:10', '', 1443589775079641088, 0.00, 0, '货车', '1');
INSERT INTO `t_dict_data` VALUES (1443589993951006722, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:55:34', '', 1443589775079641088, 0.00, 0, '挂车', '2');
INSERT INTO `t_dict_data` VALUES (1443589993951006723, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:55:40', '', 1443589775079641088, 0.00, 0, '客车（公路客运）', '3');
INSERT INTO `t_dict_data` VALUES (1443589993951006724, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:55:45', '', 1443589775079641088, 0.00, 0, '客车（出租）', '4');
INSERT INTO `t_dict_data` VALUES (1443589993951006725, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:55:54', '', 1443589775079641088, 0.00, 0, '低速载货、三轮', '5');
INSERT INTO `t_dict_data` VALUES (1443589993951006726, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:55:59', '', 1443589775079641088, 0.00, 0, '特种车', '6');
INSERT INTO `t_dict_data` VALUES (1443589993951006727, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:56:04', '', 1443589775079641088, 0.00, 0, '客车（家庭自用）', '7');
INSERT INTO `t_dict_data` VALUES (1443589993951006728, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:56:08', '', 1443589775079641088, 0.00, 0, '客车（企业自用）', '8');
INSERT INTO `t_dict_data` VALUES (1443589993951006729, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:56:14', '', 1443589775079641088, 0.00, 0, '客车（党政机关事业团体自用）', '9');
INSERT INTO `t_dict_data` VALUES (1443589993951006730, 'admin', '2021-09-30 09:54:10', 0, 'admin', '2021-10-21 21:56:19', '', 1443589775079641088, 0.00, 0, '城市公交', '10');
INSERT INTO `t_dict_data` VALUES (1443594910379937792, 'admin', '2021-09-30 10:13:42', 0, 'admin', '2021-10-22 10:38:10', '', 1443594794969468928, 0.00, 0, '牵引车', '1');
INSERT INTO `t_dict_data` VALUES (1443595149841141760, 'admin', '2021-09-30 10:14:39', 0, 'admin', '2021-10-22 02:51:24', '', 1443595072150048768, 0.00, 0, '10万', '100000');
INSERT INTO `t_dict_data` VALUES (1443596184957620224, 'admin', '2021-09-30 10:18:46', 0, 'admin', '2021-09-30 10:18:46', '', 1443596083673567232, 0.00, 0, '年度统筹', 'year');
INSERT INTO `t_dict_data` VALUES (1443596232361644032, 'admin', '2021-09-30 10:18:57', 0, 'admin', '2021-09-30 10:18:57', '', 1443596083673567232, 0.00, 0, '短期统筹', 'month');
INSERT INTO `t_dict_data` VALUES (1443596400565817344, 'admin', '2021-09-30 10:19:37', 0, 'admin', '2021-09-30 10:19:37', '', 1443596355175059456, 0.00, 0, '1', '1');
INSERT INTO `t_dict_data` VALUES (1443596420597813248, 'admin', '2021-09-30 10:19:42', 0, 'admin', '2021-09-30 10:19:42', '', 1443596355175059456, 2.00, 0, '2', '2');
INSERT INTO `t_dict_data` VALUES (1443596444236910592, 'admin', '2021-09-30 10:19:48', 0, 'admin', '2021-09-30 10:19:48', '', 1443596355175059456, 3.00, 0, '3', '3');
INSERT INTO `t_dict_data` VALUES (1443596469713113088, 'admin', '2021-09-30 10:19:54', 0, 'admin', '2021-09-30 10:19:54', '', 1443596355175059456, 4.00, 0, '4', '4');
INSERT INTO `t_dict_data` VALUES (1443596495231258624, 'admin', '2021-09-30 10:20:00', 0, 'admin', '2021-09-30 10:20:00', '', 1443596355175059456, 5.00, 0, '5', '5');
INSERT INTO `t_dict_data` VALUES (1443596514579582976, 'admin', '2021-09-30 10:20:04', 0, 'admin', '2021-09-30 10:20:04', '', 1443596355175059456, 6.00, 0, '6', '6');
INSERT INTO `t_dict_data` VALUES (1443596534229897216, 'admin', '2021-09-30 10:20:09', 0, 'admin', '2021-09-30 10:20:09', '', 1443596355175059456, 7.00, 0, '7', '7');
INSERT INTO `t_dict_data` VALUES (1443596555960586240, 'admin', '2021-09-30 10:20:14', 0, 'admin', '2021-09-30 10:20:14', '', 1443596355175059456, 8.00, 0, '8', '8');
INSERT INTO `t_dict_data` VALUES (1443596573450833920, 'admin', '2021-09-30 10:20:19', 0, 'admin', '2021-09-30 10:20:19', '', 1443596355175059456, 9.00, 0, '9', '9');
INSERT INTO `t_dict_data` VALUES (1443596609723174912, 'admin', '2021-09-30 10:20:27', 0, 'admin', '2021-09-30 10:20:27', '', 1443596355175059456, 10.00, 0, '10', '10');
INSERT INTO `t_dict_data` VALUES (1443596642665238528, 'admin', '2021-09-30 10:20:35', 0, 'admin', '2021-09-30 10:20:35', '', 1443596355175059456, 11.00, 0, '11', '11');
INSERT INTO `t_dict_data` VALUES (1443596675204648960, 'admin', '2021-09-30 10:20:43', 0, 'admin', '2021-09-30 10:20:43', '', 1443596355175059456, 12.00, 0, '12', '12');
INSERT INTO `t_dict_data` VALUES (1443602587214548992, 'admin', '2021-09-30 10:44:12', 0, 'admin', '2021-10-23 02:24:12', '', 1443600051065720832, 0.00, 0, '1', '若发生事故时，本标的的行驶证未按规定检验或者检验不合格，统筹人不承担赔偿责任。');
INSERT INTO `t_dict_data` VALUES (1443609772405428224, 'admin', '2021-09-30 11:12:45', 0, 'admin', '2021-09-30 11:12:45', '', 1443609680856354816, 0.00, 0, '自然人', '自然人');
INSERT INTO `t_dict_data` VALUES (1443609806773555200, 'admin', '2021-09-30 11:12:54', 0, 'admin', '2021-09-30 11:12:54', '', 1443609680856354816, 0.00, 0, '法人', '法人');
INSERT INTO `t_dict_data` VALUES (1443772373328334848, 'admin', '2021-09-30 21:58:52', 0, 'admin', '2021-09-30 21:58:52', '', 1443772132722085888, 0.00, 0, '机动车损失统筹', 'carloss');
INSERT INTO `t_dict_data` VALUES (1443772573224669184, 'admin', '2021-09-30 21:59:40', 0, 'admin', '2021-10-24 07:36:35', '', 1443772132722085888, 1.00, 0, '第三者责任统筹', 'thirdDuty');
INSERT INTO `t_dict_data` VALUES (1443772768314331136, 'admin', '2021-09-30 22:00:27', 0, 'admin', '2021-10-24 07:36:38', '', 1443772132722085888, 2.00, 0, '车上人员责任统筹(驾驶员)', 'driver');
INSERT INTO `t_dict_data` VALUES (1443772933444079616, 'admin', '2021-09-30 22:01:06', 0, 'admin', '2021-10-24 07:36:42', '', 1443772132722085888, 3.00, 0, '车上人员责任统筹(乘客)', 'passenger');
INSERT INTO `t_dict_data` VALUES (1444958525234221056, 'admin', '2021-10-04 04:32:13', 0, 'admin', '2021-10-23 06:18:28', '', 1444958458309906432, 3.00, 0, '10万', '100000');
INSERT INTO `t_dict_data` VALUES (1444958561082937344, 'admin', '2021-10-04 04:32:22', 0, 'admin', '2021-10-23 06:18:34', '', 1444958458309906432, 4.00, 0, '20万', '200000');
INSERT INTO `t_dict_data` VALUES (1444965672537952256, 'admin', '2021-10-04 05:00:37', 0, 'admin', '2021-10-04 05:00:37', '', 1444965620495028224, 0.00, 0, '1座', '1');
INSERT INTO `t_dict_data` VALUES (1444965710546735104, 'admin', '2021-10-04 05:00:46', 0, 'admin', '2021-10-24 07:33:38', '', 1444965620495028224, 1.00, 0, '2座', '2');
INSERT INTO `t_dict_data` VALUES (1445257931716169728, 'admin', '2021-10-05 00:21:57', 0, 'admin', '2021-10-05 00:21:57', '', 1445257863781027840, 0.00, 0, '1', '0.1');
INSERT INTO `t_dict_data` VALUES (1445257956814884864, 'admin', '2021-10-05 00:22:03', 0, 'admin', '2021-10-05 00:22:03', '', 1445257863781027840, 0.00, 0, '2', '0.2');
INSERT INTO `t_dict_data` VALUES (1445257983188668416, 'admin', '2021-10-05 00:22:09', 0, 'admin', '2021-10-05 00:22:09', '', 1445257863781027840, 0.00, 0, '3', '0.3');
INSERT INTO `t_dict_data` VALUES (1445258011814793216, 'admin', '2021-10-05 00:22:16', 0, 'admin', '2021-10-05 00:22:16', '', 1445257863781027840, 0.00, 0, '4', '0.4');
INSERT INTO `t_dict_data` VALUES (1445258037395853312, 'admin', '2021-10-05 00:22:22', 0, 'admin', '2021-10-05 00:22:22', '', 1445257863781027840, 0.00, 0, '5', '0.5');
INSERT INTO `t_dict_data` VALUES (1445258062356156416, 'admin', '2021-10-05 00:22:28', 0, 'admin', '2021-10-05 00:22:28', '', 1445257863781027840, 0.00, 0, '6', '0.6');
INSERT INTO `t_dict_data` VALUES (1445258200176791552, 'admin', '2021-10-05 00:23:01', 0, 'admin', '2021-10-05 00:23:01', '', 1445257863781027840, 0.00, 0, '7', '0.7');
INSERT INTO `t_dict_data` VALUES (1445258225917235200, 'admin', '2021-10-05 00:23:07', 0, 'admin', '2021-10-05 00:23:07', '', 1445257863781027840, 0.00, 0, '8', '0.8');
INSERT INTO `t_dict_data` VALUES (1445258275602960384, 'admin', '2021-10-05 00:23:19', 0, 'admin', '2021-10-05 00:23:19', '', 1445257863781027840, 0.00, 0, '9', '0.85');
INSERT INTO `t_dict_data` VALUES (1445258329877254144, 'admin', '2021-10-05 00:23:32', 0, 'admin', '2021-10-05 00:23:32', '', 1445257863781027840, 0.00, 0, '10', '0.9');
INSERT INTO `t_dict_data` VALUES (1445258358004256768, 'admin', '2021-10-05 00:23:39', 0, 'admin', '2021-10-05 00:23:39', '', 1445257863781027840, 0.00, 0, '11', '0.95');
INSERT INTO `t_dict_data` VALUES (1445258417668231168, 'admin', '2021-10-05 00:23:53', 0, 'admin', '2021-10-05 00:23:53', '', 1445257863781027840, 0.00, 0, '12', '1');
INSERT INTO `t_dict_data` VALUES (1446472874159378432, 'admin', '2021-10-08 08:49:42', 0, 'admin', '2021-10-08 08:49:42', '', 1446472824091971584, 0.00, 0, '微信', 'weixin');
INSERT INTO `t_dict_data` VALUES (1446472914055598080, 'admin', '2021-10-08 08:49:52', 0, 'admin', '2021-10-08 08:49:52', '', 1446472824091971584, 0.00, 0, '支付宝', 'zhifubao');
INSERT INTO `t_dict_data` VALUES (1447065373152251904, 'admin', '2021-10-10 00:04:05', 0, 'admin', '2021-10-10 07:16:18', '', 75388696739713024, 0.00, 0, '统筹单管理-审核操作(orderCheck)', 'orderCheck');
INSERT INTO `t_dict_data` VALUES (1447151854357188608, 'admin', '2021-10-10 05:47:44', 0, 'admin', '2021-10-10 07:17:26', '', 75388696739713024, 0.00, 0, '统筹单管理-报价单(orderBaojia)', 'orderBaojia');
INSERT INTO `t_dict_data` VALUES (1447151951665041408, 'admin', '2021-10-10 05:48:07', 0, 'admin', '2021-10-10 07:18:06', '', 75388696739713024, 0.00, 0, '统筹单管理-提交审核(orderCommitForAudit)', 'orderCommitForAudit');
INSERT INTO `t_dict_data` VALUES (1447172706490519552, 'admin', '2021-10-10 07:10:35', 0, 'admin', '2021-10-10 07:15:54', '', 75388696739713024, 0.00, 0, '统筹单管理-添加(orderAdd)', 'orderAdd');
INSERT INTO `t_dict_data` VALUES (1447172977803268096, 'admin', '2021-10-10 07:11:40', 0, 'admin', '2021-10-10 07:16:41', '', 75388696739713024, 0.00, 0, '统筹单管理-删除(orderDelete)', 'orderDelete');
INSERT INTO `t_dict_data` VALUES (1447173124654239744, 'admin', '2021-10-10 07:12:15', 0, 'admin', '2021-10-10 07:16:58', '', 75388696739713024, 0.00, 0, '统筹单管理-编辑(orderEdit)', 'orderEdit');
INSERT INTO `t_dict_data` VALUES (1447173854433775616, 'admin', '2021-10-10 07:15:09', 0, 'admin', '2021-10-10 07:15:46', '', 75388696739713024, 0.00, 0, '统筹单管理-暂存保存(orderZancun)', 'orderZancun');
INSERT INTO `t_dict_data` VALUES (1447174795262300160, 'admin', '2021-10-10 07:18:53', 0, 'admin', '2021-10-10 07:18:53', '', 75388696739713024, 0.00, 0, '统筹单管理-提交并保存(orderCommitAndSave)', 'orderCommitAndSave');
INSERT INTO `t_dict_data` VALUES (1449358402139590656, 'admin', '2021-10-16 07:55:46', 0, 'admin', '2021-10-16 08:15:32', '调查员角色id,非开发人员不要更改', 1449358222141034496, 0.00, 0, 'diaochaRoleID', '1449357433590910976');
INSERT INTO `t_dict_data` VALUES (1450343261381201920, 'admin', '2021-10-19 01:09:14', 0, 'admin', '2021-10-19 01:09:14', '', 1450341902045024256, 0.00, 0, '一类供应商', 'supplierLevel1');
INSERT INTO `t_dict_data` VALUES (1450343310064488448, 'admin', '2021-10-19 01:09:26', 0, 'admin', '2021-10-24 07:34:35', '', 1450341902045024256, 1.00, 0, '二类供应商', 'supplierLevel2');
INSERT INTO `t_dict_data` VALUES (1450343360316444672, 'admin', '2021-10-19 01:09:38', 0, 'admin', '2021-10-24 07:34:38', '', 1450341902045024256, 2.00, 0, '三类供应商', 'supplierLevel3');
INSERT INTO `t_dict_data` VALUES (1450710110929293312, 'admin', '2021-10-20 01:26:58', 0, 'admin', '2021-10-20 01:26:58', '', 1450709963231072256, 0.00, 0, '行驶证', 'runCard');
INSERT INTO `t_dict_data` VALUES (1450729167405584384, 'admin', '2021-10-20 02:42:42', 0, 'admin', '2021-10-20 02:42:42', '', 1450709963231072256, 0.00, 0, '事故照片', 'accidentPic');
INSERT INTO `t_dict_data` VALUES (1451009716317720576, 'admin', '2021-10-20 21:17:30', 0, 'admin', '2021-10-20 21:17:30', '', 75388696739713024, 0.00, 0, '互助补偿管理-案件受理', 'helpOrderCommit');
INSERT INTO `t_dict_data` VALUES (1451009874195517440, 'admin', '2021-10-20 21:18:07', 0, 'admin', '2021-10-20 21:18:07', '', 75388696739713024, 0.00, 0, '互助补偿管理-删除', 'helpOrderDelete');
INSERT INTO `t_dict_data` VALUES (1451009992541999104, 'admin', '2021-10-20 21:18:36', 0, 'admin', '2021-10-20 21:18:36', '', 75388696739713024, 0.00, 0, '互助补偿管理-编辑', 'helperOrderEdit');
INSERT INTO `t_dict_data` VALUES (1451010083587756032, 'admin', '2021-10-20 21:18:57', 0, 'admin', '2021-10-20 21:18:57', '', 75388696739713024, 0.00, 0, '互助补偿管理-调查', 'helpOrderDiaocha');
INSERT INTO `t_dict_data` VALUES (1451010164688818176, 'admin', '2021-10-20 21:19:17', 0, 'admin', '2021-10-20 21:19:17', '', 75388696739713024, 0.00, 0, '互助补偿管理-审核', 'helpOrderCheck');
INSERT INTO `t_dict_data` VALUES (1451010369047891968, 'admin', '2021-10-20 21:20:05', 0, 'admin', '2021-10-20 21:20:05', '', 75388696739713024, 0.00, 0, '互助补偿管理-调查派工', 'helpOrderPaigong');
INSERT INTO `t_dict_data` VALUES (1451010669104205824, 'admin', '2021-10-20 21:21:17', 0, 'admin', '2021-10-20 21:21:17', '', 75388696739713024, 0.00, 0, '互助补偿管理-编辑-提交并保存', 'helpOrderEditCommit');
INSERT INTO `t_dict_data` VALUES (1451010891540729856, 'admin', '2021-10-20 21:22:10', 0, 'admin', '2021-10-20 21:23:07', '', 75388696739713024, 0.00, 0, '互助补偿管理-调查-损失列表-添加子数据', 'helpOrderDiaochaAddchild');
INSERT INTO `t_dict_data` VALUES (1451012062510714880, 'admin', '2021-10-20 21:26:49', 0, 'admin', '2021-10-20 21:26:49', '', 75388696739713024, 0.00, 0, '互助补偿管理-损失列表-修改并保存', 'helpOrderDamagelistCommit');
INSERT INTO `t_dict_data` VALUES (1451012308762497024, 'admin', '2021-10-20 21:27:48', 0, 'admin', '2021-10-20 21:27:48', '', 75388696739713024, 0.00, 0, '互助补偿管理-其他信息-修改并保存', 'helpOrderOtherMsgCommit');
INSERT INTO `t_dict_data` VALUES (1451012519605964800, 'admin', '2021-10-20 21:28:38', 0, 'admin', '2021-10-20 21:28:38', '', 75388696739713024, 0.00, 0, '互助补偿管理-文件-修改并保存', 'helpOrderFileCommit');
INSERT INTO `t_dict_data` VALUES (1451012705430409216, 'admin', '2021-10-20 21:29:22', 0, 'admin', '2021-10-20 21:29:22', '', 75388696739713024, 0.00, 0, '互助补偿管理-提交审核', 'helpOrderCommitForCheck');
INSERT INTO `t_dict_data` VALUES (1451013409758908416, 'admin', '2021-10-20 21:32:10', 0, 'admin', '2021-10-20 21:32:10', '', 75388696739713024, 0.00, 0, '互助补偿管理-审核通过', 'helpOrderCheckSuccess');
INSERT INTO `t_dict_data` VALUES (1451013673903591424, 'admin', '2021-10-20 21:33:13', 0, 'admin', '2021-10-20 21:33:13', '', 75388696739713024, 0.00, 0, '互助补偿管理-退回调查', 'helpOrderCheckFail');
INSERT INTO `t_dict_data` VALUES (1451021332019023872, 'admin', '2021-10-20 22:03:39', 0, 'admin', '2021-10-20 22:21:22', '', 75388696739713024, 0.00, 0, '互助补偿管理-损失列表-修改并保存2', 'helpOrderDamagelistCommitSecound');
INSERT INTO `t_dict_data` VALUES (1451456205355618304, 'admin', '2021-10-22 02:51:41', 0, 'admin', '2021-10-24 07:32:21', '', 1443595072150048768, 5.00, 0, '100万', '1000000');
INSERT INTO `t_dict_data` VALUES (1451456268257595392, 'admin', '2021-10-22 02:51:56', 0, 'admin', '2021-10-24 07:33:29', '', 1443595072150048768, 13.00, 0, '1000万', '10000000');
INSERT INTO `t_dict_data` VALUES (1451456324704538624, 'admin', '2021-10-22 02:52:09', 0, 'admin', '2021-10-24 07:32:12', '', 1443595072150048768, 4.00, 0, '50万', '500000');
INSERT INTO `t_dict_data` VALUES (1451456381600272384, 'admin', '2021-10-22 02:52:23', 0, 'admin', '2021-10-24 07:33:04', '', 1443595072150048768, 10.00, 0, '500万', '5000000');
INSERT INTO `t_dict_data` VALUES (1451456422066917376, 'admin', '2021-10-22 02:52:33', 0, 'admin', '2021-10-24 07:33:12', '', 1443595072150048768, 11.00, 0, '600万', '6000000');
INSERT INTO `t_dict_data` VALUES (1451456465406660608, 'admin', '2021-10-22 02:52:43', 0, 'admin', '2021-10-24 07:31:57', '', 1443595072150048768, 3.00, 0, '30万', '300000');
INSERT INTO `t_dict_data` VALUES (1451456514631012352, 'admin', '2021-10-22 02:52:55', 0, 'admin', '2021-10-24 07:32:50', '', 1443595072150048768, 8.00, 0, '300万', '3000000');
INSERT INTO `t_dict_data` VALUES (1451456558398574592, 'admin', '2021-10-22 02:53:05', 0, 'admin', '2021-10-24 07:31:25', '', 1443595072150048768, 1.00, 0, '15万', '150000');
INSERT INTO `t_dict_data` VALUES (1451456599381118976, 'admin', '2021-10-22 02:53:15', 0, 'admin', '2021-10-24 07:32:32', '', 1443595072150048768, 6.00, 0, '150万', '1500000');
INSERT INTO `t_dict_data` VALUES (1451456644042067968, 'admin', '2021-10-22 02:53:25', 0, 'admin', '2021-10-24 07:33:22', '', 1443595072150048768, 12.00, 0, '800万', '8000000');
INSERT INTO `t_dict_data` VALUES (1451456683263004672, 'admin', '2021-10-22 02:53:35', 0, 'admin', '2021-10-24 07:32:57', '', 1443595072150048768, 9.00, 0, '400万', '4000000');
INSERT INTO `t_dict_data` VALUES (1451456729958191104, 'admin', '2021-10-22 02:53:46', 0, 'admin', '2021-10-24 07:31:37', '', 1443595072150048768, 2.00, 0, '20万', '200000');
INSERT INTO `t_dict_data` VALUES (1451456780113678336, 'admin', '2021-10-22 02:53:58', 0, 'admin', '2021-10-24 07:32:41', '', 1443595072150048768, 7.00, 0, '200万', '2000000');
INSERT INTO `t_dict_data` VALUES (1451573650800119808, 'admin', '2021-10-22 10:38:22', 0, 'admin', '2021-10-22 10:38:22', '', 1443594794969468928, 0.00, 0, '自卸车', '2');
INSERT INTO `t_dict_data` VALUES (1451573694517350400, 'admin', '2021-10-22 10:38:32', 0, 'admin', '2021-10-22 10:38:32', '', 1443594794969468928, 0.00, 0, '特种车', '3');
INSERT INTO `t_dict_data` VALUES (1451573735541837824, 'admin', '2021-10-22 10:38:42', 0, 'admin', '2021-10-22 10:38:42', '', 1443594794969468928, 0.00, 0, '普通货车', '4');
INSERT INTO `t_dict_data` VALUES (1451573780739657728, 'admin', '2021-10-22 10:38:53', 0, 'admin', '2021-10-22 10:38:53', '', 1443594794969468928, 0.00, 0, '新能源普通货车', '5');
INSERT INTO `t_dict_data` VALUES (1451573820451328000, 'admin', '2021-10-22 10:39:03', 0, 'admin', '2021-10-22 10:39:03', '', 1443594794969468928, 0.00, 0, '客车', '6');
INSERT INTO `t_dict_data` VALUES (1451573863145148416, 'admin', '2021-10-22 10:39:13', 0, 'admin', '2021-10-22 10:39:13', '', 1443594794969468928, 0.00, 0, '挂车', '7');
INSERT INTO `t_dict_data` VALUES (1451811749987749888, 'admin', '2021-10-23 02:24:29', 0, 'admin', '2021-10-23 02:24:29', '', 1443600051065720832, 0.00, 0, '2', '理赔时作业人员无合法有效的道路运输从业资格证（上岗证），属于责任免责。');
INSERT INTO `t_dict_data` VALUES (1451811782434885632, 'admin', '2021-10-23 02:24:37', 0, 'admin', '2021-10-23 02:24:37', '', 1443600051065720832, 0.00, 0, '3', '被统筹机动车因改装加装改变使用性质导致危险程度显著增加以及转卖转让或赠送他人应及时通知统筹人并办理变更手续。');
INSERT INTO `t_dict_data` VALUES (1451811816391970816, 'admin', '2021-10-23 02:24:45', 0, 'admin', '2021-10-23 02:24:45', '', 1443600051065720832, 0.00, 0, '4', '不计免赔率特约统筹只包含（选定统筹项目中是）不计免赔');
INSERT INTO `t_dict_data` VALUES (1451811852966301696, 'admin', '2021-10-23 02:24:54', 0, 'admin', '2021-10-23 02:24:54', '', 1443600051065720832, 0.00, 0, '5', '机动车辆在行驶过程中因锁止装置故障或疏忽大意未放到锁止位置发生碰撞事故，属于除外责任。');
INSERT INTO `t_dict_data` VALUES (1451811880438992896, 'admin', '2021-10-23 02:25:00', 0, 'admin', '2021-10-23 02:25:00', '', 1443600051065720832, 0.00, 0, '6', '未经交管部门处理或我公司同意，擅自撤离现场，统筹人最高承担核损金额的50%赔偿责任，直至拒赔。');
INSERT INTO `t_dict_data` VALUES (1451811911736889344, 'admin', '2021-10-23 02:25:08', 0, 'admin', '2021-10-23 02:25:08', '', 1443600051065720832, 0.00, 0, '7', '本统筹单被统筹人与车主不一致，被统筹人为***，车主为***。');
INSERT INTO `t_dict_data` VALUES (1451811945819803648, 'admin', '2021-10-23 02:25:16', 0, 'admin', '2021-10-23 02:25:16', '', 1443600051065720832, 0.00, 0, '8', '***为本统筹单第一受益人，未经第一受益人事先书面同意统筹单不能做任何批改。');
INSERT INTO `t_dict_data` VALUES (1451811979214852096, 'admin', '2021-10-23 02:25:24', 0, 'admin', '2021-10-23 02:25:24', '', 1443600051065720832, 0.00, 0, '9', '本统筹单参统的***号挂车为***号车辆的无动力挂车，附挂在其他车辆上使用时所发生的车辆本身及第三者损失，不负责赔偿责任。');
INSERT INTO `t_dict_data` VALUES (1451812017190080512, 'admin', '2021-10-23 02:25:33', 0, 'admin', '2021-10-23 02:25:33', '', 1443600051065720832, 0.00, 0, '10', '半挂牵引车主挂一体时，挂车第三者责任以主车限额为限，主挂分离时按两车分别参统的限额为准。');
INSERT INTO `t_dict_data` VALUES (1451812060647264256, 'admin', '2021-10-23 02:25:43', 0, 'admin', '2021-10-23 02:25:43', '', 1443600051065720832, 0.00, 0, '11', '水泥罐车因意外事故或不及时施救，致使罐体停转或水泥凝固而导致的固罐的损失，属于除外责任。');
INSERT INTO `t_dict_data` VALUES (1451812088052846592, 'admin', '2021-10-23 02:25:50', 0, 'admin', '2021-10-23 02:25:50', '', 1443600051065720832, 0.00, 0, '12', '统筹车辆为不足额参统时，交通事故发生后按统筹金额与车辆实际价值的比例进行互助补偿.');
INSERT INTO `t_dict_data` VALUES (1451812117287145472, 'admin', '2021-10-23 02:25:57', 0, 'admin', '2021-10-23 02:25:57', '', 1443600051065720832, 0.00, 0, '13', '本无动力挂车如挂在无商业保险或者统筹的牵引车上使用，发生事故后我公司不负责补偿。');
INSERT INTO `t_dict_data` VALUES (1451812149151272960, 'admin', '2021-10-23 02:26:04', 0, 'admin', '2021-10-23 02:26:04', '', 1443600051065720832, 0.00, 0, '14', '本公司于参统单生效前验车发现该车辆XXXX部分有损失，以验车照为准，对于此部分损失，本公司不负责赔偿。');
INSERT INTO `t_dict_data` VALUES (1451812175445364736, 'admin', '2021-10-23 02:26:11', 0, 'admin', '2021-10-23 02:26:11', '', 1443600051065720832, 0.00, 0, '15', '本统筹单***损失统筹自验车合格来我司出批单更改后生效，保险止期不变。');
INSERT INTO `t_dict_data` VALUES (1451812203731750912, 'admin', '2021-10-23 02:26:17', 0, 'admin', '2021-10-23 02:26:17', '', 1443600051065720832, 0.00, 0, '16', '相关人员在水泥罐车作业过程中，因检查其工作状况而导致的人身伤亡，属于除外责任。');
INSERT INTO `t_dict_data` VALUES (1451812235117727744, 'admin', '2021-10-23 02:26:25', 0, 'admin', '2021-10-23 02:26:25', '', 1443600051065720832, 0.00, 0, '17', '（1）统筹车辆在装卸过程中任何原因造成液压升降系统的损失以及统筹车辆车厢在举升状态下行驶造成的损失，统筹人不负担补偿责任。（2）自卸车因后置车厢未放置正常位置所发生的事故本公司不负责补偿责任，自卸车在装卸过程中所发生的倾覆事故本公司不负责补偿责任。');
INSERT INTO `t_dict_data` VALUES (1451812269892702208, 'admin', '2021-10-23 02:26:33', 0, 'admin', '2021-10-23 02:26:33', '', 1443600051065720832, 0.00, 0, '18', '本统筹单机动车损失统筹为不足额参统，发生部分损失时按比例赔付。');
INSERT INTO `t_dict_data` VALUES (1451812305640755200, 'admin', '2021-10-23 02:26:42', 0, 'admin', '2021-10-23 02:26:42', '', 1443600051065720832, 0.00, 0, '21', '此车按非营业投保。如用于营业发生保险事故，统筹人不承担赔偿责任。');
INSERT INTO `t_dict_data` VALUES (1451812336129150976, 'admin', '2021-10-23 02:26:49', 0, 'admin', '2021-10-23 02:26:49', '', 1443600051065720832, 0.00, 0, '22', '统筹车辆单方肇事后，被统筹人必须第一时间通知本公司，凡通知不及时或未经本公司同意擅自撤离现场，造成无法收集第一现场资料者，本公司有权拒绝赔偿。');
INSERT INTO `t_dict_data` VALUES (1451870265180033024, 'admin', '2021-10-23 06:17:00', 0, 'admin', '2021-10-23 06:17:00', '', 1444958458309906432, 0.00, 0, '1万', '10000');
INSERT INTO `t_dict_data` VALUES (1451870315851419648, 'admin', '2021-10-23 06:17:12', 0, 'admin', '2021-10-23 06:17:58', '', 1444958458309906432, 1.00, 0, '2万', '20000');
INSERT INTO `t_dict_data` VALUES (1451870346700525568, 'admin', '2021-10-23 06:17:20', 0, 'admin', '2021-10-23 06:18:14', '', 1444958458309906432, 2.00, 0, '5万', '50000');
INSERT INTO `t_dict_data` VALUES (1451870407115280384, 'admin', '2021-10-23 06:17:34', 0, 'admin', '2021-10-23 06:18:41', '', 1444958458309906432, 5.00, 0, '30万', '300000');
INSERT INTO `t_dict_data` VALUES (1451870438706778112, 'admin', '2021-10-23 06:17:42', 0, 'admin', '2021-10-23 06:18:47', '', 1444958458309906432, 6.00, 0, '50万', '500000');
INSERT INTO `t_dict_data` VALUES (1452252000220745728, 'admin', '2021-10-24 07:33:53', 0, 'admin', '2021-10-24 07:33:53', '', 1444965620495028224, 3.00, 0, '3座', '3');
INSERT INTO `t_dict_data` VALUES (1452252042264449024, 'admin', '2021-10-24 07:34:03', 0, 'admin', '2021-10-24 07:34:03', '', 1444965620495028224, 4.00, 0, '4座', '4');
INSERT INTO `t_dict_data` VALUES (1452252079996407808, 'admin', '2021-10-24 07:34:12', 0, 'admin', '2021-10-24 07:34:12', '', 1444965620495028224, 5.00, 0, '5座', '5');
INSERT INTO `t_dict_data` VALUES (1452252117543817216, 'admin', '2021-10-24 07:34:21', 0, 'admin', '2021-10-24 07:34:21', '', 1444965620495028224, 6.00, 0, '6座', '6');
INSERT INTO `t_dict_data` VALUES (1452252342891188224, 'admin', '2021-10-24 07:35:15', 0, 'admin', '2021-10-24 07:35:15', '', 1446472824091971584, 0.00, 0, '银行卡', 'bankCard');
INSERT INTO `t_dict_data` VALUES (1454340329783300096, 'admin', '2021-10-30 01:52:10', 0, 'admin', '2021-10-30 01:52:10', '', 75388696739713024, 0.00, 0, '费率配置-搜索/刷新', 'fee-rate-manager-search');
INSERT INTO `t_dict_data` VALUES (1454340498440458240, 'admin', '2021-10-30 01:52:50', 0, 'admin', '2021-10-30 01:52:50', '', 75388696739713024, 0.00, 0, '费率配置-删除', 'fee-rate-manager-delete');
INSERT INTO `t_dict_data` VALUES (1454343724766203904, 'admin', '2021-10-30 02:05:39', 0, 'admin', '2021-10-30 02:05:39', '', 75388696739713024, 0.00, 0, '费率配置-编辑', 'fee-rate-manager-edit');
INSERT INTO `t_dict_data` VALUES (1454343915690921984, 'admin', '2021-10-30 02:06:25', 0, 'admin', '2021-10-30 02:58:45', '', 75388696739713024, 0.00, 0, '费率配置-编辑/添加-提交', 'fee-rate-manager-edit-commit');
INSERT INTO `t_dict_data` VALUES (1454344221493432320, 'admin', '2021-10-30 02:07:38', 0, 'admin', '2021-10-30 02:07:38', '', 75388696739713024, 0.00, 0, '费率优惠政策-搜索/刷新', 'fee-youhui-rate-search');
INSERT INTO `t_dict_data` VALUES (1454344333489737728, 'admin', '2021-10-30 02:08:04', 0, 'admin', '2021-10-30 02:08:04', '', 75388696739713024, 0.00, 0, '费率优惠政策-添加', 'fee-youhui-rate-add');
INSERT INTO `t_dict_data` VALUES (1454344431405764608, 'admin', '2021-10-30 02:08:28', 0, 'admin', '2021-10-30 02:08:28', '', 75388696739713024, 0.00, 0, '费率优惠政策-删除', 'fee-youhui-rate-delete');
INSERT INTO `t_dict_data` VALUES (1454344541086814208, 'admin', '2021-10-30 02:08:54', 0, 'admin', '2021-10-30 02:08:54', '', 75388696739713024, 0.00, 0, '费率优惠政策-编辑', 'fee-youhui-rate-edit');
INSERT INTO `t_dict_data` VALUES (1454344684099997696, 'admin', '2021-10-30 02:09:28', 0, 'admin', '2021-10-30 03:01:25', '', 75388696739713024, 0.00, 0, '费率优惠政策-编辑/添加-提交', 'fee-youhui-rate-edit-commit');
INSERT INTO `t_dict_data` VALUES (1454344891944538112, 'admin', '2021-10-30 02:10:17', 0, 'admin', '2021-10-30 02:10:17', '', 75388696739713024, 0.00, 0, '渠道折扣-刷新/搜索', 'fee-channel-discount-rate-search');
INSERT INTO `t_dict_data` VALUES (1454345029958111232, 'admin', '2021-10-30 02:10:50', 0, 'admin', '2021-10-30 02:10:50', '', 75388696739713024, 0.00, 0, '渠道折扣配置-修改并保存', 'fee-channel-discount-rate-edit');
INSERT INTO `t_dict_data` VALUES (1454345200909553664, 'admin', '2021-10-30 02:11:31', 0, 'admin', '2021-10-30 02:11:31', '', 75388696739713024, 0.00, 0, '车型信息维护-搜索刷新', 'car-type-info-manager-search');
INSERT INTO `t_dict_data` VALUES (1454345329146204160, 'admin', '2021-10-30 02:12:02', 0, 'admin', '2021-10-30 02:12:02', '', 75388696739713024, 0.00, 0, '车型信息维护-添加', 'car-type-info-manager-add');
INSERT INTO `t_dict_data` VALUES (1454345441536774144, 'admin', '2021-10-30 02:12:28', 0, 'admin', '2021-10-30 02:12:28', '', 75388696739713024, 0.00, 0, '车型信息维护-编辑', 'car-type-info-manager-edit');
INSERT INTO `t_dict_data` VALUES (1454345515864035328, 'admin', '2021-10-30 02:12:46', 0, 'admin', '2021-10-30 02:12:46', '', 75388696739713024, 0.00, 0, '车型信息维护-删除', 'car-type-info-manager-delete');
INSERT INTO `t_dict_data` VALUES (1454345633187106816, 'admin', '2021-10-30 02:13:14', 0, 'admin', '2021-10-30 03:07:06', '', 75388696739713024, 0.00, 0, '车型信息维护-添加/编辑-提交', 'car-type-info-manager-add-commit');
INSERT INTO `t_dict_data` VALUES (1454346330574032896, 'admin', '2021-10-30 02:16:00', 0, 'admin', '2021-10-30 02:16:00', '', 75388696739713024, 0.00, 0, '车型月折扣配置-搜索/刷新', 'car-type-info-depreciation-search');
INSERT INTO `t_dict_data` VALUES (1454346563571814400, 'admin', '2021-10-30 02:16:56', 0, 'admin', '2021-10-30 02:16:56', '', 75388696739713024, 0.00, 0, '车型月折旧配置-添加', 'car-type-info-depreciation-add');
INSERT INTO `t_dict_data` VALUES (1454346657872351232, 'admin', '2021-10-30 02:17:18', 0, 'admin', '2021-10-30 02:17:18', '', 75388696739713024, 0.00, 0, '车型月折旧配置-编辑', 'car-type-info-depreciation-edit');
INSERT INTO `t_dict_data` VALUES (1454346729058078720, 'admin', '2021-10-30 02:17:35', 0, 'admin', '2021-10-30 02:17:35', '', 75388696739713024, 0.00, 0, '车型月折旧配置-删除', 'car-type-info-depreciation-delete');
INSERT INTO `t_dict_data` VALUES (1454346833970204672, 'admin', '2021-10-30 02:18:00', 0, 'admin', '2021-10-30 03:09:56', '', 75388696739713024, 0.00, 0, '车型月折旧配置-添加/编辑-提交', 'car-type-info-depreciation-add-commit');
INSERT INTO `t_dict_data` VALUES (1454347382203486208, 'admin', '2021-10-30 02:20:11', 0, 'admin', '2021-10-30 02:20:11', '', 75388696739713024, 0.00, 0, '批单管理-刷新/搜索', 'order-edit-manager-search');
INSERT INTO `t_dict_data` VALUES (1454349781995491328, 'admin', '2021-10-30 02:29:43', 0, 'admin', '2021-10-30 02:29:43', '', 75388696739713024, 0.00, 0, '批单管理-添加', 'order-edit-manager-add');
INSERT INTO `t_dict_data` VALUES (1454352067077476352, 'admin', '2021-10-30 02:38:48', 0, 'admin', '2021-10-30 02:38:48', '', 75388696739713024, 0.00, 0, '批单管理-提交审核', 'order-edit-manager-commitForAudit');
INSERT INTO `t_dict_data` VALUES (1454352214213660672, 'admin', '2021-10-30 02:39:23', 0, 'admin', '2021-10-30 02:39:23', '', 75388696739713024, 0.00, 0, '批单管理-修改并保存', 'order-edit-manager-edit-commit');
INSERT INTO `t_dict_data` VALUES (1454352406979678208, 'admin', '2021-10-30 02:40:09', 0, 'admin', '2021-10-30 02:40:09', '', 75388696739713024, 0.00, 0, '批单管理-审核通过/不通过', 'order-edit-manager-auditresult');
INSERT INTO `t_dict_data` VALUES (1454352534373273600, 'admin', '2021-10-30 02:40:40', 0, 'admin', '2021-10-30 02:40:40', '', 75388696739713024, 0.00, 0, '批单管理-查看', 'order-edit-manager-view');
INSERT INTO `t_dict_data` VALUES (1454352633144938496, 'admin', '2021-10-30 02:41:03', 0, 'admin', '2021-10-30 02:41:03', '', 75388696739713024, 0.00, 0, '批单管理-删除', 'order-edit-manager-delete');
INSERT INTO `t_dict_data` VALUES (1454352742544969728, 'admin', '2021-10-30 02:41:29', 0, 'admin', '2021-10-30 02:41:29', '', 75388696739713024, 0.00, 0, '批单管理-操作记录', 'order-edit-manager-actionlog');
INSERT INTO `t_dict_data` VALUES (1454364878377717760, 'admin', '2021-10-30 03:29:43', 0, 'admin', '2021-10-30 03:29:43', '', 75388696739713024, 0.00, 0, '收款维护-刷新/搜索', 'order-payinfo-manager-search');
INSERT INTO `t_dict_data` VALUES (1454364962758725632, 'admin', '2021-10-30 03:30:03', 0, 'admin', '2021-10-30 03:30:03', '', 75388696739713024, 0.00, 0, '收款维护-查看', 'order-payinfo-manager-view');
INSERT INTO `t_dict_data` VALUES (1454365038801457152, 'admin', '2021-10-30 03:30:21', 0, 'admin', '2021-10-30 03:30:21', '', 75388696739713024, 0.00, 0, '收款维护-收款', 'order-payinfo-manager-recieve');
INSERT INTO `t_dict_data` VALUES (1454365147157106688, 'admin', '2021-10-30 03:30:47', 0, 'admin', '2021-10-30 03:30:47', '', 75388696739713024, 0.00, 0, '收款维护-收款-提交', 'order-payinfo-manager-recieve-commit');
INSERT INTO `t_dict_data` VALUES (1454629564914470912, 'admin', '2021-10-30 21:01:29', 0, 'admin', '2021-10-30 21:01:29', '', 75388696739713024, 0.00, 0, '费率配置-添加', 'fee-rate-manager-add');
INSERT INTO `t_dict_data` VALUES (1454646323633590272, 'admin', '2021-10-30 22:08:04', 0, 'admin', '2021-10-30 22:08:04', '', 75388696739713024, 0.00, 0, '统筹单管理-统筹单', 'orderTongchou');
INSERT INTO `t_dict_data` VALUES (1454654603441344512, 'admin', '2021-10-30 22:40:58', 0, 'admin', '2021-10-30 22:40:58', '', 75388696739713024, 0.00, 0, '统筹单管理-审核修改', 'orderAuditEdit');
INSERT INTO `t_dict_data` VALUES (1454711536563130368, 'admin', '2021-10-31 02:27:12', 0, 'admin', '2021-10-31 02:27:12', '', 75388696739713024, 0.00, 0, '互助单管理-查看', 'help-order-manager-view');

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `cost_time` int(11) UNSIGNED NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_type` tinyint(1) NULL DEFAULT NULL,
  `data_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据标识，便于检索',
  `log_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `special_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `type` tinyint(1) NULL DEFAULT NULL,
  `sort_order` decimal(10, 2) NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) UNSIGNED NULL DEFAULT NULL,
  `button_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show_always` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (5129710648430592, '', '2018-06-04 19:02:29', 0, 'admin', '2021-10-24 07:28:55', '', 'sys', 125909152017944576, 0, 5.00, 'Main', '/sys', '系统管理', 'ios-settings', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (5129710648430593, '', '2018-06-04 19:02:32', 0, '', '2018-08-13 15:15:33', '', 'user-manage', 5129710648430592, 0, 1.10, 'sys/user-manage/userManage', 'user-manage', '用户管理', 'md-person', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (5129710648430594, '', '2018-06-04 19:02:35', 0, '', '2018-10-13 13:51:36', '', 'role-manage', 5129710648430592, 0, 1.60, 'sys/role-manage/roleManage', 'role-manage', '角色权限管理', 'md-contacts', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (5129710648430595, '', '2018-06-04 19:02:37', 0, '', '2018-09-23 23:32:02', '', 'menu-manage', 5129710648430592, 0, 1.70, 'sys/menu-manage/menuManage', 'menu-manage', '菜单权限管理', 'md-menu', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (15701400130424832, '', '2018-06-03 22:04:06', 0, '', '2018-09-19 22:16:44', '', '', 5129710648430593, 1, 1.11, '', '/xboot/user/admin/add*', '添加用户', '', 3, 'add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (15701915807518720, '', '2018-06-03 22:06:09', 0, '', '2018-06-06 14:46:51', '', '', 5129710648430593, 1, 1.13, '', '/xboot/user/admin/disable/**', '禁用用户', '', 3, 'disable', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (15708892205944832, '', '2018-06-03 22:33:52', 0, '', '2018-06-28 16:44:48', '', '', 5129710648430593, 1, 1.14, '', '/xboot/user/admin/enable/**', '启用用户', '', 3, 'enable', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (16392452747300864, '', '2018-06-05 19:50:06', 0, 'admin', '2021-10-29 01:01:58', '', 'access', 125909152017944576, 0, 100.00, 'Main', '/access', '权限按钮测试页', 'md-lock', 1, '', 0, '', b'0');
INSERT INTO `t_permission` VALUES (16392767785668608, '', '2018-06-05 19:51:21', 0, 'admin', '2018-10-23 12:34:38', '', 'access_index', 16392452747300864, 0, 5.10, 'access/access', 'index', '权限按钮测试页', 'md-lock', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16438800255291392, '', '2018-06-05 22:54:18', 0, 'admin', '2018-10-23 12:34:51', '', '', 16392767785668608, 1, 5.11, '', 'test-add', '添加按钮测试', '', 3, 'add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16438962738434048, '', '2018-06-05 22:54:55', 0, 'admin', '2018-10-23 12:35:05', '', '', 16392767785668608, 1, 5.12, '', 'test-edit', '编辑按钮测试', '', 3, 'edit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16439068543946752, '', '2018-06-05 22:55:20', 0, 'admin', '2018-10-23 12:34:54', '', '', 16392767785668608, 1, 5.13, '', 'test-delete', '删除按钮测试', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16678126574637056, '', '2018-06-06 14:45:16', 0, '', '2018-09-19 22:16:48', '', '', 5129710648430593, 1, 1.12, '', '/xboot/user/admin/edit*', '编辑用户', '', 3, 'edit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16678447719911424, '', '2018-06-06 14:46:32', 0, 'admin', '2020-04-28 14:27:03', '', '', 5129710648430593, 1, 1.15, '', '/xboot/user/delByIds**', '删除用户', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16687383932047360, '', '2018-06-06 15:22:03', 0, '', '2018-09-19 22:07:34', '', '', 5129710648430594, 1, 1.21, '', '/xboot/role/save*', '添加角色', '', 3, 'add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16689632049631232, '', '2018-06-06 15:30:59', 0, '', '2018-09-19 22:07:37', '', '', 5129710648430594, 1, 1.22, '', '/xboot/role/edit*', '编辑角色', '', 3, 'edit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16689745006432256, '', '2018-06-06 15:31:26', 0, 'admin', '2020-04-28 14:29:02', '', '', 5129710648430594, 1, 1.23, '', '/xboot/role/delByIds**', '删除角色', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16689883993083904, NULL, '2018-06-06 15:31:59', 0, NULL, '2018-06-06 15:31:59', NULL, NULL, 5129710648430594, 1, 1.24, NULL, '/xboot/role/editRolePerm**', '分配权限', NULL, 3, 'editPerm', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (16690313745666048, '', '2018-06-06 15:33:41', 0, '', '2018-09-19 22:07:46', '', '', 5129710648430594, 1, 1.25, '', '/xboot/role/setDefault*', '设为默认角色', '', 3, 'setDefault', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16694861252005888, '', '2018-06-06 15:51:46', 0, '', '2018-09-19 22:07:52', '', '', 5129710648430595, 1, 1.31, '', '/xboot/permission/add*', '添加菜单', '', 3, 'add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16695107491205120, '', '2018-06-06 15:52:44', 0, '', '2018-09-19 22:07:57', '', '', 5129710648430595, 1, 1.32, '', '/xboot/permission/edit*', '编辑菜单', '', 3, 'edit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (16695243126607872, '', '2018-06-06 15:53:17', 0, 'admin', '2020-04-28 14:29:17', '', '', 5129710648430595, 1, 1.33, '', '/xboot/permission/delByIds**', '删除菜单', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (25014528525733888, '', '2018-06-29 14:51:09', 0, '', '2018-10-08 11:13:27', '', '', 5129710648430593, 1, 1.16, '', '无', '上传图片', '', 3, 'upload', 0, '', b'1');
INSERT INTO `t_permission` VALUES (39915540965232640, '', '2018-08-09 17:42:28', 0, 'admin', '2021-10-24 07:29:02', '', 'monitor', 125909152017944576, 0, 6.00, 'Main', '/monitor', '系统监控', 'ios-analytics', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (39916171171991552, '', '2018-08-09 17:44:57', 0, 'admin', '2021-10-24 07:01:43', '', 'druid', 39915540965232640, 0, 2.40, 'sys/monitor/monitor', 'druid', 'SQL监控', 'md-analytics', 2, '', 0, 'http://admin.66hp.top/druid/login.html', b'1');
INSERT INTO `t_permission` VALUES (39918482854252544, '', '2018-08-09 17:54:08', 0, 'admin', '2021-10-24 07:02:12', '', 'swagger', 39915540965232640, 0, 2.50, 'sys/monitor/monitor', 'swagger', '接口文档', 'md-document', 2, '', 0, 'http://admin.66hp.top/swagger-ui.html', b'1');
INSERT INTO `t_permission` VALUES (40238597734928384, NULL, '2018-08-10 15:06:10', 0, NULL, '2018-08-10 15:06:10', NULL, 'department-manage', 5129710648430592, 0, 1.20, 'sys/department-manage/departmentManage', 'department-manage', '部门管理', 'md-git-branch', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (41363147411427328, '', '2018-08-13 17:34:43', 0, 'admin', '2020-03-25 20:31:16', '', 'log-manage', 39915540965232640, 0, 2.20, 'sys/log-manage/logManage', 'log-manage', '日志管理', 'md-list-box', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (41363537456533504, '', '2018-08-13 17:36:16', 0, 'admin', '2020-04-28 14:31:54', '', '', 41363147411427328, 1, 2.11, '', '/xboot/log/delByIds**', '删除日志', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (41364927394353152, '', '2018-08-13 17:41:48', 0, '', '2018-09-19 22:08:57', '', '', 41363147411427328, 1, 2.12, '', '/xboot/log/delAll*', '清空日志', '', 3, 'undefined', 0, '', b'1');
INSERT INTO `t_permission` VALUES (41370251991977984, NULL, '2018-08-13 18:02:57', 0, NULL, '2018-08-13 18:02:57', NULL, 'quartz-job', 39915540965232640, 0, 2.10, 'sys/quartz-manage/quartzManage', 'quartz-job', '定时任务', 'md-time', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (41371711400054784, '', '2018-08-13 18:08:45', 0, '', '2019-07-07 21:24:26', '', 'actuator', 39915540965232640, 0, 2.30, 'sys/actuator/actuator', 'actuator', 'Actuator监控', 'logo-angular', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (41373430515240960, '', '2018-08-13 18:15:35', 0, 'admin', '2021-10-24 07:29:26', '', 'vue-template', 125909152017944576, 0, 8.00, 'Main', '/vue-template', '后台Vue模版', 'ios-albums', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (41375330996326400, '', '2018-08-13 18:23:08', 0, 'admin', '2020-03-21 20:41:28', '', 'simple-table', 41373430515240960, 0, 3.10, 'xboot-vue-template/simple-table/simpleTable', 'simple-table', '简单表格+动态列', 'ios-grid-outline', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (41378916912336896, '', '2018-08-13 18:37:23', 0, 'admin', '2019-04-12 15:38:08', '', 'tree', 41373430515240960, 0, 3.22, 'xboot-vue-template/tree/tree', 'tree', '树形结构', 'ios-git-network', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (41469219249852416, NULL, '2018-08-14 00:36:13', 0, NULL, '2018-08-14 00:36:13', NULL, '', 41371711400054784, 1, 2.31, '', '无', '查看数据', '', 3, 'view', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (42082442672082944, '', '2018-08-15 17:12:57', 0, 'admin', '2019-04-12 15:48:50', '', 'new-window', 41373430515240960, 0, 3.21, 'xboot-vue-template/new-window/newWindow', 'new-window', '新窗口操作', 'ios-browsers', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45235621697949696, '', '2018-08-24 10:02:33', 0, '', '2018-09-19 22:06:57', '', '', 40238597734928384, 1, 1.21, '', '/xboot/department/add*', '添加部门', '', 3, 'add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45235787867885568, '', '2018-08-24 10:03:13', 0, '', '2018-09-19 22:07:02', '', '', 40238597734928384, 1, 1.22, '', '/xboot/department/edit*', '编辑部门', '', 3, 'edit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45235939278065664, '', '2018-08-24 10:03:49', 0, 'admin', '2020-04-28 14:27:35', '', '', 40238597734928384, 1, 1.23, '', '/xboot/department/delByIds*', '删除部门', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45264987354042368, '', '2018-08-24 11:59:14', 0, '', '2018-09-19 22:08:11', '', '', 41370251991977984, 1, 2.11, '', '/xboot/quartzJob/add*', '添加定时任务', '', 3, 'add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45265487029866496, '', '2018-08-24 12:01:14', 0, '', '2018-09-19 22:08:17', '', '', 41370251991977984, 1, 2.12, '', '/xboot/quartzJob/edit*', '编辑定时任务', '', 3, 'edit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45265762415284224, '', '2018-08-24 12:02:19', 0, '', '2018-09-19 22:08:24', '', '', 41370251991977984, 1, 2.13, '', '/xboot/quartzJob/pause*', '暂停定时任务', '', 3, 'disable', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45265886315024384, '', '2018-08-24 12:02:49', 0, '', '2018-09-19 22:09:13', '', '', 41370251991977984, 1, 2.14, '', '/xboot/quartzJob/resume*', '恢复定时任务', '', 3, 'enable', 0, '', b'1');
INSERT INTO `t_permission` VALUES (45266070000373760, '', '2018-08-24 12:03:33', 0, 'admin', '2020-04-28 14:31:48', '', '', 41370251991977984, 1, 2.15, '', '/xboot/quartzJob/delByIds*', '删除定时任务', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (56898976661639168, '', '2018-09-25 14:28:34', 0, '', '2018-09-25 15:12:46', '', '', 5129710648430593, 1, 1.17, '', '/xboot/user/importData*', '导入用户', '', 3, 'input', 0, '', b'1');
INSERT INTO `t_permission` VALUES (75002207560273920, 'admin', '2018-11-14 13:24:21', 0, 'admin', '2018-11-20 20:06:22', '', 'dict', 5129710648430592, 0, 1.80, 'sys/dict-manage/dictManage', 'dict', '数据字典管理', 'md-bookmarks', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (76215889006956544, 'admin', '2018-11-17 21:47:05', 0, 'admin', '2018-11-17 21:47:53', '', '', 75002207560273920, 1, 1.81, '', '/xboot/dict/add*', '添加字典', '', 3, 'add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (76216071333351424, 'admin', '2018-11-17 21:47:48', 0, 'admin', '2018-11-17 21:47:48', NULL, '', 75002207560273920, 1, 1.82, '', '/xboot/dict/edit*', '编辑字典', '', 3, 'edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (76216264070008832, 'admin', '2018-11-17 21:48:34', 0, 'admin', '2020-04-28 14:29:30', '', '', 75002207560273920, 1, 1.83, '', '/xboot/dict/delByIds**', '删除字典', '', 3, 'delete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (76216459709124608, 'admin', '2018-11-17 21:49:21', 0, 'admin', '2018-11-17 21:49:21', NULL, '', 75002207560273920, 1, 1.84, '', '/xboot/dictData/add*', '添加字典数据', '', 3, 'add', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (76216594207870976, 'admin', '2018-11-17 21:49:53', 0, 'admin', '2018-11-17 21:49:53', NULL, '', 75002207560273920, 1, 1.85, '', '/xboot/dictData/edit*', '编辑字典数据', '', 3, 'edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (76216702639017984, 'admin', '2018-11-17 21:50:18', 0, 'admin', '2018-11-17 21:50:18', NULL, '', 75002207560273920, 1, 1.86, '', '/xboot/dictData/delByIds/**', '删除字典数据', '', 3, 'delete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (113602149589454848, 'admin', '2019-03-01 01:46:44', 0, 'admin', '2021-10-24 07:29:35', '', 'components', 125909152017944576, 0, 9.00, 'Main', '/components', 'XBoot业务组件', 'md-cube', 1, '', 0, '', b'0');
INSERT INTO `t_permission` VALUES (113602342657462272, 'admin', '2019-03-01 01:47:30', 0, 'admin', '2019-03-01 02:03:26', '', 'components', 113602149589454848, 0, 0.00, 'xboot-components/xboot-components', 'components', 'XBoot业务组件', 'md-cube', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (113603512293658624, 'admin', '2019-03-01 01:52:09', 0, 'admin', '2021-10-29 01:02:36', '', 'library', 125909152017944576, 0, 99.00, 'Main', '/library', '第三方依赖工具/组件', 'ios-link', 1, '', 0, '', b'0');
INSERT INTO `t_permission` VALUES (113603617897844736, 'admin', '2019-03-01 01:52:34', 0, 'admin', '2019-03-01 02:03:15', '', 'library', 113603512293658624, 0, 0.00, 'xboot-library/xboot-library', 'library', '第三方依赖工具/组件', 'ios-link', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (121426317022334976, 'admin', '2019-03-22 15:57:11', 0, 'admin', '2019-03-22 15:57:11', NULL, 'redis', 39915540965232640, 0, 2.21, 'sys/redis/redis', 'redis', 'Redis缓存管理', 'md-barcode', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (125909152017944576, 'admin', '2019-04-04 00:50:22', 0, 'admin', '2021-09-30 08:52:48', '', 'insurance', 0, -1, 0.00, '', '', '统筹管理系统', 'md-home', 0, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (127996320085446656, 'admin', '2019-04-09 19:04:02', 0, 'admin', '2020-11-11 23:40:41', '', 'app', 0, -1, 1.00, '', '', '小程序管理', 'md-phone-portrait', 0, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (129033675403694080, 'admin', '2019-04-12 15:46:07', 0, 'admin', '2019-11-25 17:33:43', '', 'single-window', 41373430515240960, 0, 1.00, 'xboot-vue-template/single-window/singleWindow', 'single-window', '动态组件单页操作', 'md-easel', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (149452775095275520, 'admin', '2019-06-08 00:04:19', 0, 'admin', '2021-10-24 07:00:58', '', 'admin', 39915540965232640, 0, 2.29, 'sys/monitor/monitor', '/admin', 'Admin监控', 'md-speedometer', 2, '', 0, 'http://admin.66hp.top/xboot/admin', b'1');
INSERT INTO `t_permission` VALUES (156365156580855808, 'admin', '2019-06-27 01:51:39', 0, 'admin', '2019-06-27 01:51:39', NULL, '', 5129710648430593, 1, 1.18, '', '/xboot/user/resetPass', '重置密码', '', 3, 'other', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (175808753877127169, 'admin', '2019-08-19 17:33:34', 0, 'admin', '2021-10-29 01:02:21', '', 'sso', 125909152017944576, 0, 101.00, 'Main', '/sso', '单点登录测试站', 'md-log-in', 1, '', 0, '', b'0');
INSERT INTO `t_permission` VALUES (175808922710446081, 'admin', '2019-08-19 17:34:15', 0, 'admin', '2019-08-19 17:34:15', NULL, 'sso', 175808753877127169, 0, 0.00, 'sso/sso', 'sso', '单点登录测试站', 'md-log-in', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (211251162815401984, 'admin', '2019-11-25 12:49:03', 0, 'admin', '2021-11-08 05:41:49', '', 'open', 125909152017944576, 0, 7.00, 'Main', '/open', '开放平台', 'ios-apps', 1, '', -1, '', b'0');
INSERT INTO `t_permission` VALUES (211251678651879424, 'admin', '2019-11-25 12:51:06', 0, 'admin', '2019-11-25 12:51:06', NULL, 'client', 211251162815401984, 0, 0.00, 'open/client/client', 'client', '接入网站管理', 'md-browsers', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (211652331387752448, 'admin', '2019-11-26 15:23:09', 0, 'admin', '2019-11-26 15:23:09', NULL, 'doc', 211251162815401984, 0, 1.00, 'sys/monitor/monitor', 'doc', '开放平台文档', 'md-document', 2, '', 0, 'https://www.kancloud.cn/exrick/xboot/1399478', b'1');
INSERT INTO `t_permission` VALUES (1251760630449442816, 'admin', '2020-04-19 14:32:46', 0, 'admin', '2020-04-27 18:31:03', '', 'member-log', 127996320085446656, 0, 3.00, 'Main', '/member-log', '日志记录', 'md-list-box', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1251761480286736384, 'admin', '2020-04-19 14:36:08', 0, 'admin', '2020-04-27 18:31:22', '', 'member-log', 1251760630449442816, 0, 0.00, 'sys/log-manage/logManage', 'member-log', '日志记录[付费]', 'md-list-box', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1255336077691064320, 'admin', '2020-04-29 11:20:18', 0, 'admin', '2020-04-29 11:20:47', '', '', 121426317022334976, 1, 0.00, '', '/xboot/redis/getAllByPage**', '获取Redis', '', 3, 'view', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1255336361339260928, 'admin', '2020-04-29 11:21:26', 0, 'admin', '2020-04-29 11:21:26', NULL, '', 121426317022334976, 1, 0.00, '', '/xboot/redis/save', '添加Redis', '', 3, 'add', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1255336455472025601, 'admin', '2020-04-29 11:21:48', 0, 'admin', '2020-04-29 11:21:48', NULL, '', 121426317022334976, 1, 0.00, '', '/xboot/redis/delByKeys**', '删除Redis', '', 3, 'delete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1255336553971060737, 'admin', '2020-04-29 11:22:12', 0, 'admin', '2020-04-29 11:22:12', NULL, '', 121426317022334976, 1, 0.00, '', '/xboot/redis/delAll', '清空Redis', '', 3, 'clear', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1255337064858259457, 'admin', '2020-04-29 11:24:13', 0, 'admin', '2020-04-29 11:24:13', NULL, '', 211251678651879424, 1, 0.00, '', '/xboot/client/save**', '添加网站', '', 3, 'add', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1255337148748533761, 'admin', '2020-04-29 11:24:33', 0, 'admin', '2020-04-29 11:24:33', NULL, '', 211251678651879424, 1, 0.00, '', '/xboot/client/delByIds**', '删除网站', '', 3, 'undefined', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1255337241379737601, 'admin', '2020-04-29 11:24:56', 0, 'admin', '2020-04-29 11:24:56', NULL, '', 211251678651879424, 1, 0.00, '', '/xboot/client/update**', '编辑网站', '', 3, 'edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1443769249247137792, 'admin', '2021-09-30 21:46:28', 0, 'admin', '2021-10-24 07:27:10', '', 'fee-rate', 125909152017944576, 0, 0.00, 'Main', '/fee-rate', '费率配置', 'md-settings', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1443769802261925888, 'admin', '2021-09-30 21:48:39', 0, 'admin', '2021-09-30 21:50:40', '', 'fee-rate-manager', 1443769249247137792, 0, 0.00, 'fee-rate/fee-rate-manager/feeRateManager', 'fee-rate-manager', '费率配置', 'md-settings', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1444136624274411520, 'admin', '2021-10-01 22:06:17', 0, 'admin', '2021-10-24 07:27:38', '', 'client-info', 125909152017944576, 0, 2.00, 'Main', '/client-info', '客户信息', 'md-contacts', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1444137131906830336, 'admin', '2021-10-01 22:08:18', 0, 'admin', '2021-10-01 22:45:15', '', 'client-info-manager', 1444136624274411520, 0, 0.00, 'client-info/client-info-manager/clientInfoManager', 'client-info-manager', '信息维护', 'md-contacts', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1444137362270588928, 'admin', '2021-10-01 22:09:13', 0, 'admin', '2021-10-24 07:27:20', '', 'car-type-info', 125909152017944576, 0, 1.00, 'Main', '/car-type-info', '车型信息', 'md-car', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1444137681457123328, 'admin', '2021-10-01 22:10:29', 0, 'admin', '2021-10-01 22:10:29', NULL, 'car-type-info-manager', 1444137362270588928, 0, 0.00, 'car-type-info/car-type-info-manager/carTypeInfoManager', 'car-type-info-manager', '信息维护', 'md-car', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1444270825019674624, 'admin', '2021-10-02 06:59:33', 0, 'admin', '2021-10-02 06:59:33', NULL, 'car-type-info-depreciation', 1444137362270588928, 0, 0.00, 'car-type-info/car-type-info-depreciation/carTypeInfoDepreciation', 'car-type-info-depreciation', '月折旧率配置', 'md-remove', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1444296198969233408, 'admin', '2021-10-02 08:40:22', 0, 'admin', '2021-10-24 07:28:39', '', 'order', 125909152017944576, 0, 3.00, 'Main', '/order', '统筹单', 'md-reorder', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1444296435863523328, 'admin', '2021-10-02 08:41:19', 0, 'admin', '2021-10-02 08:41:19', NULL, 'order-manager', 1444296198969233408, 0, 0.00, 'order/order-manager/orderManager', 'order-manager', '统筹单管理', 'md-reorder', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1444308754169466880, 'admin', '2021-10-02 09:30:16', 0, 'admin', '2021-10-02 09:30:16', NULL, 'expande', 41373430515240960, 0, 0.00, 'xboot-vue-template/expande/expandedemo', 'expande', '折叠列表示例', 'md-expand', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1444678087412420608, 'admin', '2021-10-03 09:57:52', 0, 'admin', '2021-10-03 09:57:52', NULL, 'fee-channel-discount-rate', 1443769249247137792, 0, 0.00, 'fee-rate/fee-channel-discount-rate/feeChannelDiscountRate', 'fee-channel-discount-rate', '渠道折扣配置', 'ios-cog', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1444678793880014848, 'admin', '2021-10-03 10:00:40', 0, 'admin', '2021-10-03 10:00:40', NULL, 'fee-youhui-rate', 1443769249247137792, 0, 0.00, 'fee-rate/fee-youhui-rate/feeYouhuiRate', 'fee-youhui-rate', '费率优惠政策', 'md-cog', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1446401668643164160, 'admin', '2021-10-08 04:06:45', 0, 'admin', '2021-10-08 06:18:37', '', 'order-payinfo-manager', 1444296198969233408, 0, 0.00, 'order/order-pay-manager/orderPayinfoManager', 'order-payinfo-manager', '收款维护', 'ios-apps', 2, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1447064851829624832, 'admin', '2021-10-10 00:02:01', 0, 'admin', '2021-10-10 07:42:53', '', '', 1444296435863523328, 1, 0.00, '', '无', '添加', '', 3, 'orderAdd', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1447066215150391296, 'admin', '2021-10-10 00:07:26', 0, 'admin', '2021-10-10 07:55:51', '', '', 1444296435863523328, 1, 0.00, '', '/xboot/order/audit**', '审核通过/审核不通过', '', 3, 'orderCheck', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1447068983231647744, 'admin', '2021-10-10 00:18:26', 0, 'admin', '2021-10-10 07:42:30', '', '', 1444296435863523328, 1, 0.00, '', '/xboot/order/delByIds**', '删除', '', 3, 'orderDelete', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1447151555697577984, 'admin', '2021-10-10 05:46:32', 0, 'admin', '2021-10-10 07:45:26', '', '', 1444296435863523328, 1, 0.00, '', '/xboot/order/orderEdit**', '提交并保存', '', 3, 'orderCommitAndSave', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1447152140454858752, 'admin', '2021-10-10 05:48:52', 0, 'admin', '2021-10-10 07:42:10', '', '', 1444296435863523328, 1, 0.00, '', '/xboot/order/baojia**', '报价单', '', 3, 'orderBaojia', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1447152316980531200, 'admin', '2021-10-10 05:49:34', 0, 'admin', '2021-10-10 07:41:51', '', '', 1444296435863523328, 1, 0.00, '', '/xboot/order/commitForAudit**', '提交审核', '', 3, 'orderCommitForAudit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1447181007752138752, 'admin', '2021-10-10 07:43:34', 0, 'admin', '2021-10-10 07:43:34', NULL, '', 1444296435863523328, 1, 0.00, '', '/xboot/order/orderCreate**', '暂存保存', '', 3, 'orderZancun', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1447181330298310656, 'admin', '2021-10-10 07:44:51', 0, 'admin', '2021-10-10 07:44:51', NULL, '', 1444296435863523328, 1, 0.00, '', '无', '编辑', '', 3, 'orderEdit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1448248411861159936, 'admin', '2021-10-13 06:25:04', 0, 'admin', '2021-10-24 07:28:48', '', 'help-order', 125909152017944576, 0, 4.00, 'Main', '/help-order', '互助补偿管理', 'md-help-buoy', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1448248710399135744, 'admin', '2021-10-13 06:26:14', 0, 'admin', '2021-10-13 06:26:14', NULL, 'help-order-manager', 1448248411861159936, 0, 0.00, 'help-order/help-order-manager/helpOrderManager', 'help-order-manager', '互助补偿管理', 'md-help-buoy', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451019189228802048, 'admin', '2021-10-20 21:55:08', 0, 'admin', '2021-10-20 21:55:08', NULL, '', 1448248710399135744, 1, 0.00, '', '无', '互助补偿管理-案件受理', '', 3, 'helpOrderCommit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451019630054346752, 'admin', '2021-10-20 21:56:53', 0, 'admin', '2021-10-20 21:56:53', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrder/delByIds**', '互助补偿管理-删除', '', 3, 'helpOrderDelete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451019756093181952, 'admin', '2021-10-20 21:57:23', 0, 'admin', '2021-10-20 21:57:23', NULL, '', 1448248710399135744, 1, 0.00, '', '无', '互助补偿管理-编辑', '', 3, 'helperOrderEdit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451019858568417280, 'admin', '2021-10-20 21:57:48', 0, 'admin', '2021-10-20 21:57:48', NULL, '', 1448248710399135744, 1, 0.00, '', '无', '互助补偿管理-调查', '', 3, 'helpOrderDiaocha', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451020004916072448, 'admin', '2021-10-20 21:58:23', 0, 'admin', '2021-10-20 21:58:23', NULL, '', 1448248710399135744, 1, 0.00, '', '无', '互助补偿管理-审核', '', 3, 'helpOrderCheck', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451020290237796352, 'admin', '2021-10-20 21:59:31', 0, 'admin', '2021-10-20 21:59:31', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrder/submitStartDiaocha**', '互助补偿管理-调查派工', '', 3, 'helpOrderPaigong', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451020615896141824, 'admin', '2021-10-20 22:00:48', 0, 'admin', '2021-10-20 22:00:48', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrder/update**', '互助补偿管理-编辑-提交并保存', '', 3, 'helpOrderEditCommit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451020913016442880, 'admin', '2021-10-20 22:01:59', 0, 'admin', '2021-10-20 22:01:59', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperDamage/save**', '互助补偿管理-调查-损失列表-添加子数据', '', 3, 'helpOrderDiaochaAddchild', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451021914112921600, 'admin', '2021-10-20 22:05:58', 0, 'admin', '2021-10-20 22:05:58', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperDamage/update**', '互助补偿管理-损失列表-修改并保存', '', 3, 'helpOrderDamagelistCommit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451022072720527360, 'admin', '2021-10-20 22:06:36', 0, 'admin', '2021-10-25 08:22:29', '', '', 1448248710399135744, 1, 0.00, '', '/xboot/helperDamageDetail/update**', '互助补偿管理-损失列表-修改并保存2', '', 3, 'helpOrderDamagelistCommitSecound', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1451022340262596608, 'admin', '2021-10-20 22:07:39', 0, 'admin', '2021-10-20 22:07:39', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrderCompensate/update**', '互助补偿管理-其他信息-修改并保存', '', 3, 'helpOrderOtherMsgCommit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451022516347867136, 'admin', '2021-10-20 22:08:21', 0, 'admin', '2021-10-20 22:08:21', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrderPic/saveHelpOrderPics**', '互助补偿管理-文件-修改并保存', '', 3, 'helpOrderFileCommit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451022920666189824, 'admin', '2021-10-20 22:09:58', 0, 'admin', '2021-10-20 22:09:58', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrder/commitForCheck**', '互助补偿管理-提交审核', '', 3, 'helpOrderCommitForCheck', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451023141370466304, 'admin', '2021-10-20 22:10:50', 0, 'admin', '2021-10-20 22:10:50', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrder/checkSuccess**', '互助补偿管理-审核通过', '', 3, 'helpOrderCheckSuccess', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1451023290524110848, 'admin', '2021-10-20 22:11:26', 0, 'admin', '2021-10-20 22:11:26', NULL, '', 1448248710399135744, 1, 0.00, '', '/xboot/helperOrder/checkFail', '互助补偿管理-退回调查', '', 3, 'helpOrderCheckFail', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1452815090377166848, 'admin', '2021-10-25 20:51:24', 0, 'admin', '2021-10-25 20:51:24', NULL, 'order-edit-manager', 1444296198969233408, 0, 0.00, 'order/order-edit-manager/orderEditManager', 'order-edit-manager', '批单管理', 'md-list-box', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1453963392942477312, 'admin', '2021-10-29 00:54:21', 0, 'admin', '2021-10-29 01:01:29', '', 'statistic', 125909152017944576, 0, 4.10, 'Main', '/statistic', '统计', 'md-attach', 1, '', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1453963696563949568, 'admin', '2021-10-29 00:55:33', 0, 'admin', '2021-10-29 00:55:33', NULL, 'order-statistic', 1453963392942477312, 0, 0.00, 'statistic/order-statistic/order-statistic', 'order-statistic', '收款情况统计', 'md-attach', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1453964029059010560, 'admin', '2021-10-29 00:56:53', 0, 'admin', '2021-10-29 00:56:53', NULL, 'bussiness-statistic', 1453963392942477312, 0, 0.00, 'statistic/bussiness-statistic/bussiness-statistic', 'bussiness-statistic', '成交业务统计', 'md-attach', 2, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454363703171485696, 'admin', '2021-10-30 03:25:02', 0, 'admin', '2021-10-30 03:25:02', NULL, '', 1443769802261925888, 1, 0.00, '', '/xboot/rateConfig/getByCondition**', '费率配置-搜索/刷新', '', 3, 'fee-rate-manager-search', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454627532761600000, 'admin', '2021-10-30 20:53:24', 0, 'admin', '2021-10-30 20:53:24', NULL, '', 1443769802261925888, 1, 0.00, '', '/xboot/rateConfig/delByIds**', '费率配置-删除', '', 3, 'fee-rate-manager-delete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454627710159687680, 'admin', '2021-10-30 20:54:07', 0, 'admin', '2021-10-30 20:54:07', NULL, '', 1443769802261925888, 1, 0.00, '', '无', '费率配置-编辑', '', 3, 'fee-rate-manager-edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454628297693597696, 'admin', '2021-10-30 20:56:27', 0, 'admin', '2021-10-30 20:56:27', NULL, '', 1443769802261925888, 1, 0.00, '', '/xboot/rateConfig/save**', '费率配置-添加-提交', '', 3, 'fee-rate-manager-edit-commit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454628535284142080, 'admin', '2021-10-30 20:57:23', 0, 'admin', '2021-10-30 20:57:23', NULL, '', 1443769802261925888, 1, 0.00, '', '/xboot/rateConfig/update**', '费率配置-编辑-提交', '', 3, 'undefined', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454628913920741376, 'admin', '2021-10-30 20:58:54', 0, 'admin', '2021-10-30 20:58:54', NULL, '', 1444678793880014848, 1, 0.00, '', '/xboot/youhui/getByCondition**', '费率优惠政策-搜索/刷新', '', 3, 'fee-youhui-rate-search', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454629089729187840, 'admin', '2021-10-30 20:59:36', 0, 'admin', '2021-10-30 20:59:36', NULL, '', 1444678793880014848, 1, 0.00, '', '无', '费率优惠政策-添加', '', 3, 'fee-youhui-rate-add', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454629856443764736, 'admin', '2021-10-30 21:02:38', 0, 'admin', '2021-10-30 21:02:38', NULL, '', 1443769802261925888, 1, 0.00, '', '无', '费率配置-添加', '', 3, 'fee-rate-manager-add', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454630234061148160, 'admin', '2021-10-30 21:04:08', 0, 'admin', '2021-10-30 21:04:08', NULL, '', 1444678793880014848, 1, 0.00, '', '/xboot/youhui/delByIds**', '费率优惠政策-删除', '', 3, 'fee-youhui-rate-delete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454630423849209856, 'admin', '2021-10-30 21:04:54', 0, 'admin', '2021-10-30 21:04:54', NULL, '', 1444678793880014848, 1, 0.00, '', '无', '费率优惠政策-编辑', '', 3, 'fee-youhui-rate-edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454630674567925760, 'admin', '2021-10-30 21:05:53', 0, 'admin', '2021-10-30 21:05:53', NULL, '', 1444678793880014848, 1, 0.00, '', '/xboot/youhui/save**', '费率优惠政策-添加-提交', '', 3, 'fee-youhui-rate-edit-commit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454631659881238528, 'admin', '2021-10-30 21:09:48', 0, 'admin', '2021-10-30 21:09:48', NULL, '', 1444678793880014848, 1, 0.00, '', '/xboot/youhui/update**', '费率优惠政策-编辑-提交', '', 3, '', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454631893315227648, 'admin', '2021-10-30 21:10:44', 0, 'admin', '2021-10-30 21:10:44', NULL, '', 1444678087412420608, 1, 0.00, '', '/xboot/channelDiscount/getByCondition**', '渠道折扣-刷新/搜索', '', 3, 'fee-channel-discount-rate-search', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454632063218094080, 'admin', '2021-10-30 21:11:24', 0, 'admin', '2021-10-30 21:11:24', NULL, '', 1444678087412420608, 1, 0.00, '', '/xboot/channelDiscount/save**', '渠道折扣配置-修改并保存', '', 3, 'fee-channel-discount-rate-edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454632262917296128, 'admin', '2021-10-30 21:12:12', 0, 'admin', '2021-10-30 21:12:12', NULL, '', 1444137681457123328, 1, 0.00, '', '/xboot/carType/getByCondition**', '车型信息维护-搜索刷新', '', 3, 'car-type-info-manager-search', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454632418962182144, 'admin', '2021-10-30 21:12:49', 0, 'admin', '2021-10-30 21:13:20', '', '', 1444137681457123328, 1, 0.00, '', '无', '车型信息维护-添加', '', 3, 'car-type-info-manager-add', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1454632621773557760, 'admin', '2021-10-30 21:13:38', 0, 'admin', '2021-10-30 21:13:38', NULL, '', 1444137681457123328, 1, 0.00, '', '无', '车型信息维护-编辑', '', 3, 'car-type-info-manager-edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454632756003868672, 'admin', '2021-10-30 21:14:10', 0, 'admin', '2021-10-30 21:14:10', NULL, '', 1444137681457123328, 1, 0.00, '', '/xboot/carType/delByIds**', '车型信息维护-删除', '', 3, 'car-type-info-manager-delete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454632981179273216, 'admin', '2021-10-30 21:15:03', 0, 'admin', '2021-10-30 21:15:03', NULL, '', 1444137681457123328, 1, 0.00, '', '/xboot/carType/save**', '车型信息维护-添加-提交', '', 3, 'car-type-info-manager-add-commit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454633203246698496, 'admin', '2021-10-30 21:15:56', 0, 'admin', '2021-10-30 21:15:56', NULL, '', 1444137681457123328, 1, 0.00, '', '/xboot/carType/update**', '车型信息维护-编辑-提交', '', 3, 'car-type-info-manager-add-commit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454633398231502848, 'admin', '2021-10-30 21:16:43', 0, 'admin', '2021-10-30 21:16:43', NULL, '', 1444270825019674624, 1, 0.00, '', '/xboot/carDepreciation/getByCondition**', '车型月折扣配置-搜索/刷新', '', 3, 'car-type-info-depreciation-search', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454633502313156608, 'admin', '2021-10-30 21:17:08', 0, 'admin', '2021-10-30 21:17:08', NULL, '', 1444270825019674624, 1, 0.00, '', '无', '车型月折旧配置-添加', '', 3, 'car-type-info-depreciation-add', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454633694882041856, 'admin', '2021-10-30 21:17:53', 0, 'admin', '2021-10-30 21:17:53', NULL, '', 1444270825019674624, 1, 0.00, '', '无', '车型月折旧配置-编辑', '', 3, 'car-type-info-depreciation-edit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454633840684437504, 'admin', '2021-10-30 21:18:28', 0, 'admin', '2021-10-30 21:18:28', NULL, '', 1444270825019674624, 1, 0.00, '', '/xboot/carDepreciation/delByIds**', '车型月折旧配置-删除', '', 3, 'car-type-info-depreciation-delete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454633979390070784, 'admin', '2021-10-30 21:19:01', 0, 'admin', '2021-10-30 21:19:01', NULL, '', 1444270825019674624, 1, 0.00, '', '/xboot/carDepreciation/save**', '车型月折旧配置-添加-提交', '', 3, 'car-type-info-depreciation-add-commit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454634137230118912, 'admin', '2021-10-30 21:19:39', 0, 'admin', '2021-10-30 21:19:39', NULL, '', 1444270825019674624, 1, 0.00, '', '/xboot/carDepreciation/update**', '车型月折旧配置-编辑-提交', '', 3, 'undefined', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454634407905333248, 'admin', '2021-10-30 21:20:43', 0, 'admin', '2021-10-30 21:20:43', NULL, '', 1452815090377166848, 1, 0.00, '', '/xboot/order/getEditOrderByCondition**', '批单管理-刷新/搜索', '', 3, 'order-edit-manager-search', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454634532320972800, 'admin', '2021-10-30 21:21:13', 0, 'admin', '2021-10-30 21:21:13', NULL, '', 1452815090377166848, 1, 0.00, '', '无', '批单管理-添加', '', 3, 'order-edit-manager-add', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454634756141617152, 'admin', '2021-10-30 21:22:06', 0, 'admin', '2021-10-30 21:22:06', NULL, '', 1452815090377166848, 1, 0.00, '', '/xboot/order/editOrderCommitForAudit**', '批单管理-提交审核', '', 3, 'order-edit-manager-commitForAudit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454634947762589696, 'admin', '2021-10-30 21:22:52', 0, 'admin', '2021-10-31 03:11:15', '', '', 1452815090377166848, 1, 0.00, '', '/xboot/order/orderEditEdit**', '批单管理-修改并保存', '', 3, 'order-edit-manager-edit-commit', 0, '', b'1');
INSERT INTO `t_permission` VALUES (1454635122975444992, 'admin', '2021-10-30 21:23:34', 0, 'admin', '2021-10-30 21:23:34', NULL, '', 1452815090377166848, 1, 0.00, '', '/xboot/order/orderEditAudit**', '批单管理-审核通过/不通过', '', 3, 'order-edit-manager-auditresult', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454635256807297024, 'admin', '2021-10-30 21:24:06', 0, 'admin', '2021-10-30 21:24:06', NULL, '', 1452815090377166848, 1, 0.00, '', '无', '批单管理-查看', '', 3, 'order-edit-manager-view', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454635577059184640, 'admin', '2021-10-30 21:25:22', 0, 'admin', '2021-10-30 21:25:22', NULL, '', 1452815090377166848, 1, 0.00, '', '无', '批单管理-删除(接口权限同统筹单)', '', 3, 'order-edit-manager-delete', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454635721364213760, 'admin', '2021-10-30 21:25:57', 0, 'admin', '2021-10-30 21:25:57', NULL, '', 1452815090377166848, 1, 0.00, '', '无', '批单管理-操作记录', '', 3, 'order-edit-manager-actionlog', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454635916193828864, 'admin', '2021-10-30 21:26:43', 0, 'admin', '2021-10-30 21:26:43', NULL, '', 1446401668643164160, 1, 0.00, '', '/xboot/orderPayInfo/getByCondition**', '收款维护-刷新/搜索', '', 3, 'order-payinfo-manager-search', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454636019084300288, 'admin', '2021-10-30 21:27:08', 0, 'admin', '2021-10-30 21:27:08', NULL, '', 1446401668643164160, 1, 0.00, '', '无', '收款维护-查看', '', 3, 'order-payinfo-manager-view', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454636107953213440, 'admin', '2021-10-30 21:27:29', 0, 'admin', '2021-10-30 21:27:29', NULL, '', 1446401668643164160, 1, 0.00, '', '无', '收款维护-收款', '', 3, 'order-payinfo-manager-recieve', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454636255160700928, 'admin', '2021-10-30 21:28:04', 0, 'admin', '2021-10-30 21:28:04', NULL, '', 1446401668643164160, 1, 0.00, '', '/xboot/orderPayInfo/paySuccess', '收款维护-收款-提交', '', 3, 'order-payinfo-manager-recieve-commit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454646859950854144, 'admin', '2021-10-30 22:10:12', 0, 'admin', '2021-10-30 22:10:12', NULL, '', 1444296435863523328, 1, 0.00, '', '/xboot/order/insuranceCert**', '统筹单管理-统筹单', '', 3, 'orderTongchou', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454654820911812608, 'admin', '2021-10-30 22:41:50', 0, 'admin', '2021-10-30 22:41:50', NULL, '', 1444296435863523328, 1, 0.00, '', '/xboot/order/orderAuditEdit', '统筹单管理-审核修改', '', 3, 'orderAuditEdit', 0, NULL, b'1');
INSERT INTO `t_permission` VALUES (1454711652535635968, 'admin', '2021-10-31 02:27:40', 0, 'admin', '2021-10-31 02:27:40', NULL, '', 1448248710399135744, 1, 0.00, '', '无', '互助单管理-查看', '', 3, 'help-order-manager-view', 0, NULL, b'1');

-- ----------------------------
-- Table structure for t_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `t_quartz_job`;
CREATE TABLE `t_quartz_job`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_quartz_job
-- ----------------------------
INSERT INTO `t_quartz_job` VALUES (1452504854361673728, 'admin', '2021-10-25 00:18:39', 0, 'admin', '2021-10-25 20:15:38', '0 0/1 * 1/1 * ?', '每五分钟执行一次统筹单失效任务', 'cn.exrick.xboot.your.jobs.OrderValidJob', 'null', -1);
INSERT INTO `t_quartz_job` VALUES (1452507248638169088, 'admin', '2021-10-25 00:28:10', 0, 'admin', '2021-10-25 20:15:35', '0 0/1 * 1/1 * ?', '', 'cn.exrick.xboot.quartz.jobs.SampleJob', 'null', -1);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `default_role` bit(1) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_type` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (496138616573952, '', '2018-04-22 23:03:49', 'admin', '2018-11-15 23:02:59', 'ROLE_ADMIN', 0, NULL, '超级管理员 拥有所有权限', 0);
INSERT INTO `t_role` VALUES (496138616573953, '', '2018-05-02 21:40:03', 'admin', '2021-10-10 08:20:10', 'ROLE_FIANCE', 0, b'0', '财务', 0);
INSERT INTO `t_role` VALUES (16457350655250432, '', '2018-06-06 00:08:00', 'admin', '2021-09-30 08:51:07', 'ROLE_WRITE', 0, NULL, '录单员', 1);
INSERT INTO `t_role` VALUES (1443036951866380288, 'admin', '2021-09-28 21:16:34', 'admin', '2021-11-02 02:00:38', '总部超级管理员', 0, NULL, '总部账号', 2);
INSERT INTO `t_role` VALUES (1447039685196124160, 'admin', '2021-10-09 22:22:00', 'admin', '2021-10-10 08:06:41', 'ROLE_CHECK', 0, NULL, '统筹单审核员', 0);
INSERT INTO `t_role` VALUES (1449357433590910976, 'admin', '2021-10-16 07:51:55', 'admin', '2021-10-16 07:51:55', 'ROLE_HELP_ORDER_CHECK', 0, NULL, '事故调查员', 0);
INSERT INTO `t_role` VALUES (1451026973898969088, 'admin', '2021-10-20 22:26:04', 'admin', '2021-10-20 22:26:04', 'ROLE_ACCIDENT_RECORD', 0, NULL, '事故登记员', 0);
INSERT INTO `t_role` VALUES (1451027532412489728, 'admin', '2021-10-20 22:28:17', 'admin', '2021-10-20 22:28:17', 'ROLE_ACCIDENT_SHENHE', 0, NULL, '互助单审核员', 0);
INSERT INTO `t_role` VALUES (1454637034835677184, 'admin', '2021-10-30 21:31:10', 'admin', '2021-10-30 21:31:10', 'ROLE_ORDER_EDIT_WRITE', 0, NULL, '批单录入', 0);
INSERT INTO `t_role` VALUES (1454637350578688000, 'admin', '2021-10-30 21:32:25', 'admin', '2021-10-30 21:32:25', 'ROLE_ORDER_EDIT_CHECK', 0, NULL, '批单审核', 0);

-- ----------------------------
-- Table structure for t_role_department
-- ----------------------------
DROP TABLE IF EXISTS `t_role_department`;
CREATE TABLE `t_role_department`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `department_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_department
-- ----------------------------
INSERT INTO `t_role_department` VALUES (70763874256687105, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322777781112832, 16457350655250432);
INSERT INTO `t_role_department` VALUES (70763874265075712, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322811096469504, 16457350655250432);
INSERT INTO `t_role_department` VALUES (70763874277658624, 'admin', '2018-11-02 20:42:43', 0, 'admin', '2018-11-02 20:42:43', 40322852833988608, 16457350655250432);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `permission_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1454638903708487680, 'admin', '2021-10-30 21:38:35', 0, 'admin', '2021-10-30 21:38:35', 125909152017944576, 496138616573953);
INSERT INTO `t_role_permission` VALUES (1454638903708487681, 'admin', '2021-10-30 21:38:35', 0, 'admin', '2021-10-30 21:38:35', 1444296198969233408, 496138616573953);
INSERT INTO `t_role_permission` VALUES (1454638903708487682, 'admin', '2021-10-30 21:38:35', 0, 'admin', '2021-10-30 21:38:35', 1446401668643164160, 496138616573953);
INSERT INTO `t_role_permission` VALUES (1454638903708487683, 'admin', '2021-10-30 21:38:35', 0, 'admin', '2021-10-30 21:38:35', 1454636107953213440, 496138616573953);
INSERT INTO `t_role_permission` VALUES (1454638903708487684, 'admin', '2021-10-30 21:38:35', 0, 'admin', '2021-10-30 21:38:35', 1454636019084300288, 496138616573953);
INSERT INTO `t_role_permission` VALUES (1454638903708487685, 'admin', '2021-10-30 21:38:35', 0, 'admin', '2021-10-30 21:38:35', 1454636255160700928, 496138616573953);
INSERT INTO `t_role_permission` VALUES (1454638903708487686, 'admin', '2021-10-30 21:38:35', 0, 'admin', '2021-10-30 21:38:35', 1454635916193828864, 496138616573953);
INSERT INTO `t_role_permission` VALUES (1454639236530704384, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 125909152017944576, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704385, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1454363703171485696, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704386, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1454628913920741376, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704387, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1454632262917296128, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704388, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1454633398231502848, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704389, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1444296198969233408, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704390, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1444296435863523328, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704391, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1447181330298310656, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704392, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1447064851829624832, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704393, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1447151555697577984, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704394, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1447152316980531200, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704395, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1447152140454858752, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454639236530704396, 'admin', '2021-10-30 21:39:55', 0, 'admin', '2021-10-30 21:39:55', 1447181007752138752, 16457350655250432);
INSERT INTO `t_role_permission` VALUES (1454640779556425728, 'admin', '2021-10-30 21:46:02', 0, 'admin', '2021-10-30 21:46:02', 125909152017944576, 1451026973898969088);
INSERT INTO `t_role_permission` VALUES (1454640779556425729, 'admin', '2021-10-30 21:46:03', 0, 'admin', '2021-10-30 21:46:03', 1448248411861159936, 1451026973898969088);
INSERT INTO `t_role_permission` VALUES (1454640779556425730, 'admin', '2021-10-30 21:46:03', 0, 'admin', '2021-10-30 21:46:03', 1448248710399135744, 1451026973898969088);
INSERT INTO `t_role_permission` VALUES (1454640779556425731, 'admin', '2021-10-30 21:46:03', 0, 'admin', '2021-10-30 21:46:03', 1451019756093181952, 1451026973898969088);
INSERT INTO `t_role_permission` VALUES (1454640779556425732, 'admin', '2021-10-30 21:46:03', 0, 'admin', '2021-10-30 21:46:03', 1451019189228802048, 1451026973898969088);
INSERT INTO `t_role_permission` VALUES (1454640779556425733, 'admin', '2021-10-30 21:46:03', 0, 'admin', '2021-10-30 21:46:03', 1451020615896141824, 1451026973898969088);
INSERT INTO `t_role_permission` VALUES (1454640779556425734, 'admin', '2021-10-30 21:46:03', 0, 'admin', '2021-10-30 21:46:03', 1451020290237796352, 1451026973898969088);
INSERT INTO `t_role_permission` VALUES (1454655479849553920, 'admin', '2021-10-30 22:44:27', 0, 'admin', '2021-10-30 22:44:27', 125909152017944576, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553921, 'admin', '2021-10-30 22:44:27', 0, 'admin', '2021-10-30 22:44:27', 1454363703171485696, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553922, 'admin', '2021-10-30 22:44:27', 0, 'admin', '2021-10-30 22:44:27', 1454628913920741376, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553923, 'admin', '2021-10-30 22:44:28', 0, 'admin', '2021-10-30 22:44:28', 1454632262917296128, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553924, 'admin', '2021-10-30 22:44:28', 0, 'admin', '2021-10-30 22:44:28', 1454633398231502848, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553925, 'admin', '2021-10-30 22:44:28', 0, 'admin', '2021-10-30 22:44:28', 1444296198969233408, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553926, 'admin', '2021-10-30 22:44:28', 0, 'admin', '2021-10-30 22:44:28', 1444296435863523328, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553927, 'admin', '2021-10-30 22:44:28', 0, 'admin', '2021-10-30 22:44:28', 1447181330298310656, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553928, 'admin', '2021-10-30 22:44:28', 0, 'admin', '2021-10-30 22:44:28', 1454654820911812608, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454655479849553929, 'admin', '2021-10-30 22:44:28', 0, 'admin', '2021-10-30 22:44:28', 1447066215150391296, 1447039685196124160);
INSERT INTO `t_role_permission` VALUES (1454711806760194048, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 125909152017944576, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194049, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1448248411861159936, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194050, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1448248710399135744, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194051, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451020913016442880, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194052, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451021914112921600, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194053, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1454711652535635968, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194054, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451022516347867136, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194055, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451020004916072448, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194056, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451020615896141824, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194057, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451023141370466304, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194058, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451022340262596608, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194059, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451023290524110848, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454711806760194060, 'admin', '2021-10-31 02:28:17', 0, 'admin', '2021-10-31 02:28:17', 1451022072720527360, 1451027532412489728);
INSERT INTO `t_role_permission` VALUES (1454712022670381056, 'admin', '2021-10-31 02:29:08', 0, 'admin', '2021-10-31 02:29:08', 125909152017944576, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381057, 'admin', '2021-10-31 02:29:08', 0, 'admin', '2021-10-31 02:29:08', 1448248411861159936, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381058, 'admin', '2021-10-31 02:29:08', 0, 'admin', '2021-10-31 02:29:08', 1448248710399135744, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381059, 'admin', '2021-10-31 02:29:08', 0, 'admin', '2021-10-31 02:29:08', 1451020913016442880, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381060, 'admin', '2021-10-31 02:29:08', 0, 'admin', '2021-10-31 02:29:08', 1451021914112921600, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381061, 'admin', '2021-10-31 02:29:08', 0, 'admin', '2021-10-31 02:29:08', 1454711652535635968, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381062, 'admin', '2021-10-31 02:29:08', 0, 'admin', '2021-10-31 02:29:08', 1451022516347867136, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381063, 'admin', '2021-10-31 02:29:09', 0, 'admin', '2021-10-31 02:29:09', 1451019858568417280, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381064, 'admin', '2021-10-31 02:29:09', 0, 'admin', '2021-10-31 02:29:09', 1451022920666189824, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381065, 'admin', '2021-10-31 02:29:09', 0, 'admin', '2021-10-31 02:29:09', 1451022340262596608, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454712022670381066, 'admin', '2021-10-31 02:29:09', 0, 'admin', '2021-10-31 02:29:09', 1451022072720527360, 1449357433590910976);
INSERT INTO `t_role_permission` VALUES (1454719597604376576, 'admin', '2021-10-31 02:59:14', 0, 'admin', '2021-10-31 02:59:14', 125909152017944576, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376577, 'admin', '2021-10-31 02:59:14', 0, 'admin', '2021-10-31 02:59:14', 1454363703171485696, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376578, 'admin', '2021-10-31 02:59:14', 0, 'admin', '2021-10-31 02:59:14', 1454628913920741376, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376579, 'admin', '2021-10-31 02:59:14', 0, 'admin', '2021-10-31 02:59:14', 1454632262917296128, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376580, 'admin', '2021-10-31 02:59:14', 0, 'admin', '2021-10-31 02:59:14', 1454633398231502848, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376581, 'admin', '2021-10-31 02:59:14', 0, 'admin', '2021-10-31 02:59:14', 1444296198969233408, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376582, 'admin', '2021-10-31 02:59:14', 0, 'admin', '2021-10-31 02:59:14', 1452815090377166848, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376583, 'admin', '2021-10-31 02:59:15', 0, 'admin', '2021-10-31 02:59:15', 1454634532320972800, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376584, 'admin', '2021-10-31 02:59:15', 0, 'admin', '2021-10-31 02:59:15', 1454634407905333248, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376585, 'admin', '2021-10-31 02:59:15', 0, 'admin', '2021-10-31 02:59:15', 1454635721364213760, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376586, 'admin', '2021-10-31 02:59:15', 0, 'admin', '2021-10-31 02:59:15', 1454635256807297024, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454719597604376587, 'admin', '2021-10-31 02:59:15', 0, 'admin', '2021-10-31 02:59:15', 1454634756141617152, 1454637034835677184);
INSERT INTO `t_role_permission` VALUES (1454721633251102720, 'admin', '2021-10-31 03:07:19', 0, 'admin', '2021-10-31 03:07:19', 125909152017944576, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1454721633251102721, 'admin', '2021-10-31 03:07:20', 0, 'admin', '2021-10-31 03:07:20', 1454632262917296128, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1454721633251102722, 'admin', '2021-10-31 03:07:20', 0, 'admin', '2021-10-31 03:07:20', 1444296198969233408, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1454721633251102723, 'admin', '2021-10-31 03:07:20', 0, 'admin', '2021-10-31 03:07:20', 1452815090377166848, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1454721633251102724, 'admin', '2021-10-31 03:07:20', 0, 'admin', '2021-10-31 03:07:20', 1454634407905333248, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1454721633251102725, 'admin', '2021-10-31 03:07:20', 0, 'admin', '2021-10-31 03:07:20', 1454635721364213760, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1454721633251102726, 'admin', '2021-10-31 03:07:20', 0, 'admin', '2021-10-31 03:07:20', 1454635256807297024, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1454721633251102727, 'admin', '2021-10-31 03:07:20', 0, 'admin', '2021-10-31 03:07:20', 1454635122975444992, 1454637350578688000);
INSERT INTO `t_role_permission` VALUES (1456098986187427840, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 125909152017944576, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427841, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1443769249247137792, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427842, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1444678087412420608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427843, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1454631893315227648, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427844, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1454632063218094080, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427845, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1443769802261925888, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427846, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1454629856443764736, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427847, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1454628297693597696, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427848, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1454627710159687680, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427849, 'admin', '2021-11-03 22:20:26', 0, 'admin', '2021-11-03 22:20:26', 1454363703171485696, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427850, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454628535284142080, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427851, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454627532761600000, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427852, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1444678793880014848, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427853, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454630674567925760, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427854, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454630423849209856, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427855, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454629089729187840, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427856, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454628913920741376, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427857, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454631659881238528, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427858, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454630234061148160, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427859, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1444137362270588928, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427860, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1444137681457123328, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427861, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454632262917296128, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427862, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454632418962182144, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427863, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454633203246698496, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427864, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454632756003868672, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427865, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454632621773557760, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427866, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454632981179273216, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427867, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1444270825019674624, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427868, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454633398231502848, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427869, 'admin', '2021-11-03 22:20:27', 0, 'admin', '2021-11-03 22:20:27', 1454633840684437504, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427870, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454634137230118912, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427871, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454633694882041856, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427872, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454633502313156608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427873, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454633979390070784, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427874, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1444136624274411520, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427875, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1444137131906830336, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427876, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1444296198969233408, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427877, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1446401668643164160, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427878, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454636107953213440, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427879, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454636019084300288, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427880, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454636255160700928, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427881, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454635916193828864, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427882, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1444296435863523328, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427883, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1447181330298310656, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427884, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454646859950854144, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427885, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1447068983231647744, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427886, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1454654820911812608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427887, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1447064851829624832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427888, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1447151555697577984, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427889, 'admin', '2021-11-03 22:20:28', 0, 'admin', '2021-11-03 22:20:28', 1447066215150391296, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427890, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1447152316980531200, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427891, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1447152140454858752, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427892, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1447181007752138752, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427893, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1452815090377166848, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427894, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454634532320972800, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427895, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454634947762589696, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427896, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454634407905333248, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427897, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454635577059184640, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427898, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454635721364213760, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427899, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454635256807297024, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427900, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454634756141617152, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427901, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454635122975444992, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427902, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1448248411861159936, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427903, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1448248710399135744, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427904, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1451020913016442880, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427905, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1451021914112921600, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427906, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1454711652535635968, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427907, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1451022516347867136, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427908, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1451019858568417280, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427909, 'admin', '2021-11-03 22:20:29', 0, 'admin', '2021-11-03 22:20:29', 1451020004916072448, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427910, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451019756093181952, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427911, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451022920666189824, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427912, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451019189228802048, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427913, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451020615896141824, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427914, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451023141370466304, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427915, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451020290237796352, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427916, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451019630054346752, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427917, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451022340262596608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427918, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451023290524110848, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427919, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1451022072720527360, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427920, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1453963392942477312, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427921, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1453963696563949568, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427922, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 1453964029059010560, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427923, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 5129710648430592, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427924, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 5129710648430593, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427925, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 15701400130424832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427926, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 16678126574637056, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427927, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 15701915807518720, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427928, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 15708892205944832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427929, 'admin', '2021-11-03 22:20:30', 0, 'admin', '2021-11-03 22:20:30', 16678447719911424, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427930, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 25014528525733888, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427931, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 56898976661639168, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427932, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 156365156580855808, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427933, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 40238597734928384, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427934, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 45235621697949696, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427935, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 45235787867885568, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427936, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 45235939278065664, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427937, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 5129710648430594, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427938, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16687383932047360, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427939, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16689632049631232, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427940, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16689745006432256, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427941, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16689883993083904, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427942, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16690313745666048, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427943, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 5129710648430595, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427944, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16694861252005888, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427945, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16695107491205120, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427946, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 16695243126607872, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427947, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 75002207560273920, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427948, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 76215889006956544, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427949, 'admin', '2021-11-03 22:20:31', 0, 'admin', '2021-11-03 22:20:31', 76216071333351424, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427950, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 76216264070008832, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427951, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 76216459709124608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427952, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 76216594207870976, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427953, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 76216702639017984, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427954, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 39915540965232640, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427955, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 41370251991977984, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427956, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 45264987354042368, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427957, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 45265487029866496, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427958, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 45265762415284224, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427959, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 45265886315024384, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427960, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 45266070000373760, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427961, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 41363147411427328, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427962, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 41363537456533504, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427963, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 41364927394353152, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427964, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 121426317022334976, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427965, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 1255336361339260928, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427966, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 1255336077691064320, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427967, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 1255336553971060737, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427968, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 1255336455472025601, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427969, 'admin', '2021-11-03 22:20:32', 0, 'admin', '2021-11-03 22:20:32', 149452775095275520, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427970, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 41371711400054784, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427971, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 41469219249852416, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427972, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 39916171171991552, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427973, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 39918482854252544, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427974, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 211251162815401984, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427975, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 211251678651879424, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427976, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 1255337064858259457, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427977, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 1255337148748533761, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427978, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 1255337241379737601, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427979, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 211652331387752448, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427980, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 41373430515240960, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427981, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 1444308754169466880, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427982, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 129033675403694080, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427983, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 41375330996326400, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427984, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 42082442672082944, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427985, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 41378916912336896, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427986, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 113602149589454848, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427987, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 113602342657462272, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427988, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 113603512293658624, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427989, 'admin', '2021-11-03 22:20:33', 0, 'admin', '2021-11-03 22:20:33', 113603617897844736, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427990, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 16392452747300864, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427991, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 16392767785668608, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427992, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 16438800255291392, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427993, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 16438962738434048, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427994, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 16439068543946752, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427995, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 175808753877127169, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427996, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 175808922710446081, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427997, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 127996320085446656, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427998, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 1251760630449442816, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1456098986187427999, 'admin', '2021-11-03 22:20:34', 0, 'admin', '2021-11-03 22:20:34', 1251761480286736384, 496138616573952);
INSERT INTO `t_role_permission` VALUES (1457675066186797056, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 125909152017944576, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797057, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1443769249247137792, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797058, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444678087412420608, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797059, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454631893315227648, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797060, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454632063218094080, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797061, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1443769802261925888, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797062, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454629856443764736, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797063, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454628297693597696, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797064, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454627710159687680, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797065, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454363703171485696, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797066, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454628535284142080, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797067, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454627532761600000, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797068, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444678793880014848, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797069, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454630674567925760, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797070, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454630423849209856, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797071, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454629089729187840, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797072, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454628913920741376, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797073, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454631659881238528, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797074, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454630234061148160, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797075, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444137362270588928, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797076, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444137681457123328, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797077, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454632262917296128, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797078, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454632418962182144, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797079, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454633203246698496, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797080, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454632756003868672, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797081, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454632621773557760, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797082, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454632981179273216, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797083, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444270825019674624, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797084, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454633398231502848, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797085, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454633840684437504, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797086, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454634137230118912, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797087, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454633694882041856, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797088, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454633502313156608, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797089, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454633979390070784, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797090, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444136624274411520, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797091, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444137131906830336, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797092, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444296198969233408, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797093, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1446401668643164160, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797094, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454636107953213440, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797095, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454636019084300288, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797096, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454636255160700928, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797097, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454635916193828864, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797098, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1444296435863523328, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797099, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447181330298310656, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797100, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454646859950854144, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797101, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447068983231647744, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797102, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454654820911812608, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797103, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447064851829624832, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797104, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447151555697577984, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797105, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447066215150391296, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797106, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447152316980531200, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797107, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447152140454858752, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797108, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1447181007752138752, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797109, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1452815090377166848, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797110, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454634532320972800, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797111, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454634947762589696, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797112, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454634407905333248, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797113, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454635577059184640, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797114, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454635721364213760, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797115, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454635256807297024, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797116, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454634756141617152, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797117, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454635122975444992, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797118, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1448248411861159936, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797119, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1448248710399135744, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797120, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451020913016442880, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797121, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451021914112921600, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797122, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1454711652535635968, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797123, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451022516347867136, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797124, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451019858568417280, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797125, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451020004916072448, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797126, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451019756093181952, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797127, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451022920666189824, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797128, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451019189228802048, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797129, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451020615896141824, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797130, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451023141370466304, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797131, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451020290237796352, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797132, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451019630054346752, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797133, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451022340262596608, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797134, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451023290524110848, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797135, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1451022072720527360, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797136, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1453963392942477312, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797137, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1453963696563949568, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797138, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 1453964029059010560, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797139, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 5129710648430592, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797140, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 5129710648430593, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797141, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 15701400130424832, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797142, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 16678126574637056, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797143, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 15701915807518720, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797144, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 15708892205944832, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797145, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 16678447719911424, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797146, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 25014528525733888, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797147, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 56898976661639168, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797148, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 156365156580855808, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797149, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 40238597734928384, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797150, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 45235621697949696, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797151, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 45235787867885568, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797152, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 45235939278065664, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797153, 'admin', '2021-11-08 05:43:13', 0, 'admin', '2021-11-08 05:43:13', 41370251991977984, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797154, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 45264987354042368, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797155, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 45265487029866496, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797156, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 45265762415284224, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797157, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 45265886315024384, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797158, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 45266070000373760, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797159, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 41363147411427328, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797160, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 41363537456533504, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797161, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 41364927394353152, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797162, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 121426317022334976, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797163, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1255336361339260928, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797164, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1255336077691064320, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797165, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1255336553971060737, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797166, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1255336455472025601, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797167, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 149452775095275520, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797168, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 41371711400054784, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797169, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 41469219249852416, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797170, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 39916171171991552, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797171, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 39918482854252544, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797172, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 211251678651879424, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797173, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1255337064858259457, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797174, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1255337148748533761, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797175, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1255337241379737601, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797176, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 211652331387752448, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797177, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1444308754169466880, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797178, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 129033675403694080, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797179, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 41375330996326400, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797180, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 42082442672082944, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797181, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 41378916912336896, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797182, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 113602342657462272, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797183, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 113603617897844736, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797184, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 16392767785668608, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797185, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 16438800255291392, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797186, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 16438962738434048, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797187, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 16439068543946752, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797188, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 175808922710446081, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797189, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1251760630449442816, 1443036951866380288);
INSERT INTO `t_role_permission` VALUES (1457675066186797190, 'admin', '2021-11-08 05:43:14', 0, 'admin', '2021-11-08 05:43:14', 1251761480286736384, 1443036951866380288);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `department_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `street` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pass_strength` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  UNIQUE INDEX `mobile`(`mobile`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (682265633886208, '', '2018-05-01 03:13:51', 'admin', '2021-11-15 06:14:28', '[\"370000\",\"371500\",\"371502\"]', 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', '', '710543032@qq.com', '13287582787', '超级管理员', '$2a$10$48jJBXZ9y7h9UDma6l8jk.VA7Ect5p9KhBdQNTtc/RHFqauYg6ttq', '男', 0, 1, 'admin', 0, 40322777781112832, '', '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (4363087427670016, '', '2018-05-03 15:09:42', 'admin', '2021-11-11 09:08:13', '[\"510000\",\"510100\",\"510114\"]', 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', '', 'test@exrick.cn', '18782059033', '总部管理员', '$2a$10$r1vXTTF4QyFIGBzyQw9RQO3WLkuVnWs2Gek345L3QOiyIGo/RSpFq', '男', -1, 0, 'test', 0, 40322777781112832, '', '弱', '总部', '2020-04-27 11:00:00.000000');
INSERT INTO `t_user` VALUES (16739222421508096, '', '2018-06-06 18:48:02', 'admin', '2021-11-11 09:08:11', '[]', 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', '', 'zongbu@163.com', '13287582786', '核单员', '$2a$10$6i9gmvYJuwnYcZ6KU/qTBuPONr6GxsPk9.Tt5.RkiCwr/GtmnqR3a', '男', -1, 0, 'test2', 0, 40322777781112832, '', '弱', '总部', '2020-04-22 11:00:00.000000');
INSERT INTO `t_user` VALUES (1447039256827662336, 'admin', '2021-10-09 22:20:18', 'admin', '2021-11-11 09:08:09', '', 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', '', 'test@163.com', '13287582788', '录单员', '$2a$10$3RpVyV7A5W2tcR5.hxNpc.AJSF24Uo.N7PpSkTxvnM8tx.fWcSU8O', '男', -1, 0, 'test1', 0, 40322777781112832, '', '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (1447039512143335424, 'admin', '2021-10-09 22:21:19', 'admin', '2021-11-11 09:08:07', '', 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', '', 'test3@163.com', '13287582789', '财务审核账号', '$2a$10$0mRIwCESMixNilgsDzyLsOxLDCkSqkTn9leGvK9afFlFSnBJWEH7O', '男', -1, 0, 'test3', 0, 40322777781112832, '', '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (1449357730912538624, 'admin', '2021-10-16 07:53:06', 'admin', '2021-11-11 09:08:04', '', 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', '', '123456@qq.com', '13287582790', '事故调查员', '$2a$10$ilFUNxPAjBk4T3amy28Z9.35CBAvLiAwW2R0tAuMPJysPhw8k.w2e', '男', -1, 0, 'test4', 0, 40322777781112832, '', '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (1454641138865672192, 'admin', '2021-10-30 21:47:29', 'admin', '2021-11-11 09:08:02', NULL, 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', NULL, 'test5@h1n7.top', '13287582791', '事故登记员', '$2a$10$wvHr4v46Jkfva0/CJrhZ9u.njz4J/qt8N9jIrOu8c/.1lDpH5.zKi', '', -1, 0, 'test5', 0, 40322777781112832, NULL, '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (1454641433599414272, 'admin', '2021-10-30 21:48:39', 'admin', '2021-11-11 09:08:00', NULL, 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', NULL, 'test6@h1n7.top', '13287582792', '互助单审核员', '$2a$10$498HMg3a7qWSoCzwnapjP..ehYwomKiAy.SpuA28R4Cf0FdXdeUSK', '', -1, 0, 'test6', 0, 40322777781112832, NULL, '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (1454641642136014848, 'admin', '2021-10-30 21:49:28', 'admin', '2021-11-11 09:07:58', NULL, 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', NULL, 'test7@h1n7.top', '13287582793', '批单录入', '$2a$10$L5RG47MW8v7SuCvo3Kgxp.DjKUDBL.ciIZBWEFMOnoaYrUK7uefI6', '', -1, 0, 'test7', 0, 40322777781112832, NULL, '弱', '总部', NULL);
INSERT INTO `t_user` VALUES (1454641865533034496, 'admin', '2021-10-30 21:50:22', 'admin', '2021-11-11 09:07:56', NULL, 'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png', NULL, 'test8@h1n7.top', '13287582794', '批单审核', '$2a$10$GqqlQRhO3zj4SmUQPqGtEe2LSVyNi0rkWcQvaeUdv/El.QDRgqRgK', '', -1, 0, 'test8', 0, 40322777781112832, NULL, '弱', '总部', NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1447040228400435200, 'admin', '2021-10-09 22:24:10', 0, 'admin', '2021-10-09 22:24:10', 16457350655250432, 1447039256827662336);
INSERT INTO `t_user_role` VALUES (1447040323799879680, 'admin', '2021-10-09 22:24:33', 0, 'admin', '2021-10-09 22:24:33', 1447039685196124160, 16739222421508096);
INSERT INTO `t_user_role` VALUES (1447213070916128768, 'admin', '2021-10-10 09:50:59', 0, 'admin', '2021-10-10 09:50:59', 496138616573953, 1447039512143335424);
INSERT INTO `t_user_role` VALUES (1447224232886013952, 'admin', '2021-10-10 10:35:20', 0, 'admin', '2021-10-10 10:35:20', 496138616573952, 682265633886208);
INSERT INTO `t_user_role` VALUES (1449357781969801216, 'admin', '2021-10-16 07:53:18', 0, 'admin', '2021-10-16 07:53:18', 1449357433590910976, 1449357730912538624);
INSERT INTO `t_user_role` VALUES (1454641143408103424, 'admin', '2021-10-30 21:47:29', 0, 'admin', '2021-10-30 21:47:29', 1451026973898969088, 1454641138865672192);
INSERT INTO `t_user_role` VALUES (1454641435386187776, 'admin', '2021-10-30 21:48:39', 0, 'admin', '2021-10-30 21:48:39', 1451027532412489728, 1454641433599414272);
INSERT INTO `t_user_role` VALUES (1454641643889233921, 'admin', '2021-10-30 21:49:29', 0, 'admin', '2021-10-30 21:49:29', 1454637034835677184, 1454641642136014848);
INSERT INTO `t_user_role` VALUES (1454641867303030785, 'admin', '2021-10-30 21:50:22', 0, 'admin', '2021-10-30 21:50:22', 1454637350578688000, 1454641865533034496);
INSERT INTO `t_user_role` VALUES (1457675405208195072, 'admin', '2021-11-08 05:44:34', 0, 'admin', '2021-11-08 05:44:34', 1443036951866380288, 4363087427670016);

SET FOREIGN_KEY_CHECKS = 1;
