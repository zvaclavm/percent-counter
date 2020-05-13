package lt.vtmc.ems.percentcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText;
    private TextView amountTextView;

    private TextView percentTextView;
    private SeekBar seekBar;

//    private TextView tipLabelTextView;
    private TextView tipTextView;

//    private TextView totalLabelTextView;
    private TextView totalTextView;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double amount = 0.0;
    private double percent = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);

        percentTextView = (TextView) findViewById(R.id.percentTextView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

//        tipLabelTextView = (TextView) findViewById(R.id.tipLabelTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);

//        totalLabelTextView = (TextView) findViewById(R.id.totalLabelTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    amount = Double.parseDouble(s.toString()) / 100.0;
                    amountTextView.setText(currencyFormat.format(amount));
                } catch (NumberFormatException ex){
                    amountTextView.setText("");
                    amount = 0.0;
                }

                calculateTipAndTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percent = progress / 100.0;
                calculateTipAndTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void calculateTipAndTotal() {
        double tipAmount = amount * percent;
        double totalAmount = amount + tipAmount;

        percentTextView.setText(percentFormat.format(percent));
        tipTextView.setText(currencyFormat.format(tipAmount));
        totalTextView.setText(currencyFormat.format(totalAmount));
    }
}
