-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2022 at 10:34 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `langoal`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  `advertise_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_blocked` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `tutor_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE `advertisement` (
  `id` bigint(20) NOT NULL,
  `amount_paid` float NOT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `date_paid` datetime DEFAULT NULL,
  `date_posted` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL,
  `language_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `language`
--

CREATE TABLE `language` (
  `id` bigint(20) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `language_flag` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `amount_paid` float NOT NULL,
  `date` datetime DEFAULT NULL,
  `tutor_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tutor`
--

CREATE TABLE `tutor` (
  `id` bigint(20) NOT NULL,
  `confirmemail` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `ispremium` int(11) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `nativelanguage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tutor`
--

INSERT INTO `tutor` (`id`, `confirmemail`, `email`, `firstname`, `ispremium`, `lastname`, `password`, `phone`, `image`, `nativelanguage`) VALUES
(1, 'leo@ferreira.ca', 'leo@ferreira.ca', 'Leonardo', 0, 'Ferreira', 'Leonardo', 987654321, NULL, NULL),
(2, 'test@test.com', 'test@test.com', 'asfasdfsdf', 0, 'dfassdfgdfgdf', 'test@test.com', 124345345, NULL, NULL),
(3, 'diglet@poke.com', 'test@test.com', 'sadadas', 0, 'dasdasd', '$2a$10$rbfqyHWoXfYK54nJRZK7XOFLrMl9nIwi31ry/KE7HvDJuLjdS6r1K', 123423543, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tutor_language`
--

CREATE TABLE `tutor_language` (
  `id` bigint(20) NOT NULL,
  `language_id` varchar(255) DEFAULT NULL,
  `tutor_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `confirmemail` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `ispremium` int(11) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `nativelanguage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `confirmemail`, `email`, `firstname`, `ispremium`, `lastname`, `password`, `phone`, `image`, `nativelanguage`) VALUES
(2, 'diglet@poke.com', 'diglet@poke.com', 'digle5t', 0, 'dugtrio', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5adsd', 123456789, NULL, NULL),
(3, 'test@test.com', 'test@test.com', 'Leonardo', 0, 'Ferreira', 'dfsdf', 237842782, NULL, NULL),
(4, 'asd@asd.com', 'asd@asd.com', 'asdasd', 0, 'asdasd', 'sdfsdfs', 123456789, NULL, NULL),
(5, 'ads@asda', 'ads@asda', 'asdas', 0, 'dasd', 'sadasda', 378462389, NULL, NULL),
(6, 'fsleonardo91@gmail.com', 'fsleonardo91@gmail.com', 'Leonardo', 0, 'Ferreira', '$2a$10$cAGolj3Mkmd2NT51FAScWu0ntq1nSFNWHDHW1OUjNjt8uIfAd0hEW', 123423534, NULL, NULL),
(7, 'leonardo@leo.ca', 'leonardo@leo.ca', 'Leonardo', 0, 'Dos Santos Ferreira', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5GXcIkYe98ZTwM3NiYqKJrvf2vKe', 123576989, NULL, NULL),
(8, 'test@test.com', 'test@test.com', 'digle5t', 0, 'dugtrio', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5GXcIkYe98ZTwM3NiY7q4fg8c7fg8e7f789FH47B789uibujB87BN6v786bux67v&*ty&*tdiglet', 123456789, NULL, NULL),
(9, 'asd@asd.com', 'fsleonardo91@gmail.com', 'digle5t', 0, 'dugtrio', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5GXcIkYe98ZTwM3NiY7q4fg8c7fg8e7f789FH47B789uibujB87BN6v786bux67v&*ty&*tarroz', 123456789, NULL, NULL),
(12, 'leo@leo.ca', 'leo@leo.ca', 'leo', 0, 'leo', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5leo', 123456568, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_language`
--

CREATE TABLE `user_language` (
  `id` bigint(20) NOT NULL,
  `language_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_c0r9atamxvbhjjvy5j8da1kam` (`email`);

--
-- Indexes for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `language`
--
ALTER TABLE `language`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tutor_language`
--
ALTER TABLE `tutor_language`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_language`
--
ALTER TABLE `user_language`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `advertisement`
--
ALTER TABLE `advertisement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `language`
--
ALTER TABLE `language`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tutor`
--
ALTER TABLE `tutor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tutor_language`
--
ALTER TABLE `tutor_language`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user_language`
--
ALTER TABLE `user_language`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
