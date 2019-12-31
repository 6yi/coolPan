CREATE TABLE `account`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `savefilename` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `maxsize` int(11) DEFAULT NULL,
  `nowsize` int(11) DEFAULT NULL,
  `emial` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;


CREATE TABLE `files`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountid` int(11) DEFAULT NULL,
  `filename` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `filepath` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `filetype` int(11) DEFAULT NULL,
  `size` varchar(40) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `ispublic` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_id_type` (`accountid`,`filetype`),
  KEY `idx_ispublic_id_type` (`accountid`,`ispublic`,`filetype`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

insert account values(1,'admin','adminadmin123','/',5000,0,0,1);