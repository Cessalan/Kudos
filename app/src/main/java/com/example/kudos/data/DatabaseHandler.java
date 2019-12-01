package com.example.kudos.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kudos.R;
import com.example.kudos.model.Account;
import com.example.kudos.model.UserPost;
import com.example.kudos.util.Util_Account;
import com.example.kudos.util.Util_Post;

import java.util.ArrayList;
import java.util.List;

import static com.example.kudos.util.Util_Account.KEY_PWD;
import static com.example.kudos.util.Util_Account.KEY_USERNAME;
import static com.example.kudos.util.Util_Post.KEY_ID;
import static com.example.kudos.util.Util_Post.TABLE_NAME;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context){
        super(context, Util_Post.DATABASE_NAME, null, Util_Post.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE "+ TABLE_NAME +
                "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + Util_Post.KEY_POST +")";
        db.execSQL(CREATE_POST_TABLE);

        String CREATE_ACCOUNT_TABLE = "CREATE TABLE "+ Util_Account.TABLE_NAME +
                "(" + Util_Account.KEY_ID + " INTEGER PRIMARY KEY, "
                     + Util_Account.KEY_USERNAME + " TEXT, "+
                       Util_Account.KEY_PWD+" TEXT)";
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_POST = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE_POST, new String[]{Util_Post.DATABASE_NAME});
        onCreate(db);

        String DROP_TABLE_ACCOUNT = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE_ACCOUNT, new String[]{Util_Account.DATABASE_NAME});
        onCreate(db);
    }

    public void addPost(UserPost post){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Util.KEY_ID, contact.getId());
        values.put(Util_Post.KEY_POST, post.getPost());

        db.insert(TABLE_NAME, null, values);
        Log.d("test", "addPost: "+"item added");
        db.close();

    }

    public void deletePost(UserPost post)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(post.getId())});
        db.close();
    }

    public boolean updatePost(UserPost post){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util_Post.KEY_POST, post.getPost());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(post.getId())});

        return true;
    }
    //get all contacts
    public List<UserPost> feed = new ArrayList<>();

    public List<UserPost> getAllPosts(){
        List<UserPost> postList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {

            do{
                UserPost post = new UserPost();

                post.setId(Integer.parseInt(cursor.getString(0)));
                post.setPost(cursor.getString(1));


                postList.add(post);
            }while(cursor.moveToNext());
        }
        db.close();
        return postList;
    }

    public void addAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Util_Account.KEY_USERNAME, account.getUsername());

        values.put(Util_Account.KEY_PWD, account.getPassword());


            db.insert(Util_Account.TABLE_NAME, null, values);

        Log.d("test", "addAccount: "+"item added");
        db.close();
    }

    public Account getAccount(String username){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util_Account.TABLE_NAME, new String[]{Util_Account.KEY_ID, Util_Account.KEY_USERNAME, KEY_PWD},

                KEY_USERNAME+ "=?", new String[]{String.valueOf(username)}, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
            Account account = new Account();
            try{

                account.setId(Integer.parseInt(cursor.getString(0)));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));

            }catch(Exception e){

                return null;
            }




            return account;
        }else{
            return null;
        }
    }




}
