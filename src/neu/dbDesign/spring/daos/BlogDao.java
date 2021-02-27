package neu.dbDesign.spring.daos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import neu.dbDesign.spring.models.Blog;

public class BlogDao {
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String HOST = "localhost:3306";
  static final String SCHEMA = "db_design";
  static final String CONFIG = "serverTimezone=UTC";
  static final String DB_URL =
      "jdbc:mysql://"+HOST+"/"+SCHEMA+"?"+CONFIG;
  static final String USER = "root";
  static final String PASS = "P@ssw0rd";

  public void createBlog() {}

  public List<Blog> findAllBlog() throws ClassNotFoundException, SQLException {
    Connection connection = null;
    Statement statement = null;

    List<Blog> blogs = new ArrayList<Blog>();

    Class.forName(JDBC_DRIVER);
    connection = DriverManager.getConnection(DB_URL,USER,PASS);
    statement = connection.createStatement();
    String sql = "SELECT * FROM blogs";
    ResultSet result = statement.executeQuery(sql);
    while(result.next()) {
      Integer id = result.getInt("id");
      String name = result.getString("name");
      String topics = result.getString("topic");
      Timestamp created = result.getTimestamp("created");
      Timestamp updated = result.getTimestamp("updated");
      Integer user_id = result.getInt("user");

      Blog blog = new Blog();
      blog.setId(id);
      blog.setName(name);
      blog.setTopic(topics);
      blog.setCreated(created);
      blog.setUpdated(updated);
      blog.setUserId(user_id);

      blogs.add(blog);
    }
    return blogs;
  }
  public Blog findBlogById(Integer userid) throws ClassNotFoundException, SQLException {
    Connection connection = null;
    Statement statement = null;

    Blog blog = null;

    Class.forName(JDBC_DRIVER);
    connection = DriverManager.getConnection(DB_URL,USER,PASS);
    statement = connection.createStatement();
    String sql = "SELECT * FROM blogs WHERE id =" + userid;
    ResultSet result = statement.executeQuery(sql);

    if (result.next()) {
      Integer id = result.getInt("id");
      String name = result.getString("name");
      String topics = result.getString("topic");
      Timestamp created = result.getTimestamp("created");
      Timestamp updated = result.getTimestamp("updated");
      Integer user_id = result.getInt("user");

      blog = new Blog();
      blog.setId(id);
      blog.setName(name);
      blog.setTopic(topics);
      blog.setCreated(created);
      blog.setUpdated(updated);
      blog.setUserId(user_id);
    }
    return blog;

  }
  public void updateBlog() {}
  public void deleteBlog() {}

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    System.out.println("BlogDAO");

    BlogDao dao = new BlogDao();
    List<Blog> blogs = dao.findAllBlog();
    for (Blog blog : blogs) {
      //System.out.println(blog);
    }

    Blog spacex = dao.findBlogById(3);
    System.out.println(spacex);

  }
}
