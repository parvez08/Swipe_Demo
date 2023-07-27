# Swipe_Demo
# Product Management App

This is a simple Android app that allows users to manage a list of products. The app provides two main screens: a product list screen and an add product screen.

## Features

### Product List Screen

- Display a list of products with the product name, product type, price, and tax rate for each product.
- Allow users to search for products using a search bar.
- Include a "Refresh" button to reload the product list from the server.
- Show loading progress using a progress bar while fetching product data from the server.
- Load images for each product from a URL. If the URL is empty, a default image will be used.
- Users can scroll through the list to view all products.
- Include a "Add Product" button to navigate to the Add Product screen.
- Users can fetch the data from the server using the GET method to the API endpoint https://app.getswipe.in/api/public/get.

### Add Product Screen

- Users can select the product type from a list of options.
- Enter the product name, selling price, and tax rate using text fields.
- Optionally select images in JPEG or PNG format with a 1:1 ratio.
- Validate fields such as product type selection, non-empty product name, and decimal numbers for selling price and tax.
- Users can submit the data to the server using the POST method to the API endpoint https://app.getswipe.in/api/public/add.
- Provide clear and concise feedback to the user upon successful completion of the action.

## Technologies and Best Practices Used

- MVVM architecture for clear separation of concerns and maintainability.
- Retrofit for making REST API calls to retrieve product data.
- Kotlin coroutines for handling asynchronous tasks efficiently.
- LiveData for observing and updating UI components.
- Picasso or Glide for loading and caching images from URLs.
- RecyclerView for displaying the list of products efficiently.

## How to Build and Run the App

1. Clone this repository to your local machine using `git clone`.

2. Open the project in Android Studio.

3. Build the project and resolve any required dependencies.

4. Run the app on an Android emulator or physical device.

## Additional Information

- The API endpoint https://app.getswipe.in/api/public/get is used to retrieve product data.

- The API endpoint https://app.getswipe.in/api/public/add is used to submit new product data. Ensure that the server is properly set up to handle incoming requests.

- The app has been designed with a user-friendly interface to make navigation and interaction easy for users.

- Extensive testing has been done to ensure the app works correctly in various scenarios and conditions.

- The code is well-documented for future reference and maintenance.

- If you encounter any issues or have any questions, feel free to open an issue in this repository.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify it according to your needs.
