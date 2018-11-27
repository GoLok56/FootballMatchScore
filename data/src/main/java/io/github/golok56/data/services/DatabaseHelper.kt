package io.github.golok56.data.services

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import io.github.golok56.data.entities.LeagueData
import io.github.golok56.data.entities.PlayerData
import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.data.entities.TeamData
import org.jetbrains.anko.db.*


class DatabaseHelper private constructor(
    context: Context
) : ManagedSQLiteOpenHelper(context, "football_schedule.sqlite", null, 5) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.apply {
            createTable(
                LeagueData.TABLE_LEAGUE, true,
                LeagueData.COLUMN_ID to TEXT + PRIMARY_KEY,
                LeagueData.COLUMN_NAME to TEXT,
                LeagueData.COLUMN_SPORT to TEXT,
                LeagueData.COLUMN_ALTERNATE_NAME to TEXT,
                LeagueData.COLUMN_LOGO to TEXT,
                LeagueData.COLUMN_UPDATED_ON to INTEGER
            )

            createTable(
                TeamData.TABLE_TEAM, true,
                TeamData.COLUMN_ID to TEXT + PRIMARY_KEY,
                TeamData.COLUMN_NAME to TEXT,
                TeamData.COLUMN_LOGO to TEXT,
                TeamData.COLUMN_YEAR to TEXT,
                TeamData.COLUMN_STADIUM to TEXT,
                TeamData.COLUMN_DESCRIPTION to TEXT,
                TeamData.COLUMN_LEAGUE to TEXT,
                TeamData.COLUMN_UPDATED_ON to INTEGER
            )

            createTable(
                TeamData.TABLE_TEAM_FAVORITE, true,
                TeamData.COLUMN_ID to TEXT + PRIMARY_KEY,
                TeamData.COLUMN_NAME to TEXT,
                TeamData.COLUMN_LOGO to TEXT,
                TeamData.COLUMN_YEAR to TEXT,
                TeamData.COLUMN_STADIUM to TEXT,
                TeamData.COLUMN_DESCRIPTION to TEXT,
                TeamData.COLUMN_LEAGUE to TEXT,
                TeamData.COLUMN_UPDATED_ON to INTEGER
            )

            createTable(
                ScheduleData.TABLE_FAVORITE, true,
                ScheduleData.COLUMN_ID to TEXT + PRIMARY_KEY,
                ScheduleData.COLUMN_HOME_ID to TEXT,
                ScheduleData.COLUMN_HOME_TEAM to TEXT,
                ScheduleData.COLUMN_HOME_SCORE to TEXT,
                ScheduleData.COLUMN_HOME_GK to TEXT,
                ScheduleData.COLUMN_HOME_DF to TEXT,
                ScheduleData.COLUMN_HOME_MF to TEXT,
                ScheduleData.COLUMN_HOME_FW to TEXT,
                ScheduleData.COLUMN_HOME_SUB to TEXT,
                ScheduleData.COLUMN_AWAY_ID to TEXT,
                ScheduleData.COLUMN_AWAY_TEAM to TEXT,
                ScheduleData.COLUMN_AWAY_SCORE to TEXT,
                ScheduleData.COLUMN_AWAY_GK to TEXT,
                ScheduleData.COLUMN_AWAY_DF to TEXT,
                ScheduleData.COLUMN_AWAY_MF to TEXT,
                ScheduleData.COLUMN_AWAY_FW to TEXT,
                ScheduleData.COLUMN_AWAY_SUB to TEXT,
                ScheduleData.COLUMN_DATE to TEXT,
                ScheduleData.COLUMN_LEAGUE_ID to TEXT
            )

            createTable(
                PlayerData.TABLE_PLAYER, true,
                PlayerData.COLUMN_ID to TEXT + PRIMARY_KEY,
                PlayerData.COLUMN_TEAM_ID to TEXT,
                PlayerData.COLUMN_NAME to TEXT,
                PlayerData.COLUMN_BIRTH to TEXT,
                PlayerData.COLUMN_BIRTH_LOCATION to TEXT,
                PlayerData.COLUMN_DESCRIPTION to TEXT,
                PlayerData.COLUMN_POSITION to TEXT,
                PlayerData.COLUMN_HEIGHT to TEXT,
                PlayerData.COLUMN_WEIGHT to TEXT,
                PlayerData.COLUMN_THUMB to TEXT,
                PlayerData.COLUMN_CUTOUT to TEXT
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1) upgradeV1(db)
        if (oldVersion <= 2) db?.delete(TeamData.TABLE_TEAM) // Make sure team is not cached
        if (oldVersion <= 3) upgradeV3(db)
        if (oldVersion <= 4) upgradeV4(db)
    }

    private fun upgradeV1(db: SQLiteDatabase?) {
        db?.execSQL("ALTER TABLE ${TeamData.TABLE_TEAM} ADD ${TeamData.COLUMN_YEAR} TEXT;")
        db?.execSQL("ALTER TABLE ${TeamData.TABLE_TEAM} ADD ${TeamData.COLUMN_STADIUM} TEXT;")
        db?.execSQL("ALTER TABLE ${TeamData.TABLE_TEAM} ADD ${TeamData.COLUMN_DESCRIPTION} TEXT;")
        db?.execSQL("ALTER TABLE ${TeamData.TABLE_TEAM} ADD ${TeamData.COLUMN_LEAGUE} TEXT;")

        db?.createTable(
            TeamData.TABLE_TEAM_FAVORITE, true,
            TeamData.COLUMN_ID to TEXT + PRIMARY_KEY,
            TeamData.COLUMN_NAME to TEXT,
            TeamData.COLUMN_LOGO to TEXT,
            TeamData.COLUMN_YEAR to TEXT,
            TeamData.COLUMN_STADIUM to TEXT,
            TeamData.COLUMN_DESCRIPTION to TEXT,
            TeamData.COLUMN_LEAGUE to TEXT,
            TeamData.COLUMN_UPDATED_ON to INTEGER
        )
    }

    private fun upgradeV3(db: SQLiteDatabase?) {
        db?.createTable(
            PlayerData.TABLE_PLAYER, true,
            PlayerData.COLUMN_ID to TEXT + PRIMARY_KEY,
            PlayerData.COLUMN_TEAM_ID to TEXT,
            PlayerData.COLUMN_NAME to TEXT,
            PlayerData.COLUMN_BIRTH to TEXT,
            PlayerData.COLUMN_BIRTH_LOCATION to TEXT,
            PlayerData.COLUMN_DESCRIPTION to TEXT,
            PlayerData.COLUMN_POSITION to TEXT,
            PlayerData.COLUMN_HEIGHT to TEXT,
            PlayerData.COLUMN_WEIGHT to TEXT,
            PlayerData.COLUMN_THUMB to TEXT,
            PlayerData.COLUMN_CUTOUT to TEXT
        )
    }

    private fun upgradeV4(db: SQLiteDatabase?) {
        db?.execSQL("ALTER TABLE ${ScheduleData.TABLE_FAVORITE} ADD ${ScheduleData.COLUMN_LEAGUE_ID} TEXT;")
    }

    companion object {
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context)
            }

            return instance!!
        }
    }
}

fun Context.getDatabase() = DatabaseHelper.getInstance(this)