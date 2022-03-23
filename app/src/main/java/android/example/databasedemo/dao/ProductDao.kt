package android.example.databasedemo.dao

import android.example.databasedemo.entity.Product
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(p: Product)

    @Query("Select * from product_table")
    fun getAllProduct():List<Product>

    @Query("Select * from product_table where price<= :price")
    fun getProductByPrice(price :Double): List<Product>
}