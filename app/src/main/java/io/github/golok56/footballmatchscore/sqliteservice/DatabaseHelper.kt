package io.github.golok56.footballmatchscore.sqliteservice

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.model.Team
import org.jetbrains.anko.db.*

class DatabaseHelper private constructor(context: Context)
    : ManagedSQLiteOpenHelper(context, "football_schedule.sqlite") {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.apply {
            createTable(
                League.TABLE_LEAGUE, true,
                League.COLUMN_ID to TEXT + PRIMARY_KEY,
                League.COLUMN_NAME to TEXT,
                League.COLUMN_SPORT to TEXT,
                League.COLUMN_ALTERNATE_NAME to TEXT,
                League.COLUMN_LOGO to TEXT,
                League.COLUMN_UPDATED_ON to INTEGER
            )

            createTable(
                Team.TABLE_TEAM, true,
                Team.COLUMN_ID to TEXT + PRIMARY_KEY,
                Team.COLUMN_NAME to TEXT,
                Team.COLUMN_LOGO to TEXT,
                Team.COLUMN_UPDATED_ON to INTEGER
            )

            createTable(
                Schedule.TABLE_FAVORITE, true,
                Schedule.COLUMN_ID to TEXT + PRIMARY_KEY,
                Schedule.COLUMN_HOME_ID to TEXT,
                Schedule.COLUMN_HOME_TEAM to TEXT,
                Schedule.COLUMN_HOME_SCORE to TEXT,
                Schedule.COLUMN_HOME_GK to TEXT,
                Schedule.COLUMN_HOME_DF to TEXT,
                Schedule.COLUMN_HOME_MF to TEXT,
                Schedule.COLUMN_HOME_FW to TEXT,
                Schedule.COLUMN_HOME_SUB to TEXT,
                Schedule.COLUMN_AWAY_ID to TEXT,
                Schedule.COLUMN_AWAY_TEAM to TEXT,
                Schedule.COLUMN_AWAY_SCORE to TEXT,
                Schedule.COLUMN_AWAY_GK to TEXT,
                Schedule.COLUMN_AWAY_DF to TEXT,
                Schedule.COLUMN_AWAY_MF to TEXT,
                Schedule.COLUMN_AWAY_FW to TEXT,
                Schedule.COLUMN_AWAY_SUB to TEXT,
                Schedule.COLUMN_DATE to TEXT,
                Schedule.COLUMN_LEAGUE_ID to TEXT
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context)
            }

            return instance!!
        }
    }
}

fun Context.getDatabase() = DatabaseHelper.getInstance(this)