const {
  findAllProductItems,
  insertProductItem,
  findProductItemById,
  editProductItem,
  existingProductCatalog,
  findAllProductItemByProducerId,
  findAllProductItemByDistributorId,
  findAllProductItemBySellerId,
  findAllProductItemPrices,
} = require('./productitem.repository')

const getAllProductItems = async () => {
  const products = await findAllProductItems()

  return products
}

const getAllProductItemPrices = async () => {
  const products = await findAllProductItemPrices()

  return products
}

const getProductItemById = async (productId) => {
  const product = await findProductItemById(productId)

  if (!product) {
    throw Error()
  }

  return product
}

const createNewProductItem = async (newProductItemData) => {
  const existingProduct = await existingProductCatalog(newProductItemData)

  if (existingProduct) {
    throw new Error(`Product with catalog ID ${newProductItemData.productcatalog_id} already exists!`)
  }

  const product = await insertProductItem(newProductItemData)

  return product
}

const editProductItemById = async (productId, productItemData) => {
  await getProductItemById(productId)

  const product = await editProductItem(productId, productItemData)

  return product
}

const getAllProductItemByProducerId = async (userId) => {
  const products = await findAllProductItemByProducerId(userId)

  return products
}

const getAllProductItemByDistributorId = async (userId) => {
  const products = await findAllProductItemByDistributorId(userId)

  return products
}

const getAllProductItemBySellerId = async (userId) => {
  const products = await findAllProductItemBySellerId(userId)

  return products
}

module.exports = {
  getAllProductItems,
  getProductItemById,
  createNewProductItem,
  editProductItemById,
  getAllProductItemByProducerId,
  getAllProductItemByDistributorId,
  getAllProductItemBySellerId,
  getAllProductItemPrices,
}
