const { PrismaClient } = require('@prisma/client')
const prisma = new PrismaClient()

async function main() {
  const products = await prisma.product_catalog.createMany({
    data: [
      {
        category: 'Buah',
        name: 'Semangka',
        description:
          'Semangka kaya akan air, sehingga dapat membantu tubuh untuk tetap terhidrasi, terutama di musim panas. Buah ini juga mengandung vitamin A, vitamin C, dan likopen yang baik untuk kesehatan.',
        photo_url: '',
        national_price: 22000,
        prediction_price: 27000,
      },
      {
        category: 'Daging',
        name: 'Daging Ayam Tulang',
        description:
          'Daging ayam tulang adalah salah satu bahan makanan yang paling populer di dunia. Daging ini mudah didapat, terjangkau, dan dapat diolah menjadi berbagai macam hidangan yang lezat.',
        photo_url: '',
        national_price: 38000,
        prediction_price: 42000,
      },
      {
        category: 'Ikan',
        name: 'Ikan Nila',
        description:
          'Ikan Nila merupakan sumber protein yang baik. Protein diperlukan untuk membangun dan memelihara otot, tulang, dan jaringan tubuh lainnya.',
        photo_url: '',
        national_price: 25000,
        prediction_price: 26000,
      },
      {
        category: 'Sayur',
        name: 'Selada',
        description:
          'Selada berasal dari daerah Mediterania. Sayur ini mulai diperkenalkan ke Indonesia pada abad ke-17, dan kini telah menjadi salah satu sayuran yang paling banyak dikonsumsi di Indonesia.',
        photo_url: '',
        national_price: 12000,
        prediction_price: 12500,
      },
      {
        category: 'Ikan',
        name: 'Ikan Mas',
        description:
          'Ikan mas adalah salah satu ikan konsumsi yang paling populer di Indonesia. Ikan ini memiliki rasa yang gurih dan tekstur yang lembut.',
        photo_url: '',
        national_price: 25000,
        prediction_price: 28000,
      },
      {
        category: 'Buah',
        name: 'Pisang',
        description:
          'Pisang merupakan buah yang sangat bergizi, mengandung berbagai vitamin dan mineral penting yang dibutuhkan oleh tubuh.Pisang termasuk dalam famili Musaceae dan genus Musa.',
        photo_url: '',
        national_price: 20000,
        prediction_price: 25000,
      },
    ],
  })
  console.log(products)
}
main()
  .then(async () => {
    await prisma.$disconnect()
  })
  .catch(async (e) => {
    console.error(e)
    await prisma.$disconnect()
    process.exit(1)
  })
