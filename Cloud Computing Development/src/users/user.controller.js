const express = require('express')
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')
const { getAllUsers, Register, Login, getUserByEmail, editUserByEmail, getUserById } = require('./user.service')
const verifyToken = require('../middleware/verify.token')

const router = express.Router()

router.get('/', (req, res) => {
  res.send({
    message: 'This is API for SmartHarvest Application Made By Team CH2-PS143',
  })
})

router.get('/login', (req, res) => {
  res.sendStatus(405)
})

router.get('/register', (req, res) => {
  res.sendStatus(405)
})

router.get('/users', verifyToken, async (req, res) => {
  const users = await getAllUsers()

  res.send(users)
})

router.post('/register', async (req, res) => {
  try {
    const newUserData = req.body

    const user = await Register(newUserData)

    res.send({
      error: 'false',
      message: 'User Created',
      data: {
        name: user.name,
        email: user.email,
        type: user.type,
      },
    })
  } catch (error) {
    res.status(400).send({
      message: 'Error! Email Must Be Unique or Maybe Some Fields Missing.',
    })
  }
})

router.post('/login', async (req, res) => {
  try {
    const userData = req.body
    const user = await Login(userData)

    const match = await bcrypt.compare(req.body.password, user[0].password)

    if (!match) return res.status(400).send({ message: 'Wrong Password!' }) //validasi password

    const userId = user[0].id
    const name = user[0].name
    const email = user[0].email
    const type = user[0].type

    const accessToken = jwt.sign({ userId, name, email }, process.env.ACCESS_TOKEN_SECRET, {
      expiresIn: '1800s',
    })

    res.send({
      error: 'false',
      message: 'Login Successful',
      loginResult: {
        id: userId,
        type: type,
        name: name,
        token: accessToken,
      },
    })
  } catch (error) {
    res.status(404).send({
      message: 'Email not found.',
    })
  }
})

router.get('/user/:email', verifyToken, async (req, res) => {
  try {
    const userEmail = req.params.email
    const user = await getUserByEmail(userEmail)

    res.send(user)
  } catch (error) {
    res.status(404).send({
      message: 'Email Not Found!',
    })
  }
})

router.get('/user-id/:id', verifyToken, async (req, res) => {
  try {
    const userId = req.params.id
    const user = await getUserById(userId)

    res.send(user)
  } catch (error) {
    res.status(404).send({
      message: 'User Not Found!',
    })
  }
})

router.put('/user/:email', verifyToken, async (req, res) => {
  try {
    const userEmail = req.params.email
    const userData = req.body

    if ((userData.email || userData.password) != null) {
      throw Error()
    }

    await editUserByEmail(userEmail, userData)

    const user = await getUserByEmail(userEmail)

    res.send({
      error: 'false',
      message: 'User Updated',
      data: {
        name: user.name,
        email: user.email,
        type: user.type,
        photo_url: user.photo_url,
      },
    })
  } catch (error) {
    res.status(400).send({
      message: "You Can't Change Email and Password",
    })
  }
})

module.exports = router
