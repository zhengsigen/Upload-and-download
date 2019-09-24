/**
 * 
 */
package cn.zhengsigen.test3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Topin@JSC
 *
 */
@WebServlet("/download")
public class Download extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String header_img = req.getParameter("header_img");
		File file = new File(header_img);
		doDownload(file, header_img, resp);
		
	}
	private void doDownload(File file, String header_img, HttpServletResponse resp) throws IOException {

		String suffixName = header_img.substring(header_img.lastIndexOf("."));
		int index = header_img.lastIndexOf("-");
		int index1 = header_img.lastIndexOf(".");
		String headName = header_img.substring(index + 1, index1);

		resp.setContentType("application/octem-stream");
		resp.setContentLength((int) file.length());
		resp.setHeader("Content-Disposition",
				"attachment; filename=\"" + URLEncoder.encode(headName, "UTF-8") + suffixName);
		ServletOutputStream os = resp.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes = new byte[1024 * 1024 * 4];
		int length = -1;
		while ((length = fis.read(bytes)) != -1) {
			os.write(bytes, 0, length);
		}
		fis.close();
	}
}
