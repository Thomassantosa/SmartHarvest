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

const insertProductData = async (newProductData) => {
  const product = await prisma.product_catalog.create({
    data: {
      name: newProductData.name,
      category: newProductData.category,
      description: newProductData.description,
      photo_url: newProductData.photo_url,
      national_price: newProductData.national_price,
      prediction_price: newProductData.prediction_price,
    },
  })

  return product
}

const editProduct = async (productId, productData) => {
  const product = await prisma.product_catalog.update({
    where: {
      id: productId,
    },
    data: {
      name: productData.name,
      category: productData.category,
      description: productData.description,
      photo_url: productData.photo_url,
      national_price: productData.national_price,
      prediction_price: productData.prediction_price,
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

module.exports = { findAllProducts, findProductByCategory, findProductById, insertProductData, editProduct }
