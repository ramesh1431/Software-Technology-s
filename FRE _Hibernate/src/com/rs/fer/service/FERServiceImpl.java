
package com.rs.fer.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.rs.fer.DBUtil.DBUtil;
import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.PersonalInfo;
import com.rs.fer.bean.User;
import com.rs.util.HibernateUtil;

public class FERServiceImpl implements FERService {
	//final static Logger logger = Logger.getLogger(FERServiceImpl.class);
	final static Logger logger = Logger.getLogger(FERServiceImpl.class);

	public boolean Login(String username, String password) {
		
		// Connection connection = null;

		/*
		 * try { // connection = DBUtil.getConnection();
		 * 
		 * PreparedStatement p = connection.prepareStatement(
		 * "select * from User where Username=? and  password=?");
		 * p.setString(1, username); p.setString(2, password);
		 * 
		 * ResultSet resultset = p.executeQuery();
		 * 
		 * while (resultset.next()) { isValidUser = true; } } catch
		 * (SQLException e) { e.printStackTrace(); } finally {
		 * DBUtil.closeConnection(connection); }
		 */
		boolean isValidUser = false;
		logger.info("login:: input :" + username);
		logger.debug("login::input:" + password);
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(User.class);
			Criterion crit = Restrictions.eq("username", username);
			Criterion crit1 = Restrictions.eq("password", password);
			criteria.add(crit);
			criteria.add(crit1);
			List<String> l = criteria.list();
			if (l != null) {
				return l.get(0) != null;

			}
		} catch (Exception e) {
			logger.error("login:: SQLExpection:" + e);
			session.close();
		}
		logger.info("login:: output:" + isValidUser);
		return isValidUser;
	}

	public boolean registration(User user) {
		Session session = null;
		boolean registrationFlag = false;
		try {

			session = HibernateUtil.getSession();

			Transaction tr = session.beginTransaction();
			session.save(user);
			tr.commit();
			registrationFlag = true;

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return registrationFlag;

	}
	// Connection connection = null;

	/*
	 * PreparedStatement pstatement = null; ; try { //connection =
	 * DBUtil.getConnection();
	 * 
	 * pstatement = connection.prepareStatement(
	 * "insert into User (firstName,middleName,lastName,Email,phone,Username,password) values(?,?,?,?,?,?,?)"
	 * );
	 * 
	 * pstatement.setString(1, user.getFirstName()); pstatement.setString(2,
	 * user.getMiddleName()); pstatement.setString(3, user.getLastName());
	 * pstatement.setString(4, user.getEmail()); pstatement.setString(5,
	 * user.getPhone()); pstatement.setString(6, user.getUsername());
	 * pstatement.setString(7, user.getPassword());
	 * 
	 * int numOfRecInserted = pstatement.executeUpdate(); //connection.commit();
	 * 
	 * return numOfRecInserted > 0;
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * DBUtil.closeConnection(connection); }
	 * 
	 * return false;
	 */

	public boolean addExpense(Expense expense) {
		// TODO Auto-generated method stub

		// Connection connection = DBUtil.getConnection();
		boolean addExpense = false;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tr = session.beginTransaction();
			session.save(expense);
			tr.commit();
			addExpense = true;
		} catch (Exception e) {
			session.close();
		}
		return false;
	}
	/*
	 * try { PreparedStatement pstatement;
	 * 
	 * pstatement = connection.prepareStatement(
	 * "insert into expenses(expensetype,date,price,numberofitems,total,bywhom)values(?,?,?,?,?,?)"
	 * );
	 * 
	 * pstatement.setString(1, expense.getExpensetype());
	 * pstatement.setString(2, expense.getDate()); pstatement.setString(3,
	 * expense.getPrice()); pstatement.setString(4, expense.getNumberofitems());
	 * pstatement.setString(5, expense.getTotal()); pstatement.setString(6,
	 * expense.getBywhom()); // sspstatement.setInt(7, expense.getId()); int
	 * noofresadd = pstatement.executeUpdate(); return noofresadd > 0;
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * 
	 * e.printStackTrace();
	 * 
	 * } finally {
	 * 
	 * DBUtil.closeConnection(connection); } // System.out.println(addExpense);
	 */

	public boolean deleteExpense(int expenseid) {
		// Connection connection = null;
		// PreparedStatement p = null;
		boolean deleteExpenseFlag = false;

		/*
		 * try { // connection = DBUtil.getConnection();
		 * 
		 * p = connection.prepareStatement("delete from expenses where id=?");
		 * 
		 * p.setInt(1, expenseid); int deletedId = p.executeUpdate();
		 * 
		 * System.out.println(deletedId); deleteExpenseFlag = true; } catch
		 * (SQLException e) { e.printStackTrace(); } finally {
		 * DBUtil.closeConnection(connection); }
		 */
		Session session = null;

		/*
		 * session = HibernateUtil.getSession(); Criteria criteria =
		 * session.createCriteria(Expense.class); Criterion crit =
		 * Restrictions.eq("id", expenseid); criteria.add(crit);
		 */
		/*
		 * Query q=session.createQuery(
		 * "delete from expense_table where expenseid=?"); int i=
		 * q.executeUpdate();
		 */

		try {
			session = HibernateUtil.getSession();
			Expense expense = (Expense) session.get(Expense.class, expenseid);
			Transaction tx = session.beginTransaction();
			session.delete(expense);
			tx.commit();
			deleteExpenseFlag = true;
		} catch (Exception e) {
			session.close();
		}

		return deleteExpenseFlag;
	}

	public boolean editExpense(Expense expense) {

		// Connection connection = DBUtil.getConnection();

		// PreparedStatement pstatement;
		boolean editExpenseFlag = false;
		/*
		 * try { pstatement = connection.prepareStatement(
		 * "update expenses set expensetype=? ,date=?,price=?,numberofitems=?,total=?,bywhom=? where id=?"
		 * );
		 * 
		 * pstatement.setString(1, expense.getExpensetype());
		 * pstatement.setString(2, expense.getDate()); pstatement.setString(3,
		 * expense.getPrice()); pstatement.setString(4,
		 * expense.getNumberofitems()); pstatement.setString(5,
		 * expense.getTotal()); pstatement.setString(6, expense.getBywhom());
		 * pstatement.setInt(7, expense.getId()); int updateid =
		 * pstatement.executeUpdate(); editExpenseFlag = true; // return
		 * updateid > 0; } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } finally {
		 * DBUtil.closeConnection(connection); }
		 */
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tr = session.beginTransaction();

			session.update(expense);
			tr.commit();
		} catch (Exception e) {
			session.close();
		}
		return editExpenseFlag;
	}

	public Expense getExpense(int expenseId) {

		/*
		 * Connection connection = DBUtil.getConnection();
		 * 
		 * PreparedStatement p = null; Expense expense = null; try { p =
		 * connection.prepareStatement("select * from expenses where id=?");
		 * p.setInt(1, expenseId);
		 * 
		 * ResultSet resultset = p.executeQuery();
		 * 
		 * while (resultset.next()) { expense = new Expense();
		 * expense.setId(resultset.getInt("id"));
		 * expense.setExpensetype(resultset.getString("expensetype"));
		 * expense.setDate(resultset.getString("date"));
		 * expense.setPrice(resultset.getString("price"));
		 * expense.setNumberofitems(resultset.getString("numberofitems"));
		 * expense.setTotal(resultset.getString("total"));
		 * expense.setBywhom(resultset.getString("bywhom"));
		 * 
		 * // expense.setRemarks(resultset.getString(8));;
		 * 
		 * System.out.println("total:" + expense.getTotal());
		 * System.out.println("type=" + expense.getExpensetype()); }
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } finally {
		 * 
		 * DBUtil.closeConnection(connection); }
		 */
		Expense expense = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from Expense" + " e where e.id='" + expenseId + "'");

			/*
			 * List<Expense>expenses = query.list(); for(Expense expen :
			 * expenses){ System.out.println(expen); }
			 */
			List<Expense> list = query.list();
			Iterator<Expense> iterator = list.iterator();
			while (iterator.hasNext()) {
				expense = iterator.next();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return expense;
	}

	public List<Expense> getExpenseReport(String expensetype, String fromdate, String todate) {
		// List<Expense> expenses = new ArrayList<Expense>();

		// Connection connection = DBUtil.getConnection();

		/*
		 * PreparedStatement p; try { p = connection.prepareStatement(
		 * "select * from expenses where expensetype='" + expensetype +
		 * "'and date between '" + fromdate + "' and '" + todate + "' ");
		 * 
		 * ResultSet resultset = (ResultSet) p.executeQuery(); while
		 * (resultset.next()) { Expense expense = new Expense();
		 * expense.setId(resultset.getInt(1));
		 * expense.setExpensetype(resultset.getString(2));
		 * expense.setDate(resultset.getString(3));
		 * expense.setPrice(resultset.getString(4));
		 * expense.setNumberofitems(resultset.getString(5));
		 * expense.setTotal(resultset.getString(6));
		 * expense.setBywhom(resultset.getString(7)); expenses.add(expense); } }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } finally { DBUtil.closeConnection(connection);
		 * }
		 */

		Session session = null;
		Expense expense = null;
		List<Expense> expenses = new ArrayList<Expense>();
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from Expense e where e.expensetype='" + expensetype
					+ "'and e.date between'" + fromdate + "'and'" + todate + "'");
			List<Expense> list = query.list();
			Iterator<Expense> iterator = list.iterator();
			while (iterator.hasNext()) {
				expense = iterator.next();
				expenses.add(expense);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return expenses;

	}

	/*
	 * for (Expense expense1 : expenses) { System.out.println("Id: " +
	 * expense1.getId() + " and expensetype: " + expense1.getExpensetype());
	 * 
	 * }
	 */

	public List<Expense> getExpenses() {
		List<Expense> expenses = new ArrayList<Expense>();
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement p;
		 * try { p = connection.prepareStatement("select * from expenses ");
		 * 
		 * ResultSet resultset = (ResultSet) p.executeQuery(); while
		 * (resultset.next()) { Expense expense = new Expense();
		 * expense.setId(resultset.getInt(1));
		 * expense.setExpensetype(resultset.getString(2));
		 * expense.setDate(resultset.getString(3));
		 * expense.setPrice(resultset.getString(4));
		 * expense.setNumberofitems(resultset.getString(5));
		 * expense.setTotal(resultset.getString(6));
		 * expense.setBywhom(resultset.getString(7)); //
		 * expense.setRemarks(resultset.getString(8));;
		 * 
		 * expenses.add(expense);
		 * 
		 * } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace();
		 * 
		 * } finally { DBUtil.closeConnection(connection); }
		 */
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria cr = session.createCriteria(Expense.class);

			List<Expense> list = cr.list();
			/*
			 * Iterator<Expense> iterator = list.iterator(); while
			 * (iterator.hasNext()) { expense = iterator.next();
			 * expenses.add(expense);
			 */
			for (Expense e : list) {
				expenses.add(e);
			}
		} catch (Exception e) {
			session.close();
		}

		return expenses;
	}

	public boolean resetPassword(String id, String oldpassword, String newpassword) {
		// Connection connection = null;
		// connection = DBUtil.getConnection();
		boolean resetPasswordFlag = false;
		/*
		 * try {
		 * 
		 * PreparedStatement
		 * 
		 * p = connection.prepareStatement(
		 * "update User set password=? where username=? and password=?");
		 * p.setString(1, newpassword); p.setString(2, username); p.setString(3,
		 * oldpassword);
		 * 
		 * int updateid = p.executeUpdate(); return updateid > 0; } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } finally {
		 * 
		 * DBUtil.closeConnection(connection); // System.out.println(updateid);
		 * }
		 */
		Session session = null;

		try {
			session = HibernateUtil.getSession();
			User user = (User) session.get(User.class, id);
			if (oldpassword.equals(user.getPassword())) {
				user.setPassword(newpassword);
				Transaction tr = session.beginTransaction();
				// session.update(user);
				tr.commit();

				resetPasswordFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resetPasswordFlag;
	}

	public PersonalInfo getPersonalInfo(String username) {
		PersonalInfo persanlInfo = new PersonalInfo();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparesatement;
		try {
			preparesatement = connection.prepareStatement(
					"SELECT u.*, a.* FROM USER u LEFT JOIN address a ON u.id=a.UserId WHERE u.username='" + username
							+ "'");
			ResultSet resultset = (ResultSet) preparesatement.executeQuery();
			while (resultset.next()) {
				User user = new User();
				user.setId(resultset.getInt(1));
				user.setFirstName(resultset.getString(2));
				user.setMiddlename(resultset.getString(3));
				user.setLastName(resultset.getString(4));
				user.setEmail(resultset.getString(5));
				user.setPhone(resultset.getString(6));
				user.setUsername(resultset.getString(7));
				user.setPassword(resultset.getString(8));

				Address address = new Address();
				address.setId(resultset.getInt(9));
				address.setCountry(resultset.getString(10));
				address.setState(resultset.getString(11));
				address.setDistric(resultset.getString(12));
				address.setMandal(resultset.getString(13));
				address.setZip(resultset.getString(14));
				address.setUserId(resultset.getString(15));

				persanlInfo.setUser(user);
				persanlInfo.setAddress(address);

				System.out.println(persanlInfo.getUser().getFirstName());

				System.out.println(persanlInfo.getAddress().getDistric());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);

		}

		return persanlInfo;
	}

	public boolean updatePersonalInfo(User user, Address address) {
		Connection connection = DBUtil.getConnection();

		// address.setUserid(2);
		boolean isUpdated = false;

		PreparedStatement preparedStatement = null;

		try {
			/*
			 * Class.forName("com.mysql.jdbc.Driver"); Connection connection =
			 * (Connection) DriverManager.getConnection(
			 * "jdbc:mysql://localhost:3306/familyexpensereport", "root",
			 * "user");
			 */

			String sqlquery = "update user set firstName=?,middleName=?,lastName=?,email=?,phone=?,username=?,password=?  where id=?";
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlquery);
			// preparedStatement=connection.prepareStatement("update user set
			// firstName=?,middleName=?,lastName=?,email=?,userName=?,password=?
			// ,mobilenumber=? where id=?");
			// preparedStatement=connection.prepareStatement("insert into user
			// (id,firstname,middlename,lastname,email,username,password,mobilenumber)values(?,?,?,?,?,?,?,?)");
			// user

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddlename());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setString(6, user.getUsername());
			preparedStatement.setString(7, user.getPassword());
			// preparedStatement.setString(8, user.getUserId());

			preparedStatement.setInt(8, user.getId());
			int noofRec = preparedStatement.executeUpdate();
			// System.out.println("NOOFREC Insert:" + noofRec);
			// address
			if (noofRec > 0) {
				int recordsUpdated = 0;
				String query = "";

				if (address.getId() == 0) {

					query = ("insert into address (country, state, distric,mandal , zip, UserId)values(?,?,?,?,?,?)");
					preparedStatement.setString(1, address.getCountry());
					preparedStatement.setString(2, address.getState());
					preparedStatement.setString(3, address.getDistric());
					preparedStatement.setString(4, address.getMandal());
					preparedStatement.setString(5, address.getZip());
					preparedStatement.setString(6, address.getUserId());
				} else {

					preparedStatement = (PreparedStatement) connection.prepareStatement(
							"update address set country=?,state=?,distric=?,mandal=?,zip=?,UserId=?, where id=?");

					preparedStatement.setString(1, address.getCountry());
					preparedStatement.setString(2, address.getState());
					preparedStatement.setString(3, address.getDistric());
					preparedStatement.setString(4, address.getMandal());
					preparedStatement.setString(5, address.getZip());
					preparedStatement.setString(6, address.getUserId());

					preparedStatement.setInt(7, address.getId());
					/*
					 * recordsUpdated=preparedStatement.executeUpdate();
					 * isUpdated =recordsUpdated>0;
					 */

				}
			}
			// preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		finally {

			DBUtil.closeConnection(connection);
		}

		return isUpdated;
	}

	public PersonalInfo getPersonalInfoMain(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public PersonalInfo getPersonalInfoMain(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
