const {
  findAllProducts,
  findProductByCategory,
  findProductById,
  insertProductData,
  editProduct,
  existingCategory,
} = require('./product.repository')

const getAllProducts = async () => {
  const products = await findAllProducts()

  return products
}

const getProductByCategory = async (productCategory) => {
  const category = await existingCategory(productCategory)

  if (!category) {
    throw Error('Category Not Found!')
  }

  const product = await findProductByCategory(productCategory)

  return product
}

const getProductById = async (productId) => {
  const product = await findProductById(productId)

  if (!product) {
    throw Error()
  }

  return product
}

const createProduct = async (newProductData) => {
  const product = await insertProductData(newProductData)

  return product
}

const editProductById = async (productId, productData) => {
  await getProductById(productId)

  const product = editProduct(productId, productData)

  return product
}

module.exports = { getAllProducts, getProductByCategory, getProductById, createProduct, editProductById }
