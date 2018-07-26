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

import com.test.web.model.LoginCredentials;

@Repository
public class LoginDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HbaseTemplate hbaseTemplate;

	private String tableNameStr = "LoginCredentials";
	private String columnFamily = "Credentials";
	private byte[] loginColumnFamily = Bytes.toBytes(columnFamily);
	private byte[] userName = Bytes.toBytes("userName");
	private byte[] password = Bytes.toBytes("password");

	private Admin admin;
	private TableName tableName;

	public LoginDao() {
	}

	@PostConstruct
	public void init() {

		try {
			admin = ConnectionFactory.createConnection(hbaseTemplate.getConfiguration()).getAdmin();
			tableName = TableName.valueOf(tableNameStr);

			if (!admin.tableExists(tableName)) {
				HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
				HColumnDescriptor columnDescriptor = new HColumnDescriptor(loginColumnFamily);
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

	public void insert(LoginCredentials loginData) {
		hbaseTemplate.execute(tableNameStr, new TableCallback<Object>() {
			public Object doInTable(HTableInterface table) throws Throwable {
				Put loginDetails = new Put(Bytes.toBytes(loginData.getUserName()));
				loginDetails.addColumn(loginColumnFamily, userName, Bytes.toBytes(loginData.getPassword()));
				table.put(loginDetails);
				return null;
			}
		});
	}

	public LoginCredentials findOne(String userName) {
		LoginCredentials loginDetails = hbaseTemplate.get(tableNameStr, userName, columnFamily,
				new LoginDataRowMapper());

		if (loginDetails.getUserName() == null) {
			logger.info("No data present in db for username >>>" + userName);
			loginDetails = null;
		}

		return loginDetails;
	}

	private class LoginDataRowMapper implements RowMapper<LoginCredentials> {
		@Override
		public LoginCredentials mapRow(Result result, int rowNum) throws Exception {
			LoginCredentials loginData = new LoginCredentials();
			loginData.setUserName(Bytes.toString(result.getRow()));
			loginData.setPassword(Bytes.toString(result.getValue(loginColumnFamily, password)));
			logger.info("Username : " + loginData.getUserName() + " Password : " + loginData.getPassword());
			return loginData;
		}
	}

}
