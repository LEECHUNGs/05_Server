package edu.kh.playlist.song.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.playlist.member.model.dao.MemberDAO;
import edu.kh.playlist.song.dto.Song;
import static edu.kh.todoList.common.JDBCTemplate.*;

public class SongListDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public SongListDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class
								.getResource("/edu/kh/playlist/sql/song-sql.xml")
								.getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 노래목록 조회 SQL 수행 DAO
	 * @param conn
	 * @return
	 */
	public List<Song> songList(Connection conn) throws Exception{
		List<Song> songList = new ArrayList<Song>();
		
		try {
			String sql = prop.getProperty("songList");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Song song = new Song();
				
				song.setSongNo(rs.getInt(1));
				song.setSongTitle(rs.getString(2));
				song.setSongArtist(rs.getString(3));
				song.setSongAlbum(rs.getString(4));
				song.setSongLike(rs.getInt(5));
				
				songList.add(song);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return songList;
	}

	/** 노래 추가 SQL 수행 DAO
	 * @param conn
	 * @param songTitle
	 * @param songArtist
	 * @param songAlbum
	 * @return result
	 */
	public int songAdd(Connection conn, String songTitle, String songArtist, String songAlbum) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("songAdd");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, songTitle);
			pstmt.setString(2, songArtist);
			pstmt.setString(3, songAlbum);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
