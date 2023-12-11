const express = require('express')
const { getAllProducts, getProductByCategory, getProductById } = require('./product.service')

const rProduct = express.Router()

rProduct.get('/test-products', (req, res) => {
  res.send({
    message: 'this endpoint ready to use!',
  })
})

rProduct.get('/products-catalog', async (req, res) => {
  const products = await getAllProducts()

  res.send({
    message: 'Success',
    productCatalog: products,
  })
})

rProduct.get('/product-category/:category', async (req, res) => {
  try {
    const productCategory = req.params.category
    const product = await getProductByCategory(productCategory)

    res.send({
      message: 'Success',
      productCatalog: product,
    })
  } catch (error) {
    // console.log(error)
    res.status(404).send({
      message: 'Product Category not Found!',
    })
  }
})

rProduct.get('/product-id/:id', async (req, res) => {
  try {
    const productId = req.params.id
    const product = await getProductById(productId)

    res.send({
      message: 'Success',
      productCatalog: product,
    })
  } catch (error) {
    // console.log(error)
    res.status(404).send({
      message: 'Product not Found!',
    })
  }
})

// rProduct.get('/product-req-body', async (req, res) => {
//   try {
//     const productName = req.body
//     const product = await getProductByName(productName)

//     res.send({
//       message: 'Success',
//       productCatalog: product,
//     })
//   } catch (error) {
//     console.log(error)
//     // res.status(404).send({
//     //   message: 'Product not Found!',
//     // })
//   }
// }) //not working well

module.exports = rProduct
