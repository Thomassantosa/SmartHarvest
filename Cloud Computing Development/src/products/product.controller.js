const express = require('express')
const verifyToken = require('../middleware/verify.token')
const {
  getAllProducts,
  getProductByCategory,
  getProductById,
  editProductById,
  createProduct,
} = require('./product.service')

const rProduct = express.Router()

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

rProduct.post('/product-catalog', async (req, res) => {
  try {
    const newProductData = req.body
    const product = await createProduct(newProductData)

    res.send({
      message: 'Add Product Success.',
      body: product,
    })
  } catch (error) {
    // console.log(error)
    res.status(400).send({
      message: 'Some fields missing!',
    })
  }
})

rProduct.put('/product-catalog/:id', async (req, res) => {
  try {
    // console.log('apakah ini di exekusi')
    const productId = req.params.id
    const productData = req.body

    await editProductById(productId, productData)
    const product = await getProductById(productId)

    res.send({
      message: 'Update Product Data Success',
      body: {
        name: product.name,
        category: product.category,
        description: product.description,
        photo_url: product.photo_url,
        national_price: product.national_price,
        prediction_price: product.prediction_price,
      },
    })
  } catch (error) {
    // console.log(error)
    res.sendStatus(404)
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