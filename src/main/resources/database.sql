CREATE TABLE `blog_category` (
  `blog_category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(120) NOT NULL,
  `seo_title` varchar(200) NOT NULL,
  `seo_meta_desc` varchar(200) NOT NULL,
  `status` char(8) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_category_id`)
);

CREATE TABLE `blog_category_images` (
  `blog_category_images_id` int NOT NULL AUTO_INCREMENT,
  `blog_category_id` int NOT NULL,
  `category_image_name` varchar(250) DEFAULT NULL,
  `category_image_url` varchar(250) DEFAULT NULL,
  `status` char(8) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_category_images_id`),
  KEY `blog_category_id` (`blog_category_id`),
  CONSTRAINT `blog_category_images_ibfk_1` FOREIGN KEY (`blog_category_id`) REFERENCES `blog_category` (`blog_category_id`)
);

CREATE TABLE `blogs` (
  `blog_id` int NOT NULL AUTO_INCREMENT,
  `blog_category_id` int NOT NULL,
  `author_name` varchar(120) DEFAULT NULL,
  `descriptions` longtext NOT NULL,
  `blog_title` varchar(200) NOT NULL,
  `seo_title` varchar(80) not null,
  `seo_meta_desc` varchar(200) NOT NULL,
  `status` char(8) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_id`),
  KEY `blog_category_id` (`blog_category_id`),
  CONSTRAINT `blogs_ibfk_1` FOREIGN KEY (`blog_category_id`) REFERENCES `blog_category` (`blog_category_id`)
);

CREATE TABLE `blog_images` (
  `blog_image_id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `blog_image_name` varchar(120),
  `download_url` varchar(250) DEFAULT NULL,
  `image_type` char(12) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_image_id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `blog_images_ibfk_2` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`)
);

CREATE TABLE `blog_comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `customer_id` int NOT NULL,
  `ip_address` char(20) DEFAULT NULL,
  `status` char(8) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `blog_comments_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`)
);

CREATE TABLE `blog_history` (
  `blog_history_id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `descriptions_history` longtext NOT NULL,
  `blog_title_history` varchar(200) NOT NULL,
  `blog_banner_history` blob,
  `blog_banner_history_url` varchar(250) DEFAULT NULL,
  `blog_sub_image_history` blob,
  `blog_sub_image_history_url` varchar(250) DEFAULT NULL,
  `seo_meta_desc_history` varchar(200) NOT NULL,
  `status` char(8) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_history_id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `blog_history_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`)
);

create table user_registration (
user_id int not null PRIMARY KEY AUTO_INCREMENT,
user_name varchar(120) not null,
email varchar(250) not null UNIQUE,
mobile varchar(12) not null UNIQUE,
password varchar(250) not null,
status char(12) DEFAULT Null,
created_by INT DEFAULT NULL,
modified_by INT DEFAULT NULL,
created_date timestamp NOT NULL DEFAULT  current_timestamp(),
modified_date timestamp NULL DEFAULT NULL ON Update current_timestamp()
);

create table api_credentials(
user_id int not null primary key auto_increment,
username varchar(120) not null,
password varchar(250) not null,
status varchar(8) not null,
created_by int DEFAULT NULL,
modified_by int DEFAULT NULL,
created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
modified_date timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

insert into api_credentials (username , password) values("user","password");

