package com.geyu.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.geyu.database.ben.Account;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACCOUNT".
*/
public class AccountDao extends AbstractDao<Account, Long> {

    public static final String TABLENAME = "ACCOUNT";

    /**
     * Properties of entity Account.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AccountName = new Property(1, String.class, "accountName", false, "ACCOUNT_NAME");
        public final static Property CreateTime = new Property(2, long.class, "createTime", false, "CREATE_TIME");
        public final static Property AccountId = new Property(3, long.class, "accountId", false, "ACCOUNT_ID");
        public final static Property AccountBookId = new Property(4, long.class, "accountBookId", false, "ACCOUNT_BOOK_ID");
        public final static Property SyncStatus = new Property(5, int.class, "syncStatus", false, "SYNC_STATUS");
    }


    public AccountDao(DaoConfig config) {
        super(config);
    }
    
    public AccountDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACCOUNT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ACCOUNT_NAME\" TEXT," + // 1: accountName
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 2: createTime
                "\"ACCOUNT_ID\" INTEGER NOT NULL UNIQUE ," + // 3: accountId
                "\"ACCOUNT_BOOK_ID\" INTEGER NOT NULL ," + // 4: accountBookId
                "\"SYNC_STATUS\" INTEGER NOT NULL );"); // 5: syncStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACCOUNT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Account entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String accountName = entity.getAccountName();
        if (accountName != null) {
            stmt.bindString(2, accountName);
        }
        stmt.bindLong(3, entity.getCreateTime());
        stmt.bindLong(4, entity.getAccountId());
        stmt.bindLong(5, entity.getAccountBookId());
        stmt.bindLong(6, entity.getSyncStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Account entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String accountName = entity.getAccountName();
        if (accountName != null) {
            stmt.bindString(2, accountName);
        }
        stmt.bindLong(3, entity.getCreateTime());
        stmt.bindLong(4, entity.getAccountId());
        stmt.bindLong(5, entity.getAccountBookId());
        stmt.bindLong(6, entity.getSyncStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Account readEntity(Cursor cursor, int offset) {
        Account entity = new Account( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // accountName
            cursor.getLong(offset + 2), // createTime
            cursor.getLong(offset + 3), // accountId
            cursor.getLong(offset + 4), // accountBookId
            cursor.getInt(offset + 5) // syncStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Account entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAccountName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.getLong(offset + 2));
        entity.setAccountId(cursor.getLong(offset + 3));
        entity.setAccountBookId(cursor.getLong(offset + 4));
        entity.setSyncStatus(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Account entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Account entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Account entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
