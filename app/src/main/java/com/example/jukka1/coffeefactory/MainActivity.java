package com.example.jukka1.coffeefactory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
     * if price is 0, the guide the user to choose something.
     * If some of the required fields are empty, guide the user.
     */
    public void updateCart(View view) {
        int price = calculatePrice();
        String priceMessage = "";
        boolean isReadyToOrder = true;

        if (price == 0) {
            priceMessage += "Guide: Your cart is empty. Please choose something delicious!\n";
            isReadyToOrder = false;
        }
        if (getName().isEmpty()) {
            priceMessage += "Guide: Insert name, please.\n";
            isReadyToOrder = false;
        }
        if (isValidEmail(getEmail()) == false) {
            priceMessage += "Guide: Insert valid email address, please.";
            isReadyToOrder = false;
        }
        if (isReadyToOrder == true) {
            displayMessage(createOrderSummary(price));
            displayOrderButton();
            return;
        }
        displayMessage(priceMessage);
        hideOrderButton();
    }

    /**
     * This method opens email app with filled information about order to send order confirmation via email.
     */
    public void emailOrder(String[] addresses, String subject, String message) {
        String mssg = "Thank you for your order!\n\n";
        mssg += message;

        if(mssg.lastIndexOf("\n")>0) {
            mssg = mssg.substring(0, mssg.lastIndexOf("\n"));
        }

        mssg += "\n Regards,\nCoffee Factory team\n";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, mssg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * This method is called when the Order button is clicked.
     */
    public void verifyOrder(View view) {
        Toast.makeText(this, "Thank you!", Toast.LENGTH_SHORT).show();
        String message = createOrderSummary(calculatePrice());
        String[] addresses = new String[1];
        addresses[0] = getEmail();

        emailOrder(addresses, "Coffee Factory, order confirmation", message);
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
     * This method returns String value of the current state of EditText nameField
     */
    private String getEmail() {
        EditText emailField = (EditText) findViewById(R.id.email_edittext_view);
        String emailAddress = emailField.getText().toString();
        if (isValidEmail(emailAddress) == true) {
            return emailAddress;
        }
        return "not valid email";
    }

    /**
     * This method creates a order summary and returns it as an String
     */
    private String createOrderSummary(int price) {
        String priceMessage = "Name: " + getName() +
                "\nEmail: " + getEmail() + "\n" +
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

        priceMessage += "\nTotal price is " + price + "€ \n";
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
        if (americanQuantity < 100) {
            americanQuantity++;
        } else {
            Toast.makeText(this, "You cannot order more than 100 american coffees", Toast.LENGTH_SHORT).show();
        }
        displayAmerican(americanQuantity);
    }

    public void americanDecrement(View view) {
        if (americanQuantity > 0) {
            americanQuantity--;
        }
        displayAmerican(americanQuantity);
    }

    public void espressoIncrement(View view) {
        if (espressoQuantity < 100) {
            espressoQuantity++;
        } else {
            Toast.makeText(this, "You cannot order more than 100 espressos", Toast.LENGTH_SHORT).show();
        }
        displayEspresso(espressoQuantity);
    }

    public void espressoDecrement(View view) {
        if (espressoQuantity > 0) {
            espressoQuantity--;
        }
        displayEspresso(espressoQuantity);
    }

    public void cappuccinoIncrement(View view) {
        if (cappuccinoQuantity < 100) {
            cappuccinoQuantity++;
        } else {
            Toast.makeText(this, "You cannot order more than 100 cappuccinos", Toast.LENGTH_SHORT).show();
        }

        displayCappuccino(cappuccinoQuantity);
    }

    public void cappuccinoDecrement(View view) {
        if (cappuccinoQuantity > 0) {
            cappuccinoQuantity--;
        }
        displayCappuccino(cappuccinoQuantity);
    }

    public void latteIncrement(View view) {
        if (latteQuantity < 100) {
            latteQuantity++;
        } else {
            Toast.makeText(this, "You cannot order more than 100 lattes", Toast.LENGTH_SHORT).show();
        }
        displayLatte(latteQuantity);
    }

    public void latteDecrement(View view) {
        if (latteQuantity > 0) {
            latteQuantity--;
        }
        displayLatte(latteQuantity);
    }

    public void specialIncrement(View view) {
        if (specialQuantity < 50) {
            specialQuantity++;
        } else {
            Toast.makeText(this, "You cannot order more than 50 Coffee Factor specials", Toast.LENGTH_SHORT).show();
        }
        displaySpecial(specialQuantity);
    }

    public void specialDecrement(View view) {
        if (specialQuantity > 0) {
            specialQuantity--;
        }
        displaySpecial(specialQuantity);
    }


    /**
     * This method tries to validate user given email address
     */
    public final static boolean isValidEmail(CharSequence emailAddress) {
        if (emailAddress == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
        }
    }

}