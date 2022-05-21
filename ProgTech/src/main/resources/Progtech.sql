-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Máj 21. 23:22
-- Kiszolgáló verziója: 10.4.19-MariaDB
-- PHP verzió: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `Progtech`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `graphics_card`
--

CREATE TABLE `graphics_card` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `memory_type` varchar(50) NOT NULL,
  `tdp` int(10) NOT NULL,
  `capacity` int(10) NOT NULL,
  `speed` int(10) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `graphics_card`
--

INSERT INTO `graphics_card` (`id`, `name`, `memory_type`, `tdp`, `capacity`, `speed`, `price`) VALUES
(1, 'Nvidia RTX 2070 Mobile', 'GDDR6', 115, 8, 1750, 45000),
(2, 'Radeon RX 6650M', 'GDDR6', 120, 8, 2416, 70000),
(3, 'Nvidia RTX 3080 Ti Mobile', 'GDDR6', 115, 16, 2000, 120000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `laptops`
--

CREATE TABLE `laptops` (
  `id` int(10) NOT NULL,
  `storage` int(10) NOT NULL,
  `processor` int(10) NOT NULL,
  `graphics_card` int(10) DEFAULT NULL,
  `memory` int(10) NOT NULL,
  `os` int(10) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL,
  `type` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `memory`
--

CREATE TABLE `memory` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `speed` int(10) NOT NULL,
  `capacity` int(10) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `memory`
--

INSERT INTO `memory` (`id`, `name`, `type`, `speed`, `capacity`, `price`) VALUES
(1, 'G.Skill Trident Z5', 'DDR5', 6000, 32, 40000),
(2, 'Kinston HyperX Predator ', 'DDR4', 4800, 16, 20000),
(3, 'Corsair Vangeance RGB', 'DDR4', 3200, 8, 8000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `orders`
--

CREATE TABLE `orders` (
  `id` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `lid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `os`
--

CREATE TABLE `os` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `os`
--

INSERT INTO `os` (`id`, `name`, `price`) VALUES
(1, 'FreeDOS 1.1', 0),
(2, 'Windows 7 Home', 8000),
(3, 'Windows 10 Home', 12000),
(4, 'Windows 10 Pro', 14000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `processor`
--

CREATE TABLE `processor` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `speed` int(10) NOT NULL,
  `tdp` int(50) NOT NULL,
  `core` int(10) NOT NULL,
  `thread` int(10) NOT NULL,
  `gpu_name` varchar(50) NOT NULL,
  `gpu_speed` int(10) NOT NULL,
  `gpu_memory` varchar(50) NOT NULL,
  `gpu_capacity` int(10) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `processor`
--

INSERT INTO `processor` (`id`, `name`, `speed`, `tdp`, `core`, `thread`, `gpu_name`, `gpu_speed`, `gpu_memory`, `gpu_capacity`, `price`) VALUES
(1, 'Ryzen 5 6008U', 4700, 15, 8, 16, 'Radeon 680M', 2400, 'GDDR6', 2, 35000),
(2, 'Intel Core i5-12600H', 4500, 45, 12, 16, 'Iris Xe 80EU', 1100, 'GDDR6', 2, 40000),
(3, 'Intel Celeron 7300', 1000, 9, 5, 6, 'UHD Graphics 48EU', 1150, 'GDDR6', 1, 9000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `storage`
--

CREATE TABLE `storage` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `speed` int(10) NOT NULL,
  `capacity` int(10) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `storage`
--

INSERT INTO `storage` (`id`, `name`, `type`, `speed`, `capacity`, `price`) VALUES
(1, 'Samsung EVO 970 ', 'SSD', 2500, 1000, 22000),
(2, 'Seagate BarraCuda', 'HDD', 6000, 2000, 18000),
(3, 'Western Digital WD Blue', 'HDD', 6000, 1000, 11000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `graphics_card`
--
ALTER TABLE `graphics_card`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `laptops`
--
ALTER TABLE `laptops`
  ADD PRIMARY KEY (`id`),
  ADD KEY `memory` (`memory`),
  ADD KEY `os` (`os`),
  ADD KEY `graphics_card` (`graphics_card`),
  ADD KEY `processor` (`processor`),
  ADD KEY `storage` (`storage`);

--
-- A tábla indexei `memory`
--
ALTER TABLE `memory`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lid` (`lid`),
  ADD KEY `uid` (`uid`);

--
-- A tábla indexei `os`
--
ALTER TABLE `os`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `processor`
--
ALTER TABLE `processor`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `storage`
--
ALTER TABLE `storage`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `graphics_card`
--
ALTER TABLE `graphics_card`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `laptops`
--
ALTER TABLE `laptops`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `memory`
--
ALTER TABLE `memory`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `os`
--
ALTER TABLE `os`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `processor`
--
ALTER TABLE `processor`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `storage`
--
ALTER TABLE `storage`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `laptops`
--
ALTER TABLE `laptops`
  ADD CONSTRAINT `laptops_ibfk_2` FOREIGN KEY (`memory`) REFERENCES `memory` (`id`),
  ADD CONSTRAINT `laptops_ibfk_3` FOREIGN KEY (`os`) REFERENCES `os` (`id`),
  ADD CONSTRAINT `laptops_ibfk_6` FOREIGN KEY (`graphics_card`) REFERENCES `graphics_card` (`id`),
  ADD CONSTRAINT `laptops_ibfk_7` FOREIGN KEY (`processor`) REFERENCES `processor` (`id`),
  ADD CONSTRAINT `laptops_ibfk_8` FOREIGN KEY (`storage`) REFERENCES `storage` (`id`);

--
-- Megkötések a táblához `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`lid`) REFERENCES `laptops` (`id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
