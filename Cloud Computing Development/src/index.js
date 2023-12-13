const express = require('express')
const dotenv = require('dotenv')
const cors = require('cors')
const router = require('./users/user.controller')
<<<<<<< Updated upstream
=======
const rProduct = require('./products/product.controller')
const rProductItem = require('./product-item/productitem.controller')
>>>>>>> Stashed changes

dotenv.config()

const app = express()
const PORT = process.env.PORT

app.use(
  cors({
    origin: ['*'],
  })
)
app.use(express.json())
app.use(router)

app.listen(PORT, process.env.NODE_ENV !== 'production' ? 'localhost' : '0.0.0.0', () => {
  console.log(`SmartHarvestAPI listening on port ${PORT}`)
})
