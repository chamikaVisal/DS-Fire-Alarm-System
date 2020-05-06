-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 04:44 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sensor_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(25);

-- --------------------------------------------------------

--
-- Table structure for table `sensor_app`
--

CREATE TABLE `sensor_app` (
  `id` int(11) NOT NULL,
  `co2lvl` int(11) DEFAULT NULL,
  `sensor_id` int(11) NOT NULL,
  `smoke_lvl` int(11) DEFAULT NULL,
  `floor_no` int(11) DEFAULT NULL,
  `room_no` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sensor_app`
--

INSERT INTO `sensor_app` (`id`, `co2lvl`, `sensor_id`, `smoke_lvl`, `floor_no`, `room_no`, `status`) VALUES
(17, 1, 5, 10, 1, 2, NULL),
(3, 5, 10, 3, 1, 2, ''),
(13, 4, 3, 10, 0, 0, NULL),
(4, 10, 4, 1, 3, 2, ''),
(22, 1, 100, 11, 10, 10, ''),
(23, 10, 101, 10, 10, 11, NULL),
(24, 20, 103, 11, 10, 11, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_`
--

CREATE TABLE `user_` (
  `id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_`
--

INSERT INTO `user_` (`id`, `password`, `user_name`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sensor_app`
--
ALTER TABLE `sensor_app`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sensor_id` (`sensor_id`);

--
-- Indexes for table `user_`
--
ALTER TABLE `user_`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
