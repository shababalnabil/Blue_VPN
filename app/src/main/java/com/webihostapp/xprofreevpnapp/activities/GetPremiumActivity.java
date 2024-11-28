package com.webihostapp.xprofreevpnapp.activities;

import static com.webihostapp.xprofreevpnapp.utils.BillConfig.IN_PURCHASE_KEY;
import static com.webihostapp.xprofreevpnapp.utils.BillConfig.One_Month_Sub;
import static com.webihostapp.xprofreevpnapp.utils.BillConfig.One_Year_Sub;
import static com.webihostapp.xprofreevpnapp.utils.BillConfig.PRIMIUM_STATE;
import static com.webihostapp.xprofreevpnapp.utils.BillConfig.Six_Month_Sub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.webihostapp.xprofreevpnapp.Preference;
import com.webihostapp.xprofreevpnapp.R;

import java.util.ArrayList;
import java.util.List;


public class GetPremiumActivity extends AppCompatActivity {

    public String SKU_DELAROY_MONTHLY;
    public String SKU_DELAROY_SIXMONTH;
    public String SKU_DELAROY_YEARLY;
    public String base64EncodedPublicKey;
    LinearLayout one_month, six_months, twelve_months;
    TextView one_month_sub_cost, six_months_sub_cost, one_year_sub_cost;

    boolean mSubscribedToDelaroy = false;
    String mDelaroySku = "";
    boolean mAutoRenewEnabled = false;
    private Preference preference;



    @SuppressLint("SetTextI18n")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_premium);

        finish();

        preference = new Preference(GetPremiumActivity.this);
        one_month = findViewById(R.id.one_month_layout);
        six_months = findViewById(R.id.six_months_layout);
        twelve_months = findViewById(R.id.twelve_months_layout);
        one_month_sub_cost = findViewById(R.id.one_month_sub_cost);
        six_months_sub_cost = findViewById(R.id.six_months_sub_cost);
        one_year_sub_cost = findViewById(R.id.one_year_sub_cost);


        base64EncodedPublicKey = preference.getStringpreference(IN_PURCHASE_KEY, base64EncodedPublicKey);
        SKU_DELAROY_MONTHLY = preference.getStringpreference(One_Month_Sub, SKU_DELAROY_MONTHLY);
        SKU_DELAROY_SIXMONTH = preference.getStringpreference(Six_Month_Sub, SKU_DELAROY_SIXMONTH);
        SKU_DELAROY_YEARLY = preference.getStringpreference(One_Year_Sub, SKU_DELAROY_YEARLY);


        setUpBilling();


    }

    private void setUpBilling() {

        //Initialize a BillingClient with PurchasesUpdatedListener onCreate method



        //start the connection after initializing the billing client
        establishConnection();

    }


    void establishConnection() {

    }


    @SuppressLint("SetTextI18n")
    void showProducts() {

//        ImmutableList<QueryProductDetailsParams.Product> productList = ImmutableList.from(
//                //Product 1
//                QueryProductDetailsParams.Product.newBuilder()
//                        .setProductId(SKU_DELAROY_MONTHLY)
//                        .setProductType(BillingClient.ProductType.SUBS)
//                        .build(),
//
//                //Product 2
//                QueryProductDetailsParams.Product.newBuilder()
//                        .setProductId(SKU_DELAROY_SIXMONTH)
//                        .setProductType(BillingClient.ProductType.SUBS)
//                        .build(),
//
//                //Product 3
//                QueryProductDetailsParams.Product.newBuilder()
//                        .setProductId(SKU_DELAROY_YEARLY)
//                        .setProductType(BillingClient.ProductType.SUBS)
//                        .build()
//        );

        /*QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();*/

        /*billingClient.queryProductDetailsAsync(
                params,
                (billingResult, prodDetailsList) -> {
                    // Process the result
                    productDetailsList.clear();
                    productDetailsList.addAll(prodDetailsList);
                    Log.e("TAG", productDetailsList.size() + " number of products");
                }
        );*/

    }


    void alert(String message) {
        android.app.AlertDialog.Builder bld = new android.app.AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        bld.create().show();
    }

    private void unlockdata() {
        if (mSubscribedToDelaroy) {
            unlock();
        } else {
            preference.setBooleanpreference(PRIMIUM_STATE, false);
        }
    }

    public void unlock() {
        preference.setBooleanpreference(PRIMIUM_STATE, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
