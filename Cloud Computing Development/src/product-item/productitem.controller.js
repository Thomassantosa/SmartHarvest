const express = require('express')
const rProductItem = express.Router()
const {
  getAllProductItems,
  createNewProductItem,
  getProductItemById,
  editProductItemById,
  getAllProductItemByProducerId,
  getAllProductItemByDistributorId,
  getAllProductItemBySellerId,
  getAllProductItemPrices,
} = require('./productitem.service')
const { getUserById } = require('../users/user.service')
const verifyToken = require('../middleware/verify.token')

rProductItem.get('/products-item', verifyToken, async (req, res) => {
  const products = await getAllProductItems()

  res.send(products)
})

rProductItem.get('/products-item-price', verifyToken, async (req, res) => {
  const products = await getAllProductItemPrices()

  res.send(products)
})

rProductItem.get('/product-item/:id', async (req, res) => {
  try {
    const productId = req.params.id

    const product = await getProductItemById(productId)

    res.send({
      message: 'Success',
      data: product,
    })
  } catch (error) {
    res.status(404).send({
      message: 'Product Item Not Found!',
    })
  }
})

rProductItem.post('/product-item', async (req, res) => {
  try {
    const NewProductItemData = req.body

    const product = await createNewProductItem(NewProductItemData)

    res.send({
      message: 'Create Product Item Success',
      data: product,
    })
  } catch (error) {
    res.send({
      message: error.message,
    })
  }
})

rProductItem.put('/product-item/:id', async (req, res) => {
  try {
    const productId = req.params.id
    const productItemData = req.body

    await editProductItemById(productId, productItemData)

    const product = await getProductItemById(productId)

    res.send({
      error: 'False',
      message: 'Product Updated',
      data: product,
    })
  } catch (error) {
    res.send({
      message: error.message,
    })
  }
})

rProductItem.get('/products-item-producer/:id', async (req, res) => {
  try {
    const userId = req.params.id

    const producer = await getUserById(userId)

    const products = await getAllProductItemByProducerId(userId)

    res.send({
      message: `Success Get Products By ${producer.name}`,
      data: products,
    })
  } catch (error) {
    res.send({
      message: error.message,
    })
  }
})

rProductItem.get('/products-item-distributor/:id', async (req, res) => {
  try {
    const userId = req.params.id

    const distributor = await getUserById(userId)

    const products = await getAllProductItemByDistributorId(userId)

    res.send({
      message: `Success Get Products By ${distributor.name}`,
      data: products,
    })
  } catch (error) {
    res.send({
      message: error.message,
    })
  }
})

rProductItem.get('/products-item-seller/:id', async (req, res) => {
  try {
    const userId = req.params.id

    const seller = await getUserById(userId)

    const products = await getAllProductItemBySellerId(userId)

    res.send({
      message: `Success Get Products By ${seller.name}`,
      data: products,
    })
  } catch (error) {
    res.send({
      message: error.message,
    })
  }
})

module.exports = rProductItem
