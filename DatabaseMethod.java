//	import java.sql.Connection;
//	import java.sql.ResultSet;
//	import java.sql.Statement;
//	import java.util.ArrayList;
//	import java.util.List;
//
//	import javax.sql.DataSource;
//
//import com.ecommerce.Student;
//import com.ecommerce.Subject;
//import com.ecommerce.Teacher;
//
//	
//
//	public class DatabaseMethod {
//
//		private DataSource dataSource;
//
//		public DatabaseMethod(DataSource dataSource) {
//			this.dataSource = dataSource;
//		}
//
//		public List<Student> getStudents() {
//
//			List<Student> students = new ArrayList<>();
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM student";
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("student_id");
//					String firstName = myRs.getString("student_name");
//					String lastName = myRs.getString("class_id");
//					int age = myRs.getInt("age");
//					int aclass = myRs.getInt("contact");
//
//					// create new student object
//					Student tempStudent = new Student(id, firstName, lastName, age, aclass);
//
//					// add it to the list of students
//					students.add(tempStudent);
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return students;
//
//		}
//
//		public List<Teacher> getTeachers() {
//
//			List<Teacher> teachers = new ArrayList<>();
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM teacher";
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("id");
//					String firstName = myRs.getString("teacher_name");
//					String lastName = myRs.getString("contact");
//					int age = myRs.getInt("age");
//
//					// create new student object
//					Teacher temp = new Teacher(id, firstName, lastName, age);
//
//					// add it to the list of students
//					teachers.add(temp);
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return teachers;
//
//		}
//
//		public List<Subject> getSubjects() {
//
//			List<Subject> subjects = new ArrayList<>();
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM subject";
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("id");
//					String name = myRs.getString("name");
//					//int shortcut = myRs.getString("shortcut");
//
//					// create new student object
//					Subject temp = new Subject(id, name,0,0);
//
//					// add it to the list of students
//					subjects.add(temp);
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return subjects;
//
//		}
//
//		public List<Class> getClasses() {
//
//			List<Class> classes = new ArrayList<>();
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM classes";
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("id");
//					
//					int subject = myRs.getInt("subject");
//					int teacher = myRs.getInt("teacher_id");
//					String time = myRs.getString("class_id");
//
//					Teacher tempTeacher = loadTeacher(teacher);
//					Subject tempSubject = loadSubject(subject);
//
//					String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();
//
//					// create new student object
//					Class temp = new Class(id, subject, teacher_id,class_id, tempSubject.getName());
//
//					// add it to the list of students
//					classes.add(temp);
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return classes;
//
//		}
//
//		public Teacher loadTeacher(int teacherId) {
//
//			Teacher theTeacher = null;
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM teachers WHERE id = " + teacherId;
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("id");
//					String fname = myRs.getString("teacher_name");
//					String lname = myRs.getString("contact");
//					int age = myRs.getInt("age");
//					theTeacher = new Teacher(id, teacher_name, contact, age);
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return theTeacher;
//
//		}
//
//		public Subject loadSubject(int subjectId) {
//
//			Subject theSubject = null;
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM subject WHERE id = " + subjectId;
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("id");
//					String name = myRs.getString("subject");
//					String shortcut = myRs.getString("teacher_id");
//					String class_id = myRs.getInt("class_id");
//
//					theSubject = new Subject(id, name,shortcut);
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return theSubject;
//
//		}
//
//		public Class loadClass(int classId) {
//
//			Class theClass = null;
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM clasess WHERE id = " + id;
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("id");
//					int section = myRs.getInt("class_number");
//					int subject = myRs.getString("section");
//					
//					Teacher tempTeacher = loadTeacher(teacher);
//					Subject tempSubject = loadSubject(subject);
//
//					String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return theClass;
//
//		}
//
//		public List<Student> loadClassStudents(int classId) {
//
//			List<Student> students = new ArrayList<>();
//
//			Connection myConn = null;
//			Statement myStmt = null;
//			ResultSet myRs = null;
//
//			try {
//
//				// get a connection
//				myConn = dataSource.getConnection();
//
//				// create sql stmt
//				String sql = "SELECT * FROM students WHERE class = " + classId;
//				myStmt = myConn.createStatement();
//
//				// execute query
//				myRs = myStmt.executeQuery(sql);
//
//				// process result
//				while (myRs.next()) {
//
//					// retrieve data from result set row
//					int id = myRs.getInt("student_id");
//					String firstName = myRs.getString("student_name");
//					String lastName = myRs.getInt("class_id");
//					int age = myRs.getInt("age");
//					int aclass = myRs.getInt("contact");
//
//					// create new student object
//					Student tempStudent = new Student(id, student_name, class_id, age, contact);
//					students.add(tempStudent);
//
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			} finally {
//				// close JDBC objects
//				close(myConn, myStmt, myRs);
//			}
//			return students;
//
//		}
//
//		private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
//
//			try {
//				if (myRs != null) {
//					myRs.close();
//				}
//				if (myStmt != null) {
//					myStmt.close();
//				}
//				if (myConn != null) {
//					myConn.close();
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}
//
//}
