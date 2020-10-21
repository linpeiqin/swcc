-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('3fb1c570496d4c09ab99b8d31b06ccc', 'ROLE_USER', '普通用户', '2019-09-10 10:08:58', '2019-09-10 10:08:58', '');
INSERT INTO `sys_authority` VALUES ('3fb1c570496d4c09ab99b8d31b06xxx', 'ROLE_SA', '超级管理员', '2019-09-10 10:08:58', '2019-09-10 10:08:58', '/sys/**,/logging');
INSERT INTO `sys_authority` VALUES ('3fb1c570496d4c09ab99b8d31b06zzz', 'ROLE_ADMIN', '管理员', '2019-09-10 10:08:58', '2019-09-10 10:08:58', '/sys/**');

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02005', '智慧厕所云平台', '/xxx', '', '2019-09-11 18:05:21', '2019-09-11 18:05:21');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xaa', '权限管理', '/sys/sysAuthority/authority', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xcc', '菜单管理', '/sys/sysMenu/menu', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xxx', '系统管理', '/sys', '', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xzz', '用户管理', '/sys/sysUser/user', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('74315e162f524a4d88aa931f02416f26', '实时监控', '/monitor', '35cb950cebb04bb18bb1d8b742a02xxx', '2020-06-10 15:07:07', '2020-06-10 15:07:07');
INSERT INTO `sys_menu` VALUES ('914aa22c78af4327822061f3eada4067', '实时日志', '/logging', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-11 11:19:52', '2019-09-11 11:19:52');
INSERT INTO `sys_menu` VALUES ('bcf17dc0ce304f0ba02d64ce21ddb4f9', '系统设置', '/sys/sysSetting/setting', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-17 10:46:11', '2019-09-17 10:46:11');

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES ('1', 'SWC', 'https://avatar.gitee.com/uploads/0/5137900_huanzi-qch.png!avatar100?1562729811', '© 2019 - 2020   智慧厕所云平台', '<h1 style=\"white-space: normal; text-align: center;\"><span style=\"color: rgb(255, 0, 0);\">通知</span></h1><p style=\"white-space: normal;\"><span style=\"color: rgb(255, 0, 0);\">1、不得在公共场合吸烟；</span></p><p style=\"white-space: normal;\"><span style=\"color: rgb(255, 0, 0);\">2、xxxxxxx；</span></p><p><br/></p>', '2019-09-17 10:15:38', '2019-09-17 10:15:40', '123456', 'rgba(54, 123, 183,  0.73)', 'Y');

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'sa', '超级管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'Y', '2019-07-19 16:36:03', '2019-09-17 12:00:36');
INSERT INTO `sys_user` VALUES ('2', 'admin', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'N', '2019-07-19 16:36:03', '2019-09-12 16:14:28');
INSERT INTO `sys_user` VALUES ('3fb1c570496d4c09ab99b8d31b0671cf', 'daji', '妲己', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'Y', '2019-09-11 18:11:41', '2019-09-17 12:09:47');
INSERT INTO `sys_user` VALUES ('b5ac62e154964151a19c565346bb354a', 'xiaofang', '小芳', '96E79218965EB72C92A549DD5A330112', 'Y', '', NULL, '2019-09-17 12:00:36', 'N', '2019-09-17 14:12:41', '2019-09-17 14:28:57');


-- ----------------------------
-- Records of sys_user_authority
-- ----------------------------
INSERT INTO `sys_user_authority` VALUES ('0dc1b156ed544c0986823e9cd818da08', '2', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-12 16:14:28', '2019-09-12 16:14:28');
INSERT INTO `sys_user_authority` VALUES ('90c18739f3ad41ae8010f6c2b7eeaac5', '3fb1c570496d4c09ab99b8d31b0671cf', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-17 12:09:47', '2019-09-17 12:09:47');
INSERT INTO `sys_user_authority` VALUES ('9ca34956ceae4af0a74f4931344e9d1b', '1', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-17 12:00:37', '2019-09-17 12:00:37');
INSERT INTO `sys_user_authority` VALUES ('a414567aaae54b42b8344da02795cb91', '2', '3fb1c570496d4c09ab99b8d31b06zzz', '2019-09-12 16:14:28', '2019-09-12 16:14:28');
INSERT INTO `sys_user_authority` VALUES ('b34f7092406c46189fee2690d9f6e493', '1', '3fb1c570496d4c09ab99b8d31b06xxx', '2019-09-17 12:00:37', '2019-09-17 12:00:37');
INSERT INTO `sys_user_authority` VALUES ('de60e5bbbacf4c739e44a60130d0f534', 'b5ac62e154964151a19c565346bb354a', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-17 14:28:58', '2019-09-17 14:28:58');
INSERT INTO `sys_user_authority` VALUES ('f6514b57d1524afd8dfa7cb2c3ca6a11', '1', '3fb1c570496d4c09ab99b8d31b06zzz', '2019-09-17 12:00:37', '2019-09-17 12:00:37');


-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------
INSERT INTO `sys_user_menu` VALUES ('1337996a0aba460bbf0b82db9a1da207', '1', '35cb950cebb04bb18bb1d8b742a02xxx', '2020-06-10 15:07:23', '2020-06-10 15:07:23');
INSERT INTO `sys_user_menu` VALUES ('3232782f25ec44b09438ab9805b85f83', '2', '35cb950cebb04bb18bb1d8b742a02xcc', '2019-09-12 16:14:28', '2019-09-12 16:14:28');
INSERT INTO `sys_user_menu` VALUES ('39d6a157972e400fabcd753d3766efc9', '1', '35cb950cebb04bb18bb1d8b742a02xaa', '2020-06-10 15:07:23', '2020-06-10 15:07:23');
INSERT INTO `sys_user_menu` VALUES ('3b0a160e8c624b2f86918afcd5107704', '1', '35cb950cebb04bb18bb1d8b742a02xcc', '2020-06-10 15:07:23', '2020-06-10 15:07:23');
INSERT INTO `sys_user_menu` VALUES ('57791437b9774d8abf74562a49c55a1a', '2', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-12 16:14:28', '2019-09-12 16:14:28');
INSERT INTO `sys_user_menu` VALUES ('6afadafdc36c426182853761bf68d870', '1', '74315e162f524a4d88aa931f02416f26', '2020-06-10 15:07:23', '2020-06-10 15:07:23');
INSERT INTO `sys_user_menu` VALUES ('6e8fe2b9307a4855ba7d006dc17c97ae', '3fb1c570496d4c09ab99b8d31b0671cf', '35cb950cebb04bb18bb1d8b742a02005', '2019-09-17 12:09:47', '2019-09-17 12:09:47');
INSERT INTO `sys_user_menu` VALUES ('81f4999dde514e0ea43acfc70bfd35a8', '1', '914aa22c78af4327822061f3eada4067', '2020-06-10 15:07:23', '2020-06-10 15:07:23');
INSERT INTO `sys_user_menu` VALUES ('9f8ccddc9fa84e0b9ff74128d20e9024', '2', '35cb950cebb04bb18bb1d8b742a02xaa', '2019-09-12 16:14:28', '2019-09-12 16:14:28');
INSERT INTO `sys_user_menu` VALUES ('c4220e4602fd4f2ca70da046466c6b45', '2', '35cb950cebb04bb18bb1d8b742a02xzz', '2019-09-12 16:14:28', '2019-09-12 16:14:28');
INSERT INTO `sys_user_menu` VALUES ('cdf8f786c658437ba77eb7d7fdd6b9cb', '1', '35cb950cebb04bb18bb1d8b742a02xzz', '2020-06-10 15:07:23', '2020-06-10 15:07:23');
INSERT INTO `sys_user_menu` VALUES ('d646090ba4114c85b0a2fc2c9082a188', '1', 'bcf17dc0ce304f0ba02d64ce21ddb4f9', '2020-06-10 15:07:23', '2020-06-10 15:07:23');
INSERT INTO `sys_user_menu` VALUES ('d8bfa6eb34ef4946bb2cd1b9c0dbac0d', 'b5ac62e154964151a19c565346bb354a', '35cb950cebb04bb18bb1d8b742a02005', '2019-09-17 14:28:58', '2019-09-17 14:28:58');
SET FOREIGN_KEY_CHECKS = 1;
