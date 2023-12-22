/*
  Warnings:

  - Added the required column `national_price` to the `Product_item` table without a default value. This is not possible if the table is not empty.
  - Added the required column `prediction_price` to the `Product_item` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `product_item` ADD COLUMN `distributor_name` VARCHAR(191) NULL,
    ADD COLUMN `national_price` INTEGER NOT NULL,
    ADD COLUMN `prediction_price` INTEGER NOT NULL,
    ADD COLUMN `producer_name` VARCHAR(191) NULL,
    ADD COLUMN `seller_name` VARCHAR(191) NULL;
