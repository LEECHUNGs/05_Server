package edu.kh.playlist.song.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.playlist.song.dto.Song;
import edu.kh.playlist.song.model.dao.SongListDAO;
import static edu.kh.todoList.common.JDBCTemplate.*;

public class SongListService {
	
	private SongListDAO dao = new SongListDAO();

	/** 노래 목록 요청 서비스
	 * @return songList
	 */
	public List<Song> songList() throws Exception{
		Connection conn = getConnection();
		
		List<Song> songList = dao.songList(conn);
		
		close(conn);
		
		return songList;
	}

	/** 노래 추가 서비스
	 * @param songTitle
	 * @param songArtist
	 * @param songAlbum
	 * @return
	 */
	public int songAdd(String songTitle, String songArtist, String songAlbum) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.songAdd(conn, songTitle, songArtist, songAlbum);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
}
