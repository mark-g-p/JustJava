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
        quantity += 1;
        displayQuality(quantity);
    }

    public void decrement(View view) {
        int quantity = getQuantity();
        if (quantity > 0)
            quantity -= 1;
        displayQuality(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int pricePerCup = 9;
        CheckBox checkBoxWhippedCream = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkBoxWhippedCream.isChecked();
        CheckBox checkBoxChocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkBoxChocolate.isChecked();
        EditText nameEditText = findViewById(R.id.name_field);
        String name = nameEditText.getText().toString();
        String summaryMessage = createOrderSummary(calculatePrice(pricePerCup), hasWhippedCream, hasChocolate, name);
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
     * pricePerCup is the price per cup of coffee
     */
    private int calculatePrice(int pricePerCup) {
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

    private String createOrderSummary(int priceOfOrder, boolean addWhippedCream, boolean addChocolate, String name) {

        return "Name: " + name + newline +
                String.format("Add %s? ", getString(R.string.whipped_cream).toLowerCase()) +
                addWhippedCream + newline +
                String.format("Add %s? ", getString(R.string.chocolate).toLowerCase()) +
                addChocolate + newline +
                getString(R.string.quantity) + ": " + getQuantity() + newline +
                "Total: " + NumberFormat.getCurrencyInstance().format(priceOfOrder) + newline +
                getString(R.string.thanks);

    }

}