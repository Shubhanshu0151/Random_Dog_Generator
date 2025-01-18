# Random Dog Image Generator App

# This Android app generates random dog images from the public Dog CEO API and displays them in a user-friendly interface. The app features the following screens:

## Home Screen: Displays navigation buttons to other screens.
## Generate Dogs Screen: Allows users to fetch random dog images with a "Generate!" button. The app stores up to 20 of the most recent images in a persistent LRU cache.
## My Recently Generated Dogs Screen: Displays a scrollable gallery of the 20 most recent dog images, pulled from the cache. Users can also clear the gallery and the cache with the "Clear Dogs" button.
The app uses Jetpack Compose for UI design and ensures that images are stored and persist across sessions. The cache system maintains the most recent images, and any outdated ones are automatically removed.
