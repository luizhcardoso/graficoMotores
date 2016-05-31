package bancoDeDados;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javax.persistence.EntityManagerFactory;

public class DaoGrafico {

	// Create an EntityManagerFactory when you start the application.
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("grafico");
	
	


	public void escrevePontoLeitura(Grafico ponto) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {

			// Get a transaction
			transaction = manager.getTransaction();

			// Begin the transaction
			transaction.begin();
			manager.persist(ponto);
			System.out.println("gravou");
			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
	}

	/**
	 * Read all the Students.
	 * 
	 * @return a List of Students
	 */
	public List<Grafico> readAll() {

		List<Grafico> Grafico = null;

		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
//		EntityTransaction transaction = null;

		try {
			// Get a transaction
//			transaction = manager.getTransaction();
			// Begin the transaction
//			transaction.begin();

			// Get a List of Students
			 Query query=manager.createQuery("select c FROM Grafico c");
			 Grafico =query.getResultList();
			// Commit the transaction
//			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
//			if (transaction != null) {
//				transaction.rollback();
//			}
			// Print the Exception
//			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
		return Grafico;
	}
	
	public List<Grafico> retornaIntervaloDeData(String inicial,String fim) {

		List<Grafico> Grafico = null;

		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); //voc� pode usar outras m�scaras
			Date dataInicial=sdf1.parse(inicial);
			Date dataFinal=sdf1.parse(fim);
			transaction = manager.getTransaction();
			transaction.begin();
			
			Query query= manager.createQuery("FROM pontoDeLeitura AS c WHERE c.data BETWEEN :stDate AND :edDate ");
			query.setParameter("stDate", dataInicial);
			query.setParameter("edDate", dataFinal);
			Grafico=query.getResultList();
					
			} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
		return Grafico;
	}

	@SuppressWarnings("unchecked")
	public Grafico lerUltimaLinha() {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		try{
		Query query = manager.createQuery("from Grafico where codigo=(select max(codigo) from Grafico )");
		query.setMaxResults(1);
		Grafico product = (Grafico)query.getSingleResult();
		return product;
		 } catch (Exception ex) {
				// If there are any exceptions, roll back the changes
				if (transaction != null) {
					transaction.rollback();
				}
				// Print the Exception
				ex.printStackTrace();
			} finally {
				// Close the EntityManager
				manager.close();
			}
		return null;
	}
	
	public List<Grafico> retornaPaginacao(int paginacao) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		Query query = manager.createQuery("select c FROM Grafico c");
		query.setFirstResult(lerUltimaLinha().getCodigo()-paginacao);
		query.setMaxResults(paginacao);
		List<Grafico> cats = query.getResultList();
		return cats;
		      }

	/**
	 * Delete the existing Student.
	 * 
	 * @param id
	 */
	public void delete(int codigo) {
		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Get the Student object
			Grafico Grafico = manager.find(Grafico.class, codigo);

			// Delete the student
			manager.remove(Grafico);

			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
	}

	/**
	 * Update the existing Student.
	 * 
	 * @param id
	 * @param name
	 * @param age
	 */
	public void upate(int codigo, Date date, 
			boolean pressaoSkidA, boolean pressaoSkidB, boolean transferenciaSkindA, 
			boolean transferenciaSkidB, boolean vacuo, boolean portaUsina) { {
				// Create an EntityManager
				EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
				EntityTransaction transaction = null;
				
				try {
					// Get a transaction
					transaction = manager.getTransaction();
					// Begin the transaction
					transaction.begin();

					// Get the Student object
					Grafico pt = manager.find(Grafico.class, codigo);

					// Change the values

//					pt.setPortaUsina(portaUsina);
//					pt.setMotorVacuo(vacuo);
//					pt.setMotorPressaoSkidA(pressaoSkidA);
//					pt.setMotorPressaoSkidB(pressaoSkidB);
//					pt.setMotorTransferenciaSkidA(transferenciaSkindA);
//					pt.setMotorTransferenciaSkidB(transferenciaSkidB);
//					pt.setData(date);

					// Update the student
					manager.persist(pt);

					// Commit the transaction
					transaction.commit();
				} catch (Exception ex) {
					// If there are any exceptions, roll back the changes
					if (transaction != null) {
						transaction.rollback();
					}
					// Print the Exception
					ex.printStackTrace();
				} finally {
					// Close the EntityManager
					manager.close();
				}
			}
	}
	public String imprimeTodoBanco(){
		String imprime="====================================================\n";
		List<Grafico> lista=readAll();
		for(Grafico i : lista){
//			imprime+=
//					"Codigo ponto de Leitura:" +  i.getCodigo() +"\n"+
//							"PortaUsina  " +  i.isPortaUsina()+"\n"+
//							"MotorVacuo  " +  i.isMotorVacuo()+"\n"+
//							"MotorPressaoSkidA  " +  i.isMotorPressaoSkidA()+"\n"+
//							"MotorPressaoSkidB  " +  i.isMotorPressaoSkidB()+"\n"+
//							"MotorTransferenciaSkidA  " +  i.isMotorTransferenciaSkidA()+"\n"+
//							"MotorTransferenciaSkidB  " +  i.isMotorTransferenciaSkidB()+"\n"+
//							"Data  " +  i.getData()+"\n"
//							+ "___________________________________________________________\n";
		}

		return imprime;
	}
	
	}



