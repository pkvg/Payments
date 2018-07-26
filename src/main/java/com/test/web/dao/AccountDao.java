package com.test.web.dao;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import com.test.web.model.Account;

@Repository
public class AccountDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HbaseTemplate hbaseTemplate;

	private String tableNameStr = "AccountDetail";
	private String columnFamily = "PersonalData";
	private byte[] accountColumnFamily = Bytes.toBytes(columnFamily);
	private byte[] name = Bytes.toBytes("name");
	private byte[] age = Bytes.toBytes("age");
	private byte[] amount = Bytes.toBytes("amount");

	private Admin admin;
	private TableName tableName;

	public AccountDao() {
	}

	@PostConstruct
	public void init() {

		try {
			admin = ConnectionFactory.createConnection(hbaseTemplate.getConfiguration()).getAdmin();
			tableName = TableName.valueOf(tableNameStr);

			if (!admin.tableExists(tableName)) {
				HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
				HColumnDescriptor columnDescriptor = new HColumnDescriptor(accountColumnFamily);
				tableDescriptor.addFamily(columnDescriptor);
				admin.createTable(tableDescriptor);
				logger.info(tableName + "Table created");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				admin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void insert(Account account) {
		hbaseTemplate.execute(tableNameStr, new TableCallback<Object>() {
			public Object doInTable(HTableInterface table) throws Throwable {
				Put accountData = new Put(Bytes.toBytes(account.getAccountId()));
				accountData.addColumn(accountColumnFamily, name, Bytes.toBytes(account.getName()));
				accountData.addColumn(accountColumnFamily, age, Bytes.toBytes(account.getAge()));
				accountData.addColumn(accountColumnFamily, amount, Bytes.toBytes(account.getAmount()));
				table.put(accountData);
				return null;
			}
		});
	}

	public Account findOne(String accountId) {
		Account accountDetails = hbaseTemplate.get(tableNameStr, accountId, columnFamily, new AccountDataRowMapper());

		if (accountDetails.getAccountId() == null) {
			logger.info("No data present in db for id >>>" + accountId);
			accountDetails = null;
		}

		return accountDetails;
	}

	private class AccountDataRowMapper implements RowMapper<Account> {
		@Override
		public Account mapRow(Result result, int rowNum) throws Exception {
			Account account = new Account();
			account.setAccountId(Bytes.toString(result.getRow()));
			account.setName(Bytes.toString(result.getValue(accountColumnFamily, name)));
			account.setAge(Bytes.toString(result.getValue(accountColumnFamily, age)));
			account.setAmount(Bytes.toString(result.getValue(accountColumnFamily, amount)));
			logger.info("Age : " + account.getAge() + " Name : " + account.getName());
			return account;
		}
	}

}
