/*
 * Copyright 2015 Department of Computer Science and Engineering, University of Moratuwa.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import androidx.test.core.app.ApplicationProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.PersistentExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest {
    private static ExpenseManager expenseManager;

    @BeforeClass
    public static void testAddAccount(){
        Context context = ApplicationProvider.getApplicationContext();
        expenseManager = new PersistentExpenseManager(context);
        expenseManager.addAccount("200E","BOC","Theshan", 50000);
    }

    @Test
    public void checkAddedAccount(){
//        try {
//            assertTrue(expenseManager.getAccountsDAO().getAccount("200E").getAccountNo().equals("200E"));
//        }catch(InvalidAccountException e){
//            fail();
//        }
        assertTrue(expenseManager.getAccountNumbersList().contains("200E"));
    }

    @Test
    public void checkLoggedTransaction(){
       int previousTransactionListSize = expenseManager.getTransactionLogs().size();
       expenseManager.getTransactionsDAO().logTransaction(new Date(),"200E", ExpenseType.EXPENSE, 2000);
       int lastTransactionListSize = expenseManager.getTransactionLogs().size();

       assertTrue(lastTransactionListSize - previousTransactionListSize == 1 );
    }

}