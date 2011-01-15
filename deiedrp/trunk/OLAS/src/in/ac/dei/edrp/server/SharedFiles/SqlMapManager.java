package in.ac.dei.edrp.server.SharedFiles;

import com.ibatis.common.resources.Resources;

import com.ibatis.db.sqlmap.SqlMap;

import com.ibatis.sqlmap.client.*;

import java.io.Reader;

public class SqlMapManager {
	private static SqlMap sqlMap = null;
	static SqlMapManager smm;

	public static SqlMapClient getSqlMapClient() {
		try {
			Reader reader = Resources
					.getResourceAsReader("in/ac/dei/edrp/server/SharedXml/SqlMapConfig.xml");
			SqlMapClient sqlMapper = SqlMapClientBuilder
					.buildSqlMapClient(reader);
			reader.close();

			return sqlMapper;
		} catch (Exception e) {
			System.out.println("Map Exception");
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * @param sqlMap
	 *            the sqlMap to set
	 */
	public void setSqlMap(SqlMap sqlMap) {
		SqlMapManager.sqlMap = sqlMap;
	}

	/**
	 * @return the sqlMap
	 */
	public static SqlMap getSqlMap() {
		return sqlMap;
	}
}
