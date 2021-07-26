package com.geyu.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.geyu.database.ben.Record;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RECORD".
*/
public class RecordDao extends AbstractDao<Record, Long> {

    public static final String TABLENAME = "RECORD";

    /**
     * Properties of entity Record.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AccountId = new Property(1, long.class, "accountId", false, "ACCOUNT_ID");
        public final static Property AccountBookId = new Property(2, long.class, "accountBookId", false, "ACCOUNT_BOOK_ID");
        public final static Property Time = new Property(3, long.class, "time", false, "TIME");
        public final static Property CategoryUniqueName = new Property(4, String.class, "categoryUniqueName", false, "CATEGORY_UNIQUE_NAME");
        public final static Property CategoryName = new Property(5, String.class, "categoryName", false, "CATEGORY_NAME");
        public final static Property CategoryIcon = new Property(6, String.class, "categoryIcon", false, "CATEGORY_ICON");
        public final static Property Amount = new Property(7, long.class, "amount", false, "AMOUNT");
        public final static Property Desc = new Property(8, String.class, "desc", false, "DESC");
        public final static Property SyncId = new Property(9, String.class, "syncId", false, "SYNC_ID");
        public final static Property SyncStatus = new Property(10, int.class, "syncStatus", false, "SYNC_STATUS");
        public final static Property Type = new Property(11, int.class, "type", false, "TYPE");
    }


    public RecordDao(DaoConfig config) {
        super(config);
    }
    
    public RecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ACCOUNT_ID\" INTEGER NOT NULL ," + // 1: accountId
                "\"ACCOUNT_BOOK_ID\" INTEGER NOT NULL ," + // 2: accountBookId
                "\"TIME\" INTEGER NOT NULL ," + // 3: time
                "\"CATEGORY_UNIQUE_NAME\" TEXT," + // 4: categoryUniqueName
                "\"CATEGORY_NAME\" TEXT," + // 5: categoryName
                "\"CATEGORY_ICON\" TEXT," + // 6: categoryIcon
                "\"AMOUNT\" INTEGER NOT NULL ," + // 7: amount
                "\"DESC\" TEXT," + // 8: desc
                "\"SYNC_ID\" TEXT," + // 9: syncId
                "\"SYNC_STATUS\" INTEGER NOT NULL ," + // 10: syncStatus
                "\"TYPE\" INTEGER NOT NULL );"); // 11: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Record entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAccountId());
        stmt.bindLong(3, entity.getAccountBookId());
        stmt.bindLong(4, entity.getTime());
 
        String categoryUniqueName = entity.getCategoryUniqueName();
        if (categoryUniqueName != null) {
            stmt.bindString(5, categoryUniqueName);
        }
 
        String categoryName = entity.getCategoryName();
        if (categoryName != null) {
            stmt.bindString(6, categoryName);
        }
 
        String categoryIcon = entity.getCategoryIcon();
        if (categoryIcon != null) {
            stmt.bindString(7, categoryIcon);
        }
        stmt.bindLong(8, entity.getAmount());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(9, desc);
        }
 
        String syncId = entity.getSyncId();
        if (syncId != null) {
            stmt.bindString(10, syncId);
        }
        stmt.bindLong(11, entity.getSyncStatus());
        stmt.bindLong(12, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Record entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAccountId());
        stmt.bindLong(3, entity.getAccountBookId());
        stmt.bindLong(4, entity.getTime());
 
        String categoryUniqueName = entity.getCategoryUniqueName();
        if (categoryUniqueName != null) {
            stmt.bindString(5, categoryUniqueName);
        }
 
        String categoryName = entity.getCategoryName();
        if (categoryName != null) {
            stmt.bindString(6, categoryName);
        }
 
        String categoryIcon = entity.getCategoryIcon();
        if (categoryIcon != null) {
            stmt.bindString(7, categoryIcon);
        }
        stmt.bindLong(8, entity.getAmount());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(9, desc);
        }
 
        String syncId = entity.getSyncId();
        if (syncId != null) {
            stmt.bindString(10, syncId);
        }
        stmt.bindLong(11, entity.getSyncStatus());
        stmt.bindLong(12, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Record readEntity(Cursor cursor, int offset) {
        Record entity = new Record( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // accountId
            cursor.getLong(offset + 2), // accountBookId
            cursor.getLong(offset + 3), // time
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // categoryUniqueName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // categoryName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // categoryIcon
            cursor.getLong(offset + 7), // amount
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // desc
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // syncId
            cursor.getInt(offset + 10), // syncStatus
            cursor.getInt(offset + 11) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Record entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAccountId(cursor.getLong(offset + 1));
        entity.setAccountBookId(cursor.getLong(offset + 2));
        entity.setTime(cursor.getLong(offset + 3));
        entity.setCategoryUniqueName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCategoryName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCategoryIcon(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAmount(cursor.getLong(offset + 7));
        entity.setDesc(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSyncId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSyncStatus(cursor.getInt(offset + 10));
        entity.setType(cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Record entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Record entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Record entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
