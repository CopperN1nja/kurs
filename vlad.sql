-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Дек 13 2021 г., 14:25
-- Версия сервера: 5.7.33
-- Версия PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `vlad`
--

-- --------------------------------------------------------

--
-- Структура таблицы `businessman`
--

CREATE TABLE `businessman` (
  `id_businessman` int(10) NOT NULL,
  `firm_name` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `firm_unp` int(15) NOT NULL,
  `business_branch` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `income` int(15) NOT NULL,
  `tax` double DEFAULT NULL,
  `tax_sum` double DEFAULT NULL,
  `profit` double DEFAULT NULL,
  `status` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `businessman`
--

INSERT INTO `businessman` (`id_businessman`, `firm_name`, `firm_unp`, `business_branch`, `income`, `tax`, `tax_sum`, `profit`, `status`) VALUES
(5, 'q', 123, 'Фермерство', 5600, 2296, 2296, 3304, 1),
(6, 'к', 10, 'Логистика', 10000, 4500, 22500, 27500, 1),
(7, 't', 2, 'Игорный', 13231, 7277.05, 32426.05, 26529.95, 1),
(8, 'y', 17, 'Самозанятый', 900, 279, 527, 1173, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `business_branch`
--

CREATE TABLE `business_branch` (
  `id_branch` int(11) NOT NULL,
  `name` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `business_branch`
--

INSERT INTO `business_branch` (`id_branch`, `name`) VALUES
(1, 'Продажи'),
(2, 'Фермерство'),
(3, 'Арендадательсво'),
(4, 'Игорный'),
(5, 'Переработка'),
(6, 'Логистика'),
(7, 'Самозанятый');

-- --------------------------------------------------------

--
-- Структура таблицы `tax`
--

CREATE TABLE `tax` (
  `id_tax` int(11) NOT NULL,
  `tax_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tax_value` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `tax`
--

INSERT INTO `tax` (`id_tax`, `tax_name`, `tax_value`) VALUES
(1, 'НДС', 20),
(2, 'Земельный', 10),
(3, 'Акцизы', 2),
(4, 'Прибыль', 18),
(5, 'Подоходный', 13),
(6, 'Недвижимость', 2),
(7, 'Экологический', 4),
(8, 'Таможенный', 15),
(9, 'Ремонт', 5),
(10, 'Видосьемка', 5),
(11, 'Парикмахер', 10),
(12, 'Казино', 15);

-- --------------------------------------------------------

--
-- Структура таблицы `tax_branch`
--

CREATE TABLE `tax_branch` (
  `id_branch` int(11) NOT NULL,
  `id_tax` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `tax_branch`
--

INSERT INTO `tax_branch` (`id_branch`, `id_tax`) VALUES
(1, 1),
(1, 4),
(1, 10),
(1, 3),
(2, 2),
(2, 5),
(2, 4),
(3, 6),
(3, 10),
(4, 1),
(4, 3),
(4, 4),
(4, 12),
(6, 1),
(6, 2),
(6, 8),
(5, 7),
(5, 2),
(5, 4),
(7, 4),
(7, 5);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id_user` int(10) NOT NULL,
  `login` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id_user`, `login`, `password`, `role`) VALUES
(2, 'a', 'a', 1),
(3, 'q', 'q', 0),
(4, 't', 'w', 1),
(5, 'd', 'd', 0);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `businessman`
--
ALTER TABLE `businessman`
  ADD PRIMARY KEY (`id_businessman`);

--
-- Индексы таблицы `business_branch`
--
ALTER TABLE `business_branch`
  ADD PRIMARY KEY (`id_branch`);

--
-- Индексы таблицы `tax`
--
ALTER TABLE `tax`
  ADD PRIMARY KEY (`id_tax`);

--
-- Индексы таблицы `tax_branch`
--
ALTER TABLE `tax_branch`
  ADD KEY `id_branch` (`id_branch`),
  ADD KEY `tax_branch_ibfk_2` (`id_tax`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `businessman`
--
ALTER TABLE `businessman`
  MODIFY `id_businessman` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `business_branch`
--
ALTER TABLE `business_branch`
  MODIFY `id_branch` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблицы `tax`
--
ALTER TABLE `tax`
  MODIFY `id_tax` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `tax_branch`
--
ALTER TABLE `tax_branch`
  ADD CONSTRAINT `tax_branch_ibfk_1` FOREIGN KEY (`id_branch`) REFERENCES `business_branch` (`id_branch`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tax_branch_ibfk_2` FOREIGN KEY (`id_tax`) REFERENCES `tax` (`id_tax`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
