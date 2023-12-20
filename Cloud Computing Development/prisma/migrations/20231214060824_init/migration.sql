-- CreateTable
CREATE TABLE `Product_catalog` (
    `id` VARCHAR(191) NOT NULL,
    `category` VARCHAR(191) NOT NULL,
    `name` VARCHAR(191) NOT NULL,
    `description` VARCHAR(191) NOT NULL,
    `photo_url` VARCHAR(191) NULL,
    `national_price` INTEGER NOT NULL,
    `prediction_price` INTEGER NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Users` (
    `id` VARCHAR(191) NOT NULL,
    `email` VARCHAR(191) NOT NULL,
    `name` VARCHAR(191) NOT NULL,
    `password` VARCHAR(191) NOT NULL,
    `type` VARCHAR(191) NOT NULL,
    `photo_url` VARCHAR(191) NULL,
    `refreshToken` VARCHAR(191) NULL,

    UNIQUE INDEX `Users_email_key`(`email`),
    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Product_item` (
    `id` VARCHAR(191) NOT NULL,
    `productcatalog_id` VARCHAR(191) NOT NULL,
    `producer_id` VARCHAR(191) NOT NULL,
    `distributor_id` VARCHAR(191) NOT NULL,
    `seller_id` VARCHAR(191) NOT NULL,
    `description` VARCHAR(191) NOT NULL,
    `photo_url` VARCHAR(191) NULL,
    `harvest_date` DATETIME(3) NULL,
    `harvest_place` VARCHAR(191) NULL,
    `price_producer` INTEGER NULL,
    `price_distributor` INTEGER NULL,
    `price_seller` INTEGER NULL,
    `status` VARCHAR(191) NULL,
    `sell_date` DATETIME(3) NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_productcatalog_id_fkey` FOREIGN KEY (`productcatalog_id`) REFERENCES `Product_catalog`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_producer_id_fkey` FOREIGN KEY (`producer_id`) REFERENCES `Users`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_distributor_id_fkey` FOREIGN KEY (`distributor_id`) REFERENCES `Users`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_seller_id_fkey` FOREIGN KEY (`seller_id`) REFERENCES `Users`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
