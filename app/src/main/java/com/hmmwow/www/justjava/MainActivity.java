package com.hmmwow.www.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        if (quantity <= 100){
            quantity = quantity + 1;
        }
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
    private String displayText(String message){
//        TextView orderSummaryTextView  = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView .setText(message);

        String thanks = displayTextThankyou();

        return message + "\n" +thanks;
    }

    /*
    *     THANK YOU TEXT VIEW
     */
    public String displayTextThankyou(){
//        TextView thankYouMessage = (TextView) findViewById(R.id.thank_you_text_view);
//        thankYouMessage.setText("Thank You!");

        return getString(R.string.thank_you);
    }

    /**
     * will calculate the total price
     * @ return total price
     */
    public double calculatePrice(int count, double price, int wCream, int choco){
        double totalPrice = count * (price + wCream + choco);
        return totalPrice;
    }

    /**
     *  Summary of the order
     */

    private String createOrderSummary(Boolean whippedCream, Boolean chocolate, String name){
        int wCream = (whippedCream)?  1:0;
        int choco = (chocolate)? 3:0;


        if (quantity < 1){
            return getString(R.string.order_summary_name,name)
                    + "\nAdd whipped cream: " + whippedCream
                    + "\nAdd chocolate: " + chocolate
                    + "\nQuantity: " + quantity
                    + "\nTotal: Its Free";
        }
        else{
            double total  = calculatePrice(quantity,5,wCream,choco);
            DecimalFormat myDecimalFormat = new DecimalFormat("#.00");

            return getString(R.string.order_summary_name,name)
                    + "\nAdd whipped cream: " + whippedCream
                    + "\nAdd chocolate: " + chocolate
                    + "\nQuantity: " + quantity
                    + "\nTotal: "+ myDecimalFormat.format(total);
        }


    }


    private String subject(String name){
        return "Just Java order of " + name;
    }

    /**
     * This method is called when the order button is clicked.
     * Display the summary of the order
     * check if customer wants whippedcream
     */
    public void submitOrder(View view) {
          EditText nameText  = (EditText) findViewById(R.id.name_field);
           String name  =  nameText.getText().toString();

           CheckBox whippedCreamAdded = (CheckBox) findViewById(R.id.whipped_cream);
           Boolean hasWhippedCream = whippedCreamAdded.isChecked();

           CheckBox chocolateAdded = (CheckBox) findViewById(R.id.chocolate);
           Boolean hasChocolate = chocolateAdded.isChecked();


           String output = displayText(createOrderSummary(hasWhippedCream, hasChocolate, name));


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_SUBJECT, subject(name));
        intent.putExtra(intent.EXTRA_TEXT, output);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }


}