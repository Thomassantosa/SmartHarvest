const express = require('express')
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')
const { getAllUsers, Register, Login, getUserByEmail, editUserByEmail } = require('./user.service')
const verifyToken = require('../middleware/verify.token')

const router = express.Router()

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
      message: 'Some fields missing!',
    })
  }
})

router.post('/login', async (req, res) => {
  try {
    const userData = req.body
    const user = await Login(userData)

    const match = await bcrypt.compare(req.body.password, user[0].password)

    if (!match) return res.status(400).send({ message: 'Wrong Password!' })

    const userId = user[0].id
    const name = user[0].name
    const email = user[0].email
    const type = user[0].type

    const accessToken = jwt.sign({ userId, name, email }, process.env.ACCESS_TOKEN_SECRET, {
      expiresIn: '900s',
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

router.get('/user/:email', async (req, res) => {
  try {
    const userEmail = req.params.email
    const user = await getUserByEmail(userEmail)

    res.send(user)
  } catch (error) {
    res.status(404).send({
      message: 'email not found',
    })
  }
})

router.patch('/user/:email', async (req, res) => {
  try {
    const userEmail = req.params.email
    const userData = req.body

    await editUserByEmail(userEmail, userData)

    const user = await getUserByEmail(userEmail)

    res.send({
      error: 'false',
      message: 'User Updated',
      data: {
        name: user.name,
        email: user.email,
        type: user.type,
        photoUrl: user.picture,
      },
    })
  } catch (error) {
    res.send(error)
  }
})

module.exports = router
