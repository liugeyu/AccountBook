package com.geyu.database.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.geyu.database.AccountBookDao;
import com.geyu.database.AccountDao;
import com.geyu.database.CategoryModelDao;
import com.geyu.database.DaoMaster;
import com.geyu.database.TestDao;
import com.geyu.database.ben.AccountBook;
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
        // 初始化一个账本
        initAccountBook(db);

        initAccount(db);

        // 初始化支出
        List<CategoryItem> expenseInitData = CategoryInitData.getExpenseInitData();
        for (CategoryItem expenseInitDatum : expenseInitData) {
            db.execSQL("INSERT INTO " + CategoryModelDao.TABLENAME + " (" +
                    CategoryModelDao.Properties.Type.columnName + ", " +
                    CategoryModelDao.Properties.Icon.columnName + ", " +
                    CategoryModelDao.Properties.UniqueName.columnName + ", " +
                    CategoryModelDao.Properties.Name.columnName +", " +
                    CategoryModelDao.Properties.Order.columnName +", "+
                    CategoryModelDao.Properties.AccountId.columnName +", "+
                    CategoryModelDao.Properties.AccountBookId.columnName +", "+
                    CategoryModelDao.Properties.SyncStatus.columnName +
                    ") VALUES("+expenseInitDatum.type+",'"+expenseInitDatum.icon+"','"+ expenseInitDatum.uniqueName+"','"+expenseInitDatum.name+"',0,0,0,0"+
                    ")");
        }


        // 初始化收入
        List<CategoryItem> incomeInitData = CategoryInitData.getIncomeInitData();
        for (CategoryItem incomeInitDatum : incomeInitData) {
            db.execSQL("INSERT INTO " + CategoryModelDao.TABLENAME + " (" +
                    CategoryModelDao.Properties.Type.columnName + ", " +
                    CategoryModelDao.Properties.Icon.columnName + ", " +
                    CategoryModelDao.Properties.UniqueName.columnName + ", " +
                    CategoryModelDao.Properties.Name.columnName +", " +
                    CategoryModelDao.Properties.Order.columnName +", "+
                    CategoryModelDao.Properties.AccountId.columnName +", "+
                    CategoryModelDao.Properties.AccountBookId.columnName +", "+
                    CategoryModelDao.Properties.SyncStatus.columnName +
                    ") VALUES("+incomeInitDatum.type+",'"+incomeInitDatum.icon+"','"+ incomeInitDatum.uniqueName+"','"+incomeInitDatum.name+"',0,0,0,0"+
                    ")");
        }

        db.setTransactionSuccessful();
        db.endTransaction();


    }

    private void initAccount(Database db) {
        db.execSQL("INSERT INTO " + AccountDao.TABLENAME + " (" +
                AccountDao.Properties.CreateTime.columnName + ", " +
                AccountDao.Properties.AccountName.columnName + ", " +
                AccountDao.Properties.AccountId.columnName + ", " +
                AccountDao.Properties.AccountBookId.columnName + ", " +
                AccountDao.Properties.SyncStatus.columnName +
                ") VALUES("+System.currentTimeMillis()+",'"+"其他"+"',"+ 0+",0,"+0+""+
                ")");
    }

    private void initAccountBook(Database db) {
        db.execSQL("INSERT INTO " + AccountBookDao.TABLENAME + " (" +
                AccountBookDao.Properties.CreateTime.columnName + ", " +
                AccountBookDao.Properties.AccountBookName.columnName + ", " +
                AccountBookDao.Properties.AccountBookId.columnName + ", " +
                AccountBookDao.Properties.SyncStatus.columnName +
                ") VALUES("+System.currentTimeMillis()+",'"+"账本"+"',"+ 0+","+0+""+
                ")");
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
