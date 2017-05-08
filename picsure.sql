-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema picsure
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `picsure` ;

-- -----------------------------------------------------
-- Schema picsure
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `picsure` DEFAULT CHARACTER SET utf8 ;
USE `picsure` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(150) NOT NULL,
  `street2` VARCHAR(150) NULL,
  `city` VARCHAR(150) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  `zip` INT(5) NOT NULL,
  `country` VARCHAR(45) NOT NULL DEFAULT '\"United State of America\"',
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `address` (`id` ASC);


-- -----------------------------------------------------
-- Table `store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store` ;

CREATE TABLE IF NOT EXISTS `store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(25) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `addressId` INT NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_store_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `store` (`id` ASC);

CREATE UNIQUE INDEX `phone_UNIQUE` ON `store` (`phone` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `store` (`email` ASC);

CREATE INDEX `fk_store_address1_idx` ON `store` (`addressId` ASC);


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fName` VARCHAR(45) NOT NULL,
  `lName` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `user` (`username` ASC);

CREATE UNIQUE INDEX `phone_UNIQUE` ON `user` (`phone` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `user` (`email` ASC);


-- -----------------------------------------------------
-- Table `inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory` ;

CREATE TABLE IF NOT EXISTS `inventory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `storeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_inventory_store1`
    FOREIGN KEY (`storeId`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_inventory_store1_idx` ON `inventory` (`storeId` ASC);


-- -----------------------------------------------------
-- Table `equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `equipment` ;

CREATE TABLE IF NOT EXISTS `equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `make` VARCHAR(45) NULL,
  `model` VARCHAR(150) NOT NULL,
  `type` VARCHAR(150) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `image` VARCHAR(255) NOT NULL,
  `rate` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `equipment` (`model` ASC);


-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation` ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `createdDate` DATETIME NOT NULL,
  `store_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservation_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservation_user1_idx` ON `reservation` (`userId` ASC);

CREATE INDEX `fk_reservation_store1_idx` ON `reservation` (`store_id` ASC);


-- -----------------------------------------------------
-- Table `cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart` ;

CREATE TABLE IF NOT EXISTS `cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cart_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_cart_user1_idx` ON `cart` (`userId` ASC);


-- -----------------------------------------------------
-- Table `inventoryItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventoryItem` ;

CREATE TABLE IF NOT EXISTS `inventoryItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `equipmentId` INT NOT NULL,
  `inventoryId` INT NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_inventoryItem_equpiment1`
    FOREIGN KEY (`equipmentId`)
    REFERENCES `equipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventoryItem_inventory1`
    FOREIGN KEY (`inventoryId`)
    REFERENCES `inventory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_inventoryItem_equpiment1_idx` ON `inventoryItem` (`equipmentId` ASC);

CREATE INDEX `fk_inventoryItem_inventory1_idx` ON `inventoryItem` (`inventoryId` ASC);


-- -----------------------------------------------------
-- Table `reservationItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservationItem` ;

CREATE TABLE IF NOT EXISTS `reservationItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inventoryItemId` INT NOT NULL,
  `reservationId` INT NOT NULL,
  `timeOut` DATETIME NOT NULL,
  `timeIn` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservationItems_inventoryItem1`
    FOREIGN KEY (`inventoryItemId`)
    REFERENCES `inventoryItem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservationItems_reservation1`
    FOREIGN KEY (`reservationId`)
    REFERENCES `reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservationItems_inventoryItem1_idx` ON `reservationItem` (`inventoryItemId` ASC);

CREATE INDEX `fk_reservationItems_reservation1_idx` ON `reservationItem` (`reservationId` ASC);


-- -----------------------------------------------------
-- Table `cartItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cartItem` ;

CREATE TABLE IF NOT EXISTS `cartItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cartId` INT NOT NULL,
  `inventoryItemId` INT NOT NULL,
  `timeOut` DATETIME NOT NULL,
  `timeIn` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cartItems_cart1`
    FOREIGN KEY (`cartId`)
    REFERENCES `cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cartItems_inventoryItem1`
    FOREIGN KEY (`inventoryItemId`)
    REFERENCES `inventoryItem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_cartItems_cart1_idx` ON `cartItem` (`cartId` ASC);

CREATE INDEX `fk_cartItems_inventoryItem1_idx` ON `cartItem` (`inventoryItemId` ASC);


-- -----------------------------------------------------
-- Table `lister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lister` ;

CREATE TABLE IF NOT EXISTS `lister` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `storeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lister_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lister_store1`
    FOREIGN KEY (`storeId`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_lister_user1_idx` ON `lister` (`userId` ASC);

CREATE INDEX `fk_lister_store1_idx` ON `lister` (`storeId` ASC);

SET SQL_MODE = '';
GRANT USAGE ON *.* TO picSureAdmin;
 DROP USER picSureAdmin;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'picSureAdmin' IDENTIFIED BY 'picSureAdmin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'picSureAdmin';
GRANT SELECT, INSERT, TRIGGER ON TABLE * TO 'picSureAdmin';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `country`, `latitude`, `longitude`) VALUES (1, '1701 Wynkoop', NULL, 'Denver', 'CO', 80202, 'USA', 39.7391536, -104.9847034
-104.9847034
-104.9847034);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `country`, `latitude`, `longitude`) VALUES (2, '1935 North Logan St', 'Ph 1', 'Denver', 'CO', 80203, 'USA', 39.746910, -104.982303);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `country`, `latitude`, `longitude`) VALUES (3, '4380 S. Monaco St', 'Unit 2105', 'Denver', 'CO', 80237, 'USA', 39.634528, -104.911013);

COMMIT;


-- -----------------------------------------------------
-- Data for table `store`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `store` (`id`, `name`, `phone`, `email`, `addressId`, `active`) VALUES (1, 'UPic', '303-555-0137', 'BigRob@ilovecameras.com', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `user` (`id`, `fName`, `lName`, `username`, `password`, `phone`, `email`, `admin`) VALUES (1, 'Seth', 'Thomas', 'admin', 'p', '555-555-5555', 'swthomas@gmail.com', 1);
INSERT INTO `user` (`id`, `fName`, `lName`, `username`, `password`, `phone`, `email`, `admin`) VALUES (2, 'Daniel', 'Balarezo', 'danrezo', 'password', '305-484-8911', 'drezo@me.com', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `inventory` (`id`, `storeId`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `equipment`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (1, 'Canon', 'Rebel T7i', 'DSLR', 'Take your photos to the next level with the image quality and near-instant autofocus of the EOS Rebel T7i camera. A 45-point all cross-type AF system* and optical viewfinder lets you stay ready for that unforgettable moment with virtually no lag between what you see and what you get when you press the shutter. Fast and accurate Dual Pixel CMOS AF with phase-detection locks focus quickly and accurately to help make sure you don\'t miss a great shot. A 24.2 Megapixel CMOS (APS-C) sensor helps you capture special moments from vacations to family events in brilliant color, detail and vibrancy for beautifully memorable photos and videos. With a maximum ISO of 25600, you can capture photos in low light with minimal chance of blur. Built-in Wi-Fi®**, NFC*** and Bluetooth®^ connectivity lets you share these precious moments with friends and family and upload them directly to the web.', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (2, 'Canon', 'EOS 77D', 'DSLR', 'Let your creativity take control with the EOS 77D camera. Featuring an optical viewfinder with a 45-point all cross-type AF system* and fast, accurate Dual Pixel CMOS AF with phase-detection, it helps you capture the action right as it happens. Alongside the viewfinder, the EOS 77D\'s extensive, customizable controls and brilliant image quality help you get the photo looking exactly how you want it. Capture the vibrant colors and fine details of inspirational scenes with the 24.2 Megapixel CMOS (APS-C) Sensor. Without missing a beat, check and change your settings with the top-mounted LCD screen and rear Quick Control dial. When you\'re satisfied with your work, built-in Wi-Fi®**, NFC*** and Bluetooth®^ technology lets you easily share your favorite photos and videos. Creative control and innovative customizability comes with the EOS 77D.', '', 12.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (3, 'Canon', 'Rebel T5', 'DSLR', 'Perfect for families, budding photo enthusiasts and first-time SLR users alike, the EOS Rebel T5 makes it easy to capture movies and photos that are nothing short of dazzling. It features a powerful 18.0 Megapixel CMOS (APS-C) image sensor and Canon\'s DIGIC 4 Image Processor for easy recording of HD video and high-resolution photos and has a huge 3.0-inch LCD screen for Live View recording and review. With a 63-zone, Dual-layer metering system, an expanded ISO range for outstanding operation in less-than-perfect light, shooting modes like Scene Intelligent Auto to take the guesswork out of complex shots plus creative options like Canon\'s Basic+ function and Creative Auto, the EOS Rebel T5 is ready for anything. With a helpful Feature Guide, rugged, lightweight construction and proven Canon design, the EOS Rebel T5 makes EOS SLR photography faster and easier than ever!', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (4, 'Canon', 'EOS 5D', 'DSLR', 'The EOS 5D Mark IV camera builds on the powerful legacy of the 5D series, offering amazing refinements in image quality, performance and versatility. Canon\'s commitment to imaging excellence is the soul of the EOS 5D Mark IV. Wedding and portrait photographers, nature and landscape shooters, as well as creative videographers will appreciate the brilliance and power that the EOS 5D Mark IV delivers. Superb image quality is achieved with Canon\'s all-new 30.4 Megapixel full-frame sensor, and highly-detailed 4K video is captured with ease. Focus accuracy has been improved with a refined 61-point AF system and Canon\'s revolutionary Dual Pixel CMOS AF for quick, smooth AF for both video and Live View shooting. Fast operation is enhanced with Canon\'s DIGIC 6+ Image Processor, which provides continuous shooting at up to 7.0 fps*. Built-in Wi-Fi®**, GPS*** and an easy-to-navigate touch-panel LCD allow the camera to become an extension of you. When quality matters, the EOS 5D Mark IV helps deliver results to inspire even the most discerning imagemaker.', '', 10.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (5, 'Canon', 'EOS 7D', 'DSLR', 'Paired with the Wi-Fi Adapter W-E1 to bring the convenience of Wi-Fi capabilities to the camera, along with providing a change in perspective related to sight and sound with the EF-S 18-135mm IS USM lens with NANO technology for fast, smooth and near-silent operation when capturing both stills and video, the EOS 7D Mark II EF-S 18-135mm IS USM Lens Wi-Fi Adapter Kit provides the resources you need to deliver the photo and video experience you desire.\n\nThe Wi-Fi Adapter W-E1 adds the convenience of Wi-Fi® compatibility* to the features you can now utilize with the EOS 7D Mark II camera. By simply inserting the new Wi-Fi Adapter** W-E1 into the SD card slot, you can take advantage of Wi-Fi®* capabilities such as remote shooting or viewing and transferring still images and movies (MP4) using a compatible smart device (with the Camera Connect app*) or a computer (using EOS Utility).\n\nIn addition to this new Wi-Fi Adapter, the kit includes the EF-S 18–135mm f/3.5-5.6 IS USM lens. This gives you the full feature set of the EOS 7D Mark II with convenient Wi-Fi® compatibility, along with a lens boasting NANO USM AF for fast, fluid and nearly silent operation during video and still shooting, so you can get the shots you want while being as discreet as you need to be.', '', 12.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (6, 'Canon', 'EOS M6', 'Mirrorless', 'The EOS M6 is an interchangeable-lens camera that puts advanced technologies like Dual Pixel CMOS AF with phase-detection in a compact and lightweight size. Ready at hand to help capture photos and videos the instant the moment happens, it focuses quickly and precisely to help ensure you catch the shot you want. The 24.2 Megapixel CMOS (APS-C) Sensor helps ensure your results are clear, incredibly detailed and vibrantly colorful. Once you\'ve captured your most precious memories, you can easily share them with friends, family and the world through built-in wireless connectivity including Wi-Fi®*, NFC** and Bluetooth®***. Advanced technology and stunning image quality combine inside the stylish, highly portable EOS M6.', '', 9.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (7, 'Canon', 'EOS M5', 'Mirrorless', 'Compact. Powerful. Fast. The EOS M5 features the brilliant image quality you can expect from the EOS line of cameras, in a lightweight, portable design with fast autofocus and processing to help make sure you don\'t miss that unforgettable shot. Whether you\'re navigating the tight alleys of an old European city or enjoying the view after a strenuous hike, the EOS M5 will let you take advantage of every photo opportunity, more conveniently. Inside its rugged frame is a 24.2 Megapixel CMOS (APS-C) sensor that\'s able to capture sharp, high-resolution images even in low-light situations. Easy to keep on hand for virtually every situation, it\'s also fast enough to capture the moment thanks to Dual Pixel CMOS AF, which quickly and accurately locks onto your subject. A high-resolution electronic viewfinder and a 3.2-inch tilt-type LCD monitor offer versatility in lining up the angle you want. Equipped with these impressive features and a whole lot more, the EOS M5 can be ideal for advanced photographers, enthusiasts and anyone looking to capture and save the best moments from everyday life and beyond.', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (8, 'Canon', 'EOS  M3', 'Mirrorless', 'Designed to inspire, the EOS M3 digital camera brings true EOS performance and image quality to a compact, stylish and elegant package. A pleasure to operate, with the sophistication to create stunning still and moving images, the EOS M3 is an ideal EOS for many applications, such as portraiture, landscape, travel and everything in between. With its large 24.2 Megapixel APS-C sized CMOS sensor and DIGIC 6 Image Processor, the EOS M3 captures high-resolution images and Full HD movies suitable for most any application. Its lightweight, mirrorless body and interchangeable lenses add a whole new level of portability to the EOS system without compromising performance. In addition to a number of dedicated EF-M Lenses, the EOS M3 is compatible with a range of EF* and EF-S* Lenses and Speedlites, has an optional shoe-mounted electronic viewfinder, wireless capabilities, plus numerous features designed for fun and fast image capture. The EOS M defies the assumption that compact means compromise, with performance and features photographers and moviemakers can rely on to help bring their creative ideas to life.', '', 5.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (9, 'Nikon', 'D7500', 'DSLR', 'Born from a desire for flagship performance and innovation in a more compact and streamlined connected camera, the D7500 delivers the game-changing resolution, ISO range, image processing and energy efficiency of the award-winning D500 in an enthusiast-level DSLR. Simply put, the D7500 is built to outperform any camera in its class with top-tier image quality, blazing speed, flawless autofocus, 4K Ultra HD video and pro-grade creative tools—all in a comfortable, rugged design. This is a camera for the new generation of creators.', '', 10.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (10, 'Nikon', 'D5', 'DSLR', 'What determines when Nikon releases a full-frame flagship camera? When technological innovation demands it. Introducing the D5, an FX-format DSLR that makes the impossible possible. Huge advancements in sensor design, autofocus, metering and image processing result in thrilling new capabilities—low light shooting all the way to ISO 102,400 (expandable to an unheard of ISO 3,280,000), precise AF detection and tracking across that entire ISO range, regardless of your subject\'s speed or direction changes, blazing fast 12 fps continuous shooting, 4K UHD video and, of course, image quality that captures the hearts and minds of your viewers. This is not iteration, friends. This is innovation...spectacular innovation. How will it change the way you shoot?', '', 20.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (11, 'Nikon', 'D5500', 'DSLR', 'Meet the new DX flagship, the Nikon D500. At first glance, it may seem unimposing—but contained within a streamlined camera body is a veritable powerhouse of processing power and technological advances. The D500 is ready to go wherever your passion leads you, capturing everything with stunning clarity, speed and resolution. From busy, low-light cityscapes to thrilling wildlife scenes and fast action shots, the D500 is the ideal companion to your wanderlust. Marvel at the clarity of its cinematic 4K UHD video. Be amazed at its ruggedness and versatility. And, once you’ve captured your gorgeous photos, admire them on the D500’s high resolution tilt touchscreen display and share them via the built-in SnapBridge (Wi-Fi® + Bluetooth) capabilities. No matter what you shoot, you can be sure that the D500 will be up to the task, time and time again.', '', 10.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (12, 'Nikon', 'D750', 'DSLR', 'For those who find inspiration everywhere, who switch between stills and video without missing a beat, who want the look only a full-frame DSLR can achieve and who love sharing their shots, the D750 is the tool to unleash your artistry. With features inspired by D4S and D810, the D750 brings dazzling image quality, cinematic video capabilities and pro-inspired handling in a nimble design with a tilting Vari-angle LCD and built-in Wi-Fi connectivity. Enthusiasts upgrading from a DX-format DSLR will marvel at the D750\'s full-frame performance. Pros seeking a primary or secondary camera for fast-paced shoots will appreciate the D750\'s familiar handling and speed. And filmmakers looking for a compact DSLR to bring a production to life or to capture B-Roll will find the D750 a perfect fit. The D750 is a thrilling centerpiece of an exceptional imaging system', '', 12.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (13, 'Nikon', 'D3300', 'DSLR', 'Life is full of surprising, joyful moments—moments worth remembering. The D3300 makes it fun and easy to preserve those moments in the lifelike beauty they deserve: stunning 24.2-megapixel photos and 1080p Full HD videos with tack-sharp details, vibrant colors and softly blurred backgrounds. With its included zoom lens, the new ultra-compact AF-S DX NIKKOR 18-55mm f/3.5-5.6G VR II, the D3300 is a small and light HDSLR and easy to use, too. Like sharing photos? The D3300 photos can appear instantly on your compatible smartphone or tablet for easy sharing with the optional WU-1a Wireless Adapter*! Whether you\'re creating high-resolution panoramas, adding artistic special effects or recording HD video with sound, the D3300 will bring you endless joy, excitement and memories—just like the special moments of your life.', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (14, 'Nikon', 'Coolpix B500', 'Compact', 'The COOLPIX B500 feels great in your hands, whether you\'re zooming in with its super telephoto NIKKOR glass lens or recording 1080p Full HD video using the flip-up LCD. And it takes all the work out of shooting beautiful photos and videos with its 16 megapixel low-light sensor, Lens-Shift Vibration Reduction (VR), easy menus and controls, fun creative effects and outstanding automatic operation. Plus, the whole time you\'re shooting, the COOLPIX B500 can be easily and seamlessly connected to a compatible smartphone through Bluetooth® low energy (BLE) technology for instant photo sharing and remote camera control. Simply brilliant', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (15, 'Nikon', 'CoolpixP900', 'Compact', 'The zoom power of the COOLPIX P900 is nothing short of spectacular. This is 83x (2000mm equivalent)  optical zoom—more than any Nikon COOLPIX yet. Advanced shooters will appreciate the outstanding image quality and DSLR styling—a sure grip, a swiveling Vari-angle display, a high-resolution Electronic Viewfinder that turns on automatically when lifted to your eye, even a PSAM mode control dial. Those who simply want great photos and Full HD videos without any fuss will appreciate the point-and-shoot ease and long battery life—up to 360 shots per charge! And everyone will appreciate the built-in Wi-Fi® and Near Field Communication technology (NFC)** connectivity which wirelessly connects the COOLPIX P900 to a compatible smartphone or tablet for instant photo sharing and remote camera control. Change the way you see, capture and share the world.\n', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (16, 'Nikon', 'CoolpixB700', 'Compact', 'The first time you hold the COOLPIX B700, you\'ll know it\'s an exceptional camera. Its DSLR-style design, including a PSAM mode dial, puts key controls where you want them and feels great in your hands. The first time you zoom in with it, you\'ll wonder if any shot will ever be out of reach again. Subjects barely visible to the naked eye come into tight focus, held steady by cutting-edge Vibration Reduction technology. Use that power to capture stunning 20.2 megapixel RAW images, 4K Ultra High Definition (UHD) videos with stereo sound or 5 FPS high-speed sequences in nearly any light. Plus, the whole time you\'re shooting, the COOLPIX B700 can be connected to a compatible smartphone through Bluetooth® Low Energy (BLE) technology for instant photo sharing and remote camera control. This is more than a compact camera—it\'s a creative powerhouse.', '', 5.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (17, 'Sony', 'a9', 'DSLR', 'This mechanism-free camera can deliver a-class-above performance conventional mechanical SLRs have only sought. For example, this system realizes a totally blackout-free viewfinder while conventional systems can only try to reduce blackout time. A high-speed, vibration-free, silent Anti-Distortion shutter vastly extends the range of shooting situations while a mechanical system can only aim for lower vibration and quieter shutter release sound. α9 provides continuous tracking of moving subjects for foolproof AF/AE while traditional SLRs can only challenge such AF/AE performance improvements. Moreover, α9 allows its viewfinder to show not only images of the subject — available on conventional systems — but also the final image of a shot. Now, thanks to a new image sensor that superbly manages all these roles, you can surpass all expectation with α9.', '', 20.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (18, 'Sony', 'aR7II', 'DSLR', 'Now, even more comprehensive quality enters the picture. With the world’s first2 back-illuminated 35 mm full-frame CMOS image sensor with 42.4 megapixels3, the Sony α7R II takes image resolution, sensitivity (up to ISO 102,4004) and speedy response to new heights. The Fast Hybrid AF system’s dense extra-wide focal plane phase-detection AF coverage keeps a subject in sharp focus entirely throughout the frame, while 5-axis image stabilization reduces blur which otherwise tends to affect handheld shots. High resolution is further enhanced by 4K movie recording featuring full pixel readout without pixel binning. With so much insight packed into such a compact form, it’s clear that there is more to life than meets the naked eye', '', 18.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (19, 'Sony', 'a7e', 'DSLR', 'With 24.3 megapixels, the α7’s 35mm full-frame Exmor™ sensor rivals that of leading DSLRs. And with Sony’s latest BIONZ X processor and most advanced autofocus, the α7 offers outstanding detail, sensitivity, and quality. Get ready for performance that will propel your photography to new heights.', '', 15.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (20, 'Sony', 'a6300', 'Mirrorless', 'The α6300 is the flagship APS-C mirrorless interchangeable-lens camera from Sony featuring the world’s fastest2 0.052-s AF with the most3 phase-detection AF points (4253), Exmor® CMOS sensor with 24.2 effective megapixels17, extra-wide ISO 100-512004 sensitivity range, enhanced 4K movie recording, and XGA OLED Tru-Finder™.', '', 10.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (21, 'Sony', 'a6000', 'Mirrorless', 'Shoot better with the α6000: superb 24MP quality, quick autofocus—and very portable. Add an OLED Tru-Finder™, manual controls, and a range of lenses, for a superior alternative to bulky DSLRs.', '', 9.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (22, 'Sony', 'a5100', 'Mirrorless', 'Elevate your photography with phenomenal focus. A Fast Hybrid AF system with 179 points—plus intuitive touch focus—give your shots professional polish.', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (23, 'Olympus', 'OMD E-M1 MkII', 'Mirrorless', 'Introducing the OM-D E-M1 Mark II, an advanced system of innovative technology and features designed to forever change your photography. Up to 18 frames per second sequential shooting with precision C-AF Tracking. 121 Cross-Type On-Chip Phase Detection AF points. Up to 5.5 shutter speed steps of compensation with powerful in-body image stabilization. Plus a 50MP High-Res Shot Mode. It’s all enclosed in a magnesium alloy weatherproof body that weighs a mere 600 grams. Paired with the superior resolution of M.Zuiko PRO lenses, the E-M1 Mark II will deliver brilliant imagery that’s coveted by professionals everywhere.', '', 13.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (24, 'Olympus', 'OMD E-M1', 'Mirrorless', 'Get a grip on the OM-D E-M1 and experience this extraordinary photographic tool. Full magnesium alloy body construction, an intuitive control layout, an interactive electronic viewfinder…we could go on and on. In fact, we will. So many incredible features, it’s miles ahead of the technology curve.', '', 11.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (25, 'Olympus', 'OMD E-M5 MkII', 'Mirrorless', 'The E-M5 Mark II sits squarely in the sweet spot for the creative shooter who needs a portable system with all the power to realize their creative pursuits. High-speed sensor and image processor. Large interactive EVF. Touch and Swivel LCD monitor with convenient variable angle positioning. Lightning-fast autofocus and Manual Focus options that nail everything from serious fast sports action to dramatic portraits. Plus, its all metal body is sealed for protection against dust and moisture for shooting in extreme conditions.', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (26, 'Olympus', 'OMD E-M10 MkII', 'Mirrorless', 'Get serious without getting complicated. Whether you’re new to photography or an experienced enthusiast looking to maximize your creativity, the E-M10 Mark II is for you. The sophisticated but easy to use E-M10 Mark II delivers flawless image quality, best-in-class 5-Axis Image Stabilization and intuitively placed controls to help you find your own style. It’s also compact and easy to carry everywhere you go, unobtrusive enough for discreet street shooting but versatile enough for anything from long-range wildlife shots to miniature macro masterpieces.', '', 10.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (27, 'Olympus', 'OMD E-M10', 'Mirrorless', 'You don’t have to choose between powerful and portable. The OM-D E-M10 gives you pro-style performance in a camera you can actually carry. Its thoughtful design centers on a solid, premium build that feels substantial in your hands, while its ergonomic shape and nimble control scheme allow you to focus on getting the perfect shot. The E-M10 also boasts a powerful TruePic™ VII image processor, interactive EVF, warp-speed AF system, built-in flash and WiFi, and a slew of creative tools.', '', 8.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (28, 'Panasonic', 'Lumix GH4', 'Mirrorless', 'The Panasonic LUMIX DMC-GH4 continues to evolve delivering professional quality video. Take advantage of 4K (Cinema 4K: 4096x2160 / 24 fps and QFHD 4K: 3840x2160 / up to 30 fps) video recording in MOV/MP4, or ultra high bitrate video recording at 200 Mbps (ALL-Intra) or 100 Mbps (IPB) without a recording time limit.*1 *2 Discover a variety of high-end functions for professional video recording.', '', 15.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (29, 'GoPro', 'Hero5', 'Action Camera', 'HERO5 Session combines 4K video, one-button simplicity and voice control all in a small, waterproof design.', '', 10.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (30, 'GoPro', 'Hero Session', 'Action Camera', 'HERO Session™ is ready for any adventure with its one-button simplicity and compact waterproof design.\n\n', '', 7.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (31, 'GoPro', 'Karma', 'Drone', 'Karma captures amazingly smooth GoPro footage in the air, handheld or body mounted. \n', '', 35.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (32, 'DJI', 'Mavic PRO', 'Drone', 'Portable yet powerful, the Mavic Pro is your personal drone, ready to go with you everywhere.', '', 35.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (33, 'DJI', 'Phantom 4 PRO', 'Drone', 'All-new DJI Phantom camera with 1-inch 20MP Exmor R CMOS sensor, longer flight time and smarter features.\n', '', 50.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (34, 'Bogen/PM', '3249B monopod / 4876 head', 'Monopod', 'he Manfrotto 680B Pro Monopod is commonly used for Home, Landscape/scenery, Night shots, Portraits, Sports/action, Sports photography, Stabilization, Travel, Video, Weddings, Wildlife and more.The Manfrotto 680B Pro Monopod is most used by customers who consider themselves to be a Enthusiast, Photo enthusiast, Semi-pro photographer among others.The Manfrotto 680B Pro Monopod is popular because customers like the following qualities of the Manfrotto 680B Pro Monopod: Compact, Lightweight, Opens quickly and Strong construction', '', 2.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (35, 'Manfrotto', '3011 tripod / 804RC2 head', 'Tripod', 'Lightweight, sturdy and portable, this up-to-date basic tripod is one of the easiest of all Manfrotto tripods to handle. Designed for 35mm and light medium format cameras (digital or conventional) or video camcorders.', '', 2.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (36, 'Manfrotto', '190XPRO3 tripod / BHQ2 head', 'Tripod', 'The MK190XPRO3-BHQ2 Aluminum Tripod from Manfrotto pairs the MT190XPRO3 aluminum tripod legs with the MHXPRO-BHQ2 XPRO Ball Head with a 200PL quick release clamp and plate. The tripod can hold up to 15.4 lb, extend to 67.5\", and weighs 5.5 lb. The legs use the Quick Power Lock System that allows you to completely extend the tripod legs with just one hand. Once set up, you can use the tripod\'s rapid center column in standard vertical mode or swing it into horizontal position like a boom with Manfrotto\'s 90° Column feature.', '', 2.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (37, 'Manfrotto', '3001N tripod / 701HDV video head', 'Tripod', '3001N TRIPOD Lightweight, compact and sturdy, tripod ideal for 35mm SLR cameras, especially when on the move outdoors. \n\nQuick release micro fluid head 3130 A lightweight fluid head with adjustable pan bar and quick-release camera plate with secondary security. Ideal for cameras up to 8.9lbs in weight. The head has smooth fluid movements, pan and tilt locks. The adjustable pan bar can be positioned either on the left or right side. ', '', 2.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (38, 'Manfrotto', 'MVT535AQ tripod / 504HD fluid video head', 'Tripod', 'The Manfrotto 504HD Head w/546B 2-Stage Aluminum Tripod System combines the innovative and stylish 504HD Fluid Video Head with the 2-stage aluminum 546B Pro Video Tripod--creating an ideal combo of affordability, stability, and smooth, precise control. This compact, versatile system was designed with independent video and DSLR shooters in mind, and provides the level of ergonomics and support required by today\'s small crews and one-man bands.', '', 2.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (39, 'Canon', 'EF-S 18-55 f/3.5-5.6', 'Lens', 'The EF-S 18-55mm f/3.5-5.6 III is a standard zoom lens perfect for those wanting to start exploring DSLR photography. Good for most shooting situations. Aspheric Lens Element : Produces images that are distortion corrected, consistent image quality over the frame is ensured. High Speed Auto Focus : DC autofocus motor combined with a high-speed CPU and improved AF algorithm.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (40, 'Canon', 'EF-S 18-135 f/3.5-5.6 IS', 'Lens', 'The Canon EF-S 18-135mm f/3.5-5.6 IS standard zoom lens offers a winning combination of size, range and features and is a perfect complement to APS-C cameras. With high-quality optics, dedicated image stabilization and more, this new lens promises to be a favorite for EOS users.\n', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (41, 'Canon', 'EF-S 18-200 f/3.5-5.6 IS', 'Lens', 'The Canon 2752B002 EF-S 18-200mm f/3.5-5.6 IS Telephoto Zoom Lens is compact, lightweight and has a wide magnification range. It features a wide focal length range from normal to telephoto equivalent to 29-320mm in the 35mm format. It features an Optical Image Stabilizer for up to 4-stops of effective correction even at full zoom. Since Canon\'s optical image stabilization system is in the lens you can see the stabilized images through the viewfinder. And the circular aperture can give beautiful background blur effects. What\'s in the box: Canon EF-S 18-200mm f/3.5-5.6 IS Autofocus Lens,Front & Rear Lens Caps, 1-Year Warranty.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (42, 'Canon', 'EF 35mm f/1.4 L', 'Lens', 'Focal Length & Maximum Aperture: 35mm f/1.4 Lens Construction: 14 elements in 11 groups Diagonal Angle of View: 63° Focus Adjustment: AF with full-time manual Closest Focusing Distance: 0.92 ft./0.28m Filter Size: 2.8 in./72mm diameter Max. Diameter x Length, Weight: 3.2 x 4.2 in., approx. 26.8 oz. / 80.4 x 105.5mm, approx. 760g', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (43, 'Canon', 'EF 50mm f/1.4', 'Lens', 'The 50mm f/1.4 standard lens is a terrific choice for both casual and professional photographers. The lens is outfitted with two high-refraction lens elements and new Gaussian optics, which combine to eliminate astigmatism and suppress astigmatic difference.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (44, 'Canon', 'EF 100mm f/2.8 L IS Macro', 'Lens', 'The Canon EF 100mm f/2.8L IS USM Macro Lens is the first mid-telephoto macro lens to include Canon\'s sophisticated Image Stabilization. With the highest quality optics available, combined with near-silent Ultrasonic focusing and life-size close-up capabilities without an adapter, the EF 100mm f/2.8L Macro IS USM is simply unrivaled. ', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (45, 'Nikkor', 'AF-S 18-55mm f/3.5-5.6 G VR DX', 'Lens', 'Life is full of interesting views, and you need a lens that can catch them all. Whether you\'re shooting wide group photos or portraits, close-ups or landscapes, photos or HD videos, the Nikon AF-S DX NIKKOR 18-55mm f/3.5-5.6G VR II Lens delivers the sharpest, most color-rich results imaginable.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (46, 'Nikkor', 'AF-S 18-140mm f/3.5-5.6 G VR DX', 'Lens', 'If you\'re looking for an outstanding grab-and-go lens-the kind you\'ll keep on your camera for nearly every situation-check out the new Nikon AF-S DX NIKKOR 18-140mm f/3.5-5.6G ED VR Lens. Optimized to draw full potential from Nikons high-resolution DX-format image sensors, it delivers beautiful ultra-sharp photos and videos with softly blurred backgrounds. ', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (47, 'Nikkor', 'AF-S 18-200mm f/3.5-5.6 G VRII DX', 'Lens', 'New ultra-high ratio zoom lens AF-S DX NIKKOR 18-200mm f/3.5-5.6G ED VR II for use expressly with Nikon DX-format digital-SLR cameras.Dimensions (DxL) Approx. 3.0 x 3.8\" (7.62 x 9.65 cm).', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (48, 'Nikkor', 'AF-S 28mm f/1.8 G', 'Lens', 'Bring a dramatic wide-angle perspective to your still and HD video shooting. The AF-S NIKKOR 28mm f/1.8G’s fast maximum aperture lets you create in nearly any light—dusk to dawn, indoors or out—and offers outstanding depth-of-field control.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (49, 'Nikkor', 'AF-S 50mm f/1.8 G', 'Lens', 'Nikon AF-S Nikkor 50mm f/1.8G Lens, 58mm Snap-On Lens Cap, LF-4 Rear Lens Cap, HB-47 Bayonet Lens Hood for AF-S 50mm f/1.4G', '', 2.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (50, 'Nikkor', 'AF-S 24-70mm f/2.8 G ED', 'Lens', 'The classic standard zoom now featuring Vibration Reduction image stabilization, Nikon\'s AF-S NIKKOR 24-70mm f/2.8E ED VR Lens is a truly versatile wide-angle to short telephoto lens characterized by its constant f/2.8 maximum aperture and electromagnetic aperture mechanism', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (51, 'Sony', 'FE 35mm f/2.8 ZA', 'Lens', 'The small size, versatile 35mm focal length, and outstanding overall performance of this Carl Zeiss Sonnar T* lens make it an ideal match for compact 35mm full frame format E-mount bodies. ', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (52, 'Sony', 'FE 55mm f/1.8 ZA', 'Lens', 'A bright lens like this is also an advantage when shooting indoors or outdoors in low light. Linear motor driven internal focusing is smooth and quiet, and a dust and moisture resistant design ensures consistently reliable performance.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (53, 'Sony', 'FE 16-35mm f/4 ZA OSS', 'Lens', 'Vario-Tessar T FE 16-35mm F4 ZA OSS\n\n', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (54, 'Sony', 'FE 24-70mm f/2.8 GM', 'Lens', 'The Sony FE 24-70mm F2.8 GM Zoom Lens is the ultimate choice for those seeking the highest possible optical performance for portrait, travel and event photography or even simple everyday shooting.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (55, 'Sony', 'FE 28-70mm f/3.5-5.6 OSS', 'Lens', 'The Sony FE 28-70mm f/3.5-5.6 OSS Lens is a versatile, compact, and lightweight wide-angle to portrait-length zoom lens designed specifically for full-frame E-mount cameras. ', '', 4.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (56, 'Sony', 'FE 70-200mm f/4 G OSS', 'Lens', 'The small size, versatile 35mm focal length, and outstanding overall performance of this Carl Zeiss Sonnar T* lens make it an ideal match for compact 35mm full frame format E-mount bodies. ', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (57, 'Panasonic', 'Lumix G X Vario 35-100mm f/2.8 ASPH', 'Lens', 'The Panasonic LUMIX G X VARIO camera lens represents the next natural step in design and build for digital interchangeable zoom lenses. A first for the LUMIX G Series, the G VARIO constant with F2.8 zoom is suitable for professionals and enthusiasts alike.', '', 3.00);
INSERT INTO `equipment` (`id`, `make`, `model`, `type`, `description`, `image`, `rate`) VALUES (58, 'Panasonic', 'Lumix G Leica DG Nocticron 42.5mm f/1.2', 'Lens', 'The LUMIX G Leica DG Nocticron 42.5mm f/1.2 ASPH Power OIS Lens from Panasonic offers a 35mm focal length equivalent of 85mm and a fast aperture of f/1.2 making it well-suited for portraits. ', '', 5.00);

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `reservation` (`id`, `userId`, `createdDate`, `store_id`) VALUES (1, 2, '2017-05-02 12:20:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cart`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `cart` (`id`, `userId`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventoryItem`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (1, 1, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (2, 2, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (3, 3, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (4, 4, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (5, 5, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (6, 6, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (7, 7, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (8, 8, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (9, 9, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (10, 10, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (11, 11, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (12, 12, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (13, 13, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (14, 14, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (15, 15, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (16, 16, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (17, 17, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (18, 18, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (19, 19, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (20, 20, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (21, 21, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (22, 22, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (23, 23, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (24, 24, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (25, 25, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (26, 26, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (27, 27, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (28, 28, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (29, 29, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (30, 30, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (31, 31, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (32, 32, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (33, 33, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (34, 34, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (35, 35, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (36, 36, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (37, 37, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (38, 38, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (39, 39, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (40, 40, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (41, 41, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (42, 42, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (43, 43, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (44, 44, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (45, 45, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (46, 46, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (47, 47, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (48, 48, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (49, 49, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (50, 50, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (51, 51, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (52, 52, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (53, 53, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (54, 54, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (55, 55, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (56, 56, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (57, 57, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (58, 4, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (59, 7, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (60, 12, 1, 1);
INSERT INTO `inventoryItem` (`id`, `equipmentId`, `inventoryId`, `active`) VALUES (61, 15, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservationItem`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `reservationItem` (`id`, `inventoryItemId`, `reservationId`, `timeOut`, `timeIn`) VALUES (1, 1, 1, '2017-05-02 13:20:00', '2017-05-02 14:18:16');
INSERT INTO `reservationItem` (`id`, `inventoryItemId`, `reservationId`, `timeOut`, `timeIn`) VALUES (2, 41, 1, '2017-05-02 13:20:00', '2017-05-02 14:18:16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cartItem`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `cartItem` (`id`, `cartId`, `inventoryItemId`, `timeOut`, `timeIn`) VALUES (1, 1, 1, '2017-05-02 13:20:00', '2017-05-02 14:18:16');
INSERT INTO `cartItem` (`id`, `cartId`, `inventoryItemId`, `timeOut`, `timeIn`) VALUES (2, 1, 41, '2017-05-02 13:20:00', '2017-05-02 14:18:16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lister`
-- -----------------------------------------------------
START TRANSACTION;
USE `picsure`;
INSERT INTO `lister` (`id`, `userId`, `storeId`) VALUES (1, 1, 1);

COMMIT;

