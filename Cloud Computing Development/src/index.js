const express = require('express')
const dotenv = require('dotenv')
const cors = require('cors')
const router = require('./users/user.controller')

dotenv.config()

const app = express()
const PORT = process.env.PORT

app.get('/', (req, res) => {
  res.send({
    message: 'API siap untuk digunakan',
  })
})

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
