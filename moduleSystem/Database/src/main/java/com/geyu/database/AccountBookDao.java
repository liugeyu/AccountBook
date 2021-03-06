package com.geyu.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.geyu.database.ben.AccountBook;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACCOUNT_BOOK".
*/
public class AccountBookDao extends AbstractDao<AccountBook, Long> {

    public static final String TABLENAME = "ACCOUNT_BOOK";

    /**
     * Properties of entity AccountBook.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CreateTime = new Property(1, long.class, "createTime", false, "CREATE_TIME");
        public final static Property AccountBookName = new Property(2, String.class, "accountBookName", false, "ACCOUNT_BOOK_NAME");
        public final static Property AccountBookId = new Property(3, long.class, "accountBookId", false, "ACCOUNT_BOOK_ID");
        public final static Property SyncStatus = new Property(4, int.class, "syncStatus", false, "SYNC_STATUS");
    }


    public AccountBookDao(DaoConfig config) {
        super(config);
    }
    
    public AccountBookDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACCOUNT_BOOK\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 1: createTime
                "\"ACCOUNT_BOOK_NAME\" TEXT," + // 2: accountBookName
                "\"ACCOUNT_BOOK_ID\" INTEGER NOT NULL UNIQUE ," + // 3: accountBookId
                "\"SYNC_STATUS\" INTEGER NOT NULL );"); // 4: syncStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACCOUNT_BOOK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AccountBook entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCreateTime());
 
        String accountBookName = entity.getAccountBookName();
        if (accountBookName != null) {
            stmt.bindString(3, accountBookName);
        }
        stmt.bindLong(4, entity.getAccountBookId());
        stmt.bindLong(5, entity.getSyncStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AccountBook entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCreateTime());
 
        String accountBookName = entity.getAccountBookName();
        if (accountBookName != null) {
            stmt.bindString(3, accountBookName);
        }
        stmt.bindLong(4, entity.getAccountBookId());
        stmt.bindLong(5, entity.getSyncStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AccountBook readEntity(Cursor cursor, int offset) {
        AccountBook entity = new AccountBook( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // createTime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // accountBookName
            cursor.getLong(offset + 3), // accountBookId
            cursor.getInt(offset + 4) // syncStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AccountBook entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCreateTime(cursor.getLong(offset + 1));
        entity.setAccountBookName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAccountBookId(cursor.getLong(offset + 3));
        entity.setSyncStatus(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AccountBook entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AccountBook entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AccountBook entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
