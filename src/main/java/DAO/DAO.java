package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.TalkRequest;
import model.TechTalk;
import model.User;

public class DAO {
	private Connection mySqlConnect;

	// for MySQL DB
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_USER = "root";
	private static final String DB_PASSWD = "root";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String DB_NAME = "atmecs";

	public DAO() {
		mySqlConnect = getConnection();
	}

	// establish connection to the MySQL DB
	private Connection getConnection() {
		try {
			Class.forName(MYSQL_DRIVER);

			Connection con = DriverManager.getConnection(DB_URL + DB_NAME,
					DB_USER, DB_PASSWD);

			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public int addUser(User user) {
		int flag = isUserExist(user);

		if (flag != 0 && flag != 1) {
			// add user to the DB
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "INSERT INTO users(name, email, password) VALUES('"
						+ user.getName()
						+ "', '"
						+ user.getEmail()
						+ "', '"
						+ user.getPassword() + "');";

				if (stmt.executeUpdate(query) > 0) {
					// success
					mySqlConnect.close();
					return 100;
				} else {
					// failed to add new record
					mySqlConnect.close();
					return -1;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

		}

		return flag;
	}

	//
	private int isUserExist(User user) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "SELECT * FROM users WHERE email = '"
						+ user.getEmail() + "';";
				ResultSet resultSet = stmt.executeQuery(query); // This method
																// cannot be
																// called on a
																// PreparedStatement
																// or
																// CallableStatement.
				// a ResultSet object that contains the data produced by the
				// given query; never null

				if (resultSet.next()) {
					// 1 record exists
					return 1;
				} else {
					// no record returned
					return 2;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// mySqlConnect is Null or SQLException has occurred
		return 0;
	}

	// authenticate user
	public User authenticateUser(String email, String passwd) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "SELECT password, name FROM users WHERE email = '"
						+ email + "';";

				ResultSet resultSet = stmt.executeQuery(query);
				if (resultSet.next()) {
					String password = resultSet.getString(1);

					if (passwd.equals(password)) {
						// user verified
						return new User(resultSet.getString(2), email, passwd);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	// adds new tech talk
	public int addNewTalk(TechTalk techTalk) {
		if (mySqlConnect != null) {
			try {

				Statement stmt = mySqlConnect.createStatement();
				String query = "INSERT INTO techtonics(date, title, description, presenter) VALUES('"
						+ techTalk.getDate()
						+ "', '"
						+ techTalk.getTitle()
						+ "', '"
						+ techTalk.getDescription()
						+ "', '"
						+ techTalk.getPresenter() + "');";
				int count = stmt.executeUpdate(query);
				mySqlConnect.close();

				if (count > 0) {
					// success
					return 100;
				} else {
					// failure
					return -1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return 0;
	}

	// retrieves all upcoming tech talks
	public List<TechTalk> fetchAllTechTalks() {
		if (mySqlConnect != null) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				Statement stmt = mySqlConnect.createStatement();
				// String query = "SELECT * FROM techtonics WHERE date >= '" +
				// dateFormat.format(new Date()) + "';";
				String query = "SELECT id, DATE(date), title, description, presenter FROM techtonics WHERE date >= '"
						+ dateFormat.format(new Date()) + "';";

				ResultSet resultSet = stmt.executeQuery(query);
				// mySqlConnect.close();

				List<TechTalk> lstTalks = new ArrayList<TechTalk>();
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String date = resultSet.getString(2);
					String title = resultSet.getString("title");
					String desc = resultSet.getString("description");
					String presenter = resultSet.getString("presenter");

					lstTalks.add(new TechTalk(id, date, title, desc, presenter));
				}

				return lstTalks;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	// method to return tech talk info with the given id
	public TechTalk getTechTalk(String techTalkId) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "SELECT * FROM techtonics WHERE id = "
						+ techTalkId + ";";

				ResultSet resultSet = stmt.executeQuery(query);
				if (resultSet.next()) {
					int id = resultSet.getInt("id");
					String date = resultSet.getString(2);
					String title = resultSet.getString("title");
					String desc = resultSet.getString("description");
					String presenter = resultSet.getString("presenter");

					return new TechTalk(id, date, title, desc, presenter);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	// update the details of the tech talk
	public int updateTalk(TechTalk talk) {
		if (mySqlConnect != null) {
			System.out.println(talk);
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "UPDATE techtonics SET date='" + talk.getDate()
						+ "', title='" + talk.getTitle() + "', description='"
						+ talk.getDescription() + "', presenter='"
						+ talk.getPresenter() + "' WHERE id=" + talk.getId()
						+ ";";
				System.out.println(query);
				if (stmt.executeUpdate(query) > 0) {
					// success
					return 100;
				} else {
					// failure
					return -1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	// registers a user for a tech talk
	public int registerTechTalk(String talkId, String email) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "INSERT INTO registered VALUES(" + talkId
						+ ", '" + email + "');";

				if (stmt.executeUpdate(query) > 0) {
					return 100; // success
				} else {
					return -1; // failure
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return 0;
	}

	// get all the registered users(email) for the given talkId
	public List<User> fethRegistration(String talkId) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "SELECT name, users.email FROM users, registered WHERE users.email=registered.email AND reg_id="
						+ talkId + " ;";
				// SELECT name, users.email FROM users, registered WHERE
				// users.email=registered.email AND reg_id= ;
				ResultSet resultSet = stmt.executeQuery(query);
				List<User> emailLst = new ArrayList<User>();
				while (resultSet.next()) {
					String name = resultSet.getString(1);
					String email = resultSet.getString("email");
					emailLst.add(new User(name, email));
				}

				return emailLst;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	// fetches the techtalks registered by user
	public List<Integer> fetchMyRegistartion(String curEmail) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "SELECT reg_id FROM registered WHERE email='"
						+ curEmail + "';";
				// SELECT name, users.email FROM users, registered WHERE
				// users.email=registered.email AND reg_id= ;
				ResultSet resultSet = stmt.executeQuery(query);
				List<Integer> techIdlst = new ArrayList<Integer>();
				while (resultSet.next()) {
					Integer id = resultSet.getInt(1);
					techIdlst.add(id);
				}

				return techIdlst;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	// unregisters the techtalk for the user
	public int unregisterTechTalk(String talkId, String email) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "DELETE FROM registered WHERE email='" + email
						+ "' AND reg_id=" + talkId + ";";

				if (stmt.executeUpdate(query) > 0) {
					return 100; // success
				} else {
					return -1; // failure
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return 0;
	}

	// deletes the tech talk with id
	public int deleteTechTalk(String talkId) {
		if (mySqlConnect != null) {
			try {
				Statement stmt = mySqlConnect.createStatement();
				String query = "DELETE FROM techtonics WHERE id=" + talkId
						+ ";";

				if (stmt.executeUpdate(query) > 0) {
					return 100; // success
				} else {
					return -1; // failure
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return 0;

	}

	// makes a request for a techtalk
	public int makeRequest(String topic, String email) {
		if (mySqlConnect != null) {
			try {

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = dateFormat.format(new Date());
				Statement stmt = mySqlConnect.createStatement();
				String query = "INSERT INTO requests(date, topic, email) VALUES('"
						+ date + "', '" + topic + "', '" + email + "');";

				if (stmt.executeUpdate(query) > 0) {
					return 100; // success
				} else {
					return -1; // failure
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return 0;
	}

	// gets the user's request
	public List<TalkRequest> fetchMyRequests(String e_mail) {
		if (mySqlConnect != null) {
			try {

				Statement stmt = mySqlConnect.createStatement();
				String query = "SELECT * FROM requests WHERE email='" + e_mail
						+ "';";

				ResultSet resultSet = stmt.executeQuery(query);
				List<TalkRequest> reqLst = new ArrayList<TalkRequest>();

				while (resultSet.next()) {
					String reqId = resultSet.getString(1);
					String date = resultSet.getString(2);
					String topic = resultSet.getString(3);
					String email = resultSet.getString(4);
					String status = resultSet.getString(5);

					reqLst.add(new TalkRequest(reqId, date, topic, email,
							status));
				}

				return reqLst;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	// metch to fetch all users' requests
	public List<TalkRequest> fetchAllRequests() {
		if (mySqlConnect != null) {
			try {

				Statement stmt = mySqlConnect.createStatement();
				String query = "SELECT * FROM requests;";

				ResultSet resultSet = stmt.executeQuery(query);
				List<TalkRequest> reqLst = new ArrayList<TalkRequest>();

				while (resultSet.next()) {
					String reqId = resultSet.getString(1);
					String date = resultSet.getString(2);
					String topic = resultSet.getString(3);
					String email = resultSet.getString(4);
					String status = resultSet.getString(5);

					reqLst.add(new TalkRequest(reqId, date, topic, email,
							status));
				}

				return reqLst;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	// method to approve the users' request
	public List<TalkRequest> approveRequest(String reqId) {
		if (mySqlConnect != null) {
			try {

				Statement stmt = mySqlConnect.createStatement();
				String query = "UPDATE requests SET status='approved' WHERE req_id=" + reqId + ";";

				if (stmt.executeUpdate(query) > 0) {
					List<TalkRequest> reqLst = fetchAllRequests();
					
					System.out.println("DAO: " + reqLst);
					
					return reqLst;
				} else
					return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

}
