package co.edu.uan.simpsonsvm0411.services

data class DogImage(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
) {
}

/**
 * example:
 * {
 *     "id": "B1pDZx9Nm",
 *     "url": "https://cdn2.thedogapi.com/images/B1pDZx9Nm_1280.jpg",
 *     "width": 1200,
 *     "height": 798
 *   }
 */