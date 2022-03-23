package android.example.databasedemo.database

import android.content.Context
import android.example.databasedemo.dao.ProductDao
import android.example.databasedemo.entity.Product
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Product::class], version=1)
abstract class MyDatabase:RoomDatabase() {
    abstract val productDao:ProductDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "Mydatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}