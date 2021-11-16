package hk.edu.polyu.comp.comp2021.clevis.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Clevis {

    ArrayList<Shape> shapeList;
    int count ;

    ArrayList<String> orderRecord;
    public String html = "log.html", txt = "log.txt";

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
        System.out.println("----------------------------------------------\n");
    }
    public void read( ) throws IOException {
        orderRecord = new ArrayList<>();
        File file = new File(txt);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
            this.orderRecord.add(sc.nextLine());
        }
        sc.close();
    }

    public ArrayList<Double> bounding_result(Shape x){
        double bounding_x = 0 , bounding_y= 0, bounding_w= 0, bounding_h= 0;
        ArrayList<Double> result = new ArrayList<Double>();
        if (x instanceof  Rectangle || x instanceof  Square){
            bounding_w = ((Rectangle) x).getHeight();
            bounding_h = ((Rectangle) x).getWidth();
            Coordination X = x.getTopLeft();
            bounding_x = X.getX();
            bounding_y = X.getY();
        }else if(x instanceof Circle){
            bounding_w = ((Circle) x).getRadius() *2;
            bounding_h = bounding_w;
            Coordination X = x.getTopLeft();
            bounding_x = X.getX()-((Circle) x).getRadius();
            bounding_y = X.getY()-((Circle) x).getRadius();
        }else if(x instanceof Line){
            Coordination X = x.getTopLeft();
            if (X.getX() == ((Line) x).getEndX() || X.getY() == ((Line) x).getEndY() ){
                result.add(-87.87);
                return result;
            }
            if (X.getY() > ((Line) x).getEndY()){
                bounding_h = X.getY() - ((Line) x).getEndY();
                bounding_y = ((Line) x).getEndY();
            }else {
                bounding_h = ((Line) x).getEndY() - X.getY();
                bounding_y = X.getY();
            }

            if (X.getX() > ((Line) x).getEndX()){
                bounding_w = X.getX() - ((Line) x).getEndX();
                bounding_x = ((Line) x).getEndX();
            }else{
                bounding_w = ((Line) x).getEndX() - X.getX();
                bounding_h = X.getX();
            }
        }
        result.add(bounding_x);
        result.add(bounding_y);
        result.add(bounding_x+bounding_w);
        result.add(bounding_y+bounding_h);
        result.add(bounding_w);
        result.add(bounding_h);
        return result;
    }

    public void writeLog(String command) throws IOException {
        File t = new File(txt);
        if (!t.exists()){
            FileWriter output = new FileWriter(t);
            output.append(command+"\n");
            output.close();
        }else {
            FileWriter output = new FileWriter(t,true);
            output.append(command+"\n");
            output.close();
        }

        read(); //Update the orderRecord

        FileWriter htmlwriter = new FileWriter(html);
        htmlwriter.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Index</th>\n" +
                "    <th>Operation</th>\n" +
                "  </tr>");
        int index = 1;
        for (String each: orderRecord){
            htmlwriter.write("  <tr>\n" +
                    "    <td>"+index+++"</td>\n" +
                    "    <td>"+each+"</td>\n" +
                    "  </tr>");
        }
        htmlwriter.write("</table>\n" +
                "</body>\n" +
                "</html>\n");
        htmlwriter.close();
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

        for (Shape k : groupedList){
            System.out.println(k.getName());
        }
        return groupedList;
    }

    public void add(String x){
        switch (x){
            case "Rectangle()":
                System.out.println("Please input your " + x + "'s name");
                Scanner sc = new Scanner(System.in);
                String name = sc.nextLine();
                while(match(this.shapeList,name)!= -1){
                    System.out.println("The shape with name: " + name +" has already used \n Please try again");
                    name = sc.nextLine();
                }
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
                while(match(this.shapeList,name)!= -1){
                    System.out.println("The shape with name: " + name +" has already used \n Please try again");
                    name = sc.nextLine();
                }
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
                while(match(this.shapeList,name)!= -1){
                    System.out.println("The shape with name: " + name +" has already used \n Please try again");
                    name = sc.nextLine();
                }
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
                while(match(this.shapeList,name)!= -1){
                    System.out.println("The shape with name: " + name +" has already used \n Please try again");
                    name = sc.nextLine();
                }
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
                if (shapeList.size() != 0){
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
                    else System.out.println("The shape " + name + " is not exist");
                    UI();
                    break;
                }else{
                    System.out.println("The shape is empty Please add some shape before using Delete()");
                    UI();
                    break;
                }

            case "List()":
                if(shapeList.size()!= 0 ){
                    System.out.println("Please input the name of the shape you want to see it information");
                    sc = new Scanner(System.in);
                    name = sc.nextLine();
                    int found = match(this.shapeList,name);
                    UI();
                    Shape temp;
                    if (found != -1 && shapeList.get(found).getlock() == false) {
                        availableList(shapeList.get(found),found);
                    }
                    UI();
                    break;
                }else{
                    System.out.println("The shape is empty Please add some shape before using List()");
                    UI();
                    break;
                }

            case "Listall()":
                if (shapeList.size()!=0){
                    for (int i = shapeList.size()-1; i>=0;i--){
                        if (shapeList.get(i).getlock() == false) availableList(shapeList.get(i),i);
                    }

                    UI();
                    break;
                }else{
                    System.out.println("The shape is empty Please add some shape before using Listall()");
                    UI();
                    break;
                }

            case "Move()":
                if (shapeList.size() != 0){
                    System.out.println("Please input the name of the shape you want to move: ");
                    sc = new Scanner(System.in);
                    name = sc.nextLine();
                    int found = match(this.shapeList,name);
                    UI();
                    System.out.println("Please input the units to move "+name+" horizontally: ");
                    double dx = sc.nextDouble();
                    System.out.println("Please input the units to move "+name+" vertically: ");
                    double dy = sc.nextDouble();
                    if (found != -1) {
                        Shape temp = shapeList.get(found);
                        if (temp instanceof Groupped)
                            moveGroup((Groupped) temp, dx, dy);
                        else if (temp.getlock()){
                            for (Groupped s: getGroupedList())
                                if (s.lock.contains(temp))
                                    moveGroup(s, dx, dy);
                        }else move(temp,dx, dy);
                    }
                    else{
                        System.out.println("There is no such shape call: " + name);
                    }
                    UI();
                    break;
                }else{
                    System.out.println("The shape is empty Please add some shape before using Move()");
                    UI();
                    break;
                }

            case "Pick_and_move()":
                if (shapeList.size() != 0 ){
                    System.out.println("Please input the x coordinate of picked point: ");
                    sc = new Scanner(System.in);
                    double px = sc.nextDouble();
                    System.out.println( "Please input the y coordinate of picked point: ");
                    double py = sc.nextDouble();
                    UI();
                    System.out.println("Please input the units to move the picked shape horizontally: ");
                    double dx = sc.nextDouble();
                    System.out.println("Please input the units to move the picked shape vertically: ");
                    double dy = sc.nextDouble();
                    //pick have not done
                    boolean ispick = false;
                    for (int i = shapeList.size() -1; i >=0; i-- ){
                        Shape pmtemp = shapeList.get(i);
                        if (pmtemp.getlock() == false){
                            if (pmtemp instanceof Circle){
                                Coordination pmx = pmtemp.getTopLeft();
                                double pmtemp1 = px-pmx.getX();
                                double pmtemp2 = py-pmx.getY();
                                double pmtemp3 = Math.sqrt(pmtemp1*pmtemp1+pmtemp2*pmtemp2)-((Circle) pmtemp).getRadius();
                                if (pmtemp3< 0.05 && pmtemp3>= 0){
                                    move(pmtemp,dx,dy);
                                }
                            }else if (pmtemp instanceof Line){

                            }
                        }
                    }
                    if (ispick == false){
                        String output1 = String.format("There is no successful pick with %.2f ,%.2f Please try again",px,py);
                        System.out.println(output1);
                    }
                    //move

                    UI();
                    break;
                }else{
                    System.out.println("The shape is empty Please add some shape before using Pick_and_move()");
                    UI();
                    break;
                }


            case "Group()":
                if (shapeList.size()!=0){
                    System.out.println("Please input the name of the grouped shape: ");
                    sc = new Scanner(System.in);
                    String groupname = sc.nextLine();
                    while(match(shapeList,groupname)!= -1){
                        System.out.println("The name: " + groupname+" has been used please try again");
                        groupname = sc.nextLine();
                    }
                    boolean finish_input = false;

                    Groupped groupped_name = new Groupped(groupname);

                    while(finish_input == false){
                        System.out.println("Please input the name of the shape you want to group");
                        sc = new Scanner(System.in);
                        name = sc.nextLine();
                        int found = match(this.shapeList,name);
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
                }else{
                    System.out.println("The shape is empty Please add some shape before using Group()");
                    UI();
                    break;
                }

            case "Ungroup()":
                if (shapeList.size() != 0){
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

                    if (hvgp == false ) System.out.println("The shape " + name + " is not exist");

                    break;
                }
                else{
                    System.out.println("The shape is empty Please add some shape before using Ungroup()");
                    UI();
                    break;
                }
            case "Boundingbox()":
                getGroupedList();
                if (shapeList.size() !=0){
                    System.out.println("Please input the shape name you check the Bounding box: ");
                    sc = new Scanner(System.in);
                    name = sc.nextLine();
                    while(match(shapeList,name) == -1){
                        System.out.println("There is no such shape call: " + name +" Please try again");
                        name = sc.nextLine();
                    }
                    double bounding_x, bounding_y, bounding_w, bounding_h;
                    Shape temp2 = shapeList.get(match(this.shapeList,name));
                    if (temp2 instanceof  Groupped == false){
                        ArrayList<Double> temp4 = bounding_result(temp2);
                        double x1 ,y1 ,x2 ,y2;
                        if (temp4.get(0) != -87.87){
                            x1 = (double) temp4.get(0);
                            y1 = (double) temp4.get(1);
                            x2 = (double) temp4.get(4);
                            y2 = (double) temp4.get(5);
                            String output = String.format("The top-left corner of shape%s is %.2f,%.2f",name,x1,y1);
                            System.out.println(output);
                            System.out.println("The width of bounding box is " + x2);
                            System.out.println("The hight of bounding box is " + y2);
                        }else{
                            System.out.println("The line with name: " + name + " is a Horizontal line or Vertical line. It don't have bounding box.");
                        }

                    }
                    else if(temp2 instanceof Groupped){
                        ArrayList<Shape> temp_gplist = ((Groupped) temp2).returnlist();
                        ArrayList<ArrayList> temp5 = new ArrayList<ArrayList>();
                        for (int i = 1; i < temp_gplist.size(); i++){
                            ArrayList<Double> temp4 = bounding_result(temp_gplist.get(i));
                            temp5.add(temp4);
                        }
                        double x1 ,y1 ,x2 ,y2;
                        double fx1 = 0 ,fy1 = 0 ,fx2=0 ,fy2 = 0;
                        for (int i = 0 ; i < temp5.size()-1; i++){
                            if((double)temp5.get(i).get(0) != -87.87){
                                x1 = (double) temp5.get(i).get(0);
                                y1 = (double) temp5.get(i).get(1);
                                x2 = (double) temp5.get(i).get(2);
                                y2 = (double) temp5.get(i).get(3);
                                for (int j = i+1 ; j < temp5.size(); j++){
                                    if (x1 <(double) temp5.get(j).get(0)){
                                        fx1 = (double) temp5.get(j).get(0);
                                    }else fx1 = x1;

                                    if (y1 < (double)temp5.get(j).get(1) ){
                                        fy1 = (double)temp5.get(j).get(1);
                                    }else fy1 = y1;

                                    if (x2 > (double)temp5.get(j).get(2) ){
                                        fx2 = x2;
                                    }else fx2 = (double)temp5.get(j).get(2);

                                    if (y2 > (double)temp5.get(j).get(3) ){
                                        fy2 = y2;
                                    }else fy2 = (double)temp5.get(j).get(3);
                                }
                            }
                            else{
                                continue;
                            }

                        }
                        String output = String.format("The top-left corner of shape%s is %.2f,%.2f",name,fx1,fy1);
                        double fw = 0 ,fh = 0;
                        if (fx1 > fx2){
                            fw = fx1 - fx2;
                        }else fw = fx2 - fx1;
                        if (fy1 > fy2){
                            fh = fy1 - fy2;
                        }else fw = fy2 - fy1;
                        System.out.println(output);
                        System.out.println("The width of bounding box is " + fw);
                        System.out.println("The hight of bounding box is " + fh);

                    }
                    UI();
                    break;
                }else{
                    System.out.println("The shape is empty Please add some shape before using Boundingbox()");
                    UI();
                    break;
                }
        }

    }

    public void delete(String name){
        for (Shape item: shapeList){
            if(name == item.getName()) {shapeList.remove(item);return;}
        }
    }

    /*public void list(String name){
        for (Shape item: shapeList){
            if(name == item.getName()){
                if (item instanceof Rectangle)
            }
        }
    }*/

    public void group(String n, String[] ungroupList){
        if(ungroupList.length==0 || ungroupList==null)
            throw new IllegalArgumentException();
        ArrayList<Shape> groupList = new ArrayList<>();
        for (String name: ungroupList){
            for (Shape item: shapeList){
                if(name == item.getName()){
                    groupList.add(item);
                    continue;
                }
            }
        }


    }
}
