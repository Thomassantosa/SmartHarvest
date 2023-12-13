const {
  findAllProducts,
  findProductByCategory,
  findProductById,
  insertProductData,
  editProduct,
} = require('./product.repository')

const getAllProducts = async () => {
  const products = await findAllProducts()

  return products
}

const getProductByCategory = async (productCategory) => {
  const product = await findProductByCategory(productCategory)

  if (!product) {
    throw Error()
  }

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

// const getProductByName = async (productName) => {
//   const product = await findProductByName(productName)

//   if (!product) {
//     throw Error()
//   }

//   return product
// } //not working well

module.exports = { getAllProducts, getProductByCategory, getProductById, createProduct, editProductById }
