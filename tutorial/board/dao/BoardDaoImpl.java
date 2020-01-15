package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import board.vo.BoardVO;

public class BoardDaoImpl implements BoardDao{
	
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {
		
	}
	
	public static BoardDaoImpl getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	@Override
	public Object insertBoard(BoardVO bv) {
		Object obj = null;
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
			
			BoardVO bvo = new BoardVO();
			bvo.setBoard_title(bv.getBoard_title());
			bvo.setBoard_writer(bv.getBoard_writer());
			bvo.setBoard_content(bv.getBoard_content());
			
			obj = smc.insert("board.insertboard", bvo);
			
		
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@Override
	public int updateBoard(BoardVO bv) {
		int chk = 0;
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
			
			BoardVO bvo = new BoardVO();
			
			bvo.setBoard_no(bv.getBoard_no());
			bvo.setBoard_title(bv.getBoard_title());
			bvo.setBoard_writer(bv.getBoard_writer());
			bvo.setBoard_content(bv.getBoard_content());
			
			
			chk = smc.update("board.updateboard",bvo);
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}
	@Override
	public int deleteBoard(BoardVO bv) {
		int chk = 0;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
			
			BoardVO bvo = new BoardVO();
			bvo.setBoard_no(bv.getBoard_no());
			
			chk = smc.delete("board.deleteboard",bvo);
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}
	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> bolist = null;
		
		try {
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		
			
		bolist = smc.queryForList("board.getboardall");
		
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bolist;
	}
		
	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
			List<BoardVO> bolist = null;
		
		try {
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			bolist = smc.queryForList("board.getserch",bv.getBoard_no());

		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return bolist;
	}

}
