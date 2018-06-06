-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 21, 2018 at 12:40 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `contruction`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `createdon` datetime DEFAULT NULL,
  `modifiedon` datetime DEFAULT NULL,
  `refid` int(11) DEFAULT NULL,
  `COMMENT` varchar(1000) DEFAULT NULL,
  `postid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `createdon`, `modifiedon`, `refid`, `COMMENT`, `postid`) VALUES
(10, '2018-05-20 22:53:52', '2018-05-20 22:53:52', 26, '       My Commenbt', 1),
(11, '2018-05-20 23:02:52', '2018-05-20 23:02:52', 26, '          asdasdasdadsddadada          ', 2),
(12, '2018-05-21 10:04:32', '2018-05-21 10:04:32', 26, '                    HAHAHA', 3),
(13, '2018-05-21 10:05:02', '2018-05-21 10:05:02', 26, '    DAn toilet eka galawapan hutto                ', 3),
(14, '2018-05-21 10:07:08', '2018-05-21 10:07:08', 26, 'kattiaya shfshfsifs                    ', 4),
(15, '2018-05-21 10:08:44', '2018-05-21 10:08:44', 30, '                    fgdgfdgdgd', 4),
(16, '2018-05-21 10:29:22', '2018-05-21 10:29:22', 26, '     test ASD     ', 4),
(17, '2018-05-21 10:29:50', '2018-05-21 10:29:50', 26, '                    gdfgfgfdgdgfd', 4),
(18, '2018-05-21 10:48:25', '2018-05-21 10:48:25', 29, '                    fgdggfdgdgdf', 4),
(19, '2018-05-21 15:25:20', '2018-05-21 15:25:20', 26, 'Hutto paka\r\n', 5);

-- --------------------------------------------------------

--
-- Stand-in structure for view `commentview`
-- (See below for the actual view)
--
CREATE TABLE `commentview` (
`id` int(11)
,`createdon` datetime
,`modifiedon` datetime
,`refid` int(11)
,`comment` varchar(1000)
,`postid` int(11)
,`fullname` varchar(150)
);

-- --------------------------------------------------------

--
-- Table structure for table `forumpost`
--

CREATE TABLE `forumpost` (
  `id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `postedby` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `descri` varchar(150) DEFAULT NULL,
  `expl` varchar(1000) DEFAULT NULL,
  `URL1` varchar(200) DEFAULT NULL,
  `URL2` varchar(200) DEFAULT NULL,
  `URL3` varchar(200) DEFAULT NULL,
  `cate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forumpost`
--

INSERT INTO `forumpost` (`id`, `created`, `modified`, `postedby`, `title`, `descri`, `expl`, `URL1`, `URL2`, `URL3`, `cate`) VALUES
(1, '2018-05-20 17:36:13', '2018-05-20 17:36:13', 26, 'Test', 'ad', '\r\nTestq', '26_995.40.13.png', '26_0.40.01.png', '26_1.41.32.png', 3),
(2, '2018-05-20 20:12:49', '2018-05-20 20:12:49', 26, 'Painting', 'I am going to color wash my house.', 'Please help me to choose a good paint. I have a some issues with water leaks too.\r\n', '26_424.36.37.png', '26_473.40.40.png', '26_515.40.45.png', 1),
(3, '2018-05-20 22:59:30', '2018-05-20 22:59:30', 26, 'Read Issue', 'This is a real comment', '\r\nThis is  a real issue i want to figure out this is what??', '26_959.jpeg', '26_2.jpeg', '26_2.jpeg', 3),
(4, '2018-05-21 10:06:38', '2018-05-21 10:06:38', 26, 'Lat eke bate hira wela', 'kari siddiya', 'mala huththak neh oi\r\n', '26_282.jpeg', '26_316.jpeg', '26_316.jpeg', 3),
(5, '2018-05-21 15:24:44', '2018-05-21 15:24:44', 26, 'rwer', 'wrwrer', '\r\nsfsdfssdfsd', '26_258.jpeg', '26_413.jpeg', '26_472.jpeg', 2);

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `postedby` int(11) DEFAULT NULL,
  `topic` varchar(175) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `picurl` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `created`, `postedby`, `topic`, `content`, `picurl`) VALUES
(1, '2018-05-14 05:21:03', 1, 'ddd', 'dddd', 'invalid'),
(2, '2018-05-14 05:44:24', 1, NULL, NULL, 'invalid');

-- --------------------------------------------------------

--
-- Table structure for table `servicepost`
--

CREATE TABLE `servicepost` (
  `id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `postedby` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `descri` varchar(150) DEFAULT NULL,
  `expl` varchar(1000) DEFAULT NULL,
  `URL1` varchar(200) DEFAULT NULL,
  `URL2` varchar(200) DEFAULT NULL,
  `URL3` varchar(200) DEFAULT NULL,
  `cate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fullname` varchar(150) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(180) DEFAULT NULL,
  `type` int(11) DEFAULT '2',
  `status` smallint(6) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `fullname`, `username`, `password`, `type`, `status`) VALUES
(26, 'asd@asd.com', 'Shenal Menidiya', 'Shenal', 'asdasd', 1, 1),
(29, 'qwe@qwe.com', 'Architect', 'Architec', 'qweqwe', 2, 1),
(30, 'asdd@asd.com', 'Ayesh Jayasekara', 'ayesh', 'asdasd', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usertype`
--

CREATE TABLE `usertype` (
  `id` int(11) NOT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`id`, `type`) VALUES
(1, 'Service Provider'),
(2, 'Regular User');

-- --------------------------------------------------------

--
-- Structure for view `commentview`
--
DROP TABLE IF EXISTS `commentview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `commentview`  AS  select `a`.`id` AS `id`,`a`.`createdon` AS `createdon`,`a`.`modifiedon` AS `modifiedon`,`a`.`refid` AS `refid`,`a`.`COMMENT` AS `comment`,`a`.`postid` AS `postid`,`b`.`fullname` AS `fullname` from (`users` `b` join `comment` `a`) where (`a`.`refid` = `b`.`id`) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `forumpost`
--
ALTER TABLE `forumpost`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `usertype`
--
ALTER TABLE `usertype`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `forumpost`
--
ALTER TABLE `forumpost`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
