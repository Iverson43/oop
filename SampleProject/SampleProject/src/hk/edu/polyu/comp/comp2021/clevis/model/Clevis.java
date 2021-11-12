package hk.edu.polyu.comp.comp2021.clevis.model;
import java.util.*;
public class Clevis {

    ArrayList<Shape> shapeList;

    int count ;

    public Clevis(){
        shapeList = new ArrayList<Shape>();
        count = 0;
    }
    public int match(ArrayList<Shape> x, String y){
        for (int i = 0 ; i < count ; i++){
            if (x.get(i).getName().equals(y)) return i;
        }
        return -1;
    }
    public void UI(){
        System.out.println("-------------------\n");
    }
    public void add(String x){
        switch (x){
            case "Rectangle()":
                System.out.println("Please input your " + x + "'s name");
                Scanner sc = new Scanner(System.in);
                String name = sc.nextLine();
                System.out.println( "Please input " + name + "'s top left x coordinate");
                double xcoord = sc.nextDouble();
                System.out.println( "Please input " + name + "'s top left y coordinate");
                double ycoord = sc.nextDouble();
                System.out.println( "Please input " + name + "'s width");
                double width = sc.nextDouble();
                System.out.println( "Please input " + name + "'s hight");
                double hight = sc.nextDouble();
                UI();
                shapeList.add(new Rectangle(name,xcoord,ycoord,width,hight));
                count++;
                break;
            case "Line()":
                System.out.println("Please input your " + x + "'s name");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                System.out.println( "Please input " + name + "'s starting x coordinate");
                double startx = sc.nextDouble();
                System.out.println( "Please input " + name + "'s starting y coordinate");
                double starty = sc.nextDouble();
                System.out.println( "Please input " + name + "'s ending x coordinate");
                double endx = sc.nextDouble();
                System.out.println( "Please input " + name + "'s endting y coordinate");
                double endy = sc.nextDouble();
                UI();
                shapeList.add(new Line(name,startx,starty,endx,endy)) ;
                count++;
                UI();
                break;
            case "Circle()":
                System.out.println("Please input your " + x + "'s name");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                System.out.println( "Please input " + name + "'s center x coordinate");
                double centerx = sc.nextDouble();
                System.out.println( "Please input " + name + "'s center y coordinate");
                double centery = sc.nextDouble();
                System.out.println( "Please input " + name + "'s radius");
                double radius = sc.nextDouble();
                UI();
                shapeList.add(new Circle(name,centerx,centery,radius)) ;
                count++;
                UI();
                break;
            case "Square()":
                System.out.println("Please input your " + x + "'s name");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                System.out.println( "Please input " + name + "'s top left x coordinate");
                double sqx = sc.nextDouble();
                System.out.println( "Please input " + name + "'s top left y coordinate");
                double sqy = sc.nextDouble();
                System.out.println( "Please input " + name + "'s width");
                double sqw = sc.nextDouble();
                UI();
                shapeList.add(new Square(name,sqx,sqy,sqw));
                count++;
                UI();
                break;
            case "Delete()":
                System.out.println("Please input the name of the shape you want to delete: ");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                UI();
                int found = match(this.shapeList,name);
                if (found != -1){
                    shapeList.remove(found);
                    count --;
                    System.out.println("The shape " + name + " is deleted");
                }

                else System.out.println("The shape " + name + " is not exsit");
                UI();
                break;

            case "List()":
                System.out.println("Please input the name of the shape you want to see it information");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                found = match(this.shapeList,name);
                UI();
                if (found != -1){
                    System.out.println(shapeList.get(found).getName());
                }else System.out.println("There is no shape call: " + name + " please try again");
                UI();
                break;
            case "Group()":
                System.out.println("Please input the name of the grouped shape: ");
                sc = new Scanner(System.in);
                String groupname = sc.nextLine();
                boolean finish_input = false;
                Groupped groupped_name = new Groupped(groupname);

                while(finish_input == false){
                    System.out.println("Please input the name of the shape you want to group");
                    sc = new Scanner(System.in);
                    name = sc.nextLine();
                    found = match(this.shapeList,name);
                    if (found != -1){
                        shapeList.get(found).setlock();
                        groupped_name.addintogroup(shapeList.get(found));
                    }else System.out.println("There is no shape call: " + name);
                    UI();
                    System.out.println("Continue to add?\nType 'yes' to continue\nType 'no' to stop adding");
                    sc = new Scanner(System.in);
                    String Continue = sc.nextLine();
                    if(Continue.equals("yes")) continue;
                    else finish_input = true;
                }

                shapeList.add(groupped_name);
                groupped_name.printtest();
                UI();
                break;
            case "Ungroup()":
                System.out.println("Please input the name of the shape you want to ungroup");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                boolean havegp = false;
                for (int i = 0; i < shapeList.size(); i++){
                    if(shapeList.get(i) instanceof Groupped){
                        if (((Groupped) shapeList.get(i)).getgpname().equals(name)){
                            ((Groupped)shapeList.get(i)).ungroup();
                            havegp = true;
                            ((Groupped) shapeList.get(i)).printtest();
                        }
                    }
                }
                UI();
                if(!havegp) System.out.println("There is not grouped shape call:" + name + " please try again: ");
                UI();
                break;
        }

    }

}
