package Principal;

import java.util.*;
import Dijkstra.*;

public class Menu {

// ATTRIBUTES
  
  ArrayList<Airport> airportsList;
  String originCode, destinyCode;
  HashSet<String> statesList;
  HashSet<String> citiesList;


// METHODS
  // Constructor
  public Menu(ArrayList<Airport> apList) {
    airportsList = new ArrayList<Airport>(apList);
    statesList = new HashSet<>();
    citiesList = new HashSet<>();
  }
  
  // Starts the menu for the user
  public void showMenu() {        
    System.out.println("--------- ROTAS ÁREAS ---------\n");
    selectDestiny();
    selectOrigin();
    printShortestPath();
    }
    
  // Select the destiny Airport's code
  private void selectDestiny() {
    Scanner obj = new Scanner(System.in);
    
    System.out.println("Seleção do Destino:");
    System.out.println("1. Inserir código do Aeroporto de destino");
    System.out.println("2. Listar códigos dos Aeroportos");
    System.out.print("Escolha uma opção: ");

    try {
      Integer option = obj.nextInt();
      switch(option) {
        case 1:
          destinyCode = insertApCode();
          if(destinyCode == null)
            selectDestiny();
          break;
        case 2:
          listApCodes();
          selectDestiny();
          break;
        default:
          System.out.println("\nOpção inválida!\n");
          showMenu();
      }
    }
    catch(Exception e) {
      System.out.println("Opção inválida!\n");
      selectDestiny();
    }
    
  }

  // Select the origin Airport's code
  private void selectOrigin() {
    Scanner obj = new Scanner(System.in);

    System.out.println("Seleção da Origem:");
    System.out.println("1. Inserir código do Aeroporto de origem");
    System.out.println("2. Listar códigos dos Aeroportos");
    System.out.print("Escolha uma opção: ");

    try {
      Integer option = obj.nextInt();
      switch(option) {
        case 1:
          originCode = insertApCode();
          if(originCode == null)
            selectOrigin();
          break;
        case 2:
          listApCodes();
          selectOrigin();
          break;
        default:
          System.out.println("\nOpção inválida!\n");
          showMenu();
      }
    }
    catch(Exception e) {
      System.out.println("Opção inválida!\n");
      selectOrigin();
    }
  }
  
  // Print the result obtained through the Dijkstra algorithm
  private void printShortestPath() {
    Graph airportsGraph =new Graph(airportsList);
    Node destinyNode = DijkstraAlgorithm.calculateShortestPathFromSource(airportsGraph, originCode, destinyCode);
    Scanner sc = new Scanner(System.in);
        
        System.out.printf("\nTrecho %s -> %s\n", originCode, destinyCode);
        System.out.println("---------------------");
        System.out.print("| ");

        for(Node apNode: destinyNode.getShortestPath()) {
            System.out.print(apNode.getAirport().getCode());
            System.out.print(" -> ");
        }
        System.out.println(destinyCode);
        System.out.printf("| %.2f Km\n\n",destinyNode.getDistance());

        System.out.println("Deseja fazer outra consulta?\n1. Sim\n2. Não");
        System.out.print("Escolha uma opção: ");
        try {
          Integer option = sc.nextInt();
          if(option == 1)
            showMenu();
        }
        catch(Exception e) {
          System.out.println("\nOpção inválida!\n");
        }
  }

  // Handle the insertion of a Airport code by the user
  private String insertApCode() {
    Scanner obj = new Scanner(System.in);
    
    System.out.print("Insira o código do Aeroporto: ");
    try {
      String apCode = obj.nextLine();
      if(apCode.length() != 3) {
        System.out.println("O código deve ser composto por 3 letras maiúsculas!");
        return null;
      }
      if(!verifyCode(apCode)) {
        System.out.println("\nNão existe aeroporto internacional brasileiro com esse código!\n");
        return null;
      }
      System.out.println();
      return apCode;
    }
    catch(Exception e) {
      System.out.println("Código inválido!\n" + e);
      return null;
    }
  }

  // List the Airport codes from the city chosen by the user
  private void listApCodes() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n------ ESTADOS ------");
    for(Airport ap: airportsList) {
      String apState = ap.getState();
      if(!statesList.contains(apState))
      statesList.add(apState);
      System.out.println(apState);
    }
    System.out.print("\nEscolha um dos estados acima: ");
    
    try {
      String stateSelected = sc.nextLine();
      if(!verifyState(stateSelected))
      listApCodes();
      
      System.out.println("\n------ MUNICÍPIOS ------");
      for(Airport ap: airportsList) {
        String apCity = ap.getCity();
        if(!citiesList.contains(apCity) && stateSelected.equals(ap.getState())) {
          citiesList.add(apCity);
          System.out.println(apCity);
        }
      }
      System.out.print("\nEscolha um dos estados acima: ");
      
      try {
        String citySelected = sc.nextLine();
        if(!verifyCity(citySelected))
        listApCodes();
        
        System.out.println("\n------ AEROPORTOS ------");
        for(Airport ap: airportsList) {
          if(citySelected.equals(ap.getCity())) {
            System.out.println(ap.getCode() + "\n");
          }
        }
      }
      catch(Exception e) {
        System.out.println("Município inválido!\n");
        listApCodes();
      }
    }
    catch(Exception e) {
      System.out.println("Estado inválido!\n");
      listApCodes();
    }
    
  }
  
  // Verify if a Airport Code exists
  private boolean verifyCode(String apCode) {
    for(Airport ap: airportsList) {
      if(apCode.equals(ap.getCode()))
        return true;
    }
    return false;
  }

  // Verify if a State exists
  private boolean verifyState(String apState) {
    if(!statesList.contains(apState)) {
      System.out.println("Estado inválido!\n");
      return false;
    }
    return true;
  }

  // Verify if a City is within a certain State
  private boolean verifyCity(String apCity) {
    if(!citiesList.contains(apCity)) {
      System.out.println("Município inválido!\n");
      return false;
    }
    return true;
  }
  
}
