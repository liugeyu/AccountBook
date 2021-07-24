package com.geyu.database.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.geyu.database.CategoryModelDao;
import com.geyu.database.DaoMaster;
import com.geyu.database.ben.CategoryItem;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.data.CategoryConstant;
import com.geyu.database.data.CategoryIconHelper;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class SQLiteOpenHelper extends DaoMaster.OpenHelper{
    public SQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
        db.beginTransaction();
        // 初始化支出
        List<CategoryItem> expenseInitData = CategoryInitData.getExpenseInitData();
        CategoryItem expenseInitDatum = expenseInitData.get(0);
        // INSERT INTO CATEGORY_MODEL (_id, UNIQUE_NAME) VALUES(1, 'Example Note')
//        db.execSQL("INSERT INTO " + CategoryModelDao.TABLENAME + " (" +
//                CategoryModelDao.Properties.Type.columnName + ", " +
//                CategoryModelDao.Properties.Icon.columnName + ", " +
//                CategoryModelDao.Properties.UniqueName.columnName + ", " +
//                CategoryModelDao.Properties.Name.columnName +", " +
//                CategoryModelDao.Properties.Order.columnName +", "+
//                CategoryModelDao.Properties.AccountId.columnName +", "+
//                CategoryModelDao.Properties.SyncStatus.columnName +
//                ") VALUES("+expenseInitDatum.type+",'"+expenseInitDatum.icon+"','"+ expenseInitDatum.uniqueName+"','"+expenseInitDatum.name+"',0,0,0"+
//                ")");


        db.execSQL("INSERT INTO " + CategoryModelDao.TABLENAME + " (" +
                CategoryModelDao.Properties.Id.columnName + ", " +
                CategoryModelDao.Properties.UniqueName.columnName + ", " +
                CategoryModelDao.Properties.Name.columnName + ", " +
                CategoryModelDao.Properties.Icon.columnName + ", " +
                CategoryModelDao.Properties.Order.columnName + ", " +
                CategoryModelDao.Properties.Type.columnName + ", " +
                CategoryModelDao.Properties.AccountId.columnName + ", " +
                CategoryModelDao.Properties.SyncStatus.columnName +
                ") VALUES(1, '0', 'Example Note','2','3',0,0,0)");
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }
            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        });

    }

}
