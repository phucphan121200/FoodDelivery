-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 23, 2021 lúc 01:16 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `delivery`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `pickupaddress` varchar(2000) DEFAULT NULL,
  `deliveryaddress` varchar(2000) DEFAULT NULL,
  `mass` float DEFAULT NULL,
  `receivername` varchar(100) DEFAULT NULL,
  `receiverphone` varchar(10) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `collectstate` bit(1) DEFAULT NULL,
  `collectmoney` float DEFAULT NULL,
  `postage` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `typeofservice` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `idpayment` int(11) DEFAULT NULL,
  `idshipment` int(11) DEFAULT NULL,
  `typeoforder` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `content` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `idorder` int(11) DEFAULT NULL,
  `danhgiadichvu` float DEFAULT NULL,
  `ghichu` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `content` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `type` varchar(500) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `idwallet` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `policy`
--

CREATE TABLE `policy` (
  `id` int(11) NOT NULL,
  `name` varchar(2000) DEFAULT NULL,
  `description` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shipment`
--

CREATE TABLE `shipment` (
  `id` int(11) NOT NULL,
  `iddriver` int(11) DEFAULT NULL,
  `totalmass` float DEFAULT NULL,
  `totalorders` int(11) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `address` varchar(2000) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `idnumber` varchar(12) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `activationcode` varchar(6) DEFAULT NULL,
  `resetpasswordcode` varchar(6) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `driverlicensenumber` varchar(12) DEFAULT NULL,
  `typeofuser` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `gender`, `phone`, `address`, `email`, `idnumber`, `username`, `password`, `activationcode`, `resetpasswordcode`, `state`, `driverlicensenumber`, `typeofuser`) VALUES
(52, 'Đinh Tấn Tú', '', '327644657', NULL, NULL, NULL, 'dinhtantu', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'block', NULL, 'CUSTOMER'),
(53, 'Võ Hồ An Khang', NULL, '896521486', NULL, NULL, NULL, 'khangvo123', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER'),
(56, NULL, NULL, NULL, NULL, NULL, NULL, 'diepthuyvi', '25f9e794323b453885f5181f1b624db', '532082', '882700', 'consudung', NULL, 'ADMIN'),
(57, 'Diệp Thúy Vi', 'Nữ', '', 'Đồng Tháp', 'diepvi2810@gmail.com', NULL, 'vivivivi', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'DRIVER'),
(115, 'Duong Thanh Trang', NULL, '086555656', NULL, NULL, NULL, 'Trang127', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'DRIVER'),
(129, 'Diep Thuy Vi', NULL, NULL, NULL, NULL, NULL, 'vi116', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'DRIVER'),
(134, 'Diep Vi', NULL, NULL, NULL, NULL, NULL, 'vi188', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER'),
(136, 'Boi Tuyen', NULL, '898018964', NULL, NULL, NULL, 'tuyen191', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'block', NULL, 'CUSTOMER'),
(137, 'Cao Thi Mai Tram', NULL, '902441480', NULL, NULL, NULL, 'tram185', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER'),
(140, 'Duong Boi Tuyen', NULL, '889562225', NULL, NULL, NULL, 'tuyen101', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', NULL, 'CUSTOMER'),
(142, 'Duong Gia Han', 'Fem', '896125368', 'TPHCM', 'giahan@gmail.com', '123123123', 'han88', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'block', '123123123', 'DRIVER'),
(144, 'Duong Thanh Trang', 'Fem', '863125964', 'Can Tho', 'tranglun@gmail.com', '123456789', 'trang41', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', '123123124', 'DRIVER'),
(145, 'Duong Thanh Trang', 'fem', '863125961', 'Can Tho', 'tranglun@gmail.com', '123456789', 'trang150', '25f9e794323b453885f5181f1b624db', NULL, NULL, 'consudung', '123123124', 'DRIVER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wallet`
--

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `type` varchar(500) DEFAULT NULL,
  `isactive` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idpayment` (`idpayment`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idshipment` (`idshipment`);

--
-- Chỉ mục cho bảng `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`);

--
-- Chỉ mục cho bảng `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idorder` (`idorder`);

--
-- Chỉ mục cho bảng `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idwallet` (`idwallet`);

--
-- Chỉ mục cho bảng `policy`
--
ALTER TABLE `policy`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `shipment`
--
ALTER TABLE `shipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iddriver` (`iddriver`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `policy`
--
ALTER TABLE `policy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `shipment`
--
ALTER TABLE `shipment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=147;

--
-- AUTO_INCREMENT cho bảng `wallet`
--
ALTER TABLE `wallet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`idpayment`) REFERENCES `payment` (`id`),
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`idshipment`) REFERENCES `shipment` (`id`);

--
-- Các ràng buộc cho bảng `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`idorder`) REFERENCES `bill` (`id`);

--
-- Các ràng buộc cho bảng `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`idwallet`) REFERENCES `wallet` (`id`);

--
-- Các ràng buộc cho bảng `shipment`
--
ALTER TABLE `shipment`
  ADD CONSTRAINT `shipment_ibfk_1` FOREIGN KEY (`iddriver`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
