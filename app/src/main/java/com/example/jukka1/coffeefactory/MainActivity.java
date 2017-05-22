package com.example.jukka1.coffeefactory;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
     * This method is called when the Update cart/Order button is clicked.
     * if price is 0, the guide the user to choose someting.
     * If some of the required fields are empty, guide the user.
     */
    public void updateCart(View view) {
        int price = calculatePrice();
        
        String priceMessage = "";

        if (price == 0 && getName().isEmpty()) {
            priceMessage = "Guide:";
            priceMessage += "\n- Your cart is empty. Please choose something delicious!";
            priceMessage += "\n- Insert name, please.";
            displayMessage(priceMessage);
            hideOrderButton();
        } else if (getName().isEmpty()) {
            priceMessage = "Insert name, please.";
            displayMessage(priceMessage);
            hideOrderButton();
        } else if (price == 0) {
            priceMessage = "Your cart is empty. Please choose something delicious!";
            displayMessage(priceMessage);
            hideOrderButton();
        } else {
            displayMessage(createOrderSummary(price));
            displayOrderButton();
        }
    }

    /**
     * This method is called when the Order button is clicked.
     */
    public void verifyOrder(View view) {
        Toast.makeText(MainActivity.this, "Thank you!", Toast.LENGTH_SHORT).show();
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
     * This method returns String value of the current state of EditText nameField
     */
    private String getName() {
        EditText nameField = (EditText) findViewById(R.id.name_edittext_view);
        String name = nameField.getText().toString();
        return name;
    }

    /**
     * This method creates a order summary and returns it as an String
     */
    private String createOrderSummary(int price) {
        String priceMessage = "Name: " + getName() +
                "\nTotal price is " + price + "€ \n" +
                "\nOrder list:\n";

        if (americanQuantity > 0) {
            priceMessage += americanQuantity + " x American\n";
        }
        if (espressoQuantity > 0) {
            priceMessage += espressoQuantity + " x Espresso\n";
        }
        if (cappuccinoQuantity > 0) {
            priceMessage += cappuccinoQuantity + " x Cappuccino\n";
        }
        if (latteQuantity > 0) {
            priceMessage += latteQuantity + " x Latte\n";
        }
        if (specialQuantity > 0) {
            priceMessage += specialQuantity + " x Special coffee\n";
        }

        priceMessage += "\nPlease click ORDER-button to confirm your order!";
        return priceMessage;
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
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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