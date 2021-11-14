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
        }
        UI();
    }
    public void availableList(Shape s, int index){
        s = shapeList.get(index);
        if (!s.getlock()) listPrint(s);
        if (s instanceof Groupped){
            for (int i = ((Groupped) s).lock.size()-1;i<=0;i--) listPrint(((Groupped) s).lock.get(i));
        }
    }
    public void move(Shape s, double dx, double dy){
        s.move(dx,dy);
        if (s instanceof Line) ((Line) s).moveEnd(dx,dy);
    }
    public void moveGroup(Groupped s, double dx, double dy){
        for (Shape each: s.lock) move(each,dx,dy);
    }
    public ArrayList<Groupped> getGroupedList(){
        ArrayList<Groupped> groupedList = new ArrayList<Groupped>();
        for (Shape s: shapeList)
            if (s instanceof Groupped) groupedList.add((Groupped)s);
        return groupedList;
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
                UI();
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
                int found = match(this.shapeList,name);
                UI();
                if (found != -1 && shapeList.get(found).getlock() == false){
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
                Shape temp;
                if (found != -1 && shapeList.get(found).getlock() == false) {
                    availableList(shapeList.get(found),found);
                }
                UI();
                break;
            case "Listall()":
                for (int i = shapeList.size()-1; i>=0;i--){
                    if (shapeList.get(i).getlock() == false) availableList(shapeList.get(i),i);
                }

                UI();
                break;
            case "Move()":
                System.out.println("Please input the name of the shape you want to move: ");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                found = match(this.shapeList,name);
                UI();
                System.out.println("Please input the units to move "+name+" horizontally: ");
                double dx = sc.nextDouble();
                System.out.println("Please input the units to move "+name+" vertically: ");
                double dy = sc.nextDouble();
                if (found != -1) {
                    temp = shapeList.get(found);
                    if (temp instanceof Groupped)
                        moveGroup((Groupped) temp, dx, dy);
                    else if (temp.getlock()){
                        for (Groupped s: getGroupedList())
                            if (s.lock.contains(temp))
                                moveGroup(s, dx, dy);
                    }else move(temp,dx, dy);
                }
                UI();
                break;
            case "Pick_and_move()":
                System.out.println("Please input the x coordinate of picked point: ");
                sc = new Scanner(System.in);
                double px = sc.nextDouble();
                System.out.println( "Please input the y coordinate of picked point: ");
                double py = sc.nextDouble();
                UI();
                System.out.println("Please input the units to move the picked shape horizontally: ");
                dx = sc.nextDouble();
                System.out.println("Please input the units to move the picked shape vertically: ");
                dy = sc.nextDouble();
                //pick have not done

                //move
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
                    if (found != -1 && shapeList.get(found).getlock() == false){
                        shapeList.get(found).setlock();
                        groupped_name.addintogroup(shapeList.get(found));
                    }else System.out.println("There is no shape call: " + name + "or the shape " + name +" is locked");
                    UI();
                    System.out.println("Continue to add?\nType 'yes' to continue\nType 'no' to stop adding");
                    sc = new Scanner(System.in);
                    String Continue = sc.nextLine();
                    if(Continue.equals("yes")) continue;
                    else finish_input = true;
                }

                shapeList.add(groupped_name);
                UI();
                break;
            case "Ungroup()":
                System.out.println("Please input the name of the shape you want to ungroup");
                sc = new Scanner(System.in);
                name = sc.nextLine();
                boolean hvgp = false;
                for (int i = 0; i < shapeList.size(); i++){
                    if(shapeList.get(i) instanceof Groupped){
                        if (((Groupped) shapeList.get(i)).getgpname().equals(name)){
                            ((Groupped)shapeList.get(i)).ungroup();
                            hvgp = true;
                        }
                    }
                }
                UI();
                if (!hvgp) System.out.println("There is no grouped shape call: " + name + " please try again");
                else System.out.println("The grouped shape: " + name +" has been unlocked");
                UI();

                if (found == false ) System.out.println("The shape " + name + " is not exist");

                break;
        }

    }

}
