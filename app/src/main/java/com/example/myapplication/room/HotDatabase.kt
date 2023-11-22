import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.room.Hot
import com.example.myapplication.room.HotDao
import com.example.myapplication.room.User


@Database(version = 1, entities = [Hot::class], exportSchema = false)
abstract class HotDatabase :RoomDatabase(){
    abstract fun hotDao(): HotDao

    companion object{
        private var instance: HotDatabase?= null
        fun getDatabase(context: Context): HotDatabase{
            //在非空的情况下执行一段代码块
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                HotDatabase::class.java, "hot_database")
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }
    }
}

