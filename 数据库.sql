/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : hospital

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 03/07/2025 18:45:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '科室名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '皮肤科');
INSERT INTO `dept` VALUES (2, '牙科');
INSERT INTO `dept` VALUES (3, '儿科');
INSERT INTO `dept` VALUES (4, '妇科');
INSERT INTO `dept` VALUES (5, '消化科');
INSERT INTO `dept` VALUES (6, '神经内科');
INSERT INTO `dept` VALUES (7, '眼科');
INSERT INTO `dept` VALUES (8, '耳鼻喉科');
INSERT INTO `dept` VALUES (9, '泌尿外科');
INSERT INTO `dept` VALUES (10, '呼吸内科');

-- ----------------------------
-- Table structure for drug_info
-- ----------------------------
DROP TABLE IF EXISTS `drug_info`;
CREATE TABLE `drug_info`  (
  `ID` int NOT NULL AUTO_INCREMENT,
  `drug_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '药品名称',
  `drug_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '药品编码',
  `mnemonic_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '拼音助记码',
  `drug_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '药品类型',
  `manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生产厂家',
  `drug_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '药品单价',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_info
-- ----------------------------
INSERT INTO `drug_info` VALUES (1, '复方利血平片', 'H33022388', 'fflxpp', '16片/盒', '杭州民生药业有限公司', 15.00);
INSERT INTO `drug_info` VALUES (2, '厄贝沙坦胶囊', 'H20000546', 'ebstjn', '12粒/盒', '上海信谊天平药业有限公司', 16.00);
INSERT INTO `drug_info` VALUES (3, '黄豆苷元胶囊', 'H20066413', 'hdayjn', '8粒/盒', '陕西海天制药有限公司', 20.00);
INSERT INTO `drug_info` VALUES (4, '吲达帕胺片', 'H20003270', 'ydpap', '8片/盒', '河南中杰药业有限公司', 13.00);
INSERT INTO `drug_info` VALUES (5, '酒石酸美托洛尔片', 'H20034091', 'jssmtlep', '10片/盒', '苏州爱美津制药有限公司', 21.00);
INSERT INTO `drug_info` VALUES (6, '复方氢溴酸右美沙芬糖浆', 'H20066647', 'ffqxsymsftj', '1瓶/盒', '湖北凤凰白云山药业有限公司', 27.00);
INSERT INTO `drug_info` VALUES (7, '健儿强骨颗粒', 'B20020298', 'jeqgkl', '12粒/盒', '江西本真药业有限责任公司', 129.00);
INSERT INTO `drug_info` VALUES (8, '健儿消食口服液', 'Z52020365', 'jexskfy', '1瓶/盒', '贵阳润丰制药有限公司', 30.00);
INSERT INTO `drug_info` VALUES (9, '醋酸氯己定痔疮栓', 'H21020006', 'sslydzcs', '1瓶/盒', '锦州九泰药业有限责任公司', 15.00);
INSERT INTO `drug_info` VALUES (10, '开塞露', 'H21020970', 'ksl', '1瓶/盒', '辽宁美大康华邦药业有限公司', 5.00);
INSERT INTO `drug_info` VALUES (11, '痔速宁胶囊', 'Z20060163', 'zsnjn', '16粒/盒', '江西银涛药业有限公司', 12.50);
INSERT INTO `drug_info` VALUES (12, '京万红软膏', 'Z12020440', 'jwhrg', '1支/盒', '天津达仁堂京万红药业有限公司', 28.90);
INSERT INTO `drug_info` VALUES (13, '牛磺酸滴眼液', 'H20083246', 'nlsdyy', '1瓶/盒', '黑龙江天龙药业有限公司', 23.20);
INSERT INTO `drug_info` VALUES (14, '头孢地尼胶囊', 'J20170014', 'tbdnnn', '12粒/盒', '安徽华辰制药有限公司', 22.50);
INSERT INTO `drug_info` VALUES (15, '排毒清脂片', 'Z20090660', 'pdqzp', '4片/盒', '吉林吉尔吉药业有限公司', 31.00);
INSERT INTO `drug_info` VALUES (16, '格列齐特片', 'H14022297', 'glqtp', '6片/盒', '山西省太原晋阳制药厂', 45.00);
INSERT INTO `drug_info` VALUES (17, '阿胶', 'Z37021368', 'ej', '200g/盒', '东阿阿胶股份有限公司', 1490.00);
INSERT INTO `drug_info` VALUES (18, '龟甲胶', 'Z62020715', 'gjj', '125g/盒', '甘肃天水岐黄药业有限责任公司', 1100.00);
INSERT INTO `drug_info` VALUES (19, '健脑补肾口服液', 'Z37020805', 'jnbskfy', '4支/盒', '东阿阿胶股份有限公司', 57.50);
INSERT INTO `drug_info` VALUES (20, '八子补肾胶囊', 'B20020585', 'bzbsjn', '16粒/盒', '石家庄以岭药业股份有限公司', 68.00);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `dept_id` int NULL DEFAULT NULL COMMENT '所在科室id',
  `register_level` int NOT NULL COMMENT '挂号级别',
  `emp_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_deptid`(`dept_id` ASC) USING BTREE,
  INDEX `employee___fk_levle`(`register_level` ASC) USING BTREE,
  CONSTRAINT `employee___fk_levle` FOREIGN KEY (`register_level`) REFERENCES `regist_level` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_deptid` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 1, 1, '刘观志');
INSERT INTO `employee` VALUES (2, 1, 2, '李四');
INSERT INTO `employee` VALUES (3, 1, 3, '王五');
INSERT INTO `employee` VALUES (4, 1, 3, '张三');
INSERT INTO `employee` VALUES (5, 2, 1, '杜明濮');
INSERT INTO `employee` VALUES (6, 2, 2, '张石山');
INSERT INTO `employee` VALUES (7, 2, 3, '孙木');
INSERT INTO `employee` VALUES (8, 2, 3, '彭万里');
INSERT INTO `employee` VALUES (9, 3, 1, '赵怡博');
INSERT INTO `employee` VALUES (10, 3, 2, '孙邦原');
INSERT INTO `employee` VALUES (11, 3, 3, '孙旭诚');
INSERT INTO `employee` VALUES (12, 3, 3, '孙向明');
INSERT INTO `employee` VALUES (13, 4, 1, '刘奕泽');
INSERT INTO `employee` VALUES (14, 4, 2, '孙丹');
INSERT INTO `employee` VALUES (15, 4, 3, '孙恒易');
INSERT INTO `employee` VALUES (16, 4, 3, ' 孙楚一');
INSERT INTO `employee` VALUES (17, 5, 1, '侯盛杰');
INSERT INTO `employee` VALUES (18, 5, 2, '冯兴国');
INSERT INTO `employee` VALUES (19, 5, 3, '郝爱民');
INSERT INTO `employee` VALUES (20, 5, 3, '于学忠');
INSERT INTO `employee` VALUES (21, 6, 1, '崔品冬');
INSERT INTO `employee` VALUES (22, 6, 2, '马连良');
INSERT INTO `employee` VALUES (23, 6, 3, '刁富贵');
INSERT INTO `employee` VALUES (24, 6, 3, '傅芊涵');
INSERT INTO `employee` VALUES (25, 7, 1, '蒙瑞炎');
INSERT INTO `employee` VALUES (26, 7, 2, '李开富');
INSERT INTO `employee` VALUES (27, 7, 3, '温奕筑');
INSERT INTO `employee` VALUES (28, 7, 3, '田巧倪');
INSERT INTO `employee` VALUES (29, 8, 1, '刘金阳');
INSERT INTO `employee` VALUES (30, 8, 2, '丘耘瑈');
INSERT INTO `employee` VALUES (31, 8, 3, '洪菁玲');
INSERT INTO `employee` VALUES (32, 8, 3, '董仕鑫');
INSERT INTO `employee` VALUES (33, 9, 1, '庞佳耀');
INSERT INTO `employee` VALUES (34, 9, 2, '文晋宇');
INSERT INTO `employee` VALUES (35, 9, 3, '譚雨桐');
INSERT INTO `employee` VALUES (36, 9, 3, '潘嘉琪');
INSERT INTO `employee` VALUES (37, 10, 1, '秦丰睿');
INSERT INTO `employee` VALUES (38, 10, 2, '李慧婷');
INSERT INTO `employee` VALUES (39, 10, 3, '彭心盈');
INSERT INTO `employee` VALUES (40, 10, 3, '沈亦珊');

-- ----------------------------
-- Table structure for medical_record
-- ----------------------------
DROP TABLE IF EXISTS `medical_record`;
CREATE TABLE `medical_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `register_id` int NULL DEFAULT NULL,
  `readme` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主诉',
  `present` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '现病史',
  `present_treat` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '现病治疗情况',
  `proposal` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '检验建议',
  `careful` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '注意事项',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `medical_record___fk_reid`(`register_id` ASC) USING BTREE,
  CONSTRAINT `medical_record___fk_reid` FOREIGN KEY (`register_id`) REFERENCES `register` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '患者病历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_record
-- ----------------------------
INSERT INTO `medical_record` VALUES (2, 21, '无', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (3, 44, '无', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (4, 45, '无', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (7, 43, '无', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (8, 19, '无', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (9, 31, '无', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (10, 35, '无', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (11, 37, '精神紊乱', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (12, 40, '内分泌失调', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (13, 41, '便秘', '无', '无', '无', '无');
INSERT INTO `medical_record` VALUES (14, 1, '五', '无', '五', '无', '五');
INSERT INTO `medical_record` VALUES (15, 31, '11', '11', '11', '1', '1');
INSERT INTO `medical_record` VALUES (16, 37, '11', '11', '11', '1', '1');
INSERT INTO `medical_record` VALUES (17, 75, '觉得头痛，并且偶尔会失眠，同时睡觉时偶尔会觉得头皮发痒，非常痒', '暂未接受任何治疗', '无', '无', '减少熬夜，避免过度疲劳');
INSERT INTO `medical_record` VALUES (18, 75, '', '', '', '', '');
INSERT INTO `medical_record` VALUES (19, 76, '头痛，并且在睡觉时偶尔会觉得头皮发痒', '未接收任何治疗', '无', '无', '无');

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `register_id` int NULL DEFAULT NULL COMMENT '病历id',
  `drug_id` int NULL DEFAULT NULL COMMENT '药品id',
  `pcondition` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '拿药状态',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `prescription___fk_drug`(`drug_id` ASC) USING BTREE,
  INDEX `prescription___fk_register`(`register_id` ASC) USING BTREE,
  CONSTRAINT `prescription___fk_drug` FOREIGN KEY (`drug_id`) REFERENCES `drug_info` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `prescription___fk_register` FOREIGN KEY (`register_id`) REFERENCES `register` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prescription
-- ----------------------------
INSERT INTO `prescription` VALUES (1, 16, 2, '未处理', NULL);
INSERT INTO `prescription` VALUES (2, 16, 3, '已处理', NULL);
INSERT INTO `prescription` VALUES (3, 16, 6, '已发药', NULL);
INSERT INTO `prescription` VALUES (4, 16, 10, '已发药', NULL);
INSERT INTO `prescription` VALUES (5, 23, 1, '已发药', NULL);
INSERT INTO `prescription` VALUES (6, 23, 10, '已发药', NULL);
INSERT INTO `prescription` VALUES (7, 23, 17, '已发药', NULL);
INSERT INTO `prescription` VALUES (8, 19, 6, '未处理', NULL);
INSERT INTO `prescription` VALUES (9, 19, 15, '已发药', NULL);
INSERT INTO `prescription` VALUES (10, 16, 17, '已发药', NULL);
INSERT INTO `prescription` VALUES (11, 16, 20, '已发药', NULL);
INSERT INTO `prescription` VALUES (12, 19, 20, '已发药', NULL);
INSERT INTO `prescription` VALUES (13, 21, 20, '未处理', NULL);
INSERT INTO `prescription` VALUES (14, 19, 15, '未处理', NULL);
INSERT INTO `prescription` VALUES (15, 44, 5, '未处理', NULL);
INSERT INTO `prescription` VALUES (16, 44, 15, '已发药', NULL);
INSERT INTO `prescription` VALUES (17, 44, 19, '未处理', NULL);
INSERT INTO `prescription` VALUES (18, 45, 6, '已发药', NULL);
INSERT INTO `prescription` VALUES (19, 45, 3, '已发药', NULL);
INSERT INTO `prescription` VALUES (20, 45, 20, '已发药', NULL);
INSERT INTO `prescription` VALUES (21, 16, 5, '未处理', NULL);
INSERT INTO `prescription` VALUES (22, 16, 7, '未处理', NULL);
INSERT INTO `prescription` VALUES (23, 16, 12, '已发药', NULL);
INSERT INTO `prescription` VALUES (24, 19, 2, '已发药', NULL);
INSERT INTO `prescription` VALUES (25, 19, 6, '未处理', NULL);
INSERT INTO `prescription` VALUES (26, 19, 11, '未处理', NULL);
INSERT INTO `prescription` VALUES (27, 31, 6, '未处理', NULL);
INSERT INTO `prescription` VALUES (28, 31, 7, '已发药', NULL);
INSERT INTO `prescription` VALUES (29, 37, 1, '未处理', NULL);
INSERT INTO `prescription` VALUES (30, 37, 8, '未处理', NULL);
INSERT INTO `prescription` VALUES (31, 37, 17, '未处理', NULL);
INSERT INTO `prescription` VALUES (32, 76, 4, '已发药', NULL);
INSERT INTO `prescription` VALUES (33, 76, 8, '已发药', NULL);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目名称',
  `fee` decimal(8, 2) NULL DEFAULT NULL COMMENT '项目费用',
  `doctor` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_project_doctor_id_fk`(`doctor` ASC) USING BTREE,
  CONSTRAINT `project_project_doctor_id_fk` FOREIGN KEY (`doctor`) REFERENCES `project_doctor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '检查项目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '心脏彩超', 279.00, 1);
INSERT INTO `project` VALUES (2, '头颅CT', 135.00, 2);
INSERT INTO `project` VALUES (3, '头部磁共振', 650.00, 2);
INSERT INTO `project` VALUES (4, '肝动脉化疗', 1000.00, 3);
INSERT INTO `project` VALUES (5, '全身术中放射治疗', 5000.00, 4);
INSERT INTO `project` VALUES (6, '颅内肿瘤栓塞术', 1500.00, 2);
INSERT INTO `project` VALUES (7, '足部骨折手法整复术', 200.00, 5);
INSERT INTO `project` VALUES (8, '人工洗胃', 30.00, 6);
INSERT INTO `project` VALUES (9, '眼YAG激光', 150.00, 7);
INSERT INTO `project` VALUES (10, '旋转调强放疗', 3000.00, 8);

-- ----------------------------
-- Table structure for project_doctor
-- ----------------------------
DROP TABLE IF EXISTS `project_doctor`;
CREATE TABLE `project_doctor`  (
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_doctor
-- ----------------------------
INSERT INTO `project_doctor` VALUES ('张三', 1);
INSERT INTO `project_doctor` VALUES ('李四', 2);
INSERT INTO `project_doctor` VALUES ('王五', 3);
INSERT INTO `project_doctor` VALUES ('刘金', 4);
INSERT INTO `project_doctor` VALUES ('刘洋', 5);
INSERT INTO `project_doctor` VALUES ('孟艳', 6);
INSERT INTO `project_doctor` VALUES ('李可', 7);
INSERT INTO `project_doctor` VALUES ('孟迪', 8);

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `reg_id` int NOT NULL,
  `pro_id` int NULL DEFAULT NULL,
  `results` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '检测结果',
  `state` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用来判断是否录入jian',
  `jiancha_state` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '检查状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `projects_project_id_fk`(`pro_id` ASC) USING BTREE,
  INDEX `projects_register_id_fk`(`reg_id` ASC) USING BTREE,
  CONSTRAINT `projects_project_id_fk` FOREIGN KEY (`pro_id`) REFERENCES `project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `projects_register_id_fk` FOREIGN KEY (`reg_id`) REFERENCES `register` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of projects
-- ----------------------------
INSERT INTO `projects` VALUES (1, 1, 2, '正常', '已处理', '检查完成');
INSERT INTO `projects` VALUES (2, 16, 5, '无异常状态', '已处理', '检查完成');
INSERT INTO `projects` VALUES (4, 76, 2, '脑缺血灶', '已处理', '检查完成');

-- ----------------------------
-- Table structure for regist_level
-- ----------------------------
DROP TABLE IF EXISTS `regist_level`;
CREATE TABLE `regist_level`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `level_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '挂号级别 ',
  `fee` decimal(8, 2) NULL DEFAULT NULL COMMENT '收费数额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of regist_level
-- ----------------------------
INSERT INTO `regist_level` VALUES (1, '主任', 20.00);
INSERT INTO `regist_level` VALUES (2, '副主任', 15.00);
INSERT INTO `regist_level` VALUES (3, '主治医师', 10.00);

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `case_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '病历号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `card_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `home_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `visit_date` datetime NULL DEFAULT NULL COMMENT '看诊日期',
  `noon` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '午别',
  `deptment_id` int NULL DEFAULT NULL COMMENT '挂号科室ID',
  `employee_id` int NULL DEFAULT NULL COMMENT '本次挂号医生ID',
  `regist_level_id` int NULL DEFAULT NULL COMMENT '挂号级别ID',
  `re_condition` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '病历单处理状态',
  `pay_way` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付方式',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `register___fk_detpt_id`(`deptment_id` ASC) USING BTREE,
  INDEX `register___fk_employeeId`(`employee_id` ASC) USING BTREE,
  INDEX `register___fk_level`(`regist_level_id` ASC) USING BTREE,
  CONSTRAINT `register___fk_detpt_id` FOREIGN KEY (`deptment_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `register___fk_employeeId` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `register___fk_level` FOREIGN KEY (`regist_level_id`) REFERENCES `regist_level` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `check_gender` CHECK (`gender` in (_utf8mb4'男',_utf8mb4'女')),
  CONSTRAINT `check_noon` CHECK (`noon` in (_utf8mb4'上午',_utf8mb4'下午'))
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '患者挂号信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of register
-- ----------------------------
INSERT INTO `register` VALUES (1, '1', '刘奕泽', '男', '130533200000005519', 24, '河北省邢台市威县', '2024-08-20 15:09:46', '下午', 1, 1, 1, '已处理', '微信支付');
INSERT INTO `register` VALUES (16, '16', '刘观志', '男', '130533200409150000', 20, '河北省邢台市威县', '2024-08-23 00:00:00', '下午', 5, 18, 2, '已处理', '支付宝支付');
INSERT INTO `register` VALUES (19, '19', '刘奕泽', '男', '130533200609990000', 18, '河北省邢台市平乡', '2024-08-23 00:00:00', '下午', 2, 5, 1, '已处理', '支付宝支付');
INSERT INTO `register` VALUES (21, '21', '杜明濮', '男', '130522200409153333', 20, '河北省', '2024-08-24 00:00:00', '下午', 4, 13, 1, '已处理', '微信支付');
INSERT INTO `register` VALUES (23, '23', '赵鸡脖', '男', '130533200409551234', 20, '河北省邢台市柏乡县', '2024-08-06 00:00:00', '上午', 3, 9, 1, '已处理', '支付宝支付');
INSERT INTO `register` VALUES (28, '28', '吴鹏宇', '男', '133533200401111111', 20, '河北省邢台', '2024-08-27 00:00:00', '上午', 1, 1, 1, '已处理', '支付宝支付');
INSERT INTO `register` VALUES (31, '31', '焦焦', '男', '130222200509556666', 19, '河北省邢台', '2024-08-28 00:00:00', '下午', 6, 21, 1, '未处理', '现金支付');
INSERT INTO `register` VALUES (35, '35', '小美', '女', '123533200601234567', 18, '河北省邢台市巨鹿县', '2024-08-28 00:00:00', '下午', 4, 13, 1, '未处理', '现金支付');
INSERT INTO `register` VALUES (37, '37', '鹏仔', '男', '130533201103141514', 13, '河北省邢台', '2024-08-27 00:00:00', '下午', 2, 5, 1, '未处理', '现金支付');
INSERT INTO `register` VALUES (40, '40', '吴鹏宇', '男', '130522200400211234', 20, '河北省邢台市', '2024-08-28 00:00:00', '下午', 4, 13, 1, '未处理', '微信支付');
INSERT INTO `register` VALUES (41, '41', '小美', '女', '130533200609111224', 18, '河北省邢台市', '2024-08-28 00:00:00', '下午', 6, 21, 1, '未处理', '现金支付');
INSERT INTO `register` VALUES (42, '42', '刘观志', '男', '130533200411111234', 20, '河北省邢台市', '2024-08-28 00:00:00', '下午', 2, 6, 2, '已处理', '现金支付');
INSERT INTO `register` VALUES (43, '43', '花磊', '男', '130522200411111234', 20, '河北省邢台市', '2024-08-29 00:00:00', '下午', 7, 25, 1, '已处理', '微信支付');
INSERT INTO `register` VALUES (44, '44', '花磊', '男', '130512200401231234', 20, '河北省邢台市', '2024-08-12 00:00:00', '上午', 1, 1, 1, '已处理', '微信支付');
INSERT INTO `register` VALUES (45, '45', '吴鹏宇', '男', '130522200401231234', 20, '河北省邢台市', '2024-08-22 00:00:00', '上午', 4, 13, 1, '已处理', '支付宝支付');
INSERT INTO `register` VALUES (46, '46', '秦丰睿', '女', '1234456', 18, 'adsds', '2024-09-04 00:00:00', '上午', 5, 19, 3, '已处理', '微信支付');
INSERT INTO `register` VALUES (47, '47', '秦丰睿', '男', '12', 12, '121', '2024-09-04 00:00:00', '上午', 2, 5, 1, '已处理', '微信支付');
INSERT INTO `register` VALUES (55, '55', '庞佳耀', '男', '130533200409111111', 20, '河南省驻马店市', '2024-09-09 00:00:00', '上午', 3, 9, 1, '未处理', '现金支付');
INSERT INTO `register` VALUES (56, '56', '刘观志', '男', '13333330333333', 18, '河北', '2024-09-24 00:00:00', '上午', 3, 9, 1, '已处理', '支付宝支付');
INSERT INTO `register` VALUES (63, '63', '刘观志', '男', '11', 20, '11', '2025-06-30 00:00:00', '上午', 7, 26, 2, '未处理', '支付宝支付');
INSERT INTO `register` VALUES (64, '64', '刘观志', '男', '111', 20, '11', '2025-06-30 00:00:00', '下午', 5, 18, 2, '未处理', '微信支付');
INSERT INTO `register` VALUES (74, '74', '刘观志', '男', '1300000000000', 20, '河北省邢台市', '2025-07-04 00:00:00', '上午', 2, 5, 1, '未处理', '微信支付');
INSERT INTO `register` VALUES (75, '75', '蒙瑞炎', '男', '134567789', 20, '河南省滑县', '2025-07-04 00:00:00', '上午', 6, 22, 2, '已处理', '微信支付');
INSERT INTO `register` VALUES (76, '76', '蒙瑞炎', '男', '133234342543', 20, '河南省滑县', '2025-07-04 00:00:00', '下午', 6, 21, 1, '未处理', '微信支付');
INSERT INTO `register` VALUES (77, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已处理', NULL);
INSERT INTO `register` VALUES (78, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已处理', NULL);
INSERT INTO `register` VALUES (79, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已处理', NULL);
INSERT INTO `register` VALUES (80, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已处理', NULL);
INSERT INTO `register` VALUES (81, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已处理', NULL);
INSERT INTO `register` VALUES (82, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已处理', NULL);
INSERT INTO `register` VALUES (83, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已处理', NULL);

-- ----------------------------
-- View structure for doctor_view
-- ----------------------------
DROP VIEW IF EXISTS `doctor_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `doctor_view` AS select `d`.`dept_name` AS `dept_name`,`e`.`emp_name` AS `emp_name`,`rl`.`level_name` AS `level_name`,`rl`.`fee` AS `fee` from ((`dept` `d` join `employee` `e`) join `regist_level` `rl`) where ((`d`.`id` = `e`.`dept_id`) and (`e`.`register_level` = `rl`.`id`));

-- ----------------------------
-- View structure for re_need_drug
-- ----------------------------
DROP VIEW IF EXISTS `re_need_drug`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `re_need_drug` AS select `register`.`case_number` AS `case_number`,`register`.`real_name` AS `real_name`,`register`.`gender` AS `gender`,`register`.`card_number` AS `card_number`,`register`.`age` AS `age`,`register`.`home_address` AS `home_address` from `register` where `register`.`id` in (select distinct `prescription`.`register_id` from `prescription` where (`prescription`.`pcondition` = '未处理'));

-- ----------------------------
-- View structure for register_new
-- ----------------------------
DROP VIEW IF EXISTS `register_new`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `register_new` AS select `r`.`id` AS `id`,`r`.`real_name` AS `real_name`,`r`.`gender` AS `gender`,`r`.`card_number` AS `card_number`,`r`.`age` AS `age`,`r`.`home_address` AS `home_address`,`r`.`visit_date` AS `visit_date`,`r`.`noon` AS `noon`,`r`.`re_condition` AS `re_condition`,`d`.`dept_name` AS `dept_name`,`rl`.`fee` AS `fee`,`rl`.`level_name` AS `level_name`,`em`.`emp_name` AS `emp_name` from (((`register` `r` join `dept` `d`) join `regist_level` `rl`) join `employee` `em`) where ((`r`.`regist_level_id` = `rl`.`id`) and (`r`.`employee_id` = `em`.`id`) and (`r`.`deptment_id` = `d`.`id`) and (`r`.`re_condition` = '未处理')) order by `r`.`id`;

SET FOREIGN_KEY_CHECKS = 1;
