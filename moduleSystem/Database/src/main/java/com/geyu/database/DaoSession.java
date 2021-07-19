package com.geyu.database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.geyu.database.ben.Record;
import com.geyu.database.ben.Test;

import com.geyu.database.RecordDao;
import com.geyu.database.TestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig recordDaoConfig;
    private final DaoConfig testDaoConfig;

    private final RecordDao recordDao;
    private final TestDao testDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        recordDaoConfig = daoConfigMap.get(RecordDao.class).clone();
        recordDaoConfig.initIdentityScope(type);

        testDaoConfig = daoConfigMap.get(TestDao.class).clone();
        testDaoConfig.initIdentityScope(type);

        recordDao = new RecordDao(recordDaoConfig, this);
        testDao = new TestDao(testDaoConfig, this);

        registerDao(Record.class, recordDao);
        registerDao(Test.class, testDao);
    }
    
    public void clear() {
        recordDaoConfig.clearIdentityScope();
        testDaoConfig.clearIdentityScope();
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public TestDao getTestDao() {
        return testDao;
    }

}
