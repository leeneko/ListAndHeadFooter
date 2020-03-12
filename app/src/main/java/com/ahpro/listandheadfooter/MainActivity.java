package com.ahpro.listandheadfooter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button header_menu;
    private TextView header_title;
    private DrawerLayout drawer;

    private Button footer_01;
    private Button footer_02;
    private Button footer_03;
    private Button footer_04;
    private Button footer_05;

    private List<ListItem> arData;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        menuSetting();
        listViewSet();
        SubMenuSet();
    }

    private void menuSetting() {
        // drawer = new DrawerLayout(getApplicationContext());
        // drawer = new DrawerLayout(MainActivity.this);
        // drawer = findViewById(R.id.drawer);
        // drawer = findViewById(R.id.drawer_layout);
        // drawer = findViewById(R.id.drawer_nav);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        header_menu = findViewById(R.id.header_menu);
        header_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                }
                /*
                // Snackbar.make(v, "메뉴 버튼 클릭", Snackbar.LENGTH_LONG).show();
                //AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("AlertDialog Title");
                builder.setMessage("메뉴 버튼을 클릭했습니다");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(v, "Positive 클릭", Snackbar.LENGTH_LONG).show();
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(v, "Negative 클릭", Snackbar.LENGTH_LONG).show();
                            }
                        });
                builder.show();
                */
            }
        });
        header_title = findViewById(R.id.header_title);
        header_title.setText("Main Activity");

        footer_01 = findViewById(R.id.footer_button_01);

        footer_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("ItemCode", "item0");
                startActivityForResult(intent, 1);
            }
        });

        footer_02 = findViewById(R.id.footer_button_02);
        footer_03 = findViewById(R.id.footer_button_03);
        footer_04 = findViewById(R.id.footer_button_04);
        footer_05 = findViewById(R.id.footer_button_05);
    }

    private void listViewSet() {
        arData = new ArrayList<>();
        adapter = new ListAdapter(this, R.layout.list_item, arData);
        adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                /*
                if (pos != -1) {
                    Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                    intent.putExtra("ItemCode", arData.get(pos).getItemCode());
                    startActivityForResult(intent, 1);
                }
                 */
            }
        });

        RecyclerView rv = findViewById(R.id.rv_main);
        rv.setNestedScrollingEnabled(false);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        for (int i = 0; i < 30; i++) {
            arData.add(new ListItem("" + i, "item" + i, i + "아이템", "2020-03-" + i, "2020-03-" + i, "" + i, "EA", "0", "비고 테스트 " + i));
        }

        rv.setAdapter(adapter);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void SubMenuSet() {
        final Map<String, Class<?>> btnActs = new LinkedHashMap<>();
        ListView listView = findViewById(R.id.test_nav_list);
        try {
            // menulist.json 파일 읽어오기
            AssetManager am = getResources().getAssets();
            InputStream is = am.open("menulist.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            // 읽어온 json 파일 파싱해서 메뉴로 사용할 수 있는 Map으로 변경
            String data = response.toString();
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String key = jsonObject.getString("key");
                String value = jsonObject.getString("value");
                // Log.d("롴_키, 밸류 ", key + ", " + value);
                Class<?> c = Class.forName(value);
                btnActs.put(key, c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 메뉴와 ListView 연결
        final List<String> arData = new ArrayList<>();
        for (String key: btnActs.keySet()) {
            arData.add(key);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arData);
        listView.setAdapter(adapter);
        // ListView 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView lv = (ListView) parent;
                String item = (String) lv.getItemAtPosition(position);
                // Log.d("롴_선택된 아이템 ", String.valueOf(btnActs.get(item)));
                Intent intent = new Intent(getApplicationContext(), btnActs.get(item));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String popupItemCode = data.getStringExtra("ItemCode");
                String popupBatchNum = data.getStringExtra("BatchNum");
                String popupQuantity = data.getStringExtra("Quantity");

                for (int i = 0; i < arData.size(); i++) {
                    if (arData.get(i).getItemCode().equals(popupItemCode)) {
                        arData.get(i).setCmplQty(popupQuantity);
                    }
                }

                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
