package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MessageDTO;

//DB의 CRUD기능을 위한 클래스
public class MessageDAO {
			private static MessageDAO dao;
			private MessageDAO() {}
			// 싱글톤을 위한 함수
			static public MessageDAO getInstance() {
						if (dao == null) {
									dao = new MessageDAO();
						}
						return dao;
			}

			public void insert(Connection conn, MessageDTO msg) {
						PreparedStatement pstmt = null;
						
						try {
									String sql = "insert into "+DBKeyword.Table_GuestBook+" ( "
															+ DBKeyword.Column_User+" , "
															+ DBKeyword.Column_Content+" , "
															+ DBKeyword.Column_Time+" , "
															+ DBKeyword.Column_Password
															+ " ) values (?,?,?,?) ";
									pstmt = conn.prepareStatement(sql);
									//user
									pstmt.setString(1, msg.getUser());
									//content
									pstmt.setString(2, msg.getContents());
									//time
									pstmt.setString(3, DBUtil.getTime());
									//psw
									pstmt.setString(4, msg.getPassword());
									pstmt.executeUpdate();
						}
						catch(SQLException e){ 
									e.printStackTrace();
						}
						finally {
									DBUtil.close(pstmt);
						}
			}
			public ArrayList<MessageDTO> select_AllList(Connection conn){
						Statement stmt = null;
						ResultSet rs = null;
						try{
									String sql = " select * from "
															+DBKeyword.Table_GuestBook
															+" order by "
															+DBKeyword.Column_Time
															+" asc ";
									stmt = conn.createStatement();
									rs = stmt.executeQuery(sql);
									if(rs.next()){
												ArrayList<MessageDTO> msgList = new ArrayList();
												msgList.add(makeMessage(rs));
												while(rs.next()){
															msgList.add(makeMessage(rs));
												}
												return msgList;
									}else{
												return null;
									}
						}
						catch(SQLException e){ 
									e.printStackTrace();
						}
						finally {
									DBUtil.close(stmt);
									DBUtil.close(rs);
						}
						return null;
			}
			public MessageDTO select_ById(Connection conn, int msgId) throws SQLException{
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try{
									String sql = " select * from "
															+DBKeyword.Table_GuestBook
															+" where "
															+DBKeyword.Column_id+" = ? ";
									pstmt = conn.prepareStatement(sql);
									pstmt.setInt(1, msgId);
									rs = pstmt.executeQuery();
									if(rs.next()){
												return makeMessage(rs);
									}else{
												return null;
									}
						}
						finally {
									DBUtil.close(pstmt);
									DBUtil.close(rs);
						}
			}
			public void update(Connection conn, MessageDTO msg) throws SQLException{
						PreparedStatement pstmt = null;
						try{
									String sql = " update "
															+DBKeyword.Table_GuestBook
															+" set "
															+DBKeyword.Column_Content
															+" = ? "
															+DBKeyword.Column_Time
															+" = ? "
															+" where "
															+DBKeyword.Column_id
															+" = ? ";
									pstmt = conn.prepareStatement(sql);
									pstmt.setString(1, msg.getContents());
									pstmt.setString(2, DBUtil.getTime());
									pstmt.setInt(3, msg.getId());
									pstmt.executeUpdate();
						}finally {
									DBUtil.close(pstmt);
						}
			}
			public void delete(Connection conn, int msgId) throws SQLException{
						PreparedStatement pstmt = null;
						try{
									String sql = " delete from "
															+DBKeyword.Table_GuestBook
															+" where "
															+DBKeyword.Column_id+" = ? ";
									pstmt = conn.prepareStatement(sql);
									pstmt.setInt(1, msgId);
									pstmt.executeUpdate();
						}finally {
									DBUtil.close(pstmt);
						}
			}
			public MessageDTO makeMessage(ResultSet rs) throws SQLException{
						MessageDTO msg = new MessageDTO();
						
						msg.setId(rs.getInt(DBKeyword.Column_id));
						msg.setUser(rs.getString(DBKeyword.Column_User));
						msg.setContents(rs.getString(DBKeyword.Column_Content));
						msg.setPassword(rs.getString(DBKeyword.Column_Password));
						msg.setTime(rs.getString(DBKeyword.Column_Time));
						return msg;
			}
}