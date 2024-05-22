-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2024 at 09:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java`
--

-- --------------------------------------------------------

--
-- Table structure for table `docgia2`
--

CREATE TABLE `docgia2` (
  `readerId` int(11) NOT NULL,
  `readername` varchar(30) NOT NULL,
  `lop` varchar(30) NOT NULL,
  `gender` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `docgia2`
--

INSERT INTO `docgia2` (`readerId`, `readername`, `lop`, `gender`, `email`, `phone`) VALUES
(1, 'a', '12', 'Nam', '1', 1),
(3, 'ac', '13', 'Nam', 'sd', 134),
(5, 'Phạm Thị Kim Dung', '72DCTT23', 'Nữ', 'dung@gmail.com', 998765345),
(6, 'Trần Thị Hồng Ánh', '72DCTT23', 'Nữ', 'anh@gmail.com', 937848932),
(7, 'Đỗ Thị Phương Thảo', '72DCTT23', 'Nữ', 'thao@gmai.com', 523456786),
(8, 'Lê Thị Phương Hoa', '72DCTT23', 'Nữ', 'hoa@gmail.com', 876543453),
(9, 'Hoàng Hải Nam', '72DCTT24', 'Nam', 'nam@gamilcom', 987654323),
(10, 'Nguyễn Quang Hưng', '72DCTT24', 'Nam', 'hung@gmail.com', 965432345),
(11, 'Đỗ Ngọc Khanh', '72DCTT25', 'Nam', 'khanh@gmailcom', 765432345),
(12, 'Bùi Hông Khanh', '72DCTT23', 'Nữ', 'khanh@gmail.com', 234567890),
(13, 'Nguyễn Thị Như Quỳnh', '73DCTT24', 'Khác', 'quynh@gmail.com', 123456677),
(14, 'kjnj', 'tt56', 'Nam', 'db@', 567890876);

-- --------------------------------------------------------

--
-- Table structure for table `phieu_muon`
--

CREATE TABLE `phieu_muon` (
  `id_phieu_muon` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `readerID` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `phi_muon` varchar(40) NOT NULL,
  `ngay_muon` date NOT NULL,
  `ngay_tra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieu_muon`
--

INSERT INTO `phieu_muon` (`id_phieu_muon`, `bookId`, `readerID`, `so_luong`, `phi_muon`, `ngay_muon`, `ngay_tra`) VALUES
(11, 20, 5, 2, '10000', '2024-06-09', '2024-04-07'),
(12, 20, 6, 1, '10000', '2024-04-08', '2024-01-01'),
(13, 25, 7, 1, '5000', '2024-05-06', '2023-05-06'),
(14, 26, 8, 2, '10000', '2024-04-08', '2024-03-02'),
(15, 28, 8, 1, '10000', '2024-05-06', '2024-02-05'),
(16, 30, 11, 1, '5000', '2024-04-08', '2024-04-03'),
(17, 29, 10, 3, '5000', '2024-06-07', '2023-06-07'),
(18, 22, 7, 1, '5000', '2024-06-09', '2023-06-09'),
(19, 30, 11, 1, '20000', '2024-04-08', '2024-04-03'),
(20, 22, 7, 1, '5000', '2024-06-09', '2023-06-09');

--
-- Triggers `phieu_muon`
--
DELIMITER $$
CREATE TRIGGER `update_phieu_tra` AFTER INSERT ON `phieu_muon` FOR EACH ROW BEGIN INSERT INTO phieu_tra (id_phieu_muon, tinh_trang) VALUES (NEW.id_phieu_muon, 'Đang Mượn'); END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `phieu_tra`
--

CREATE TABLE `phieu_tra` (
  `id_phieu_tra` int(11) NOT NULL,
  `id_phieu_muon` int(11) NOT NULL,
  `tinh_trang` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieu_tra`
--

INSERT INTO `phieu_tra` (`id_phieu_tra`, `id_phieu_muon`, `tinh_trang`) VALUES
(1, 4, 'Đang Mượn'),
(2, 2, 'Đã Trả'),
(5, 7, 'Đã Trả'),
(7, 9, 'Đã Trả'),
(8, 10, 'Đã Trả'),
(9, 11, 'Đang Mượn'),
(10, 12, 'Đã Trả'),
(11, 13, 'Đang Mượn'),
(12, 14, 'Đã Trả'),
(13, 15, 'Đang Mượn'),
(14, 16, 'Đang Mượn'),
(15, 17, 'Đang Mượn'),
(16, 18, 'Đang Mượn'),
(17, 19, 'Đang Mượn'),
(18, 20, 'Đang Mượn');

-- --------------------------------------------------------

--
-- Table structure for table `qlythe`
--

CREATE TABLE `qlythe` (
  `ID` int(11) NOT NULL,
  `IDDocGia` int(11) NOT NULL,
  `NgayDangKy` date NOT NULL,
  `NgayHetHan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `qlythe`
--

INSERT INTO `qlythe` (`ID`, `IDDocGia`, `NgayDangKy`, `NgayHetHan`) VALUES
(1, 3, '2024-04-06', '2024-04-06'),
(3, 5, '2024-04-08', '2024-04-08'),
(12, 1, '2024-04-03', '2024-04-07');

-- --------------------------------------------------------

--
-- Table structure for table `themsach`
--

CREATE TABLE `themsach` (
  `bookID` int(11) NOT NULL,
  `bookName` varchar(100) NOT NULL,
  `language` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `category` varchar(100) NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `year` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `themsach`
--

INSERT INTO `themsach` (`bookID`, `bookName`, `language`, `price`, `quantity`, `category`, `publisher`, `year`) VALUES
(20, 'Ngữ Văn', 'Tiếng Việt', 20000, 4, 'Giáo trình', 'Kim Đồng', '2003'),
(21, 'Tiếng Anh', 'Tiếng Anh', 20000, 6, 'Giáo trình', 'Kim Đồng', '2002'),
(22, 'Toán', 'Tiếng Việt', 10000, 3, 'Tài liệu tham khảo', 'Sự Thật', '2003'),
(23, 'Mỹ Thuật', 'Tiếng Việt', 30000, 3, 'Sách bài tập', 'Kim Đồng', '2013'),
(24, 'Triết Học ', 'Tiếng Việt', 20000, 4, 'Giáo trình', 'Kim Đồng', '2013'),
(25, 'Toán Cao Cấp', 'Tiếng Việt', 20000, 4, 'Giáo trình', 'Bách Khoa Hà Nội', '2003'),
(26, 'Toán Rời Rạc', 'Tiếng Anh', 25000, 5, 'Tài liệu tham khảo', 'Bách Khoa Hà Nội', '2013'),
(27, 'Tiếng Nhật', 'Tiếng Nhật', 30000, 5, 'Giáo trình', 'Kim Đồng', '2003'),
(28, 'Vật Lý Đại Cương', 'Tiếng Việt', 20000, 5, 'Giáo trình', 'Bách Khoa Hà Nội', '2001'),
(29, ' Ngôn Ngữ C#', 'Tiếng Việt', 20000, 5, 'Giáo trình', 'Tài Chính', '2013'),
(30, 'Lập Trình Nhúng', 'Tiếng Việt', 15000, 3, 'Tài liệu tham khảo', 'Bách Khoa Hà Nội', '2000'),
(31, 'Lập Trình C++', 'Tiếng Anh', 50000, 5, 'Sách bài tập', 'Quốc Gia', '2004'),
(32, 'Lịch Sử Đảng', 'Tiếng Việt', 20000, 10, 'Giáo trình', 'Bách Khoa Hà Nội', '2003'),
(33, 'Giao Thông Thông Minh', 'Tiếng Việt', 15000, 5, 'Giáo trình', 'Kim Đồng', '2006'),
(34, 'Java Nâng Cao', 'Tiếng Anh', 25000, 15, 'Tài liệu tham khảo', 'Bách Kha Hà Nội', '2006'),
(35, 'BigData', 'Tiếng Anh', 15000, 10, 'Tài liệu tham khảo', 'Tài Chính', '2015');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `docgia2`
--
ALTER TABLE `docgia2`
  ADD PRIMARY KEY (`readerId`);

--
-- Indexes for table `phieu_muon`
--
ALTER TABLE `phieu_muon`
  ADD PRIMARY KEY (`id_phieu_muon`);

--
-- Indexes for table `phieu_tra`
--
ALTER TABLE `phieu_tra`
  ADD PRIMARY KEY (`id_phieu_tra`),
  ADD KEY `id_phieu_muon` (`id_phieu_muon`);

--
-- Indexes for table `qlythe`
--
ALTER TABLE `qlythe`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_DocGia` (`IDDocGia`);

--
-- Indexes for table `themsach`
--
ALTER TABLE `themsach`
  ADD PRIMARY KEY (`bookID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `phieu_muon`
--
ALTER TABLE `phieu_muon`
  MODIFY `id_phieu_muon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `phieu_tra`
--
ALTER TABLE `phieu_tra`
  MODIFY `id_phieu_tra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `themsach`
--
ALTER TABLE `themsach`
  MODIFY `bookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `qlythe`
--
ALTER TABLE `qlythe`
  ADD CONSTRAINT `ID_DocGia` FOREIGN KEY (`IDDocGia`) REFERENCES `docgia2` (`readerId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
