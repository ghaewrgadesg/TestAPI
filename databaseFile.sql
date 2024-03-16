Drop database if exists InformationManagementSystem;
CREATE DATABASE InformationManagementSystem;
USE InformationManagementSystem;
-- -----------------------------------------------------
-- Table `InformationManagementSystem`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Users`(
    `id` INT AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `DOB` DATE NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE(`username`),
    UNIQUE(`email`)
);

-- -----------------------------------------------------
-- Table `InformationManagementSystem`.`Project`
-- -----------------------------------------------------
create table if not exists `Project`(
	`name` varchar(255) NOT NULL,
    `manager_email` varchar(255) NOT NULL,
    `start_date` date NOT NULL,
    `end_date` date NOT NULL,
    `description` text,
    Primary key (`name`),
	constraint `fk_manager_email`
		foreign key (`manager_email`) references Users(`email`)
		ON DELETE CASCADE
		ON UPDATE NO ACTION
);

    
-- -----------------------------------------------------
-- Table `InformationManagementSystem`.`Tasks`
-- -----------------------------------------------------
create table if not exists `Tasks`(
	`name` varchar(255) NOT NULL,
    `project_name` varchar(255) NOT NULL,
    `cost` int NOT NULL,
    `start_date` date NOT NULL,
    `end_date` date NOT NULL,
    `status` varchar(20) NOT NULL,
    `description` text,
    Primary key(`name`,`project_name`),
    constraint `fk_project_name`
    foreign key (`project_name`) references Project(`name`)
    ON DELETE CASCADE
	ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `InformationManagementSystem`.`ProjectMember`
-- -----------------------------------------------------
create table if not exists `ProjectMember`(
	`member_email` varchar(255) NOT NULL,
    `project_name` varchar(255) NOT NULL,
    Primary key(`member_email`,`project_name`),
    constraint `fk_pMember_member_email`
    foreign key (`member_email`) references Users(`email`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    constraint `fk_pMember_project_name`
	foreign key (`project_name`) references Project(`name`)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);
    
-- -----------------------------------------------------
-- Table `InformationManagementSystem`.`TaskAssignment`
-- -----------------------------------------------------
create table if not exists `TaskAssignment`(
	`member_email` varchar(255) NOT NULL,
    `project_name` varchar(255) NOT NULL,
    `task_name` varchar(255) NOT NULL,
    primary key(`task_name`, `member_email`, `project_name`),
    constraint `fk_aTMember_task_name` 
		foreign key (`task_name`) references Tasks(`name`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    constraint `fk_atMember_member_email`
    foreign key (`member_email`) references Users(`email`)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    constraint `fk_aTMember_project_name`
    foreign key (`project_name`) references Project(`name`)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);