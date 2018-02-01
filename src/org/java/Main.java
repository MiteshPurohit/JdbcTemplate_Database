/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author f1cmpica-1
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            
            ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            circleDAOimpl c = (circleDAOimpl) context.getBean("circleDAO");

            Circle cir = context.getBean("cir", Circle.class);

            while (true) {
                System.out.println("------------- Menu --------------");
                System.out.println("---------------------------------");
                System.out.println("1.) CREATE circle TABLE.....");
                System.out.println("2.) INSERT record.....");
                System.out.println("3.) DISPLAY TOTAL COUNT.....");
                System.out.println("4.) DISPLAY NAME for ID.....");
                System.out.println("5.) DISPLAY ALL for ID.....");
                System.out.println("6.) DISPLAY ALL.....");
                System.out.println("7.) EXIT.....");
                System.out.println("---------------------------------");
                System.out.print("Enter your Choice .:");
                int ch = Integer.parseInt(br.readLine());
                switch (ch) {
                    case 1:
                        c.createCircle();
                        System.out.println("--------------------------------");
                        System.out.println("....Table Created....");
                        break;
                    case 2:
                        System.out.print("Enter Circle ID :");
                        int id = Integer.parseInt(br.readLine());

                        System.out.print("Enter Circle NAME :");
                        String nm = br.readLine();

                        cir.setC_id(id);
                        cir.setC_name(nm);

                        c.insertCircle();
                        System.out.println("--------------------------------");
                        System.out.println(".....Record Inserted.....");
                        break;
                    case 3:
                        System.out.println("--------------------------------");
                        System.out.println("Total Record(s) .:" + c.getCircleCount());
                        System.out.println("--------------------------------");
                        break;

                    case 4:
                        System.out.print("Enter Circle ID :");
                        int id1 = Integer.parseInt(br.readLine());

                        System.out.println("--------------------------------");
                        System.out.println("Name is .:" + c.getCircleForID(id1).getC_name());
                        System.out.println("--------------------------------");
                        break;

                    case 5:
                        System.out.print("Enter Circle ID :");
                        int id2 = Integer.parseInt(br.readLine());

                        System.out.println("--------------------------------");
                        System.out.println("ID is   .:" + c.getCircleForID(id2).getC_id());
                        System.out.println("Name is .:" + c.getCircleForID(id2).getC_name());
                        System.out.println("--------------------------------");
                        break;

                    case 6:
                        System.out.println("--------------------------------");
                        System.out.println("ID\tNAME");
                        System.out.println("--------------------------------");

                        for (Circle c2 : c.getALL()) {

                            System.out.print(c2.getC_id());
                            System.out.println("\t" + c2.getC_name());
                        }
                        System.out.println("--------------------------------");
                        break;

                    case 7:
                        exit(0);

                    default:
                        System.out.println("......Invalid Choice......");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
