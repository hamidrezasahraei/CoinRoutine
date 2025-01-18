package dev.coinroutine.app.core.database.portfolio

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.coinroutine.app.portfolio.data.local.PortfolioCoinEntity
import dev.coinroutine.app.portfolio.data.local.PortfolioDao

@Database(entities = [PortfolioCoinEntity::class], version = 1)
abstract class PortfolioDatabase: RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}