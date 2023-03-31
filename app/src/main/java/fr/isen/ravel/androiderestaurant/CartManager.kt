package fr.isen.ravel.androiderestaurant

class CartManager
{
    companion object {
        private val cartItems = ArrayList<CartItem>()

        fun getCartItems(): ArrayList<CartItem> {
            return cartItems
        }

        fun addCartItem(item: CartItem) {
            cartItems.add(item)
        }

        fun clearCart() {
            cartItems.clear()
        }
    }
}