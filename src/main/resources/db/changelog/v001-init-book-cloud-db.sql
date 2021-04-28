--liquibase formatted sql

--changeset subscription-executor:create-table-subscription-executor
CREATE TABLE `book` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR (255) NOT NULL,
    `type` VARCHAR (25) NOT NULL,
    `description` VARCHAR (1000),
    `publish_at` DATETIME,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `author` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR (255) NOT NULL,
    `last_name` VARCHAR (255) NOT NULL,
    `birth_day` DATETIME,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `book_author` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `book_id` INT NOT NULL,
    `author_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_book_author_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
    CONSTRAINT `fk_book_author_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
);