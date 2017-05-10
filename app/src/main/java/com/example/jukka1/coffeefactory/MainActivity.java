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
    int americanPrice = 2;
    int espressoQPrice = 2;
    int cappuccinoPrice = 3;
    int lattePrice = 3;
    int specialPrice = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = "";

        if (price == 0) {
            priceMessage = "Your cart is empty. Please choose something delicious!";
            displayMessage(priceMessage);
            hideOrderButton();
        } else {
            priceMessage = "Total price is " + price + "€ \n" +
                    "Please click ORDER-button to confirm your order!";
            displayMessage(priceMessage);
            displayOrderButton();
        }
    }

    /**
     * This method calculates total price of the order
     */
    private int calculatePrice() {
        int price = 0;
        price += americanQuantity * americanPrice;
        price += espressoQuantity * espressoQPrice;
        price += cappuccinoQuantity * cappuccinoPrice;
        price += latteQuantity * lattePrice;
        price += specialQuantity * specialPrice;
        return price;
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
     * This method hides the Order-button if Update cart-button is pressed but cart is empty
     */
    private void hideOrderButton() {
        Button orderButton = (Button) findViewById(R.id.order_button_view);
        orderButton.setVisibility(View.INVISIBLE);
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