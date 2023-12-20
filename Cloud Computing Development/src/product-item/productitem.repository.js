const prisma = require('../db')
const { findProductById } = require('../products/product.repository')
const { findUserById } = require('../users/user.repository')

const findAllProductItems = async () => {
  const products = prisma.product_item.findMany()

  return products
}

const findAllProductItemPrices = async () => {
  const products = prisma.product_item.findMany({
    select: {
      name: true,
      category: true,
      price_producer: true,
      price_distributor: true,
      price_seller: true,
    },
  })

  return products
}

const findProductItemById = async (productId) => {
  const product = prisma.product_item.findUnique({
    where: {
      id: productId,
    },
  })

  return product
}

const insertProductItem = async (newProductItemData) => {
  const productCatalog = await findProductById(newProductItemData.productcatalog_id)
  // const producer = await findUserById(newProductItemData.producer_id)
  // const distributor = await findUserById(newProductItemData.distributor_id)
  // const seller = await findUserById(newProductItemData.seller_id)

  const product = await prisma.product_item.create({
    data: {
      productcatalog_id: newProductItemData.productcatalog_id,
      producer_id: newProductItemData.producer_id,
      distributor_id: newProductItemData.distributor_id,
      seller_id: newProductItemData.seller_id,

      name: productCatalog.name,
      category: productCatalog.category,
      description: newProductItemData.description,
      photo_url: newProductItemData.photo_url,
      national_price: productCatalog.national_price,
      prediction_price: productCatalog.prediction_price,

      producer_name: newProductItemData.producer_name,
      distributor_name: newProductItemData.distributor_name,
      seller_name: newProductItemData.seller_name,
      price_producer: newProductItemData.price_producer,
      price_distributor: newProductItemData.price_distributor,
      price_seller: newProductItemData.price_seller,

      harvest_date: newProductItemData.harvest_date,
      harvest_place: newProductItemData.harvest_place,
      status: newProductItemData.status,
      sell_date: newProductItemData.sell_date,
    },
  })

  return product
}

const editProductItem = async (productId, productItemData) => {
  // const distributor = await findUserById(productItemData.distributor_id)
  // const seller = await findUserById(productItemData.seller_id)

  const product = prisma.product_item.update({
    where: {
      id: productId,
    },
    data: {
      productcatalog_id: productItemData.productcatalog_id,
      producer_id: productItemData.producer_id,
      distributor_id: productItemData.distributor_id,
      seller_id: productItemData.seller_id,

      description: productItemData.description,
      photo_url: productItemData.photo_url,

      producer_name: productItemData.producer_name,
      distributor_name: productItemData.distributor_name,
      seller_name: productItemData.seller_name,
      price_producer: productItemData.price_producer,
      price_distributor: productItemData.price_distributor,
      price_seller: productItemData.price_seller,

      harvest_date: productItemData.harvest_date,
      harvest_place: productItemData.harvest_place,
      status: productItemData.status,
      sell_date: productItemData.sell_date,
    },
  })

  return product
}

const existingProductCatalog = async (newProductItemData) => {
  const product = await prisma.product_item.findFirst({
    where: {
      productcatalog_id: newProductItemData.productcatalog_id,
    },
  })

  return product
}

const findAllProductItemByProducerId = async (userId) => {
  const products = await prisma.product_item.findMany({
    where: {
      producer_id: userId,
    },
  })

  return products
}

const findAllProductItemByDistributorId = async (userId) => {
  const products = await prisma.product_item.findMany({
    where: {
      distributor_id: userId,
    },
  })

  return products
}

const findAllProductItemBySellerId = async (userId) => {
  const products = await prisma.product_item.findMany({
    where: {
      seller_id: userId,
    },
  })

  return products
}

module.exports = {
  findAllProductItems,
  findProductItemById,
  insertProductItem,
  editProductItem,
  existingProductCatalog,
  findAllProductItemByProducerId,
  findAllProductItemByDistributorId,
  findAllProductItemBySellerId,
  findAllProductItemPrices,
}
