-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2022 at 10:56 AM
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
  `is_blocked` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `advertisement_id` bigint(20) NOT NULL,
  `tutor_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL
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
  `date` date DEFAULT NULL,
  `nativelanguage` varchar(255) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `tutor_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`id`, `date`, `nativelanguage`, `time`, `tutor_id`, `user_id`) VALUES
(1, '2022-12-05', 'german', '20:27:00', NULL, NULL);

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
  `tutor_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
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
  `nativelanguage` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tutor`
--

INSERT INTO `tutor` (`id`, `confirmemail`, `email`, `firstname`, `ispremium`, `lastname`, `password`, `phone`, `image`, `nativelanguage`, `price`) VALUES
(1, 'leo@ferreira.ca', 'leo@ferreira.ca', 'Leonardo', 0, 'Ferreira', 'Leonardo', 987654321, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9icmF6aWwucG5n', 'portugese', 10),
(2, 'test@test.com', 'test@test.com', 'asfasdfsdf', 0, 'dfassdfgdfgdf', 'test@test.com', 124345345, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9nZXJtYW4ucG5n', 'german', 14),
(4, NULL, 'lucas@gmail.com', 'Lucas', 0, 'Brawn', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 123456789, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy91bml0ZWRraW5nZG9tLnBuZw==', 'english', 65),
(5, NULL, 'michael@michael.com', 'Michael', 0, 'Waltz', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 124345245, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9nZXJtYW4ucG5n', 'german', 23),
(6, NULL, 'michael@gmail.com', 'Michael', 0, 'Grim', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 239044239, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9nZXJtYW4ucG5n', 'german', 11),
(10, NULL, 'philip@phil.com', 'philip', 0, 'phil', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 128934618, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9jaGluYS5wbmc=', 'mandarim', 34),
(11, NULL, 'test@test.com', 'Michael', 0, 'dugtrio', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 123456789, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9jaGluYS5wbmc=', 'cantonese', 120),
(13, NULL, 'lukas@gmail.com', 'Lukas', 0, 'Green', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 129083717, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9nZXJtYW4ucG5n', 'german', 80),
(14, NULL, 'gilbert@gmail.com', 'Gilbert', 0, 'Dohn', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 129043245, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9nZXJtYW4ucG5n', 'german', 45),
(15, NULL, 'alex@gmail.com', 'Alex', 0, 'Mihn', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 127863781, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9jaGluYS5wbmc=', 'cantonese', 32),
(16, NULL, 'michael@yahoo.com', 'Michael', 0, 'kind', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 129138912, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9pdGFseS5wbmc=', 'italian', 12);

-- --------------------------------------------------------

--
-- Table structure for table `tutor_language`
--

CREATE TABLE `tutor_language` (
  `id` bigint(20) NOT NULL,
  `language_id` bigint(20) NOT NULL,
  `tutor_id` bigint(20) NOT NULL
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
(2, 'diglet@poke.com', 'diglet@poke.com', 'digle5t', 0, 'dugtrio', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5adsd', 123456789, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9icmF6aWwucG5n', 'portuguese'),
(3, 'test@test.com', 'test@test.com', 'Leonardo', 0, 'Ferreira', 'dfsdf', 237842782, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy91bml0ZWRraW5nZG9tLnBuZw==', 'english'),
(6, 'fsleonardo91@gmail.com', 'fsleonardo91@gmail.com', 'Leonardo', 0, 'Ferreira', '$2a$10$cAGolj3Mkmd2NT51FAScWu0ntq1nSFNWHDHW1OUjNjt8uIfAd0hEW', 123423534, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy91bml0ZWRraW5nZG9tLnBuZw==', 'english'),
(8, 'test@test.com', 'test@test.com', 'digle5t', 0, 'dugtrio', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5GXcIkYe98ZTwM3NiY7q4fg8c7fg8e7f789FH47B789uibujB87BN6v786bux67v&*ty&*tdiglet', 123456789, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9qYXBhbi5wbmc=', 'japanese'),
(9, 'asd@asd.com', 'fsleonardo91@gmail.com', 'digle5t', 0, 'dugtrio', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5GXcIkYe98ZTwM3NiY7q4fg8c7fg8e7f789FH47B789uibujB87BN6v786bux67v&*ty&*tarroz', 123456789, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy91bml0ZWRraW5nZG9tLnBuZw==', 'english'),
(12, 'leo@leo.ca', 'leo@leo.ca', 'leonardo', 0, 'leo', '$2a$10$z.ySlIolTAHlz57POccaKe5Py5leo', 123456568, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9icmF6aWwucG5n', 'portuguese'),
(26, NULL, 'ricardo@gmail.com', 'Ricardo', 0, 'almeida', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 123423534, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9icmF6aWwucG5n', 'portuguese'),
(27, NULL, 'luke@gmail.com', 'Luke', 0, 'Holland', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 124233427, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy91bml0ZWRraW5nZG9tLnBuZw==', 'english'),
(28, NULL, 'yusuke@gmail.com', 'Yusuki', 0, 'Yuramesh', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 123423534, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9qYXBhbi5wbmc=', 'japanese'),
(29, NULL, 'josh@gmail.com', 'Josh', 0, 'John', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 123490237, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9ydXNzaWEucG5n', 'russian'),
(30, NULL, 'fsleonardo91@gmail.com', 'Leonardo', 0, 'Ferreira', '$2a$10$z.ySlIolTAHlz57POccaKe5Py51234', 123423453, 'c3JjL21haW4vcmVzb3VyY2VzL2ltZy9icmF6aWwucG5n', 'portuguese');

-- --------------------------------------------------------

--
-- Table structure for table `user_language`
--

CREATE TABLE `user_language` (
  `id` bigint(20) NOT NULL,
  `language_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3dd4xhjkh49d9klt4jbc949xi` (`advertisement_id`),
  ADD KEY `FKfvs6jjtnbud33tsbfjdh9rxv6` (`tutor_id`),
  ADD KEY `FK8ahhk8vqegfrt6pd1p9i03aej` (`user_id`);

--
-- Indexes for table `advertisement`
--
ALTER TABLE `advertisement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa8m1smlfsc8kkjn2t6wpdmysk` (`user_id`);

--
-- Indexes for table `language`
--
ALTER TABLE `language`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr5r370x35p0c154hro7irsd3s` (`tutor_id`),
  ADD KEY `FK4spfnm9si9dowsatcqs5or42i` (`user_id`);

--
-- Indexes for table `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tutor_language`
--
ALTER TABLE `tutor_language`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcm7cimwm4mesorgoxhqq3sfve` (`language_id`),
  ADD KEY `FK6scg7ml282sgm26m4hpqecnxl` (`tutor_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_language`
--
ALTER TABLE `user_language`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5dhe6kqh7ol7f48ker70e368r` (`language_id`),
  ADD KEY `FK5tj1u2cplnxdoiouqvmvy3ksp` (`user_id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tutor_language`
--
ALTER TABLE `tutor_language`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `user_language`
--
ALTER TABLE `user_language`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK3dd4xhjkh49d9klt4jbc949xi` FOREIGN KEY (`advertisement_id`) REFERENCES `advertisement` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK8ahhk8vqegfrt6pd1p9i03aej` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKfvs6jjtnbud33tsbfjdh9rxv6` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `FKa8m1smlfsc8kkjn2t6wpdmysk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FK4spfnm9si9dowsatcqs5or42i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKr5r370x35p0c154hro7irsd3s` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `tutor_language`
--
ALTER TABLE `tutor_language`
  ADD CONSTRAINT `FK6scg7ml282sgm26m4hpqecnxl` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKcm7cimwm4mesorgoxhqq3sfve` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_language`
--
ALTER TABLE `user_language`
  ADD CONSTRAINT `FK5dhe6kqh7ol7f48ker70e368r` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK5tj1u2cplnxdoiouqvmvy3ksp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
