const { empty } = require('@prisma/client/runtime/library')
const { findAllProducts, findProductByCategory, findProductById } = require('./product.repository')

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

// const getProductByName = async (productName) => {
//   const product = await findProductByName(productName)

//   if (!product) {
//     throw Error()
//   }

//   return product
// } //not working well

module.exports = { getAllProducts, getProductByCategory, getProductById }
