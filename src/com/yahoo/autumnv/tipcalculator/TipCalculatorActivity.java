package com.yahoo.autumnv.tipcalculator;

import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {
	private static final NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);

	private Double currentTipPercent = 0.0;
	private Double totalBill = 0.0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        
        addCustomPctListener();
        addBillListener();
        
    }
    
    
    
    private void addBillListener() {
    	EditText etBillListener = (EditText) findViewById(R.id.etBillAmount);
    	etBillListener.addTextChangedListener(new TextWatcher() {
			
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
					totalBill = Double.valueOf(s.toString());
				} else {
					totalBill = 0.0;
				}
				processTipAmount();
			}
		});
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
					setCurrentTipPercent(Double.valueOf(s.toString()) / 100);
				} else {
					setCurrentTipPercent(0.0);
				}
				processTipAmount();
			}
		});
    }
	
	private void setCurrentTipPercent(double tipPercent) {
		currentTipPercent = tipPercent;
		TextView tipPctView = (TextView)findViewById(R.id.current_tip_pct);
		tipPctView.setText(String.valueOf(currentTipPercent * 100));
	}
    
    public void on15PctBtnClicked(View v) {
    	setCurrentTipPercent(0.15);
    	processTipAmount();
    }
    
    public void on10PctBtnClicked(View v) {
    	setCurrentTipPercent(0.1);
    	processTipAmount();
    }
    
    public void on20PctBtnClicked(View v) {
    	setCurrentTipPercent(0.2);
    	processTipAmount();
    }
    
    public void processTipAmount() {
        double totalTipAmount = calculateTipAmount(currentTipPercent, totalBill);
		String tipTotal = formatCurrency(totalTipAmount);
        updateTipAmountView(tipTotal);
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
	
	public void onCheckBoxClicked(View v) {
		CheckBox checkBox = (CheckBox)findViewById(R.id.cb_color_green);
		int color = Color.rgb(116, 65, 145);
		if (checkBox != null && checkBox.isChecked()) {
			color = Color.rgb(0, 170, 113);
		}
		setBackgroundColorOfView(R.id.btn10PctTip, color);
		setBackgroundColorOfView(R.id.btn15PctTip, color);
		setBackgroundColorOfView(R.id.btn20PctTip, color);

	}



	private void setBackgroundColorOfView(int id, int color) {
		View view = findViewById(id);
		view.setBackgroundColor(color);
	}
	
	
}
