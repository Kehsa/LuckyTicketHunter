package com.blackcube.kehsa.luckytickethunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LuckyTicketCalculator luckyTicketCalculator = new LuckyTicketCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        luckyTicketCalculator.setNumberSize(6);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    final public void buttonCalcClick(View v) {
        EditText numTextEditor = (EditText) findViewById(R.id.editNumberText);
        String numStr = numTextEditor.getText().toString();
        TextView ansverView = (TextView) findViewById(R.id.ansverView);

        if (numStr.length() == 0) {
            ansverView.setText(R.string.errNoNumber);
            return;
        }
        StringBuilder sb = new StringBuilder();
        int nextMan = luckyTicketCalculator.nextLuckyMan(numStr);
        System.out.println(nextMan);
        switch (nextMan) {
            case -1:
                String[] text_ansver = getResources().getStringArray(R.array.errBadNum);
                sb.append(text_ansver[0]);
                sb.append(" ");
                sb.append(luckyTicketCalculator.getNumberSize());
                sb.append(" ");
                sb.append(text_ansver[1]);
                break;
            case 0:
                sb.append(getResources().getString(R.string.isLuck));
                break;
            case 1:
                sb.append(getResources().getString(R.string.isNext));
                break;
            default:
                text_ansver = getResources().getStringArray(R.array.isunluck);
                sb.append(text_ansver[0]);
                sb.append(" ");
                sb.append(nextMan);
                sb.append(" ");
                sb.append(text_ansver[1]);
        }
        char[] buf = sb.toString().toCharArray();
        ansverView.setText(buf, 0, buf.length);
    }
}
