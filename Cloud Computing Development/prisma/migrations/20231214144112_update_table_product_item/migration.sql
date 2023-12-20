/*
  Warnings:

  - Added the required column `category` to the `Product_item` table without a default value. This is not possible if the table is not empty.
  - Added the required column `name` to the `Product_item` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE `product_item` DROP FOREIGN KEY `Product_item_distributor_id_fkey`;

-- DropForeignKey
ALTER TABLE `product_item` DROP FOREIGN KEY `Product_item_producer_id_fkey`;

-- DropForeignKey
ALTER TABLE `product_item` DROP FOREIGN KEY `Product_item_productcatalog_id_fkey`;

-- DropForeignKey
ALTER TABLE `product_item` DROP FOREIGN KEY `Product_item_seller_id_fkey`;

-- AlterTable
ALTER TABLE `product_item` ADD COLUMN `category` VARCHAR(191) NOT NULL,
    ADD COLUMN `name` VARCHAR(191) NOT NULL,
    MODIFY `productcatalog_id` VARCHAR(191) NULL,
    MODIFY `producer_id` VARCHAR(191) NULL,
    MODIFY `distributor_id` VARCHAR(191) NULL,
    MODIFY `seller_id` VARCHAR(191) NULL,
    MODIFY `description` VARCHAR(191) NULL;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_productcatalog_id_fkey` FOREIGN KEY (`productcatalog_id`) REFERENCES `Product_catalog`(`id`) ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_producer_id_fkey` FOREIGN KEY (`producer_id`) REFERENCES `Users`(`id`) ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_distributor_id_fkey` FOREIGN KEY (`distributor_id`) REFERENCES `Users`(`id`) ON DELETE SET NULL ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Product_item` ADD CONSTRAINT `Product_item_seller_id_fkey` FOREIGN KEY (`seller_id`) REFERENCES `Users`(`id`) ON DELETE SET NULL ON UPDATE CASCADE;
