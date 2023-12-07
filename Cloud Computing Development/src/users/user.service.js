const dotenv = require('dotenv')
const { findAllUsers, insertUser, findUserByEmail, findUserWithEmail, editUser } = require('./user.repository')

dotenv.config()

//menampilkan semua users
const getAllUsers = async (req, res) => {
  const users = await findAllUsers()

  return users
}

//API untuk pendaftaran
const Register = async (newUserData) => {
  const user = await insertUser(newUserData)

  return user
}

//API untuk login
const Login = async (userData) => {
  const user = await findUserByEmail(userData)

  return user
}

const getUserByEmail = async (userEmail) => {
  const user = await findUserWithEmail(userEmail)

  if (!user) {
    throw Error('email not found')
  }

  return user
}

const editUserByEmail = async (userEmail, userData) => {
  await getUserByEmail(userEmail)

  const user = await editUser(userEmail, userData)

  return user
}

module.exports = { getAllUsers, Register, Login, getUserByEmail, editUserByEmail }
