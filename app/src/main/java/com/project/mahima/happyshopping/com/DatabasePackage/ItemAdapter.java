package com.project.mahima.happyshopping.com.DatabasePackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mahima.happyshopping.Item;
import com.project.mahima.happyshopping.R;
import com.project.mahima.happyshopping.UserActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahima on 23/4/17.
 */
public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<Item> itemList;
    private DbHelper dbHelper;
    private MyDatabase database;
    private boolean isCart;

    public ItemAdapter(Context context){
        mContext = context;
        itemList = new ArrayList<>();
        dbHelper = new DbHelper(mContext, "HappyShoppingDatabase", null, 1);
        database = new MyDatabase(dbHelper);
        isCart = false;
    }

    public void setIsCart(boolean val){
        this.isCart = val;
    }

    @Override
    public int getCount() {
        if(itemList != null){
            return itemList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.card_item,viewGroup,false);
        final Item item = itemList.get(i);
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvDesc = (TextView) view.findViewById(R.id.tvDesc);
        TextView tvCost = (TextView) view.findViewById(R.id.tvCost);
        Button btnAddToCart = (Button) view.findViewById(R.id.btnAddToCart);


        byte[] encodeByte= Base64.decode(item.getImage(),Base64.DEFAULT);

        Bitmap imgbitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        ivImage.setImageBitmap(imgbitmap);
        tvTitle.setText(item.getName());
        tvDesc.setText(item.getDesc());
        tvCost.setText(item.getCost());

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, item.getName() + " Added to Cart", Toast.LENGTH_SHORT).show();
                database.addtocart(item.getName(),
                        item.getImage(),
                        item.getCost(),
                        item.getType(),
                        item.getDesc(),
                        UserActivity.Login_User_Name);
            }
        });

        if(isCart){
            btnAddToCart.setVisibility(View.GONE);
        }
        return view;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
