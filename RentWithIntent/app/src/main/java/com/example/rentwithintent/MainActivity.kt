package com.example.rentwithintent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var itemImage: ImageView
    private lateinit var itemName: TextView
    private lateinit var itemPrice: TextView
    private lateinit var itemRating: RatingBar
    private lateinit var itemCondition: TextView
    private lateinit var nextButton: Button
    private lateinit var borrowButton: Button

    private val rentalItems = mutableListOf(
        RentalItem("Drum", 10, 4.5f, "New"),
        RentalItem("Guitar", 8, 4.0f, "Good"),
        RentalItem("Piano", 5, 3.5f, "Fair")
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemImage = findViewById(R.id.item_image)
        itemName = findViewById(R.id.item_name)
        itemPrice = findViewById(R.id.item_price)
        itemRating = findViewById(R.id.item_rating)
        itemCondition = findViewById(R.id.item_condition)
        nextButton = findViewById(R.id.next_button)
        borrowButton = findViewById(R.id.borrow_button)

        updateUI()

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % rentalItems.size
            updateUI()
        }

        borrowButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("item", rentalItems[currentIndex])
            }
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val updatedItem = data?.getParcelableExtra<RentalItem>("item")
            updatedItem?.let { item ->
                rentalItems.find { it.name == item.name }?.expirationDate = item.expirationDate
                updateUI()
            }
        }
    }

    private fun updateUI() {
        val currentItem = rentalItems[currentIndex]
        itemName.text = currentItem.name
        itemPrice.text = "Price: ${currentItem.price} credits"
        itemRating.rating = currentItem.rating
        itemCondition.text = currentItem.condition

        itemImage.setImageResource(
            when (currentItem.name) {
                "Drum" -> R.drawable.drum
                "Guitar" -> R.drawable.guitar
                "Piano" -> R.drawable.piano
                else -> R.drawable.drum
            }
        )

        currentItem.expirationDate?.let { expiry ->
            val formattedDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(expiry))
            val currentTime = System.currentTimeMillis()

            borrowButton.isEnabled = currentTime >= expiry
            borrowButton.text = "Due Back $formattedDate"
        } ?: run {
            borrowButton.isEnabled = true
            borrowButton.text = "Borrow"
        }
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}
