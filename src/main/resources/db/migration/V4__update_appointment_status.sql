UPDATE `appointment`
SET `status` = 'Completed'
WHERE `status` = 'Past';

ALTER TABLE `appointment`
    MODIFY COLUMN `status` ENUM('Upcoming', 'Completed', 'Cancelled');
