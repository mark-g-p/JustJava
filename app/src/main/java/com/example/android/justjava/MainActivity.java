/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuality(0);
    }

    /**
     * This method increases quantity
     */
    public void increment(View view) {
        int quantity = getQuantity();
        if (quantity < 100) {
            quantity += 1;
        }
        displayQuality(quantity);
    }

    public void decrement(View view) {
        int quantity = getQuantity();
        if (quantity > 1) {
            quantity -= 1;
        }
        displayQuality(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        // Check if user ordered Whipped Cream
        CheckBox checkBoxWhippedCream = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkBoxWhippedCream.isChecked();

        // Check if user ordered Chocolate
        CheckBox checkBoxChocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkBoxChocolate.isChecked();

        //Check user name.
        EditText nameEditText = findViewById(R.id.name_field);
        String name = nameEditText.getText().toString();
        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);
        String summaryMessage = createOrderSummary(totalPrice, name, hasWhippedCream, hasChocolate);
        displayMessage(summaryMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private int getQuantity() {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        return Integer.parseInt(quantityTextView.getText().toString());
    }

    private void displayQuality(int numberOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(getString(R.string.number_of_coffees, numberOfCoffees));
    }

    /**
     * Calculates the price of the order.
     * <p>
     *
     * @param hasWhippedCream does user want whipped cream
     * @param hasChocolate    does user want chocolate
     *                        pricePerCup is the price per cup of coffee
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int pricePerCup = 9;

        // Calculate new price per cup.
        if (hasWhippedCream) {
            pricePerCup += 1;
        }
        if (hasChocolate) {
            pricePerCup += 2;
        }

        return getQuantity() * pricePerCup;

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public static String newline = System.getProperty("line.separator");

    /**
     * @param priceOfOrder    total price of order
     * @param addWhippedCream order contains whipped cream
     * @param addChocolate    order contains chocolate
     * @return text summary of whole order
     */

    private String createOrderSummary(int priceOfOrder, String name, boolean addWhippedCream, boolean addChocolate) {

        return getString(R.string.order_summary_name, name) + newline +
                getString(R.string.order_summary_whipped_cream, addWhippedCream) + newline +
                getString(R.string.order_summary_chocolate, addChocolate) + newline +
                getString(R.string.order_summary_quantity, getQuantity()) + newline +
                getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(priceOfOrder)) + newline +
                getString(R.string.thank_you);

    }

}