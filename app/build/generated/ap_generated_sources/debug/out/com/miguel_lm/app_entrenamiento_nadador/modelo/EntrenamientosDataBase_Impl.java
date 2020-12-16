package com.miguel_lm.app_entrenamiento_nadador.modelo;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EntrenamientosDataBase_Impl extends EntrenamientosDataBase {
  private volatile DAOEntrenamiento _dAOEntrenamiento;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Entrenamientos` (`key` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fecha` INTEGER NOT NULL, `horas` INTEGER NOT NULL, `minutos` INTEGER NOT NULL, `segundos` INTEGER NOT NULL, `distanciaMts` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '58ba6c14fd3cafc63e629cff912f69f2')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Entrenamientos`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsEntrenamientos = new HashMap<String, TableInfo.Column>(6);
        _columnsEntrenamientos.put("key", new TableInfo.Column("key", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntrenamientos.put("fecha", new TableInfo.Column("fecha", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntrenamientos.put("horas", new TableInfo.Column("horas", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntrenamientos.put("minutos", new TableInfo.Column("minutos", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntrenamientos.put("segundos", new TableInfo.Column("segundos", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEntrenamientos.put("distanciaMts", new TableInfo.Column("distanciaMts", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEntrenamientos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEntrenamientos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEntrenamientos = new TableInfo("Entrenamientos", _columnsEntrenamientos, _foreignKeysEntrenamientos, _indicesEntrenamientos);
        final TableInfo _existingEntrenamientos = TableInfo.read(_db, "Entrenamientos");
        if (! _infoEntrenamientos.equals(_existingEntrenamientos)) {
          return new RoomOpenHelper.ValidationResult(false, "Entrenamientos(com.miguel_lm.app_entrenamiento_nadador.modelo.Entrenamiento).\n"
                  + " Expected:\n" + _infoEntrenamientos + "\n"
                  + " Found:\n" + _existingEntrenamientos);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "58ba6c14fd3cafc63e629cff912f69f2", "759e58d98dbcee69e9e6981cd49a6141");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Entrenamientos");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Entrenamientos`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DAOEntrenamiento getDAOEntrenamiento() {
    if (_dAOEntrenamiento != null) {
      return _dAOEntrenamiento;
    } else {
      synchronized(this) {
        if(_dAOEntrenamiento == null) {
          _dAOEntrenamiento = new DAOEntrenamiento_Impl(this);
        }
        return _dAOEntrenamiento;
      }
    }
  }
}
