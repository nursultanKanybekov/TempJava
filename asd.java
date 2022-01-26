import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.util.*;
import java.text.DecimalFormat;

import java.awt.event.*;
import javax.swing.*;



public class asd extends JFrame implements ActionListener{
    
    public static void main(String[] args){
        new asd();
    }


    JRadioButton r1,r2,firstFunc,secondFunc,thirdFunc;
    JLabel la,lb,lc,ls,lf,stepl,epsilonl;
    JButton b,root;
    JTextField fa,fb,fc,fs;
    JTextArea area;
    Graphics2D gr;
    JScrollPane scroll;
    DrawStuff draw;
    Calc ans;

    int s,f;
    float iter;  
    int option=0;  

    asd(){

        super("Function Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        area=new JTextArea();
        area.setBounds(260,20,200,300);
        add(area);
    
        scroll=new JScrollPane(area);
        scroll.setBounds(260,20,150,300);
        add(scroll);

        b=new JButton("Go!");
        b.setBounds(15,320,130,20);
        add(b);
        b.addActionListener(this);
        root=new JButton("Draw function!");
        root.setBounds(15,350,130,20);
        add(root);
        root.addActionListener(this);


        r1=new JRadioButton("y1=k1*x+b1");
        //r1.setBounds(10,20,80,20);
        r1.setBounds(15,20,160,22);
        r1.setSelected(true);
        add(r1);

        r2=new JRadioButton("y2=k2*x+b2");
        //r2.setBounds(30,60,80,20);
        r2.setBounds(15,50,160,22);
        add(r2);

        firstFunc=new JRadioButton("Cross by function!");
        firstFunc.setBounds(10,230,160,20);
        add(firstFunc);
        firstFunc=new JRadioButton("Find cross!");
        firstFunc.setBounds(10,260,160,20);
        add(firstFunc);
        firstFunc=new JRadioButton("Answer by function");
        firstFunc.setBounds(10,290,160,20);
        add(firstFunc);

        ButtonGroup bg=new ButtonGroup();
        bg.add(r1); bg.add(r2); 
        add(r1); add(r2);

         fa=new JTextField();
        fa.setBounds(90,110,60,20);
        add(fa);
        fb=new JTextField();
        fb.setBounds(90,140,60,20);
        add(fb);
        fc=new JTextField();
        fc.setBounds(90,170,60,20);
        add(fc);
        fs=new JTextField();
        fs.setBounds(90,200,60,20);
        add(fs);
       /* ff=new JTextField();
        ff.setBounds(90,230,60,20);
        add(ff);

        stepf=new JTextField();
        stepf.setBounds(90,260,60,20);
        add(stepf);

        epsilonf=new JTextField();
        epsilonf.setBounds(90,290,60,20);
        add(epsilonf);
*/
        la=new JLabel("k1");
        la.setBounds(15,110,60,20);
        add(la);
        lb=new JLabel("k2");
        lb.setBounds(15,140,60,20);
        add(lb);
        lc=new JLabel("b1");
        lc.setBounds(15,170,60,20);
        add(lc);
        ls=new JLabel("b2");
        ls.setBounds(15,200,60,20);
        add(ls);
     /*   lf=new JLabel("Final");
        lf.setBounds(15,230,60,20);
        add(lf); 

        stepl=new JLabel("Step");
        stepl.setBounds(15,260,60,20);
        add(stepl);

        epsilonl=new JLabel("Epsilon");
        epsilonl.setBounds(15,290,60,20);
        add(epsilonl);*/


        draw=new DrawStuff();
        this.getContentPane().add(draw);
        
        ans=new Calc();
        // add(new DrawStuff(), BorderLayout.CENTER);
        setVisible(true);
        setSize(1000,1000);

       

}

                
       


    public void actionPerformed(ActionEvent e){
       
        if(r1.isSelected())option=1;
        else if(r2.isSelected())option=2;     

        if(e.getSource()==b){       
            area.setText("x      y\n");
            area.append(ans.display(option));
            ans.setField(fa,fb,fc,fs);
            ans.resetArrays();
            draw.resetArr();
    
            draw.setArrays(ans.xmas(),ans.ymas(option));
            draw.setDelim(true);
            draw.setButton(true);
            draw.setRoot(false);
            draw.repaint();
        
        }
        if(e.getSource()==root){
            // ans.bisec(option);
            int  s=Integer.parseInt(fs.getText());
            //int f=Integer.parseInt(ff.getText());
            draw.setRoot(true);
            draw.setBisec(ans.bisec(s,f,option));
            ans.resetArrays();
            draw.resetArr();
            draw.setArrays(ans.xmas(),ans.ymas(option));
            draw.setDelim(true);

            draw.repaint();

        }
        

 }


         class DrawStuff extends JComponent{
                Vector<Double> masx = new Vector<>();
                Vector<Double> masy = new Vector<>();

              
            public void setArrays(Vector<Double>masx,  Vector<Double>masy){
                    for(int i=0; i<masx.size(); i++){
                        this.masx.add(masx.get(i));
                        this.masy.add(masy.get(i));

                }
            }
            public void resetArr(){
                this.masy.clear();
                this.masx.clear();
            }
           

            boolean button=false;
            public void setButton(boolean b){
                button = b;
               
            }

            boolean delim=false;
            public void setDelim(boolean b){
                delim=b;
                
            }

            boolean root=false;
            public void setRoot(boolean b){
                root=b;
            }
            double otvet;
            public void setBisec(double ans){
                otvet=ans;
            }

             public void paintComponent(Graphics g){
                
                gr = (Graphics2D) g;
                gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Shape yline = new Line2D.Float(700, 90, 700, 420);
                Shape xline = new Line2D.Float(540,250,870,250);
                    
                gr.setColor(Color.RED);
                gr.draw(xline);
                gr.draw(yline);

                double xmax=-Double.MAX_VALUE;
                double ymax=-Double.MAX_VALUE;
                double  endmax=0.0;
                double step;



                for(int i=0; i<masx.size(); i++){
                    System.out.println(masy.get(i));
                    if(Math.abs(masx.get(i))>xmax)xmax=Math.abs(masx.get(i));
                    if(Math.abs(masy.get(i))>ymax)ymax=Math.abs(masy.get(i));
                    
                }
                // genmin=(Math.abs(xmin)>Math.abs(ymin)?Math.abs(xmin):Math.abs(ymin));
                // genmax=(Math.abs(xmax)>Math.abs(ymax)?Math.abs(xmax):Math.abs(ymax));
                // endmax=(Math.abs(genmax)>Math.abs(genmin)?Math.abs(genmax):Math.abs(genmin));
                endmax=(xmax>ymax? xmax:ymax);
                System.out.println("MAX "+endmax);
                step=(endmax)/5.0;
                
                DecimalFormat value = new DecimalFormat("#.#");
                
            

                 for(float i=550; i<870; i+=30){
                    gr.draw(new Line2D.Float(i , 255 , i , 245));
                 }

                 for(float i=100; i<420; i+=30){
                    gr.draw(new Line2D.Float(695 , i , 705 , i));
                 }
            


                if(delim){

                //x delimetr
                
                Font myFont = new Font ("Calibri", 1, 12);
                gr.setFont (myFont);

                double maxx=0;
                double maxy=endmax;


                for(float i=700; i<870; i+=30){
                    gr.draw(new Line2D.Float(i , 255 , i , 245));
                   
                        gr.drawString((value.format(maxx)),i,270);
                        maxx+=step;

                    
                }
                maxx-=step;
                maxx=-maxx;
               
                   for(float i=550; i<700; i+=30){
                    gr.draw(new Line2D.Float(i , 255 , i , 245));
                   
                        gr.drawString((value.format(maxx)),i,270);
                        maxx+=step;

                    
                }
                //y delimetr
              
                for(float i=100; i<250; i+=30){
                    gr.draw(new Line2D.Float(695 , i , 705 , i));
                    gr.drawString(value.format(maxy),680,i);
                    maxy-=step;
                 
                    
                }
                maxy=0;
                for(float i=250; i<420; i+=30){
                    gr.draw(new Line2D.Float(695 , i , 705 , i));
                    if(maxy!=0)gr.drawString(value.format(maxy),680,i);
                    maxy-=step;
                 
                    
                }
            }
                delim=false;
                

                    if(button){
                    //    System.out.println("NEW");         
                    for (int i=0 ;  i<masx.size(); i++){
                        gr.setColor(Color.blue);
                        // System.out.println(masx.get(i));
                        gr.draw(new Ellipse2D.Double(700+masx.get(i)*(30/step), 250-masy.get(i)*30/step, 4, 4));
                        // if(masy.get(i)==0){
                        //     gr.setColor(Color.BLUE);
                        //     gr.draw(new Rectangle2D.Double(700+masx.get(i)*(30/step), 250-masy.get(i)*30/step, 4, 4));
                        // }
                    }
                }

                if(root){
                    int x=800,y=500;
                            if(otvet!=100000.0){
                            Shape c= new Ellipse2D.Double(700+otvet*(30/step), 250, 5, 5);
                            gr.setPaint(Color.black);
                            gr.fill(c);
                            gr.draw(c);

                            gr.drawString("Root: "+otvet,x,y);
                        }
                        }
                    
                        }
                    }

                }
            
            

    
    



class Calc{
        double a,b,c;
        int s,f = 5;
        double iter = 5,epsilon = 5;
        public void setField(JTextField fa, JTextField fb, JTextField fc, JTextField fs){

        a=Float.parseFloat(fa.getText());
        b=Float.parseFloat(fb.getText());
        c=Float.parseFloat(fc.getText());
        s=Integer.parseInt(fs.getText());
    }

  

    double answer(int option,double iter){
        double ans=0;
        switch(option){
            case 1:
                ans=a*iter+b;
                break;

            case 2:
                ans=iter*iter+b*iter+c;
                break;

            case 3:
                ans=iter*iter*iter+b*iter+c*iter;
                break;
                
        }

        return ans; 

    }
        
    String  display(int option){
        String str="";
         for(double i=s; i<f; i+=iter){
            str+=(i+"      "+answer(option,i)+'\n');
        }
        return str;
    }

    Vector<Double> masx = new Vector<>();
    Vector<Double> masy = new Vector<>();

    public Vector<Double>xmas(){
        for(double i=s; i<f; i+=iter){
            masx.add(i);

        }

            return masx;
    }

    public Vector<Double> ymas(int option){
         for(double i=s; i<f; i+=iter){
            masy.add(answer(option,i)); 
    }

        return masy;
    }
     public void resetArrays(){
                this.masx.clear();
                this.masy.clear();
            }


   

       public double bisec(double a, double b,int option) 
    { 
        double c = 100000.0; 
        // if (answer(option,a) * answer(option,b) >= 0) 
        // { 
        //     System.out.println("You have not assumed"
        //                 + " right a and b"); 
        //     return c; 
        // } 
  
         c = a; 
        while ((b-a) > epsilon) 
        { 
            // Find middle point 
            c = (a+b)/2; 
  
            // Check if middle point is root 
            if (answer(option,c) == 0.0) 
                break; 
  
            // Decide the side to repeat the steps 
            else if (answer(option,c)*answer(option,a) < 0) 
                b = c; 
            else
                a = c; 
        }
        return c; 

    
    }
}


    

