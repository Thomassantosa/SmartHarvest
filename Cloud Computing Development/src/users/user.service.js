const {
  findAllUsers,
  insertUser,
  findUserByEmail,
  findUserWithEmail,
  editUser,
  findUserById,
} = require('./user.repository')

const getAllUsers = async (req, res) => {
  const users = await findAllUsers()

  return users
}

const Register = async (newUserData) => {
  const user = await insertUser(newUserData)

  return user
}

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

const getUserById = async (userId) => {
  const user = await findUserById(userId)

  if (!user) {
    throw Error('User Not Found!')
  }

  return user
}

const editUserByEmail = async (userEmail, userData) => {
  await getUserByEmail(userEmail)

  const user = await editUser(userEmail, userData)

  return user
}

module.exports = { getAllUsers, Register, Login, getUserByEmail, editUserByEmail, getUserById }
