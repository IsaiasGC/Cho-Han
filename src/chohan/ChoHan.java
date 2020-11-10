/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chohan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author Isaias
 */
public class ChoHan implements ActionListener{
    
    private final Interfaz interfaz;
    private int dado1;
    private int dado2;
    private double saldo=0;
    
    public ChoHan(){
        interfaz=new Interfaz(this);
        interfaz.setVisible(true);
    }
    public void jugar(){
        double apuesta=interfaz.getApuesta();
        if(apuesta<=saldo ){
            if(apuesta>=0){
                tirarDados();
                verificar(apuesta);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No tiene saldo Suficiente", "Error", 0);
        }
    }
    public void tirarDados(){
        dado1=getValorDado();
        dado2=getValorDado();
        interfaz.setDados(dado1, dado2);
    }
    public int getValorDado(){
        double num=Math.random();
        int dado;
        if(num>=0 && num<=0.1666666)
            dado=1;
        else if(num>0.1666666 && num<=0.3333333)
            dado=2;
        else if(num>=0.3333333 && num<=0.5)
            dado=3;
        else if(num>=0.5 && num<=0.6666666)
            dado=4;
        else if(num>=0.6666666 && num<=0.8333333)
            dado=5;
        else
            dado=6;
        
        return dado;
    }
    public void verificar(double apuesta){
        int suma=dado1+dado2;
        String apostado=interfaz.getChoHan();
        String gana="";
        if(suma%2==0){
            gana="CHO";
        }else{
            gana="HAN";
        }
        if(gana.equals(apostado)){
            JOptionPane.showMessageDialog(null, "Has Ganado!!", "FELICIDADES", 1);
            saldo+=apuesta;
        }else{
            JOptionPane.showMessageDialog(null, "Has Perdido", "Lo sentimos", 1);
            saldo-=apuesta;
        }
        interfaz.limpiarDados();
        interfaz.setSaldo(saldo);
    }
    public void setSaldo(){
        double saldo;
        try{
            saldo=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el Saldo a Aumentar"));
            this.saldo+=saldo;
            interfaz.setSaldo(this.saldo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ingrese cantidad valida", "Error", 0);
            setSaldo();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChoHan();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("jugar")){
            if(interfaz.seleccionado())
                jugar();
            else
                JOptionPane.showMessageDialog(null, "Seleccione a que le apuesta(CHO/HAN)", "Error", 2);
        }else{
            setSaldo();
        }
    }
    
}
