CREATE TABLE `Num`  (
  `ip` varchar(255) NULL,
  `toy_type` varchar(255) NULL,
  `num` int NULL
);

CREATE TABLE `ToyInstance`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ip` varchar(25) NOT NULL,
  `toy_type` int NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ToyType`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_unique`(`name`)
);

ALTER TABLE `ToyInstance` ADD CONSTRAINT `fk_ToyInstance_ToyType_1` FOREIGN KEY (`toy_type`) REFERENCES `ToyType` (`id`);

CREATE VIEW `StatisticsView` AS SELECT ip, toy_type, COUNT(*) as num
FROM ToyInstance
GROUP BY ip, toy_type;

