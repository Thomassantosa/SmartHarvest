const prisma = require('../db')
const bcrypt = require('bcrypt')

const findAllUsers = async () => {
  const users = await prisma.users.findMany()

  return users
}

const findUserByEmail = async (userData) => {
  const user = await prisma.users.findMany({
    where: {
      email: userData.email,
    },
  })

  return user
}

const insertUser = async (newUserData) => {
  const salt = await bcrypt.genSalt()
  const hashPassword = await bcrypt.hash(newUserData.password, salt)

  const user = await prisma.users.create({
    data: {
      name: newUserData.name,
      email: newUserData.email,
      password: hashPassword,
      type: newUserData.type,
      photo_url: newUserData.photo_url,
    },
  })

  return user
}

const findUserWithEmail = async (userEmail) => {
  const user = await prisma.users.findUnique({
    where: {
      email: userEmail,
    },
  })

  return user
}

const editUser = async (userEmail, userData) => {
  // const salt = await bcrypt.genSalt()
  // const hashPassword = await bcrypt.hash(userData.password, salt)

  const user = await prisma.users.update({
    where: {
      email: userEmail,
    },
    data: {
      name: userData.name,
      email: userData.email,
      // password: hashPassword,
      type: userData.type,
      photo_url: userData.photo_url,
    },
  })

  return user
}

module.exports = { findAllUsers, findUserByEmail, insertUser, findUserWithEmail, editUser }
