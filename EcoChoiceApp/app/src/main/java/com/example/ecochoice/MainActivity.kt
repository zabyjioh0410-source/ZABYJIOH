package com.example.ecochoice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.search.SearchBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: SearchBar
    private lateinit var ecoProductsAdapter: EcoProductsAdapter
    private lateinit var productList: List<EcoProduct>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация представлений
        recyclerView = findViewById(R.id.recyclerViewProducts)
        searchBar = findViewById(R.id.searchBar)

        // Создание списка эко-продуктов
        productList = createEcoProductsList()

        // Настройка RecyclerView
        setupRecyclerView()

        // Настройка поиска
        setupSearch()
    }

    private fun createEcoProductsList(): List<EcoProduct> {
        return listOf(
            EcoProduct("Органические яблоки", "Свежие яблоки без пестицидов", R.drawable.apple, 95),
            EcoProduct("Биоразлагаемый шампунь", "Натуральный шампунь без сульфатов", R.drawable.shampoo, 88),
            EcoProduct("Эко-сумка", "Многоразовая сумка из переработанных материалов", R.drawable.bag, 92),
            EcoProduct("Бамбуковая зубная щетка", "Разлагаемая зубная щетка", R.drawable.toothbrush, 90),
            EcoProduct("Органический кофе", "Кофе справедливой торговли", R.drawable.coffee, 87),
            EcoProduct("Натуральное мыло", "Мыло ручной работы без химии", R.drawable.soap, 94),
            EcoProduct("Переработанная бумага", "Бумага из вторсырья", R.drawable.paper, 85),
            EcoProduct("Солнечная батарея", "Портативное зарядное устройство", R.drawable.solar, 96),
            EcoProduct("Эко-бутылка", "Многоразовая бутылка для воды", R.drawable.bottle, 91),
            EcoProduct("Органический чай", "Чай из органических ферм", R.drawable.tea, 89)
        )
    }

    private fun setupRecyclerView() {
        ecoProductsAdapter = EcoProductsAdapter(productList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ecoProductsAdapter
    }

    private fun setupSearch() {
        searchBar.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterProducts(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterProducts(newText ?: "")
                return true
            }
        })
    }

    private fun filterProducts(query: String) {
        val filteredList = productList.filter {
            it.name.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true)
        }
        ecoProductsAdapter.updateList(filteredList)
    }
}
