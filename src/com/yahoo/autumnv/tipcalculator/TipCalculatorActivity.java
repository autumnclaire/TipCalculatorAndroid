package com.yahoo.autumnv.tipcalculator;

import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {
	private static final NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        
        addCustomPctListener();
        
    }
    
    private void addCustomPctListener() {
    	EditText etCustomPct = (EditText) findViewById(R.id.etCustomPct);
    	etCustomPct.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//do nothing
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//do nothing
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (s != null && s.length() > 0) {
					Double tipPct = Double.valueOf(s.toString());
						onPctBtnClicked( tipPct / 100);
				}
				else {
					onPctBtnClicked(0);
				}
			}
		});
    }
    
    public void on15PctBtnClicked(View v) {
    	onPctBtnClicked(0.15);
    }
    
    public void on10PctBtnClicked(View v) {
    	onPctBtnClicked(0.1);
    }
    
    public void on20PctBtnClicked(View v) {
    	onPctBtnClicked(0.2);
    }
    
    public void onPctBtnClicked(double percent) {
    	Double totalBill = getTotalBill();
        double totalTipAmount = calculateTipAmount(percent, totalBill);
		String tipTotal = formatCurrency(totalTipAmount);
        updateTipAmountView(tipTotal);
    }

	private Double getTotalBill() {
		TextView tvTotalBill = (TextView) findViewById(R.id.etBillAmount);
        CharSequence text = tvTotalBill.getText();
        if (text == null || text.length() == 0) {
        	text = "0.00";
        }
		Double totalBill = Double.valueOf(text.toString());
		return totalBill;
	}

	private double calculateTipAmount(double percent, Double totalBill) {
		return totalBill * percent;
	}

	private void updateTipAmountView(String tipTotal) {
		TextView findViewById = (TextView) findViewById(R.id.tvTipAmount);
        findViewById.setText(tipTotal);
	}
    
	public static String formatCurrency(Double coord) {
		return df.format(coord);
	}
	
	
}
