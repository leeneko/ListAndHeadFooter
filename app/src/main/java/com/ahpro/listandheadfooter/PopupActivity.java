package com.ahpro.listandheadfooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class PopupActivity extends Activity {

    private Intent intent;

    TextView etItemCode;
    TextView etBatchNumber;
    TextView etQuantity;
    Button btnSubmit;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀바 없애기
        setContentView(R.layout.activity_popup);


        init();
    }

    private void init() {
        SetActivity();
        SetBtnEvent();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) { // 팝업 바깥을 눌러도 안닫히게
            return false;
        }
        return super.onTouchEvent(event);
    }

    private void SetActivity() {
        intent = getIntent();
        String itemCode = intent.getStringExtra("ItemCode");
        etItemCode = findViewById(R.id.etItemCode);
        etItemCode.setText(itemCode);
        etBatchNumber = findViewById(R.id.etBatchNumber);
        etQuantity = findViewById(R.id.etQuantity);

        etQuantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        btnSubmit.callOnClick();
                        break;
                }
                return true;
            }
        });

        etQuantity.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void SetBtnEvent() {
        btnSubmit.setOnClickListener(new View.OnClickListener() { // 확인 버튼
            @Override
            public void onClick(View v) {
                // 키보드 숨기기
                InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                int i_qty;
                String s_qty = String.valueOf(etQuantity.getText());

                if (!s_qty.equals("")) {
                    i_qty = Integer.parseInt(s_qty);
                } else {
                    i_qty = 0;
                }

                if (i_qty >= 0) {
                    Intent intent = new Intent();
                    intent.putExtra("ItemCode", String.valueOf(etItemCode.getText()));
                    intent.putExtra("BatchNum", String.valueOf(etBatchNumber.getText()));
                    intent.putExtra("Quantity", String.valueOf(i_qty));
                    setResult(RESULT_OK, intent);
                } else {

                    setResult(RESULT_CANCELED);
                    finish();
                }
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() { // 취소 버튼
            @Override
            public void onClick(View v) {
                // 키보드 숨기기
                InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                // 결과를 취소로 리턴
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
