-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.34-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 dtcq.hero 结构
DROP TABLE IF EXISTS `hero`;
CREATE TABLE IF NOT EXISTS `hero` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '英雄名',
  `sec` int(10) NOT NULL COMMENT '英雄占位顺序',
  `image` varchar(100) NOT NULL COMMENT '英雄图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='刀塔传奇英雄表';

-- 正在导出表  dtcq.hero 的数据：~59 rows (大约)
DELETE FROM `hero`;
/*!40000 ALTER TABLE `hero` DISABLE KEYS */;
INSERT INTO `hero` (`id`, `name`, `sec`, `image`) VALUES
	(1, '船长', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/1.jpg'),
	(2, '小黑', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/2.jpg'),
	(3, '火女', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/3.jpg'),
	(4, '宙斯', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/4.jpg'),
	(5, '电魂', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/5.jpg'),
	(6, '恶魔巫师', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/6.jpg'),
	(7, '痛苦女王', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/7.jpg'),
	(8, '全能骑士', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/8.jpg'),
	(9, '修补匠', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/9.jpg'),
	(10, '火枪', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/10.jpg'),
	(11, '风行', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/11.jpg'),
	(12, '冰女', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/12.jpg'),
	(13, '小娜迦', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/13.jpg'),
	(14, '大鱼人', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/14.jpg'),
	(15, '潮汐', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/15.jpg'),
	(16, '美杜莎', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/16.jpg'),
	(17, '骨弓', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/17.jpg'),
	(18, '骨法', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/18.jpg'),
	(19, '骷髅王', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/19.jpg'),
	(20, '死灵法师', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/20.jpg'),
	(21, '黑鸟', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/21.jpg'),
	(22, '暗牧', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/22.jpg'),
	(23, '影魔', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/23.jpg'),
	(24, '熊喵酒仙', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/24.jpg'),
	(25, '小鹿', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/25.jpg'),
	(26, '拍拍熊', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/26.jpg'),
	(27, '蓝胖', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/27.jpg'),
	(28, '复仇', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/28.jpg'),
	(29, '神牛', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/29.jpg'),
	(30, '小小', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/30.jpg'),
	(31, '月骑', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/31.jpg'),
	(32, '剑圣', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/32.jpg'),
	(33, '斧王', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/33.jpg'),
	(34, '沉默', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/34.jpg'),
	(35, '白虎', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/35.jpg'),
	(36, '巫妖', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/36.jpg'),
	(37, '神灵武士', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/37.jpg'),
	(38, '双头龙', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/38.jpg'),
	(39, '亚龙', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/39.jpg'),
	(40, '光法', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/40.jpg'),
	(41, '直升机', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/41.jpg'),
	(42, '死骑', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/42.jpg'),
	(43, '死亡先知', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/43.jpg'),
	(44, '敌法', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/44.jpg'),
	(45, '巨魔', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/45.jpg'),
	(47, '刚背猪', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/47.jpg'),
	(48, '术士', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/48.jpg'),
	(49, '机枪兵', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/49.jpg'),
	(50, '人马', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/50.jpg'),
	(51, '圣堂刺客', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/51.jpg'),
	(52, '凤凰', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/52.jpg'),
	(53, '召唤师', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/53.jpg'),
	(54, '猴子', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/54.jpg'),
	(55, '灵魂守卫', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/55.jpg'),
	(56, '巫医', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/56.jpg'),
	(57, '白牛', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/57.jpg'),
	(59, '老鹿', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/59.jpg'),
	(60, '灰烬之灵', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/60.jpg'),
	(61, '树精卫士', 0, 'http://d.longtugame.com/uploadimg/dotaData/hero/pic2/61.jpg');
/*!40000 ALTER TABLE `hero` ENABLE KEYS */;


-- 导出  表 dtcq.result 结构
DROP TABLE IF EXISTS `result`;
CREATE TABLE IF NOT EXISTS `result` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `success` varchar(50) DEFAULT NULL COMMENT '胜利组阵容',
  `slevel` varchar(50) DEFAULT NULL COMMENT '胜利组等级',
  `sstar` varchar(50) DEFAULT NULL COMMENT '胜利组星级',
  `error` varchar(50) DEFAULT NULL COMMENT '失败组阵容',
  `elevel` varchar(50) DEFAULT NULL COMMENT '失败组等级',
  `estar` varchar(50) DEFAULT NULL COMMENT '失败组星级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='英雄p竞技结果表';

-- 正在导出表  dtcq.result 的数据：~5 rows (大约)
DELETE FROM `result`;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` (`id`, `success`, `slevel`, `sstar`, `error`, `elevel`, `estar`) VALUES
	(1, '20,35,43,45,54', '59,59,59,59,59', '3,3,3,3,2', '24,35,43,45,54', '60,60,60,60,60', '3,2,3,3,2'),
	(2, '2,35,38,43,45', '60,59,59,60,60', '3,3,2,3,3', '14,24,35,38,54', '60,60,60,60,60', '2,3,2,3,2'),
	(3, '2,29,24,35,54', '59,59,59,59,59', '2,4,3,4,2', '24,35,38,43,54', '60,60,60,60,60', '3,2,3,3,2'),
	(4, '2,24,35,38,43', '59,59,59,59,59', '3,3,2,3,3', '24,35,38,43,54', '60,60,60,60,60', '3,2,3,3,2'),
	(5, '2,17,24,35,54', '57,57,57,57,57', '3,3,3,3,2', '24,35,43,45,54', '60,60,60,60,60', '3,2,3,3,2'),
	(6, '2,4,24,35,54', '59,59,59,59,59', '2,2,3,3,2', '2,24,35,43,54', '60,60,60,60,60', '3,3,2,3,2');
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
