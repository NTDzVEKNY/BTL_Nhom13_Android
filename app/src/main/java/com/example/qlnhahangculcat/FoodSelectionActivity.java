package com.example.qlnhahangculcat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnhahangculcat.adapter.FoodSelectionAdapter;
import com.example.qlnhahangculcat.database.DatabaseHelper;
import com.example.qlnhahangculcat.model.backup.Food;
import com.example.qlnhahangculcat.model.backup.OrderItem;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FoodSelectionActivity extends AppCompatActivity {
    
    private TextView textViewTableInfo;
    private TextView textViewDate;
    private Spinner spinnerCategory;
    private ListView listViewFoods;
    private Button buttonConfirm;
    private TextView textViewEmptyMenu;

    private DatabaseHelper databaseHelper;
    private List<Food> foodList;
    private List<String> categories;
    private FoodSelectionAdapter adapter;
    private List<OrderItem> selectedItems = new ArrayList<>();
    
    private long tableId;
    private String tableName;
    private boolean todayOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);

        // Initialize database helper
        databaseHelper = DatabaseHelper.getInstance(this);

        // Get table information from intent
        tableId = getIntent().getLongExtra("tableId", -1);
        tableName = getIntent().getStringExtra("tableName");
        todayOnly = getIntent().getBooleanExtra("todayOnly", false);

        // Initialize views
        initViews();
        
        // Set table information
        textViewTableInfo.setText("Bàn: " + tableName);
        
        // Set current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String today = dateFormat.format(new Date());
        textViewDate.setText("Ngày: " + today);
        
        // Load food items for today's menu
        loadFoodItems();

        // Set up category spinner
        setupCategorySpinner();
        
        // Set up buttons
        buttonConfirm.setOnClickListener(v -> confirmSelection());

        // Set up action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Gọi món");
        }
    }

    private void initViews() {
        textViewTableInfo = findViewById(R.id.textViewTableInfo);
        textViewDate = findViewById(R.id.textViewDate);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        listViewFoods = findViewById(R.id.listViewFoods);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        textViewEmptyMenu = findViewById(R.id.textViewEmptyMenu);
    }
    
    private void loadFoodItems() {
        // Get items from today's menu
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String today = dateFormat.format(new Date());
        
        // Log để debug
        Log.d("FoodSelectionActivity", "Loading food items for date: " + today);
        
        if (todayOnly) {
            // Get items from today's menu only
            foodList = databaseHelper.getMenuItemsForDate(today);
            Log.d("FoodSelectionActivity", "Found " + (foodList != null ? foodList.size() : 0) + " items in today's menu");
            
            // Nếu không có món nào trong menu hôm nay, hỏi người dùng có muốn xem tất cả các món không
            if (foodList == null || foodList.isEmpty()) {
                showNoMenuItemsDialog();
                return;
            }
        } else {
            // Get all available food items
            foodList = databaseHelper.getAllAvailableFoods();
            Log.d("FoodSelectionActivity", "Found " + (foodList != null ? foodList.size() : 0) + " available food items");
        }
        
        // Đảm bảo foodList không null
        if (foodList == null) {
            foodList = new ArrayList<>();
        }
        
        if (foodList.isEmpty()) {
            textViewEmptyMenu.setVisibility(View.VISIBLE);
            listViewFoods.setVisibility(View.GONE);
            buttonConfirm.setEnabled(false);
            
            // Thêm thông báo toast để người dùng biết
            Toast.makeText(this, "Không có món ăn nào có sẵn", Toast.LENGTH_LONG).show();
            Log.w("FoodSelectionActivity", "No food items found");
        } else {
            textViewEmptyMenu.setVisibility(View.GONE);
            listViewFoods.setVisibility(View.VISIBLE);
            buttonConfirm.setEnabled(true);
            
            // Extract categories
            setupCategories();
            
            // Set up food list adapter
            adapter = new FoodSelectionAdapter(this, foodList);
            listViewFoods.setAdapter(adapter);
        }
    }
    
    private void setupCategories() {
        // Extract unique categories from food list
        categories = new ArrayList<>();
        categories.add("Tất cả");
        
        for (Food food : foodList) {
            String[] foodCategories = food.getCategoryString().split(",");
            for (String category : foodCategories) {
                category = category.trim();
                if (!categories.contains(category)) {
                    categories.add(category);
                }
            }
        }
    }
    
    private void setupCategorySpinner() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);
        
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterFoodsByCategory(categories.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }
    
    private void filterFoodsByCategory(String category) {
        if (category.equals("Tất cả")) {
            adapter.setFilteredList(foodList);
        } else {
            List<Food> filteredList = new ArrayList<>();
            for (Food food : foodList) {
                if (food.getCategoryString().contains(category)) {
                    filteredList.add(food);
                }
            }
            adapter.setFilteredList(filteredList);
        }
    }
    
    private void confirmSelection() {
        // Get selected items from adapter
        selectedItems = adapter.getSelectedItems();
        
        if (selectedItems.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn ít nhất một món", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Return selected items to calling activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedItems", (Serializable) selectedItems);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void showNoMenuItemsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Không có món ăn nào trong menu hôm nay");
        builder.setMessage("Không có món ăn nào được thêm vào menu hôm nay. Bạn có muốn xem tất cả các món ăn có sẵn không?");
        
        builder.setPositiveButton("Có", (dialog, which) -> {
            todayOnly = false;
            loadFoodItems();
        });
        
        builder.setNegativeButton("Không", (dialog, which) -> {
            // Hiển thị danh sách trống
            foodList = new ArrayList<>();
            textViewEmptyMenu.setVisibility(View.VISIBLE);
            listViewFoods.setVisibility(View.GONE);
            buttonConfirm.setEnabled(false);
        });
        
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 