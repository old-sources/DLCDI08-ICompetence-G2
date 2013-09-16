package org.imie.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.GroupeDeTravailDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.interfaces.IGroupeDeTravailService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class GroupServletClass
 */
@WebServlet("/GroupServletClass")
public class GroupServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (request.getParameter("numLigne") != null) {
			String ligne = request.getParameter("numLigne");
			System.out.println("entré dans le if de load   "+ ligne );
			List<GroupeDeTravailDTO> listgdt = (List<GroupeDeTravailDTO>) session
					.getAttribute("listgdt");
			Integer numLigne = Integer.valueOf(ligne);
			GroupeDeTravailDTO chosengdt = listgdt.get(numLigne - 1);
			request.setAttribute("chosengdt", chosengdt);
			
			request.getRequestDispatcher("./DetailGroupeDeTravail.jsp")
			.forward(request, response);
		} else {

			IGroupeDeTravailService gdtService = BaseConcreteFactory
					.getInstance().creerGroupeDeTravailService(null);
			List<GroupeDeTravailDTO> gdtlist = null;
			try {

				gdtlist = gdtService.afficherGroupeDeTravail();
				session.setAttribute("listgdt", gdtlist);
			} catch (TransactionalConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String urlParam = request.getParameter("UrlParam");

			
			if (urlParam != null) {

				if (urlParam.equals("supr")) {
					GroupeDeTravailDTO currentgdt = null;
					currentgdt = gdtlist.get(Integer.valueOf(request
							.getParameter("chosengdt")) - 1);
					

					try {

						gdtService.supprimerGroupeDeTravail(currentgdt);
						
					} catch (TransactionalConnectionException e) {
						ExceptionManager.getInstance().manageException(e);
					}
					response.sendRedirect("./AccueilServletClass");

				}
			} else {

				request.setAttribute("UrlParam", null);
				request.setAttribute("listgdt", gdtlist);
				session.setAttribute("listgdt", gdtlist);
				

				request.getRequestDispatcher("./ListeGroupeDeTravail.jsp")
						.forward(request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IGroupeDeTravailService gdtService = BaseConcreteFactory.getInstance()
				.creerGroupeDeTravailService(null);

		List<GroupeDeTravailDTO> gdtlist = null;

		String urlParam = null;
		urlParam = request.getParameter("UrlParam");

		if (urlParam.equals("creer")) {
			GroupeDeTravailDTO gdtDTOcreer = new GroupeDeTravailDTO();
			String nomParam = request.getParameter("nom");
			String typeParam = request.getParameter("type");
			gdtDTOcreer.setType_projet(typeParam);
			gdtDTOcreer.setNom(nomParam);
			try {
				System.out.println("creation du groupe de travail");
				gdtService.creerGroupeDeTravail(gdtDTOcreer);
			} catch (TransactionalConnectionException e) {
				System.out.println("echec de la creation du groupe de travail");
				e.printStackTrace();
			}
			response.sendRedirect("./GroupServletClass");
		}

		if (urlParam.equals("listUser")) {
			try {
				gdtlist = gdtService.afficherGroupeDeTravail();
				GroupeDeTravailDTO currentgdt = null;
				currentgdt = gdtlist.get(Integer.parseInt(request
						.getParameter("gdt")));
				List<UserDTO> listUserDTO = gdtService
						.utilisateurParGroupeDeTravail(currentgdt);
			} catch (TransactionalConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			response.sendRedirect("./GroupServletClass");

		}

		if (urlParam.equals("modif")) {
			GroupeDeTravailDTO gdtDTOmodif = new GroupeDeTravailDTO();

			String idGdtParam = request.getParameter("gdtid");
			if (idGdtParam != null) {
				Integer idGdt = Integer.valueOf(idGdtParam);
				gdtDTOmodif.setId_gdt(idGdt);

				try {
					System.out.println("modification du groupe de travail");
					gdtService.modifierGroupeDeTravail(gdtDTOmodif);
				} catch (TransactionalConnectionException e) {
					System.out
							.println("echec de la modif du groupe de travail");
					e.printStackTrace();
				}
			}
			response.sendRedirect("./listeGroupeDeTravail.jsp");
		}

	}

}
