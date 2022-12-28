package lat.pam.utsrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMenu extends AppCompatActivity {
    TextView pizza, pizzaDetails;
    TextView spaghetti, spaghettiDetails;
    TextView burger, burgerDetails;
    TextView steak, steakDetails;
    TextView frenchFries, frenchFriesDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        pizza = findViewById(R.id.pepperoni_pizza);
        pizzaDetails = findViewById(R.id.pepperoni_pizza_detail);
        spaghetti = findViewById(R.id.spaghetti);
        spaghettiDetails = findViewById(R.id.spaghetti_detail);
        burger = findViewById(R.id.burger);
        burgerDetails = findViewById(R.id.burger_detail);
        steak = findViewById(R.id.steak);
        steakDetails = findViewById(R.id.steak_detail);
        frenchFries = findViewById(R.id.french_fries);
        frenchFriesDetails = findViewById(R.id.french_fries_detail);


        hubungi_server();

        FloatingActionButton fab = findViewById(R.id.button_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMenu.this, DetailMenu.class));
            }
        });


    }
    private void hubungi_server() {
        String url= "https://retoolapi.dev/StWODX/uasresto";
        StringRequest request = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    String pizzaName = jsonArray.getJSONObject(0).getString("foodName");
                    pizza.setText(pizzaName);
                    String pizzaDetail = jsonArray.getJSONObject(0).getString("details");
                    pizzaDetails.setText(pizzaDetail);

                    String spaghettiName = jsonArray.getJSONObject(1).getString("foodName");
                    spaghetti.setText(spaghettiName);
                    String spaghettiDetail = jsonArray.getJSONObject(1).getString("details");
                    spaghettiDetails.setText(spaghettiDetail);

                    String burgerName = jsonArray.getJSONObject(2).getString("foodName");
                    burger.setText(burgerName);
                    String burgerDetail = jsonArray.getJSONObject(2).getString("details");
                    burgerDetails.setText(burgerDetail);

                    String steakName = jsonArray.getJSONObject(3).getString("foodName");
                    steak.setText(steakName);
                    String steakDetail = jsonArray.getJSONObject(3).getString("details");
                    steakDetails.setText(steakDetail);

                    String frenchFriesName = jsonArray.getJSONObject(4).getString("foodName");
                    frenchFries.setText(frenchFriesName);
                    String frenchFriesDetail = jsonArray.getJSONObject(4).getString("details");
                    frenchFriesDetails.setText(frenchFriesDetail);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListMenu.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(ListMenu.this);
        requestQueue.add(request);
    }
}