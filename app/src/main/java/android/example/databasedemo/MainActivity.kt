package android.example.databasedemo

import android.example.databasedemo.database.MyDatabase
import android.example.databasedemo.databinding.ActivityMainBinding
import android.example.databasedemo.entity.Product
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = MyDatabase.getInstance(applicationContext).productDao

        binding.btnInsert.setOnClickListener(){
            val newProduct=Product(0,"iPhone",3500.00)
        CoroutineScope(IO).launch {
            dao.insertProduct(newProduct)}
        }
        binding.btnGet.setOnClickListener(){

            var proName=""

            CoroutineScope(IO).launch {
                val productList=dao.getAllProduct()

                for(p in productList){
                    proName+=p.name+"\n"
                }
                CoroutineScope(Main).launch {
                    binding.tvResult.text = proName
                }
            }

        }
    }
}