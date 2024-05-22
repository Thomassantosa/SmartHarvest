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

![App Screenshot1](https://github.com/Thomassantosa/SmartHarvest/blob/main/images/Screenshot1.png)
![App Screenshot2](https://github.com/Thomassantosa/SmartHarvest/blob/main/images/Screenshot2.png)
![App Screenshot3](https://github.com/Thomassantosa/SmartHarvest/blob/main/images/Screenshot3.png)
<br>

## Cloud Computing Infrastructure
![Design Infra](https://github.com/Thomassantosa/SmartHarvest/blob/main/images/CloudInrastrukture.png)

<br/>

## SmartHarvestAPI Reference
### Authentications
|Endpoint              |Method  | Parameter          | Authorization  | Description                                   |
|:---------------------|:-------| :------------------| :------------- | :---------------------------------------------|
| `/register`          |POST    | `application/json` |                | Register user                                 |
| `/login`             |POST    | `application/json` |                | Login user                                    |

### Users
|Endpoint              |Method  | Parameter          | Authorization  | Description                                   |
|:---------------------|:-------| :------------------| :------------- | :---------------------------------------------|
| `/users`             |GET     | `application/json` |                | Ger list of users                             |
| `/user/${email}`     |GET     | `application/json` | `Bearer Token` | Get user by email                             |
| `/user/${email}`     |PUT     | `application/json` | `Bearer Token` | Update data user by spesific email            |

### Products Catalog
|Endpoint                      |Method  | Parameter          | Authorization  | Description                           |
| `/products-catalog`          | GET    | `application/json` | `Bearer Token` | Get all product catalog               |
| `/product-category/category` | GET    | `application/json` | `Bearer Token` | Get product catalog based on category |
| `/product-id/id`             | GET    | `application/json` | `Bearer Token` |GET product by Id                      |
| `/product-catalog`           | POST   | `application/json` | `Bearer Token` |Add Product for Catalog                |
| `/product-catalog/id`        |PUT     | `application/json` | `Bearer Token` |Update Product Catalog                 |

### Products Item
|Endpoint                          |Method  | Parameter          | Authorization  | Description                                                     |
| `/products-item`                 | GET    | `application/json` | `Bearer Token` | Get all product item                                            |
| `/products-item-producer/:id`    | GET    | `application/json` | `Bearer Token` | Get all products based on producer_id                           |
| `/products-item-distributor/:id` | GET    | `application/json` | `Bearer Token` | Get all products based on distributor_id                        |
| `/products-item-seller/:id`      | GET    | `application/json` | `Bearer Token` | Get all products based on seller_id                             |
| `/product-item/:id`              | GET    | `application/json` | `Bearer Token` | Get product_item based on id                                    |
| `/product-item`                  | POST   | `application/json` | `Bearer Token` | Add new product item                                            |
| `/product-item/:id`              | PUT    | `application/json` | `Bearer Token` | Update product information by distributor/seller using params id|

