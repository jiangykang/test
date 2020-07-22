#初始化sql文件,先写delete 在写 insert,此行不可以删除
/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : bdp

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 27/04/2020 16:52:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 993 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '祖父id',
  `grade` tinyint(22) DEFAULT NULL COMMENT '部门等级',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人id',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_date` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '删除标志',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `custom_sort` bigint(11) DEFAULT NULL COMMENT '自定义排序',
  `enable_flag` tinyint(1) DEFAULT NULL COMMENT '有效',
  `org_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表（组织）' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '发家致富公司', '0', '0', 1, '1001', '彭于晏', '2020-03-25 16:29:29', '1001', '彭于晏', '2020-03-25 16:29:29', 0, NULL, NULL, 1, '81');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `enable_flag` tinyint(1) DEFAULT 1 COMMENT '启用有效0 无效  1 有效',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人id',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_date` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '删除标志',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `custom_sort` bigint(11) DEFAULT NULL COMMENT '自定义排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '男', '0', 'sys_user_sex', '', '', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '性别男', 1);
INSERT INTO `sys_dict_data` VALUES ('10', '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '默认分组', 1);
INSERT INTO `sys_dict_data` VALUES ('11', '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '系统分组', 2);
INSERT INTO `sys_dict_data` VALUES ('12', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '系统默认是', 1);
INSERT INTO `sys_dict_data` VALUES ('13', '否', 'N', 'sys_yes_no', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '系统默认否', 2);
INSERT INTO `sys_dict_data` VALUES ('14', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '通知', 1);
INSERT INTO `sys_dict_data` VALUES ('15', '公告', '2', 'sys_notice_type', '', 'success', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '公告', 2);
INSERT INTO `sys_dict_data` VALUES ('16', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES ('17', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '关闭状态', 2);
INSERT INTO `sys_dict_data` VALUES ('18', '新增', '1', 'sys_oper_type', '', 'info', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '新增操作', 1);
INSERT INTO `sys_dict_data` VALUES ('19', '修改', '2', 'sys_oper_type', '', 'info', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '修改操作', 2);
INSERT INTO `sys_dict_data` VALUES ('2', '女', '1', 'sys_user_sex', '', '', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '性别女', 2);
INSERT INTO `sys_dict_data` VALUES ('20', '删除', '3', 'sys_oper_type', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '删除操作', 3);
INSERT INTO `sys_dict_data` VALUES ('202004071652a8', '无效', '0', 'sys_valid_status', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-07 15:15:48', '1001', '彭于晏', '2020-04-09 11:38:47', 0, '', 0);
INSERT INTO `sys_dict_data` VALUES ('20200407e40b57', '有效', '1', 'sys_valid_status', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-07 15:15:35', '1001', '彭于晏', '2020-04-07 15:15:35', 0, '', 1);
INSERT INTO `sys_dict_data` VALUES ('202004131692ff', '按钮', 'F', 'sys_menu_type', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-13 11:18:41', '1001', '彭于晏', '2020-04-13 15:34:32', 0, '', 2);
INSERT INTO `sys_dict_data` VALUES ('202004131cdafa', '菜单', 'C', 'sys_menu_type', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-13 11:19:20', '1001', '彭于晏', '2020-04-13 15:34:28', 0, '', 1);
INSERT INTO `sys_dict_data` VALUES ('202004139b8d88', '目录', 'M', 'sys_menu_type', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-13 15:33:25', '1001', '彭于晏', '2020-04-13 15:33:25', 0, '', 0);
INSERT INTO `sys_dict_data` VALUES ('20200415024107', '异常', '1', 'sys_oper_status', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-15 15:00:06', '1001', '彭于晏', '2020-04-15 15:00:06', 0, '', 1);
INSERT INTO `sys_dict_data` VALUES ('20200415128b82', 'POST', 'POST', 'sys_request_type', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-15 11:12:57', '1001', '彭于晏', '2020-04-15 15:14:14', 0, 'post请求', 1);
INSERT INTO `sys_dict_data` VALUES ('2020041522a31c', 'DELETE', 'DELETE', 'sys_request_type', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-15 11:14:03', '1001', '彭于晏', '2020-04-15 15:14:29', 0, 'delete请求', 3);
INSERT INTO `sys_dict_data` VALUES ('20200415a5b5e7', 'GET', 'GET', 'sys_request_type', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-15 11:12:44', '1001', '彭于晏', '2020-04-15 15:14:05', 0, 'get请求', 0);
INSERT INTO `sys_dict_data` VALUES ('20200415f7e1e3', '正常', '0', 'sys_oper_status', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-15 14:59:52', '1001', '彭于晏', '2020-04-15 14:59:52', 0, '', 0);
INSERT INTO `sys_dict_data` VALUES ('20200415ff8dff', 'PUT', 'PUT', 'sys_request_type', NULL, NULL, 'N', 1, '1001', '彭于晏', '2020-04-15 11:13:24', '1001', '彭于晏', '2020-04-15 15:14:21', 0, 'put请求', 2);
INSERT INTO `sys_dict_data` VALUES ('21', '授权', '4', 'sys_oper_type', '', 'primary', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '授权操作', 4);
INSERT INTO `sys_dict_data` VALUES ('22', '导出', '5', 'sys_oper_type', '', 'warning', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '导出操作', 5);
INSERT INTO `sys_dict_data` VALUES ('23', '导入', '6', 'sys_oper_type', '', 'warning', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '导入操作', 6);
INSERT INTO `sys_dict_data` VALUES ('24', '强退', '7', 'sys_oper_type', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '强退操作', 7);
INSERT INTO `sys_dict_data` VALUES ('25', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '生成操作', 8);
INSERT INTO `sys_dict_data` VALUES ('26', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '清空操作', 9);
INSERT INTO `sys_dict_data` VALUES ('27', '成功', '0', 'sys_common_status', '', 'primary', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES ('28', '失败', '1', 'sys_common_status', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '停用状态', 2);
INSERT INTO `sys_dict_data` VALUES ('3', '未知', '2', 'sys_user_sex', '', '', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', '1001', '彭于晏', '2020-04-07 13:34:29', 0, '性别未知', 3);
INSERT INTO `sys_dict_data` VALUES ('4', '显示', '1', 'sys_show_hide', '', 'primary', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', '1001', '彭于晏', '2020-04-13 11:30:16', 0, '显示菜单', 1);
INSERT INTO `sys_dict_data` VALUES ('5', '隐藏', '0', 'sys_show_hide', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', '1001', '彭于晏', '2020-04-13 11:30:21', 0, '隐藏菜单', 2);
INSERT INTO `sys_dict_data` VALUES ('6', '启用', '1', 'sys_normal_disable', '', 'primary', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '启用状态', 1);
INSERT INTO `sys_dict_data` VALUES ('7', '停用', '0', 'sys_normal_disable', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '停用状态', 0);
INSERT INTO `sys_dict_data` VALUES ('8', '正常', '0', 'sys_job_status', '', 'primary', 'Y', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '正常状态', 1);
INSERT INTO `sys_dict_data` VALUES ('9', '暂停', '1', 'sys_job_status', '', 'danger', 'N', 1, 'admin', '彭于晏', '2020-04-03 10:53:36', 'admin', '彭于晏', '2020-04-03 10:53:36', 0, '停用状态', 2);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典类型',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人id',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_date` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '删除标志',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `custom_sort` bigint(11) DEFAULT NULL COMMENT '自定义排序',
  `enable_flag` tinyint(1) DEFAULT 1 COMMENT '有效状态（1正常 0停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 16:07:43', 0, '用户性别列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('10', '系统状态', 'sys_common_status', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '登录状态列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '菜单状态列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('202004075304f8', '是否有效', 'sys_valid_status', 'admin', '彭于晏', '2020-04-07 15:15:04', 'admin', '彭于晏', '2020-04-07 15:15:04', 0, '有效无效', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('202004136001a5', '菜单类型', 'sys_menu_type', 'admin', '彭于晏', '2020-04-13 11:17:45', 'admin', '彭于晏', '2020-04-13 11:17:45', 0, '', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('202004159a2719', '请求方式', 'sys_request_type', 'admin', '彭于晏', '2020-04-15 11:10:52', 'admin', '彭于晏', '2020-04-15 11:11:46', 0, '系统请求方式', 13, 1);
INSERT INTO `sys_dict_type` VALUES ('20200415fe71d1', '操作状态', 'sys_oper_status', 'admin', '彭于晏', '2020-04-15 14:57:05', 'admin', '彭于晏', '2020-04-15 14:57:26', 0, '', 14, 1);
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '系统开关列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '任务状态列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('5', '任务分组', 'sys_job_group', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '任务分组列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('6', '系统是否', 'sys_yes_no', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '系统是否列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('7', '通知类型', 'sys_notice_type', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '通知类型列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('8', '通知状态', 'sys_notice_status', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '通知状态列表', NULL, 1);
INSERT INTO `sys_dict_type` VALUES ('9', '操作类型', 'sys_oper_type', 'admin', '彭于晏', '2020-04-03 10:35:08', 'admin', '彭于晏', '2020-04-03 10:35:08', 0, '操作类型列表', NULL, 1);

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info`  (
  `info_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户账号',
  `ip_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_login_info
-- ----------------------------
INSERT INTO `sys_login_info` VALUES ('2020-04-278778eb', '彭于晏', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-27 16:49:00');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父菜单id',
  `parent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父菜单名',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路由地址',
  `menu_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组建路径',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '#' COMMENT '菜单图标',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '菜单状态（true显示 false隐藏）',
  `is_frame` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '是否为外链（ true 是  false否）',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人id',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_date` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标志',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `custom_sort` bigint(11) DEFAULT NULL COMMENT '自定义排序',
  `enable_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '', '/system', 'M', '', 'icon-setup', '1', '0', '', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-13 17:35:07', '0', '系统管理目录', 2, '1');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', NULL, 'user', 'C', 'views/system/users/index', 'icon-ls-9', '1', '0', 'system:user:list', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 09:48:08', '0', '用户管理菜单', 1, '1');
INSERT INTO `sys_menu` VALUES ('1001', '用户查询', '100', NULL, '', 'F', '', '#', '1', '0', 'system:user:query', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('1002', '用户新增', '100', NULL, '', 'F', '', '#', '1', '0', 'system:user:add', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('1003', '用户修改', '100', NULL, '', 'F', '', '#', '1', '0', 'system:user:edit', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('1004', '用户删除', '100', NULL, '', 'F', '', '#', '1', '0', 'system:user:remove', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 4, '1');
INSERT INTO `sys_menu` VALUES ('1005', '角色启用', '101', NULL, '', 'F', '', '#', '1', '0', 'system:role:enable', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 5, '1');
INSERT INTO `sys_menu` VALUES ('1007', '重置密码', '100', NULL, '', 'F', '', '#', '1', '0', 'system:user:resetPwd', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 7, '1');
INSERT INTO `sys_menu` VALUES ('1008', '角色查询', '101', NULL, '', 'F', '', '#', '1', '0', 'system:role:query', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('1009', '角色新增', '101', NULL, '', 'F', '', '#', '1', '0', 'system:role:add', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', NULL, 'role', 'C', 'views/system/roles/index', 'icon-ls-8', '1', '0', 'system:role:list', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 09:49:33', '0', '角色管理菜单', 2, '1');
INSERT INTO `sys_menu` VALUES ('1010', '角色修改', '101', NULL, '', 'F', '', '#', '1', '0', 'system:role:edit', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('1011', '角色删除', '101', NULL, '', 'F', '', '#', '1', '0', 'system:role:remove', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 4, '1');
INSERT INTO `sys_menu` VALUES ('1012', '数据权限', '101', NULL, '', 'F', '', '#', '1', '0', 'system:role:data', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 5, '1');
INSERT INTO `sys_menu` VALUES ('1013', '菜单查询', '102', NULL, '', 'F', '', '#', '1', '0', 'system:menu:query', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('1014', '菜单新增', '102', NULL, '', 'F', '', '#', '1', '0', 'system:menu:add', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('1015', '菜单修改', '102', NULL, '', 'F', '', '#', '1', '0', 'system:menu:edit', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('1016', '菜单删除', '102', NULL, '', 'F', '', '#', '1', '0', 'system:menu:remove', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 4, '1');
INSERT INTO `sys_menu` VALUES ('1017', '部门查询', '103', NULL, '', 'F', '', '#', '1', '0', 'system:dept:query', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('1018', '部门新增', '103', NULL, '', 'F', '', '#', '1', '0', 'system:dept:add', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('1019', '部门修改', '103', NULL, '', 'F', '', '#', '1', '0', 'system:dept:edit', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', NULL, 'menu', 'C', 'views/system/menu/index', 'icon-qrcode', '1', '0', 'system:menu:list', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-13 17:38:51', '0', '菜单管理菜单', 3, '1');
INSERT INTO `sys_menu` VALUES ('1020', '部门删除', '103', NULL, '', 'F', '', '#', '1', '0', 'system:dept:remove', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 4, '1');
INSERT INTO `sys_menu` VALUES ('1026', '字典查询', '105', NULL, '#', 'F', '', '#', '1', '0', 'system:dict:query', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('1027', '字典新增', '105', NULL, '#', 'F', '', '#', '1', '0', 'system:dict:add', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('1028', '字典修改', '105', NULL, '#', 'F', '', '#', '1', '0', 'system:dict:edit', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('1029', '字典删除', '105', NULL, '#', 'F', '', '#', '1', '0', 'system:dict:remove', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 4, '1');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', NULL, 'dept', 'C', 'views/system/organization/index', 'icon-ls-10', '1', '0', 'system:dept:list', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 09:48:21', '0', '部门管理菜单', 4, '1');
INSERT INTO `sys_menu` VALUES ('1040', '操作查询', '500', NULL, '#', 'F', '', '#', '1', '0', 'monitor:operlog:query', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('1041', '操作删除', '500', NULL, '#', 'F', '', '#', '1', '0', 'monitor:operlog:remove', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', NULL, '#', 'F', '', '#', '1', '0', 'monitor:logininfor:query', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', NULL, '#', 'F', '', '#', '1', '0', 'monitor:logininfor:remove', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', NULL, 'dict', 'C', 'views/system/dict/index', 'icon-zidian', '1', '0', 'system:dict:list', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 09:44:13', '0', '字典管理菜单', 6, '1');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', NULL, 'log', 'M', '', 'icon-ls-2', '1', '0', '', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 16:30:55', '0', '日志管理菜单', 9, '1');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', NULL, 'druid', 'C', 'views/monitor/druid/index', 'icon-computer', '1', '0', 'monitor:druid:list', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 15:15:07', '0', '数据监控菜单', 3, '1');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', NULL, 'server', 'C', 'views/monitor/server/index', 'icon-ls-3', '1', '0', 'monitor:server:list', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 15:15:12', '0', '服务监控菜单', 4, '1');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', NULL, '/monitor', 'M', '', 'icon-computer', '1', '0', '', '1', 'admin', '2020-03-11 09:51:50', '1001', '彭于晏', '2020-04-14 15:14:14', '0', '系统监控目录', 3, '1');
INSERT INTO `sys_menu` VALUES ('202004143dcac5', '代码生成', '20200414f0e2db', NULL, 'gen', 'C', 'views/systemTool/gen/index', 'icon-ls-3', '1', '0', '', '1001', '彭于晏', '2020-04-14 10:09:51', '1001', '彭于晏', '2020-04-14 10:09:51', '0', '', 0, '1');
INSERT INTO `sys_menu` VALUES ('20200414a84794', '系统接口', '20200414f0e2db', NULL, 'http://127.0.0.1:8081/doc.html', 'C', '#', 'icon-fullscreen', '1', '0', '', '1001', '彭于晏', '2020-04-14 10:12:53', '1001', '彭于晏', '2020-04-26 16:12:47', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('20200414f0e2db', '系统工具', '0', NULL, '/tool', 'M', '', 'icon-ls-5', '1', '0', '', '1001', '彭于晏', '2020-04-14 10:05:36', '1001', '彭于晏', '2020-04-14 10:05:36', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('202004240ae7f3', '生成代码', '202004143dcac5', NULL, '', 'F', '', '', '1', '0', 'tool_gen_generate', '1001', '彭于晏', '2020-04-24 08:43:19', '1001', '彭于晏', '2020-04-24 08:43:19', '0', '', 0, '1');
INSERT INTO `sys_menu` VALUES ('202004249ac320', '表单修改', '202004143dcac5', NULL, '', 'F', '', '', '1', '0', 'tool_gen_edit', '1001', '彭于晏', '2020-04-24 08:47:35', '1001', '彭于晏', '2020-04-24 08:48:00', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('20200424a9fc22', '表单删除', '202004143dcac5', NULL, '', 'F', '', '', '1', '0', 'tool_gen_del', '1001', '彭于晏', '2020-04-24 08:46:08', '1001', '彭于晏', '2020-04-24 08:46:08', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('20200424c83f2f', '表单导入', '202004143dcac5', NULL, '', 'F', '', '', '1', '0', 'tool_gen_import', '1001', '彭于晏', '2020-04-24 08:44:12', '1001', '彭于晏', '2020-04-24 08:47:06', '0', '', 1, '1');
INSERT INTO `sys_menu` VALUES ('202004266b963b', 'Avue表格设计', '20200414f0e2db', NULL, 'https://crud.avuejs.com/', 'C', '#', 'icon-workbench', '1', '0', '', '1001', '彭于晏', '2020-04-26 16:08:39', '1001', '彭于晏', '2020-04-26 16:12:41', '0', '', 2, '1');
INSERT INTO `sys_menu` VALUES ('20200426e6895d', 'Avue表单设计', '20200414f0e2db', NULL, 'https://form.avuejs.com/', 'C', '#', 'icon-createtask', '1', '0', '', '1001', '彭于晏', '2020-04-26 16:12:31', '1001', '彭于晏', '2020-04-26 16:12:31', '0', '', 3, '1');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', NULL, 'operlog', 'C', 'views/monitor/log/operLog/index', 'form', '1', '0', 'monitor:operlog:list', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '操作日志菜单', 1, '1');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', NULL, 'logininfo', 'C', 'views/monitor/log/loginInfo/index', 'logininfor', '1', '0', 'monitor:logininfor:list', '1', 'admin', '2020-03-11 09:51:50', '1', 'admin', '2020-03-11 09:51:50', '0', '登录日志菜单', 2, '1');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '模块标题',
  `business_type` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '返回参数',
  `status` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色值',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据权限',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人id',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_date` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '删除标志',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `custom_sort` bigint(11) DEFAULT NULL COMMENT '自定义排序',
  `enable_flag` tinyint(1) DEFAULT NULL COMMENT '有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('r100001', 'admin', '系统管理员', '1', '1', 'admin', '2020-03-11 09:14:31', '1', 'admin', '2020-03-11 09:14:40', 0, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `dept_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单（权限）表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `phone` bigint(11) DEFAULT NULL COMMENT '电话号码',
  `job_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号',
  `dept_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码盐',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人id',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_date` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '删除标志',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `custom_sort` bigint(11) DEFAULT NULL COMMENT '自定义排序',
  `enable_flag` tinyint(1) DEFAULT NULL COMMENT '有效',
  `hierarchy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dept_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1001', 13211111111, 'admin', 'admin', '1', '彭于晏', 'ce70de3fc34e3dcb1a0b67d2ecadb6df', 'http://lanshi-cloud-web.oss-cn-qingdao.aliyuncs.com/lspreventiontest/20200426c97c74.png', 'HqqAROcKfEHPrgnxGq', 'erp', 'erp', '2020-03-25 15:55:15', '1001', '彭于晏', '2020-04-26 10:28:05', 0, NULL, NULL, 1, NULL, '发家致富公司', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('20200331e527e3', '1001', 'r100001');

SET FOREIGN_KEY_CHECKS = 1;
