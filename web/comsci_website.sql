-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: May 16, 2013 at 06:42 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `comsci_website`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `academic`
-- 

CREATE TABLE `academic` (
  `id_aca` varchar(20) NOT NULL,
  `owner` varchar(1000) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `file` varchar(1000) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY  (`id_aca`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `academic`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `calendar`
-- 

CREATE TABLE `calendar` (
  `id_cal` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `detail` varchar(5000) NOT NULL,
  `date` varchar(8) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `calendar`
-- 

INSERT INTO `calendar` VALUES ('000002', 'ssssss', 'sssssss', '25560205', '1');

-- --------------------------------------------------------

-- 
-- Table structure for table `course`
-- 

CREATE TABLE `course` (
  `id_cou` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `file` varchar(1000) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `course`
-- 

INSERT INTO `course` VALUES ('000001', '1', '1', '1');
INSERT INTO `course` VALUES ('000001', '2', '2', '1');

-- --------------------------------------------------------

-- 
-- Table structure for table `download`
-- 

CREATE TABLE `download` (
  `id_dow` varchar(20) NOT NULL,
  `id_gro` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `file` varchar(1000) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `download`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `event`
-- 

CREATE TABLE `event` (
  `id_eve` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `detail` varchar(5000) NOT NULL,
  `image` varchar(100) NOT NULL,
  `startdate` varchar(8) NOT NULL,
  `enddate` varchar(8) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY  (`id_eve`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `event`
-- 

INSERT INTO `event` VALUES ('000002', 'กิจกรรมทดสอบ', 'ราบระเอียดกิจกรรม', '', '25560101', '25570101', '1');

-- --------------------------------------------------------

-- 
-- Table structure for table `groupdownload`
-- 

CREATE TABLE `groupdownload` (
  `id_gro` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `groupdownload`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `knowledge`
-- 

CREATE TABLE `knowledge` (
  `id_kno` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `detail` varchar(5000) NOT NULL,
  `image` varchar(100) NOT NULL,
  `startdate` varchar(8) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `knowledge`
-- 

INSERT INTO `knowledge` VALUES ('000002', 'สาระน่ารู้', 'detail knowledge', '', '25560101', '1');
INSERT INTO `knowledge` VALUES ('000003', 'แก้ไขสาระน่ารู้', 'edit detail knowledge', '', '25560101', '1');

-- --------------------------------------------------------

-- 
-- Table structure for table `link`
-- 

CREATE TABLE `link` (
  `id_lin` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `link` varchar(1000) NOT NULL,
  `sequence` varchar(2) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `link`
-- 

INSERT INTO `link` VALUES ('000002', 'ccc', 'http://google.com', '1', '1');

-- --------------------------------------------------------

-- 
-- Table structure for table `news`
-- 

CREATE TABLE `news` (
  `id_new` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `detail` varchar(5000) NOT NULL,
  `file` varchar(1000) NOT NULL,
  `startdate` varchar(8) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY  (`id_new`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `news`
-- 

INSERT INTO `news` VALUES ('000001', 'ทดสอบข่าว', 'เนื้อหาข่าว', '/news/000001.rar', '25560501', '1');
INSERT INTO `news` VALUES ('000002', 'ทดสอบข่าวปักหมุด', 'เนื้อหาข่าวปักหมุด', '/news/000002.rar', '25560501', '2');

-- --------------------------------------------------------

-- 
-- Table structure for table `personnel`
-- 

CREATE TABLE `personnel` (
  `id_per` varchar(20) NOT NULL,
  `name` varchar(200) NOT NULL,
  `position` varchar(200) NOT NULL,
  `detail` varchar(5000) NOT NULL,
  `image` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `personnel`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `project`
-- 

CREATE TABLE `project` (
  `id_pro` varchar(20) NOT NULL,
  `owner` varchar(1000) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `link` varchar(1000) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `project`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `qassurance`
-- 

CREATE TABLE `qassurance` (
  `id_qas` varchar(20) NOT NULL,
  `category` varchar(100) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `file` varchar(1000) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `qassurance`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `research`
-- 

CREATE TABLE `research` (
  `id_res` varchar(20) NOT NULL,
  `owner` varchar(1000) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `file` varchar(1000) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `research`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `slideshow`
-- 

CREATE TABLE `slideshow` (
  `id_sli` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `link` varchar(1000) NOT NULL,
  `sequence` varchar(2) NOT NULL,
  `startdate` varchar(8) NOT NULL,
  `enddate` varchar(8) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY  (`id_sli`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `slideshow`
-- 

INSERT INTO `slideshow` VALUES ('000002', 'test', '2', '', '2', '25560501', '25560601', '1');
INSERT INTO `slideshow` VALUES ('000004', '1', '1', '', '1', '25560501', '25560601', '1');
INSERT INTO `slideshow` VALUES ('000005', '1', '1', '', '1', '25560501', '25560601', '1');
INSERT INTO `slideshow` VALUES ('000006', '1', '1', '', '1', '25560501', '25560601', '1');
INSERT INTO `slideshow` VALUES ('000007', '1', '1', '', '1', '25560501', '25560601', '1');
INSERT INTO `slideshow` VALUES ('000008', '1', '1', '', '1', '25560501', '25560601', '1');
INSERT INTO `slideshow` VALUES ('000009', '1', '1', '', '1', '25560501', '25560601', '1');

-- --------------------------------------------------------

-- 
-- Table structure for table `student`
-- 

CREATE TABLE `student` (
  `id_stu` varchar(20) NOT NULL,
  `name` varchar(200) NOT NULL,
  `detail` varchar(5000) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `student`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `user`
-- 

CREATE TABLE `user` (
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `pname` varchar(100) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `user`
-- 

