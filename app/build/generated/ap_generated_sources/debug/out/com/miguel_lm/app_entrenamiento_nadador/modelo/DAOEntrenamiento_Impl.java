package com.miguel_lm.app_entrenamiento_nadador.modelo;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DAOEntrenamiento_Impl implements DAOEntrenamiento {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Entrenamiento> __insertionAdapterOfEntrenamiento;

  private final EntityDeletionOrUpdateAdapter<Entrenamiento> __deletionAdapterOfEntrenamiento;

  private final EntityDeletionOrUpdateAdapter<Entrenamiento> __updateAdapterOfEntrenamiento;

  public DAOEntrenamiento_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntrenamiento = new EntityInsertionAdapter<Entrenamiento>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Entrenamientos` (`key`,`fecha`,`horas`,`minutos`,`segundos`,`distanciaMts`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Entrenamiento value) {
        stmt.bindLong(1, value.getKey());
        final Long _tmp;
        _tmp = DateConverter.toTimestamp(value.getFecha());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getHoras());
        stmt.bindLong(4, value.getMinutos());
        stmt.bindLong(5, value.getSegundos());
        stmt.bindLong(6, value.getDistanciaMts());
      }
    };
    this.__deletionAdapterOfEntrenamiento = new EntityDeletionOrUpdateAdapter<Entrenamiento>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Entrenamientos` WHERE `key` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Entrenamiento value) {
        stmt.bindLong(1, value.getKey());
      }
    };
    this.__updateAdapterOfEntrenamiento = new EntityDeletionOrUpdateAdapter<Entrenamiento>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Entrenamientos` SET `key` = ?,`fecha` = ?,`horas` = ?,`minutos` = ?,`segundos` = ?,`distanciaMts` = ? WHERE `key` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Entrenamiento value) {
        stmt.bindLong(1, value.getKey());
        final Long _tmp;
        _tmp = DateConverter.toTimestamp(value.getFecha());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getHoras());
        stmt.bindLong(4, value.getMinutos());
        stmt.bindLong(5, value.getSegundos());
        stmt.bindLong(6, value.getDistanciaMts());
        stmt.bindLong(7, value.getKey());
      }
    };
  }

  @Override
  public void insertar(final Entrenamiento nuevoEntrenamiento) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEntrenamiento.insert(nuevoEntrenamiento);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void eliminarEntrenamiento(final Entrenamiento entrenamiento) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfEntrenamiento.handle(entrenamiento);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void actualizarEntrenamiento(final Entrenamiento entrenamiento) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfEntrenamiento.handle(entrenamiento);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Entrenamiento> obtenerEntrenamientos() {
    final String _sql = "SELECT `Entrenamientos`.`key` AS `key`, `Entrenamientos`.`fecha` AS `fecha`, `Entrenamientos`.`horas` AS `horas`, `Entrenamientos`.`minutos` AS `minutos`, `Entrenamientos`.`segundos` AS `segundos`, `Entrenamientos`.`distanciaMts` AS `distanciaMts` FROM Entrenamientos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
      final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
      final int _cursorIndexOfHoras = CursorUtil.getColumnIndexOrThrow(_cursor, "horas");
      final int _cursorIndexOfMinutos = CursorUtil.getColumnIndexOrThrow(_cursor, "minutos");
      final int _cursorIndexOfSegundos = CursorUtil.getColumnIndexOrThrow(_cursor, "segundos");
      final int _cursorIndexOfDistanciaMts = CursorUtil.getColumnIndexOrThrow(_cursor, "distanciaMts");
      final List<Entrenamiento> _result = new ArrayList<Entrenamiento>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Entrenamiento _item;
        final Date _tmpFecha;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfFecha)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfFecha);
        }
        _tmpFecha = DateConverter.toDate(_tmp);
        final int _tmpHoras;
        _tmpHoras = _cursor.getInt(_cursorIndexOfHoras);
        final int _tmpMinutos;
        _tmpMinutos = _cursor.getInt(_cursorIndexOfMinutos);
        final int _tmpSegundos;
        _tmpSegundos = _cursor.getInt(_cursorIndexOfSegundos);
        final int _tmpDistanciaMts;
        _tmpDistanciaMts = _cursor.getInt(_cursorIndexOfDistanciaMts);
        _item = new Entrenamiento(_tmpFecha,_tmpHoras,_tmpMinutos,_tmpSegundos,_tmpDistanciaMts);
        final int _tmpKey;
        _tmpKey = _cursor.getInt(_cursorIndexOfKey);
        _item.setKey(_tmpKey);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
