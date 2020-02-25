DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '发布作者',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `evaluation_shoe_type` varchar(20) DEFAULT NULL COMMENT '鞋方向',
  `postion` varchar(20) DEFAULT NULL COMMENT '篮球场上的位置',
  `half_court` varchar(20) DEFAULT NULL COMMENT '全场多/半场多',

  `stack_no_ball_state` varchar(20) DEFAULT NULL COMMENT '进攻无球状态',
  `dribble_use` varchar(20) DEFAULT NULL COMMENT '运气运用',
  `shang_lan_use` varchar(20) DEFAULT NULL COMMENT '上篮运用',
  `tou_lan_use` varchar(20) DEFAULT NULL COMMENT '投篮运用',

    `attack` varchar(100) DEFAULT NULL COMMENT '关于抢断',
  `defend` varchar(100) DEFAULT NULL COMMENT '关于篮板',
  `nut_cap` varchar(100) DEFAULT NULL COMMENT '关于盖帽',
  `basketball_play` varchar(100) DEFAULT NULL COMMENT '篮球打法描述',
  
  `gender` int(1) DEFAULT NULL COMMENT '1 男  2女',
  `age` int(2) DEFAULT NULL,
  `height` double(11,0) DEFAULT NULL,
  `weight` int(3) DEFAULT NULL,
  
  `foot_type` varchar(20) DEFAULT NULL COMMENT '对应脚型类型',
  `foot_img_url` varchar(200) DEFAULT NULL COMMENT '脚形图片',
  `bound_img_url` varchar(200) DEFAULT NULL COMMENT '足弓图片',
  `bound_type` varchar(20) DEFAULT NULL COMMENT '对应足弓类型',
  `instep_type` varchar(20) DEFAULT NULL COMMENT '对应脚背类型',
  `instep_img_url` varchar(200) DEFAULT NULL COMMENT '脚背图片',
  `bound_feet_file_id` bigint(20) DEFAULT NULL COMMENT '足弓类型',
  `foot_type_file_id` bigint(20) DEFAULT NULL COMMENT '脚形图片类型',
  `instep_file_id` bigint(20) DEFAULT NULL COMMENT '脚背类型',
  
   `physical_function` varchar(20) DEFAULT NULL,
	  `physical_function_file_id` bigint(20) DEFAULT NULL COMMENT '身体机能标识id',
  
  `shoe_type` varchar(20) DEFAULT NULL,
  `shoe_type_id` bigint(20) DEFAULT NULL COMMENT '鞋款类型标识id',
  	
  `brand_name` varchar(20) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  
  `shoe_name` varchar(20) DEFAULT NULL,
  `shoe_id` bigint(20) DEFAULT NULL COMMENT '鞋款标识id',
 
  `is_partial_code` varchar(20) DEFAULT NULL,
  `is_partial_id` bigint(20) DEFAULT NULL COMMENT '是否编码标识id',

	
  `shoe_size_name` varchar(20) DEFAULT NULL,
  `shoe_size_id` bigint(20) DEFAULT NULL COMMENT '鞋形大小',
  
    `sock_type` varchar(20) DEFAULT NULL,
	  `sock_type_id` bigint(20) DEFAULT NULL COMMENT '袜子标识',
  
      `field_type` varchar(20) DEFAULT NULL,
	  `field_type_id` bigint(20) DEFAULT NULL COMMENT '篮球场地标识',
  
   `first_foot_feel_content` varchar(20) DEFAULT NULL COMMENT '第一次较高磨合描述',
    `after_use_foot_feel_content` varchar(20) DEFAULT NULL COMMENT '使用后脚感磨合描述',
  
       `appreance_level` int(2) DEFAULT NULL,
	  `appreance_img_url` varchar(20) DEFAULT NULL COMMENT '鞋款图片',
  `appreance_shoe_evaluate` varchar(20) DEFAULT NULL COMMENT '鞋款评价',
  `appreance_color_evaluate` varchar(20) DEFAULT NULL COMMENT '配色评价',
  `appreance_influence` varchar(20) DEFAULT NULL COMMENT '外观影响',
  
  
         `work_level` int(2) DEFAULT NULL,
	  `work_img_url` varchar(20) DEFAULT NULL COMMENT '做工图片',
  `work_feng_xian` varchar(20) DEFAULT NULL COMMENT '做工缝线',
  `work_jiao_shui` varchar(20) DEFAULT NULL COMMENT '做工胶水',
  

         `package_level` int(2) DEFAULT NULL,
	  `package_img_url` varchar(20) DEFAULT NULL COMMENT '包裹图片',
  `package_first` varchar(20) DEFAULT NULL COMMENT '包裹',
  `package_middle` varchar(20) DEFAULT NULL COMMENT '包裹',
  `package_after` varchar(20) DEFAULT NULL COMMENT '包裹',
  
           `zhi_cheng_level` int(2) DEFAULT NULL,
	  `zhi_cheng_img_url` varchar(20) DEFAULT NULL COMMENT '支撑图片',
  `zhi_cheng_mian_first` varchar(20) DEFAULT NULL COMMENT '支撑面',
  `zhi_cheng_mian_middle` varchar(20) DEFAULT NULL COMMENT '支撑面',
  `zhi_cheng_mian_after` varchar(20) DEFAULT NULL COMMENT '支撑面',
    `zhi_cheng_bottom_first` varchar(20) DEFAULT NULL COMMENT '鞋底支撑面',
  `zhi_cheng_bottom_middle` varchar(20) DEFAULT NULL COMMENT '支撑面',
  `zhi_cheng_bottom_after` varchar(20) DEFAULT NULL COMMENT '支撑面',
  
  
           `flex_level` int(2) DEFAULT NULL,
	  `flex_img_url` varchar(20) DEFAULT NULL COMMENT '做工图片',
  `flex_shoe_mian_bottom` varchar(20) DEFAULT NULL COMMENT '鞋面喝鞋底配合',
  `flex_shoe_mian` varchar(20) DEFAULT NULL COMMENT '鞋面扭功能',
  `flex_shoe_bottom` varchar(20) DEFAULT NULL COMMENT '鞋底防扭功能',
  
             `touqi_level` int(2) DEFAULT NULL,
	  `touqi_img_url` varchar(20) DEFAULT NULL COMMENT '透气图片',
  `touqi_content` varchar(20) DEFAULT NULL COMMENT '透气内容',
  
  
               `zhuadili_level` int(2) DEFAULT NULL,
	  `zhuadili_img_url` varchar(20) DEFAULT NULL COMMENT '抓地力',
  `zhuadili_content` varchar(20) DEFAULT NULL COMMENT '抓地力内容',
  
                 `naimo_level` int(2) DEFAULT NULL,
	  `naimo_img_url` varchar(20) DEFAULT NULL COMMENT '抓地力',
  `naimo_content` varchar(20) DEFAULT NULL COMMENT '抓地力内容',
  
  
             `huanzhen_level` int(2) DEFAULT NULL,
	  `huanzhen_img_url` varchar(20) DEFAULT NULL COMMENT '支撑图片',
  `huanzhen_content` varchar(20) DEFAULT NULL COMMENT '支撑面',
  `huanzhen_first` varchar(20) DEFAULT NULL COMMENT '支撑面',
  `huanzhen_after` varchar(20) DEFAULT NULL COMMENT '支撑面',

  
  `summary` varchar(200) DEFAULT NULL COMMENT '总结',
`summary_img_url` varchar(200) DEFAULT NULL COMMENT '总结',

`evaluation_img_url` varchar(200) DEFAULT NULL COMMENT '测评封面',
  `title` varchar(100) DEFAULT NULL,
    `introduction` varchar(200) DEFAULT NULL COMMENT '简介',
  
  `evaluation_date` datetime DEFAULT NULL COMMENT '发布测评时间',

  
  `status` int(11) DEFAULT NULL COMMENT '测评状态  0待审核  1审核成功  2下架',  
  `order_num` int(30) DEFAULT NULL,
  `recomm_num` int(11) DEFAULT '0' COMMENT '推荐值',
  `hot_num` int(11) DEFAULT '0' COMMENT '热门值',
  

  `remark` varchar(500) DEFAULT '',

  `create_date` timestamp NOT NULL  COMMENT '创建时间',
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `english_name` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL  COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', '阿迪达斯', 'Adidas');
INSERT INTO `brand` VALUES ('2', '耐克', 'Nike');
INSERT INTO `brand` VALUES ('3', '李宁', 'Li-Ning');
INSERT INTO `brand` VALUES ('13', '安踏', 'ANTA');
INSERT INTO `brand` VALUES ('14', '安德玛', 'Under Armour');
INSERT INTO `brand` VALUES ('15', '无品牌', '无品牌');

-- ----------------------------
-- Table structure for `size_type`
-- ----------------------------
DROP TABLE IF EXISTS `shoe_size_type`;
CREATE TABLE `shoe_size_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL  COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of size_type
-- ----------------------------
INSERT INTO `shoe_size_type` VALUES ('7', 'US(美国码)', '1');
INSERT INTO `shoe_size_type` VALUES ('8', 'UK(英国码)', '1');
INSERT INTO `shoe_size_type` VALUES ('9', 'MM(中国码)', '1');
INSERT INTO `shoe_size_type` VALUES ('10', 'EUR(欧洲码)', '1');
INSERT INTO `shoe_size_type` VALUES ('91', '请问 ', '19');
INSERT INTO `shoe_size_type` VALUES ('92', 'UK(英国码)', '2');
INSERT INTO `shoe_size_type` VALUES ('93', 'US(美国码)', '2');
INSERT INTO `shoe_size_type` VALUES ('94', 'EUR(欧洲码)', '2');
INSERT INTO `shoe_size_type` VALUES ('95', 'test', '3');

-- ----------------------------
-- Table structure for `shoe_size`
-- ----------------------------
DROP TABLE IF EXISTS `shoe_size`;
CREATE TABLE `shoe_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `size` double DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `type` tinyint(20) DEFAULT NULL,
  `shoe_size_type` bigint(20) DEFAULT NULL,
  `shoe_size_type_name` varchar(50) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL  COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=322 DEFAULT CHARSET=utf8;

