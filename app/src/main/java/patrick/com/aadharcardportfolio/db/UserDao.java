package patrick.com.aadharcardportfolio.db;

/**
 * Created by rana on 6/2/18.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface UserDao {

    @Query("select * from User order by createDate desc")
    List<User> loadAllUsers();

    @Query("SELECT * FROM user where name LIKE  :Name AND createDate LIKE :time")
    User findByName(String Name, String time);

    @Insert(onConflict = IGNORE)
    void insertUser(User... user);

    @Delete
    void deleteUser(User user);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceUsers(User... users);

    @Delete
    void deleteUsers(User user1, User user2);

    @Update
    void updateUser(User user);

}