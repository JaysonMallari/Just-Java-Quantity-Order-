package com.hmmwow.www.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /* INCREMENT */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /* DECREMENT */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity -= 1;
        }
        displayQuantity(quantity);

    }


    /*
    * THIS METHOD WILL GIVE TXT TO THE SCREEN
     */
    private void displayText(String message){
        TextView orderSummaryTextView  = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView .setText(message);
    }

    /*
    *     THANK YOU TEXT VIEW
     */
    public void displayTextThankyou(){
        TextView thankYouMessage = (TextView) findViewById(R.id.thank_you_text_view);
        thankYouMessage.setText("Thank You!");
    }

    /**
     * will calculate the total price
     * @ return total price
     */
    public double calculatePrice(int count, double price){
        double totalPrice = count * price;
        return totalPrice;
    }

    /**
     *  Summary of the order
     */

    private String createOrderSummary(){
        if (quantity < 1){
            return "Name: Jayson Mallari \nQuantity: "+ quantity + "\nTotal: Its Free";
        }
        else{
            double total  = calculatePrice(quantity,3.5);
            DecimalFormat myDecimalFormat = new DecimalFormat("#.00");

            return "Name: Jayson Mallari \nQuantity: "+ quantity + "\nTotal: "+ myDecimalFormat.format(total);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

            displayText(createOrderSummary());
            displayTextThankyou();

    }


}