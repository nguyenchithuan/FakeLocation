package edu.wkd.fakelocation.data.database_local.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.wkd.fakelocation.models.obj.User;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("DELETE FROM user")
    void deleteAllUser();
}
