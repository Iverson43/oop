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
    public void listPrint(Shape s){
        System.out.println("Name: "+s.getName());
        if (s instanceof Circle) {
            System.out.println("Center: ("+s.getTopLeft().getX()+","+s.getTopLeft().getY()+")");
            System.out.println("Radius: ("+((Circle) s).getRadius());
        }else if (s instanceof Line){
            System.out.print("Two ends: ("+s.getTopLeft().getX()+","+s.getTopLeft().getY()+") and ("
                    +((Line) s).getEndX()+","+((Line) s).getEndY()+")");
        }else if (s instanceof Rectangle){
            System.out.println("Top-left corner: ("+s.getTopLeft().getX()+","+s.getTopLeft().getY()+")" );
            if (s instanceof Square){
                System.out.println("Side Length: "+ ((Square) s).getWidth());
            }else {
                System.out.println("Width: "+ ((Rectangle) s).getWidth());
                System.out.println("Height: "+ ((Rectangle) s).getHeight());
            }
        }else{
            //For grouped element
        }

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
                shapeList.add(new Line(name,startx,starty,endx,endy)) ;
                count++;
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
                shapeList.add(new Circle(name,centerx,centery,radius)) ;
                count++;
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
                shapeList.add(new Square(name,sqx,sqy,sqw));
                count++;
                break;
            case "Delete()":
                System.out.println("Please input the name of the shape you want to delete: ");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                int found = match(this.shapeList,name);
                if (found != -1){
                    shapeList.remove(found);
                    count --;
                    System.out.println("The shape " + name + " is deleted");
                }

                else System.out.println("The shape " + name + " is not exist");
                break;
            case "List()":
                System.out.println("Please input the name of the shape you want to see it information");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                found = match(this.shapeList,name);
                if (found != -1){
                    listPrint(shapeList.get(found));
                }
            case "ListAll()":
                    for (Shape each: shapeList){
                        listPrint(each);
                    }

        }

    }

}
