package com.example.jukka1.coffeefactory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int americanQuantity = 0;
    int espressoQuantity = 0;
    int cappuccinoQuantity = 0;
    int latteQuantity = 0;
    int specialQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = 0;
        String priceMessage = "";
        price += americanQuantity * 2;
        price += espressoQuantity * 2;
        price += cappuccinoQuantity * 3;
        price += latteQuantity * 3;
        price += specialQuantity * 7;

        if (price == 0) {
            priceMessage = "Your cart is empty. Please choose something delicious!";
            displayMessage(priceMessage);

        } else {
            priceMessage = "Total price is " + price + "€ \n" +
                    "Please click ORDER-button to confirm your order!";
            displayMessage(priceMessage);
            displayOrderButton();
        }
    }

    /**
     * These methods displays the given Quantity value of the chosen coffees.
     */
    private void displayAmerican(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.american_quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayEspresso(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.espresso_quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayCappuccino(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.cappuccino_quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayLatte(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.latte_quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displaySpecial(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.special_quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given message on the screen
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays Order-button after Update Cart-button is clicked
     */
    private void displayOrderButton() {
        Button orderButton = (Button) findViewById(R.id.order_button_view);
        orderButton.setVisibility(View.VISIBLE);
    }

    /**
     * These methods decreases or increases the Quantity of chosen coffees by 1
     */

    public void americanIncrement(View view) {
        americanQuantity++;
        displayAmerican(americanQuantity);
    }

    public void americanDecrement(View view) {
        if (americanQuantity > 0) {
            americanQuantity--;
        }
        displayAmerican(americanQuantity);
    }

    public void espressoIncrement(View view) {
            espressoQuantity++;
        displayEspresso(espressoQuantity);
    }

    public void espressoDecrement(View view) {
        if (espressoQuantity > 0) {
            espressoQuantity--;
        }
        displayEspresso(espressoQuantity);
    }

    public void cappuccinoIncrement(View view) {
        cappuccinoQuantity++;
        displayCappuccino(cappuccinoQuantity);
    }

    public void cappuccinoDecrement(View view) {
        if (cappuccinoQuantity > 0) {
            cappuccinoQuantity--;
        }
        displayCappuccino(cappuccinoQuantity);
    }

    public void latteIncrement(View view) {
        latteQuantity++;
        displayLatte(latteQuantity);
    }

    public void latteDecrement(View view) {
        if (latteQuantity > 0) {
            latteQuantity--;
        }
        displayLatte(latteQuantity);
    }

    public void specialIncrement(View view) {
        specialQuantity++;
        displaySpecial(specialQuantity);
    }

    public void specialDecrement(View view) {
        if (specialQuantity > 0) {
            specialQuantity--;
        }
        displaySpecial(specialQuantity);
    }
}