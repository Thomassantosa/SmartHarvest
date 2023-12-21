<h2 align="center"> SmartHarvest Application - Quaity and Transparency Food Supply Chain </h2> 
<br>
<p>Think you have no idea where your food comes from? Meet SmartHarvest, the app that's like a truth serum for your lunchbox! We're talking Android magic, machine learning muscle, and cloud power all mashed up to bring you food transparency that's juicy fresh.

Fruits, veggies, meats – you name it, we track it. Forget fuzzy labels and shady distributors. Scan our special QR codes and bam! You're on a farm tour from seed to Supermarket Sally. Trust your veggies? You betcha!</p>
<br>

## Team Member CH2-PS143
<div align="center">

| Bangkit ID |     Name         |  Learning Path    |     Campus    |
|:----------:|:----------------:|:-----------------:|:-----------------:|
|M003BSY0004| Daniel Pratama          | Machine Learning   | Institut Teknologi Harapan Bangsa   |
|M011BSY1330| Bagus Aji Alghifari     | Machine Learning   | Universitas Padjadjaran             |
|M369BSY0120| Muhamad Haekal Rizky    | Machine Learning   | STMIK Amik Bandung                  |
|C630BSY3052| Satria Purnama Putra    | Cloud Computing    | Universitas Islam Nusantara         |
|C003BSY3228| Philip Evandry          | Cloud Computing    | Institut Teknologi Harapan Bangsa   |
|A003BSY2975| Thomas Budi Santosa     | Mobile Development | Institut Teknologi Harapan Bangsa   |
|A296BSY2483| Taufiq Arinta Ardiyono  | Mobile Development | Universitas Pembangunan Nasional Veteran Jawa Timur |

</div>

## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)
<br>

## Infrastructure Design
![Design Infra](https://media.discordapp.net/attachments/1151090369233174605/1186933990783987722/Screenshot_2023-12-20_140323.png)

<br/>

## SmartHarvestAPI Reference
### Authentications
|Endpoint              |Method               | Parameter          | Type     | Description                                   |
|:---------------------|:--------------------| :------------------| :------- | :---------------------------------------------|
| `/register`          |POST                 | `application/json` | `string` | This endpoint used for user register. Payload contains name, email, password, and type field, after you fill payload and send request to server, server will response "User Created" and data user. |
| `/login`             |POST                 | `application/json` | `string` | This endpoint used for user login. Payload contains email and password field, after you fill payload and send request to server, server will response "Login Successful" and get loginResult contains accessToken. |

### Users
|Endpoint              |Method               | Parameter          | Type     | Description                                   |
|:---------------------|:--------------------| :------------------| :------- | :---------------------------------------------|
| `/users`             |GET                  | `application/json` | `string` | This endpoint used for get all user in database you have to add authentication using barier token to access this endpoint. |
| `/user/${email}`     |GET                  | `application/json` | `string` | This endpoint used for get user by email |
| `/user/${email}`     |PUT                  | `application/json` | `string` | This endpoint used for update data user by spesific email |

