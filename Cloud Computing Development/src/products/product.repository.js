const prisma = require('../db')

const findAllProducts = async () => {
  const products = await prisma.product_catalog.findMany()

  return products
}

const findProductByCategory = async (productCategory) => {
  const product = await prisma.product_catalog.findMany({
    where: {
      category: productCategory,
    },
  })

  return product
}

const findProductById = async (productId) => {
  const product = await prisma.product_catalog.findUnique({
    where: {
      id: productId,
    },
  })

  return product
}

// const findProductByName = async (productName) => {
//   const product = await prisma.product_catalog.findUnique({
//     where: {
//       id: productName,
//     },
//   })

//   return product
// } //not runing

module.exports = { findAllProducts, findProductByCategory, findProductById }
