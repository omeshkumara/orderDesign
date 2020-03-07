package com.o.just_java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity= 0;
    int price = 5;
    int whippedCreamPrice=0;
    int chocolatePrice=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        EditText text = (EditText)findViewById(R.id.name_field);
        String personName = text.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        boolean hasWhippedCream= whippedCreamCheckBox.isChecked();

        CheckBox chocolateBox = (CheckBox) findViewById(R.id.chocolateBox);
        boolean hasChocolate= chocolateBox.isChecked();

        if(hasWhippedCream)
            {
                whippedCreamPrice=2;
            }
            else {
            whippedCreamPrice=0;
            }
        if(hasChocolate) {
            chocolatePrice=3;
        }
        else {
            chocolatePrice=0;
        }

        String priceMessage=getString(R.string.order_summary_name, personName) +
                "\nQuantity: " +quantity+
                "\nwippedCream: " + hasWhippedCream +
                "\nChocolate: " + hasChocolate +
                "\nTotal $ "+ quantity* (price +whippedCreamPrice +chocolatePrice) +
                "\nThankyou";
        displayMessage(priceMessage);
        // displayPrice(quantity*5);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "omeshkumarfso@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "hi");
        intent.putExtra(Intent.EXTRA_STREAM, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Increment(View view)
    {
        if (quantity==100)
        {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity= quantity + 1;
        display(quantity);
    }

    public void Decrement(View view)
    {
        if (quantity==1)
        {
            Toast.makeText(this, "You cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity= quantity - 1;
        display(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}