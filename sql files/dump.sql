-- Procedures

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_orders_log` (IN `item` INT, IN `customer_id` INT, IN `quan` INT, IN `total` FLOAT, IN `ordered` DATETIME)   BEGIN

INSERT INTO `orders_log`(`item_id`, `user_id`, `quantity`, `total_price`, `ordered_at`) VALUES(item,customer_id,quan,total,'ordered');

END$$




CREATE DEFINER=`root`@`localhost` PROCEDURE `cancel_order` (IN `order_id` INT)   BEGIN

UPDATE `orders` SET `is_canceled`= 1 WHERE `id` = order_id AND `is_canceled` = 0;

END$$


-- Table structure for table category


CREATE TABLE `category` (
  `id` int NOT NULL,
  `is_deleted` int DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table category

INSERT INTO `category` (`id`, `is_deleted`, `type`) VALUES
(1, 0, 'Bakery'),
(5, 0, 'Meals'),
(6, 0, 'Cake'),
(7, 0, 'Ice-cream');


-- Triggers orders_log

DELIMITER $$
CREATE TRIGGER `TR_after_orders_log_insert` AFTER INSERT ON `orders_log` FOR EACH ROW BEGIN

DELETE FROM orders WHERE  item_id = NEW.item_id AND user_id = NEW.user_id AND ordered_at = NEW.ordered_at AND is_canceled =0;

END
$$
DELIMITER ;