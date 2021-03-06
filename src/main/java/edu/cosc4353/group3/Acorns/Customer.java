package edu.cosc4353.group3.Acorns;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {
	private String user;
	private String passwd;
	private int card;
	private char[] ports;

	public Customer(String username, String password, int cardNumber) {
		user = username;
		passwd = password;
		card = cardNumber;
		ports = new char[4];
	}
	
	public Customer() {
		passwd = "";
		card = 0;
		ports = new char[4];
	}

	public String returnUsername() {
		return user;
	}
	public int returnCardNumber() {
		return card;
	}
	
	public static int hashUsername(String username) {
		int total = 0;
		
		for(int i = 0; i < username.length(); i++) {
			total += (int)username.charAt(i);
		}
		return((total)%5);
	}
	// Username hash to store and identify object
	public void storeUser() {
		
	}
	// TODO Pull user object if pass match
	
	
	//New Code for user create/store
	
        
        File file = new File("");
        public static final int PASS_LENGTH = 6;
	
	public static int getCardNumberI(Customer c) {
            int temp= c.card;
            return temp;
	}
        public String getUser_Name(){
            return user;
        }
        public String getUser_Pass(){
            return passwd;
        }
        public int getUser_CardNumb(){
            return card;
        }
        public static void setCardNumber(Customer c ,int number){
            c.card = number;
        }
        //Create New User Account
        public static boolean CreateAccount()
        {
            System.out.println("User Creation! ");
            Scanner input = new Scanner(System.in);
            String username = "";
            String password = "";
            String cardId = "";
            
            boolean CreateError = true;
            while (CreateError)
            {
            System.out.println("Enter A Username: ");
             username = input.next();
            
            if (isNotUnique(username)){   System.out.println("Username Taken, Try Again...." + "\n"); }
            else { CreateError = false; System.out.println("Username Accepted!" + "\n"); }
            }
            CreateError = true;
            while (CreateError)
            {
                System.out.println("Enter A " + PASS_LENGTH + " Character password: ");
                password = input.next();
                
                if (password.length() != PASS_LENGTH){ CreateError = true; System.out.println("Password Length Error, Try Again...." + "\n");}
                else {  CreateError = false; System.out.println("Password Accepted, Account Created!" + "\n");}
            }
            CreateError = true;
            while(CreateError)
            {
                System.out.println("Enter a 16 digit Debit Card number: ");
                cardId = input.next();
                
                if (cardId.length() < 16) { CreateError = true; System.out.println("Card Number Error.....");   }
                else {  CreateError = false;    }
            }
            //Enter Card Info Here
                StoreUserData(username, password,cardId); //Add card Argument
            return true;
        }
        //Store Accepted User Login Data in .txt
        public static void StoreUserData(String user, String pass, String cardNumb)
        {
            String Storage = user + " " + pass + " " + cardNumb + " " + "unlocked";
            System.out.println(Storage);
            
            try
            {
                String filename = "LoginInfo.txt";
                BufferedWriter  outP;
                outP = new BufferedWriter(new FileWriter(filename, true));
                
                outP.newLine(); 
                outP.append(Storage);
                outP.close();
            }
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
        //Check Originality of Username
        public static boolean isNotUnique(String UserInput)
        {
            File file = new File("LoginInfo.txt");
	try 
        {    
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) 
            {
                String TempScan = sc.next();
                //System.out.println(TempScan);
                if (UserInput.equals(TempScan))// write first word from line
                {
                    return true;
                }
                //System.out.println("next");
                else{   sc.nextLine();  }
            }
        } 
        catch (IOException e) { e.printStackTrace();    }
            
        return false;
    }
        //User Login Function
        public static void Login()
        {
            Scanner input = new Scanner(System.in);
            int tempCard =0;
            boolean InvalidCheck = false;
            while (!InvalidCheck)
            {
            System.out.println("Enter User: ");
            String username = input.next();
		
            System.out.println("Enter Password: ");
            String password = input.next();
                if(CheckLogin(username, password))
                {
                    InvalidCheck = true; //User Sucessful login
                    Customer CurrentCustomer = new Customer(username, password, 9999);
                    tempCard = getCardNumberI(CurrentCustomer);
                    CurrentCustomer.card = tempCard;
                    //Interface intfc = new Interface();
                    //intfc.UserInput(CurrentCustomer);
                }
            }
        }
        
        //Check User Existance in .txt
        public static boolean CheckLogin(String user, String pass) //Check if User login exists and is correct
        {
            File file = new File("LoginInfo.txt");
            try 
        {    
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) 
            {
                String TempScan = sc.nextLine();
                String[] passString = TempScan.split(" ");
               

                if (user.equals( passString[0]) &&  pass.equals(passString[1]))
                {
                    System.out.println("Login Success!" + "\n");
                    return true;
                }
                else if (user.equals(passString[0]) && !pass.equals(passString[1]))
                {
                    System.out.println("Password Error..." + "\n");
                    return false;
                }
            }
            System.out.println("User Does Not Exist.....");
            return false;
        } 
        catch (IOException e) { e.printStackTrace();    }
            return false;
        }

}
