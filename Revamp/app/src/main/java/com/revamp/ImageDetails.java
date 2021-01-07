package com.revamp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ImageDetails extends AppCompatActivity {
    TextView tvItemName, tvItemAdress;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        tvItemName = (TextView) findViewById(R.id.item_name);
        tvItemAdress = (TextView) findViewById(R.id.item_adress);
        img = (ImageView) findViewById(R.id.img);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String j = (String) b.get("object_name");


            if (j.equalsIgnoreCase("Bench")) {
                img.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.bench));
                tvItemName.setText("Swati Furnitures");
                tvItemAdress.setText("5,6 Shikhar Complex, Opposite. Navneet Prakashan, Gurukul Road, Nilmani Society, Memnagar, Ahmedabad, Gujarat 380052 \nCell Number: 098250 30061");
            } else if (j.equalsIgnoreCase("Chair")) {
                img.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.chair));
                tvItemName.setText("Henry Furnitures");
                tvItemAdress.setText("825 Shivalik Complex, Opposite. Navneet Prakashan, Gurukul Road, Nilmani Society, Memnagar, Ahmedabad, Gujarat 380052 \nCell Number: 098250 30061");

            } else if (j.equalsIgnoreCase("Stool")) {
                img.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.stool));

                tvItemName.setText("Kala Furnitures");
                tvItemAdress.setText("830 Shiv Complex, Opposite. Navneet Prakashan, Gurukul Road, Nilmani Society, Memnagar, Ahmedabad, Gujarat 380052 \nCell Number: 098250 30061");

            } else if (j.equalsIgnoreCase("Shelf")) {
                img.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.self));

                tvItemName.setText("Modern Furnitures");
                tvItemAdress.setText("100 Titanium Complex, Opposite. Navneet Prakashan, Gurukul Road, Nilmani Society, Memnagar, Ahmedabad, Gujarat 380052 \nCell Number: 098250 30061");

            } else if (j.equalsIgnoreCase("Table")) {
                img.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.table));

                tvItemName.setText("Platinum Furnitures");
                tvItemAdress.setText("100 Titanium Square, Opposite. Navneet Prakashan, Gurukul Road, Nilmani Society, Memnagar, Ahmedabad, Gujarat 380052 \nCell Number: 098250 30061");

            }
        }
    }
}
