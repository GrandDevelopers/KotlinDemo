package grand.app.humanconnector.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductFromDatabase")
    fun getAll(): List<ProductFromDatabase>

    @Query("SELECT * FROM ProductFromDatabase WHERE name LIKE :name")
    fun getAllByName(vararg name: String): List<ProductFromDatabase>

    @Query("DELETE FROM ProductFromDatabase")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM ProductFromDatabase")
    fun getProductsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: ProductFromDatabase)
}