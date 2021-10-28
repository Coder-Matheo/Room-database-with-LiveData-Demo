package rob.myappcompany.roomlivedatademo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE uid LIKE :uid LIMIT 1")
    User findById(int uid);

    @Delete
    void delete(User user);

    @Update
    void updateUser(User user);

    @Insert
    void insertMultipleUsers(List<User> userList);

    @Query("SELECT * FROM user WHERE first_name LIKE 1")
    List<User> getAllFirstNames();

    @Query("SELECT * FROM user")
    LiveData<List<User>> findUserUsingLiveDataOnly();


    @Insert
    void insertAll(User... users);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);


    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int [] userIds);
}
