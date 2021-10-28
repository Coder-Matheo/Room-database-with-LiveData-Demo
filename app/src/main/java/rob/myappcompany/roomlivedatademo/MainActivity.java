package rob.myappcompany.roomlivedatademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertSingelUser(View view){
        User user = new User("Matheo", "Dinarvand");
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        insertAsyncTask.execute(user);
    }

    public void getAllUser(View view){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> userList = MyRoomDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getAllUsers();
                for (int i = 0; i < userList.size(); i++){
                    Log.i("Main", userList.get(i).getFirstName());
                }

            }
        });
        thread.start();
    }
    public void deleteUser(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = MyRoomDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .findById(1);

                if (user != null){
                    Log.d("Main", user.getFirstName());

                    MyRoomDatabase.getInstance(getApplicationContext())
                            .userDao()
                            .delete(user);

                    Log.d("Main", "Has been deleted");
                }

            }
        }).start();

    }

    public void updateUser(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = MyRoomDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .findById(6);

                if (user != null){
                    Log.d("Main", user.getFirstName());
                    user.setFirstName("Mattt");

                    MyRoomDatabase.getInstance(getApplicationContext())
                            .userDao()
                            .updateUser(user);

                    Log.d("Main", "Has been deleted");
                }

            }
        }).start();

    }

    public void insertMultipleUser(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> userList = new ArrayList<>();
                userList.add(new User("Shahram", "Khodadadi"));
                userList.add(new User("Amir", "Saberi"));
                userList.add(new User("Ali", "Dinarvand"));

                MyRoomDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .insertMultipleUsers(userList);
                Log.d("Main", "User multiple Inserted");
            }
        }).start();

    }
    public void findComplatedUser(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> userList = MyRoomDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getAllFirstNames();

                for (int i = 0; i < userList.size(); i++){
                    Log.d("Main", userList.get(i).getFirstName().toString());
                }

            }
        }).start();

    }

    public void getAllUsingLiveDataOnly(View view){
        LiveData<List<User>> userList = MyRoomDatabase.getInstance(getApplicationContext())
                .userDao()
                .findUserUsingLiveDataOnly();

        userList.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.d(TAG, "onChanged: "+users.toString());
                Log.d(TAG, "onChanged: "+users.size());
                // userList.removeObserver(this);
            }
        });


    }

    public void getAllUsingViewModelAndLiveData(View view){

    }
    class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            MyRoomDatabase.getInstance(getApplicationContext())
                    .userDao()
                    .insert(users[0]);
            Log.i("Main", "Inserted");

            return null;
        }
    }
}