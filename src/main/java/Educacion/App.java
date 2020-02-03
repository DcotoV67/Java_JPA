package Educacion;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("damPersistence");
        EntityManager em = emf.createEntityManager();

        Boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("Elige que quieres hacer:");

            System.out.println("   1- Añadir Alumno\n" +
                    "   2- Añadir Clase\n" +
                    "   3- Añadir Instituto\n" +
                    "   4- Comprobar número alumnos\n" +
                    "   5- Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                     try {
                    String nombre;
                    int dni, claseid;



                            TypedQuery<Alumno> query1 = em.createQuery("SELECT i FROM Alumno i", Alumno.class);
                            List<Alumno> results1 = query1.getResultList();
                            results1.forEach(System.out::println);

                            TypedQuery<Clase> query2 = em.createQuery("SELECT i FROM Clase i", Clase.class);
                            List<Clase> results2 = query2.getResultList();


                            System.out.println("Clases disponibles: ");
                            results2.forEach(System.out::println);

                            System.out.println("\nAlumnos disponibles: ");
                            results1.forEach(System.out::println);




                    System.out.println("\nIntroduce el NOMBRE del alumno:");
                    nombre = scanner.nextLine();

                    System.out.println("\nIntroduce el DNI del alumno:");
                    dni = scanner.nextInt();

                    System.out.println("\nIntroduce el ID DE LA CLASE del alumno:");
                    claseid = scanner.nextInt();

                    //Crear Alumno
                    Alumno alumno1 = new Alumno();
                    alumno1.setDni(dni);
                    alumno1.setNombre(nombre);
                    alumno1.setClaseId(claseid);

                    em.persist(alumno1);
                    em.getTransaction().begin();
                    em.getTransaction().commit();

                    break;
                     }catch (Exception e){
                         System.out.println(e);
                         break;
                     }

                case 2:

                    try{
                    TypedQuery<Clase> query3 = em.createQuery("SELECT i FROM Clase i", Clase.class);
                    List<Clase> results3 = query3.getResultList();



                    TypedQuery<Instituto> query4 = em.createQuery("SELECT i FROM Instituto i", Instituto.class);
                    List<Instituto> results4 = query4.getResultList();

                    System.out.println("Clases disponibles: ");
                    results3.forEach(System.out::println);

                    System.out.println("Institutos disponibles: ");
                    results4.forEach(System.out::println);


                    int nAlumnosC, idC, institutoIdC;
                    String nombreC, ramaC;

                    System.out.println("\nIntroduce el Número de alumnos: ");
                    nAlumnosC = scanner.nextInt();

                    System.out.println("\n Introduce el ID de la clase: ");
                    idC = scanner.nextInt();

                    System.out.println("\n Introduce el ID del instituto: ");
                    institutoIdC = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\n Introduce el nombre de la clase: ");
                    nombreC = scanner.nextLine();

                    System.out.println("\n Introduce la rama de la clase: ");
                    ramaC = scanner.nextLine();

                    //Crear Clase
                    Clase clase = new Clase();
                    clase.setId(idC);
                    clase.setInstitutoId(institutoIdC);
                    clase.setNAlumnos(nAlumnosC);
                    clase.setNombre(nombreC);
                    clase.setRama(ramaC);

                    em.persist(clase);
                    em.getTransaction().begin();
                    em.getTransaction().commit();

                    break;

                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }

                case 3:

                    try {
                        //Crear Instituto
                        String nombreI;
                        int idI, nalumnosI;


                        System.out.println("Institutos: \n");
                        TypedQuery<Alumno> totalAlumnos = em.createQuery("SELECT i FROM Alumno i", Alumno.class);
                        List<Alumno> alumnosTotalList = totalAlumnos.getResultList();


                        Instituto instituto = new Instituto();
                        System.out.println("\n Introduce el ID del instituto: ");
                        idI = scanner.nextInt();
                        instituto.setId(idI);

                        System.out.println("\n Introduce el numero de alumnos del instituto: ");
                        nalumnosI = scanner.nextInt();
                        instituto.setNAlumnos(nalumnosI);

                        scanner.nextLine();
                        System.out.println("\n Introduce el ID del instituto: ");
                        nombreI = scanner.nextLine();
                        instituto.setNombre(nombreI);

                        em.persist(instituto);
                        em.getTransaction().begin();
                        em.getTransaction().commit();

                        break;

                    }catch (Exception e){
                            System.out.println(e);
                            break;
                        }


                        case 4:

                            try{

                    TypedQuery<Alumno> totalAlumnos = em.createQuery("SELECT i FROM Alumno i", Alumno.class);
                    List<Alumno> alumnosTotalList = totalAlumnos.getResultList();

                    TypedQuery<Clase> totalClases = em.createQuery("SELECT i FROM Clase i", Clase.class);
                    List<Clase> clasesList = totalClases.getResultList();

                    TypedQuery<Instituto> totalInstitutos = em.createQuery("SELECT i FROM Instituto i", Instituto.class);
                    List<Instituto> institutosList = totalInstitutos.getResultList();

                for (Instituto i : institutosList) {

                    System.out.println("Instituto ID: " + i.getId());
                    int cont = 0;

                    for (Clase c : clasesList) {

                        if (c.getInstitutoId() == i.getId()) {

                            System.out.println("Clase ID: " + c.getId());

                            for (Alumno a : alumnosTotalList) {
                                if (a.getClaseId() == c.getId()) {
                                    System.out.println(a);
                                    cont++;
                                }
                            }
                        }

                    }
                    System.out.println("\n--------------------------");
                    System.out.println("Total Alumnos: " + cont);
                    System.out.println("\n--------------------------");
                }

                    System.out.println("Número de alumnos: " + alumnosTotalList.size() + "\n");

                    break;
                            }catch (Exception e){
                                System.out.println(e);
                                break;
                            }
                case 5:
                    System.out.println("Finalizando el programa...");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida, selecciona una opción válida");
                    break;
            }
        }

        em.close();
    }
}