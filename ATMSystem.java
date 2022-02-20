package com.wilson.atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    private static ArrayList<Account> accounts = new ArrayList();

    public static void showMainMenu() {
        Scanner sc = new Scanner(System.in);
        int select;
        boolean controller = true;

        do {
            System.out.println("============== Welcome to System =================");
            System.out.println("1. LogIn");
            System.out.println("2. Sign Up");
            System.out.println("0. Exit");
            System.out.println("Please Select: ");
            select = sc.nextInt();
            switch (select) {
                case 1:
                    if (accounts.isEmpty()) {
                        System.out.println("No user");
                        break;
                    }
                    logIn(accounts, sc);
                    break;
                case 2:
                    signup(accounts, sc);
                    break;
                case 0:
                    controller = false;
                    break;
                default:
                    System.out.println("Try Again!");
                    break;
            }
        } while (controller);
    }

    public static void signup(ArrayList<Account> accounts, Scanner sc) {
        if (accounts == null || sc == null) {
            return;
        }

        Account newUser = new Account();
        String password;
        String okPassword;
        String newID;

        System.out.println("=================== Welcome to System =======================");
        System.out.println("Please input your first name: ");
        newUser.setUserFirstNameName(sc.next());
        System.out.println("Please input your last name: ");
        newUser.setUserLastNameName(sc.next());

        while (true) {
            System.out.println("Please input your password: ");
            password = sc.next();
            System.out.println("Please input your password: ");
            okPassword = sc.next();
            if (password.equals(okPassword)) {
                newUser.setPassword(password);
                break;
            } else {
                System.out.println("Please input same password! Try again");
            }
        }

        System.out.println("Please set the max withdraw: ");
        newUser.setMaxWithdraw(sc.nextInt());

        boolean check = false;
        do {
            newID = creatUserID();
            for (int i = 0; i < accounts.size(); i++) {
                if (newID.equals(accounts.get(i).getUserID())) {
                    check = true;
                    break;
                }
            }
        } while (check);
        newUser.setUserID(newID);

        accounts.add(newUser);

        System.out.println("Succeeded");
        showUserInfo(newUser);
    }

    public static String creatUserID() {
        String userID = "";
        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            userID += r.nextInt(10);
        }

        return userID;
    }

    public static void showUserInfo(Account user) {
        if (user == null) {
            return;
        }

        System.out.println("Name: " + user.getUserFirstNameName() + " " + user.getUserLastNameName());
        System.out.println("ID: " + user.getUserID());
        System.out.println("MaxWithdraw: " + user.getMaxWithdraw());
        System.out.println("Balances: " + user.getBalances());
    }

    public static void logIn(ArrayList<Account> accounts, Scanner sc) {
        Account user;
        String ID;
        String password;
        while (true) {
            System.out.println("=================== Welcome to System =======================");
            System.out.println("Please input your ID: ");
            ID = sc.next();
            System.out.println("Please input your password: ");
            password = sc.next();
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUserID().equals(ID) && accounts.get(i).getPassword().equals(password)) {
                    user = accounts.get(i);
                    showUserMenu(user, sc);
                    return;
                }
            }
            System.out.println("Incorrect ID or password, please try again");
        }
    }

    public static void showUserMenu(Account user, Scanner sc) {
        if (user == null && sc == null) {
            return;
        }

        boolean controller = true;
        do {
            System.out.println("===================== Welcome to System =========================");
            System.out.println("1. Save");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Details");
            System.out.println("4. Transfer");
            System.out.println("5. Change Password");
            System.out.println("6. Cancel Account");
            System.out.println("0. LogOut");
            System.out.println("Please Select: ");
            switch (sc.nextInt()) {
                case 1:
                    save(user, sc);
                    break;
                case 2:
                    withdraw(user, sc);
                    break;
                case 3:
                    showUserInfo(user);
                    break;
                case 4:
                    Account userTo = userChecker(accounts, sc);
                    if (userTo != null) {
                        transfer(user, userTo, sc);
                    }
                    break;
                case 5:
                    changePassword(user, sc);
                    break;
                case 6:
                    cancelAccount(user);
                    return;
                case 0:
                    controller = false;
                    break;
                default:
                    System.out.println("Try Again");
                    break;
            }
        } while (controller);
    }

    public static void cancelAccount(Account user) {
        if (user == null) {
            return;
        }

        if (accounts.remove(user)) {
            System.out.println("Canceled");
        } else {
            System.out.println("Failed to Cancel");
        }
    }

    public static void changePassword(Account user, Scanner sc) {
        if (user == null) {
            return;
        }

        String oldPassword;
        String newPassword;
        while (true) {
            System.out.println("Please input your password: ");
            oldPassword = sc.next();
            if (oldPassword.equals(user.getPassword())) {
                System.out.println("Please input new password: ");
                newPassword = sc.next();
                System.out.println("Please confirm new password: ");
                if (newPassword.equals(sc.next())) {
                    user.setPassword(newPassword);
                    System.out.println("Password has been changed");
                    return;
                } else {
                    System.out.println("Please input same password!");
                }
            } else {
                System.out.println("Incorrect password, please try again");
            }
        }
    }

    public static void save(Account user, Scanner sc) {
        if (user == null && sc == null) {
            return;
        }

        System.out.println("How much you save: ");
        int newBalance = user.getBalances() + sc.nextInt();
        user.setBalances(newBalance);

        System.out.println("Saved, your balances: " + newBalance);
    }

    public static void withdraw(Account user, Scanner sc) {
        if (user == null && sc == null) {
            return;
        }

        while (true) {
            System.out.println("How much you withdraw: ");
            int money = sc.nextInt();
            if (money > user.getBalances()) {
                System.out.println("You don't have enough money!");
            } else if (money > user.getMaxWithdraw()) {
                System.out.println("The amount is over the MaxWithdraw!");
            } else {
                int newBalances = user.getBalances() - money;
                user.setBalances(newBalances);
                System.out.println("Saved, your balances: " + newBalances);
                System.out.println("Please take your money, Having a good day!");
                return;
            }
        }
    }

    public static Account userChecker(ArrayList<Account> accounts, Scanner sc) {
        if (accounts.size() == 1) {
            System.out.println("Don't have other users");
            return null;
        }

        System.out.println("Please input ID of payee: ");
        String payeeID = sc.next();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserID().equals(payeeID)) {
                while (true) {
                    System.out.println("Payee's first name is " + accounts.get(i).getUserFirstNameName());
                    System.out.println("Please input payee's last name: ");
                    if (sc.next().equals(accounts.get(i).getUserLastNameName())) {
                        return accounts.get(i);
                    } else {
                        System.out.println("Incorrect last name! Try again");
                    }
                }
            }
        }
        System.out.println("Can't find this ID!");
        return null;
    }

    public static void transfer(Account userF, Account userT, Scanner sc) {
        if (userF == null || userT == null || sc == null) {
            return;
        }

        while (true) {
            System.out.println("How much you transfer: ");
            int money = sc.nextInt();
            if (money > userF.getBalances()) {
                System.out.println("You don't have enough money!");
            } else if (money > userF.getMaxWithdraw()) {
                System.out.println("The amount is over the MaxWithdraw!");
            } else {
                for (int i = 2;i>=0;i--) {
                    System.out.println("Please input your password: ");
                    if (userF.getPassword().equals(sc.next())) {
                        int newBalances = userF.getBalances() - money;
                        userF.setBalances(newBalances);
                        newBalances = userT.getBalances() + money;
                        userT.setBalances(newBalances);
                        System.out.println("Transferred, your balances: " + newBalances);
                        return;
                    }else{
                        System.out.println("Incorrect password, you have "+i+" more chances");
                    }
                }
                return;
            }
        }
    }
}

