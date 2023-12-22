# SmartHarvestAPI

The SmartHarvest API is an integral component of the SmartHarvest application. It employs a RESTful API architecture, utilizing ExpressJS as its web application framework and Prisma as the ORM database.

## Cloud Computing Team CH2-PS143

<table  align="center">
  <thead>
    <tr>
      <th>Name</th>
      <th>University</th>
			<th>Bangkit ID</th>
			<th>Social Media</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Satria Purnama Putra</td>
			<td>Universitas Islam Nusantara</td>
			<td>C630BSY3052</td>
			<td><p><a href="https://www.instagram.com/satria.prnama/">Instagram</a></p> & <p><a href="https://github.com/satriapurnama0311/"> Github</a></p></td>
    </tr>
		<tr>
      <td>Philip Evandry</td>
			<td>Institut Teknologi Harapan Bangsa</td>
			<td>C003BSY3228</td>
			<td><p><a href="https://www.instagram.com/philipevandry15/">Instagram</a></p> & <p><a href="https://github.com/philipevandry"> Github</a></p></td>
    </tr>
  </tbody>
</table>

## Features

- Developed with the Express JS framework for enhanced flexibility and speed
- Using Prisma ORM for easly query from database
- Includes JWT Bearer authentication
- API Documentations with Postman
- Layered Architecture

## 📂Project Structure

- **prisma** (this foler contains prisma configuration)
  - **migrations** (migration folder for prisma)
  - schema.prisma (the main configuration file for our Prisma setup)
- **src** (main folder)
  - **db** (this folder contains file for connection with database)
  - **middleware** (this folder contain middleware file to verify the token)
  - **product-item** (this folder contain configuration product item)
  - **products** (this folder contain configuration product catalog)
  - **seed-database** (this folder contain file to populate the database with default)
  - **user** (this folder contain configuration users)
    - user.controller.js (controller layer aims to handle request, response, and body validation )
    - user.repository.js (repository layer aims to communicate with the database)
    - user.service.js (service layer aims to handle business logic)
  - index.js (This file is the core file that runs the Express JS framework)
- .env.development (this is sample of .env you can copy it when you try to run API locally)
- .gitignore (file to tells Git which files to ignore when committing your project to the GitHub repository)
- package-lock.json (automatically generated for any operations where npm modifies either the node_modules tree, or package. json)
- package.json (this file is the heart of any Node project)

## ⚙️Instalation/Run Locally

Here are the steps to install and run the SmartHarvestAPI in your local machine:

1. Clone this repository to your local directory:

```shell
git clone https://github.com/Thomassantosa/SmartHarvest.git
```

2. Navigate to the project directory:

```shell
cd SmartHarvest/'Cloud Computing Development'
```

3. Install the required dependencies using npm:

```shell
npm install
```

4. Prepare the database, you can use XAMPP or other, after the database ready you can continue to the next step,

5. in the root folder, add New File name it `.env` and copy the configuration from `.env.development` file

   - Add your `DATABASE_URL`
   - If you not use my Mysql, change the datasource db provider in file `prisma/schema.prisma` adjust to your database

6. Push and generate `schema.database` to your database using npx command:

```shell
npx prisma db push
```

7. Optionally you can seed or populate the database using command:

```shell
npx prisma db seed
```

8. Run the SmartHarvestAPI using command:

```shell
npm run startDev
```

### For API Documentation you can see [here](#)
