package cn.zhengsigen.test3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Register
 */
@WebServlet("/uploading")
@MultipartConfig
public class Uploading extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/interface/uploading.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String answer = null;

		String name = request.getParameter("name");
		Part headerImg = request.getPart("headerImg");

		if (name == null || name.trim().length() == 0) {
			answer = "请输入文件名称";
			request.setAttribute("answer", answer);
			request.getRequestDispatcher("/interface/register.jsp").forward(request, response);
			return;
		}

		if (headerImg == null || headerImg.getSize() == 0) {
			answer = "请选择上传文件";
			request.setAttribute("answer", answer);
			request.getRequestDispatcher("/interface/register.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("headerImg",headerImg);
		save(request, headerImg);
		request.getRequestDispatcher("/list").forward(request, response);

	}

	private String save(HttpServletRequest req, Part part) throws IOException {
		InputStream is = part.getInputStream();
		String uplaodsDir = req.getServletContext().getRealPath("/uploads/imgs");
		String contentDisposition = part.getHeader("content-disposition");
		String fileName = contentDisposition.split("; ")[2].split("=")[1].replaceAll("\"", "");
		File file = new File(uplaodsDir, UUID.randomUUID().toString() + "-" + fileName);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		String path = "/uploads/imgs/" + file.getName();
		FileOutputStream fos = new FileOutputStream(file);
		byte[] bytes = new byte[1024 * 1024]; // 1M
		int length = -1;
		while ((length = is.read(bytes)) != -1) {
			fos.write(bytes, 0, length);
		}
		fos.close();
		return path;
	}
}
