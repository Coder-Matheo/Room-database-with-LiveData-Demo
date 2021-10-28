package rob.myappcompany.roomlivedatademo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    private static volatile MyRoomDatabase INSTANCE;

    //Singleton Pattern
    static MyRoomDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MyRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "DB_NAME_LIVEDATA").build();
                }
            }
        }
        return INSTANCE;
    }



}
