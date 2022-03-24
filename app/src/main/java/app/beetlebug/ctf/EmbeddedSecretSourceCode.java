package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class EmbeddedSecretSourceCode extends AppCompatActivity {
    public String beetle_bug_shop_promo_code = "beetle1759";
    EditText m_promo;
    TextView m_price;
    Button m_button, m_purchase;
    SharedPreferences sharedPreferences;
    public static String flag_scores = "flag_scores";
    public static String ctf_score_secret_source = "ctf_score_secret_source";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_secret_source_code);

        m_promo = findViewById(R.id.editTextPromoCode);
        m_button = findViewById(R.id.button);
        m_purchase = findViewById(R.id.buttonPurchase);

        sharedPreferences = getSharedPreferences(flag_scores, Context.MODE_PRIVATE);

        m_purchase.setVisibility(View.GONE);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_price = findViewById(R.id.textViewPrice);

                if (m_promo.getText().toString().equals(beetle_bug_shop_promo_code)) {
                    String price = m_price.getText().toString();
                    int price_int = Integer.parseInt(price);
                    int new_price = price_int/2;
                    String s = Integer.toString(new_price);
                    m_price.setText(s);

                    Toast.makeText(EmbeddedSecretSourceCode.this, "Your new price is: " + new_price, Toast.LENGTH_LONG).show();
                    int user_score_secret_source = 9;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(ctf_score_secret_source, user_score_secret_source);
                    editor.commit();

                    m_purchase.setVisibility(View.VISIBLE);
                    m_purchase.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent ctf_captured = new Intent(EmbeddedSecretSourceCode.this, FlagCaptured.class);
                            startActivity(ctf_captured);
                        }
                    });



                } else {
                    Toast.makeText(EmbeddedSecretSourceCode.this, "Wrong discount code", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void purchaseItem(View view) {
        Intent i = new Intent(EmbeddedSecretSourceCode.this, FlagCaptured.class);
        startActivity(i);
    }
}