package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.model.Table;
import com.example.qlnhahangculcat.database.service.TableService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableManager {
    private TableService tableService;

    public TableManager() {
        tableService = RetrofitClient.getRetrofitInstance().create(TableService.class);
    }

    // --- Phương thức API ---

    public void getAllTables(final TableCallback<List<Table>> callback) {
        Call<List<Table>> call = tableService.getAllTables("all");
        call.enqueue(new TableResponseCallback<>(callback));
    }

    public void getTableById(int id, final TableCallback<Table> callback) {
        Call<Table> call = tableService.getTableById(id);
        call.enqueue(new TableResponseCallback<>(callback));
    }

    public void getTablesByStatus(String status, final TableCallback<List<Table>> callback) {
        Call<List<Table>> call = tableService.getTablesByStatus("by_status", status);
        call.enqueue(new TableResponseCallback<>(callback));
    }

    public void addTable(Table table, final TableCallback<Void> callback) {
        Call<Void> call = tableService.addTable("add_table", table);
        call.enqueue(new TableResponseCallback<>(callback));
    }

    public void updateTable(int id, Table table, final TableCallback<Void> callback) {
        Call<Void> call = tableService.updateTable("update_table", id, table);
        call.enqueue(new TableResponseCallback<>(callback));
    }

    public void updateTableStatus(int id, String status, final TableCallback<Void> callback) {
        Table table = new Table();
        table.setStatus(status);
        Call<Void> call = tableService.updateTableStatus("update_status", id, table);
        call.enqueue(new TableResponseCallback<>(callback));
    }

    public void deleteTable(int id, final TableCallback<Void> callback) {
        Call<Void> call = tableService.deleteTable(id);
        call.enqueue(new TableResponseCallback<>(callback));
    }

    // --- Callback Interface ---

    public interface TableCallback<T> {
        void onSuccess(T result);

        void onError(String error);
    }

    // --- Private Callback Helper Class ---

    private static class TableResponseCallback<T> implements Callback<T> {
        private final TableCallback<T> callback;

        public TableResponseCallback(TableCallback<T> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                callback.onSuccess(response.body());
            } else {
                callback.onError("API Error: " + response.message() + " (" + response.code() + ")");
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            callback.onError("Network Error: " + t.getMessage());
        }
    }
}
