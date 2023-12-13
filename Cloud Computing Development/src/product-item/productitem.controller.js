const express = require('express')
const prisma = require('../db')
const rProductItem = express.Router()

rProductItem.get('/product-item-router', (req, res) => {
  res.send({
    message: 'this endpoint ready to use',
  })
})

rProductItem.get('/product-item', async (req, res) => {
  const products = await prisma.product_item.findMany()

  res.send(products)
})

module.exports = rProductItem
